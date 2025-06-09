package com.augusto.rediscache.service;

import com.augusto.rediscache.domain.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PersonService {

    private static Map<Long, Person> PERSONS_SIMULATED_DATABASE = Map.of(
            1L, new Person(1L, "carlos", "carlos@email.com"),
            2L, new Person(2L, "rafael", "rafael@email.com"),
            3L, new Person(3L, "john", "john@email.com"),
            4L, new Person(4L, "alfred", "alfred@email.com"),
            5L, new Person(5L, "peter", "peter@email.com")
    );

    @Cacheable("persons-by-id")
    public Person findById(Long id) {
        System.out.println("Searching product of id: " + id);
        simulateLatency();
        return PERSONS_SIMULATED_DATABASE.get(id);
    }

    private void simulateLatency() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
