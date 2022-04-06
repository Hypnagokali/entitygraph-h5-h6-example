package org.example.entitygraph;

import org.example.entitygraph.model.Person;
import org.example.entitygraph.model.PersonGroup;
import org.example.entitygraph.repository.PersonGroupRepository;

public class HibernateTestDataService {
    private PersonGroupRepository personGroupRepository;

    public HibernateTestDataService() {
        personGroupRepository = new PersonGroupRepository();

        if (personGroupRepository.findAll().size() == 0) {
            generateTestData();
        }
    }

    private void generateTestData() {
        System.out.println("GENERATE DATA");
        System.out.println("---------------------------");
        Person alex = new Person(0, "Alex");
        Person tessa = new Person(0, "Tessa");
        Person sandra = new Person(0, "Sandra");
        Person knut = new Person(0, "Knut");

        PersonGroup pg = new PersonGroup("women");
        pg.add(tessa);
        pg.add(sandra);

        PersonGroup pg2 = new PersonGroup("men");
        pg2.add(alex);
        pg2.add(knut);

        personGroupRepository.save(pg);
        personGroupRepository.save(pg2);
    }


}
