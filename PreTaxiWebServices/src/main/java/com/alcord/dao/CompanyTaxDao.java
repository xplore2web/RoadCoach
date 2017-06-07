/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyTax;

/**
 * @author saurabh
 *
 */
public interface CompanyTaxDao {
	
	/**
     * This method pass id as input and get the {@link CompanyTax} from DAO layer
     *
     * @param id
     * @return {@link CompanyTax}
     * @throws ProcessFailed the process failed
     */
    public CompanyTax getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass CompanyTax as input and get the {@link CompanyTax} from DAO layer.
     *
     * @param companyTax
     * @return {@link companyTax}
     * @throws ProcessFailed the process failed
     */
    public UUID save(CompanyTax companyTax) throws ProcessFailed;
    
    /**
     * This method pass CompanyTax as input and updates the {@link CompanyTax} from database
     *
     * @param companyTax
     * @throws ProcessFailed the process failed
     */
    public void update(CompanyTax companyTax) throws ProcessFailed;
	
	

}
