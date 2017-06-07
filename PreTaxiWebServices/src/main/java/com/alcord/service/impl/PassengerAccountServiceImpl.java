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

import com.alcord.dao.AddressDao;
import com.alcord.dao.PassengerDao;
import com.alcord.dao.PassengerEmergencyContactDao;
import com.alcord.dao.PassengerPreferenceDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Address;
import com.alcord.model.Passenger;
import com.alcord.model.PassengerEmergencyContact;
import com.alcord.model.PassengerPreference;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.modelmappers.EmergencyContactDetail;
import com.alcord.modelmappers.PassengerDetail;
import com.alcord.modelmappers.PassengerPreferenceDetail;
import com.alcord.service.GalleryService;
import com.alcord.service.PassengerAccountService;
import com.alcord.utility.StringUtility;

@Service
@Transactional
public class PassengerAccountServiceImpl extends BaseServiceImpl implements PassengerAccountService {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PassengerEmergencyContactDao passengerEmergencyContactDao;
	@Autowired
	private PassengerDao passengerDao;
	@Autowired
	private GalleryService galleryService;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private PassengerPreferenceDao passengerPreferenceDao;

	/**
	 * {@inheritDoc}
	 */
	public void updateProfile(PassengerDetail passengerDetail) throws ProcessFailed {
		try {
			Passenger passenger = passengerDao.getById(UUID.fromString(passengerDetail.getId()));
			passenger.setFirstName(passengerDetail.getFirstName());
			passenger.setLastName(passengerDetail.getLastName());
			passenger.setPhoto(passengerDetail.getPhoto());
			passenger.setUpdatedAt(new Date());
			passengerDao.update(passenger);

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public AccountDetail getPassengerProfileDetail(UUID id) throws ProcessFailed {
		try {
			Passenger passenger = passengerDao.getById(id);
			AccountDetail accountDetail = new AccountDetail();
			accountDetail.setId(passenger.getId().toString());
			accountDetail.setFirstName(passenger.getFirstName());
			accountDetail.setLastName(passenger.getLastName());
			accountDetail.setAccountName(passenger.getFkeyAccountId().getAccountName());

			return accountDetail;
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addEmergencyContact(EmergencyContactDetail emergencyContactDetail) throws ProcessFailed {
		PassengerEmergencyContact passengerEmergencyContact = new PassengerEmergencyContact();
		Passenger passenger = new Passenger();
		passenger.setId(UUID.fromString(emergencyContactDetail.getPassengerId()));
		passengerEmergencyContact.setFkeyPassengerId(passenger);
		passengerEmergencyContact.setFullName(emergencyContactDetail.getFullName());
		passengerEmergencyContact.setPhone(emergencyContactDetail.getPhoneNumber());
		passengerEmergencyContact.setCreatedAt(new Date());
		passengerEmergencyContactDao.save(passengerEmergencyContact);

	}

	/**
	 * {@inheritDoc}
	 */
	public void updateEmergencyContact(EmergencyContactDetail emergencyContactDetail) throws ProcessFailed {
		PassengerEmergencyContact passengerEmergencyContact = passengerEmergencyContactDao
				.getById(UUID.fromString(emergencyContactDetail.getId()));
		passengerEmergencyContact.setFullName(emergencyContactDetail.getFullName());
		passengerEmergencyContact.setPhone(emergencyContactDetail.getPhoneNumber());
		passengerEmergencyContact.setUpdatedAt(new Date());
		passengerEmergencyContactDao.update(passengerEmergencyContact);

	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteEmergencyContact(UUID id) throws ProcessFailed {
		PassengerEmergencyContact passengerEmergencyContact = passengerEmergencyContactDao.getById(id);
		passengerEmergencyContactDao.delete(passengerEmergencyContact);

	}

	/**
	 * {@inheritDoc}
	 */
	public List<EmergencyContactDetail> getAllEmergencyContactByPassengerId(UUID passengerId) throws ProcessFailed {
		List<EmergencyContactDetail> emergencyContactDetailList = new ArrayList<>();
		List<PassengerEmergencyContact> passengerEmergencyContactList = passengerEmergencyContactDao.getAllPassengerEmergencyContactByPassengerId(passengerId);
		for (PassengerEmergencyContact passengerEmergencyContact : passengerEmergencyContactList) {
			EmergencyContactDetail emergencyContactDetail = parsePassengerEmergencyContactToEmergencyContactDetail(passengerEmergencyContact);
			emergencyContactDetailList.add(emergencyContactDetail);
		}
		return emergencyContactDetailList;
	}
	
	private EmergencyContactDetail parsePassengerEmergencyContactToEmergencyContactDetail(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed {
		EmergencyContactDetail emergencyContactDetail = new EmergencyContactDetail();
		emergencyContactDetail.setFullName(passengerEmergencyContact.getFullName());
		emergencyContactDetail.setId(passengerEmergencyContact.getId().toString());
		emergencyContactDetail.setPassengerId(passengerEmergencyContact.getFkeyPassengerId().getId().toString());
		emergencyContactDetail.setPhoneNumber(passengerEmergencyContact.getPhone());

		return emergencyContactDetail;
	}

	/**
	 * {@inheritDoc}
	 */
	public Passenger getPassengerProfile(String passengerId) {
		Passenger passenger = passengerDao.getById(UUID.fromString(passengerId));
		return passenger;
	}

	/**
	 * {@inheritDoc}
	 */
	public Passenger getByAccountId(UUID id) throws ProcessFailed {
		return passengerDao.getByAccountId(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public PassengerDetail parsePassengerToPassengerDetail(Passenger passenger) {
		PassengerDetail passengerDetail = new PassengerDetail();
		if (passenger != null) {
			passengerDetail.setAccountId(passenger.getFkeyAccountId().getId().toString());
			passengerDetail.setFirstName(passenger.getFirstName());
			passengerDetail.setLastName(passenger.getLastName());
			passengerDetail.setId(passenger.getId().toString());
			String profilePic = "";
			if (StringUtility.isEmpty(passenger.getPhoto())) {
				try {
					profilePic = galleryService.getPassengerProfilePic(passenger.getId().toString());
				} catch (Exception e) {
					profilePic = galleryService.getDefaultDriverProfilePic();
					e.printStackTrace();
				}
			}
			if (StringUtility.isEmpty(profilePic)) {
				profilePic = galleryService.getDefaultDriverProfilePic();
			}
			passengerDetail.setPhoto(profilePic);
		}
		return passengerDetail;

	}

	/**
	 * {@inheritDoc}
	 */
	public void addPreferences(PassengerPreferenceDetail passengerPreferenceDetail)
			throws ProcessFailed {
		Address address = new Address();
		address.setAddress1(passengerPreferenceDetail.getAddress());
		address.setAddressType(passengerPreferenceDetail.getAddressType());
		address.setCreatedAt(new Date());
		UUID addressId = addressDao.save(address);
		PassengerPreference passengerPreference = new PassengerPreference();
		Address addressObject = new Address();
		addressObject.setId(addressId);
		passengerPreference.setFkeyAddressId(addressObject);
		Passenger passenger = new Passenger();
		passenger.setId(UUID.fromString(passengerPreferenceDetail.getPassengerId()));
		passengerPreference.setFkeyPassengerId(passenger);
		passengerPreference.setCreatedAt(new Date());
		passengerPreferenceDao.save(passengerPreference);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updatePreferences(PassengerPreferenceDetail passengerPreferenceDetail) throws ProcessFailed {
		Address address = addressDao.getById(UUID.fromString(passengerPreferenceDetail.getAddressId()));
		address.setAddress1(passengerPreferenceDetail.getAddress());
		address.setAddressType(passengerPreferenceDetail.getAddressType());
		address.setUpdatedAt(new Date());
		 addressDao.update(address);
		
	}



	/**
	 * {@inheritDoc}
	 */
	public List<PassengerPreferenceDetail> getAllPassengerPreferenceDetailByPassengerId(UUID passengerId)
			throws ProcessFailed {
		List<PassengerPreferenceDetail> passengerPreferenceDetailList = new ArrayList<>();
		List<PassengerPreference> passengerPreferenceList = passengerPreferenceDao.getPassengerPreferenceByPassengerId(passengerId);
		for (PassengerPreference passengerPreference : passengerPreferenceList) {
			PassengerPreferenceDetail passengerPreferenceDetail = parsePassengerPassengerPreferenceDetailToPassengerPreferenceDetail(passengerPreference);
			passengerPreferenceDetailList.add(passengerPreferenceDetail);
		}
		return null;
	}


	
	private PassengerPreferenceDetail parsePassengerPassengerPreferenceDetailToPassengerPreferenceDetail(PassengerPreference passengerPreference) throws ProcessFailed {
		PassengerPreferenceDetail passengerPreferenceDetail = new PassengerPreferenceDetail();
		passengerPreferenceDetail.setAddress(passengerPreference.getFkeyAddressId().getAddress1());
		passengerPreferenceDetail.setAddressType(passengerPreference.getFkeyAddressId().getAddressType());
		passengerPreferenceDetail.setAddressId(passengerPreference.getFkeyAddressId().getId().toString());
		passengerPreferenceDetail.setPassengerId(passengerPreference.getFkeyPassengerId().getId().toString());

		return passengerPreferenceDetail;
	}


}
