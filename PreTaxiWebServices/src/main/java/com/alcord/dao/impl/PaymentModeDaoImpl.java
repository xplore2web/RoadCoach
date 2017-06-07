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

import com.alcord.dao.PaymentModeDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverTax;
import com.alcord.model.PaymentMode;

/**
 * @author saurabh
 *
 */
@Repository
@Transactional
public class PaymentModeDaoImpl extends BaseDaoImpl implements PaymentModeDao {

	/**
	 * {@inheritDoc}
	 */
	public PaymentMode getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(PaymentMode.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (PaymentMode) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public UUID save(PaymentMode paymentMode) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(paymentMode);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(PaymentMode paymentMode) throws ProcessFailed {
		try {
			getCurrentSession().update(paymentMode);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PaymentMode paymentMode) throws ProcessFailed {
		try {
			getCurrentSession().delete(paymentMode);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
