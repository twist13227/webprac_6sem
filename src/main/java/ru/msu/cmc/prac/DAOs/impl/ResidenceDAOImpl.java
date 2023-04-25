package ru.msu.cmc.prac.DAOs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.prac.DAOs.Person_ResidenceDAO;
import ru.msu.cmc.prac.DAOs.ResidenceDAO;
import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Person_Residence;
import ru.msu.cmc.prac.classes.Residence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ResidenceDAOImpl extends CommonClassDAOImpl<Residence, Long> implements ResidenceDAO {

    public ResidenceDAOImpl() {
        super(Residence.class);
    }
    @Autowired
    private final Person_ResidenceDAO person_residenceDAO = new Person_ResidenceDAOImpl();

    @Override
    public List<Person> getPlaceResidents(Residence residence) {
        List<Person> residents_list = new ArrayList<>();
        for(Person_Residence person_residence : person_residenceDAO.getAll()) {
            if (Objects.equals(person_residence.getResidence_id().getId(), residence.getId())) {
                residents_list.add(person_residence.getPerson_id());
            }
        }
        return residents_list;
    }
}
