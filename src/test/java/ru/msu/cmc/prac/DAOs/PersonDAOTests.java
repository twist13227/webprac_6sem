package ru.msu.cmc.prac.DAOs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.prac.classes.Person;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import ru.msu.cmc.prac.DAOs.PersonDAO.Filter;
import static ru.msu.cmc.prac.DAOs.PersonDAO.getFilterBuilder;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class PersonDAOTests {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Test
    void testSimpleManipulations() {
        List<Person> list_of_person = (List<Person>) personDAO.getAll();
        assertEquals(10, list_of_person.size());
        List<Person> all_person_name= personDAO.getAllPersonByName("Дмитриев", "Иван", "Андреевич");
        assertEquals(1, all_person_name.size());
        assertEquals("Андреевич", all_person_name.get(0).getPatronymic());
        Person person_by_id = personDAO.getById(3L);
        assertEquals("Яковлев", person_by_id.getSurname());
        Person unreal_person= personDAO.getById(999L);
        assertNull(unreal_person);
        assertEquals("м", person_by_id.getGender());
        Person person_equal = personDAO.getSinglePersonByName("Яковлев", "Никита", "Алексеевич");
        assertTrue(person_by_id.equals(person_equal));
        Person unreal_person2 = personDAO.getSinglePersonByName("Яковлев", "Андрей", "Алексеевич");
        assertNull(unreal_person2);
    }

    @Test
    void testUpdate() {
        String birth_date = "1920-09-15";
        String death_date = "2015-03-06";
        Person person_to_be_updated = personDAO.getSinglePersonByName("Никитин", "Алексей", "Петрович");
        person_to_be_updated.setBirth_date(birth_date);
        person_to_be_updated.setDeath_date(death_date);
        personDAO.update(person_to_be_updated);
        Person test_update_person = personDAO.getSinglePersonByName("Никитин", "Алексей", "Петрович");
        assertEquals(birth_date, test_update_person.getBirth_date());
        assertEquals(death_date, test_update_person.getDeath_date());
    }

    @Test
    void testDelete() {
        Person person_to_delete = personDAO.getSinglePersonByName("Семёнов", "Дмитрий", "Андреевич");
        personDAO.delete(person_to_delete);
        Person test_person = personDAO.getSinglePersonByName("Семёнов", "Дмитрий", "Андреевич");
        assertNull(test_person);
        Person person2_to_delete = personDAO.getSinglePersonByName("Никитин", "Аркадий", "Петрович");
        personDAO.deleteById(10L);
        Person test2_person = personDAO.getSinglePersonByName("Никитин", "Аркадий", "Петрович");
        assertNull(test2_person);
    }
    @Test
    void lifeYears() {
        Person person1_for_life_test = personDAO.getSinglePersonByName("Иванова", "Анна", "Фёдоровна");
        assertEquals("1992-08-15 - н.в.", personDAO.getPersonLifeYears(person1_for_life_test));
        Person person2_for_life_test = personDAO.getSinglePersonByName("Терентьева", "Елизавета", "Ивановна");
        assertEquals("1917-03-06 - 2010-06-03", personDAO.getPersonLifeYears(person2_for_life_test));
    }
    @Test
    void characteristics() {
        Person person_for_characteristics = personDAO.getSinglePersonByName("Терентьева", "Елизавета", "Ивановна");
        String characteristics = personDAO.getPersonCharacteristics(person_for_characteristics);
        String correct_characteristics = "";
        correct_characteristics += "ФИО: " + "Терентьева" + ' ' + "Елизавета" + ' ' + "Ивановна" + "\n";
        correct_characteristics += "Годы жизни: " + "1917-03-06 - 2010-06-03"  + "\n";
        correct_characteristics += "Краткая характеристика: " + "Учительница";
        assertEquals(characteristics, correct_characteristics);
    }
    @Test
    void testFilter(){
        Filter test_filter = Filter.builder()
                .surname("Семёнов")
                .name("Дмитрий")
                .patronymic("Андреевич")
                .build();
        PersonDAO.Filter.FilterBuilder filter_builder = getFilterBuilder();
        assertThat(test_filter.getSurname())
                .isEqualTo("Семёнов");
        assertThat(test_filter.getName())
                .isEqualTo("Дмитрий");
        assertThat(test_filter.getPatronymic())
                .isEqualTo("Андреевич");
        List<Person> all_filtered_person_name= personDAO.getPeopleByFilter(test_filter);
        assertEquals(1, all_filtered_person_name.size());
    }

    @BeforeEach
    void beforeEach() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(53L, "Семёнов", "Дмитрий", "Андреевич", "м", "1988-10-12", null, "Сотрудник Майкрософт"));
        personList.add(new Person(45L, "Дмитриев", "Иван", "Андреевич", "м", "1968-11-09", null, "Типичный работник завода"));
        personList.add(new Person(19L, "Яковлев", "Никита", "Алексеевич", "м", "1973-05-29", null, "Программист Касперского"));
        personList.add(new Person(34L, "Никитин", "Алексей", "Петрович", "м", "1956-04-22", null, "Преподаватель университета"));
        personList.add(new Person(25L, "Иванова", "Анна", "Фёдоровна", "ж", "1992-08-15", null, "Домработница"));
        personList.add(new Person(67L, "Петрова", "Екатерина", "Евгеньевна", "ж", "1957-07-19", null, "Пекарь"));
        personList.add(new Person(77L, "Терентьева", "Елизавета", "Ивановна", "ж", "1917-03-06", "2010-06-03", "Учительница"));
        personList.add(new Person(29L, "Яковлев", "Андрей", "Алексеевич", "м", "1973-05-29", null, "Программист Касперского"));
        personList.add(new Person(39L, "Яковлев", "Андрей", "Алексеевич", "м", "1973-05-29", null, "Программист Касперского"));
        personList.add(new Person(34L, "Никитин", "Аркадий", "Петрович", "м", "1956-04-22", null, "Преподаватель университета"));
        personDAO.saveCollection(personList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE person RESTART IDENTITY CASCADE;").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE person_person_id_seq RESTART WITH 1;").executeUpdate();
            entityManager.getTransaction().commit();
        }
    }
}