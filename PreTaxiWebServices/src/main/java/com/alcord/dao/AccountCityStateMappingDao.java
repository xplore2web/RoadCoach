package com.alcord.dao;


import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;

/**
 * <code> {@link AccountCityStateMappingDao} </code> Interface to get AccountCityStateMapping details from
 * AccountCityStateMapping table
 *
 * @author sandeep
 */
public interface AccountCityStateMappingDao {
	/**
     * This method pass id as input and get the {@link AccountCityStateMapping} from DAO layer
     *
     * @param id
     * @return {@link AccountCityStateMapping}
     * @throws ProcessFailed the process failed
     */
    public AccountCityStateMapping getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass cityId as input and get the {@link AccountCityStateMapping} from DAO layer
     *
     * @param cityId
     * @return {@link AccountCityStateMapping}
     * @throws ProcessFailed the process failed
     */
    public List<AccountCityStateMapping> getByCityId(UUID cityId) throws ProcessFailed;
    
    /**
     * This method pass AccountCityStateMapping as input and get the {@link AccountCityStateMapping} from DAO layer.
     *
     * @param accountCityStateMapping
     * @return {@link AccountCityStateMapping}
     * @throws ProcessFailed the process failed
     */
    public UUID save(AccountCityStateMapping accountCityStateMapping) throws ProcessFailed;
    
    /**
     * This method pass AccountCityStateMapping as input and updates the {@link AccountCityStateMapping} from database
     *
     * @param accountCityStateMapping
     * @throws ProcessFailed the process failed
     */
    public void update(AccountCityStateMapping accountCityStateMapping) throws ProcessFailed;
    
    /**
     * This method pass AccountCityStateMapping as input and deletes the {@link AccountCityStateMapping} from DAO layer.
     *
     * @param accountCityStateMapping
     * @throws ProcessFailed the process failed
     */
    public void delete(AccountCityStateMapping accountCityStateMapping) throws ProcessFailed;
    
    /**
     * This method pass accountId as input and get the {@link AccountCityStateMapping} from DAO layer.
     *
     * @param accountId
     * @throws ProcessFailed the process failed
     */
    public AccountCityStateMapping getByAccountId(UUID accountId) throws ProcessFailed;
}
