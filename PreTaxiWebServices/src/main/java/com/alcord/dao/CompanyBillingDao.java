/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyBilling;
import com.alcord.model.MasterBaseFare;

/**
 * @author saurabh
 *
 */
public interface CompanyBillingDao {
	/**
     * This method pass id as input and get the {@link CompanyBilling} from DAO layer
     *
     * @param id
     * @return {@link CompanyBilling}
     * @throws ProcessFailed the process failed
     */
    public CompanyBilling getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass companyBilling as input and get the {@link CompanyBilling} from DAO layer.
     *
     * @param companyBilling
     * @return {@link CompanyBilling}
     * @throws ProcessFailed the process failed
     */
    public UUID save(CompanyBilling companyBilling) throws ProcessFailed;
    
    /**
     * This method pass companyBilling as input and updates the {@link CompanyBilling} from database
     *
     * @param companyBilling
     * @throws ProcessFailed the process failed
     */
    public void update(CompanyBilling companyBilling) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link CompanyBilling} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(CompanyBilling companyBilling) throws ProcessFailed;
	

}
