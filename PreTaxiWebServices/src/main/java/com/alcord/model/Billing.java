/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "billing")
public class Billing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "base_fare")
    private Double baseFare;
    @Column(name = "distance_fare")
    private Double distanceFare;
    @Column(name = "trip_time_fare")
    private Double tripTimeFare;
    @Column(name = "parking_fare")
    private Double parkingFare;
    @Size(max = 50)
    @Column(name = "payment_mode")
    private String paymentMode;
    @Column(name = "toll_fare")
    private Double tollFare;
    @Column(name = "additional_fare")
    private Double additionalFare;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "total_bill")
    private Double totalBill;
    @Column(name = "cancellation_fare")
    private Double cancellationFare;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "fkey_company_tax_id", referencedColumnName = "id")
    @ManyToOne
    private CompanyTax fkeyCompanyTaxId;
    @JoinColumn(name = "fkey_driver_tax_id", referencedColumnName = "id")
    @ManyToOne
    private DriverTax fkeyDriverTaxId;
    @JoinColumn(name = "fkey_payment_mode_id", referencedColumnName = "id")
    @ManyToOne
    private PaymentMode fkeyPaymentModeId;
    @JoinColumn(name = "fkey_trip_id", referencedColumnName = "id")
    @ManyToOne
    private Trip fkeyTripId;
    @OneToMany(mappedBy = "fkeyBillingId")
    private Collection<DriverBilling> driverBillingCollection;

    public Billing() {
    }

    public Billing(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }

    public Double getDistanceFare() {
        return distanceFare;
    }

    public void setDistanceFare(Double distanceFare) {
        this.distanceFare = distanceFare;
    }

    public Double getTripTimeFare() {
        return tripTimeFare;
    }

    public void setTripTimeFare(Double tripTimeFare) {
        this.tripTimeFare = tripTimeFare;
    }

    public Double getParkingFare() {
        return parkingFare;
    }

    public void setParkingFare(Double parkingFare) {
        this.parkingFare = parkingFare;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Double getTollFare() {
        return tollFare;
    }

    public void setTollFare(Double tollFare) {
        this.tollFare = tollFare;
    }

    public Double getAdditionalFare() {
        return additionalFare;
    }

    public void setAdditionalFare(Double additionalFare) {
        this.additionalFare = additionalFare;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    public Double getCancellationFare() {
        return cancellationFare;
    }

    public void setCancellationFare(Double cancellationFare) {
        this.cancellationFare = cancellationFare;
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
    public CompanyTax getFkeyCompanyTaxId() {
        return fkeyCompanyTaxId;
    }

    public void setFkeyCompanyTaxId(CompanyTax fkeyCompanyTaxId) {
        this.fkeyCompanyTaxId = fkeyCompanyTaxId;
    }

    public PaymentMode getFkeyPaymentModeId() {
        return fkeyPaymentModeId;
    }

    public void setFkeyPaymentModeId(PaymentMode fkeyPaymentModeId) {
        this.fkeyPaymentModeId = fkeyPaymentModeId;
    }

    public Trip getFkeyTripId() {
        return fkeyTripId;
    }

    public void setFkeyTripId(Trip fkeyTripId) {
        this.fkeyTripId = fkeyTripId;
    }
}
