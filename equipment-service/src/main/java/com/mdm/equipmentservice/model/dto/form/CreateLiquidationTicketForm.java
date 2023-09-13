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
public class CreateLiquidationTicketForm implements Serializable {

    @NotNull(message = "{createdDateMustNotBeNull}")
    private LocalDateTime createdDate;

    private String creatorNote;

    @NotNull(message = "{liquidationDateMustNotBeNull}")
    private LocalDateTime liquidationDate;

    private String liquidationNote;

    @NotNull(message = "{priceMustNotBeNull}")
    private Double price;
}