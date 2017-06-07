package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleColour;

/**
*
* @author Arbin Taj 
*/
public interface VehicleColourDao {
	/**
	 * This method pass id as input and get the {@link VehicleColour} from DAO
	 * layer
	 *
	 * @param id
	 * @return {@link VehicleColour}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public VehicleColour getById(UUID id) throws ProcessFailed;

	/**
	 * This method pass driver as input and get the {@link VehicleColour} from
	 * DAO layer.
	 *
	 * @param VehicleColour
	 * @return {@link VehicleColour}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public Integer save(VehicleColour vehicleColour) throws ProcessFailed;

	/**
	 * This method pass VehicleColour as input and updates the
	 * {@link VehicleColour} from database
	 *
	 * @param VehicleColour
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public void update(VehicleColour vehicleColour) throws ProcessFailed;

	/**
	 * This method pass id as input and deletes the {@link VehicleColour} from
	 * DAO layer.
	 *
	 * @param id
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public void delete(UUID id) throws ProcessFailed;
	/**
	 * This method pass id as input and get the {@link VehicleColour} from DAO
	 * layer
	 *
	 * @param id
	 * @return {@link VehicleColour}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public List<VehicleColour> getAllVehicleColour() throws ProcessFailed;
}
