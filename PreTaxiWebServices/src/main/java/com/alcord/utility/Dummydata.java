package com.alcord.utility;

import java.util.ArrayList;
import java.util.List;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.modelmappers.DriverAccountDetail;
import com.alcord.modelmappers.DriverDetail;
import com.alcord.modelmappers.DriverStatus;
import com.alcord.modelmappers.DriverTodayDetail;
import com.alcord.modelmappers.PassengerDetail;
import com.alcord.modelmappers.PassengerPreferences;
import com.alcord.modelmappers.RatingDetail;
import com.alcord.modelmappers.TripDetail;
import com.alcord.modelmappers.VehicleDetail;

public class Dummydata {

	public static DriverTodayDetail getDriverTodayDetailData() throws ProcessFailed {
		DriverTodayDetail driverTodayDetail = new DriverTodayDetail();
		driverTodayDetail.setNumberOftrips(12);
		driverTodayDetail.setOnlineHours(14.0);
		driverTodayDetail.setTotalEarned(5000.00);

		return driverTodayDetail;
	}

	public static List<DriverStatus> getAllDriverStatusData() throws ProcessFailed {
		List<DriverStatus> driverStatusList = new ArrayList<>();

		for (int i = 0; i <= 2; i++) {
			DriverStatus driverStatus = new DriverStatus();
			/*if (i == 0) {
				driverStatus.setId("11111");
				driverStatus.setStatusName(DriverLoginStatus.ONLINE.name());
				driverStatusList.add(driverStatus);
			} else if (i == 1) {
				driverStatus.setId("22222");
				driverStatus.setStatusName(DriverLoginStatus.OFFLINE.name());
				driverStatusList.add(driverStatus);
			} else if (i == 2) {
				driverStatus.setId("33333");
				driverStatus.setStatusName(DriverLoginStatus.ONTRIP.name());
				driverStatusList.add(driverStatus);
			}*/
		}
		return driverStatusList;
	}
	
	public static List<VehicleDetail> getAllVehicleDetailData() {

		List<VehicleDetail> vehicleDetailList = new ArrayList<>();
		VehicleDetail vehicleDetail = new VehicleDetail();
		vehicleDetail.setRegistrationNumber("KA02JB4616");
		vehicleDetail.setChassisNumber("MEG4NBBN8GN5NMUI993");
		vehicleDetail.setEngineNumber("NVVJ5903593");
		vehicleDetail.setVehicleType("MINI");
		vehicleDetail.setVehicleModel("Renault");
		vehicleDetail.setVehicleColour("White");
		vehicleDetail.setVehicleModel("Renault Duster");
		vehicleDetailList.add(vehicleDetail);
		return vehicleDetailList;

	}
	
	public static VehicleDetail getVehicleDetailData() {

		VehicleDetail vehicleDetail = new VehicleDetail();
		vehicleDetail.setRegistrationNumber("KA02JB4616");
		vehicleDetail.setChassisNumber("MEG4NBBN8GN5NMUI993");
		vehicleDetail.setEngineNumber("NVVJ5903593");
		vehicleDetail.setVehicleType("MINI");
		vehicleDetail.setVehicleModel("Renault");
		vehicleDetail.setVehicleColour("White");
		vehicleDetail.setVehicleModel("Renault Duster");
		return vehicleDetail;

	}
	
	public static List<TripDetail> getAllRides(String startDate, String endDate) {
		List<TripDetail> tripDetailList = new ArrayList<>();
		TripDetail tripDetail = new TripDetail();
		// tripDetail.setId("11111");
		// tripDetail.setfKeyDriverId("11111");
		// tripDetail.setfKeyPassengerId("11111");
		// tripDetail.setStartDate(new Date());
		// tripDetail.setEndDate(new Date());
		// tripDetail.setVehicleType("Tata Nano");
		// tripDetail.setRideType("Ride Now");
		// tripDetail.setStatus("Complete");
		// tripDetail.setPickupLocation("Start location");
		// tripDetail.setDropLocation("End location");
		tripDetailList.add(tripDetail);
		return tripDetailList;
	}
	
	public static List<TripDetail> getAllRides(String startDate, String endDate, Account account) {
		List<TripDetail> tripDetailList = new ArrayList<>();
		TripDetail tripDetail = new TripDetail();
		// tripDetail.setId("11111");
		// tripDetail.setfKeyDriverId("11111");
		// tripDetail.setfKeyPassengerId("11111");
		// tripDetail.setStartDate(new Date());
		// tripDetail.setEndDate(new Date());
		// tripDetail.setVehicleType("Tata Nano");
		// tripDetail.setRideType("Ride Now");
		// tripDetail.setStatus("Complete");
		// tripDetail.setPickupLocation("Start location");
		// tripDetail.setDropLocation("End location");
		tripDetailList.add(tripDetail);
		return tripDetailList;
	}
	
