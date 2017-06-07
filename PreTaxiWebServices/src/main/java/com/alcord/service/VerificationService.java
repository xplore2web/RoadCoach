package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.modelmappers.DriverDetail;
import com.alcord.modelmappers.VehicleDetail;

public interface VerificationService {
	
	/**
     * @param driverDetail
     * @throws ProcessFailed the process failed
     */
    public void saveDriver(DriverDetail driverDetail) throws ProcessFailed;
    
    /**
     * @param driverId
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public List<DriverVehicleMapping> getAllVehiclesByDriverId(String driverId) throws ProcessFailed;
    
    /**
     * @param cityId
     * @return {@link AccountCityStateMapping}
     * @throws ProcessFailed the process failed
     */
    public List<AccountCityStateMapping> getByCityId(UUID cityId) throws ProcessFailed;

}
