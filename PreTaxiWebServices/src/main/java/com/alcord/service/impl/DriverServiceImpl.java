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
import com.alcord.dao.DriverAccountVehicleDao;
import com.alcord.dao.DriverDao;
import com.alcord.dao.DriverLocationDao;
import com.alcord.dao.DriverSlabDao;
import com.alcord.dao.DriverVehicleMappingDao;
import com.alcord.dao.MasterSlabDao;
import com.alcord.dao.TripDao;
import com.alcord.enums.DriverLoginStatus;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.Address;
import com.alcord.model.City;
import com.alcord.model.Driver;
import com.alcord.model.DriverLocation;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.model.MasterSlab;
import com.alcord.model.State;
import com.alcord.model.Vehicle;
import com.alcord.modelmappers.AddressDetail;
import com.alcord.modelmappers.DriverAccountDetail;
import com.alcord.modelmappers.DriverDetail;
import com.alcord.modelmappers.DriverLocationDetail;
import com.alcord.modelmappers.DriverSlabDetail;
import com.alcord.modelmappers.DriverStatus;
import com.alcord.modelmappers.DriverTodayDetail;
import com.alcord.modelmappers.UpdateDriverAccount;
import com.alcord.modelmappers.VehicleDetail;
import com.alcord.service.DriverService;
import com.alcord.utility.Dummydata;
import com.alcord.view.DriverAccountVehicle;

@Service
@Transactional
public class DriverServiceImpl extends BaseServiceImpl implements DriverService {

	@Autowired
	private DriverDao driverDao;
	@Autowired
	private DriverVehicleMappingDao driverVehicleMappingDao;
	@Autowired
	private DriverLocationDao driverLocationDao;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MasterSlabDao masterSlabDao;
	@Autowired
	private DriverSlabDao driverSlabDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private TripDao tripDao;
	@Autowired
	private DriverAccountVehicleDao driverAccountVehicleDao;

	/**
	 * {@inheritDoc}
	 */
	public List<DriverStatus> getAllDriverStatus() throws ProcessFailed {
		tripDao.getAllAvailableDriverDetails();
		return Dummydata.getAllDriverStatusData();
	}

	/**
	 * {@inheritDoc}
	 */
	public DriverTodayDetail getDriverTodayDetail(UUID driverId) throws ProcessFailed {

		return Dummydata.getDriverTodayDetailData();
	}

