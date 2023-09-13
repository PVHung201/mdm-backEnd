package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UpsertDepartmentForm implements Serializable {

    @NotBlank(message = "{departmentNameMustNotBeBlank}")
    private final String name;

    private final String alias;

    private final String phone;

    private final String email;

    private final String address;

    private final Long contactPersonId;

    private final Long headOfDepartmentId;

    private final Long chiefNurseId;

    private final Long managerId;
}