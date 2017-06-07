/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Driver;
import com.alcord.model.MasterBaseFare;

/**
 * @author saurabh
 *
 */
public interface MasterBaseFareDao {
	/**
     * This method pass id as input and get the {@link MasterBaseFare} from DAO layer
     *
     * @param id
     * @return {@link MasterBaseFare}
     * @throws ProcessFailed the process failed
     */
    public MasterBaseFare getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass masterBaseFare as input and get the {@link MasterBaseFare} from DAO layer.
     *
     * @param masterBaseFare
     * @return {@link MasterBaseFare}
     * @throws ProcessFailed the process failed
     */
    public UUID save(MasterBaseFare masterBaseFare) throws ProcessFailed;
    
    /**
     * This method pass masterBaseFare as input and updates the {@link MasterBaseFare} from database
     *
     * @param masterBaseFare
     * @throws ProcessFailed the process failed
     */
    public void update(MasterBaseFare masterBaseFare) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link MasterBaseFare} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(MasterBaseFare masterBaseFare) throws ProcessFailed;
    
}
