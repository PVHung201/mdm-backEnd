package com.mdm.equipmentservice.keycloak.service;

import com.mdm.equipmentservice.model.dto.form.UpsertUserForm;
import com.mdm.equipmentservice.model.entity.User;
import org.keycloak.representations.idm.CredentialRepresentation;

public interface KeycloakUserService {

    void createUser(User user, String password);


    CredentialRepresentation getPasswordCredentialRepresentation(String password);

    void updatePassword(String username, String password);

    void deleteUser(String username);

    void updateUser(String username, UpsertUserForm upsertUserForm, String oldRoleName);

    void updateRole(String username, String roleName);
}
