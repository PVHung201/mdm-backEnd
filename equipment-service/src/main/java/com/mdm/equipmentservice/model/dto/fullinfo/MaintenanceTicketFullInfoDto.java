package com.mdm.equipmentservice.model.dto.fullinfo;

import com.mdm.equipmentservice.model.dto.base.EquipmentDto;
import com.mdm.equipmentservice.model.dto.base.FileStorageDto;
import com.mdm.equipmentservice.model.dto.base.SupplierDto;
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
public class MaintenanceTicketFullInfoDto implements Serializable {

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

    private LocalDateTime maintenanceDate;

    private LocalDateTime nextTime;

    private SupplierDto maintenanceCompany;

    private String maintenanceNote;

    private Double price;

    private List<FileStorageDto> attachments;
}