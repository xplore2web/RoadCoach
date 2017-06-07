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

import com.alcord.dao.CompanyBillingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyBilling;
import com.alcord.model.DriverTax;

/**
 * @author saurabh
 *
 */
@Repository
@Transactional
public class CompanyBillingDaoImpl extends BaseDaoImpl implements CompanyBillingDao{

	@Override
	public CompanyBilling getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(CompanyBilling.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (CompanyBilling) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(CompanyBilling companyBilling) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(companyBilling);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(CompanyBilling companyBilling) throws ProcessFailed {
		try {
			getCurrentSession().update(companyBilling);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(CompanyBilling companyBilling) throws ProcessFailed {
		try {
			getCurrentSession().delete(companyBilling);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
