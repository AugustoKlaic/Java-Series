package com.augusto.unittesting.service.testingjunitfeatures;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import com.augusto.unittesting.service.NotificationService;
import com.augusto.unittesting.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PersonServiceUsingArgThatTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @Test
    void shouldCreatePersonAndVerifyWithArgThat() {
        Person dto = TestFactory.createPersonWithoutId();

        doNothing().when(notificationService).sendWelcomeMessage(anyString());
        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });

        Person result = service.create(dto);

        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getAge(), result.getAge());

        verify(repository, times(1)).save(argThat(saved -> saved.getName().equals(dto.getName())));
        verify(notificationService, times(1)).sendWelcomeMessage(argThat(sent -> sent.equals(dto.getName())));
    }
}
