package com.mdm.equipmentservice.model.dto.fullinfo;

import com.mdm.equipmentservice.model.entity.DepartmentActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentFullInfoDto implements Serializable {

    private final Long id;

    private final String name;

    private final String alias;

    private final String phone;

    private final String email;

    private final String address;

    private final DepartmentActiveStatus activeStatus;

    private final UserDetailDto contactPerson;

    private final UserDetailDto headOfDepartment;

    private final UserDetailDto chiefNurse;

    private final UserDetailDto manager;

    private final Set<UserDetailDto> users;

}