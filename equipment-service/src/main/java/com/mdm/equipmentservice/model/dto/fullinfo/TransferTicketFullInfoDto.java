package com.mdm.equipmentservice.model.dto.fullinfo;

import com.mdm.equipmentservice.model.dto.base.DepartmentDto;
import com.mdm.equipmentservice.model.dto.base.EquipmentDto;
import com.mdm.equipmentservice.model.dto.base.FileStorageDto;
import com.mdm.equipmentservice.model.dto.base.UserDto;
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
public class TransferTicketFullInfoDto implements Serializable {

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

    private DepartmentDto fromDepartment;

    private DepartmentDto toDepartment;

    private LocalDateTime dateTransfer;

    private String transferNote;

    private List<FileStorageDto> attachments; //TODO: lam tat ca nhu the nay
}