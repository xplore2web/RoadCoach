package com.ajit.service;

import com.ajit.exception.ProcessFailed;
import com.ajit.model.TaxiUser;

/**
 * <code>{@link TaxiUserService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface TaxiUserService {
  
	 /**
     * This method save {@link ContactForm} into the database.
     *
     * @param contactForm the contactForm
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(TaxiUser taxiUser) throws ProcessFailed;
	

}
