package com.mdm.equipmentservice.model.dto.fullinfo;

import com.mdm.equipmentservice.model.dto.base.*;
import com.mdm.equipmentservice.model.entity.EquipmentStatus;
import com.mdm.equipmentservice.model.entity.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentFullInfoDto implements Serializable {

    private Long id;

    private String name;

    private String model;

    private String serial;

    private String code;

    private String hashCode;

    private RiskLevel riskLevel;

    private String technicalParameter;

    private LocalDateTime warehouseImportDate;

    private Year yearOfManufacture;

    private Year yearInUse;

    private String configuration;

    private Double importPrice;

    private Double initialValue;

    private Double annualDepreciation;

    private String usageProcedure;

    private String note;

    private EquipmentStatus status;

    private String manufacturer;

    private String manufacturingCountry;

    private EquipmentCategoryFullInfoDto category;

    private DepartmentFullInfoDto department;

    private Integer regularMaintenance;

    private Integer regularInspection;

    private LocalDateTime jointVentureContractExpirationDate;

    private LocalDateTime warrantyExpirationDate;

    private Set<EquipmentSupplyUsageDto> equipmentSupplyUsages;

    private ProjectDto project;

    private Set<InspectionTicketFullInfoDto> inspectionTickets;

    private SupplierFullInfoDto supplier;

    private Set<HandoverTicketFullInfoDto> handoverTickets;

    private Set<InventoryDto> inventories;

    private Boolean deleted = false;

    private List<FileStorageDto> attachments;

    private EquipmentUnitDto unit;

    private Set<RepairTicketFullInfoDto> repairTickets;

    private Set<ReportBrokenTicketFullInfoDto> reportBrokenTickets;

    private Set<MaintenanceTicketFullInfoDto> maintenanceTickets;

    private Set<TransferTicketFullInfoDto> transferTickets;

    private Set<LiquidationTicketFullInfoDto> liquidationTickets;
}
