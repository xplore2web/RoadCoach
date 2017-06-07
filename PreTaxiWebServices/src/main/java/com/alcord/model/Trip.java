/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "trip")
public class Trip implements Serializable {

   

   

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
    private UUID id;
    @Column(name = "trip_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tripStartTime;
    @Column(name = "trip_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tripEndTime;
    @Size(max = 50)
    @Column(name = "trip_type")
    private String tripType;
    @Size(max = 50)
    @Column(name = "trip_status")
    private String tripStatus;
    @Size(max = 100)
    @Column(name = "trip_start_location")
    private String tripStartLocation;
    @Size(max = 100)
    @Column(name = "trip_drop_location")
    private String tripDropLocation;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 100)
    @Column(name = "trip_start_latitude")
    private String tripStartLatitude;
    @Size(max = 100)
    @Column(name = "trip_start_longitude")
    private String tripStartLongitude;
    @Column(name = "trip_booked_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tripBookedTime;
    @Column(name = "number_of_passengers")
    private Integer numberOfPassengers;
    @Size(max = 100)
    @Column(name = "trip_end_latitude")
    private String tripEndLatitude;
    @Size(max = 100)
    @Column(name = "trip_end_longitude")
    private String tripEndLongitude;
    @Column(name = "trip_booked_accept_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tripBookedAcceptTime;
    @Size(max = 50)
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Size(max = 50)
    @Column(name = "otp")
    private String otp;
    @Column(name = "is_passenger_self")
    private Boolean isPassengerSelf;
    @Size(max = 50)
    @Column(name = "trip_passenger_name")
    private String tripPassengerName;
    @Column(name = "pickup_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupStartTime;
    @Column(name = "pickup_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupEndTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "trip_distance")
    private Double tripDistance;
    @JoinColumn(name = "fkey_driver_id", referencedColumnName = "id")
    @ManyToOne
    private Driver fkeyDriverId;
    @JoinColumn(name = "fkey_driver_slab_id", referencedColumnName = "id")
    @ManyToOne
    private DriverSlab fkeyDriverSlabId;
    @JoinColumn(name = "fkey_passenger_id", referencedColumnName = "id")
    @ManyToOne
    private Passenger fkeyPassengerId;
    @Column(name = "is_cancelled")
    private Boolean isCancelled;
    @Size(max = 50)
    @Column(name = "cancelled_by")
    private String cancelledBy;
    @JoinColumn(name = "fkey_vehicle_id", referencedColumnName = "id")
    @ManyToOne
    private Vehicle fkeyVehicleId;
    @Column(name = "booked_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedTime;
    @Column(name = "driver_accept_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date driverAcceptTime;
    @Size(max = 100)
    @Column(name = "drop_latitude")
    private String dropLatitude;
    @Size(max = 100)
    @Column(name = "drop_location")
    private String dropLocation;
    @Size(max = 100)
    @Column(name = "drop_longitude")
    private String dropLongitude;
    @Size(max = 100)
    @Column(name = "pickup_latitude")
    private String pickupLatitude;
    @Size(max = 100)
    @Column(name = "pickup_location")
    private String pickupLocation;
    @Size(max = 100)
    @Column(name = "pickup_longitude")
    private String pickupLongitude;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "fkeyTripId")
    private Collection<Billing> billingCollection;

    public Trip() {
    }

   
    public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public Date getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(Date tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public Date getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(Date tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getTripStartLocation() {
        return tripStartLocation;
    }

    public void setTripStartLocation(String tripStartLocation) {
        this.tripStartLocation = tripStartLocation;
    }

    public String getTripDropLocation() {
        return tripDropLocation;
    }

    public void setTripDropLocation(String tripDropLocation) {
        this.tripDropLocation = tripDropLocation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTripStartLatitude() {
        return tripStartLatitude;
    }

    public void setTripStartLatitude(String tripStartLatitude) {
        this.tripStartLatitude = tripStartLatitude;
    }

    public String getTripStartLongitude() {
        return tripStartLongitude;
    }

    public void setTripStartLongitude(String tripStartLongitude) {
        this.tripStartLongitude = tripStartLongitude;
    }

    public Date getTripBookedTime() {
        return tripBookedTime;
    }

    public void setTripBookedTime(Date tripBookedTime) {
        this.tripBookedTime = tripBookedTime;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getTripEndLatitude() {
        return tripEndLatitude;
    }

    public void setTripEndLatitude(String tripEndLatitude) {
        this.tripEndLatitude = tripEndLatitude;
    }

    public String getTripEndLongitude() {
        return tripEndLongitude;
    }

    public void setTripEndLongitude(String tripEndLongitude) {
        this.tripEndLongitude = tripEndLongitude;
    }

    public Date getTripBookedAcceptTime() {
        return tripBookedAcceptTime;
    }

    public void setTripBookedAcceptTime(Date tripBookedAcceptTime) {
        this.tripBookedAcceptTime = tripBookedAcceptTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Boolean getIsPassengerSelf() {
        return isPassengerSelf;
    }

    public void setIsPassengerSelf(Boolean isPassengerSelf) {
        this.isPassengerSelf = isPassengerSelf;
    }

    public String getTripPassengerName() {
        return tripPassengerName;
    }

    public void setTripPassengerName(String tripPassengerName) {
        this.tripPassengerName = tripPassengerName;
    }

    public Date getPickupStartTime() {
        return pickupStartTime;
    }

    public void setPickupStartTime(Date pickupStartTime) {
        this.pickupStartTime = pickupStartTime;
    }

    public Date getPickupEndTime() {
        return pickupEndTime;
    }

    public void setPickupEndTime(Date pickupEndTime) {
        this.pickupEndTime = pickupEndTime;
    }

    public Double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public Driver getFkeyDriverId() {
        return fkeyDriverId;
    }

    public void setFkeyDriverId(Driver fkeyDriverId) {
        this.fkeyDriverId = fkeyDriverId;
    }

    public DriverSlab getFkeyDriverSlabId() {
        return fkeyDriverSlabId;
    }

    public void setFkeyDriverSlabId(DriverSlab fkeyDriverSlabId) {
        this.fkeyDriverSlabId = fkeyDriverSlabId;
    }

    public Passenger getFkeyPassengerId() {
        return fkeyPassengerId;
    }

    public void setFkeyPassengerId(Passenger fkeyPassengerId) {
        this.fkeyPassengerId = fkeyPassengerId;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }


	public Vehicle getFkeyVehicleId() {
		return fkeyVehicleId;
	}


	public void setFkeyVehicleId(Vehicle fkeyVehicleId) {
		this.fkeyVehicleId = fkeyVehicleId;
	}

    public Date getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(Date bookedTime) {
        this.bookedTime = bookedTime;
    }

    public Date getDriverAcceptTime() {
        return driverAcceptTime;
    }

    public void setDriverAcceptTime(Date driverAcceptTime) {
        this.driverAcceptTime = driverAcceptTime;
    }

    public String getDropLatitude() {
        return dropLatitude;
    }

    public void setDropLatitude(String dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getDropLongitude() {
        return dropLongitude;
    }

    public void setDropLongitude(String dropLongitude) {
        this.dropLongitude = dropLongitude;
    }

    public String getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(String pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(String pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
