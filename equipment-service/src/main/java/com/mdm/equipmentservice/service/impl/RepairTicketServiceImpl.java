package com.mdm.equipmentservice.service.impl;

import com.mdm.equipmentservice.event.RepairTicketAcceptanceTestingEvent;
import com.mdm.equipmentservice.event.RepairTicketAcceptedEvent;
import com.mdm.equipmentservice.event.RepairTicketCreatedEvent;
import com.mdm.equipmentservice.event.RepairTicketUpdatedEvent;
import com.mdm.equipmentservice.exception.ResourceNotFoundException;
import com.mdm.equipmentservice.mapper.EquipmentMapper;
import com.mdm.equipmentservice.mapper.RepairTicketMapper;
import com.mdm.equipmentservice.model.dto.form.AcceptRepairTicketForm;
import com.mdm.equipmentservice.model.dto.form.CreateRepairTicketForm;
import com.mdm.equipmentservice.model.dto.form.UpdateRepairTicketForm;
import com.mdm.equipmentservice.model.dto.fullinfo.RepairTicketFullInfoDto;
import com.mdm.equipmentservice.model.dto.list.EquipmentListRepairDto;
import com.mdm.equipmentservice.model.entity.*;
import com.mdm.equipmentservice.model.repository.EquipmentRepository;
import com.mdm.equipmentservice.model.repository.RepairTicketRepository;
import com.mdm.equipmentservice.model.repository.UserRepository;
import com.mdm.equipmentservice.query.param.GetEquipmentsForRepairQueryParam;
import com.mdm.equipmentservice.query.predicate.EquipmentPredicate;
import com.mdm.equipmentservice.service.EquipmentService;
import com.mdm.equipmentservice.service.FileStorageService;
import com.mdm.equipmentservice.service.RepairTicketService;
import com.mdm.equipmentservice.util.MessageUtil;
import com.mdm.equipmentservice.util.SecurityUtil;
import com.mdm.equipmentservice.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class RepairTicketServiceImpl implements RepairTicketService {

    private final UserRepository userRepository;

    private final RepairTicketRepository repairTicketRepository;

    private final MessageUtil messageUtil;

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    private final RepairTicketMapper repairTicketMapper;

    private final FileStorageService fileStorageService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ValidationUtil validationUtil;

    @Autowired
    public RepairTicketServiceImpl(
            MessageUtil messageUtil, EquipmentService equipmentService, EquipmentRepository equipmentRepository,
            EquipmentMapper equipmentMapper,
            RepairTicketMapper repairTicketMapper,
            RepairTicketRepository repairTicketRepository,
            FileStorageService fileStorageService,
            ApplicationEventPublisher applicationEventPublisher,
            UserRepository userRepository,
            ValidationUtil validationUtil
    ) {

        this.messageUtil = messageUtil;
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
        this.repairTicketMapper = repairTicketMapper;
        this.repairTicketRepository = repairTicketRepository;
        this.fileStorageService = fileStorageService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override

    public RepairTicketFullInfoDto createRepairTicket(
            CreateRepairTicketForm createRepairTicketForm, Long equipmentId, MultipartFile[] attachments
    ) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("equipmentNotFound")));
        validationUtil.validateEquipmentStatus(equipment, EquipmentStatus.BROKEN, "cannotCreateRepairTicketOnNotBrokenEquipment");
        RepairTicket repairTicket = repairTicketMapper.createTicket(createRepairTicketForm, equipment);
        equipment.setStatus(EquipmentStatus.PENDING_ACCEPT_REPAIR);
        RepairTicket savedRepairTicket = repairTicketRepository.save(repairTicket);
        fileStorageService.uploadMultipleFiles(
                savedRepairTicket.getClass().getSimpleName(),
                savedRepairTicket.getId(),
                FileDescription.DOCUMENT,
                attachments
        );
        applicationEventPublisher.publishEvent(new RepairTicketCreatedEvent(savedRepairTicket));
