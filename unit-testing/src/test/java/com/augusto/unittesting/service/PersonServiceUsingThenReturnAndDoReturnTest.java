package com.augusto.unittesting.service;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceUsingThenReturnAndDoReturnTest extends UnitTestBase {


    @Mock
    private PersonRepository repository;

    @Spy
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @Test
    void shouldMockMethodSafelyWithDoReturn() {
        doReturn("MockedPrefix").when(notificationService).getWelcomePrefix();
        Person input = TestFactory.createPersonWithoutId();
        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });
        service.create(input);
        verify(notificationService, times(1)).getWelcomePrefix();
    }

    @Test
    void shouldExecuteRealMethodWithThenReturn() {
        when(notificationService.getWelcomePrefix()).thenReturn("MockedPrefix");

        Person input = TestFactory.createPersonWithoutId();
        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });
        service.create(input);

        verify(notificationService, times(1)).getWelcomePrefix();
    }
}
