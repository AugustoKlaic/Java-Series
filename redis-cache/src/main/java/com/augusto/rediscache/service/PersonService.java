package com.augusto.rediscache.service;

import com.augusto.rediscache.domain.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    private static final Map<Long, Person> PERSONS_SIMULATED_DATABASE = new HashMap<Long, Person>() {
        {
            put(1L, new Person(1L, "carlos", "carlos@email.com"));
            put(2L, new Person(2L, "rafael", "rafael@email.com"));
            put(3L, new Person(3L, "john", "john@email.com"));
            put(4L, new Person(4L, "alfred", "alfred@email.com"));
            put(5L, new Person(5L, "peter", "peter@email.com"));
        }
    };

    @Cacheable("persons-by-id")
    public Person findById(Long id) {
        System.out.println("Searching product of id: " + id);
        simulateLatency();
        return PERSONS_SIMULATED_DATABASE.get(id);
    }

    @Cacheable("persons-all")
    public List<Person> findAll() {
        System.out.println("Listing all persons...");
        simulateLatency();
        return PERSONS_SIMULATED_DATABASE.values().stream().toList();
    }

    @CacheEvict(value = "persons-all", allEntries = true)
    public Long createPerson(Person person) {
        PERSONS_SIMULATED_DATABASE.put(person.id(), person);
        return person.id();
    }

    private void simulateLatency() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
