package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptInspectionTicketForm {

    @NotNull
    private LocalDateTime approvalDate;

    private String approverNote;

    @NotNull
    private Boolean isApproved;
}
