package com.augusto.unittesting.service;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import org.junit.jupiter.api.RepeatedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class PersonServiceRepeatedTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @RepeatedTest(10)
    void shouldThrowExceptionWhenAgeIsUnder18() {
        Person dto = TestFactory.createInvalidAgePerson();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        assertEquals("Person must be over 18 years.", ex.getMessage());
        verify(repository, never()).save(any(Person.class));
    }
}
