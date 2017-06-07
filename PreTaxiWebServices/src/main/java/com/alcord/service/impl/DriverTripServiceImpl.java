package com.alcord.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.TripDao;
import com.alcord.enums.TripStatus;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.Driver;
import com.alcord.model.DriverSlab;
import com.alcord.model.Trip;
import com.alcord.model.Vehicle;
import com.alcord.modelmappers.TripDetail;
import com.alcord.service.DriverTripService;
import com.alcord.utility.Dummydata;

@Service
@Transactional
public class DriverTripServiceImpl extends BaseServiceImpl implements DriverTripService{
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private TripDao tripDao ;

	/**
	 * {@inheritDoc}
	 */
	public void acceptTrip(TripDetail tripDetail) throws ProcessFailed {
		try {
			Trip trip = tripDao.getById(UUID.fromString(tripDetail.getId()));
			 Driver driver = new Driver();
			 driver.setId(UUID.fromString(tripDetail.getDriverId()));
			 trip.setFkeyDriverId(driver);
			 DriverSlab driverSlab = new DriverSlab();
			 driverSlab.setId(UUID.fromString(tripDetail.getDriverSlabId()));
			 trip.setFkeyDriverSlabId(driverSlab);
			 Vehicle vehicle = new Vehicle();
			 vehicle.setId(UUID.fromString(tripDetail.getVehicleId()));
			 trip.setFkeyVehicleId(vehicle);
			 trip.setTripBookedAcceptTime(new Date());
			 trip.setUpdatedAt(new Date());
			 trip.setTripStatus(TripStatus.TRIP_BOOKED_ACCEPT.toString());
			 tripDao.update(trip);
			
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
		
	}


	/**
	 * {@inheritDoc}
	 */
	public List<TripDetail> getAllTrip(String startDate, String endDate, UUID driverId) throws ProcessFailed {
		// to-do Ajit replace this method with view
		List<TripDetail> tripDetailList = new ArrayList<TripDetail>();
		List<Trip> tripList = tripDao.getAllTrip();
		for (Trip trip : tripList) {
			TripDetail tripDetail = new TripDetail();
			tripDetail = parseTripToTripDetail(trip);
			tripDetailList.add(tripDetail);
		}
		return tripDetailList;
	}

	private TripDetail parseTripToTripDetail(Trip trip) throws ProcessFailed {
		TripDetail tripDetail = new TripDetail();
	       tripDetail.setPassengerId(trip.getFkeyPassengerId().getId().toString());
	       tripDetail.setId(trip.getId().toString());
	       tripDetail.setDriverId(trip.getFkeyDriverId().getId().toString());
	       tripDetail.setTripBookedTime(trip.getTripBookedTime());
	       tripDetail.setIsCancelled(trip.getIsCancelled());
	       tripDetail.setTripStartTime(trip.getTripStartTime());
	       tripDetail.setTripEndTime(trip.getTripEndTime());
	       tripDetail.setTripDistance(trip.getTripDistance());
	       tripDetail.setTripStartLocation(trip.getTripStartLocation());
	       tripDetail.setTripType(trip.getTripType());
	       tripDetail.setTripEndLongitude(trip.getTripEndLongitude());
	       tripDetail.setVehicleType(trip.getVehicleType());
	       tripDetail.setTripStartLocation(trip.getTripStartLocation());
		return tripDetail;
	}
}
