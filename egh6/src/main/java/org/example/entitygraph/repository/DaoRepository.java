package org.example.entitygraph.repository;

import java.util.Set;

public interface DaoRepository<T> {

    T save(T entity);
    Set<T> findAll();
}
