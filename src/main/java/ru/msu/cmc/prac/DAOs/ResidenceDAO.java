package ru.msu.cmc.prac.DAOs;

import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Residence;

import java.util.List;

public interface ResidenceDAO extends CommonClassDAO<Residence> {
    List<Person> getPlaceResidents(Residence residence);
}
