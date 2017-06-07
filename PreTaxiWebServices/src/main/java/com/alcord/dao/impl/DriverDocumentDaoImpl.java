package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverDocumentDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverDocument;

/**
*
* @author Hidayath 
*/
@Repository
@Transactional
public class DriverDocumentDaoImpl extends BaseDaoImpl  implements DriverDocumentDao{
	
	@Override
	public DriverDocument getById(UUID id) throws ProcessFailed {
		try{
			Criteria criteria = getCurrentSession().createCriteria(DriverDocument.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (DriverDocument) criteria.list().get(0);
		
	} 
		catch (ProcessFailed ex) 
		{
			loggingUtility.error(ex);
		throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
	}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(DriverDocument driverdocument) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driverdocument);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(DriverDocument driverdocument) throws ProcessFailed {
		try {
			getCurrentSession().update(driverdocument);
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