package ru.msu.cmc.prac.classes;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "person_residence")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Person_Residence  implements CommonClass<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "node_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    @NonNull
    private Person person_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "residence_id")
    @ToString.Exclude
    @NonNull
    private Residence residence_id;
}
