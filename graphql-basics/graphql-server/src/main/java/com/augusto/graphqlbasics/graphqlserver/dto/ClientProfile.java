package com.augusto.graphqlbasics.graphqlserver.dto;

import java.time.LocalDate;

public record ClientProfile(Long id, String name, String nationalId, LocalDate birthDate) {

    public ClientProfile withName(String newName){
        return new ClientProfile(this.id, newName, this.nationalId, this.birthDate);
    }

    public ClientProfile withNationalId(String newNationalId){
        return new ClientProfile(this.id, this.name, newNationalId, this.birthDate);
    }

    public ClientProfile withBirthDate(LocalDate newBirthDate){
        return new ClientProfile(this.id, this.name, this.nationalId, newBirthDate);
    }
}
