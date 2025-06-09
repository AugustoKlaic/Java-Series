package com.augusto.rediscache.controller;

import com.augusto.rediscache.domain.Person;
import com.augusto.rediscache.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
