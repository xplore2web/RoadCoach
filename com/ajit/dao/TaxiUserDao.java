package com.ajit.dao;

import com.ajit.exception.ProcessFailed;
import com.ajit.model.TaxiUser;

/**
 * <code> {@link TaxiUserDao} </code> Interface to get Contact Form details from
 * taxiUser form table
 *
 * @author Ajit
 */
public interface TaxiUserDao {
	
	 /**
     * This method save {@link TaxiUser} into the database.
     *
     * @param taxiUser the taxiUser
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(TaxiUser taxiUser) throws ProcessFailed;

}
