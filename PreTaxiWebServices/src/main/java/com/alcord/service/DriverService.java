package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.Driver;
import com.alcord.modelmappers.DriverAccountDetail;
import com.alcord.modelmappers.DriverDetail;
import com.alcord.modelmappers.DriverLocationDetail;
import com.alcord.modelmappers.DriverSlabDetail;
import com.alcord.modelmappers.DriverStatus;
import com.alcord.modelmappers.DriverTodayDetail;
import com.alcord.modelmappers.UpdateDriverAccount;

public interface DriverService {
	 /**
     * This method pass Diver Status
     *  as input and get the {@link DriverStatus} from DAO layer
     *
     * @return {@link DriverStatus}
     * @throws ProcessFailed the process failed
     */
    public List<DriverStatus> getAllDriverStatus() throws ProcessFailed;
    /**
     * This method pass Diver Driver Today Detail
     *  as input and get the {@link DriverTodayDetail} from DAO layer
     *
     * @return {@link DriverStatus}
     * @throws ProcessFailed the process failed
     */
    public DriverTodayDetail getDriverTodayDetail(UUID diverId) throws ProcessFailed;
    /**
     * This method pass DriverAccountDetails 
     *  as input and get the {@link Driver} from DAO layer
     *  
     *@param accountId
     * @return {@link DriverDetail}
     * @throws ProcessFailed the process failed
     */
    public DriverAccountDetail getDriverAccountDetailsByAccountId(UUID accountId) throws ProcessFailed;
    
    /**
     * This method pass driver as input and updates the {@link DriverStatus} from database
     *
     * @param driver
     * @throws ProcessFailed the process failed
     */
    public void updateStatus(DriverStatus driverStatus) throws ProcessFailed ;
    
    /**
     * This method pass driver as input and updates the {@link DriverDetails} from database
     *
     * @param driverDetail
     * @throws ProcessFailed the process failed
     */
    public void updateDriver(DriverDetail driverDetail) throws ProcessFailed ;
    
    /**
     * This method pass accountId
     *  as input and get the {@link Driver} from DAO layer
     *
     * @return {@link Driver}
     * @throws ProcessFailed the process failed
     */
	public Driver getByAccountId(UUID accountId) throws ProcessFailed;
	/**
     * This method pass driverLocationDetail as input and updates the {@link DriverLocationDetail} from database
     *
     * @param driverLocationDetail
     * @throws ProcessFailed the process failed
     */
    public void saveCurrentLocation(DriverLocationDetail driverLocationDetail) throws ProcessFailed ;
    
    /**
     * This method pass driverSlabDetail as input and updates the {@link DriverSlabDetail} from database
     *
     * @param driverSlabDetail
     * @throws ProcessFailed the process failed
     */
    public void saveSlab(DriverSlabDetail driverSlabDetail) throws ProcessFailed ;
    
    /**
     * @param badgeNumber
     * @param cityId
     * @return return {@link Driver}
     * @throws ProcessFailed
     */
    public List<Driver> getByBadgeNumber(String badgeNumber, UUID stateId) throws ProcessFailed;
    /**
     * 
     * @param name
     * @param cityId
     * @return return {@link Driver}
     * @throws ProcessFailed
     */
    public List<Driver> getByName(String name, UUID stateId) throws ProcessFailed;
    /* * 
     * This method pass updateDriverAccount as input and updates the {@link UpdateDriverAccount} from database
     * 
     *
     * @param updateDriverAccount
     * @throws ProcessFailed the process failed
     */
    public void updateAccount(UpdateDriverAccount updateDriverAccount) throws ProcessFailed ;
    
    /**
     * This method pass DriverAccountDetails 
     *  as input and get the {@link Driver} from DAO layer
     *  
     *@param accountId
     * @return {@link DriverDetail}
     * @throws ProcessFailed the process failed
     */
    public DriverAccountDetail getDriverAccountDetailsBydriverId(UUID driverId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link Driver} from DAO layer
     *
     * @param id
     * @return {@link Driver}
     * @throws ProcessFailed the process failed
     */
    public Driver getById(UUID id) throws ProcessFailed;

}
