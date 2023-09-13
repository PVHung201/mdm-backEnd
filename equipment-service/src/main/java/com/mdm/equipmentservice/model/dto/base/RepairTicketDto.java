package com.mdm.equipmentservice.model.dto.base;

import com.mdm.equipmentservice.model.entity.RepairStatus;
import com.mdm.equipmentservice.model.entity.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairTicketDto implements Serializable {

    private Long id;

    private String code;

    private LocalDateTime createdDate;

    private String creatorNote;

    private LocalDateTime approvalDate;

    private String approverNote;

    private TicketStatus status;

    private Long estimatedCost;

    private RepairStatus repairStatus;

    private LocalDateTime repairStartDate;

    private LocalDateTime repairEndDate;

    private Long actualCost;
}