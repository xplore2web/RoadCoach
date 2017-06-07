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

import com.alcord.dao.CompanyDriverShareDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyDriverShare;
import com.alcord.model.DriverTax;

/**
 * @author Saurabh
 *
 */
@Repository
@Transactional
public class CompanyDriverShareDaoImpl extends BaseDaoImpl implements CompanyDriverShareDao{

	/**
	 * {@inheritDoc}
	 */
	public CompanyDriverShare getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(CompanyDriverShare.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (CompanyDriverShare) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public UUID save(CompanyDriverShare companyDriverShare) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(companyDriverShare);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(CompanyDriverShare companyDriverShare) throws ProcessFailed {
		try {
			getCurrentSession().update(companyDriverShare);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(CompanyDriverShare companyDriverShare) throws ProcessFailed {
		try {
			getCurrentSession().delete(companyDriverShare);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
