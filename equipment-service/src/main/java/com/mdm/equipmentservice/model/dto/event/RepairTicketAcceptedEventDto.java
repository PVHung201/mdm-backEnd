package com.mdm.equipmentservice.model.dto.event;

import com.mdm.equipmentservice.model.dto.fullinfo.RepairTicketFullInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RepairTicketAcceptedEventDto implements Serializable {

    private final RepairTicketFullInfoDto repairTicket;
}