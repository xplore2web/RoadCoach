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

import com.alcord.dao.DriverAccountStatusDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverAccountStatus;
import com.alcord.model.DriverTax;

/**
 * @author saurabh
 *
 */

@Repository
@Transactional
public class DriverAccountStatusDaoImpl extends BaseDaoImpl implements DriverAccountStatusDao {

	/**
	 * {@inheritDoc}
	 */
	public DriverAccountStatus getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverAccountStatus.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverAccountStatus) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(DriverAccountStatus driverAccountStatus) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverAccountStatus);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverAccountStatus driverAccountStatus) throws ProcessFailed {
		try {
			getCurrentSession().update(driverAccountStatus);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(DriverAccountStatus driverAccountStatus) throws ProcessFailed {
		try {
			getCurrentSession().delete(driverAccountStatus);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
