/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.dao;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import java.util.List;
import java.util.UUID;

/**
 * <code> {@link AccountDao} </code> Interface to get Account details from
 * Account table
 *
 * @author ajit
 */
public interface AccountDao {
    
    /**
     * This method get Account detail  {@link Account} based on the
     * organization from the database.
     * 
     * @param accountName
     * @return Account detail from Account table
     * @throws ProcessFailed the process failed
     */
    Account findByAccountName(String accountName) throws ProcessFailed;
    
    /**
     * This method pass Account as input and get the {@link Boolean} from database
     *
     * @param account
     * @return {@link Boolean}
     * @throws ProcessFailed the process failed
     */
    public Boolean checkUniqueAccount(Account account) throws ProcessFailed;
    
    /**
     * This method pass Account as input and get the {@link Integer} from database
     *
     * @param account
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Account account) throws ProcessFailed;
    
    /**
     * This method pass Account as input and updates the {@link Account} from database
     *
     * @param account
     * @throws ProcessFailed the process failed
     */
    public void update(Account account) throws ProcessFailed;

    /**
     * This method pass Account as input and updates the {@link Account} from database
     *
     * @param account
     * @throws ProcessFailed the process failed
     */
    public void delete(Account account) throws ProcessFailed;
    
    /**
     * This method pass Account as input and get the {@link Account} from database
     *
     * @param AccountId
     * @return {@link Account}
     * @throws ProcessFailed the process failed
     */
    public Account getAccountById(UUID id) throws ProcessFailed;
    
       /**
     * This method pass Account as input and get the {@link Account} from database
     *
     * @return {@link List<Account>}
     * @throws ProcessFailed the process failed
     */
    public List<Account>getAll()throws ProcessFailed;
    
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
     * This method get Account detail  {@link Account} based on the
     * organization from the database.
     * 
     * @param emailId
     * @return Account detail from Account table
     * @throws ProcessFailed the process failed
     */
    Account getByEmailId(String emailId) throws ProcessFailed;
    /**
     * This method get Account detail  {@link Account} based on the
     * organization from the database.
     * 
     * @param phoneNumber
     * @return Account detail from Account table
     * @throws ProcessFailed the process failed
     */
    Account getByPhoneNumber(String phoneNumber) throws ProcessFailed;
}
