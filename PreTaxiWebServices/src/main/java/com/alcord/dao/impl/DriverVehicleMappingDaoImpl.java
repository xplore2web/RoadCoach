package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverVehicleMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverVehicleMapping;

/**
 *
 * @author Razak
 */
@Repository
@Transactional

public class DriverVehicleMappingDaoImpl extends BaseDaoImpl implements DriverVehicleMappingDao {



	/**
	 * {@inheritDoc}
	 */
	public DriverVehicleMapping getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN).setFetchMode("fkeyVehicleId", FetchMode.JOIN)
					.add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverVehicleMapping) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(DriverVehicleMapping driverVehicleMapping) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverVehicleMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverVehicleMapping driverVehicleMapping) throws ProcessFailed {
		try {
			getCurrentSession().update(driverVehicleMapping);
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
	public List<DriverVehicleMapping> getAllDriverVehicleByDriverIdAndVehicleStatus(UUID id, Boolean approved)
			throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN).setFetchMode("fkeyVehicleId", FetchMode.JOIN)
					 .createAlias("fkeyVehicleId", "vId")
					.add(Restrictions.eq("fkeyDriverId.id", id)).add(Restrictions.eq("vId.approved", approved));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DriverVehicleMapping> getAllDriverVehicleByDriverId(UUID id)throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyDriverId.id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}else{
				return criteria.list();
			}

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DriverVehicleMapping getCurrentVehicleByDriverId(UUID driverId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN).setFetchMode("fkeyVehicleId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyDriverId.id", driverId))
					.add(Restrictions.eq("isCurrentVehicle", true));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverVehicleMapping) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DriverVehicleMapping> getAllVehiclesByDriverId(UUID driverId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN)
					.setFetchMode("fkeyVehicleId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyDriverId.id", driverId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DriverVehicleMapping> getAllDriverVehicle() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN).setFetchMode("fkeyVehicleId", FetchMode.JOIN);
			if (criteria.list().isEmpty()) {
				return null;
			}
			return criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public DriverVehicleMapping getDriverVehicleMappingByVehicleId(UUID vehicleId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverVehicleMapping.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN).setFetchMode("fkeyVehicleId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyVehicleId.id", vehicleId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverVehicleMapping) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
