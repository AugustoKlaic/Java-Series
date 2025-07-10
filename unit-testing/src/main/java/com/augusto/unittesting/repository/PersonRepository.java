package com.augusto.unittesting.repository;

import com.augusto.unittesting.dto.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonRepository {

    private final Map<Long, Person> banco = new HashMap<>();
    private long sequence = 1L;

    public Person save(Person pessoa) {
        if (pessoa.getId() == null) {
            pessoa.setId(sequence++);
        }
        banco.put(pessoa.getId(), pessoa);
        return pessoa;
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public List<Person> listAll() {
        return new ArrayList<>(banco.values());
    }

    public void delete(Long id) {
        banco.remove(id);
    }

    public boolean exists(Long id) {
        return banco.containsKey(id);
    }
}
