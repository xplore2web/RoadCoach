/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Driver;
import com.alcord.model.DriverBilling;
import com.alcord.model.MasterBaseFare;

/**
 * @author saurabh
 *
 */
public interface DriverBillingDao {
	
	
	/**
     * This method pass id as input and get the {@link DriverBilling} from DAO layer
     *
     * @param id
     * @return {@link DriverBilling}
     * @throws ProcessFailed the process failed
     */
    public DriverBilling getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass driverBilling as input and get the {@link DriverBilling} from DAO layer.
     *
     * @param driverBilling
     * @return {@link DriverBilling}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverBilling driverBilling) throws ProcessFailed;
    
    /**
     * This method pass driverBilling as input and updates the {@link DriverBilling} from database
     *
     * @param driverBilling
     * @throws ProcessFailed the process failed
     */
    public void update(DriverBilling driverBilling) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverBilling} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(DriverBilling driverBilling) throws ProcessFailed;

}
