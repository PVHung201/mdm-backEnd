package com.mdm.equipmentservice.mapper;

import com.mdm.equipmentservice.model.dto.base.EquipmentDto;
import com.mdm.equipmentservice.model.dto.base.EquipmentExcelDto;
import com.mdm.equipmentservice.model.dto.form.*;
import com.mdm.equipmentservice.model.dto.fullinfo.EquipmentFullInfoDto;
import com.mdm.equipmentservice.model.dto.list.*;
import com.mdm.equipmentservice.model.entity.*;
import com.mdm.equipmentservice.service.FileStorageService;
import com.mdm.equipmentservice.util.CommonUtil;
import com.mdm.equipmentservice.util.MapperUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        IdToEntityMapper.class,
        RepairTicketMapper.class,
        ReportBrokenTicketMapper.class,
        LiquidationTicketMapper.class,
        TransferTicketMapper.class,
        DepartmentMapper.class,
        InspectionTicketMapper.class,
        SupplierMapper.class,
        ProjectMapper.class,
        InventoryMapper.class,
        EquipmentUnitMapper.class,
        MaintenanceTicketMapper.class,
        EquipmentCategoryMapper.class,
        HandoverTicketMapper.class
}, imports = {Set.class, CollectionUtils.class, FileDescription.class, EquipmentStatus.class, CommonUtil.class}
)
public abstract class EquipmentMapper {

    @Autowired
    protected FileStorageService fileStorageService;

    @Autowired
    protected MapperUtil mapperUtil;

    @Autowired
    protected IdToEntityMapper idToEntityMapper;

    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(source = "category.id", target = "categoryId")
    public abstract EquipmentDto toDto(Equipment equipment);

    @Mapping(
            target = "attachments",
            expression = "java(fileStorageService.getAllFilesOfAnEntityWithoutData(equipment.getClass().getSimpleName(),equipment.getId(),FileDescription.IMAGE))"
    )
    public abstract EquipmentFullInfoDto toFullInfoDto(Equipment equipment);

    @Mapping(target = "unit", source = "unitId")
    @Mapping(target = "department", source = "upsertEquipmentForm.departmentId")
    @Mapping(target = "category", source = "upsertEquipmentForm.categoryId")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public abstract Equipment toEntity(UpsertEquipmentForm upsertEquipmentForm);

    @Mapping(target = "unit", source = "upsertEquipmentForm.unitId")
    @Mapping(target = "department", source = "upsertEquipmentForm.departmentId")
    @Mapping(target = "supplier", source = "upsertEquipmentForm.supplierId")
    @Mapping(target = "project", source = "upsertEquipmentForm.projectId")
    @Mapping(target = "category", source = "upsertEquipmentForm.categoryId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public abstract Equipment toEntity_updateCase(UpsertEquipmentForm upsertEquipmentForm, @MappingTarget Equipment equipment);

    @Mapping(target = "regularRadiationInspection", ignore = true)
    @Mapping(target = "regularExternalQualityAssessment", ignore = true)
    @Mapping(target = "regularClinicEnvironmentInspection", ignore = true)
    @Mapping(target = "regularCVRadiation", ignore = true)
    @Mapping(source = "riskLevel", target = "riskLevel")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "unit.id", target = "unitName", qualifiedByName = "toUnitName")
    @Mapping(source = "department.id", target = "departmentName", qualifiedByName = "toDepartmentName")
    @Mapping(source = "category.id", target = "categoryName", qualifiedByName = "toCategoryName")
    @Mapping(source = "category.id", target = "categoryGroupName", qualifiedByName = "toCategoryGroupName")
    public abstract EquipmentExcelDto toExcelDto(EquipmentListDto equipmentListDto);


    @Named("toUnitName")
    public String toUnitName(Long unitId) {
        EquipmentUnit unit = idToEntityMapper.toEquipmentUnit(unitId);
        return unit == null ? null : unit.getName();
    }

    @Named("toDepartmentName")
    public String toDepartmentName(Long departmentId) {
        Department department = idToEntityMapper.toDepartment(departmentId);
        return department == null ? null : department.getName();
    }

