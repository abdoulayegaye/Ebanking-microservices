package com.xoslu.tech.gatewayservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "keycloak-client",
        url = "${keycloak.auth-server-url}",
        configuration = KeycloakErrorDecoder.class
)
public interface KeycloakFeignClient {

    @PostMapping(
            value = "/realms/ebanking-realm/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    KeycloakTokenResponse getToken(@RequestBody MultiValueMap<String, String> formParams);
}