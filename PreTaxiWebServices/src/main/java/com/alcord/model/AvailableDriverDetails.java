package com.alcord.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author Haider-Khan
 * 
 */

@Entity
@Table(name = "available_driver_details")
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "getDriverInPassengerArea",
            query = "SELECT*FROM driver_in_passenger_area(:latitude,:longitude,:radius)",
            resultClass = AvailableDriverDetails.class
    )
})
public class AvailableDriverDetails implements Serializable {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")   
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
	private UUID id;
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Size(max = 100)
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 50)
    @Column(name = "photo")
    private String photo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private Double rating;
    @Size(max = 100)
    @Column(name = "latitude")
    private String locationLatitude;
    @Size(max = 100)
    @Column(name = "longitude")
    private String locationLongitude;
    @Size(max = 50)
    @Column(name = "vehicle_model")
    private String vehicleModel;
    @Size(max = 50)
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Lob
    @Column(name = "vehicle_photo")
    private Object vehiclePhoto;
//    @Lob
//    @Column(name = "fkey_driver_slab_id")
    @Column(columnDefinition = "BINARY(16)", name = "fkey_driver_slab_id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
    private UUID fkeyDriverSlabId;

    public AvailableDriverDetails() {
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Object getVehiclePhoto() {
        return vehiclePhoto;
    }

    public void setVehiclePhoto(Object vehiclePhoto) {
        this.vehiclePhoto = vehiclePhoto;
    }

	public UUID getFkeyDriverSlabId() {
		return fkeyDriverSlabId;
	}

	public void setFkeyDriverSlabId(UUID fkeyDriverSlabId) {
		this.fkeyDriverSlabId = fkeyDriverSlabId;
	}

   public String toString(){
	   
	   return "[ "+getId().toString()+", "+getFirstName()+", "+getLastName()+", "+getFirstName()+", "+getPhoto()
	           +", "+getRating()+", "+getLocationLatitude()+", "+getLocationLongitude()+", "+getVehicleType()
	           +", "+getVehicleModel()+", "+getVehiclePhoto()+", "+getFkeyDriverSlabId().toString() +" ]";
   }
    
    
}