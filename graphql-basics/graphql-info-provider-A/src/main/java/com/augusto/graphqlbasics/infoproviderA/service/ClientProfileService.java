package com.augusto.graphqlbasics.infoproviderA.service;

import com.augusto.graphqlbasics.infoproviderA.dto.ClientProfile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientProfileService {

    private static final Map<Long, ClientProfile> CLIENT_PROFILE_SIMULATED_DATABASE = new HashMap<Long, ClientProfile>() {
        {
            put(1L, new ClientProfile(1L, "carlos", "01909019003", LocalDate.of(1993, 12, 23)));
            put(2L, new ClientProfile(2L, "rafael", "25624470002", LocalDate.of(1993, 3, 16)));
            put(3L, new ClientProfile(3L, "john", "59404070050", LocalDate.of(1993, 5, 1)));
            put(4L, new ClientProfile(4L, "alfred", "78960731021", LocalDate.of(1993, 8, 11)));
            put(5L, new ClientProfile(5L, "peter", "16945784029", LocalDate.of(1993, 10, 30)));
        }
    };

    public List<ClientProfile> findAll() {
        return CLIENT_PROFILE_SIMULATED_DATABASE.values().stream().toList();
    }

    public ClientProfile findById(Long id) {
        return CLIENT_PROFILE_SIMULATED_DATABASE.get(id);
    }

    public Long createClientProfile(ClientProfile clientProfile) {
        CLIENT_PROFILE_SIMULATED_DATABASE.putIfAbsent(clientProfile.id(), clientProfile);
        return clientProfile.id();
    }

    public void deleteClientProfile(Long id) {
        CLIENT_PROFILE_SIMULATED_DATABASE.remove(id);
    }

    public ClientProfile updateClientProfile(Long id, ClientProfile clientProfile) {
        ClientProfile clientProfileToUpdate = CLIENT_PROFILE_SIMULATED_DATABASE.get(id);

        if (clientProfile.name() != null) {
            clientProfileToUpdate = clientProfileToUpdate.withName(clientProfile.name());
        }
        if (clientProfile.nationalId() != null) {
            clientProfileToUpdate = clientProfileToUpdate.withNationalId(clientProfile.nationalId());
        }
        if (clientProfile.birthDate() != null) {
            clientProfileToUpdate = clientProfileToUpdate.withBirthDate(clientProfile.birthDate());
        }

        return CLIENT_PROFILE_SIMULATED_DATABASE.replace(id, clientProfileToUpdate);
    }

}
