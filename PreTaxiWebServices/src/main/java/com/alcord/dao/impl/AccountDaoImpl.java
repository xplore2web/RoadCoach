/**
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.AccountDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;

/**
 *
 * @author ajit
 */
@Repository
@Transactional
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {


	/**
	 * {@inheritDoc}
	 */
	public Account findByAccountName(String accountName) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Account.class)
					.add(Restrictions.eq("accountName", accountName));
			List<Account> AccountList = criteria.list();
			if (AccountList.isEmpty()) {
				return null;
			}
			return AccountList.get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Account getAccountById(UUID id) throws ProcessFailed {
		try{
			Criteria criteria = getCurrentSession().createCriteria(Account.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Account) criteria.list().get(0);
		
	} catch (ProcessFailed ex) {
		loggingUtility.error(ex);
		throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
	}
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean checkUniqueAccount(Account account)  throws ProcessFailed{
		Boolean isAccountUnique = true;
		Criteria criteria = getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("accountName", account.getAccountName()));
		if (criteria.list().isEmpty()) {
			isAccountUnique = true;
		} else {
			isAccountUnique = false;
		}
		return isAccountUnique;
	}


	/**
	 * {@inheritDoc}
	 */
	public UUID save(Account account) {
		try {
			return (UUID) getCurrentSession().save(account);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Account account) {
		try {
			getCurrentSession().update(account);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
	}


	/**
	 * {@inheritDoc}
	 */
	public void delete(Account account) throws ProcessFailed {
		try {
			getCurrentSession().delete(account);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Account> getAll() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Account.class);
			List<Account> list = criteria.list();
			if (list.isEmpty()) {
				return null;
			}
			return list;

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Account getAccountByActivationKey(String activationKey) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Account.class)
					.add(Restrictions.eq("activationKey", activationKey));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Account) criteria.list().get(0);
		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public Account getByEmailId(String emailId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Account.class)
					.add(Restrictions.eq("emailAddress", emailId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Account) criteria.list().get(0);
		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Account getByPhoneNumber(String phoneNumber) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Account.class)
					.add(Restrictions.eq("phone", phoneNumber));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Account) criteria.list().get(0);
		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