	public static TripDetail getTripDetail(Account account) {
		TripDetail tripDetail = new TripDetail();
		// tripDetail.setId("11111");
		// tripDetail.setfKeyDriverId("11111");
		// tripDetail.setfKeyPassengerId("11111");
		// tripDetail.setStartDate(new Date());
		// tripDetail.setEndDate(new Date());
		// tripDetail.setVehicleType("Tata Nano");
		// tripDetail.setRideType("Ride Now");
		// tripDetail.setStatus("Complete");
		// tripDetail.setPickupLocation("Start location");
		// tripDetail.setDropLocation("End location");
		return tripDetail;
	}
	
	public static TripDetail getTripDetail(Integer id) {
		TripDetail tripDetail = new TripDetail();
		// tripDetail.setId("11111");
		// tripDetail.setfKeyDriverId("11111");
		// tripDetail.setfKeyPassengerId("11111");
		// tripDetail.setStartDate(new Date());
		// tripDetail.setEndDate(new Date());
		// tripDetail.setVehicleType("Tata Nano");
		// tripDetail.setRideType("Ride Now");
		// tripDetail.setStatus("Complete");
		// tripDetail.setPickupLocation("Start location");
		// tripDetail.setDropLocation("End location");
		return tripDetail;
	}
	
	public static List<RatingDetail> getAllRatings(Account account) {
		List<RatingDetail> ratingDetailList = new ArrayList<>();
		RatingDetail ratingDetail = new RatingDetail();
//		ratingDetail.setId("11111");
//		ratingDetail.setPassengerId("11111");
//		ratingDetail.setDriverId("11111");
//		ratingDetail.setRideId("11111");
//		ratingDetail.setStars(4);
//		ratingDetail.setType("Driver");
//		ratingDetail.setComment("None");
		/*ratingDetail.setId("11111");
		ratingDetail.setPassengerId("11111");
		ratingDetail.setDriverId("11111");
		ratingDetail.setRideId("11111");
		ratingDetail.setStars(4);
		ratingDetail.setType("Driver");
		ratingDetail.setComment("None");*/
		ratingDetailList.add(ratingDetail);
		return ratingDetailList;
	}
	
	public static RatingDetail getRatingById(Integer id) {
		RatingDetail ratingDetail = new RatingDetail();
//		ratingDetail.setId("11111");
//		ratingDetail.setPassengerId("11111");
//		ratingDetail.setDriverId("11111");
//		ratingDetail.setRideId("11111");
//		ratingDetail.setStars(5);
//		ratingDetail.setType("Driver");
//		ratingDetail.setComment("None");
		/*ratingDetail.setId("11111");
		ratingDetail.setPassengerId("11111");
		ratingDetail.setDriverId("11111");
		ratingDetail.setRideId("11111");
		ratingDetail.setStars(5);
		ratingDetail.setType("Driver");
		ratingDetail.setComment("None");*/
		return ratingDetail;
	}

	public static DriverAccountDetail getDriverAccountDetail() {
		DriverAccountDetail driverAccountDetail = new DriverAccountDetail();
		driverAccountDetail.setDriverDetail(getDriverAccountDetail(null));
		driverAccountDetail.setVehicleDetail(getVehicleDetailData());
		return driverAccountDetail;
	}
	public static DriverDetail getDriverAccountDetail(Account account) {
		DriverDetail driverDetail = new DriverDetail();
		//driverDetail.setAddress("GVR Palaza, 2nd Floor, LR Bande, Bangalore - 560032");
		driverDetail.setCurrentStatus("Online");
		driverDetail.setFirstName("Murli");
		driverDetail.setLastName("Prasad");
		driverDetail.setPhoto("http://www.psdgraphics.com/file/taxi-driver-icon.jpg");
		driverDetail.setRating(4.5);
		return driverDetail;
	}
	
	public static PassengerPreferences getAccountPreferences(Account account) {
		PassengerPreferences passengerPreferences = new PassengerPreferences("11111", "11111", "11111", true, false);
		return passengerPreferences;
	}
	
	public static PassengerDetail getPassengerProfile(Account account) {
		PassengerDetail passengerDetail = new PassengerDetail("11111", "11111", "Rohit", "Sharma", "http://img.freepik.com/free-icon/businessman_318-72886.jpg?size=338c&ext=jpg");
		return passengerDetail;
		
	}

}
