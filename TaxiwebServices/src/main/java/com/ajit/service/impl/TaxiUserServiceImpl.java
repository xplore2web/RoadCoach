package com.ajit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajit.dao.TaxiUserDao;
import com.ajit.exception.ProcessFailed;
import com.ajit.model.TaxiUser;
import com.ajit.service.TaxiUserService;

/**
 * <code> {@link TaxiUserServiceImpl} </code> is implementation of
 * {@link TaxiUserService} for communicating between Controller and DAO classes
 *
 * @author Ajit
 */
@Service
@Transactional
public class TaxiUserServiceImpl implements TaxiUserService{
	
	@Autowired
	private TaxiUserDao taxiUserDao;

	/**
	 *
	 * {@inheritDoc}
	 */
	public Integer save(TaxiUser taxiUser) throws ProcessFailed {
		return taxiUserDao.save(taxiUser);
	}

}
