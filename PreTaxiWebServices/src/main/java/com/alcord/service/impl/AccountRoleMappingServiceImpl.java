package com.alcord.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.AccountCityStateMappingDao;
import com.alcord.dao.AccountRoleMappingDao;
import com.alcord.dao.RoleDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.AccountRoleMapping;
import com.alcord.model.Role;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.service.AccountRoleMappingService;
import com.alcord.utility.Authorities;
import com.alcord.utility.IConstants;

@Service
@Transactional
public class AccountRoleMappingServiceImpl implements AccountRoleMappingService {

	@Autowired
	private AccountRoleMappingDao accountRoleMappingDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AccountCityStateMappingDao accountCityStateMappingDao;

	/**
	 * {@inheritDoc}
	 */
	public UUID save(AccountRoleMapping accountRoleMapping) throws ProcessFailed {
		return accountRoleMappingDao.save(accountRoleMapping);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(AccountRoleMapping accountRoleMapping) throws ProcessFailed {
		accountRoleMappingDao.update(accountRoleMapping);

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(AccountRoleMapping accountRoleMapping) throws ProcessFailed {
		accountRoleMappingDao.delete(accountRoleMapping);

	}

	/**
	 * {@inheritDoc}
	 */
	public AccountRoleMapping getByAccountId(UUID accountId) throws ProcessFailed {
		return accountRoleMappingDao.getByAccountId(accountId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountDetail> getByRole(String team) throws ProcessFailed {
		List<Integer> roleIds = new ArrayList<>();

		if (team.toUpperCase().contains(IConstants.kVERIFICATION)) {
			Role role = roleDao.getByRoleName(Authorities.ROLE_VERIFICATION_SUPERVISER.name());
			if (role != null)
				roleIds.add(role.getId());
			role = roleDao.getByRoleName(Authorities.ROLE_VERIFICATION_ANALYST.name());
			if (role != null)
				roleIds.add(role.getId());

		} else if (team.toUpperCase().contains(IConstants.kACCOUNTS)) {
			Role role = roleDao.getByRoleName(Authorities.ROLE_ACCOUNTS_SUPERVISER.name());
			if (role != null)
				roleIds.add(role.getId());
			role = roleDao.getByRoleName(Authorities.ROLE_ACCOUNTS_ANALYST.name());
			if (role != null)
				roleIds.add(role.getId());
		}

		List<AccountRoleMapping> accountRoleList = accountRoleMappingDao.getByRole(roleIds);
		if (accountRoleList.size()>0){
			List<AccountDetail> accountDetailList = parseToAccountDetailsList(accountRoleList);
			return accountDetailList;
		}else {
			return null;
		}
		
	}
	
	private List<AccountDetail> parseToAccountDetailsList(List<AccountRoleMapping> accountRoleList){
		List<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
		
		for(AccountRoleMapping accountRoleMapping: accountRoleList){
			AccountDetail accountDetails = new AccountDetail();
			AccountCityStateMapping accountCityStateMapping = accountCityStateMappingDao.getByAccountId(accountRoleMapping.getFkeyAccountId().getId());
			accountDetails.setFkeyCityId(accountCityStateMapping.getFkeyCityId().getId());
			accountDetails.setAccountName(accountRoleMapping.getFkeyAccountId().getAccountName());
			accountDetails.setAccountRole(accountRoleMapping.getFkeyRoleId().getRoleName());
			accountDetails.setEmailAddress(accountRoleMapping.getFkeyAccountId().getEmailAddress());
			accountDetails.setPhone(accountRoleMapping.getFkeyAccountId().getPhone());
			String user[] = accountRoleMapping.getFkeyAccountId().getAccountName().split(" ");
			accountDetails.setFirstName(user[0]);
			accountDetails.setLastName(user[1]);
			accountDetailList.add(accountDetails);
		}
		return accountDetailList;
	}
}
