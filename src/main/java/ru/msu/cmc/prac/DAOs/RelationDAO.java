package ru.msu.cmc.prac.DAOs;

import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Relation;

import java.util.List;

public interface RelationDAO extends CommonClassDAO<Relation, Long> {
    List<Person> getPersonByRelationType(Person person, Relation.RelType type);
    Person getMother(Person person);
    Person getFather(Person person);
    List <Person> getSpouse(Person person);
    List<Person> getParents(Person person);
    List<Person> getChildren(Person person);
    Person getAdoptiveMother(Person person);
    Person getAdoptiveFather(Person person);
    List<Person> getAdoptiveParents(Person person);
    List<Person> getAdoptedChildren(Person person);
    boolean isBornInMarriage(Person person);
}

