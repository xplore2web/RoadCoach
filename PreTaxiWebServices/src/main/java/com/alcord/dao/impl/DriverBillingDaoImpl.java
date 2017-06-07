/**
 * 
 */
package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverBillingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyDriverShare;
import com.alcord.model.DriverBilling;
import com.alcord.model.DriverTax;

/**
 * @author saurabh
 *
 */
@Repository
@Transactional
public class DriverBillingDaoImpl extends BaseDaoImpl implements DriverBillingDao{

	/**
	 * {@inheritDoc}
	 */
	public DriverBilling getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverTax.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverBilling) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(DriverBilling driverBilling) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverBilling);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverBilling driverBilling) throws ProcessFailed {
		try {
			getCurrentSession().update(driverBilling);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(DriverBilling driverBilling) throws ProcessFailed {
		try {
			getCurrentSession().delete(driverBilling);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
