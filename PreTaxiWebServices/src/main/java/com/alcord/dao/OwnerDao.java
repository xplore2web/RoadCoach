package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Owner;

/**
*
* @author Razak
*/

public interface OwnerDao {
	
	/**
     * This method pass id as input and get the {@link Owner} from DAO layer
     *
     * @param id
     * @return {@link Owner}
     * @throws ProcessFailed the process failed
     */
    public Owner getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass owner as input and get the {@link Owner} from DAO layer.
     *
     * @param owner
     * @return {@link Owner}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Owner owner) throws ProcessFailed;
    
    /**
     * This method pass owner as input and updates the {@link Owner} from database
     *
     * @param owner
     * @throws ProcessFailed the process failed
     */
    public void update(Owner owner) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Owner} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;

}
