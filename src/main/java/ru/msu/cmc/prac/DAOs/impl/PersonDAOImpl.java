package ru.msu.cmc.prac.DAOs.impl;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.prac.DAOs.PersonDAO;
import ru.msu.cmc.prac.classes.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl extends CommonClassDAOImpl<Person> implements PersonDAO {
    public PersonDAOImpl() {
        super(Person.class);
    }
    @Override
    public List<Person> getAllPersonByName(String personSurname, String personName, String personPatronymic) {
        try (Session session = openSession()) {
            Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotSurname AND surname LIKE :gotName AND patronymic like :gotPatronymic", Person.class)
                    .setParameter("gotSurname", lookslike(personSurname))
                    .setParameter("gotName", lookslike(personName))
                    .setParameter("gotPatronymic", lookslike(personPatronymic));
            if (query.getResultList().size() == 0)
                return null;
            else
                return query.getResultList();
        }
    }

    @Override
    public Person getSinglePersonByName(String personSurname, String personName, String personPatronymic){
        List<Person> people = this.getAllPersonByName(personSurname, personName, personPatronymic);
        if (people == null)
            return null;
        else {
            if (people.size() == 1)
                return people.get(0);
            else
                return null;
        }
    }

    @Override
    public String getPersonLifeYears(Person person) {
        String ret = "";
        if (person.getBirth_date().toString() != null)
            ret += person.getBirth_date().toString() + " - ";
        else
            ret += "Неизвестно - ";
        if (person.getDeath_date().toString() != null)
            ret += person.getDeath_date().toString();
        else
            ret += "н.в.";
        return ret;
    }
    @Override
    public String getPersonCharacteristics(Person person){
        String ret = "";
        ret += "ФИО: " + person.getSurname() + ' ' + person.getName() + ' ' + person.getPatronymic() + "\n";
        ret += "Годы жизни: " + getPersonLifeYears(person) + "\n";
        ret += "Краткая характеристика: " + person.getCharacteristics();
        return ret;
    }

    @Override
    public List<Person> getPeopleByFilter(Filter filter) {
        try (Session session = openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null)
                predicates.add(builder.like(root.get("name"), lookslike(filter.getName())));
            if (predicates.size() != 0)
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
    private String lookslike(String param) {
        return "%" + param + "%";
    }
}
