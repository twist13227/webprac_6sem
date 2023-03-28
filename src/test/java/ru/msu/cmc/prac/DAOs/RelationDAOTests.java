package ru.msu.cmc.prac.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Person_Residence;
import ru.msu.cmc.prac.classes.Relation;
import ru.msu.cmc.prac.classes.Residence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class RelationDAOTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private RelationDAO relationDAO;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void simpleTests() {
        List<Person> parents_test = relationDAO.getParents(personDAO.getById(1L));
        assertEquals(2, parents_test.size());
        List <Person> children_test = relationDAO.getChildren(personDAO.getById(2L));
        assertEquals("Семёнов", children_test.get(0).getSurname());
        assertEquals(1, children_test.size());
        List<Person> spouse_test = relationDAO.getSpouse(personDAO.getById(2L));
        assertEquals("Анна", spouse_test.get(0).getName());
        Person mother_test = relationDAO.getMother(personDAO.getById(1L));
        Person father_test = relationDAO.getFather(personDAO.getById(1L));
        assertEquals("Фёдоровна", mother_test.getPatronymic());
        assertEquals("Андреевич", father_test.getPatronymic());
        Relation test_fields1 = relationDAO.getById(1L);
        Relation test_fields2 = relationDAO.getById(2L);
        assertEquals(Relation.RelType.SPOUSE, test_fields1.getRelation_type());
        assertEquals("Дата женитьбы: 2010-03-19", test_fields1.getInformation());
        assertTrue(test_fields1.equals(test_fields1));
        assertFalse(test_fields1.equals(test_fields2));
        Person mother_test_false = relationDAO.getMother(personDAO.getById(8L));
        Person father_test_false = relationDAO.getFather(personDAO.getById(8L));
        assertNull(mother_test_false);
        assertNull(father_test_false);
        assertTrue(relationDAO.isBornInMarriage(personDAO.getById(1L)));
        assertFalse(relationDAO.isBornInMarriage(personDAO.getById(9L)));
    }
    @Test
    void PersonByRelTypeTest(){
        List<Person> personByreltype = relationDAO.getPersonByRelationType(personDAO.getById(4L), Relation.RelType.SPOUSE);
        assertEquals("Петрова", personByreltype.get(0).getSurname());
        assertEquals(1, personByreltype.size());
        List<Person> personByreltype1 = relationDAO.getPersonByRelationType(personDAO.getById(2L), Relation.RelType.BASTARD);
        assertEquals("Новикова", personByreltype1.get(0).getSurname());
        assertEquals(1, personByreltype1.size());
    }
    @Test
    void AdoptiveTests(){
        List<Person> adoptive_parents_test = relationDAO.getAdoptiveParents(personDAO.getById(11L));
        assertEquals(2, adoptive_parents_test.size());
        List <Person> adoptive_children_test = relationDAO.getAdoptedChildren(personDAO.getById(10L));
        assertEquals("Дмитриев", adoptive_children_test.get(0).getSurname());
        assertEquals(1, adoptive_children_test.size());
        Person adoptive_mother_test = relationDAO.getAdoptiveMother(personDAO.getById(11L));
        Person adoptive_father_test = relationDAO.getAdoptiveFather(personDAO.getById(11L));
        assertEquals("Фёдоровна", adoptive_mother_test.getPatronymic());
        assertEquals("Андреевич", adoptive_father_test.getPatronymic());
        Person adoptive_mother_test_false = relationDAO.getAdoptiveMother(personDAO.getById(13L));
        Person adoptive_father_test_false = relationDAO.getAdoptiveFather(personDAO.getById(13L));
        assertNull(adoptive_mother_test_false);
        assertNull(adoptive_father_test_false);
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
        personList.add(new Person(7L, "Новикова", "Юлия", "Алексеевна", "ж", "1997-07-19", null, "Врач"));
        personList.add(new Person(8L, "Яковлев", "Ярослав", "Алексеевич", "м", "1963-05-29", null, "Программист Яндекса"));
        personList.add(new Person(9L, "Климова", "Евгения", "Ярославна", "ж", "1987-07-19", null, "Медсестра"));
        personList.add(new Person(10L, "Семёнов", "Иван", "Андреевич", "м", "1988-10-12", null, "Сотрудник Майкрософт"));
        personList.add(new Person(11L, "Дмитриев", "Дмитрий", "Андреевич", "м", "1968-11-09", null, "Типичный работник завода"));
        personList.add(new Person(12L, "Иванова", "Ивана", "Фёдоровна", "ж", "1992-08-15", null, "Домработница"));
        personList.add(new Person(13L, "Дмитриев", "Игорь", "Андреевич", "м", "1968-11-09", null, "Типичный работник завода"));
        personDAO.saveCollection(personList);
        List<Relation> relationList = new ArrayList<>();
        relationList.add(new Relation(1L, personDAO.getById(2L), personDAO.getById(5L), Relation.RelType.SPOUSE, "Дата женитьбы: 2010-03-19"));
        relationList.add(new Relation(2L, personDAO.getById(5L), personDAO.getById(2L), Relation.RelType.SPOUSE, "Дата женитьбы: 2010-03-19"));
        relationList.add(new Relation(3L, personDAO.getById(4L), personDAO.getById(6L), Relation.RelType.SPOUSE, "Дата женитьбы: 1990-06-29"));
        relationList.add(new Relation(4L, personDAO.getById(6L), personDAO.getById(4L), Relation.RelType.SPOUSE, "Дата женитьбы: 2000-06-29"));
        relationList.add(new Relation(5L, personDAO.getById(1L), personDAO.getById(2L), Relation.RelType.CHILD, ""));
        relationList.add(new Relation(6L, personDAO.getById(1L), personDAO.getById(5L), Relation.RelType.CHILD, ""));
        relationList.add(new Relation(7L, personDAO.getById(2L), personDAO.getById(1L), Relation.RelType.PARENT, ""));
        relationList.add(new Relation(8L, personDAO.getById(5L), personDAO.getById(1L), Relation.RelType.PARENT, ""));
        relationList.add(new Relation(9L, personDAO.getById(7L), personDAO.getById(2L), Relation.RelType.PARENT, ""));
        relationList.add(new Relation(10L, personDAO.getById(2L), personDAO.getById(7L), Relation.RelType.BASTARD, ""));
        relationList.add(new Relation(11L, personDAO.getById(8L), personDAO.getById(9L), Relation.RelType.PARENT, ""));
        relationList.add(new Relation(12L, personDAO.getById(9L), personDAO.getById(8L), Relation.RelType.BASTARD, ""));
        relationList.add(new Relation(13L, personDAO.getById(10L), personDAO.getById(12L), Relation.RelType.SPOUSE, "Дата женитьбы: 2010-03-19"));
        relationList.add(new Relation(14L, personDAO.getById(12L), personDAO.getById(10L), Relation.RelType.SPOUSE, "Дата женитьбы: 2010-03-19"));
        relationList.add(new Relation(15L, personDAO.getById(11L), personDAO.getById(10L), Relation.RelType.ADOPTED_CHILD, ""));
        relationList.add(new Relation(16L, personDAO.getById(11L), personDAO.getById(12L), Relation.RelType.ADOPTED_CHILD, ""));
        relationList.add(new Relation(17L, personDAO.getById(10L), personDAO.getById(11L), Relation.RelType.ADOPTIVE_PARENT, ""));
        relationList.add(new Relation(18L, personDAO.getById(12L), personDAO.getById(11L), Relation.RelType.ADOPTIVE_PARENT, ""));
        relationDAO.saveCollection(relationList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("TRUNCATE relation RESTART IDENTITY CASCADE;").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE relation_relation_id_seq RESTART WITH 1;").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE person RESTART IDENTITY CASCADE;").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE person_person_id_seq RESTART WITH 1;").executeUpdate();
            entityManager.getTransaction().commit();
        }
    }
}
