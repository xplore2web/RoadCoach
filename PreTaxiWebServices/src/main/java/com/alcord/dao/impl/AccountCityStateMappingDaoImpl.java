package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.AccountCityStateMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;

/**
 *
 * @author sandeep
 */
@Repository
@Transactional
public class AccountCityStateMappingDaoImpl extends BaseDaoImpl implements AccountCityStateMappingDao {


	/**
	 * {@inheritDoc}
	 */
	public AccountCityStateMapping getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(AccountCityStateMapping.class)
					.add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (AccountCityStateMapping) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<AccountCityStateMapping> getByCityId(UUID cityId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(AccountCityStateMapping.class)
					.add(Restrictions.eq("fkeyCityId.id", cityId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public AccountCityStateMapping getByAccountId(UUID accountId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(AccountCityStateMapping.class)
					.setFetchMode("fkeyAccountId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyAccountId.id", accountId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (AccountCityStateMapping)criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public UUID save(AccountCityStateMapping accountCityStateMapping) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(accountCityStateMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(AccountCityStateMapping accountCityStateMapping) throws ProcessFailed {
		try {
			getCurrentSession().update(accountCityStateMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(AccountCityStateMapping accountCityStateMapping) throws ProcessFailed {
		try {
			getCurrentSession().delete(accountCityStateMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

}
