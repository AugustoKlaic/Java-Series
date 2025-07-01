package com.augusto.graphqlbasics.graphqlserver.client;

import com.augusto.graphqlbasics.graphqlserver.dto.ClientProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-profile", url = "http://localhost:8081")
public interface ClientProfileFeign {

    @GetMapping("/client-profile")
    List<ClientProfile> getAll();

    @GetMapping("/client-profile/{id}")
    ClientProfile getById(@PathVariable("id") Long id);

    @PostMapping("/client-profile")
    Long createClientProfile(@RequestBody ClientProfile clientProfile);

    @PutMapping("/client-profile/{id}")
    ClientProfile updateClientProfile(@PathVariable("id") Long id,
                                      @RequestBody ClientProfile clientProfile);

    @DeleteMapping("/client-profile/{id}")
    void deleteClientProfile(@PathVariable("id") Long id);
}
