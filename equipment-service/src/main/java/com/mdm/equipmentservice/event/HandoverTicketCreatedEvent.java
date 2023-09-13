package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.HandoverTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandoverTicketCreatedEvent {

    private HandoverTicket handoverTicket;
}
