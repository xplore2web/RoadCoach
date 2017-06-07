package com.alcord.dao.impl;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alcord.dao.AccountRoleMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountRoleMapping;

/**
*
* @author ajit
*/
@Repository
@Transactional 
public class AccountRoleMappingDaoImpl extends BaseDaoImpl implements AccountRoleMappingDao {


	/**
	 * {@inheritDoc}
	 */
	public UUID save(AccountRoleMapping accountRoleMapping) throws ProcessFailed {
		try {
			return (UUID) getCurrentSession().save(accountRoleMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(AccountRoleMapping accountRoleMapping) throws ProcessFailed {
		try {
			getCurrentSession().update(accountRoleMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(AccountRoleMapping accountRoleMapping) throws ProcessFailed {
		try {
			getCurrentSession().delete(accountRoleMapping);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[] {}, Locale.US));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public AccountRoleMapping getByAccountId(UUID accountId) throws ProcessFailed {
		 try{
				Criteria criteria = getCurrentSession().createCriteria(AccountRoleMapping.class)
						.setFetchMode("fkeyAccountId", FetchMode.JOIN)
						.setFetchMode("fkeyRoleId", FetchMode.JOIN)
						.add(Restrictions.eq("fkeyAccountId.id", accountId));
				if (criteria.list().isEmpty()) {
					return null;
				}
				return (AccountRoleMapping) criteria.list().get(0);
			
		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}
	
	public List<AccountRoleMapping> getByRole(List<Integer> roleIds) throws ProcessFailed {
		 try{
				Criteria criteria = getCurrentSession().createCriteria(AccountRoleMapping.class)
						.setFetchMode("fkeyAccountId", FetchMode.JOIN)
						.setFetchMode("fkeyRoleId", FetchMode.JOIN);
				Criterion[] criterions = new Criterion[roleIds.size()];
				Integer i = 0;
				for (Integer roleId : roleIds) {
					criterions[i++] = (Restrictions.eq("fkeyRoleId.id", roleId));
				}
				criteria.add(Restrictions.or(criterions));
				
				if (criteria.list().isEmpty()) {
					return null;
				}
				return criteria.list();
			
		} catch (ProcessFailed ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

}
