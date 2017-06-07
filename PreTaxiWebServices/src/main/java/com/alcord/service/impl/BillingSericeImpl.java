package com.alcord.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.BillingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Billing;
import com.alcord.model.Trip;
import com.alcord.modelmappers.TripAddtionalFareDetail;
import com.alcord.service.BillingSerice;
@Service
@Transactional
public class BillingSericeImpl implements BillingSerice {
	
	@Autowired
	private BillingDao  billingDao;

	/**
	 * {@inheritDoc}
	 */
	public Billing getById(UUID id) throws ProcessFailed {
		return  billingDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Billing billing) throws ProcessFailed {
		return billingDao.save(billing);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Billing billing) throws ProcessFailed {
		billingDao.update(billing);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Billing billing) throws ProcessFailed {
		billingDao.delete(billing);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void addAddtionalFare(TripAddtionalFareDetail tripAddtionalFareDetail) throws ProcessFailed {
		Billing billing = new Billing();
		Trip trip = new Trip();
		 trip.setId(UUID.fromString(tripAddtionalFareDetail.getTripId()));
		 billing.setFkeyTripId(trip);
		 billing.setParkingFare(tripAddtionalFareDetail.getParkingFare());
		 billing.setTollFare(tripAddtionalFareDetail.getTollFare());
		 billing.setCreatedAt(new Date());
		 billingDao.save(billing);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAddtionalFare(TripAddtionalFareDetail tripAddtionalFareDetail) throws ProcessFailed {
		Billing billing = billingDao.getById(UUID.fromString(tripAddtionalFareDetail.getBillingId()));
		Trip trip = new Trip();
		 trip.setId(UUID.fromString(tripAddtionalFareDetail.getTripId()));
		 billing.setFkeyTripId(trip);
		 billing.setParkingFare(tripAddtionalFareDetail.getParkingFare());
		 billing.setTollFare(tripAddtionalFareDetail.getTollFare());
		 billing.setCreatedAt(new Date());
		 billingDao.update(billing);
		
	}

}
