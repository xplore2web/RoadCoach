package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.OwnerVehicleMapping;

/**
*
* @author Razak
*/

public interface OwnerVehicleMappingDao {
	
	/**
     * This method pass id as input and get the {@link OwnerVehicleMapping} from DAO layer
     *
     * @param Id
     * @return {@link OwnerVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public OwnerVehicleMapping getById(UUID Id) throws ProcessFailed;
    
    /**
     * This method pass ownerVehicleMapping as input and get the {@link OwnerVehicleMapping} from DAO layer.
     *
     * @param OwnerVehicleMapping
     * @return {@link OwnerVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public UUID save(OwnerVehicleMapping ownerVehicleMapping) throws ProcessFailed;
    
    /**
     * This method pass ownerVehicleMapping as input and updates the {@link OwnerVehicleMapping} from database
     *
     * @param ownerVehicleMapping
     * @throws ProcessFailed the process failed
     */
    public void update(OwnerVehicleMapping ownerVehicleMapping) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link OwnerVehicleMapping} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;

}
