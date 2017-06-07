/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.AccountRoleMapping;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.modelmappers.ChangePasswordDetail;
import com.alcord.modelmappers.UserDetail;

/**
 *
 * @author ajit
 */
public interface AccountService {
    
    /**
     * 
     * @param accountName
     * @return
     * @throws com.alcord.exception.ProcessFailed
     * @throws ProcessFailed 
     */
    Account findByAccountName(String accountName) throws ProcessFailed;
    
    /**
     * 
     * @param accountName
     * @return
     * @throws com.alcord.exception.ProcessFailed
     * @throws ProcessFailed 
     */
    Account isAccountExist(String accountName) throws ProcessFailed;
    /**
     * This method pass Account as input and get the {@link Boolean} from DAO layer.
     *
     * @param account
     * @return {@link Boolean}
     * @throws ProcessFailed the process failed
     */
    public Boolean checkUniqueAccount(Account account) throws ProcessFailed;

    /**
     * This method pass Account as input and get the {@link Integer} from DAO layer.
     *
     * @param accountDetail
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public void save(AccountDetail accountDetail) throws ProcessFailed;
    
    /**
     * This method pass User as input and get the {@link Integer} from DAO layer.
     *
     * @param usersDetails
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public void saveUser(UserDetail userDetail) throws ProcessFailed;
    
    /**
     * This method pass Account as input and updates the {@link Account} from DAO layer.
     *
     * @param account
     * @throws ProcessFailed the process failed
     */
    public void update(Account account) throws ProcessFailed;
    
    /**
     * This method pass Account and AccountDetail as input and parses it
     *
     * @param account
     * @param accountDetails
     * @return {@link Account}
     * @throws ProcessFailed the process failed
     */
    public Account parseAccountDetailsToAccount(Account account, AccountDetail accountDetails) throws ProcessFailed;
    
    /**
     * This method pass Account as input and get the {@link Account} from DAO layer
     *
     * @param id
     * @return {@link Account}
     * @throws ProcessFailed the process failed
     */
    public Account getAccountById(UUID id) throws ProcessFailed;
    
    /**
     * This method get Account detail  {@link Account} based on the
     * organization from the database.
     * 
     * @param activationKey
     * @return Account detail from Account table
     * @throws ProcessFailed the process failed
     */
    public Account getAccountByActivationKey(String activationKey) throws ProcessFailed;
    
    /**
     * This method sendForgotPasswordMail {@link Account} based on the accountName from the
     * database.
     *
     * @param accountName the accountName
     * @throws ProcessFailed the process failed
     */
    public void sendForgotPasswordMail(String accountName) throws ProcessFailed;
    
    /**
     * This method changePassword {@link Account} based on the id from the
     * database.
     *
     * @param changePasswordDetail the changePasswordDetail
     * @throws ProcessFailed the process failed
     */
    public void changePassword(ChangePasswordDetail changePasswordDetail) throws ProcessFailed;
}
