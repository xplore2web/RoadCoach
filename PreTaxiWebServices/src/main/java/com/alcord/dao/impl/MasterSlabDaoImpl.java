package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.MasterSlabDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.MasterSlab;
/**
*
* @author ajit
*/
@Repository
@Transactional
public class MasterSlabDaoImpl extends BaseDaoImpl implements MasterSlabDao {


	/**
	 * {@inheritDoc}
	 */
	public MasterSlab getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(MasterSlab.class)
					.add(Restrictions.eq("id", id));
			List<MasterSlab> masterSlabList = criteria.list();
			if (masterSlabList.isEmpty()) {
				return null;
			}
			return masterSlabList.get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(MasterSlab masterSlab) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(masterSlab);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(MasterSlab masterSlab) throws ProcessFailed {
		try {
			getCurrentSession().update(masterSlab);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(MasterSlab masterSlab) throws ProcessFailed {
		try {
			getCurrentSession().delete(masterSlab);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

}
