package com.augusto.unittesting.controller;

import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Person> updateName(@PathVariable Long id, @RequestBody String newName) {
        return ResponseEntity.ok(service.updateName(id, newName));
    }

    @GetMapping
    public ResponseEntity<List<Person>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
