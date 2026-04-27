package com.xoslu.tech.gatewayservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xoslu.tech.gatewayservice.dtos.AuthRequest;
import com.xoslu.tech.gatewayservice.feign.KeycloakAuthService;
import com.xoslu.tech.gatewayservice.feign.KeycloakTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final KeycloakAuthService keycloakAuthService;

    @GetMapping("/infos")
    @ResponseBody
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

    @PostMapping
    public KeycloakTokenResponse authenticateByKeycloak(@RequestBody AuthRequest authRequest) {
        log.info("Authenticating with Keycloak");
        KeycloakTokenResponse response = null;
        try {
            response = keycloakAuthService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Token Generated {}", response.getAccessToken());
        return response;
    }
}

