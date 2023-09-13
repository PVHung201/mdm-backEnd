package com.mdm.equipmentservice.model.dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInspectionTicketForm {

    private LocalDateTime createdDate;

    private String creatorNote;
}
