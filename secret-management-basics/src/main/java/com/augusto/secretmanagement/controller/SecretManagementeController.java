package com.augusto.secretmanagement.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/secrets")
public class SecretManagementeController {

    @Value("${database.password}")
    private String dbPassword;

    @Value("${api.key}")
    private String apiKey;

    @Value("${app.env}")
    private String appEnv;

    @GetMapping
    public Map<String, String> showSecrets() {
        return Map.of(
                "db_password", dbPassword,
                "api_key", apiKey,
                "app_env", appEnv
        );
    }
}
