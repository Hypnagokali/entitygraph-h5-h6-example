package org.example.entitygraph;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entitygraph.model.PersonGroup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityGraphTest {

    @BeforeAll
    static void setUp() throws Exception {
        // generate test data
        HibernateTestDataService testDataService = new HibernateTestDataService();
    }

    @Test
    public void findGroupByPersonNameTessa_ExpectTessaAndSandraInGroup() throws Exception {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();

        final EntityGraph<PersonGroup> entityGraph = entityManager.createEntityGraph(PersonGroup.class);
        entityGraph.addAttributeNodes("personSet");

        final TypedQuery<PersonGroup> query = entityManager.createQuery(
                "SELECT pg FROM PersonGroup pg " +
                        "LEFT JOIN pg.personSet p " +
                        "WHERE p.name = ?1",
                PersonGroup.class
        );

        query.setParameter(1, "Tessa");

        query.setHint("jakarta.persistence.fetchgraph", entityGraph);

        final List<PersonGroup> groups = query.getResultList();

        entityManager.close();

        assertThat(groups).hasSize(1);
        assertThat(groups.stream().findAny().get().getPersonSet()).hasSize(2);

    }
}
