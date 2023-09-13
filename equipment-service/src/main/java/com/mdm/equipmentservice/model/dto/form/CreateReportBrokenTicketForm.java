package com.mdm.equipmentservice.model.dto.form;

import com.mdm.equipmentservice.model.entity.RepairPriority;
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
public class CreateReportBrokenTicketForm implements Serializable {

    @NotNull(message = "{createdDateMustNotBeNull}")
    private LocalDateTime createdDate;

    private String creatorNote;

    private String reason;

    @NotNull(message = "{priorityMustNotBeNull}")
    private RepairPriority priority;


}