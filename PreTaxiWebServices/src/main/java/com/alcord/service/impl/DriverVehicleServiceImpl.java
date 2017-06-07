package com.alcord.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.DriverDao;
import com.alcord.dao.DriverVehicleMappingDao;
import com.alcord.dao.VehicleDao;
import com.alcord.dao.impl.DriverVehicleMappingDaoImpl;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Driver;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.model.Vehicle;
import com.alcord.modelmappers.VehicleDetail;
import com.alcord.service.DriverVehicleService;
import com.alcord.service.GalleryService;

@Service
@Transactional
public class DriverVehicleServiceImpl extends BaseServiceImpl implements DriverVehicleService {

	@Autowired
	private DriverDao driverDao;
	@Autowired
	private DriverVehicleMappingDao driverVehicleMappingDao;
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private GalleryService galleryService;

	/**
	 * {@inheritDoc}
	 */
	public List<VehicleDetail> getAllApprovedVehicleDetail(String driverId) throws ProcessFailed {
		Driver driver = driverDao.getById(UUID.fromString(driverId));
		List<VehicleDetail> vehicleDetailList = new ArrayList<>();
		if (driver != null) {
			List<DriverVehicleMapping> driverVehicleMappingList = driverVehicleMappingDao
					.getAllDriverVehicleByDriverIdAndVehicleStatus(driver.getId(), true);
			for (DriverVehicleMapping driverVehicleMapping : driverVehicleMappingList) {
				VehicleDetail vehicleDetail = parseVehicleTOVehicleDetail(driverVehicleMapping.getFkeyVehicleId());
				vehicleDetailList.add(vehicleDetail);
			}
		}
		return vehicleDetailList;
	}

	public List<VehicleDetail> getAllDriverVehicleByDriverId(UUID id) throws ProcessFailed {
		List<VehicleDetail> vehicleDetailsList = new ArrayList<VehicleDetail>();
		try {
			List<DriverVehicleMapping> driverVehicleMappingList = driverVehicleMappingDao
					.getAllDriverVehicleByDriverId(id);

			if (driverVehicleMappingList != null) {
				for (int i = 0; i < driverVehicleMappingList.size(); i++) {
					VehicleDetail vehicleDetails = new VehicleDetail();
					vehicleDetails.setId(driverVehicleMappingList.get(i).getFkeyVehicleId().getId().toString());
					vehicleDetails.setApproved(driverVehicleMappingList.get(i).getFkeyVehicleId().getApproved());
					vehicleDetails
							.setChassisNumber(driverVehicleMappingList.get(i).getFkeyVehicleId().getChassisNumber());
					vehicleDetails
							.setEngineNumber(driverVehicleMappingList.get(i).getFkeyVehicleId().getEngineNumber());
					vehicleDetails
							.setNumberOfSeat(driverVehicleMappingList.get(i).getFkeyVehicleId().getNumberOfSeat());
					vehicleDetails.setRegistrationNumber(
							driverVehicleMappingList.get(i).getFkeyVehicleId().getRegistrationNumber());
					vehicleDetails
							.setVehicleColour(driverVehicleMappingList.get(i).getFkeyVehicleId().getVehicleColour());
					vehicleDetails
							.setVehicleModel(driverVehicleMappingList.get(i).getFkeyVehicleId().getVehicleModel());
					vehicleDetails.setVehicleType(driverVehicleMappingList.get(i).getFkeyVehicleId().getVehicleType());
					vehicleDetails.setFitnessValidity(driverVehicleMappingList.get(i).getFkeyVehicleId().getFitnessValidity());
					vehicleDetails.setInsurancePolicyNumber(driverVehicleMappingList.get(i).getFkeyVehicleId().getInsurancePolicyNumber());
					vehicleDetails.setInsuranceValidity(driverVehicleMappingList.get(i).getFkeyVehicleId().getInsuranceValidity());
					vehicleDetails.setPermitNumber(driverVehicleMappingList.get(i).getFkeyVehicleId().getPermitNumber());
					vehicleDetails.setPermitValidity(driverVehicleMappingList.get(i).getFkeyVehicleId().getPermitValidity());
					vehicleDetails.setVehicleBrand(driverVehicleMappingList.get(i).getFkeyVehicleId().getVehicleBrand());
					
					List<String> vehiclePhotoList = galleryService.getVehiclePictures(driverVehicleMappingList.get(i).getFkeyVehicleId().getId().toString());
					
					vehicleDetails.setVehiclePhoto(vehiclePhotoList);
					
					vehicleDetailsList.add(vehicleDetails);
				}
			}
			return vehicleDetailsList;
		} catch (Exception ex) {
			throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[] {}, Locale.US));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VehicleDetail> getAllVehicleDetail() throws ProcessFailed {
		List<VehicleDetail> vehicleDetailList = new ArrayList<>();
		List<Vehicle> vehicleList = vehicleDao.getAllVehicle();
		for (Vehicle vehicle : vehicleList) {
			VehicleDetail vehicleDetail = parseVehicleTOVehicleDetail(vehicle);
			vehicleDetailList.add(vehicleDetail);
		}
		return vehicleDetailList;
	}

