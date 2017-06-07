package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.City;

public interface CityDao {
	
	/**
     * This method pass id as input and get the {@link City} from DAO layer
     *
     * @param Id
     * @return {@link City}
     * @throws ProcessFailed the process failed
     */
    public City getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method {@link City} from Database
     *
     * @return list of {@link City}
     * @throws ProcessFailed the process failed
     */
    public List<City> getAllCities() throws ProcessFailed;
    
    /**
     * This method pass city as input and get the {@link City} from DAO layer.
     *
     * @param City
     * @return {@link City}
     * @throws ProcessFailed the process failed
     */
    public UUID save(City city) throws ProcessFailed;
    
    /**
     * This method pass City as input and updates the {@link City} from database
     *
     * @param city
     * @throws ProcessFailed the process failed
     */
    public void update(City city) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link City} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(City city) throws ProcessFailed;

}
