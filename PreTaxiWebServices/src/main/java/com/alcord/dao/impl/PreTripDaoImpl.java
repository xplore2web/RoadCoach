package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.PreTripDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.PreTrip;
@Repository
@Transactional
public class PreTripDaoImpl extends BaseDaoImpl implements PreTripDao {
	

	/**
	 * {@inheritDoc}
	 */
	public PreTrip getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession()
								.createCriteria(PreTrip.class)
								.setFetchMode("fkeyTripId", FetchMode.JOIN)
								.setFetchMode("fkeyDriverId", FetchMode.JOIN)
								.setFetchMode("fkeyPassengerId", FetchMode.JOIN)
					            .add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (PreTrip) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(PreTrip preTrip) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(preTrip);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(PreTrip preTrip) throws ProcessFailed {
		try {
			getCurrentSession().update(preTrip);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PreTrip preTrip) throws ProcessFailed {
		try {
			getCurrentSession().delete(preTrip);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PreTrip> getAllPreTripByTripId(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession()
								.createCriteria(PreTrip.class)
								.setFetchMode("fkeyTripId", FetchMode.JOIN)
								.setFetchMode("fkeyDriverId", FetchMode.JOIN)
								.setFetchMode("fkeyPassengerId", FetchMode.JOIN)
					            .add(Restrictions.eq("fkeyTripId.id", id));
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
