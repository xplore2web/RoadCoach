package com.alcord.service;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Billing;
import com.alcord.modelmappers.TripAddtionalFareDetail;

public interface BillingSerice {
	
	/**
     * This method pass id as input and get the {@link Billing} from DAO layer
     *
     * @param Id
     * @return {@link Billing}
     * @throws ProcessFailed the process failed
     */
    public Billing getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass billing as input and get the {@link Billing} from DAO layer.
     *
     * @param Billing
     * @return {@link Billing}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Billing billing) throws ProcessFailed;
    
    /**
     * This method pass Billing as input and updates the {@link Billing} from database
     *
     * @param billing
     * @throws ProcessFailed the process failed
     */
    public void update(Billing billing) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Billing} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Billing billing) throws ProcessFailed;
    
    /**
     * This method pass tripAddtionalFareDetail as input and add the {@link TripAddtionalFareDetail} from database
     *
     * @param tripAddtionalFareDetail
     * @throws ProcessFailed the process failed
     */
    public void addAddtionalFare(TripAddtionalFareDetail tripAddtionalFareDetail) throws ProcessFailed;
    
    /**
     * This method pass tripAddtionalFareDetail as input and updates the {@link TripAddtionalFareDetail} from database
     *
     * @param tripAddtionalFareDetail
     * @throws ProcessFailed the process failed
     */
    public void updateAddtionalFare(TripAddtionalFareDetail tripAddtionalFareDetail) throws ProcessFailed;
    
    

}
