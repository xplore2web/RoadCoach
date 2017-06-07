package com.alcord.dao.impl;

import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.RoleDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Role;

/**
 *
 * @author Razak
 */
@Repository
@Transactional

public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	/**
	 * {@inheritDoc}
	 */
	public Role getById(UUID id) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("id", id));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Role) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}

	}
	
	/**
	 * {@inheritDoc}
	 */
	public Role getByRoleName(String roleName) throws ProcessFailed {
		try {
			Criteria criteria = getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("roleName", roleName));
			if (criteria.list().isEmpty()) {
				return null;
			}
			return (Role) criteria.list().get(0);

		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isRoleExist(Role role) throws ProcessFailed {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Role role) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(role);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
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