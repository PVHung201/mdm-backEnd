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
public class LiquidationDto implements Serializable {

    private Long id;

    private String code;

    private LocalDateTime createdDate;

    private String creatorNote;

    private LocalDateTime approvalDate;

    private String approverNote;

    private TicketStatus status;

    private LocalDateTime liquidationDate;

    private String liquidationNote;

    private Double price;
}