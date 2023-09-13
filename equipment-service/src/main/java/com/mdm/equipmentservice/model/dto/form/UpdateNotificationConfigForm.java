package com.mdm.equipmentservice.model.dto.form;

import com.mdm.equipmentservice.model.entity.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationConfigForm {

    @NotNull
    private NotificationType notificationType;

    @NotNull
    private Long roleId;
}
