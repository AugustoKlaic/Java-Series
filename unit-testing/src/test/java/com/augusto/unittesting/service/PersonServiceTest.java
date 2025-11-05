package com.augusto.unittesting.service;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PersonServiceTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Spy
    private NotificationService notificationService;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        inOrderOf(repository, notificationService);
    }

    @Nested
    class createPerson {

        @Captor
        private ArgumentCaptor<Person> personCaptor;

        private void happyPathMocks() {
            lenient().when(repository.save(any(Person.class))).thenAnswer(ans -> {
                Person person = ans.getArgument(0);
                person.setId(1L);
                       return person;
                });

            lenient().doCallRealMethod().when(notificationService).sendWelcomeMessage(anyString());
        }

        @Test
        void shouldCreatePersonSuccessfully() {
            happyPathMocks();
            Person dto = TestFactory.createPersonWithoutId();
            Person result = personService.create(dto);

            assertNotNull(result);
            assertEquals(dto.getId(), result.getId());
            assertEquals(dto.getName(), result.getName());
            assertEquals(dto.getAge(), result.getAge());

            inOrder.verify(repository, times(1)).save(personCaptor.capture());
            inOrder.verify(notificationService, times(1)).sendWelcomeMessage(personCaptor.getValue().getName());
        }

    }
}