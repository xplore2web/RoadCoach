/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.ajit.service.impl;

import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.ajit.config.CustomPasswordEncoder;
import com.ajit.dao.AccountDao;
import com.ajit.dao.TaxiUserDao;
//import com.ajit.dao.AccountRoleMappingDao;
import com.ajit.exception.ProcessFailed;
import com.ajit.model.Account;
import com.ajit.model.TaxiUser;
//import com.ajit.model.AccountRoleMapping;
//import com.ajit.model.Role;
import com.ajit.modelmappers.LoginDetails;
import com.ajit.modelmappers.UserLoginDetails;
import com.ajit.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {

	private final static Logger logger = Logger.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao accountsDao;
	@Autowired
	private TaxiUserDao taxiUserDao;
	@Autowired
	private MessageSource messageSource;

//
//	/**
//	 * {@inheritDoc}
//	 */
//	public Account findByAccountName(String accountName) throws ProcessFailed {
//		Account Account = accountsDao.findByAccountName(accountName);
//		if (Account == null) {
//			throw new ProcessFailed(messageSource.getMessage("account_not_found", new String[] {}, Locale.US));
//		}
//		return Account;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	public Account isAccountExist(String accountName) throws ProcessFailed {
//		Account Account = accountsDao.findByAccountName(accountName);
//		if (Account == null) {
//			return null;
//		}
//		return Account;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	public Boolean checkUniqueAccount(Account account) throws ProcessFailed {
//		return accountsDao.checkUniqueAccount(account);
//	}



	/**
	 * {@inheritDoc}
	 */
	public void save(UserLoginDetails userDetailsRequest) throws ProcessFailed {
		try {

			TaxiUser user = new TaxiUser();
			Account account = new Account();
			user.setEmaiId(userDetailsRequest.getEmailId());
			String encodedPassword = userDetailsRequest.getPassword();
			user.setPassword(encodedPassword);

//			account.setCreatedAt(new Date());
//			account.setActivationKey(GeneratePassword.generateRandomPassword());
//			account.setActivated(false);
//			Integer accountId = accountsDao.save(account);
			Integer accountId = taxiUserDao.save(user);

//			AccountRoleMapping accountRoleMapping = new AccountRoleMapping();
//			Account accountObject = new Account();
//			TaxiUser taxiUserObject = new TaxiUser();
//			taxiUserObject.setId(accountId);
//			accountRoleMapping.setFkeyAccountId(accountObject);
//			Role accountRole = new Role();
//			accountRole.setId(Constant.ROLE_ADMIN);
//			accountRoleMapping.setFkeyRoleId(accountRole);
//			accountRoleMapping.setCreatedAt(new Date());
//			accountRoleMappingDao.save(accountRoleMapping);

			
		} catch (Throwable throwable) {
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
	}

//	@Override
//	public void save(LoginDetails accountDetailsRequest) throws ProcessFailed {
//		// TODO Auto-generated method stub
//		
//	}


//	/**
//	 * {@inheritDoc}
//	 */
//	public void changePassword(Account account, String oldPassword, String newPassword) throws ProcessFailed {
//		if (customPasswordEncoder.matches(oldPassword, account.getAccountPassword())) {
//			account.setAccountPassword(customPasswordEncoder.encode(newPassword));
//			accountsDao.update(account);
//		} else if (!customPasswordEncoder.matches(oldPassword, account.getAccountPassword())) {
//			throw new ProcessFailed(messageSource.getMessage("invalid_current_password", new String[] {}, Locale.US));
//
//		}
//	}

}
