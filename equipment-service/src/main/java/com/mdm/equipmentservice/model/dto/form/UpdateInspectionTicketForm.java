package com.mdm.equipmentservice.model.dto.form;

import com.mdm.equipmentservice.model.entity.InspectionEvaluationStatus;
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
public class UpdateInspectionTicketForm {

    @NotNull
    private LocalDateTime inspectionDate;

    private String inspectionNote;

    private Long inspectionCompanyId;

    @NotNull
    private Double price;

    @NotNull
    private InspectionEvaluationStatus evaluationStatus;
}
