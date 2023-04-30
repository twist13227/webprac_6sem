package ru.msu.cmc.prac.DAOs.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.prac.classes.Person;

import ru.msu.cmc.prac.DAOs.RelationDAO;
import ru.msu.cmc.prac.classes.Relation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RelationDAOImpl extends CommonClassDAOImpl<Relation, Long> implements RelationDAO {

    public RelationDAOImpl(){
        super(Relation.class);
    }

    @Override
    public List<Person> getPersonByRelationType(Person person, Relation.RelType type) {
        if (type == Relation.RelType.SPOUSE)
            return getSpouse(person);
        List<Person> res = new ArrayList<>();
        for (var relation : getRelation(type))
            if (Objects.equals(relation.getFirst_person_id().getId(), person.getId()))
                res.add(relation.getSecond_person_id());
        return res;
    }

    @Override
    public Person getMother(Person person) {
        List<Person> personList = getParents(person);
        for (Person mother : personList) {
            if (mother.getGender().equals("ж")) {
                return mother;
            }
        }
        return null;
    }

    @Override
    public Person getFather(Person person) {
        List<Person> personList = getParents(person);
        for (Person father : personList) {
            if (father.getGender().equals("м")) {
                return father;
            }
        }
        return null;
    }

    @Override
    public boolean isBornInMarriage(Person person) {
        for (Relation relation : getRelation(Relation.RelType.BASTARD))
            if (Objects.equals(relation.getFirst_person_id().getId(), person.getId()))
                return false;
        return true;
    }

    @Override
    public List<Person> getParents(Person person) {
        List<Person> personList = getPersonByRelationType(person, Relation.RelType.CHILD);
        personList.addAll(getPersonByRelationType(person, Relation.RelType.BASTARD));
        return personList;
    }
    @Override
    public List<Person> getChildren(Person person) {
        return getPersonByRelationType(person, Relation.RelType.PARENT);
    }

    @Override
    public Person getAdoptiveMother(Person person) {
        List<Person> personList = getAdoptiveParents(person);
        for (Person adoptive_mother : personList)
            if (adoptive_mother.getGender().equals("ж"))
                return adoptive_mother;
        return null;
    }

    @Override
    public Person getAdoptiveFather(Person person) {
        List<Person> personList = getAdoptiveParents(person);
        for (Person adoptive_father : personList)
            if (adoptive_father.getGender().equals("м"))
                return adoptive_father;
        return null;
    }

    @Override
    public List<Person> getAdoptiveParents(Person person) {
        return getPersonByRelationType(person, Relation.RelType.ADOPTED_CHILD);
    }
    @Override
    public List<Person> getAdoptedChildren(Person person) {
        return getPersonByRelationType(person, Relation.RelType.ADOPTIVE_PARENT);
    }
    private List<Relation> getRelation(Relation.RelType reltype) {
        try (Session session = sessionFactory.openSession()) {
            Query<Relation> query = session.createQuery("FROM Relation WHERE relation_type = :gotType", Relation.class)
                    .setParameter("gotType", reltype);
            return query.getResultList();
        }
    }

    @Override
    public List<Person> getSpouse(Person person) {
        List<Person> res = new ArrayList<>();
        for (var relation : getRelation(Relation.RelType.SPOUSE)) 
            if (Objects.equals(relation.getFirst_person_id().getId(), person.getId()))
                res.add(relation.getSecond_person_id());
        return res;
    }
}
