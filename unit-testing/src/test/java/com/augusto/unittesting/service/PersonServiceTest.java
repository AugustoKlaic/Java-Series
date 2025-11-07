package com.augusto.unittesting.service;

import com.augusto.unittesting.config.UnitTestBase;
import com.augusto.unittesting.dto.Person;
import com.augusto.unittesting.fixture.TestFactory;
import com.augusto.unittesting.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

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
    class CreatePerson {

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

        private void verifyErrorMocksCalls() {
            verify(notificationService, never()).sendWelcomeMessage(anyString());
            verify(repository, never()).save(any(Person.class));
        }

        private void verifyInOrderMocksCalls() {
            inOrder.verify(repository, times(1)).save(personCaptor.capture());
            inOrder.verify(notificationService, times(1)).sendWelcomeMessage(personCaptor.getValue().getName());
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

            verifyInOrderMocksCalls();
        }

        @RepeatedTest(10)
        void shouldThrowExceptionWhenCreatePersonUnder18Years() {
            Person dto = TestFactory.createInvalidAgePerson();
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> personService.create(dto));
            assertEquals("Person must be over 18 years.", ex.getMessage());
            verifyErrorMocksCalls();
        }

        @ParameterizedTest(name = "shouldAllowNamesOtherThanAdmin: ''{0}''")
        @MethodSource("com.augusto.unittesting.fixture.TestFactory#generateValidNames")
        void shouldAllowNamesOtherThanAdmin(String name) {
            happyPathMocks();
            Person dto = TestFactory.createPersonWithoutIdAndName(name);
            Person result = personService.create(dto);
            assertEquals(name, result.getName());
            verifyInOrderMocksCalls();
        }

        @ParameterizedTest(name = "shouldNotAllowAdminName")
        @MethodSource("com.augusto.unittesting.fixture.TestFactory#generateInvalidName")
        void shouldNotAllowAdminName(String name) {
            Person dto = TestFactory.createPersonWithoutIdAndName(name);
            assertThrows(RuntimeException.class, () -> personService.create(dto));
            verifyErrorMocksCalls();
        }

        @Test
        void shouldCreateAllPersonsSuccessfully() {
            happyPathMocks();
            List<Person> dtos = TestFactory.createListPerson();
            List<Person> result = personService.createAll(dtos);
            assertFalse(result.isEmpty());
            verifyInOrderMocksCalls();
        }
    }

    @Nested
    class UpdatePerson {

        @Captor
        private ArgumentCaptor<Person> personCaptor;

        private void happyPathMocks(Person person) {
            lenient().doAnswer(ans -> {
                Long id = ans.getArgument(0);
                person.setId(id);
                return Optional.of(person);
            }).when(repository).findById(anyLong());

            lenient().when(repository.save(any(Person.class))).thenAnswer(ans -> ans.getArgument(0));
            lenient().doCallRealMethod().when(notificationService).sendWelcomeMessage(anyString());
        }

        private void errorPathMocks() {
            lenient().when(repository.findById(anyLong())).thenReturn(Optional.empty());
        }

        private void verifyInOrderMocksCalls() {
            inOrder.verify(repository, times(1)).findById(anyLong());
            inOrder.verify(repository, times(1)).save(personCaptor.capture());
        }

        private void verifyErrorMocksCalls() {
            verify(repository, times(1)).findById(anyLong());
            verify(repository, never()).save(any(Person.class));
        }

        @Test
        void shouldUpdatePersonNameSuccessfully() {
            Person person = TestFactory.createPersonWithoutId();
            happyPathMocks(person);
            Person result = personService.updateName(1L, "New Name");
            verifyInOrderMocksCalls();

            assertEquals(personCaptor.getValue().getId(), result.getId());
            assertEquals(personCaptor.getValue().getName(), result.getName());
        }

        @Test
        void shouldThrowNotFoundWhenTryingToUpdatePersonName() {
            errorPathMocks();
            RuntimeException ex = assertThrows(RuntimeException.class, () -> personService.updateName(1L, "Novo"));
            assertEquals("Person not found!", ex.getMessage());
            verifyErrorMocksCalls();
        }

        @ParameterizedTest(name = "shouldUpdateAgeIfNewAgeIsGreater: ''{0}''")
        @MethodSource("com.augusto.unittesting.fixture.TestFactory#generateRandomAges")
        void shouldUpdateAgeIfNewAgeIsGreater(Integer age) {
            Person person = TestFactory.createPersonWithoutIdAndAge(age-1);
            happyPathMocks(person);
            personService.updateAge(1L, age);
            verifyInOrderMocksCalls();
            assertEquals(age, personCaptor.getValue().getAge());
        }

        @ParameterizedTest(name = "shouldNotUpdateAgeIfNewAgeIsLower: ''{0}''")
        @MethodSource("com.augusto.unittesting.fixture.TestFactory#generateRandomAges")
        void shouldNotUpdateAgeIfNewAgeIsLower(Integer age) {
            Person person = TestFactory.createPersonWithoutIdAndAge(age+1);
            happyPathMocks(person);
            personService.updateAge(1L, age);
            verifyErrorMocksCalls();
        }
    }
}