package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.LiquidationTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiquidationTicketAcceptedEvent {

    private LiquidationTicket liquidationTicket;
}

