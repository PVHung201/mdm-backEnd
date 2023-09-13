package com.mdm.equipmentservice.model.dto.form;

import jakarta.validation.constraints.NotNull;
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
public class CreateTransferTicketForm implements Serializable {

    @NotNull(message = "{createdDateMustNotBeNull}")
    private LocalDateTime createdDate;

    private String creatorNote;

    @NotNull(message = "{toDepartmentMustNotBeNull}")
    private Long toDepartmentId;

    @NotNull(message = "{dateTransferMustNotBeNull}")
    private LocalDateTime dateTransfer;

    private String transferNote;
}