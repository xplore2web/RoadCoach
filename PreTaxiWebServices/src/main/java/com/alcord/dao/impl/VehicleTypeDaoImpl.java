package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.VehicleTypeDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleType;

/**
 *
 * @author Arbin Taj
 */
@Repository
@Transactional
public class VehicleTypeDaoImpl extends BaseDaoImpl implements VehicleTypeDao {

	/**
	 * {@inheritDoc}
	 */
	public VehicleType getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(VehicleType.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (VehicleType) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(VehicleType vehicleType) throws ProcessFailed {
		try {
			return (Integer) getCurrentSession().save(vehicleType);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(VehicleType vehicleType) throws ProcessFailed {
		try {
			getCurrentSession().update(vehicleType);
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
	public List<VehicleType> getAllVehicleType() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(VehicleType.class);
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
