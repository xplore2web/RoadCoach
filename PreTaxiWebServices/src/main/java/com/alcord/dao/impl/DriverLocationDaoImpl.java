package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverLocationDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverLocation;

/**
*
* @author ajit
*/
@Repository
@Transactional
public class DriverLocationDaoImpl extends BaseDaoImpl implements DriverLocationDao{
	

	/**
	 * {@inheritDoc}
	 */
	public DriverLocation getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(DriverLocation.class)
					.add(Restrictions.eq("id", id));
			List<DriverLocation> driverLocationList = criteria.list();
			if (driverLocationList.isEmpty()) {
				return null;
			}
			return driverLocationList.get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(DriverLocation driverLocation) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverLocation);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverLocation driverLocation) throws ProcessFailed {
		try {
			getCurrentSession().update(driverLocation);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(DriverLocation driverLocation) throws ProcessFailed {
		try {
			getCurrentSession().delete(driverLocation);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public DriverLocation getDriverCurrentLoactionBydriverId(UUID driverId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession()
								.createCriteria(DriverLocation.class)
								.setFetchMode("fkeyDriverId", FetchMode.JOIN)
					            .add(Restrictions.eq("fkeyDriverId.id", driverId))
					            .addOrder(Order.desc("createdAt"));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverLocation) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
