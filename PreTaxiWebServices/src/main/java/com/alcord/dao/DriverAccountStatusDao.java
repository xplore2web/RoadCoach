/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyBilling;
import com.alcord.model.DriverAccountStatus;
import com.alcord.model.MasterBaseFare;

/**
 * @author saurabh
 *
 */
public interface DriverAccountStatusDao {
	
	/**
     * This method pass id as input and get the {@link DriverAccountStatus} from DAO layer
     *
     * @param id
     * @return {@link DriverAccountStatus}
     * @throws ProcessFailed the process failed
     */
    public DriverAccountStatus getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass driverAccountStatus as input and get the {@link DriverAccountStatus} from DAO layer.
     *
     * @param driverAccountStatus
     * @return {@link DriverAccountStatus}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverAccountStatus driverAccountStatus) throws ProcessFailed;
    
    /**
     * This method pass driverAccountStatus as input and updates the {@link DriverAccountStatus} from database
     *
     * @param driverAccountStatus
     * @throws ProcessFailed the process failed
     */
    public void update(DriverAccountStatus driverAccountStatus) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverAccountStatus} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(DriverAccountStatus driverAccountStatus) throws ProcessFailed;
	

}
