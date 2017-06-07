package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.OwnerVehicleMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.OwnerVehicleMapping;

/**
 *
 * @author Razak
 */
@Repository
@Transactional
	
public class OwnerVehicleMappingDaoImpl extends BaseDaoImpl implements OwnerVehicleMappingDao {


	/**
	 * {@inheritDoc}
	 */
	public OwnerVehicleMapping getById(UUID id) throws ProcessFailed {
		try{
			Criteria criteria = getCurrentSession().createCriteria(OwnerVehicleMapping.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (OwnerVehicleMapping) criteria.list().get(0);
		
	} catch (ProcessFailed ex) {
		loggingUtility.error(ex);
		throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
	}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(OwnerVehicleMapping ownerVehicleMapping) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(ownerVehicleMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(OwnerVehicleMapping ownerVehicleMapping) throws ProcessFailed {
		try {
			getCurrentSession().update(ownerVehicleMapping);
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

}