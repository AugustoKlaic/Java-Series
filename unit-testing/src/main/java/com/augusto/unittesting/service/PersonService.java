package com.augusto.unittesting.service;

import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(Person dto) {
        if (dto.getAge() < 18) {
            throw new IllegalArgumentException("Person must be over 18 years.");
        }

        Person person = new Person(null, dto.getName(), dto.getAge());
        return repository.save(person);
    }

    public Person updateName(Long id, String newName) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person not found!"));

        person.setName(newName);
        return repository.save(person);
    }

    public List<Person> listAll() {
        return repository.listAll();
    }

    public void delete(Long id) {
        if (!repository.exists(id)) {
            throw new IllegalArgumentException("Person not found.");
        }
        repository.delete(id);
    }
}
