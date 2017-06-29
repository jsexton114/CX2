/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class StandardUserMunicipalitesResponse implements Serializable {


    @JsonProperty("ID")
    @ColumnAlias("ID")
    private Integer id;

    @JsonProperty("MunicipalityGuid")
    @ColumnAlias("MunicipalityGuid")
    private String municipalityGuid;

    @JsonProperty("MunicipalityName")
    @ColumnAlias("MunicipalityName")
    private String municipalityName;

    @JsonProperty("Address1")
    @ColumnAlias("Address1")
    private String address1;

    @JsonProperty("Address2")
    @ColumnAlias("Address2")
    private String address2;

    @JsonProperty("City")
    @ColumnAlias("City")
    private String city;

    @JsonProperty("PostalCode")
    @ColumnAlias("PostalCode")
    private String postalCode;

    @JsonProperty("Phone")
    @ColumnAlias("Phone")
    private String phone;

    @JsonProperty("Email")
    @ColumnAlias("Email")
    private String email;

    @JsonProperty("AllowManualPayment")
    @ColumnAlias("AllowManualPayment")
    private Boolean allowManualPayment;

    @JsonProperty("ManualPaymentPercent")
    @ColumnAlias("ManualPaymentPercent")
    private Integer manualPaymentPercent;

    @JsonProperty("Active")
    @ColumnAlias("Active")
    private Boolean active;

    @JsonProperty("GlobalEmailSig")
    @ColumnAlias("GlobalEmailSig")
    private String globalEmailSig;

    @JsonSerialize(using = ByteArrayToStringSerializer.class)
    @JsonProperty(value = "Logo", access = Access.READ_ONLY)
    @ColumnAlias("Logo")
    private byte[] logo;

    @JsonProperty("StateId")
    @ColumnAlias("StateId")
    private Integer stateId;

    @JsonProperty("MondayYN")
    @ColumnAlias("MondayYN")
    private Boolean mondayYn;

    @JsonProperty("TuesdayYN")
    @ColumnAlias("TuesdayYN")
    private Boolean tuesdayYn;

    @JsonProperty("WednesdayYN")
    @ColumnAlias("WednesdayYN")
    private Boolean wednesdayYn;

    @JsonProperty("ThursdayYN")
    @ColumnAlias("ThursdayYN")
    private Boolean thursdayYn;

    @JsonProperty("FridayYN")
    @ColumnAlias("FridayYN")
    private Boolean fridayYn;

    @JsonProperty("SaturdayYN")
    @ColumnAlias("SaturdayYN")
    private Boolean saturdayYn;

    @JsonProperty("SundayYN")
    @ColumnAlias("SundayYN")
    private Boolean sundayYn;

    @JsonProperty("TimeZone")
    @ColumnAlias("TimeZone")
    private String timeZone;

    @JsonProperty("OpenTime")
    @ColumnAlias("OpenTime")
    private Time openTime;

    @JsonProperty("CloseTime")
    @ColumnAlias("CloseTime")
    private Time closeTime;

    @JsonProperty("DbName")
    @ColumnAlias("DbName")
    private String dbName;

    @JsonProperty("DbUser")
    @ColumnAlias("DbUser")
    private String dbUser;

    @JsonProperty("DbPassword")
    @ColumnAlias("DbPassword")
    private String dbPassword;

    @JsonProperty("UserLimit")
    @ColumnAlias("UserLimit")
    private Integer userLimit;

    @JsonProperty("FormLimit")
    @ColumnAlias("FormLimit")
    private Integer formLimit;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAllowManualPayment() {
        return this.allowManualPayment;
    }

    public void setAllowManualPayment(Boolean allowManualPayment) {
        this.allowManualPayment = allowManualPayment;
    }

    public Integer getManualPaymentPercent() {
        return this.manualPaymentPercent;
    }

    public void setManualPaymentPercent(Integer manualPaymentPercent) {
        this.manualPaymentPercent = manualPaymentPercent;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getGlobalEmailSig() {
        return this.globalEmailSig;
    }

    public void setGlobalEmailSig(String globalEmailSig) {
        this.globalEmailSig = globalEmailSig;
    }

    public byte[] getLogo() {
        return this.logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Boolean getMondayYn() {
        return this.mondayYn;
    }

    public void setMondayYn(Boolean mondayYn) {
        this.mondayYn = mondayYn;
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

    public Boolean getThursdayYn() {
        return this.thursdayYn;
    }

    public void setThursdayYn(Boolean thursdayYn) {
        this.thursdayYn = thursdayYn;
    }

    public Boolean getFridayYn() {
        return this.fridayYn;
    }

    public void setFridayYn(Boolean fridayYn) {
        this.fridayYn = fridayYn;
    }

    public Boolean getSaturdayYn() {
        return this.saturdayYn;
    }

    public void setSaturdayYn(Boolean saturdayYn) {
        this.saturdayYn = saturdayYn;
    }

    public Boolean getSundayYn() {
        return this.sundayYn;
    }

    public void setSundayYn(Boolean sundayYn) {
        this.sundayYn = sundayYn;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Time getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return this.dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public Integer getUserLimit() {
        return this.userLimit;
    }

    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
    }

    public Integer getFormLimit() {
        return this.formLimit;
    }

    public void setFormLimit(Integer formLimit) {
        this.formLimit = formLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardUserMunicipalitesResponse)) return false;
        final StandardUserMunicipalitesResponse standardUserMunicipalitesResponse = (StandardUserMunicipalitesResponse) o;
        return Objects.equals(getId(), standardUserMunicipalitesResponse.getId()) &&
                Objects.equals(getMunicipalityGuid(), standardUserMunicipalitesResponse.getMunicipalityGuid()) &&
                Objects.equals(getMunicipalityName(), standardUserMunicipalitesResponse.getMunicipalityName()) &&
                Objects.equals(getAddress1(), standardUserMunicipalitesResponse.getAddress1()) &&
                Objects.equals(getAddress2(), standardUserMunicipalitesResponse.getAddress2()) &&
                Objects.equals(getCity(), standardUserMunicipalitesResponse.getCity()) &&
                Objects.equals(getPostalCode(), standardUserMunicipalitesResponse.getPostalCode()) &&
                Objects.equals(getPhone(), standardUserMunicipalitesResponse.getPhone()) &&
                Objects.equals(getEmail(), standardUserMunicipalitesResponse.getEmail()) &&
                Objects.equals(getAllowManualPayment(), standardUserMunicipalitesResponse.getAllowManualPayment()) &&
                Objects.equals(getManualPaymentPercent(), standardUserMunicipalitesResponse.getManualPaymentPercent()) &&
                Objects.equals(getActive(), standardUserMunicipalitesResponse.getActive()) &&
                Objects.equals(getGlobalEmailSig(), standardUserMunicipalitesResponse.getGlobalEmailSig()) &&
                Objects.equals(getLogo(), standardUserMunicipalitesResponse.getLogo()) &&
                Objects.equals(getStateId(), standardUserMunicipalitesResponse.getStateId()) &&
                Objects.equals(getMondayYn(), standardUserMunicipalitesResponse.getMondayYn()) &&
                Objects.equals(getTuesdayYn(), standardUserMunicipalitesResponse.getTuesdayYn()) &&
                Objects.equals(getWednesdayYn(), standardUserMunicipalitesResponse.getWednesdayYn()) &&
                Objects.equals(getThursdayYn(), standardUserMunicipalitesResponse.getThursdayYn()) &&
                Objects.equals(getFridayYn(), standardUserMunicipalitesResponse.getFridayYn()) &&
                Objects.equals(getSaturdayYn(), standardUserMunicipalitesResponse.getSaturdayYn()) &&
                Objects.equals(getSundayYn(), standardUserMunicipalitesResponse.getSundayYn()) &&
                Objects.equals(getTimeZone(), standardUserMunicipalitesResponse.getTimeZone()) &&
                Objects.equals(getOpenTime(), standardUserMunicipalitesResponse.getOpenTime()) &&
                Objects.equals(getCloseTime(), standardUserMunicipalitesResponse.getCloseTime()) &&
                Objects.equals(getDbName(), standardUserMunicipalitesResponse.getDbName()) &&
                Objects.equals(getDbUser(), standardUserMunicipalitesResponse.getDbUser()) &&
                Objects.equals(getDbPassword(), standardUserMunicipalitesResponse.getDbPassword()) &&
                Objects.equals(getUserLimit(), standardUserMunicipalitesResponse.getUserLimit()) &&
                Objects.equals(getFormLimit(), standardUserMunicipalitesResponse.getFormLimit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getMunicipalityGuid(),
                getMunicipalityName(),
                getAddress1(),
                getAddress2(),
                getCity(),
                getPostalCode(),
                getPhone(),
                getEmail(),
                getAllowManualPayment(),
                getManualPaymentPercent(),
                getActive(),
                getGlobalEmailSig(),
                getLogo(),
                getStateId(),
                getMondayYn(),
                getTuesdayYn(),
                getWednesdayYn(),
                getThursdayYn(),
                getFridayYn(),
                getSaturdayYn(),
                getSundayYn(),
                getTimeZone(),
                getOpenTime(),
                getCloseTime(),
                getDbName(),
                getDbUser(),
                getDbPassword(),
                getUserLimit(),
                getFormLimit());
    }
}
