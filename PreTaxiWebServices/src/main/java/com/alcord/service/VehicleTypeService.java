package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleType;

public interface VehicleTypeService {
	/**
	 * This method pass id as input and get the {@link VehicleType} from DAO
	 * layer
	 *
	 * @param id
	 * @return {@link VehicleType}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public VehicleType getById(UUID id) throws ProcessFailed;

	/**
	 * This method pass driver as input and get the {@link VehicleType} from
	 * DAO layer.
	 *
	 * @param VehicleType
	 * @return {@link VehicleType}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public Integer save(VehicleType vehicleType) throws ProcessFailed;

	/**
	 * This method pass VehicleBrand as input and updates the
	 * {@link VehicleType} from database
	 *
	 * @param VehicleType
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public void update(VehicleType vehicleType) throws ProcessFailed;

	/**
	 * This method pass id as input and deletes the {@link VehicleType} from
	 * DAO layer.
	 *
	 * @param id
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public void delete(UUID id) throws ProcessFailed;
	/**
	 * This method pass id as input and get the {@link VehicleType} from DAO
	 * layer
	 *
	 * @param id
	 * @return {@link VehicleType}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public List<VehicleType> getAllVehicleType() throws ProcessFailed;

}
