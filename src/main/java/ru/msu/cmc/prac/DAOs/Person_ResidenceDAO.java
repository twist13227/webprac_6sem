package ru.msu.cmc.prac.DAOs;

import ru.msu.cmc.prac.classes.Person_Residence;
import ru.msu.cmc.prac.classes.Residence;

import java.util.List;

public interface Person_ResidenceDAO extends CommonClassDAO<Person_Residence, Long>  {
    List<Residence> getPersonResidences(Long id);
}