//        return repairTicketMapper.toFullInfoDto(savedRepairTicket);

        return null;
    }

    @Override
    public Page<EquipmentListRepairDto> getAllEquipmentsForRepair(
            GetEquipmentsForRepairQueryParam getEquipmentsForRepairQueryParam, Pageable pageable
    ) {
        Page<Equipment> equipmentPage = equipmentRepository.findAll(
                EquipmentPredicate.getAllEquipmentsForRepairPredicate(getEquipmentsForRepairQueryParam),
                pageable
        );
        return equipmentPage.map(equipmentMapper::toListRepairDto);
    }

    @Override
    public RepairTicketFullInfoDto getRepairDetail(Long equipmentId, Long repairId) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("equipmentNotFound")));
        RepairTicket repairTicket = equipment.getRepairTickets()
                .stream()
                .filter(rt -> rt.getId().equals(repairId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("repairNotFound")));
        return repairTicketMapper.toFullInfoDto(repairTicket);
    }

    @Override

    public RepairTicketFullInfoDto acceptRepairTicket(
            Long equipmentId, Long repairId, AcceptRepairTicketForm acceptRepairTicketForm
    ) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("equipmentNotFound")));
        validationUtil.validateEquipmentStatus(
                equipment,
                EquipmentStatus.PENDING_ACCEPT_REPAIR,
                "cannotAcceptRepairTicketOnEquipmentNotPendingAcceptRepair"
        );
        RepairTicket repairTicket = equipment.getRepairTickets()
                .stream()
                .filter(rt -> rt.getId().equals(repairId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("repairNotFound")));
        repairTicketMapper.acceptTicket(acceptRepairTicketForm, repairTicket);
        equipment = equipmentMapper.acceptRepairTicket(equipment, acceptRepairTicketForm);
        repairTicket.setEquipment(equipment);
        RepairTicket savedRepairTicket = repairTicketRepository.save(repairTicket);
        applicationEventPublisher.publishEvent(new RepairTicketAcceptedEvent(savedRepairTicket));
//        return repairTicketMapper.toFullInfoDto(savedRepairTicket);

        return null;
    }

    @Override
    public RepairTicketFullInfoDto updateRepairTicket(Long repairTicketId, Long equipmentId, UpdateRepairTicketForm updateRepairTicketForm,
                                                      MultipartFile[] attachments) {
        RepairTicket repairTicket = repairTicketRepository.findById(repairTicketId)
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("repairTicketNotFound")));
        validationUtil.validateRepairStatus(repairTicket, RepairStatus.IN_PROGRESS, "cannotUpdateRepairTicketOnNotInProgressRepairTicket");
        repairTicket = repairTicketMapper.updateTicket(updateRepairTicketForm, repairTicket);
        Equipment equipment = repairTicket.getEquipment();
        equipment.setStatus(EquipmentStatus.PENDING_ACCEPTANCE_TESTING);
        equipmentRepository.save(equipment);
        fileStorageService.uploadMultipleFiles(repairTicket.getClass().getSimpleName(), repairTicket.getId(), FileDescription.DOCUMENT, attachments);
        applicationEventPublisher.publishEvent(new RepairTicketUpdatedEvent(repairTicket));
//        return repairTicketMapper.toFullInfoDto(repairTicket);

        return null;
    }

    /**
     * Nghiệm thu thiết bị
     */
    @Override

    public void acceptanceTesting(Long repairTicketId, Long ticketId, MultipartFile[] attachments) {
        RepairTicket repairTicket = repairTicketRepository.findById(repairTicketId)
                .orElseThrow(() -> new ResourceNotFoundException(messageUtil.getMessage("repairTicketNotFound")));
        //if repair status is done, it means that equipment is repaired successfully, then set equipment status to IN_USE
        //else set equipment status to INACTIVE
        Equipment equipment = repairTicket.getEquipment();
        validationUtil.validateEquipmentStatus(
                equipment,
                EquipmentStatus.PENDING_ACCEPTANCE_TESTING,
                "cannotAcceptanceTestingOnNotPendingAcceptanceTestingEquipment"
        );
        equipment.setStatus(repairTicket.getRepairStatus().equals(RepairStatus.DONE) ? EquipmentStatus.IN_USE : EquipmentStatus.INACTIVE);
        equipmentRepository.save(equipment);
        repairTicket.setAcceptanceTester(SecurityUtil.getCurrentLoggedInUser(userRepository));
        repairTicket = repairTicketRepository.saveAndFlush(repairTicket);
        fileStorageService.uploadMultipleFiles(repairTicket.getClass().getSimpleName(), repairTicket.getId(), FileDescription.DOCUMENT, attachments);
        applicationEventPublisher.publishEvent(new RepairTicketAcceptanceTestingEvent(repairTicket));
    }
}
