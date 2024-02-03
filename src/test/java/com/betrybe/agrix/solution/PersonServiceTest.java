package com.betrybe.agrix.solution;

import com.betrybe.agrix.AgrixApplication;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AgrixApplication.class)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class PersonServiceTest {
  @Autowired
  private PersonService personService;
  private Person person;

  @BeforeAll
  public void setUp() {
    person = new Person();
    person.setUsername("username");
    person.setPassword("password");
    person.setRole(Role.ADMIN);
  }

  @Test
  @DisplayName("Test Create Person")
  public void testCreatePerson() {
    Person createdPerson = personService.create(person);
    Assertions.assertNotNull(createdPerson);
    Assertions.assertNotNull(createdPerson.getId());
    Assertions.assertEquals(person.getUsername(), createdPerson.getUsername());
    Assertions.assertEquals(person.getPassword(), createdPerson.getPassword());
    Assertions.assertEquals(person.getRole(), createdPerson.getRole());
  }

  @Test
  @DisplayName("Test Find Person By Id")
  public void testFindPersonById() {
    Person createdPerson = personService.create(person);
    Person foundPerson = personService.getPersonById(createdPerson.getId());
    Assertions.assertNotNull(foundPerson);
    Assertions.assertEquals(createdPerson.getId(), foundPerson.getId());
    Assertions.assertEquals(createdPerson.getUsername(), foundPerson.getUsername());
    Assertions.assertEquals(createdPerson.getPassword(), foundPerson.getPassword());
    Assertions.assertEquals(createdPerson.getRole(), foundPerson.getRole());
  }

  @Test
  @DisplayName("Test Find Person By Username")
  public void testFindPersonByUsername() {
    Person createdPerson = personService.create(person);
    Person foundPerson = personService.getPersonByUsername(createdPerson.getUsername());
    Assertions.assertNotNull(foundPerson);
    Assertions.assertEquals(createdPerson.getId(), foundPerson.getId());
    Assertions.assertEquals(createdPerson.getUsername(), foundPerson.getUsername());
    Assertions.assertEquals(createdPerson.getPassword(), foundPerson.getPassword());
    Assertions.assertEquals(createdPerson.getRole(), foundPerson.getRole());
  }
}
