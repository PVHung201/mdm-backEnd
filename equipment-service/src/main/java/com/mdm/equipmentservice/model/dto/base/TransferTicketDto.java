package com.mdm.equipmentservice.model.dto.base;

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
public class TransferTicketDto implements Serializable {

    private Long id;

    private String fromDepartmentName;

    private String toDepartmentName;

    private LocalDateTime dateTransfer;


    private String note;

    private String approverName;

    private String approverUsername;
}