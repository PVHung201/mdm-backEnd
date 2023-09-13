package com.mdm.equipmentservice.model.dto.form;


import com.mdm.equipmentservice.model.entity.WorkingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertUserForm implements Serializable {

    @Schema(hidden = true)
    private Long id;

    @NotBlank(message = "{nameMustNotBeBlank}")
    private String name;

    @NotBlank(message = "{usernameMustNotBeBlank}")
    private String username;

    @NotBlank(message = "{emailMustNotBeBlank}")
    @Email(message = "{wrongEmailFormat}")
    private String email;

    @NotBlank(message = "{phoneMustNotBeBlank}")
    private String phone;

    @NotNull(message = "{genderMustNotBeNull}")
    private Boolean gender;

    private String address;

    @NotNull(message = "{birthdayMustNotBeNull}")
    private LocalDateTime birthday;

    private boolean enabled;

    private WorkingStatus workingStatus;

    @NotNull(message = "{roleMustNotBeBlank}")
    private Long roleId;

    @NotNull(message = "{departmentMustNotBeNull}")
    private Long departmentId;

    private Set<Long> departmentResponsibilityIds;

    private String password;
}