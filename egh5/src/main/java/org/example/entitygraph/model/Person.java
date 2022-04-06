package org.example.entitygraph.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "personSet")
    private Set<PersonGroup> personGroupSet = new LinkedHashSet<>();

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
