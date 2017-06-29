/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class AdminVendorsListResponse implements Serializable {


    @JsonProperty("Address1")
    @ColumnAlias("Address1")
    private String address1;

    @JsonProperty("Address2")
    @ColumnAlias("Address2")
    private String address2;

    @JsonProperty("AltPhone")
    @ColumnAlias("AltPhone")
    private String altPhone;

    @JsonProperty("BusinessClassification")
    @ColumnAlias("BusinessClassification")
    private String businessClassification;

    @JsonSerialize(using = ByteArrayToStringSerializer.class)
    @JsonProperty(value = "COI", access = Access.READ_ONLY)
    @ColumnAlias("COI")
    private byte[] coi;

    @JsonProperty("City")
    @ColumnAlias("City")
    private String city;

    @JsonProperty("CompanyEmail")
    @ColumnAlias("CompanyEmail")
    private String companyEmail;

    @JsonProperty("CompanyName")
    @ColumnAlias("CompanyName")
    private String companyName;

    @JsonProperty("CompanyPhone")
    @ColumnAlias("CompanyPhone")
    private String companyPhone;

    @JsonProperty("CompanyWebsite")
    @ColumnAlias("CompanyWebsite")
    private String companyWebsite;

    @JsonProperty("ContractorTypeId")
    @ColumnAlias("ContractorTypeId")
    private BigDecimal contractorTypeId;

    @JsonProperty("Country")
    @ColumnAlias("Country")
    private String country;

    @JsonProperty("FaxNumber")
    @ColumnAlias("FaxNumber")
    private String faxNumber;

    @JsonProperty("FeinNumber")
    @ColumnAlias("FeinNumber")
    private String feinNumber;

    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;

    @JsonProperty("InsuranceCompany")
    @ColumnAlias("InsuranceCompany")
    private String insuranceCompany;

    @JsonProperty("InsuranceExpires")
    @ColumnAlias("InsuranceExpires")
    private Date insuranceExpires;

    @JsonProperty("LastUpdated")
    @ColumnAlias("LastUpdated")
    private Date lastUpdated;

    @JsonProperty("NumberOfEmployees")
    @ColumnAlias("NumberOfEmployees")
    private BigDecimal numberOfEmployees;

    @JsonProperty("PostalCode")
    @ColumnAlias("PostalCode")
    private String postalCode;

    @JsonProperty("StateId")
    @ColumnAlias("StateId")
    private BigDecimal stateId;

    @JsonProperty("StateRegNumber")
    @ColumnAlias("StateRegNumber")
    private String stateRegNumber;

    @JsonProperty("TypeOfBusiness")
    @ColumnAlias("TypeOfBusiness")
    private String typeOfBusiness;

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

    public String getAltPhone() {
        return this.altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String getBusinessClassification() {
        return this.businessClassification;
    }

    public void setBusinessClassification(String businessClassification) {
        this.businessClassification = businessClassification;
    }

    public byte[] getCoi() {
        return this.coi;
    }

    public void setCoi(byte[] coi) {
        this.coi = coi;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyEmail() {
        return this.companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return this.companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyWebsite() {
        return this.companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public BigDecimal getContractorTypeId() {
        return this.contractorTypeId;
    }

    public void setContractorTypeId(BigDecimal contractorTypeId) {
        this.contractorTypeId = contractorTypeId;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFaxNumber() {
        return this.faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getFeinNumber() {
        return this.feinNumber;
    }

    public void setFeinNumber(String feinNumber) {
        this.feinNumber = feinNumber;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public BigDecimal getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

    public void setNumberOfEmployees(BigDecimal numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BigDecimal getStateId() {
        return this.stateId;
    }

    public void setStateId(BigDecimal stateId) {
        this.stateId = stateId;
    }

    public String getStateRegNumber() {
        return this.stateRegNumber;
    }

    public void setStateRegNumber(String stateRegNumber) {
        this.stateRegNumber = stateRegNumber;
    }

    public String getTypeOfBusiness() {
        return this.typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminVendorsListResponse)) return false;
        final AdminVendorsListResponse adminVendorsListResponse = (AdminVendorsListResponse) o;
        return Objects.equals(getAddress1(), adminVendorsListResponse.getAddress1()) &&
                Objects.equals(getAddress2(), adminVendorsListResponse.getAddress2()) &&
                Objects.equals(getAltPhone(), adminVendorsListResponse.getAltPhone()) &&
                Objects.equals(getBusinessClassification(), adminVendorsListResponse.getBusinessClassification()) &&
                Objects.equals(getCoi(), adminVendorsListResponse.getCoi()) &&
                Objects.equals(getCity(), adminVendorsListResponse.getCity()) &&
                Objects.equals(getCompanyEmail(), adminVendorsListResponse.getCompanyEmail()) &&
                Objects.equals(getCompanyName(), adminVendorsListResponse.getCompanyName()) &&
                Objects.equals(getCompanyPhone(), adminVendorsListResponse.getCompanyPhone()) &&
                Objects.equals(getCompanyWebsite(), adminVendorsListResponse.getCompanyWebsite()) &&
                Objects.equals(getContractorTypeId(), adminVendorsListResponse.getContractorTypeId()) &&
                Objects.equals(getCountry(), adminVendorsListResponse.getCountry()) &&
                Objects.equals(getFaxNumber(), adminVendorsListResponse.getFaxNumber()) &&
                Objects.equals(getFeinNumber(), adminVendorsListResponse.getFeinNumber()) &&
                Objects.equals(getId(), adminVendorsListResponse.getId()) &&
                Objects.equals(getInsuranceCompany(), adminVendorsListResponse.getInsuranceCompany()) &&
                Objects.equals(getInsuranceExpires(), adminVendorsListResponse.getInsuranceExpires()) &&
                Objects.equals(getLastUpdated(), adminVendorsListResponse.getLastUpdated()) &&
                Objects.equals(getNumberOfEmployees(), adminVendorsListResponse.getNumberOfEmployees()) &&
                Objects.equals(getPostalCode(), adminVendorsListResponse.getPostalCode()) &&
                Objects.equals(getStateId(), adminVendorsListResponse.getStateId()) &&
                Objects.equals(getStateRegNumber(), adminVendorsListResponse.getStateRegNumber()) &&
                Objects.equals(getTypeOfBusiness(), adminVendorsListResponse.getTypeOfBusiness());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress1(),
                getAddress2(),
                getAltPhone(),
                getBusinessClassification(),
                getCoi(),
                getCity(),
                getCompanyEmail(),
                getCompanyName(),
                getCompanyPhone(),
                getCompanyWebsite(),
                getContractorTypeId(),
                getCountry(),
                getFaxNumber(),
                getFeinNumber(),
                getId(),
                getInsuranceCompany(),
                getInsuranceExpires(),
                getLastUpdated(),
                getNumberOfEmployees(),
                getPostalCode(),
                getStateId(),
                getStateRegNumber(),
                getTypeOfBusiness());
    }
}
