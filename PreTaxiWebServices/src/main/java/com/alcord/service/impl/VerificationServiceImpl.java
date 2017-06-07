package com.alcord.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.AccountCityStateMappingDao;
import com.alcord.dao.AddressDao;
import com.alcord.dao.DriverVehicleMappingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.Address;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.model.Vehicle;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.modelmappers.AddressDetail;
import com.alcord.modelmappers.DriverDetail;
import com.alcord.modelmappers.VehicleDetail;
import com.alcord.service.AccountService;
import com.alcord.service.EmailProviderService;
import com.alcord.service.VehicleService;
import com.alcord.service.VerificationService;
import com.alcord.utility.Authorities;
import com.alcord.utility.GeneratePassword;

@Service
@Transactional
public class VerificationServiceImpl implements VerificationService {

	@Autowired
	AccountService accountService;

	@Autowired
	private EmailProviderService emailProviderService;

	@Autowired
	private DriverVehicleMappingDao driverVehicleMappingDao;

	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private AccountCityStateMappingDao accountCityStateMappingDao;

	@Override
	public void saveDriver(DriverDetail driverDetail) throws ProcessFailed {
		AccountDetail accountDetail = parseDriverDetailToAccountDetail(driverDetail);

		accountService.save(accountDetail);

	}

	@Override
	public List<DriverVehicleMapping> getAllVehiclesByDriverId(String driverId) throws ProcessFailed {
		return driverVehicleMappingDao.getAllVehiclesByDriverId(UUID.fromString(driverId));
	}

	private AccountDetail parseDriverDetailToAccountDetail(DriverDetail driverDetail) {
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setEmailAddress(driverDetail.getEmailAddress());
		accountDetail.setFirstName(driverDetail.getFirstName());
		accountDetail.setLastName(driverDetail.getLastName());
		accountDetail.setPhone(driverDetail.getPhone());
		accountDetail.setBadgeNumber(driverDetail.getBadgeNumber());
		if(driverDetail.getCityId()!= null){
			accountDetail.setFkeyCityId(UUID.fromString(driverDetail.getCityId()));			
		}
		accountDetail.setFkeyStateId(UUID.fromString(driverDetail.getStateId()));
		
		accountDetail.setAccountPassword(driverDetail.getPassword());
		accountDetail.setAccountRole(Authorities.ROLE_DRIVER.name());
		

		List<UUID> addressIdList = new ArrayList<>();
		for (AddressDetail addressDetail : driverDetail.getAddress()) {

			Address address = new Address();
			address.setAddress1(addressDetail.getAddress1());
			address.setAddress2(addressDetail.getAddress2());
			address.setCity(addressDetail.getCity());
			address.setState(addressDetail.getState());
			address.setCountry(addressDetail.getCountry());
			address.setZipcode(addressDetail.getZipcode());
			address.setAddressType(addressDetail.getType());
			address.setCreatedAt(new Date());
			addressIdList.add(addressDao.save(address));
		}
		if (!addressIdList.isEmpty()) {
			accountDetail.setFkAddressId1(addressIdList.get(0));
			accountDetail.setFkAddressId2(addressIdList.get(1));
		}
		
		
		return accountDetail;
	}
	
	public List<AccountCityStateMapping> getByCityId(UUID cityId) throws ProcessFailed {
		return accountCityStateMappingDao.getByCityId(cityId);
	}

}
