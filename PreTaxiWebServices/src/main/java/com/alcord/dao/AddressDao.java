package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Address;

/**
 * <code> {@link AddressDao} </code> Interface to get Address details from
 * Address table
 *
 * @author sandeep
 */
public interface AddressDao {
	/**
     * This method pass id as input and get the {@link Address} from DAO layer
     *
     * @param id
     * @return {@link Address}
     * @throws ProcessFailed the process failed
     */
    public Address getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass Address as input and get the {@link Address} from DAO layer.
     *
     * @param address
     * @return {@link Address}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Address address) throws ProcessFailed;
    
    /**
     * This method pass Address as input and updates the {@link Address} from database
     *
     * @param address
     * @throws ProcessFailed the process failed
     */
    public void update(Address address) throws ProcessFailed;
    
    /**
     * This method pass address as input and deletes the {@link Address} from DAO layer.
     *
     * @param address
     * @throws ProcessFailed the process failed
     */
    public void delete(Address address) throws ProcessFailed;
}
