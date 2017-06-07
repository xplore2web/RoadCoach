package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.PassengerEmergencyContactDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.PassengerEmergencyContact;

/**
 *
 * @author Razak
 */
@Repository
@Transactional
public class PassengerEmergencyContactDaoImpl extends BaseDaoImpl implements PassengerEmergencyContactDao {


	/**
	 * {@inheritDoc}
	 */
	public PassengerEmergencyContact getById(UUID Id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(PassengerEmergencyContact.class)
					.setFetchMode("fkeyPassengerId", FetchMode.JOIN)
					.add(Restrictions.eq("id", Id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (PassengerEmergencyContact) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(passengerEmergencyContact);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void update(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed {
		try {
			getCurrentSession().update(passengerEmergencyContact);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed {
		try {
			getCurrentSession().delete(passengerEmergencyContact);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PassengerEmergencyContact> getAllPassengerEmergencyContactByPassengerId(UUID passengerId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(PassengerEmergencyContact.class)
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
