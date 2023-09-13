package com.mdm.equipmentservice.model.dto.base;

import com.mdm.equipmentservice.model.entity.DepartmentActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDto implements Serializable {

    private final Long id;

    private final String name;

    private final String alias;

    private final String phone;

    private final String email;

    private final String address;

    private final DepartmentActiveStatus activeStatus;
}