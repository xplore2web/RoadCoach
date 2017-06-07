/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.config.CustomPasswordEncoder;
import com.alcord.dao.AccountCityStateMappingDao;
import com.alcord.dao.AccountDao;
import com.alcord.dao.UsersDao;
import com.alcord.dao.AccountRoleMappingDao;
import com.alcord.dao.AddressDao;
import com.alcord.dao.DriverDao;
import com.alcord.dao.PassengerDao;
import com.alcord.enums.DriverLoginStatus;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.Users;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.AccountRoleMapping;
import com.alcord.model.Address;
import com.alcord.model.City;
import com.alcord.model.Driver;
import com.alcord.model.Passenger;
import com.alcord.model.Role;
import com.alcord.model.State;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.modelmappers.ChangePasswordDetail;
import com.alcord.modelmappers.UserDetail;
import com.alcord.service.AccountService;
import com.alcord.utility.Authorities;
import com.alcord.utility.Constant;
import com.alcord.utility.GeneratePassword;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountsDao;
	@Autowired
	private UsersDao UsersDao;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private CustomPasswordEncoder customPasswordEncoder;
	@Autowired
	private AccountRoleMappingDao accountRoleMappingDao;
	@Autowired
	private DriverDao driverDao;
	@Autowired
	private PassengerDao passengerDao;
	@Autowired
	private AccountCityStateMappingDao accountCityStateMappingDao;

	/**
	 * {@inheritDoc}
	 */
	public Account findByAccountName(String accountName) throws ProcessFailed {
		Account Account = accountsDao.findByAccountName(accountName);
		if (Account == null) {
			throw new ProcessFailed(messageSource.getMessage("account_not_found", new String[] {}, Locale.US));
		}
		return Account;
	}

	/**
	 * {@inheritDoc}
	 */
	public Users findByUserName(String userName) throws ProcessFailed {
		Users Users = UsersDao.findByUserName(userName);
		if (Users == null) {
			throw new ProcessFailed(messageSource.getMessage("account_not_found", new String[] {}, Locale.US));
		}
		return Users;
	}

	/**
	 * {@inheritDoc}
	 */
	public Account isAccountExist(String accountName) throws ProcessFailed {
		Account Account = accountsDao.findByAccountName(accountName);
		if (Account == null) {
			return null;
		}
		return Account;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean checkUniqueAccount(Account account) throws ProcessFailed {
		return accountsDao.checkUniqueAccount(account);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(UserDetail userDetail) throws ProcessFailed {
		try {
			Users user = new Users();
			user.setName(userDetail.getName());
			user.setEmailId(userDetail.getEmailId());
			String encodedPassword = customPasswordEncoder.encode(userDetail.getPassword());
			user.setPassword(encodedPassword);
			user.setCreatedAt(new Date());
			UsersDao.save(user);
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void save(AccountDetail accountDetail) throws ProcessFailed {
		Account accountEmail = accountsDao.getByEmailId(accountDetail.getEmailAddress());
		if (accountEmail != null) {
			throw new ProcessFailed(messageSource.getMessage("email_id_already_exit", new String[] {}, Locale.US));
		}
		Account accountPhone = accountsDao.getByPhoneNumber(accountDetail.getPhone());
		if (accountPhone != null) {
			throw new ProcessFailed(messageSource.getMessage("mobile_number_already_exit", new String[] {}, Locale.US));
		}
		try {
			Account account = new Account();
			account.setAccountName(accountDetail.getAccountName());
			String encodedPassword = customPasswordEncoder.encode(accountDetail.getAccountPassword());
			account.setAccountPassword(encodedPassword);
			account.setCreatedAt(new Date());
			account.setEmailAddress(accountDetail.getEmailAddress());
			account.setPhone(accountDetail.getPhone());
			account.setActivationKey(GeneratePassword.generateRandomPassword());
			account.setActivated(true);
			UUID accountId = accountsDao.save(account);
			AccountRoleMapping accountRoleMapping = new AccountRoleMapping();
			Account accountObject = new Account();
			accountObject.setId(accountId);
			accountRoleMapping.setFkeyAccountId(accountObject);
			Role accountRole = new Role();
			if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_DRIVER.name())) {

				accountRole.setId(Constant.ROLE_DRIVER);

				Driver driver = new Driver();
				driver.setFirstName(accountDetail.getFirstName());
				driver.setLastName(accountDetail.getLastName());
				driver.setFkeyAccountId(accountObject);
				driver.setCurrentStatus(DriverLoginStatus.OFFLINE.name());
				driver.setBadgeNumber(accountDetail.getBadgeNumber());

				State state = new State();
				state.setId(accountDetail.getFkeyStateId());
				driver.setFkeyStateId(state);

				Address presentAddress = new Address();
				presentAddress.setId(accountDetail.getFkAddressId1());

				Address permanentAddress = new Address();
				permanentAddress.setId(accountDetail.getFkAddressId2());

				driver.setFkeyPresentAddressId(presentAddress);
				driver.setFkeyPermanentAddressId(permanentAddress);

				driver.setCreatedAt(new Date());
				driverDao.save(driver);
			} else if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_PASSENGER.name())) {
				accountRole.setId(Constant.ROLE_PASSENGER);
				Passenger passenger = new Passenger();
				passenger.setFirstName(accountDetail.getFirstName());
				passenger.setLastName(accountDetail.getLastName());
				passenger.setFkeyAccountId(accountObject);
				passenger.setCreatedAt(new Date());
				passengerDao.save(passenger);
			}
			if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_VERIFICATION_SUPERVISER.name())
					|| accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_VERIFICATION_ANALYST.name())
					|| accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_ACCOUNTS_SUPERVISER.name())
					|| accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_ACCOUNTS_ANALYST.name())) {

				if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_VERIFICATION_SUPERVISER.name()))
					accountRole.setId(Constant.ROLE_VERIFICATION_SUPERVISER);
				else if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_VERIFICATION_ANALYST.name()))
					accountRole.setId(Constant.ROLE_VERIFICATION_ANALYST);
				else if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_ACCOUNTS_SUPERVISER.name()))
					accountRole.setId(Constant.ROLE_ACCOUNTS_SUPERVISER);
				else if (accountDetail.getAccountRole().equalsIgnoreCase(Authorities.ROLE_ACCOUNTS_ANALYST.name()))
					accountRole.setId(Constant.ROLE_ACCOUNTS_ANALYST);

				AccountCityStateMapping accountCityStateMapping = new AccountCityStateMapping();
				accountCityStateMapping.setFkeyAccountId(accountObject);

				City fkeyCityId = new City();
				fkeyCityId.setId(accountDetail.getFkeyCityId());
				accountCityStateMapping.setFkeyCityId(fkeyCityId);

				accountCityStateMapping.setCreatedAt(new Date());
				accountCityStateMappingDao.save(accountCityStateMapping);
			}
			accountRoleMapping.setFkeyRoleId(accountRole);
			accountRoleMapping.setCreatedAt(new Date());
			accountRoleMappingDao.save(accountRoleMapping);

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Account Account) throws ProcessFailed {
		accountsDao.update(Account);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Account parseAccountDetailsToAccount(Account account, AccountDetail accountDetails) throws ProcessFailed {
		if (accountDetails.getAccountName() != null)
			account.setAccountName(accountDetails.getAccountName());
		if (accountDetails.getAccountPassword() != null) {
			String encodedPassword = customPasswordEncoder.encode(accountDetails.getAccountPassword());
			account.setAccountPassword(encodedPassword);
		}
		if (accountDetails.getEmailAddress() != null)
			account.setEmailAddress(accountDetails.getEmailAddress());
		if (accountDetails.getPhone() != null)
			account.setPhone(accountDetails.getPhone());
		// account.setRoles(roles);
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	public Account getAccountById(UUID id) throws ProcessFailed {
		Account account = accountsDao.getAccountById(id);
		if (account == null) {
			throw new ProcessFailed(messageSource.getMessage("account_not_found", new String[] {}, Locale.US));
		}
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	public Account getAccountByActivationKey(String activationKey) throws ProcessFailed {
		return accountsDao.getAccountByActivationKey(activationKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public void sendForgotPasswordMail(String mobileNumber) throws ProcessFailed {
		Account account = accountsDao.getByPhoneNumber(mobileNumber);
		if (account == null) {
			throw new ProcessFailed(messageSource.getMessage("account_name_not_found", new String[] {}, Locale.US));
		}
		// to-do otp sms

	}

	/**
	 * {@inheritDoc}
	 */
	public void changePassword(ChangePasswordDetail changePasswordDetail) throws ProcessFailed {
		Account account = accountsDao.getAccountById(UUID.fromString(changePasswordDetail.getAccountId()));
		if (customPasswordEncoder.matches(changePasswordDetail.getCurrentPassword(), account.getAccountPassword())) {
			account.setAccountPassword(customPasswordEncoder.encode(changePasswordDetail.getNewPassword()));
			accountsDao.update(account);
		} else if (!customPasswordEncoder.matches(changePasswordDetail.getCurrentPassword(),
				account.getAccountPassword())) {
			throw new ProcessFailed(messageSource.getMessage("invalid_current_password", new String[] {}, Locale.US));

		}
	}

}
