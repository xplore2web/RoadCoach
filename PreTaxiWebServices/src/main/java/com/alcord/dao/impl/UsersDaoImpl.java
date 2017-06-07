package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.AccountDao;
import com.alcord.dao.UsersDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Users;

/**
 *
 * @author akmal
 */
@Repository
@Transactional
public class UsersDaoImpl extends BaseDaoImpl implements UsersDao{

	@Override
	public Users findByUserName(String user) throws ProcessFailed {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID save(Users user) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(user);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

}
