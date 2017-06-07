package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverSlabDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverSlab;
/**
*
* @author ajit
*/
@Repository
@Transactional
public class DriverSlabDaoImpl extends BaseDaoImpl implements DriverSlabDao{
	


	/**
	 * {@inheritDoc}
	 */
	public DriverSlab getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession()
								.createCriteria(DriverSlab.class)
								.setFetchMode("fkeyDriverId", FetchMode.JOIN)
					            .add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverSlab) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(DriverSlab driverSlab) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverSlab);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverSlab driverSlab) throws ProcessFailed {
		try {
			getCurrentSession().update(driverSlab);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(DriverSlab driverSlab) throws ProcessFailed {
		try {
			getCurrentSession().delete(driverSlab);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public DriverSlab getDriverCurrentSlabBydriverId(UUID driverId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession()
								.createCriteria(DriverSlab.class)
								.setFetchMode("fkeyDriverId", FetchMode.JOIN)
					            .add(Restrictions.eq("fkeyDriverId.id", driverId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverSlab) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
