package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.PassengerDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Passenger;

/**
 *
 * @author Razak
 */
@Repository
@Transactional
public class PassengerDaoImpl extends BaseDaoImpl implements PassengerDao {
	/**
	 * {@inheritDoc}
	 */
	public Passenger getById(UUID Id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Passenger.class).add(Restrictions.eq("id", Id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Passenger) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Passenger passenger) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(passenger);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Passenger passenger) throws ProcessFailed {
		try {
			getCurrentSession().update(passenger);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		try {
			getCurrentSession().delete(getById(id));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Passenger getByAccountId(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Passenger.class)
					.setFetchMode("fkeyAccountId", FetchMode.JOIN).setFetchMode("fkeyAddressId", FetchMode.JOIN)
					.setFetchMode("fkeyCityId", FetchMode.JOIN).add(Restrictions.eq("fkeyAccountId.id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Passenger) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}