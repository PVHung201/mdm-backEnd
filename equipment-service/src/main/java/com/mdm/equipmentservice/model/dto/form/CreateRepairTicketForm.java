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
public class CreateRepairTicketForm implements Serializable {

    @NotNull(message = "{createdDateMustNotBeNull}")
    private LocalDateTime createdDate;

    private String creatorNote;

    @NotNull(message = "{estimatedCostMustNotBeNull}")
    private Long estimatedCost;

    @NotNull(message = "{repairStartDateMustNotBeNull}")
    private LocalDateTime repairStartDate;

    private Long repairCompanyId;
}