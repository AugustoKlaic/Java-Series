package com.augusto.kubernetesbasics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> hello() {
        return ResponseEntity.ok(Map.of("message", "Hello from Spring Boot on Kubernetes!"));
    }
}
