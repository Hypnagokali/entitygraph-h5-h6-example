package org.example.entitygraph.repository;

import org.example.entitygraph.model.PersonGroup;
import org.hibernate.query.Query;

import java.util.Set;
import java.util.stream.Collectors;

public class PersonGroupRepository extends AbstractDaoRepository<PersonGroup> {

    @Override
    public PersonGroup save(PersonGroup entity) {
        openSession();
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            closeSession();
        }

        return entity;
    }

    @Override
    public Set<PersonGroup> findAll() {
        openSession();
        final Query<PersonGroup> all = session.createQuery("SELECT pg FROM PersonGroup pg LEFT JOIN pg.personSet", PersonGroup.class);
        Set<PersonGroup> resultSet = all.getResultStream().collect(Collectors.toSet());
        closeSession();

        return resultSet;
    }

}
