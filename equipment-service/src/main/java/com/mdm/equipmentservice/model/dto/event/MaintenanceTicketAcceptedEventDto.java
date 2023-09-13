package com.mdm.equipmentservice.model.dto.event;

import com.mdm.equipmentservice.model.dto.fullinfo.MaintenanceTicketFullInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class MaintenanceTicketAcceptedEventDto implements Serializable {

    private final MaintenanceTicketFullInfoDto maintenanceTicket;
}