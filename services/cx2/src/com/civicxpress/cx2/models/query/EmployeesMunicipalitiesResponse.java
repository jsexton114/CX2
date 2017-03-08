/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class EmployeesMunicipalitiesResponse implements Serializable {

    @JsonProperty("Active")
    @ColumnAlias("Active")
    private Boolean active;
    @JsonProperty("Address1")
    @ColumnAlias("Address1")
    private String address1;
    @JsonProperty("Address2")
    @ColumnAlias("Address2")
    private String address2;
    @JsonProperty("AllowManualPayment")
    @ColumnAlias("AllowManualPayment")
    private Boolean allowManualPayment;
    @JsonProperty("City")
    @ColumnAlias("City")
    private String city;
    @JsonProperty("CloseTime")
    @ColumnAlias("CloseTime")
    private Time closeTime;
    @JsonProperty("Email")
    @ColumnAlias("Email")
    private String email;
    @JsonProperty("FridayYN")
    @ColumnAlias("FridayYN")
    private Boolean fridayYn;
    @JsonProperty("GlobalEmailSig")
    @ColumnAlias("GlobalEmailSig")
    private String globalEmailSig;
    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;
    @JsonProperty("Logo")
    @ColumnAlias("Logo")
    private byte[] logo;
    @JsonProperty("ManualPaymentPercent")
    @ColumnAlias("ManualPaymentPercent")
    private BigDecimal manualPaymentPercent;
    @JsonProperty("MondayYN")
    @ColumnAlias("MondayYN")
    private Boolean mondayYn;
    @JsonProperty("MunicipalityGuid")
    @ColumnAlias("MunicipalityGuid")
    private String municipalityGuid;
    @JsonProperty("MunicipalityName")
    @ColumnAlias("MunicipalityName")
    private String municipalityName;
    @JsonProperty("OpenTime")
    @ColumnAlias("OpenTime")
    private Time openTime;
    @JsonProperty("Phone")
    @ColumnAlias("Phone")
    private String phone;
    @JsonProperty("PostalCode")
    @ColumnAlias("PostalCode")
    private String postalCode;
    @JsonProperty("SaturdayYN")
    @ColumnAlias("SaturdayYN")
    private Boolean saturdayYn;
    @JsonProperty("StateId")
    @ColumnAlias("StateId")
    private BigDecimal stateId;
    @JsonProperty("SundayYN")
    @ColumnAlias("SundayYN")
    private Boolean sundayYn;
    @JsonProperty("ThursdayYN")
    @ColumnAlias("ThursdayYN")
    private Boolean thursdayYn;
    @JsonProperty("TimeZone")
    @ColumnAlias("TimeZone")
    private String timeZone;
    @JsonProperty("TuesdayYN")
    @ColumnAlias("TuesdayYN")
    private Boolean tuesdayYn;
    @JsonProperty("WednesdayYN")
    @ColumnAlias("WednesdayYN")
    private Boolean wednesdayYn;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Boolean getAllowManualPayment() {
        return this.allowManualPayment;
    }

    public void setAllowManualPayment(Boolean allowManualPayment) {
        this.allowManualPayment = allowManualPayment;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Time getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFridayYn() {
        return this.fridayYn;
    }

    public void setFridayYn(Boolean fridayYn) {
        this.fridayYn = fridayYn;
    }

    public String getGlobalEmailSig() {
        return this.globalEmailSig;
    }

    public void setGlobalEmailSig(String globalEmailSig) {
        this.globalEmailSig = globalEmailSig;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public byte[] getLogo() {
        return this.logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public BigDecimal getManualPaymentPercent() {
        return this.manualPaymentPercent;
    }

    public void setManualPaymentPercent(BigDecimal manualPaymentPercent) {
        this.manualPaymentPercent = manualPaymentPercent;
    }

    public Boolean getMondayYn() {
        return this.mondayYn;
    }

    public void setMondayYn(Boolean mondayYn) {
        this.mondayYn = mondayYn;
    }

    public String getMunicipalityGuid() {
        return this.municipalityGuid;
    }

    public void setMunicipalityGuid(String municipalityGuid) {
        this.municipalityGuid = municipalityGuid;
    }

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public Time getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getSaturdayYn() {
        return this.saturdayYn;
    }

    public void setSaturdayYn(Boolean saturdayYn) {
        this.saturdayYn = saturdayYn;
    }

    public BigDecimal getStateId() {
        return this.stateId;
    }

    public void setStateId(BigDecimal stateId) {
        this.stateId = stateId;
    }

    public Boolean getSundayYn() {
        return this.sundayYn;
    }

    public void setSundayYn(Boolean sundayYn) {
        this.sundayYn = sundayYn;
    }

    public Boolean getThursdayYn() {
        return this.thursdayYn;
    }

    public void setThursdayYn(Boolean thursdayYn) {
        this.thursdayYn = thursdayYn;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getTuesdayYn() {
        return this.tuesdayYn;
    }

    public void setTuesdayYn(Boolean tuesdayYn) {
        this.tuesdayYn = tuesdayYn;
    }

    public Boolean getWednesdayYn() {
        return this.wednesdayYn;
    }

    public void setWednesdayYn(Boolean wednesdayYn) {
        this.wednesdayYn = wednesdayYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeesMunicipalitiesResponse)) return false;
        final EmployeesMunicipalitiesResponse employeesMunicipalitiesResponse = (EmployeesMunicipalitiesResponse) o;
        return Objects.equals(getActive(), employeesMunicipalitiesResponse.getActive()) &&
                Objects.equals(getAddress1(), employeesMunicipalitiesResponse.getAddress1()) &&
                Objects.equals(getAddress2(), employeesMunicipalitiesResponse.getAddress2()) &&
                Objects.equals(getAllowManualPayment(), employeesMunicipalitiesResponse.getAllowManualPayment()) &&
                Objects.equals(getCity(), employeesMunicipalitiesResponse.getCity()) &&
                Objects.equals(getCloseTime(), employeesMunicipalitiesResponse.getCloseTime()) &&
                Objects.equals(getEmail(), employeesMunicipalitiesResponse.getEmail()) &&
                Objects.equals(getFridayYn(), employeesMunicipalitiesResponse.getFridayYn()) &&
                Objects.equals(getGlobalEmailSig(), employeesMunicipalitiesResponse.getGlobalEmailSig()) &&
                Objects.equals(getId(), employeesMunicipalitiesResponse.getId()) &&
                Objects.equals(getLogo(), employeesMunicipalitiesResponse.getLogo()) &&
                Objects.equals(getManualPaymentPercent(), employeesMunicipalitiesResponse.getManualPaymentPercent()) &&
                Objects.equals(getMondayYn(), employeesMunicipalitiesResponse.getMondayYn()) &&
                Objects.equals(getMunicipalityGuid(), employeesMunicipalitiesResponse.getMunicipalityGuid()) &&
                Objects.equals(getMunicipalityName(), employeesMunicipalitiesResponse.getMunicipalityName()) &&
                Objects.equals(getOpenTime(), employeesMunicipalitiesResponse.getOpenTime()) &&
                Objects.equals(getPhone(), employeesMunicipalitiesResponse.getPhone()) &&
                Objects.equals(getPostalCode(), employeesMunicipalitiesResponse.getPostalCode()) &&
                Objects.equals(getSaturdayYn(), employeesMunicipalitiesResponse.getSaturdayYn()) &&
                Objects.equals(getStateId(), employeesMunicipalitiesResponse.getStateId()) &&
                Objects.equals(getSundayYn(), employeesMunicipalitiesResponse.getSundayYn()) &&
                Objects.equals(getThursdayYn(), employeesMunicipalitiesResponse.getThursdayYn()) &&
                Objects.equals(getTimeZone(), employeesMunicipalitiesResponse.getTimeZone()) &&
                Objects.equals(getTuesdayYn(), employeesMunicipalitiesResponse.getTuesdayYn()) &&
                Objects.equals(getWednesdayYn(), employeesMunicipalitiesResponse.getWednesdayYn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActive(),
                getAddress1(),
                getAddress2(),
                getAllowManualPayment(),
                getCity(),
                getCloseTime(),
                getEmail(),
                getFridayYn(),
                getGlobalEmailSig(),
                getId(),
                getLogo(),
                getManualPaymentPercent(),
                getMondayYn(),
                getMunicipalityGuid(),
                getMunicipalityName(),
                getOpenTime(),
                getPhone(),
                getPostalCode(),
                getSaturdayYn(),
                getStateId(),
                getSundayYn(),
                getThursdayYn(),
                getTimeZone(),
                getTuesdayYn(),
                getWednesdayYn());
    }
}
