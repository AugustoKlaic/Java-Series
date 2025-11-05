package com.augusto.unittesting.service.testingjunitfeatures;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import com.augusto.unittesting.service.NotificationService;
import com.augusto.unittesting.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PersonServiceWithCaptorTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @Captor
    private ArgumentCaptor<String> messageCaptor;

    @BeforeEach
    public void setup() {
        when(repository.save(any(Person.class)))
                .thenAnswer(ans -> {
                    Person person = ans.getArgument(0);
                    person.setId(1L);
                    return person;
                });
        doNothing().when(notificationService).sendWelcomeMessage(anyString());

    }

    @Test
    void shouldCaptureMessageSentOnNotification() {
        Person dto = TestFactory.createPersonWithoutId();

        Person result = service.create(dto);
        verify(notificationService, times(1)).sendWelcomeMessage(messageCaptor.capture());
        assertEquals(result.getName(), messageCaptor.getValue());
    }

    @Test
    void shouldCaptureMessageSentOnNotificationUsingLocalCaptor() {
        Person dto = TestFactory.createPersonWithoutId();

        Person result = service.create(dto);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(notificationService, times(1)).sendWelcomeMessage(messageCaptor.capture());
        assertEquals(result.getName(), messageCaptor.getValue());
    }

    @Test
    void shouldCaptureMultipleValuesWhenSendingMessage() {
        List<Person> persons = TestFactory.createListPerson();

        List<Person> results = service.createAll(persons);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        verify(notificationService, times(persons.size())).sendWelcomeMessage(nameCaptor.capture());

        assertEquals(results.size(), nameCaptor.getAllValues().size());

        for (int i = 0; i < results.size(); i++) {
            assertEquals(results.get(i).getName(), nameCaptor.getAllValues().get(i));
        }
    }
}
