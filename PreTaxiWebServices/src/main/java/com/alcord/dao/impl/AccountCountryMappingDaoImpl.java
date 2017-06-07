package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.AccountCountryMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCountryMapping;

/**
 *
 * @author sandeep
 */
@Repository
@Transactional
public class AccountCountryMappingDaoImpl extends BaseDaoImpl implements AccountCountryMappingDao {


	
	/**
	 * {@inheritDoc}
	 */
	public AccountCountryMapping getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(AccountCountryMapping.class)
					.add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (AccountCountryMapping) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(AccountCountryMapping accountCountryMapping) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(accountCountryMapping);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(AccountCountryMapping accountCountryMapping) throws ProcessFailed {
		try {
			getCurrentSession().update(accountCountryMapping);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(AccountCountryMapping accountCountryMapping) throws ProcessFailed {
		try {
			getCurrentSession().delete(accountCountryMapping);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}

	}

}
