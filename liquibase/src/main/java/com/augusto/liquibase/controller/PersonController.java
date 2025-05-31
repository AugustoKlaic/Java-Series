package com.augusto.liquibase.controller;

import com.augusto.liquibase.entity.Person;
import com.augusto.liquibase.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @GetMapping("")
    public List<Person> getPersons() {
        return repository.findAll();
    }
}
