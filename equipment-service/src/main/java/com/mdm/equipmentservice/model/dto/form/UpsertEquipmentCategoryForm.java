package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UpsertEquipmentCategoryForm implements Serializable {

    @NotBlank(message = "{equipmentCategoryNameMustNotBeBlank}")
    private final String name;

    private final String note;

    private final String alias;

    private final Long groupId;
}