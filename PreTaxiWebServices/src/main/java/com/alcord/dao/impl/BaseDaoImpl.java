/**
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import com.alcord.utility.LoggingUtility;

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

	@Autowired
	LoggingUtility loggingUtility;
	@Autowired
	 MessageSource messageSource;

    @PersistenceContext
    private EntityManager entityManager;

    public Session getCurrentSession() {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        return hem.getSession();
    }
}
