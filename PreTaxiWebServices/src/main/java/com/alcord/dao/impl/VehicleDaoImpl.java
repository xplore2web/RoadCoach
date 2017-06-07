package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.VehicleDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Vehicle;

/**
 *
 * @author Arbin Taj
 */
@Repository
@Transactional
public class VehicleDaoImpl extends BaseDaoImpl implements VehicleDao {

	/**
	 * {@inheritDoc}
	 */
	public Vehicle getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Vehicle.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Vehicle) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Vehicle vehicle) throws ProcessFailed {

		try {
			return (UUID) getCurrentSession().save(vehicle);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Vehicle vehicle) throws ProcessFailed {
		try {
			getCurrentSession().update(vehicle);
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
			getCurrentSession().delete(id);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Vehicle> getAllVehicle() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Vehicle.class);
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
