package com.mdm.equipmentservice.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHandoverTicketForm implements Serializable {

    private Long responsiblePersonId;

    private LocalDateTime createdDate;

    private String creatorNote;

    private LocalDateTime handoverDate;

    private String handoverNote;

    private Long departmentId;
}