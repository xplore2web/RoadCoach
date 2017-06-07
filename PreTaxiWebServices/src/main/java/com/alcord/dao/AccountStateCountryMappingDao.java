package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountStateCountryMapping;

/**
 * <code> {@link AccountStateCountryMappingDao} </code> Interface to get AccountStateCountryMapping details from
 * AccountStateCountryMapping table
 *
 * @author sandeep
 */
public interface AccountStateCountryMappingDao {
	/**
     * This method pass id as input and get the {@link AccountStateCountryMapping} from DAO layer
     *
     * @param id
     * @return {@link AccountStateCountryMapping}
     * @throws ProcessFailed the process failed
     */
    public AccountStateCountryMapping getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass AccountStateCountryMapping as input and get the {@link AccountStateCountryMapping} from DAO layer.
     *
     * @param accountStateCountryMapping
     * @return {@link AccountStateCountryMapping}
     * @throws ProcessFailed the process failed
     */
    public UUID save(AccountStateCountryMapping accountStateCountryMapping) throws ProcessFailed;
    
    /**
     * This method pass AccountStateCountryMapping as input and updates the {@link AccountStateCountryMapping} from database
     *
     * @param accountStateCountryMapping
     * @throws ProcessFailed the process failed
     */
    public void update(AccountStateCountryMapping accountStateCountryMapping) throws ProcessFailed;
    
    /**
     * This method pass AccountStateCountryMapping as input and deletes the {@link AccountStateCountryMapping} from DAO layer.
     *
     * @param accountStateCountryMapping
     * @throws ProcessFailed the process failed
     */
    public void delete(AccountStateCountryMapping accountStateCountryMapping) throws ProcessFailed;

}
