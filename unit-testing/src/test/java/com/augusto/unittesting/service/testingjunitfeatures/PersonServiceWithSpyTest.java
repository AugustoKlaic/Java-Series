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
import org.mockito.Spy;

import static com.augusto.unittesting.fixture.TestFactory.createPersonWithoutId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceWithSpyTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Spy
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @Test
    void shouldCallRealNotificationAndLogForCreate() {
        Person dto = createPersonWithoutId();
        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });

        Person result = service.create(dto);

        assertEquals(dto.getName(), result.getName());
        verify(notificationService).sendWelcomeMessage(result.getName());
    }

    @Test
    void shouldSpyAndOverrideNotification() {
        Person dto = createPersonWithoutId();

        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });

        doNothing().when(notificationService).sendWelcomeMessage(anyString());
        Person result = service.create(dto);
        verify(notificationService).sendWelcomeMessage(result.getName());
    }


}
