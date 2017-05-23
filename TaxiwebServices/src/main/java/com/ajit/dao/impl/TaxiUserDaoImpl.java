package com.ajit.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ajit.dao.TaxiUserDao;
import com.ajit.exception.ProcessFailed;
import com.ajit.model.TaxiUser;


/**
 * <code> {@link TaxiUserDaoImpl} </code> is implementation of
 * {@link TaxiUserDao} and perform the database related operation for managing
 * {@link ContactForm}
 *
 * @author Ajit
 */
@Repository
@Transactional
public class TaxiUserDaoImpl extends BaseDaoImpl implements TaxiUserDao {

	/**
	 * {@inheritDoc}
	 */
	public Integer save(TaxiUser taxiUser) throws ProcessFailed {
		
		return (Integer) getCurrentSession().save(taxiUser);
	}

}
