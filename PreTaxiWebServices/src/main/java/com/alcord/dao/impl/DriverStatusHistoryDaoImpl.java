package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverStatusHistoryDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverStatusHistory;

/**
 *
 * @author Hidayath
 */
@Repository
@Transactional
public class DriverStatusHistoryDaoImpl extends BaseDaoImpl implements DriverStatusHistoryDao {

	@Override
	public DriverStatusHistory getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverStatusHistory.class)
					.add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverStatusHistory) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID save(DriverStatusHistory driverStatusHistory) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverStatusHistory);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverStatusHistory driverStatusHistory) throws ProcessFailed {
		try {
			getCurrentSession().update(driverStatusHistory);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(DriverStatusHistory driverStatusHistory) throws ProcessFailed {
		try {
			getCurrentSession().delete(driverStatusHistory);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

}