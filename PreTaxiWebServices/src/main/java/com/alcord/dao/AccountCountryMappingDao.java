package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCountryMapping;

/**
 * <code> {@link AccountCountryMappingDao} </code> Interface to get AccountCountryMapping details from
 * AccountCountryMapping table
 *
 * @author sandeep
 */
public interface AccountCountryMappingDao {
	/**
     * This method pass id as input and get the {@link AccountCountryMapping} from DAO layer
     *
     * @param id
     * @return {@link AccountCountryMapping}
     * @throws ProcessFailed the process failed
     */
    public AccountCountryMapping getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass AccountCountryMapping as input and get the {@link AccountCountryMapping} from DAO layer.
     *
     * @param accountCountryMapping
     * @return {@link AccountCountryMapping}
     * @throws ProcessFailed the process failed
     */
    public UUID save(AccountCountryMapping accountCountryMapping) throws ProcessFailed;
    
    /**
     * This method pass AccountCountryMapping as input and updates the {@link AccountCountryMapping} from database
     *
     * @param accountCountryMapping
     * @throws ProcessFailed the process failed
     */
    public void update(AccountCountryMapping accountCountryMapping) throws ProcessFailed;
    
    /**
     * This method pass address as input and deletes the {@link AccountCountryMapping} from DAO layer.
     *
     * @param accountCountryMapping
     * @throws ProcessFailed the process failed
     */
    public void delete(AccountCountryMapping accountCountryMapping) throws ProcessFailed;
}
