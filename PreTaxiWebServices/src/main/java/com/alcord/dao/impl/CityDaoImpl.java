package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.CityDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.City;

/**
 *
 * @author Hidayath
 */
@Repository
@Transactional
public class CityDaoImpl extends BaseDaoImpl implements CityDao {

	@Override
	public City getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(City.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (City) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}
	
	@Override
	public List<City> getAllCities() throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(City.class);
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
	public UUID save(City city) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(city);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(City city) throws ProcessFailed {
		try {
			getCurrentSession().update(city);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(City city) throws ProcessFailed {
		try {
			getCurrentSession().delete(city);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
	}

}