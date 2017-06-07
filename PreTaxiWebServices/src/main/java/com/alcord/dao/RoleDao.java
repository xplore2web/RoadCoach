/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Role;

/**
 *
 * @author ajit
 */
public interface RoleDao {
	
	/**
     * This method pass id as input and get the {@link Role} from DAO layer
     *
     * @param id
     * @return {@link Role}
     * @throws ProcessFailed the process failed
     */
    public Role getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass id as input and get the {@link Role} from DAO layer
     *
     * @param id
     * @return {@link Role}
     * @throws ProcessFailed the process failed
     */
    public Role getByRoleName(String roleName) throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param role
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public boolean isRoleExist(Role role) throws ProcessFailed;    
    /**
     * This method pass usersRoleLookup as input and get the {@link String} from DAO layer.
     *
     * @param role
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Role role) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Role} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
    
   
}
