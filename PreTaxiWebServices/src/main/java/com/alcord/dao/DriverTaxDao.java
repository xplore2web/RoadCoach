/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Address;
import com.alcord.model.DriverTax;

/**
 * @author saurabh
 *
 */
public interface DriverTaxDao {
	/**
     * This method pass id as input and get the {@link DriverTax} from DAO layer
     *
     * @param id
     * @return {@link DriverTax}
     * @throws ProcessFailed the process failed
     */
    public DriverTax getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass DriverTax as input and get the {@link DriverTax} from DAO layer.
     *
     * @param driverTax
     * @return {@link driverTax}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverTax driverTax) throws ProcessFailed;
    
    /**
     * This method pass DriverTax as input and updates the {@link DriverTax} from database
     *
     * @param driverTax
     * @throws ProcessFailed the process failed
     */
    public void update(DriverTax driverTax) throws ProcessFailed;
    
   
}

	

