package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleDocument;

public interface VehicleDocumentService {
	
	/**
     * This method pass id as input and get the {@link VehicleDocument} from DAO layer
     *
     * @param id
     * @return {@link VehicleDocument}
     * @throws ProcessFailed the process failed
     */
    public VehicleDocument getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass driver as input and get the {@link VehicleDocument} from DAO layer.
     *
     * @param VehicleDocument
     * @return {@link VehicleDocument}
     * @throws ProcessFailed the process failed
     */
    public UUID save(VehicleDocument vehicleDocument) throws ProcessFailed;
     /**
     * This method pass VehicleDocument as input and updates the {@link VehicleDocument} from database
     *
     * @param VehicleDocument
     * @throws ProcessFailed the process failed
     */
    public void update(VehicleDocument vehicleDocument) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link VehicleDocument} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
    
    
    

}
