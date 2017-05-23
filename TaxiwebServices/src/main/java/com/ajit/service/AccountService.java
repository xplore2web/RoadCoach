/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.ajit.service;

import com.ajit.exception.ProcessFailed;
import com.ajit.model.Account;
import com.ajit.modelmappers.UserLoginDetails;

/**
 *
 * @author ajit
 */
public interface AccountService {
//    
//    /**
//     * 
//     * @param accountName
//     * @return
//     * @throws com.alcord.exception.ProcessFailed
//     * @throws ProcessFailed 
//     */
//    Account findByAccountName(String accountName) throws ProcessFailed;
//    
//    /**
//     * 
//     * @param accountName
//     * @return
//     * @throws com.alcord.exception.ProcessFailed
//     * @throws ProcessFailed 
//     */
//    Account isAccountExist(String accountName) throws ProcessFailed;
//    /**
//     * This method pass Account as input and get the {@link Boolean} from DAO layer.
//     *
//     * @param account
//     * @return {@link Boolean}
//     * @throws ProcessFailed the process failed
//     */
//    public Boolean checkUniqueAccount(Account account) throws ProcessFailed;

    /**
     * This method pass Account as input and get the {@link Integer} from DAO layer.
     *
     * @param accountDetailsRequest
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public void save(UserLoginDetails accountDetailsRequest) throws ProcessFailed;
    
//    /**
//     * This method pass Account as input and updates the {@link Account} from DAO layer.
//     *
//     * @param account
//     * @throws ProcessFailed the process failed
//     */
//    public void update(Account account) throws ProcessFailed;
//    
//    /**
//     * This method pass Account as input and get the {@link Account} from DAO layer
//     *
//     * @param id
//     * @return {@link Account}
//     * @throws ProcessFailed the process failed
//     */
//    public Account getAccountById(Integer id) throws ProcessFailed;
//    
//    /**
//     * This method get Account detail  {@link Account} based on the
//     * organization from the database.
//     * 
//     * @param activationKey
//     * @return Account detail from Account table
//     * @throws ProcessFailed the process failed
//     */
//    public Account getAccountByActivationKey(String activationKey) throws ProcessFailed;
//    
//    /**
//     * This method sendForgotPasswordMail {@link Account} based on the accountName from the
//     * database.
//     *
//     * @param accountName the accountName
//     * @throws ProcessFailed the process failed
//     */
//    public void sendForgotPasswordMail(String accountName) throws ProcessFailed;
//    
//    /**
//     * This method changePassword {@link Account} based on the id from the
//     * database.
//     *
//     * @param account the account
//     * @param oldPassword the oldPassword
//     * @param newPassword the newPassword
//     * @throws ProcessFailed the process failed
//     */
//    public void changePassword(Account account, String oldPassword, String newPassword) throws ProcessFailed;
}
