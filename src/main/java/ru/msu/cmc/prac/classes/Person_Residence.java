package ru.msu.cmc.prac.classes;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "person_residence")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Person_Residence{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "node_id")
    private Integer node_id;

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

    public Integer getId(){
        return node_id;
    }
    public void setId(Integer id){
        node_id = id;
    }
}
