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
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class CompanyListOfEmployeeResponse implements Serializable {


    @ColumnAlias("ID")
    private Integer id;

    @ColumnAlias("CompanyName")
    private String companyName;

    @ColumnAlias("Address1")
    private String address1;

    @ColumnAlias("Address2")
    private String address2;

    @ColumnAlias("City")
    private String city;

    @ColumnAlias("StateId")
    private Integer stateId;

    @ColumnAlias("Country")
    private String country;

    @ColumnAlias("PostalCode")
    private String postalCode;

    @ColumnAlias("FaxNumber")
    private String faxNumber;

    @ColumnAlias("CompanyPhone")
    private String companyPhone;

    @ColumnAlias("AltPhone")
    private String altPhone;

    @ColumnAlias("CompanyEmail")
    private String companyEmail;

    @ColumnAlias("CompanyWebsite")
    private String companyWebsite;

    @ColumnAlias("TypeOfBusiness")
    private String typeOfBusiness;

    @ColumnAlias("ContractorTypeId")
    private Integer contractorTypeId;

    @ColumnAlias("BusinessClassification")
    private String businessClassification;

    @ColumnAlias("StateRegNumber")
    private String stateRegNumber;

    @ColumnAlias("NumberOfEmployees")
    private Integer numberOfEmployees;

    @ColumnAlias("FeinNumber")
    private String feinNumber;

    @JsonSerialize(using = ByteArrayToStringSerializer.class)
    @JsonProperty(access = Access.READ_ONLY)
    @ColumnAlias("COI")
    private byte[] coi;

    @ColumnAlias("InsuranceCompany")
    private String insuranceCompany;

    @ColumnAlias("InsuranceExpires")
    private Date insuranceExpires;

    @ColumnAlias("LastUpdated")
    private Date lastUpdated;

    @ColumnAlias("Active")
    private Boolean active;

    @ColumnAlias("FullAddress")
    private String fullAddress;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFaxNumber() {
        return this.faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getCompanyPhone() {
        return this.companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getAltPhone() {
        return this.altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String getCompanyEmail() {
        return this.companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyWebsite() {
        return this.companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getTypeOfBusiness() {
        return this.typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public Integer getContractorTypeId() {
        return this.contractorTypeId;
    }

    public void setContractorTypeId(Integer contractorTypeId) {
        this.contractorTypeId = contractorTypeId;
    }

    public String getBusinessClassification() {
        return this.businessClassification;
    }

    public void setBusinessClassification(String businessClassification) {
        this.businessClassification = businessClassification;
    }

    public String getStateRegNumber() {
        return this.stateRegNumber;
    }

    public void setStateRegNumber(String stateRegNumber) {
        this.stateRegNumber = stateRegNumber;
    }

    public Integer getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getFeinNumber() {
        return this.feinNumber;
    }

    public void setFeinNumber(String feinNumber) {
        this.feinNumber = feinNumber;
    }

    public byte[] getCoi() {
        return this.coi;
    }

    public void setCoi(byte[] coi) {
        this.coi = coi;
    }

    public String getInsuranceCompany() {
        return this.insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Date getInsuranceExpires() {
        return this.insuranceExpires;
    }

    public void setInsuranceExpires(Date insuranceExpires) {
        this.insuranceExpires = insuranceExpires;
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyListOfEmployeeResponse)) return false;
        final CompanyListOfEmployeeResponse companyListOfEmployeeResponse = (CompanyListOfEmployeeResponse) o;
        return Objects.equals(getId(), companyListOfEmployeeResponse.getId()) &&
                Objects.equals(getCompanyName(), companyListOfEmployeeResponse.getCompanyName()) &&
                Objects.equals(getAddress1(), companyListOfEmployeeResponse.getAddress1()) &&
                Objects.equals(getAddress2(), companyListOfEmployeeResponse.getAddress2()) &&
                Objects.equals(getCity(), companyListOfEmployeeResponse.getCity()) &&
                Objects.equals(getStateId(), companyListOfEmployeeResponse.getStateId()) &&
                Objects.equals(getCountry(), companyListOfEmployeeResponse.getCountry()) &&
                Objects.equals(getPostalCode(), companyListOfEmployeeResponse.getPostalCode()) &&
                Objects.equals(getFaxNumber(), companyListOfEmployeeResponse.getFaxNumber()) &&
                Objects.equals(getCompanyPhone(), companyListOfEmployeeResponse.getCompanyPhone()) &&
                Objects.equals(getAltPhone(), companyListOfEmployeeResponse.getAltPhone()) &&
                Objects.equals(getCompanyEmail(), companyListOfEmployeeResponse.getCompanyEmail()) &&
                Objects.equals(getCompanyWebsite(), companyListOfEmployeeResponse.getCompanyWebsite()) &&
                Objects.equals(getTypeOfBusiness(), companyListOfEmployeeResponse.getTypeOfBusiness()) &&
                Objects.equals(getContractorTypeId(), companyListOfEmployeeResponse.getContractorTypeId()) &&
                Objects.equals(getBusinessClassification(), companyListOfEmployeeResponse.getBusinessClassification()) &&
                Objects.equals(getStateRegNumber(), companyListOfEmployeeResponse.getStateRegNumber()) &&
                Objects.equals(getNumberOfEmployees(), companyListOfEmployeeResponse.getNumberOfEmployees()) &&
                Objects.equals(getFeinNumber(), companyListOfEmployeeResponse.getFeinNumber()) &&
                Objects.equals(getCoi(), companyListOfEmployeeResponse.getCoi()) &&
                Objects.equals(getInsuranceCompany(), companyListOfEmployeeResponse.getInsuranceCompany()) &&
                Objects.equals(getInsuranceExpires(), companyListOfEmployeeResponse.getInsuranceExpires()) &&
                Objects.equals(getLastUpdated(), companyListOfEmployeeResponse.getLastUpdated()) &&
                Objects.equals(getActive(), companyListOfEmployeeResponse.getActive()) &&
                Objects.equals(getFullAddress(), companyListOfEmployeeResponse.getFullAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getCompanyName(),
                getAddress1(),
                getAddress2(),
                getCity(),
                getStateId(),
                getCountry(),
                getPostalCode(),
                getFaxNumber(),
                getCompanyPhone(),
                getAltPhone(),
                getCompanyEmail(),
                getCompanyWebsite(),
                getTypeOfBusiness(),
                getContractorTypeId(),
                getBusinessClassification(),
                getStateRegNumber(),
                getNumberOfEmployees(),
                getFeinNumber(),
                getCoi(),
                getInsuranceCompany(),
                getInsuranceExpires(),
                getLastUpdated(),
                getActive(),
                getFullAddress());
    }
}