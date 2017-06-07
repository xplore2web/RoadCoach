package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.TripDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AvailableDriverDetails;
import com.alcord.model.Trip;

/**
 *
 * @author Razak
 */
@Repository
@Transactional

public class TripDaoImpl extends BaseDaoImpl implements TripDao {

	/**
	 * {@inheritDoc}
	 */
	public Trip getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Trip.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Trip) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public Trip getByTripId(UUID tripId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Trip.class).add(Restrictions.eq("id", tripId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Trip) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}
	/**
	 * {@inheritDoc}
	 */
	public UUID save(Trip trip) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(trip);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Trip trip) throws ProcessFailed {
		try {
			getCurrentSession().delete(trip);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Trip trip) throws ProcessFailed {
		try {
			getCurrentSession().update(trip);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public List<AvailableDriverDetails> getAllDriversInPassengerArea(Double passengerLatitude, Double passengerLongitude, Integer radius)
			throws ProcessFailed {
        try {    
            Query query = getCurrentSession().getNamedQuery("getDriverInPassengerArea")
                  .setParameter("latitude", passengerLatitude)
                  .setParameter("longitude", passengerLongitude)
                  .setParameter("radius", radius);
            	@SuppressWarnings("unchecked")
				List<AvailableDriverDetails> result = (List<AvailableDriverDetails>)query.list();
            	for(Integer i=0;i<result.size();i++) {
            	    System.out.println(((AvailableDriverDetails) result.get(i)).toString());
            	}
        	return result;

        } catch (ProcessFailed ex) {
        	loggingUtility.error(ex);
            throw new ProcessFailed("Database error while retrieving record");
        }
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AvailableDriverDetails> getAllAvailableDriverDetails() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(AvailableDriverDetails.class);
			if (criteria.list().isEmpty()) {
				return null;
			}
			System.out.println(criteria.list().size());
			return  criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Trip> getAllTrip() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Trip.class);
			if (criteria.list().isEmpty()) {
				return null;
			}
			return criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
}
