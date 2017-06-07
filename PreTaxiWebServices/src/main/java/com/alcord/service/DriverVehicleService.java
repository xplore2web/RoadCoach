package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.modelmappers.VehicleDetail;

public interface DriverVehicleService {

	/**
	 * This method retrieves the list of {@link VehicleDetail} from DAO layer.
	 *
	 * @return {@link VehicleDetail}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public List<VehicleDetail> getAllApprovedVehicleDetail(String driverId) throws ProcessFailed;

	/**
	 * This method retrieves the list of {@link VehicleDetail} from DAO layer.
	 *
	 * @return {@link VehicleDetail}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public List<VehicleDetail> getAllVehicleDetail() throws ProcessFailed;

	/**
	 * This method retrieves the VehicleDetail of {@link VehicleDetail} from DAO
	 * layer.
	 *
	 * @return {@link VehicleDetail}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public VehicleDetail getVehicleDetail(UUID vehicleId) throws ProcessFailed;
	
	/**
     * This method pass VehicleDetail as input and save {@link Vehicle} from DAO layer.
     *
     * @param vehicleDetail
     * @throws ProcessFailed the process failed
     */
    public void saveVehicleDetail(UUID driverId, VehicleDetail vehicleDetail) throws ProcessFailed;
    
    /**
     * This method pass VehicleDetail as input and update {@link Vehicle} from DAO layer.
     *
     * @param vehicleDetail
     * @throws ProcessFailed the process failed
     */
    public void updateVehicleDetail(UUID driverId, VehicleDetail vehicleDetail) throws ProcessFailed;
    /**
     * 
     * @param id
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed
     */
    public List<VehicleDetail> getAllDriverVehicleByDriverId(UUID id)throws ProcessFailed;
}
