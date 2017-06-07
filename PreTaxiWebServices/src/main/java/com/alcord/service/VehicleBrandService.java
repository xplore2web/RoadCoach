package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleBrand;

public interface VehicleBrandService {
	
	/**
	 * This method pass id as input and get the {@link VehicleBrand} from DAO
	 * layer
	 *
	 * @param id
	 * @return {@link VehicleBrand}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public VehicleBrand getById(UUID id) throws ProcessFailed;

	/**
	 * This method pass driver as input and get the {@link VehicleBrand} from
	 * DAO layer.
	 *
	 * @param VehicleBrand
	 * @return {@link VehicleBrand}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public Integer save(VehicleBrand vehicleBrand) throws ProcessFailed;

	/**
	 * This method pass VehicleBrand as input and updates the
	 * {@link VehicleBrand} from database
	 *
	 * @param VehicleBrand
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public void update(VehicleBrand vehicleBrand) throws ProcessFailed;

	/**
	 * This method pass id as input and deletes the {@link VehicleBrand} from
	 * DAO layer.
	 *
	 * @param id
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public void delete(UUID id) throws ProcessFailed;
	/**
	 * This method pass id as input and get the {@link VehicleBrand} from DAO
	 * layer
	 *
	 * @param id
	 * @return {@link VehicleBrand}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public List<VehicleBrand> getAllVehicleBrand() throws ProcessFailed;


}
