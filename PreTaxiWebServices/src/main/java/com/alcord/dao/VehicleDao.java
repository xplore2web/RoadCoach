package com.alcord.dao;


import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Vehicle;
/**
*
* @author Arbin Taj 
*/
public interface VehicleDao {
	/**
     * This method pass id as input and get the {@link Vehicle} from DAO layer
     *
     * @param id
     * @return {@link Vehicle}
     * @throws ProcessFailed the process failed
     */
	public Vehicle getById(UUID id) throws ProcessFailed;
	 /**
     * This method pass vehicle as input and get the {@link Vehicle} from DAO layer.
     *
     * @param Vehicle
     * @return {@link Vehicle}
     * @throws ProcessFailed the process failed
     */
	public UUID save(Vehicle vehicle) throws ProcessFailed;
	 /**
     * This method pass Vehicle as input and updates the {@link Vehicle} from database
     *
     * @param Vehicle
     * @throws ProcessFailed the process failed
     */
	
	public void update(Vehicle vehicle) throws ProcessFailed;
	/**
     * This method pass id as input and deletes the {@link Vehicle} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
	public void delete(UUID id) throws ProcessFailed;
	
	/**
     * This method pass id as input and get the {@link Vehicle} from DAO layer
     *
     * @param id
     * @return {@link Vehicle}
     * @throws ProcessFailed the process failed
     */
	public List<Vehicle> getAllVehicle() throws ProcessFailed;
}
