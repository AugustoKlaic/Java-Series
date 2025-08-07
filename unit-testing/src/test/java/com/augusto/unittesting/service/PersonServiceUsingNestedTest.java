package com.augusto.unittesting.service;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PersonServiceUsingNestedTest extends UnitTestBase {

    @Mock
    private PersonRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PersonService service;

    @Nested
    class CreatePerson {

        @Test
        void shouldCreatePersonSuccessfully() {
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
            verify(notificationService, times(1)).sendWelcomeMessage(dto.getName());
            verify(repository, times(1)).save(dto);
        }

        @Test
        void shouldThrowExceptionWhenAgeIsUnder18() {
            Person dto = TestFactory.createInvalidAgePerson();

            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.create(dto));
            assertEquals("Person must be over 18 years.", ex.getMessage());
            verify(repository, never()).save(any(Person.class));
        }

        @Test
        void shouldThrowExceptionWhenNameIsAdmin() {
            Person dto = TestFactory.createInvalidNamePerson();

            RuntimeException ex = assertThrows(RuntimeException.class, () -> service.create(dto));
            assertEquals("Name not allowed", ex.getMessage());
            verify(repository, never()).save(any(Person.class));
            verify(notificationService, never()).sendWelcomeMessage(anyString());
        }
    }

    @Nested
    class UpdatePersonName {

        @Test
        void shouldUpdatePersonName() {
            Person person = TestFactory.createPersonOver18Years();

            when(repository.findById(anyLong())).thenReturn(Optional.of(person));
            when(repository.save(any(Person.class))).thenReturn(person);

            Person result = service.updateName(person.getId(), "New Name");

            assertEquals(person.getId(), result.getId());
            assertEquals("New Name", result.getName());
        }

        @Test
        void shouldThrowWhenUpdateNameIfPersonNotFound() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            RuntimeException ex = assertThrows(RuntimeException.class, () -> service.updateName(1L, "Novo"));
            assertEquals("Person not found!", ex.getMessage());
        }
    }

    @Nested
    class UpdatePersonAge {

        @Test
        void shouldUpdateAgeOnlyIfNewAgeIsGreater() {
            Person existing = TestFactory.createPersonOver18Years();

            when(repository.findById(anyLong())).thenReturn(Optional.of(existing));
            when(repository.save(any(Person.class))).thenReturn(existing);

            service.updateAge(1L, 35);

            verify(repository, times(1)).save(existing);
            verify(repository, times(1)).findById(anyLong());
            assertEquals(35, existing.getAge());
        }

        @Test
        void shouldNotUpdateAgeIfNewIsSmaller() {
            Person existing = TestFactory.createPersonOver18Years();

            when(repository.findById(anyLong())).thenReturn(Optional.of(existing));

            service.updateAge(1L, 10);

            verify(repository, never()).save(any());
            assertTrue(existing.getAge() > 10);
        }

        @Test
        void shouldThrowErrorWhenNotFoundPersonWhenUpdateAge() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> service.updateAge(1L, 10));

            verify(repository, never()).save(any());
        }
    }

    @Nested
    class DeletePerson {

        @Test
        void shouldDeletePersonById() {
            when(repository.exists(anyLong())).thenReturn(true);
            service.delete(5L);
            verify(repository).delete(5L);
        }

        @Test
        void shouldThrowErrorWhenDeletePersonById() {
            when(repository.exists(anyLong())).thenReturn(false);
            RuntimeException ex = assertThrows(RuntimeException.class, () -> service.delete(5L));
            assertEquals("Person not found.", ex.getMessage());
            verify(repository, never()).delete(5L);
        }
    }

    @Nested
    class ListAllPerson {

        @Test
        void shouldListAllPersons() {
            List<Person> people = TestFactory.createListPerson();

            when(repository.listAll()).thenReturn(people);

            List<Person> result = service.listAll();

            assertEquals(people.size(), result.size());
            for (int i = 0; i < result.size(); i++) {
                assertEquals(people.get(i).getName(), result.get(i).getName());
                assertEquals(people.get(i).getAge(), result.get(i).getAge());
                assertEquals(people.get(i).getId(), result.get(i).getId());
            }
        }
    }
}
