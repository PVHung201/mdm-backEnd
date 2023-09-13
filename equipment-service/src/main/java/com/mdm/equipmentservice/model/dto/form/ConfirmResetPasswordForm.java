package com.mdm.equipmentservice.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmResetPasswordForm {

    private String newPassword;

    private String confirmPassword;

    private String uuid;
}
