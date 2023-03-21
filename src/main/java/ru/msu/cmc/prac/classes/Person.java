package ru.msu.cmc.prac.classes;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "person")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "person_id")
    private Integer person_id;

    @Column(nullable = false, name = "surname")
    @NonNull
    private String surname;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(nullable = false, name = "gender")
    @NonNull
    private String gender;

    @Column(name = "birth_date")
    @NonNull
    private Timestamp birth_date;

    @Column(name = "death_date")
    private Timestamp death_date;

    @Column(name = "characteristics")
    private String characteristics;

    @Override
    public boolean equals(Object new_one) {
        if (this == new_one) return true;
        if (new_one == null || getClass() != new_one.getClass()) return false;
        Person other = (Person) new_one;
        return Objects.equals(person_id, other.person_id)
                && surname.equals(other.surname)
                && name.equals(other.name)
                && patronymic.equals(other.patronymic)
                && gender.equals(other.gender)
                && Objects.equals(birth_date, other.birth_date)
                && Objects.equals(death_date, other.death_date);
    }
    public Integer getId(){
        return person_id;
    }
    public void setId(Integer id){
        person_id = id;
    }
}

