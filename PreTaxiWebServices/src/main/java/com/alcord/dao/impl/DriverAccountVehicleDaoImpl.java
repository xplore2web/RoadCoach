package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverAccountVehicleDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.view.DriverAccountVehicle;

@Repository
@Transactional
public class DriverAccountVehicleDaoImpl extends BaseDaoImpl implements DriverAccountVehicleDao {

	/**
	 * {@inheritDoc}
	 */
	public DriverAccountVehicle getDriverAccountVehicleByDriverId(UUID driverId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverAccountVehicle.class)
					.add(Restrictions.eq("driverId", driverId));
			List<DriverAccountVehicle> DriverAccountVehicle = criteria.list();
			if (DriverAccountVehicle.isEmpty()) {
				return null;
			}
			return DriverAccountVehicle.get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
