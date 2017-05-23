package com.ajit.dao.impl;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ajit
 */
@Repository
@Transactional
class BaseDaoImpl {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    public final static Logger logger = Logger.getLogger(BaseDaoImpl.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    public Session getCurrentSession() {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        return hem.getSession();
    }
}
