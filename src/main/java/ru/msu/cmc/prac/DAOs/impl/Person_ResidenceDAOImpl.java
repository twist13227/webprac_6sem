package ru.msu.cmc.prac.DAOs.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.prac.DAOs.Person_ResidenceDAO;
import ru.msu.cmc.prac.classes.Person_Residence;
import ru.msu.cmc.prac.classes.Residence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class Person_ResidenceDAOImpl extends CommonClassDAOImpl<Person_Residence, Long> implements Person_ResidenceDAO {

    public Person_ResidenceDAOImpl() {
        super(Person_Residence.class);
    }

    @Override
    public List<Residence> getPersonResidences(Long id) {
        List<Residence> residences_list = new ArrayList<>();
        for (Person_Residence person_residence : getAll()) {
            if (Objects.equals(person_residence.getPerson_id().getId(), id)) {
                residences_list.add(person_residence.getResidence_id());
            }
        }
        return residences_list;
    }
}