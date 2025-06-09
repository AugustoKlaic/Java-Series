package com.augusto.rediscache.controller;

import com.augusto.rediscache.domain.Person;
import com.augusto.rediscache.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Person> getAll() {
        return service.findAll();
    }

    @PostMapping
    Long cratePerson(@RequestBody Person person) {
        return service.createPerson(person);
    }
}