    @Named("toCategoryName")
    public String toCategoryName(Long categoryId) {
        EquipmentCategory category = idToEntityMapper.toCategory(categoryId);
        return category == null ? null : category.getName();
    }

    @Named("toSupplierName")
    public String toSupplierName(Long supplierId) {
        return idToEntityMapper.toSupplier(supplierId).getName();
    }

    @Named("toProjectName")
    public String toProjectName(Long projectId) {
        return idToEntityMapper.toProject(projectId).getName();
    }

    @Named("toCategoryGroupName")
    public String toCategoryGroupName(Long categoryId) {
        EquipmentCategory category = idToEntityMapper.toCategory(categoryId);
        return category == null ? null : category.getGroup().getName();
    }


    @Mapping(target = "images", ignore = true)
    public abstract EquipmentListDto toListDto(Equipment equipment);

    public Page<EquipmentListDto> toPageOfListDto(Page<Equipment> equipmentPage) {
        return (Page<EquipmentListDto>) mapperUtil.mapToIterableHasAttachmentsDto(equipmentPage, EquipmentListDto.class, null, this::toListDto);
    }


    public abstract EquipmentListRepairDto toListRepairDto(Equipment equipment);

    public abstract EquipmentListMaintenanceDto toListMaintenanceDto(Equipment equipment);

    public abstract EquipmentListInspectionDto toListInspectionDto(Equipment equipment);

    public abstract EquipmentListLiquidationDto toListLiquidationDto(Equipment equipment);

    public abstract EquipmentListTransferDto toListTransferDto(Equipment equipment);

    public abstract EquipmentListHandoverDto toListHandoverDto(Equipment equipment);

    public abstract EquipmentListReportBrokenDto toListReportBrokenDto(Equipment equipment);

    @Mapping(target = "department", source = "handoverTicket.department")
    @Mapping(target = "status", expression = "java(EquipmentStatus.PENDING_HANDOVER)")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "handoverTickets", expression = "java(CollectionUtils.isEmpty(equipment.getHandoverTickets()) ? Set.of(handoverTicket) : (Set<HandoverTicket>) CollectionUtils.union(equipment.getHandoverTickets(), Set.of(handoverTicket)))")
    public abstract Equipment createHandoverTicket(@MappingTarget Equipment equipment, CreateHandoverTicketForm createHandoverTicketForm,
                                                   HandoverTicket handoverTicket);

    @Mapping(target = "status", expression = "java(EquipmentStatus.PENDING_ACCEPT_LIQUIDATION)")
    @Mapping(
            target = "liquidationTickets",
            expression = "java(CollectionUtils.isEmpty(equipment.getLiquidationTickets()) ? Set.of(liquidationTicket) : (Set<LiquidationTicket>) CollectionUtils.union(equipment.getLiquidationTickets(), Set.of(liquidationTicket)))"
    )
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Equipment addLiquidationTicket(@MappingTarget Equipment equipment, LiquidationTicket liquidationTicket);

    @Mapping(target = "status", expression = "java(acceptHandoverTicketForm.getIsApproved() ? EquipmentStatus.IN_USE : EquipmentStatus.NEW)")
    @Mapping(target = "department", expression = "java(acceptHandoverTicketForm.getIsApproved() ? equipment.getDepartment() : null)")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Equipment acceptHandoverTicket(@MappingTarget Equipment equipment, AcceptHandoverTicketForm acceptHandoverTicketForm);

    @Mapping(target = "status", expression = "java(acceptReportBrokenTicketForm.getIsApproved() ? EquipmentStatus.BROKEN : EquipmentStatus.IN_USE)")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Equipment acceptReportBrokenTicket(@MappingTarget Equipment equipment, AcceptReportBrokenTicketForm acceptReportBrokenTicketForm);

    @Mapping(target = "status", expression = "java(acceptRepairTicketForm.getIsAccepted() ? EquipmentStatus.REPAIRING : EquipmentStatus.BROKEN)")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Equipment acceptRepairTicket(@MappingTarget Equipment equipment, AcceptRepairTicketForm acceptRepairTicketForm);
}