	/**
	 * {@inheritDoc}
	 */
	public DriverAccountDetail getDriverAccountDetailsByAccountId(UUID accountId) throws ProcessFailed {
		DriverAccountDetail driverAccountDetail = new DriverAccountDetail();
		try {
			Driver driver = driverDao.getByAccountId(accountId);
			if (driver != null) {
				DriverDetail driverDetail = new DriverDetail();
				List<AddressDetail> AddressDetailList = new ArrayList<>();
				if (driver.getFkeyPresentAddressId() != null) {
					AddressDetail presentAddress = new AddressDetail();
					presentAddress.setAddress1(driver.getFkeyPresentAddressId().getAddress1());
					presentAddress.setAddress2(driver.getFkeyPresentAddressId().getAddress2());
					presentAddress.setCity(driver.getFkeyPresentAddressId().getCity());
					presentAddress.setState(driver.getFkeyPresentAddressId().getState());
					presentAddress.setZipcode(driver.getFkeyPresentAddressId().getZipcode());
					presentAddress.setCountry(driver.getFkeyPresentAddressId().getCountry());
				}
				if (driver.getFkeyPermanentAddressId() != null) {
					AddressDetail permanentAddress = new AddressDetail();
					permanentAddress.setAddress1(driver.getFkeyPermanentAddressId().getAddress1());
					permanentAddress.setAddress2(driver.getFkeyPermanentAddressId().getAddress2());
					permanentAddress.setCity(driver.getFkeyPermanentAddressId().getCity());
					permanentAddress.setState(driver.getFkeyPermanentAddressId().getState());
					permanentAddress.setZipcode(driver.getFkeyPermanentAddressId().getZipcode());
					permanentAddress.setCountry(driver.getFkeyPermanentAddressId().getCountry());

				}
				if (driver.getFirstName() != null) {
					driverDetail.setFirstName(driver.getFirstName());
				}
				if (driver.getLastName() != null) {
					driverDetail.setLastName(driver.getLastName());
				}
				if (driver.getCurrentStatus() != null) {
					driverDetail.setCurrentStatus(driver.getCurrentStatus());
				}
				if (driver.getId() != null) {
					driverDetail.setId(driver.getId().toString());
				}
				if (driver.getRating() != null) {
					driverDetail.setRating(driver.getRating());
				}
				if (driver.getBadgeNumber() != null) {
					driverDetail.setBadgeNumber(driver.getBadgeNumber().toString());
				}
				/*if (driver.getBankAccountNumber() != null) {
					driverDetail.setBankAccountNumber(driver.getBankAccountNumber());
				}*/
				if (driver.getPanNumber() != null) {
					driverDetail.setPanNumber(driver.getPanNumber().toString());
				}
				driverAccountDetail.setDriverDetail(driverDetail);
			}
			AddressDetail permanentAddressDetail = new AddressDetail();
			permanentAddressDetail.setAddress1(driver.getFkeyPermanentAddressId().getAddress1());
			permanentAddressDetail.setAddress2(driver.getFkeyPermanentAddressId().getAddress2());
			permanentAddressDetail.setCity(driver.getFkeyPermanentAddressId().getCity());
			permanentAddressDetail.setState(driver.getFkeyPermanentAddressId().getState());
			permanentAddressDetail.setCountry(driver.getFkeyPermanentAddressId().getCountry());
			permanentAddressDetail.setZipcode(driver.getFkeyPermanentAddressId().getZipcode());
			driverAccountDetail.setPermanentAddress(permanentAddressDetail);
			
			AddressDetail presentAddressDetail = new AddressDetail();
			presentAddressDetail.setAddress1(driver.getFkeyPresentAddressId().getAddress1());
			presentAddressDetail.setAddress2(driver.getFkeyPermanentAddressId().getAddress2());
			presentAddressDetail.setCity(driver.getFkeyPermanentAddressId().getCity());
			presentAddressDetail.setState(driver.getFkeyPermanentAddressId().getState());
			presentAddressDetail.setCountry(driver.getFkeyPermanentAddressId().getCountry());
			presentAddressDetail.setZipcode(driver.getFkeyPermanentAddressId().getZipcode());
			driverAccountDetail.setPresentAddress(presentAddressDetail);
			
			DriverVehicleMapping driverVehicleMapping = driverVehicleMappingDao
					.getCurrentVehicleByDriverId(driver.getId());
			if (driverVehicleMapping != null) {
				VehicleDetail vehicleDetail = new VehicleDetail();
				vehicleDetail.setChassisNumber(driverVehicleMapping.getFkeyVehicleId().getChassisNumber());
				vehicleDetail.setVehicleModel(driverVehicleMapping.getFkeyVehicleId().getVehicleModel());
				vehicleDetail.setNumberOfSeat(driverVehicleMapping.getFkeyVehicleId().getNumberOfSeat());
				vehicleDetail.setRegistrationNumber(driverVehicleMapping.getFkeyVehicleId().getRegistrationNumber());
				vehicleDetail.setVehicleType(driverVehicleMapping.getFkeyVehicleId().getVehicleType());
				vehicleDetail.setApproved(driverVehicleMapping.getFkeyVehicleId().getApproved());
				driverAccountDetail.setVehicleDetail(vehicleDetail);
			}

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
		return driverAccountDetail;
	}


	/**
	 * {@inheritDoc}
	 */
	public void updateStatus(DriverStatus driverStatus) throws ProcessFailed {

		Driver driver = driverDao.getByAccountId(UUID.fromString(driverStatus.getDriverId()));
		if (driverStatus.getStatusName().equalsIgnoreCase(DriverLoginStatus.ONLINE.name())) {
			driver.setCurrentStatus(DriverLoginStatus.ONLINE.name());
			// To-do
			// check driver min current point
			// document
			// driver slab
			driver.setCurrentStatus(DriverLoginStatus.ONLINE.name());

		} else if (driverStatus.getStatusName().equalsIgnoreCase(DriverLoginStatus.OFFLINE.name())) {
			driver.setCurrentStatus(DriverLoginStatus.OFFLINE.name());

		} else if (driverStatus.getStatusName().equalsIgnoreCase(DriverLoginStatus.ONTRIP.name())) {
			driver.setCurrentStatus(DriverLoginStatus.ONTRIP.name());

		}
		driverDao.update(driver);

	}

	/**
	 * {@inheritDoc}
	 */
	public void updateDriver(DriverDetail driverDetail) throws ProcessFailed {
        Driver driver = driverDao.getById(UUID.fromString(driverDetail.getId()));
        //updates driver details
       /* if (driverDetail.getBankAccountNumber() != null)
            driver.setBankAccountNumber(driverDetail.getBankAccountNumber());*/
        if (driverDetail.getIfscCode() != null)
            driver.setIfscCode(driverDetail.getIfscCode());
        if (driverDetail.getDriverLicenseNumber() != null)
            driver.setDriverLicenseNumber(driverDetail.getDriverLicenseNumber());
        if (driverDetail.getPanNumber() != null)
            driver.setPanNumber(driverDetail.getPanNumber());
        if (driverDetail.getPoliceClearanceSerialId() != null)
            driver.setPoliceClearanceSerialId(driverDetail.getPoliceClearanceSerialId());
        if (driverDetail.getAadharNumber() != null)
        	driver.setAadharNumber(driverDetail.getAadharNumber());
        if (driverDetail.getDriverLicenseValidity() != null)
        	driver.setDriverLicenseValidity(driverDetail.getDriverLicenseValidity());
        if (driverDetail.getPassportNumber() != null)
        	driver.setPassportNumber(driverDetail.getPassportNumber());
        if (driverDetail.getPassportValidity() != null)
        	driver.setPassportValidity(driverDetail.getPassportValidity());
        if (driverDetail.getVoterId() != null)
        	driver.setVoterId(driverDetail.getVoterId());
        
        // updates driver
        if (driverDetail.getBadgeNumber() != null)
            driver.setBadgeNumber(driverDetail.getBadgeNumber());
        if (driverDetail.getFirstName() != null)
            driver.setFirstName(driverDetail.getFirstName());
        if (driverDetail.getLastName() != null)
            driver.setLastName(driverDetail.getLastName());
        if (driverDetail.getStateId() != null) {
            State state = new State();
            state.setId(UUID.fromString(driverDetail.getStateId()));
            driver.setFkeyStateId(state);
        }
        driver.setUpdatedAt(new Date());
        // update addresses
        if(driverDetail.getAddress() != null) {
            for (AddressDetail addressDetail : driverDetail.getAddress()) {
				Address address = addressDao.getById(UUID.fromString(addressDetail.getId()));
				if(address != null) {
					address.setAddress1(addressDetail.getAddress1());
					address.setAddress2(addressDetail.getAddress2());
					address.setAddressType(addressDetail.getType());
					address.setCity(addressDetail.getCity());
					address.setCountry(addressDetail.getCountry());
					address.setState(addressDetail.getState());
					address.setZipcode(addressDetail.getZipcode());
					address.setUpdatedAt(new Date());
					addressDao.update(address);
				}
			}
        }
        
        driverDao.update(driver);
    }

	/**
	 * {@inheritDoc}
	 */
	public Driver getByAccountId(UUID accountId) throws ProcessFailed {
		return driverDao.getByAccountId(accountId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveCurrentLocation(DriverLocationDetail driverLocationDetail) throws ProcessFailed {
		DriverLocation driverLocation = new DriverLocation();
		Driver driver = new Driver();
		driver.setId(UUID.fromString(driverLocationDetail.getDriverId()));
		driverLocation.setFkeyDriverId(driver);
		driverLocation.setLocationLatitude(driverLocationDetail.getLocationLatitude());
		driverLocation.setLocationLongitude(driverLocationDetail.getLocationLongitude());
		driverLocation.setCreatedAt(new Date());
		driverLocationDao.save(driverLocation);

	}

	/**
	 * {@inheritDoc}
	 */
	public void saveSlab(DriverSlabDetail driverSlabDetail) throws ProcessFailed {

		/*MasterSlab masterSlab = new MasterSlab();
		masterSlab.setBaseFare(driverSlabDetail.getBaseFare());
		masterSlab.setSegmentOneDistance(driverSlabDetail.getSegmentOneDistance());
		masterSlab.setSegmentOneFare(driverSlabDetail.getSegmentOneFare());
		masterSlab.setSegmentTwoDistance(driverSlabDetail.getSegmentTwoDistance());
		masterSlab.setSegmentTwoFare(driverSlabDetail.getSegmentTwoDistance());
		masterSlab.setCostPerMin(driverSlabDetail.getCostPerMin());
		UUID masterSalbId = masterSlabDao.save(masterSlab);

		DriverSlab driverSlab = new DriverSlab();

		MasterSlab slab = new MasterSlab();
		slab.setId(masterSalbId);
		driverSlab.setFkeyMasterSlabId(slab);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(UUID.fromString(driverSlabDetail.getVehicleId()));
		driverSlab.setFkeyVehicleId(vehicle);
		Driver driver = new Driver();
		driver.setId(UUID.fromString(driverSlabDetail.getDriverId()));
		driverSlab.setFkeyDriverId(driver);
		driverSlab.setCreatedAt(new Date());
		driverSlabDao.save(driverSlab);*/

	}

	@Override
	public List<Driver> getByBadgeNumber(String badgeNumber, UUID stateId) throws ProcessFailed {
		// TODO Auto-generated method stub
		try {
			return driverDao.getByBadgeNumber(badgeNumber, stateId);

		} catch (Throwable throwable) {
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}

	}

	@Override
	public List<Driver> getByName(String name, UUID stateId) throws ProcessFailed {
		// TODO Auto-generated method stub
		try {
			return driverDao.getByName(name, stateId);

		} catch (Throwable throwable) {
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
	}	
	/**
	 * {@inheritDoc}
	 */
	public void updateAccount(UpdateDriverAccount updateDriverAccount) throws ProcessFailed {
		
		Driver driver = driverDao.getById(UUID.fromString(updateDriverAccount.getDriverDetail().getId()));
		driver.setFirstName(updateDriverAccount.getDriverDetail().getFirstName());
		driver.setLastName(updateDriverAccount.getDriverDetail().getLastName());
		driver.setUpdatedAt(new Date());
		driverDao.update(driver);
		Address permanentAddress = addressDao.getById(UUID.fromString(updateDriverAccount.getPermanentAddressDetail().getId()));
		permanentAddress.setAddress1(updateDriverAccount.getPermanentAddressDetail().getAddress1());
		permanentAddress.setAddress2(updateDriverAccount.getPermanentAddressDetail().getAddress2());
		permanentAddress.setCity(updateDriverAccount.getPermanentAddressDetail().getCity());
		permanentAddress.setState(updateDriverAccount.getPermanentAddressDetail().getState());
		permanentAddress.setCountry(updateDriverAccount.getPermanentAddressDetail().getCountry());
		permanentAddress.setZipcode(updateDriverAccount.getPermanentAddressDetail().getZipcode());
		permanentAddress.setUpdatedAt(new Date());
		addressDao.update(permanentAddress);
		Address presentAddress = addressDao.getById(UUID.fromString(updateDriverAccount.getPresentAddressDetail().getId()));
		presentAddress.setAddress1(updateDriverAccount.getPresentAddressDetail().getAddress1());
		presentAddress.setAddress2(updateDriverAccount.getPresentAddressDetail().getAddress2());
		presentAddress.setCity(updateDriverAccount.getPresentAddressDetail().getCity());
		presentAddress.setState(updateDriverAccount.getPresentAddressDetail().getState());
		presentAddress.setCountry(updateDriverAccount.getPresentAddressDetail().getCountry());
		presentAddress.setZipcode(updateDriverAccount.getPresentAddressDetail().getZipcode());
		presentAddress.setUpdatedAt(new Date());
		addressDao.update(presentAddress);
	}	

	/**
	 * {@inheritDoc}
	 */
	public DriverAccountDetail getDriverAccountDetailsBydriverId(UUID driverId) throws ProcessFailed {
		DriverAccountVehicle driverAccountVehicle = driverAccountVehicleDao.getDriverAccountVehicleByDriverId(driverId);
		DriverAccountDetail driverAccountDetail = new DriverAccountDetail();
		try {
			DriverDetail driverDetail = new DriverDetail();
			if (driverAccountVehicle.getFirstName() != null) {
				driverDetail.setFirstName(driverAccountVehicle.getFirstName());
			}
			if (driverAccountVehicle.getLastName() != null) {
				driverDetail.setLastName(driverAccountVehicle.getLastName());
			}
			if (driverAccountVehicle.getCurrentStatus() != null) {
				driverDetail.setCurrentStatus(driverAccountVehicle.getCurrentStatus());
			}
			if (driverAccountVehicle.getDriverId() != null) {
				driverDetail.setId(driverAccountVehicle.getDriverId().toString());
			}
			if (driverAccountVehicle.getRating() != null) {
				driverDetail.setRating(driverAccountVehicle.getRating());
			}
			driverAccountDetail.setDriverDetail(driverDetail);
			VehicleDetail vehicleDetail = new VehicleDetail();
			vehicleDetail.setChassisNumber(driverAccountVehicle.getChassisNumber());
			vehicleDetail.setVehicleModel(driverAccountVehicle.getVehicleModel());
			vehicleDetail.setRegistrationNumber(driverAccountVehicle.getRegistrationNumber());
			vehicleDetail.setVehicleType(driverAccountVehicle.getVehicleType());
			driverAccountDetail.setVehicleDetail(vehicleDetail);

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[] {}, Locale.US));
		}
		return driverAccountDetail;

	}

	/**
	 * {@inheritDoc}
	 */
	public Driver getById(UUID id) throws ProcessFailed {
		
		return driverDao.getById(id);
	}

}