package com.augusto.graphqlbasics.infoproviderA.controller;

import com.augusto.graphqlbasics.infoproviderA.dto.ClientProfile;
import com.augusto.graphqlbasics.infoproviderA.service.ClientProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/client-profile")
public class ClientProfileController {

    private final ClientProfileService service;

    public ClientProfileController(ClientProfileService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientProfile> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientProfile getById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Long createClientProfile(@RequestBody ClientProfile clientProfile) {
        return service.createClientProfile(clientProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteClientProfile(@PathVariable(name = "id") Long id) {
        service.deleteClientProfile(id);
    }

    @PutMapping("/{id}")
    public ClientProfile updateClientProfile(@PathVariable(name = "id") Long id,
                                             @RequestBody ClientProfile clientProfile) {
        return service.updateClientProfile(id, clientProfile);
    }
}
