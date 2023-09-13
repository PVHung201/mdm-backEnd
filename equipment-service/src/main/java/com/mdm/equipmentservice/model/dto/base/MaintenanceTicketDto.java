package com.mdm.equipmentservice.model.dto.base;

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
public class MaintenanceTicketDto implements Serializable {

    private Long id;

    private LocalDateTime maintenanceDate;

    private LocalDateTime nextTime;

    private String maintenanceNote;

    private Double price;

    private String code;

    private LocalDateTime createdDate;

    private String creatorNote;

    private LocalDateTime approvalDate;

    private String approverNote;

    private TicketStatus status;
}