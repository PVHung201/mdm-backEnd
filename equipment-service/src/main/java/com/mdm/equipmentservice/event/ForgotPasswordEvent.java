package com.mdm.equipmentservice.event;

import com.mdm.equipmentservice.model.entity.ForgotPassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordEvent {

    private ForgotPassword forgotPassword;

    private String email;

    private String remoteOrigin;

    public ForgotPasswordEvent(ForgotPassword forgotPassword, String email, String remoteOrigin) {
        this.forgotPassword = forgotPassword;
        this.email = email;
        this.remoteOrigin = remoteOrigin;
    }

    public ForgotPasswordEvent(ForgotPassword forgotPassword, String email) {
        this.forgotPassword = forgotPassword;
        this.email = email;
    }
}
