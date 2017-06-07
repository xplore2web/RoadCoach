package com.alcord.dao;



import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Country;

public interface CountryDao {
	
	/**
     * This method pass id as input and get the {@link Country} from DAO layer
     *
     * @param Id
     * @return {@link Country}
     * @throws ProcessFailed the process failed
     */
    public Country getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass country as input and get the {@link Country} from DAO layer.
     *
     * @param Country
     * @return {@link Country}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Country country) throws ProcessFailed;
    
    /**
     * This method pass Country as input and updates the {@link Country} from database
     *
     * @param country
     * @throws ProcessFailed the process failed
     */
    public void update(Country country) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Country} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Country country) throws ProcessFailed;

}
