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
@NamedEntityGraph(
        name = "PersonGroup.personSet",
        attributeNodes = @NamedAttributeNode("personSet")
)
public class PersonGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String groupName = "";

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            joinColumns = @JoinColumn(name = "person_group_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> personSet = new LinkedHashSet<>();

    public PersonGroup(String groupName) {
        this.groupName = groupName;
    }

    public void add(Person p) {
        p.getPersonGroupSet().add(this);
        personSet.add(p);
    }
}
