package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeycloakUpsertRoleForm implements Serializable {

    @NotBlank(message = "{roleNameMustNotBeNull}")
    private String name;

    private String description;

    private Set<String> scopes;
}
