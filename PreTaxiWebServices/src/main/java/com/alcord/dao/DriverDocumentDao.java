package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverDocument;

public interface DriverDocumentDao {
	
	/**
     * This method pass id as input and get the {@link DriverDocument} from DAO layer
     *
     * @param Id
     * @return {@link DriverDocument}
     * @throws ProcessFailed the process failed
     */
    public DriverDocument getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass driverDocument as input and get the {@link DriverDocument} from DAO layer.
     *
     * @param DriverDocuent
     * @return {@link DriverDocument}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverDocument driverDocument) throws ProcessFailed;
    
    /**
     * This method pass DriverDocument as input and updates the {@link DriverDocument} from database
     *
     * @param driverDocument
     * @throws ProcessFailed the process failed
     */
    public void update(DriverDocument driverDocument) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverDocument} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;

}
