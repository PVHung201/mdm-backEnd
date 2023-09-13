package com.mdm.equipmentservice.model.dto.event;

import com.mdm.equipmentservice.model.dto.fullinfo.ReportBrokenTicketFullInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ReportBrokenTicketAcceptedEventDto implements Serializable {

    private final ReportBrokenTicketFullInfoDto reportBrokenTicket;
}