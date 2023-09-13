package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotNull;
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
public class AcceptMaintenanceTicketForm implements Serializable {

    @NotNull
    private LocalDateTime approvalDate;

    private String approverNote;

    @NotNull
    private Boolean isApproved;
}