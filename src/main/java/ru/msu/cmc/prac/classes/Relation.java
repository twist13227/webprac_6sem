package ru.msu.cmc.prac.classes;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "relation")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Relation implements CommonClass<Long> {
    public static enum RelType {
        PARENT,
        CHILD,
        PARENT_IN_LAW,
        CHILD_IN_LAW,
        SPOUSE,
        BASTARD,
        ADOPTED_CHILD,
        ADOPTIVE_PARENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "relation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_person_id")
    @ToString.Exclude
    @NonNull
    private Person first_person_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_person_id")
    @ToString.Exclude
    @NonNull
    private Person second_person_id;

    @Column(nullable = false, name = "relation_type")
    @NonNull
    private RelType relation_type;

    @Column(name = "information")
    private String information;

    @Override
    public boolean equals(Object new_one) {
        if (this == new_one) return true;
        if (new_one == null || getClass() != new_one.getClass()) return false;
        Relation other = (Relation) new_one;
        return Objects.equals(id, other.id)  && Objects.equals(first_person_id, other.first_person_id)  && Objects.equals(second_person_id, other.second_person_id)  && Objects.equals(relation_type, other.relation_type);
    }
}