	/**
	 * {@inheritDoc}
	 */
	public VehicleDetail getVehicleDetail(UUID vehicleId) throws ProcessFailed {
		Vehicle vehicle = vehicleDao.getById(vehicleId);
		VehicleDetail vehicleDetail = parseVehicleTOVehicleDetail(vehicle);
		return vehicleDetail;
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveVehicleDetail(UUID driverId, VehicleDetail vehicleDetail) throws ProcessFailed {
		Vehicle vehicle = null;
		vehicle = parseVehicleDetailTOVehicle(vehicleDetail, vehicle);
		vehicle.setCreatedAt(new Date());
		UUID vehicleId = vehicleDao.save(vehicle);

		DriverVehicleMapping driverVehicleMapping = new DriverVehicleMapping();
		Driver driverObject = new Driver();
		driverObject.setId(driverId);
		driverVehicleMapping.setFkeyDriverId(driverObject);
		Vehicle vehicleObject = new Vehicle();
		vehicleObject.setId(vehicleId);
		driverVehicleMapping.setFkeyVehicleId(vehicleObject);
		driverVehicleMapping.setCreatedAt(new Date());
		driverVehicleMapping.setIsCurrentVehicle(false);
		driverVehicleMappingDao.save(driverVehicleMapping);

	}

	/**
	 * {@inheritDoc}
	 */
	public void updateVehicleDetail(UUID driverId, VehicleDetail vehicleDetail) throws ProcessFailed {

		Vehicle vehicle = vehicleDao.getById(UUID.fromString(vehicleDetail.getId()));
		if (vehicle == null)
			throw new ProcessFailed(
					messageSource.getMessage("vehicle_error_retrieving_message", new String[] {}, Locale.US));
		vehicle = parseVehicleDetailTOVehicle(vehicleDetail, vehicle);
		vehicle.setUpdatedAt(new Date());
		vehicleDao.update(vehicle);

	}

	private VehicleDetail parseVehicleTOVehicleDetail(Vehicle vehicle) throws ProcessFailed {
		VehicleDetail vehicleDetail = new VehicleDetail();
		vehicleDetail.setEngineNumber(vehicle.getEngineNumber());
		vehicleDetail.setNumberOfSeat(vehicle.getNumberOfSeat());
		vehicleDetail.setApproved(vehicle.getApproved());
		vehicleDetail.setChassisNumber(vehicle.getChassisNumber());
		vehicleDetail.setVehicleColour(vehicle.getVehicleColour());
		vehicleDetail.setEngineNumber(vehicle.getEngineNumber());
		vehicleDetail.setVehicleModel(vehicle.getVehicleModel());
		vehicleDetail.setRegistrationNumber(vehicle.getRegistrationNumber());
		vehicleDetail.setVehicleType(vehicle.getVehicleType());
		vehicleDetail.setId(vehicle.getId().toString());
		vehicleDetail.setNumberOfSeat(vehicle.getNumberOfSeat());
		vehicleDetail.setFitnessValidity(vehicle.getFitnessValidity());
		vehicleDetail.setInsurancePolicyNumber(vehicle.getInsurancePolicyNumber());
		vehicleDetail.setInsuranceValidity(vehicle.getInsuranceValidity());
		vehicleDetail.setPermitNumber(vehicle.getPermitNumber());
		vehicleDetail.setPermitValidity(vehicle.getPermitValidity());
		vehicleDetail.setVehicleBrand(vehicle.getVehicleBrand());

		return vehicleDetail;

	}
	private Vehicle parseVehicleDetailTOVehicle(VehicleDetail VehicleDetail) throws ProcessFailed {
		Vehicle vehicle = new Vehicle();
		vehicle.setEngineNumber(VehicleDetail.getEngineNumber());
		vehicle.setNumberOfSeat(VehicleDetail.getNumberOfSeat());
		vehicle.setApproved(VehicleDetail.getApproved());
		vehicle.setChassisNumber(VehicleDetail.getChassisNumber());
		vehicle.setVehicleColour(VehicleDetail.getVehicleColour());
		vehicle.setEngineNumber(VehicleDetail.getEngineNumber());
		vehicle.setVehicleModel(VehicleDetail.getVehicleModel());
		vehicle.setRegistrationNumber(VehicleDetail.getRegistrationNumber());
		vehicle.setVehicleType(VehicleDetail.getVehicleType());
		vehicle.setVehicleBrand(VehicleDetail.getBrand());
		vehicle.setInsurancePolicyNumber(VehicleDetail.getInsurancePolicyNumber());
		vehicle.setInsuranceValidity(VehicleDetail.getInsuranceValidity());
		vehicle.setPermitNumber(VehicleDetail.getPermitNumber());
		vehicle.setPermitValidity(VehicleDetail.getPermitValidity());
		return vehicle;
	}
	private Vehicle parseVehicleDetailTOVehicle(VehicleDetail vehicleDetail, Vehicle vehicle) throws ProcessFailed {
		if (vehicle == null) {
			vehicle = new Vehicle();
			vehicle.setCreatedAt(new Date());
		}
		if (vehicleDetail.getEngineNumber() != null)
			vehicle.setEngineNumber(vehicleDetail.getEngineNumber());
		if (vehicleDetail.getNumberOfSeat() != null)
			vehicle.setNumberOfSeat(vehicleDetail.getNumberOfSeat());
		if (vehicleDetail.getApproved() != null)
			vehicle.setApproved(vehicleDetail.getApproved());
		if (vehicleDetail.getChassisNumber() != null)
			vehicle.setChassisNumber(vehicleDetail.getChassisNumber());
		if (vehicleDetail.getVehicleColour() != null)
			vehicle.setVehicleColour(vehicleDetail.getVehicleColour());
		if (vehicleDetail.getFitnessValidity() != null)
			vehicle.setFitnessValidity(vehicleDetail.getFitnessValidity());
		if (vehicleDetail.getInsurancePolicyNumber() != null)
			vehicle.setInsurancePolicyNumber(vehicleDetail.getInsurancePolicyNumber());
		if (vehicleDetail.getInsuranceValidity() != null)
			vehicle.setInsuranceValidity(vehicleDetail.getInsuranceValidity());
		if (vehicleDetail.getPermitNumber() != null)
			vehicle.setPermitNumber(vehicleDetail.getPermitNumber());
		if (vehicleDetail.getPermitValidity() != null)
			vehicle.setPermitValidity(vehicleDetail.getPermitValidity());
		if (vehicleDetail.getVehicleBrand() != null)
			vehicle.setVehicleBrand(vehicleDetail.getVehicleBrand());
		if (vehicleDetail.getVehicleModel() != null)
			vehicle.setVehicleModel(vehicleDetail.getVehicleModel());
		if (vehicleDetail.getVehicleType() != null)
			vehicle.setVehicleType(vehicleDetail.getVehicleType());

		return vehicle;

	}

}
