package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.BillingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Billing;

/**
 *
 * @author Hidayath
 */
@Repository
@Transactional
public class BillingDaoImpl extends BaseDaoImpl implements BillingDao {
	
	/**
	 * {@inheritDoc}
	 */
	public Billing getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Billing.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Billing) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Billing billing) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(billing);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Billing billing) throws ProcessFailed {
		try {
			getCurrentSession().update(billing);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Billing billing) throws ProcessFailed {
		try {
			getCurrentSession().delete(billing);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

}
