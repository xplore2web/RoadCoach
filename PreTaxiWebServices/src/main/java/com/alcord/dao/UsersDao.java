package com.alcord.dao;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.Users;
import java.util.List;
import java.util.UUID;

/**
 * <code> {@link UsersDao} </code> Interface to get Users details from
 * Users table
 *
 * @author akmal
 */
public interface UsersDao {
    
	/**
     * This method get Users detail  {@link Users} based on the
     * organization from the database.
     * 
     * @param user
     * @return Users detail from Users table
     * @throws ProcessFailed the process failed
     */
    Users findByUserName(String user) throws ProcessFailed;
    
    /**
     * This method pass User as input and get the {@link Integer} from database
     *
     * @param account
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Users user) throws ProcessFailed;
}
