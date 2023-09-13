package com.mdm.equipmentservice.keycloak.repository;

import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;


@Repository
public interface KeycloakUserRepository {

    Response save(UserRepresentation userRepresentation);

    void updatePassword(String password, String username);

    Response deleteById(String id);

    UserRepresentation getRepresentationByUsername(String username);

    UserResource getResourceByUsername(String username);

    void deleteByUsername(String username);


}
