package com.mdm.equipmentservice.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceTicketForm implements Serializable {

    private LocalDateTime maintenanceDate;

    private String maintenanceNote;

    private Long maintenanceCompanyId;

    private Double price;
}