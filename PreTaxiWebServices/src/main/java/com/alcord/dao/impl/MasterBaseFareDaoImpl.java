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

import com.alcord.dao.MasterBaseFareDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverTax;
import com.alcord.model.MasterBaseFare;

/**
 * @author saurabh
 *
 */
@Repository
@Transactional
public class MasterBaseFareDaoImpl extends BaseDaoImpl implements MasterBaseFareDao{

	/**
	 * {@inheritDoc}
	 */
	public MasterBaseFare getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(MasterBaseFare.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (MasterBaseFare) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(MasterBaseFare masterBaseFare) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(masterBaseFare);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(MasterBaseFare masterBaseFare) throws ProcessFailed {
		try {
			getCurrentSession().update(masterBaseFare);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(MasterBaseFare masterBaseFare) throws ProcessFailed {
		try {
			getCurrentSession().delete(masterBaseFare);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
