package com.mdm.equipmentservice.model.dto.base;

import com.mdm.equipmentservice.model.entity.HandoverStatus;
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
public class HandoverDto implements Serializable {

    Long id;

    LocalDateTime handoverDate;

    UserDto responsiblePerson;

    String handoverNote;

    DepartmentDto department;

    UserDto createdBy;

    UserDto approver;

    LocalDateTime approvalDate;

    HandoverStatus status;

    String approverNote;
}