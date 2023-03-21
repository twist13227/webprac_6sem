package ru.msu.cmc.prac.classes;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "residence")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "residence_id")
    private Integer residence_id;

    @Column(nullable = false, name = "country")
    @NonNull
    private String country;

    @Column(nullable = false, name = "town")
    @NonNull
    private String town;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;

    @Column(nullable = false, name = "description")
    private String description;

    @Override
    public boolean equals(Object new_one) {
        if (this == new_one) return true;
        if (new_one == null || getClass() != new_one.getClass()) return false;
        Residence other = (Residence) new_one;
        return Objects.equals(residence_id, other.residence_id)
                && country.equals(other.country)
                && town.equals(other.town)
                && address.equals(other.address);
    }
    public Integer getId(){
        return residence_id;
    }
    public void setId(Integer id){
        residence_id = id;
    }
}

