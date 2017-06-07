package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.RatingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Rating;


/**
 *
 * @author Razak
 */
@Repository
@Transactional
	
public class RatingDaoImpl extends BaseDaoImpl implements RatingDao {


	/**
	 * {@inheritDoc}
	 */
	public Rating getById(UUID id) throws ProcessFailed {
		try{
			Criteria criteria = getCurrentSession().createCriteria(Rating.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Rating) criteria.list().get(0);
		
	} catch (ProcessFailed ex) {
		loggingUtility.error(ex);
		throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
	}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Rating rating) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(rating);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Rating rating) throws ProcessFailed {
		try {
			getCurrentSession().delete(rating);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Rating> getAllRatingByDriverId(UUID driverId) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Rating.class)
					.setFetchMode("fkeyDriverId", FetchMode.JOIN)
					.add(Restrictions.eq("fkeyDriverId.id", driverId));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return  criteria.list();

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
}
