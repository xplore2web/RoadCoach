package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.City;
import com.alcord.model.State;


/**
 *
 * @author Razak
 */

public interface StateDao {

	/**
     * This method pass id as input and get the {@link State} from DAO layer
     *
     * @param id
     * @return {@link State}
     * @throws ProcessFailed the process failed
     */
    public State getById(UUID id) throws ProcessFailed; 
    /**
     * This method {@link State} from Database
     *
     * @return list of {@link State}
     * @throws ProcessFailed the process failed
     */
    public List<State> getAllStates() throws ProcessFailed;   
    /**
     * This method pass usersStateLookup as input and get the {@link String} from DAO layer.
     *
     * @param State
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public boolean isStateExist(State state) throws ProcessFailed;    
    /**
     * This method pass usersStateLookup as input and get the {@link String} from DAO layer.
     *
     * @param State
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public UUID save(State state) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link State} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
    
   
}

