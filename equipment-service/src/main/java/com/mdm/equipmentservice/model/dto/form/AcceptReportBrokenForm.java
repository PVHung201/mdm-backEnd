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
public class AcceptReportBrokenForm {

    private String approverNote;

    @NotNull(message = "{handoverAcceptStatusIsRequired}")
    private Boolean isAccept;

    private LocalDateTime approvalDate;
}
