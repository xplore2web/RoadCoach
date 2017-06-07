package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.AccountStateCountryMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountStateCountryMapping;
/**
*
* @author sandeep
*/
@Repository
@Transactional
public class AccountStateCountryMappingDaoImpl extends BaseDaoImpl implements AccountStateCountryMappingDao {


	

	/**
	 * {@inheritDoc}
	 */
	public AccountStateCountryMapping getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(AccountStateCountryMapping.class)
					.add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (AccountStateCountryMapping) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(AccountStateCountryMapping accountStateCountryMapping) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(accountStateCountryMapping);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(AccountStateCountryMapping accountStateCountryMapping) throws ProcessFailed {
		try {
			getCurrentSession().update(accountStateCountryMapping);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(AccountStateCountryMapping accountStateCountryMapping) throws ProcessFailed {
		try {
			getCurrentSession().delete(accountStateCountryMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}

	}

}
