package com.mdm.equipmentservice.model.dto.event;

import com.mdm.equipmentservice.model.dto.fullinfo.LiquidationTicketFullInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LiquidationTicketCreatedEventDto implements Serializable {

    private final LiquidationTicketFullInfoDto liquidationTicket;
}