package ru.msu.cmc.prac;

import ru.msu.cmc.prac.DAOs.impl.PersonDAOImpl;
import ru.msu.cmc.prac.classes.Person;

public class StaticMain {
    public static void main(String[] args) {
        PersonDAOImpl personDAO = new PersonDAOImpl();
        Person person1 = personDAO.getSinglePersonByName("Курылкин", "Геннадий", "Ярославович");
        Tree root = new Tree(person1);
        root.addAllAncestors(root);
    }
}
