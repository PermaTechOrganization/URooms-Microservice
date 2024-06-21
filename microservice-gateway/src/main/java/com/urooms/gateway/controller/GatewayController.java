package com.urooms.gateway.controller;

import com.urooms.gateway.dto.response.LoginRegisterResponseDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {
    @GetMapping("/")
    public LoginRegisterResponseDTO index(WebSession session, @AuthenticationPrincipal DefaultOidcUser principal) {
        //String username = principal.getPreferredUsername();
        //return Mono.just("Hello, " + username + "!<br/> Session: <code>" + session.getId() + "</code>" + "<br/> Id: <code>" + principal.getSubject());
        String username = principal.getPreferredUsername();
        String userId = principal.getSubject();
        return new LoginRegisterResponseDTO(username, session.getId(), userId);
    }

    @GetMapping("/token")
    public Mono<String> getToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client){
        return Mono.just(client.getAccessToken().getTokenValue());
    }

}
