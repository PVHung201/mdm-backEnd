package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.TransferTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferTicketCreatedEvent {

    private final TransferTicket transferTicket;
}
