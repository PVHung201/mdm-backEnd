package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.TransferTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferTicketAcceptedEvent {

    private TransferTicket transferTicket;

    public TransferTicketAcceptedEvent(TransferTicket transferTicket) {
        this.transferTicket = transferTicket;
    }
}
