package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.MasterSlab;

public interface MasterSlabDao {
	
	/**
     * This method pass id as input and get the {@link MasterSlab} from DAO layer
     *
     * @param Id
     * @return {@link MasterSlab}
     * @throws ProcessFailed the process failed
     */
    public MasterSlab getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass masterSlab as input and get the {@link MasterSlab} from DAO layer.
     *
     * @param MasterSlab
     * @return {@link MasterSlab}
     * @throws ProcessFailed the process failed
     */
    public UUID save(MasterSlab masterSlab) throws ProcessFailed;
    
    /**
     * This method pass MasterSlab as input and updates the {@link MasterSlab} from database
     *
     * @param masterSlab
     * @throws ProcessFailed the process failed
     */
    public void update(MasterSlab masterSlab) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link MasterSlab} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(MasterSlab masterSlab) throws ProcessFailed;

}
