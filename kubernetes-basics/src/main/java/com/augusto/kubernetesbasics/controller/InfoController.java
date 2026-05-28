package com.augusto.kubernetesbasics.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@RestController
public class InfoController {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> info() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        return ResponseEntity.ok(Map.of(
                "app", "kubernetes-basics",
                "version", appVersion,
                "hostname", hostname
        ));
    }
}
