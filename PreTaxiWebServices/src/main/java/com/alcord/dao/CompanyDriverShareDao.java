/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.CompanyDriverShare;
import com.alcord.model.Driver;
import com.alcord.model.PaymentMode;

/**
 * @author saurabh
 *
 */
public interface CompanyDriverShareDao {
	

	/**
     * This method pass id as input and get the {@link CompanyDriverShare} from DAO layer
     *
     * @param id
     * @return {@link CompanyDriverShare}
     * @throws ProcessFailed the process failed
     */
    public CompanyDriverShare getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass companyDriverShare as input and get the {@link CompanyDriverShare} from DAO layer.
     *
     * @param companyDriverShare
     * @return {@link CompanyDriverShare}
     * @throws ProcessFailed the process failed
     */
    public UUID save(CompanyDriverShare companyDriverShare) throws ProcessFailed;
    
    /**
     * This method pass companyDriverShare as input and updates the {@link CompanyDriverShare} from database
     *
     * @param companyDriverShare
     * @throws ProcessFailed the process failed
     */
    public void update(CompanyDriverShare companyDriverShare) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link CompanyDriverShare} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(CompanyDriverShare companyDriverShare) throws ProcessFailed;
	

}
