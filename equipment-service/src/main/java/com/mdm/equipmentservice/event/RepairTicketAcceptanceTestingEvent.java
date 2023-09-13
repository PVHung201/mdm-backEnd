package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.RepairTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RepairTicketAcceptanceTestingEvent {

    private RepairTicket repairTicket;
}
