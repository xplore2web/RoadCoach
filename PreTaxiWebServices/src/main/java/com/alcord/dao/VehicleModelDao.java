package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleModel;

/**
*
* @author Arbin Taj 
*/
public interface VehicleModelDao {
	/**
     * This method pass id as input and get the {@link VehicleModel} from DAO layer
     *
     * @param id
     * @return {@link VehicleModel}
     * @throws ProcessFailed the process failed
     */
    public VehicleModel getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass driver as input and get the {@link VehicleModel} from DAO layer.
     *
     * @param VehicleModel
     * @return {@link VehicleModel}
     * @throws ProcessFailed the process failed
     */
    public UUID save(VehicleModel vehicleModel) throws ProcessFailed;
    
    /**
     * This method pass VehicleModel as input and updates the {@link VehicleModel} from database
     *
     * @param VehicleModel
     * @throws ProcessFailed the process failed
     */
    public void update(VehicleModel vehicleModel) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link VehicleModel} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
    /**
     * This method pass id as input and get the {@link VehicleModel} from DAO layer
     *
     * @param id
     * @return {@link VehicleModel}
     * @throws ProcessFailed the process failed
     */
    public List<VehicleModel> getAllVehicleModel() throws ProcessFailed;

}
