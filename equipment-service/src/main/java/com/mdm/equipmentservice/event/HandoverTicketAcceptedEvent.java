package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.HandoverTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandoverTicketAcceptedEvent {

    private HandoverTicket handoverTicket;

    public HandoverTicketAcceptedEvent(HandoverTicket handoverTicket) {
        this.handoverTicket = handoverTicket;
    }
}
