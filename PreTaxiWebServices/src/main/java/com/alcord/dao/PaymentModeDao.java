/**
 * 
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Driver;
import com.alcord.model.PaymentMode;

/**
 * @author saurabh
 *
 */
public interface PaymentModeDao  {
	
	/**
     * This method pass id as input and get the {@link PaymentMode} from DAO layer
     *
     * @param id
     * @return {@link PaymentMode}
     * @throws ProcessFailed the process failed
     */
    public PaymentMode getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass paymentMode as input and get the {@link PaymentMode} from DAO layer.
     *
     * @param paymentMode
     * @return {@link PaymentMode}
     * @throws ProcessFailed the process failed
     */
    public UUID save(PaymentMode paymentMode) throws ProcessFailed;
    
    /**
     * This method pass paymentMode as input and updates the {@link PaymentMode} from database
     *
     * @param paymentMode
     * @throws ProcessFailed the process failed
     */
    public void update(PaymentMode paymentMode) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link PaymentMode} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(PaymentMode paymentMode) throws ProcessFailed;
	

}
