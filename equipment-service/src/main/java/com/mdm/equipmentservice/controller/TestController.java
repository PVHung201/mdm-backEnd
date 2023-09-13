package com.mdm.equipmentservice.controller;

import com.mdm.equipmentservice.config.KeycloakConfig;
import com.mdm.equipmentservice.response.GenericResponse;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.AuthorizationResource;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.ScopePermissionsResource;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.ScopePermissionRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/departments")
@Import(KeycloakConfig.class)
public class TestController {

    private final ApplicationContext context;


    private final ScopePermissionsResource scopePermissionsResource;


    @Autowired
    public TestController(
            ApplicationContext context, Keycloak keycloak
    ) {
        this.context = context;
        String trueClientId = keycloak.realm("mdm-local").clients().findByClientId("api-gateway").get(0).getId();

        RealmResource realmResource = keycloak.realm("mdm-local");
        ClientResource clientResource = realmResource.clients().get(trueClientId);
        AuthorizationResource authorizationResource = clientResource.authorization();
        this.scopePermissionsResource = authorizationResource.permissions().scope();
    }

    @GetMapping("/test-permission")
    public ResponseEntity<GenericResponse<ScopePermissionRepresentation>> testPermission(@RequestParam String permissionName) {
        ScopePermissionRepresentation scopePermissionRepresentation = this.scopePermissionsResource.findByName(permissionName);
        return ResponseEntity.ok(new GenericResponse<>(scopePermissionRepresentation));
    }


    @PostMapping("/test-add-user")
    public void testuser(/*@RequestParam String scope*/) {
        Configuration configuration = context.getBean("keycloakConfiguration", Configuration.class);
        Keycloak keycloak = context.getBean("keycloakInstance", Keycloak.class);
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername("test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@test.test");
        Response response = keycloak.realm("mdm-local").users().create(user);
        CredentialRepresentation password = new CredentialRepresentation();
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue("test");
        password.setTemporary(false);
    }

}
