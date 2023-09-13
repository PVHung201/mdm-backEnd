package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertEquipmentGroupForm implements Serializable {

    @NotBlank(message = "{equipmentGroupNameMustNotBeBlank}")
    private String name;

    private String note;

    @NotBlank(message = "{aliasMustNotBeBlank}")
    private String alias;
}