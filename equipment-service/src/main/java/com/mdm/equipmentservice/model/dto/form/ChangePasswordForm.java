package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm {

    @NotBlank(message = "{oldPasswordMustNotBeBlank}")
    private String oldPassword;

    @NotBlank(message = "{newPasswordMustNotBeBlank}")
    private String newPassword;

    @NotBlank(message = "{confirmPasswordMustNotBeBlank}")
    private String confirmPassword;
}
