package org.example.entitygraph.repository;

import org.example.entitygraph.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDaoRepository<T> implements DaoRepository<T> {

    protected Session session;
    protected Transaction transaction;
    
    protected void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    protected void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    protected void rollback() {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
