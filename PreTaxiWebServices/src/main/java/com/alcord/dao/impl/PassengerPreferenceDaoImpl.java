package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.PassengerPreferenceDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.PassengerPreference;

/**
 *
 * @author ajit
 */
@Repository
@Transactional
public class PassengerPreferenceDaoImpl extends BaseDaoImpl implements PassengerPreferenceDao {

	/**
	 * {@inheritDoc}
	 */
	public PassengerPreference getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(PassengerPreference.class)
					.add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (PassengerPreference) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(PassengerPreference passengerPreference) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(passengerPreference);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(PassengerPreference passengerPreference) throws ProcessFailed {
		try {
			getCurrentSession().update(passengerPreference);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PassengerPreference passengerPreference) throws ProcessFailed {
		try {
			getCurrentSession().delete(passengerPreference);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public List<PassengerPreference> getPassengerPreferenceByPassengerId(UUID passengerId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(PassengerPreference.class)
					.setFetchMode("fkeyPassengerId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyPassengerId.id", passengerId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return  criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
