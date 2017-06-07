package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.OwnerDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Owner;

/**
 *
 * @author Razak
 */
@Repository
@Transactional
	
public class OwnerDaoImpl extends BaseDaoImpl implements OwnerDao {


	/**
	 * {@inheritDoc}
	 */
	public Owner getById(UUID id) throws ProcessFailed {
		try{
			Criteria criteria = getCurrentSession().createCriteria(Owner.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Owner) criteria.list().get(0);
		
	} catch (ProcessFailed ex) {
		loggingUtility.error(ex);
		throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
	}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Owner owner) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(owner);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Owner owner) throws ProcessFailed {
		try {
			getCurrentSession().update(owner);
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