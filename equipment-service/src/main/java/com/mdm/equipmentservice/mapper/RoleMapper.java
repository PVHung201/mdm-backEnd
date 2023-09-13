package com.mdm.equipmentservice.mapper;

import com.mdm.equipmentservice.model.dto.base.RoleDto;
import com.mdm.equipmentservice.model.dto.form.UpsertRoleForm;
import com.mdm.equipmentservice.model.dto.fullinfo.RoleFullInfoDto;
import com.mdm.equipmentservice.model.entity.Permission;
import com.mdm.equipmentservice.model.entity.Role;
import com.mdm.equipmentservice.model.repository.PermissionRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {IdToEntityMapper.class})
public abstract class RoleMapper {

    @Autowired
    protected PermissionRepository permissionRepository;

    public abstract RoleDto toDto(Role role);

    public abstract RoleFullInfoDto toFullInfoDto(Role role);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionStringsToPermissionEntities")
    public abstract Role toEntity(UpsertRoleForm upsertRoleForm);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionStringsToPermissionEntities")
    public abstract Role partialUpdate(UpsertRoleForm upsertRoleForm, @MappingTarget Role role);

    @Named("permissionStringsToPermissionEntities")
    Set<Permission> permissionStringsToPermissionEntities(Set<String> permissionNames) {
        if (permissionNames == null || permissionNames.isEmpty()) {
            return null;
        }
        return permissionRepository.findByNameInIgnoreCase(permissionNames);
    }
}
