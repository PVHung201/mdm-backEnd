package com.mdm.equipmentservice.keycloak.service.impl;

import com.mdm.equipmentservice.keycloak.repository.KeycloakUserRepository;
import com.mdm.equipmentservice.keycloak.service.KeycloakUserService;
import com.mdm.equipmentservice.model.dto.form.UpsertUserForm;
import com.mdm.equipmentservice.model.entity.User;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Deprecated(forRemoval = true)
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private final KeycloakUserRepository keycloakUserRepository;

    @Autowired
    public KeycloakUserServiceImpl(KeycloakUserRepository keycloakUserRepository) {
        this.keycloakUserRepository = keycloakUserRepository;
    }

    @Override
    public void createUser(User user, String password) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setCredentials(List.of(getPasswordCredentialRepresentation(password)));
        userRepresentation.setEnabled(true);
        Response response = keycloakUserRepository.save(userRepresentation);
        if (!HttpStatus.valueOf(response.getStatus()).is2xxSuccessful()) {
            log.error("Something went wrong when save user on keycloak server. Please check the keycloak server log... User: {}", user);
            throw new RuntimeException();
        }
    }

    @Override
    public CredentialRepresentation getPasswordCredentialRepresentation(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    @Override
    public void updatePassword(String username, String password) {
        keycloakUserRepository.updatePassword(password, username);
    }

    @Override
    public void deleteUser(String username) {
        keycloakUserRepository.deleteByUsername(username);
    }

    @Override
    public void updateUser(String username, UpsertUserForm upsertUserForm, String oldRoleName) {
        if (StringUtils.isBlank(upsertUserForm.getPassword())) {
            return;
        }
        updatePassword(username, upsertUserForm.getPassword());
    }

    @Override
    public void updateRole(String username, String roleName) {
        UserRepresentation representationByUsername = keycloakUserRepository.getRepresentationByUsername(username);
        representationByUsername.setRealmRoles(List.of(roleName));
        keycloakUserRepository.getResourceByUsername(username).update(representationByUsername);
    }


}
