package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertServiceForm {

    @NotBlank(message = "Tên dịch vụ không được để trống")
    private String name;

    private String note;
}
