package com.mdm.equipmentservice.model.dto.event;

import com.mdm.equipmentservice.model.dto.fullinfo.ReportBrokenTicketFullInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportBrokenTicketCreatedEventDto {

    private ReportBrokenTicketFullInfoDto reportBrokenTicket;
}
