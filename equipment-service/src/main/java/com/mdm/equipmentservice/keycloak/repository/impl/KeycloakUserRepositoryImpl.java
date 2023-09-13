package com.mdm.equipmentservice.keycloak.repository.impl;

import com.mdm.equipmentservice.keycloak.repository.KeycloakUserRepository;
import com.mdm.equipmentservice.util.MessageUtil;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class KeycloakUserRepositoryImpl implements KeycloakUserRepository {

    private final RealmResource realmResource;

    @Value("${keycloak.realm-name}")
    private String realmName;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.server}")
    private String keycloakServer;

    @Autowired
    public KeycloakUserRepositoryImpl(Keycloak keycloak, Configuration configuration, MessageUtil messageUtil) {
        this.realmResource = keycloak.realm(configuration.getRealm());
    }

    @Override
    public Response save(UserRepresentation userRepresentation) {
        return this.realmResource.users().create(userRepresentation);
    }

    /**
     * override old password with new password
     *
     * @param password new password
     * @param username username
     */
    @Override
    public void updatePassword(String password, String username) {
        if (StringUtils.isBlank(password)) {
            return;
        }
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setTemporary(false);
        getResourceByUsername(username).resetPassword(credentialRepresentation);
    }

    @Override
    public Response deleteById(String id) {
        return this.realmResource.users().delete(id);
    }

    @Override
    public UserRepresentation getRepresentationByUsername(String username) {
        return this.realmResource.users().searchByUsername(username, true).get(0);
    }

    @Override
    public UserResource getResourceByUsername(String username) {
        return this.realmResource.users().get(getRepresentationByUsername(username).getId());
    }

    @Override
    public void deleteByUsername(String username) {
        getResourceByUsername(username).remove();
    }

}
