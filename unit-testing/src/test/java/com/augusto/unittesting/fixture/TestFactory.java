package com.augusto.unittesting.fixture;

import com.augusto.unittesting.dto.Person;
import org.instancio.Instancio;

import java.util.List;

import static org.instancio.Select.field;

public class TestFactory {

    public static Person createPersonOver18Years() {
        return Instancio.of(Person.class)
                .generate(field(Person::getId), gen -> gen.longs().range(1L, 100L))
                .generate(field(Person::getName), gen -> gen.text().pattern("Person####"))
                .generate(field(Person::getAge), gen -> gen.ints().range(18, 80))
                .create();
    }

    public static Person createPersonWithLessThen18Years() {
        return Instancio.of(Person.class)
                .generate(field(Person::getId), gen -> gen.longs().range(1L, 100L))
                .generate(field(Person::getName), gen -> gen.text().pattern("Person####"))
                .generate(field(Person::getAge), gen -> gen.ints().range(0, 18))
                .create();
    }

    public static Person createPersonWithoutId() {
        return Instancio.of(Person.class)
                .ignore(field(Person::getId))
                .generate(field(Person::getName), gen -> gen.text().pattern("Person####"))
                .generate(field(Person::getAge), gen -> gen.ints().range(10, 80))
                .create();
    }

    public static List<Person> createListPerson() {
        return Instancio.ofList(Person.class)
                .size(5)
                .generate(field(Person::getAge), gen -> gen.ints().range(18, 80))
                .generate(field(Person::getName), gen -> gen.text().pattern("Person####"))
                .generate(field(Person::getId), gen -> gen.longs().range(1L, 100L))
                .create();
    }
}
