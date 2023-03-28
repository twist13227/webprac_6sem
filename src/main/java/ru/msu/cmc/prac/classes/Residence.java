package ru.msu.cmc.prac.classes;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "residence")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Residence implements CommonClass<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "residence_id")
    private Long id;
    @Column(nullable = false, name = "country")
    @NonNull
    private String country;
    @Column(nullable = false, name = "town")
    @NonNull
    private String town;
    @Column(nullable = false, name = "address")
    @NonNull
    private String address;
    @Column(name = "description")
    private String description;
    @Override
    public boolean equals(Object new_one) {
        if (this == new_one) return true;
        if (new_one == null || getClass() != new_one.getClass()) return false;
        Residence other = (Residence) new_one;
        return Objects.equals(id, other.id)
                && country.equals(other.country)
                && town.equals(other.town)
                && address.equals(other.address);
    }
}

