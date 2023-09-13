package com.mdm.equipmentservice.model.dto.list;

import com.mdm.equipmentservice.model.dto.base.EquipmentDto;
import com.mdm.equipmentservice.model.dto.base.FileStorageDto;
import com.mdm.equipmentservice.model.dto.base.SupplierDto;
import com.mdm.equipmentservice.model.dto.base.UserDto;
import com.mdm.equipmentservice.model.entity.InspectionEvaluationStatus;
import com.mdm.equipmentservice.model.entity.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InspectionTicketListDto implements Serializable {

    private Long id;

    private String code;

    private UserDto creator;

    private LocalDateTime createdDate;

    private String creatorNote;

    private LocalDateTime approvalDate;

    private String approverNote;

    private UserDto approver;

    private TicketStatus status;

    private EquipmentDto equipment;

    private LocalDateTime inspectionDate;

    private String inspectionNote;

    private Double price;

    private InspectionEvaluationStatus evaluationStatus;

    private SupplierDto inspectionCompany;

    private List<FileStorageDto> attachments;
}