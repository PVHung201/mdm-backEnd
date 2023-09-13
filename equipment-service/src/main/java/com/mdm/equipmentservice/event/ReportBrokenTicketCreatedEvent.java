package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.ReportBrokenTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportBrokenTicketCreatedEvent {

    private ReportBrokenTicket reportBrokenTicket;

}
