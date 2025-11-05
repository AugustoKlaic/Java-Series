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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PersonServiceUsingThenAnswerAndDoAnswerTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @Test
    void shouldCreatePersonSuccessfully() {
        Person dto = TestFactory.createPersonWithoutId();

        doAnswer(invocation -> {
            String name = invocation.getArgument(0);
            assertEquals(name, dto.getName());
            return null;
        }).when(notificationService).sendWelcomeMessage(anyString());

        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });

        Person result = service.create(dto);
        verify(notificationService).sendWelcomeMessage(result.getName());
    }
}
