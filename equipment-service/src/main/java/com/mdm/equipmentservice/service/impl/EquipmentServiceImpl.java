package com.mdm.equipmentservice.service.impl;

import com.mdm.equipmentservice.exception.ResourceNotFoundException;
import com.mdm.equipmentservice.exception.ValidationFailedException;
import com.mdm.equipmentservice.mapper.EquipmentMapper;
import com.mdm.equipmentservice.mapper.EquipmentSupplyUsageMapper;
import com.mdm.equipmentservice.model.dto.base.EquipmentDto;
import com.mdm.equipmentservice.model.dto.base.EquipmentSupplyUsageDto;
import com.mdm.equipmentservice.model.dto.form.AttachSupplyForm;
import com.mdm.equipmentservice.model.dto.form.UpsertEquipmentForm;
import com.mdm.equipmentservice.model.dto.fullinfo.EquipmentFullInfoDto;
import com.mdm.equipmentservice.model.dto.list.EquipmentListDto;
import com.mdm.equipmentservice.model.entity.*;
import com.mdm.equipmentservice.model.repository.EquipmentRepository;
import com.mdm.equipmentservice.model.repository.EquipmentSupplyUsageRepository;
import com.mdm.equipmentservice.query.param.GetEquipmentsQueryParam;
import com.mdm.equipmentservice.query.predicate.EquipmentPredicate;
import com.mdm.equipmentservice.service.EquipmentService;
import com.mdm.equipmentservice.service.FileStorageService;
import com.mdm.equipmentservice.util.MessageUtil;
import com.mdm.equipmentservice.util.UniqueValidationUtil;
import com.mdm.equipmentservice.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Import({ValidationUtil.class, UniqueValidationUtil.class})
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    private final MessageUtil messageUtil;

    private final ValidationUtil validationUtil;

    private final UniqueValidationUtil uniqueValidationUtil;

    private final FileStorageService fileStorageService;

    private final EquipmentSupplyUsageRepository equipmentSupplyUsageRepository;

    private final EquipmentSupplyUsageMapper equipmentSupplyUsageMapper;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper, MessageUtil messageUtil,
                                ValidationUtil validationUtil, UniqueValidationUtil uniqueValidationUtil,
                                FileStorageService fileStorageService,
                                EquipmentSupplyUsageRepository equipmentSupplyUsageRepository, EquipmentSupplyUsageMapper equipmentSupplyUsageMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
        this.messageUtil = messageUtil;
        this.validationUtil = validationUtil;
        this.uniqueValidationUtil = uniqueValidationUtil;
        this.fileStorageService = fileStorageService;
        this.equipmentSupplyUsageRepository = equipmentSupplyUsageRepository;
        this.equipmentSupplyUsageMapper = equipmentSupplyUsageMapper;
    }

    @Override
    public Page<EquipmentListDto> getEquipments(GetEquipmentsQueryParam getEquipmentsQueryParam, Pageable pageable) {
//        return equipmentRepository.findAll(EquipmentPredicate.getEquipmentsPredicate(getEquipmentsQueryParam), pageable).map(equipmentMapper::toListDto);
        return equipmentMapper.toPageOfListDto(equipmentRepository.findAll(EquipmentPredicate.getEquipmentsPredicate(getEquipmentsQueryParam), pageable));
    }

    @Override
    public EquipmentFullInfoDto getEquipmentById(Long equipmentId) throws AccessDeniedException {
        Equipment equipment =
                equipmentRepository.findById(equipmentId).orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("equipmentNotFound")));
        return equipmentMapper.toFullInfoDto(equipment);

    }

    @Override

    public EquipmentDto createEquipment(UpsertEquipmentForm upsertEquipmentForm, MultipartFile image) {
        Equipment equipment = equipmentMapper.toEntity(upsertEquipmentForm);
        uniqueValidationUtil.validateUnique_throwExceptionIfHasError(
                equipment,
                equipmentRepository,
                messageUtil.getMessage("equipmentUniquenessValidationFailed")
        );
        equipment.setStatus(EquipmentStatus.NEW);
        equipment = equipmentRepository.save(equipment);
        Equipment finalEquipment = equipment;
        CompletableFuture.runAsync(() -> fileStorageService.uploadMultipleFiles(
                Equipment.class.getSimpleName(),
                finalEquipment.getId(),
                FileDescription.IMAGE,
                image
        ));
        return equipmentMapper.toDto(equipment);
    }

    @Override
    public EquipmentDto updateEquipment(Long equipmentId, UpsertEquipmentForm upsertEquipmentForm, MultipartFile image) {
        validationUtil.validateNotFound(equipmentId, equipmentRepository, messageUtil.getMessage("equipmentNotFound"));
        Equipment equipment =
                equipmentRepository.findById(equipmentId).orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("equipmentNotFound")));
        equipment = equipmentMapper.toEntity_updateCase(upsertEquipmentForm, equipment);
        uniqueValidationUtil.validateUnique_throwExceptionIfHasError(
                equipment,
                equipmentRepository,
                messageUtil.getMessage("equipmentUniquenessValidationFailed")
        );
        equipment = equipmentRepository.save(equipment);
        if (image != null && !image.isEmpty()) {
            fileStorageService.deleteAllFilesOfAnEntity(Equipment.class.getSimpleName(), equipmentId, FileDescription.IMAGE);
            fileStorageService.uploadMultipleFiles(Equipment.class.getSimpleName(), equipmentId, FileDescription.IMAGE, image);

        }
        return equipmentMapper.toDto(equipment);
    }


    @Override
    public void deleteEquipment(Long equipmentId) {
        validationUtil.validateNotFound(equipmentId, equipmentRepository, messageUtil.getMessage("equipmentNotFound"));
        equipmentRepository.deleteById(equipmentId);
    }

    @Override

    public void createMultipleEquipment(List<UpsertEquipmentForm> upsertEquipmentForms) {
        validationUtil.validateConstraintsOnListOfDto_andThrowExceptionIfHasError(upsertEquipmentForms, messageUtil.getMessage("equipmentValidationFailed"));
        //TODO: Optimize the process to check the uniqueness
        List<Equipment> equipments = upsertEquipmentForms.parallelStream().map(equipmentMapper::toEntity).toList();
        List<String> uniquenessValidationErrorMessages = uniqueValidationUtil.validateUniqueOnListOfEntity(equipments, equipmentRepository);
        if (!uniquenessValidationErrorMessages.isEmpty()) {
            throw new ValidationFailedException(messageUtil.getMessage("equipmentListUniquenessValidationFailed"), uniquenessValidationErrorMessages);
        }
        equipmentRepository.saveAll(equipments);
    }

    @Override

    public void deleteMultipleEquipment(List<Long> equipmentIds) {
        if (equipmentIds.isEmpty()) return;
        equipmentRepository.deleteAllById(equipmentIds);

    }

    @Override
    public Page<EquipmentFullInfoDto> statisticEquipments(GetEquipmentsQueryParam getEquipmentsQueryParam, Pageable pageable) {
        Page<Equipment> equipmentPage = equipmentRepository.findAll(EquipmentPredicate.getEquipmentsPredicate(getEquipmentsQueryParam), pageable);
        return equipmentPage.map(equipmentMapper::toFullInfoDto);
    }

    @Override
    public EquipmentSupplyUsageDto attachSupplies(AttachSupplyForm attachSupplyForm) {
        EquipmentSupplyUsage equipmentSupplyUsage = equipmentSupplyUsageMapper.toEntity(attachSupplyForm);
        Supply supply = equipmentSupplyUsage.getSupply();
        if (supply.getAmount() < equipmentSupplyUsage.getAmount() + supply.getAmountUsed()) {
            throw new ValidationFailedException(messageUtil.getMessage("supplyAmountNotEnough"), null);
        }
        supply.setAmountUsed(supply.getAmountUsed() + equipmentSupplyUsage.getAmount());
        equipmentSupplyUsage = equipmentSupplyUsageRepository.save(equipmentSupplyUsage);
        return equipmentSupplyUsageMapper.toDto(equipmentSupplyUsage);
    }


}
