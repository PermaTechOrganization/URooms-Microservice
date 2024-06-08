package com.urooms.account.security.infraestructure.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClient {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
            .serverUrl("http://localhost:8180")
            .realm("master")
            .clientId("admin-cli")
            .grantType(OAuth2Constants.PASSWORD)
            .username("admin")
            .password("admin")
            .build();
    }

}
