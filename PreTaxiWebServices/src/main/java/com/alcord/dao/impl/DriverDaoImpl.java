package com.alcord.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.DriverDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.Driver;

/**
 *
 * @author sandeep
 */
@Repository
@Transactional
public class DriverDaoImpl extends BaseDaoImpl implements DriverDao {

	/**
	 * {@inheritDoc}
	 */
	public Driver getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Driver.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Driver) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Driver driver) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(driver);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Driver driver) throws ProcessFailed {
		try {
			getCurrentSession().update(driver);
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
			getCurrentSession().delete(getById(id));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public Driver getByAccountId(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Driver.class)
					.setFetchMode("fkeyAccountId", FetchMode.JOIN).setFetchMode("fkeyAddressId", FetchMode.JOIN)
					.setFetchMode("fkeyCityId", FetchMode.JOIN).add(Restrictions.eq("fkeyAccountId.id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Driver) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Driver> getByBadgeNumber(String badgeNumber, UUID stateId) throws ProcessFailed {
		// TODO Auto-generated method stub
		try {
			Criteria criteria = getCurrentSession().createCriteria(Driver.class)
					.setFetchMode("fkeyAccountId", FetchMode.JOIN)
					.add(Restrictions.ilike("badgeNumber", "%" + badgeNumber + "%"))
					.add(Restrictions.eq("fkeyStateId.id", stateId));

			if (criteria.list().isEmpty()) {
				return null;
			}
			return criteria.list();

		} catch (Throwable throwable) {
			loggingUtility.error(throwable);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Driver> getByName(String name, UUID stateId) throws ProcessFailed {
		// TODO Auto-generated method stub

		String nameArray[] = name.split(" ");
		if (nameArray.length > 1) {
			try {

				Criteria criteria = getCurrentSession().createCriteria(Driver.class)
						.setFetchMode("fkeyAccountId", FetchMode.JOIN)
						.add(Restrictions.ilike("firstName", "%" + nameArray[0] + "%"))
						.add(Restrictions.ilike("lastName", "%" + nameArray[1] + "%"))
						.add(Restrictions.eq("fkeyStateId.id", stateId));

				if (criteria.list().isEmpty()) {
					return null;
				}
				return criteria.list();

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				throw new ProcessFailed(
						messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
			}

		} else {
			try {

				Criteria criteria = getCurrentSession().createCriteria(Driver.class)
						.setFetchMode("fkeyAccountId", FetchMode.JOIN)
						.add(Restrictions.ilike("firstName", "%" + name + "%"))
						.add(Restrictions.eq("fkeyStateId.id", stateId));
				if (criteria.list().isEmpty()) {
					return null;
				}
				return criteria.list();

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				throw new ProcessFailed(
						messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
			}
		}
	}
}
