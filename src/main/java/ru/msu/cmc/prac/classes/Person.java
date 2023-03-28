package ru.msu.cmc.prac.classes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Person implements CommonClass<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "person_id")
    private Long id;

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
    private String birth_date;

    @Column(name = "death_date")
    private String death_date;

    @Column(name = "characteristics")
    private String characteristics;

    @Override
    public boolean equals(Object new_one) {
        if (this == new_one) return true;
        if (new_one == null || getClass() != new_one.getClass()) return false;
        Person other = (Person) new_one;
        return Objects.equals(id, other.id)
                && surname.equals(other.surname)
                && name.equals(other.name)
                && patronymic.equals(other.patronymic)
                && gender.equals(other.gender)
                && Objects.equals(birth_date, other.birth_date)
                && Objects.equals(death_date, other.death_date);
    }
}

