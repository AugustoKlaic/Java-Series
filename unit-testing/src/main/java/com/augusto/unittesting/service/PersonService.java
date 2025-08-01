package com.augusto.unittesting.service;

import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final NotificationService notificationService;

    public PersonService(PersonRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Person create(Person dto) {
        if (dto.getAge() < 18) throw new IllegalArgumentException("Person must be over 18 years.");
        if ("admin".equalsIgnoreCase(dto.getName())) throw new RuntimeException("Name not allowed");

        Person saved = repository.save(dto);
        notificationService.sendWelcomeMessage(saved.getName());

        return saved;
    }

    public List<Person> createAll(List<Person> dtos) {
        return dtos.stream().map(this::create).toList();
    }

    public Person updateName(Long id, String newName) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person not found!"));

        person.setName(newName);
        return repository.save(person);
    }

    public void updateAge(Long id, int newAge) {
        Person person = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person not found!"));
        if (newAge <= person.getAge()) return;

        person.setAge(newAge);
        repository.save(person);
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
