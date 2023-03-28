package ru.msu.cmc.prac.DAOs;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.prac.classes.Person;

import java.util.List;

public interface PersonDAO extends CommonClassDAO<Person, Long> {
    Person getSinglePersonByName(String personSurname, String personName, String personPatronymic);
    List<Person> getAllPersonByName(String personSurname, String personName, String personPatronymic);
    String getPersonLifeYears(Person person);
    String getPersonCharacteristics(Person person);
    List<Person> getPeopleByFilter(Filter filter);

    @Builder
    @Getter
    class Filter {
        private String surname, name, patronymic;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}
