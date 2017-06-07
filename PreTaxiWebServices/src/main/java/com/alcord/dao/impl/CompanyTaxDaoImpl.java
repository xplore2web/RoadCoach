/**
 * 
 */
package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.alcord.dao.CompanyTaxDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyTax;
import com.alcord.model.DriverTax;

/**
 * @author saurabh
 *
 */
public class CompanyTaxDaoImpl extends BaseDaoImpl implements CompanyTaxDao {

	/**
	 * {@inheritDoc}
	 */
	public CompanyTax getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(CompanyTax.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (CompanyTax) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(CompanyTax companyTax) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(companyTax);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(CompanyTax companyTax) throws ProcessFailed {
		try {
			getCurrentSession().update(companyTax);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

		
	}

}
