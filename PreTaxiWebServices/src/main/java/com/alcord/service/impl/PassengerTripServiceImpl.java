package com.alcord.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.PassengerEmergencyContactDao;
import com.alcord.dao.TripDao;
import com.alcord.enums.TripStatus;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Passenger;
import com.alcord.model.PassengerEmergencyContact;
import com.alcord.model.Trip;
import com.alcord.modelmappers.TripDetail;
import com.alcord.service.PassengerTripService;

@Service
@Transactional
public class PassengerTripServiceImpl implements PassengerTripService {
	
	@Autowired
	private TripDao tripDao;
	@Autowired
	private PassengerEmergencyContactDao passengerEmergencyContactDao;


	@Override
	public void bookTrip(TripDetail tripDetail) throws ProcessFailed {
		
        Trip trip = new Trip();
        trip.setTripBookedTime(new Date());
        Passenger passenger = new Passenger();
        passenger.setId(UUID.fromString(tripDetail.getPassengerId()));
        trip.setFkeyPassengerId(passenger);
        trip.setTripDropLocation(tripDetail.getTripStartLocation());
        trip.setTripDropLocation(tripDetail.getTripDropLocation());
        trip.setTripStartLatitude(tripDetail.getTripStartLongitude());
        trip.setTripEndLongitude(tripDetail.getTripEndLongitude());
        trip.setIsPassengerSelf(tripDetail.getIsPassengerSelf());
        trip.setTripPassengerName(tripDetail.getTripPassengerName());
        trip.setTripType(tripDetail.getTripType());
        trip.setTripStatus(TripStatus.TRIP_BOOKED.toString());
        trip.setCreatedAt(new Date());
        tripDao.save(trip);
	}

	@Override
	public void alertEmergencyContact(UUID passengerId) throws ProcessFailed {
		// to-do send sms to emergency contact
		List<PassengerEmergencyContact> passengerEmergencyContactList = passengerEmergencyContactDao.getAllPassengerEmergencyContactByPassengerId(passengerId);
		
	}

}
