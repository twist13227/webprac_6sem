package ru.msu.cmc.prac.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Person_Residence;
import ru.msu.cmc.prac.classes.Residence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ResidenceDAOTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private ResidenceDAO residenceDAO;
    @Autowired
    private Person_ResidenceDAO person_residenceDAO;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testPersonResidences(){
        List <Residence> testResidences = person_residenceDAO.getPersonResidences(1L);
        Residence testResidences1 = testResidences.get(0);
        assertEquals(testResidences1.getCountry(), "Россия");
        assertEquals(testResidences1.getTown(), "Москва");
        assertEquals(testResidences1.getAddress(), "улица Пушкина, дом 5, кв. 50");
        assertEquals(testResidences1.getId(), 1L);
        assertEquals(testResidences1.getDescription(), "Квартира в спальном районе Москвы");
        assertTrue(testResidences1.equals(testResidences1));
        Residence testResidence2 = residenceDAO.getById(1L);
        assertTrue(testResidences1.equals(testResidence2));
        assertEquals(3, testResidences.size());
    }
    @Test
    void testAllPersonOfResidence(){
        Person_Residence person_residence_by_id = person_residenceDAO.getById(1L);
        assertEquals(person_residence_by_id.getId(), 1L);
        List <Person> testPerson = residenceDAO.getPlaceResidents(person_residence_by_id.getResidence_id());
        assertEquals(3, testPerson.size());
    }
    @BeforeEach
    void beforeEach() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Семёнов", "Дмитрий", "Андреевич", "м", "1988-10-12", null, "Сотрудник Майкрософт"));
        personList.add(new Person(2L, "Дмитриев", "Иван", "Андреевич", "м", "1968-11-09", null, "Типичный работник завода"));
        personList.add(new Person(3L, "Яковлев", "Никита", "Алексеевич", "м", "1973-05-29", null, "Программист Касперского"));
        personList.add(new Person(4L, "Никитин", "Алексей", "Петрович", "м", "1956-04-22", null, "Преподаватель университета"));
        personList.add(new Person(5L, "Иванова", "Анна", "Фёдоровна", "ж", "1992-08-15", null, "Домработница"));
        personList.add(new Person(6L, "Петрова", "Екатерина", "Евгеньевна", "ж", "1957-07-19", null, "Пекарь"));
        personDAO.saveCollection(personList);
        List<Residence> residencesList = new ArrayList<>();
        residencesList.add(new Residence(1L, "Россия", "Москва", "улица Пушкина, дом 5, кв. 50", "Квартира в спальном районе Москвы"));
        residencesList.add(new Residence(2L, "Россия", "Хабаровск", "улица Пушкина, дом 4, кв. 35", "Однушка в Хабаровске"));
        residencesList.add(new Residence(3L, "Россия", "Омск", "улица Пушкина, дом 20, кв. 90", "Квартира бабушки в Омске"));
        residencesList.add(new Residence(4L, "Россия", "Одинцово", "улица Пушкина, дом 34, кв. 3", "Загородный дом"));
        residencesList.add(new Residence(5L, "Россия", "Санкт-Петербург", "улица Пушкина, дом 67, кв. 47", "Квартира в спальном районе Санкт-Петербурга"));
        residenceDAO.saveCollection(residencesList);
        List<Person_Residence> person_residenceList = new ArrayList<>();
        person_residenceList.add(new Person_Residence(1L, personDAO.getById(1L), residenceDAO.getById(1L)));
        person_residenceList.add(new Person_Residence(2L, personDAO.getById(1L), residenceDAO.getById(2L)));
        person_residenceList.add(new Person_Residence(3L, personDAO.getById(1L), residenceDAO.getById(3L)));
        person_residenceList.add(new Person_Residence(4L, personDAO.getById(2L), residenceDAO.getById(1L)));
        person_residenceList.add(new Person_Residence(5L, personDAO.getById(3L), residenceDAO.getById(1L)));
        person_residenceList.add(new Person_Residence(6L, personDAO.getById(4L), residenceDAO.getById(4L)));
        person_residenceList.add(new Person_Residence(7L, personDAO.getById(5L), residenceDAO.getById(5L)));
        person_residenceDAO.saveCollection(person_residenceList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE person_residence RESTART IDENTITY CASCADE;").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE person_residence_node_id_seq RESTART WITH 1;").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE residence RESTART IDENTITY CASCADE;").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE residence_residence_id_seq RESTART WITH 1;").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE person RESTART IDENTITY CASCADE;").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE person_person_id_seq RESTART WITH 1;").executeUpdate();
            entityManager.getTransaction().commit();
        }
    }
}
