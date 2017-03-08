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
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class VendorsLinkedWithFormResponse implements Serializable {

    @JsonProperty("address1")
    @ColumnAlias("address1")
    private String address1;
    @JsonProperty("address2")
    @ColumnAlias("address2")
    private String address2;
    @JsonProperty("altPhone")
    @ColumnAlias("altPhone")
    private String altPhone;
    @JsonProperty("businessClassification")
    @ColumnAlias("businessClassification")
    private String businessClassification;
    @JsonProperty("city")
    @ColumnAlias("city")
    private String city;
    @JsonProperty("coi")
    @ColumnAlias("coi")
    private byte[] coi;
    @JsonProperty("companyEmail")
    @ColumnAlias("companyEmail")
    private String companyEmail;
    @JsonProperty("companyName")
    @ColumnAlias("companyName")
    private String companyName;
    @JsonProperty("companyPhone")
    @ColumnAlias("companyPhone")
    private String companyPhone;
    @JsonProperty("companyWebsite")
    @ColumnAlias("companyWebsite")
    private String companyWebsite;
    @JsonProperty("contractorTypeId")
    @ColumnAlias("contractorTypeId")
    private Integer contractorTypeId;
    @JsonProperty("contractorTypes")
    @ColumnAlias("contractorTypes")
    private ContractorTypes contractorTypes;
    @JsonProperty("country")
    @ColumnAlias("country")
    private String country;
    @JsonProperty("faxNumber")
    @ColumnAlias("faxNumber")
    private String faxNumber;
    @JsonProperty("feeses")
    @ColumnAlias("feeses")
    private List feeses;
    @JsonProperty("feinNumber")
    @ColumnAlias("feinNumber")
    private String feinNumber;
    @JsonProperty("id")
    @ColumnAlias("id")
    private Integer id;
    @JsonProperty("insuranceCompany")
    @ColumnAlias("insuranceCompany")
    private String insuranceCompany;
    @JsonProperty("insuranceExpires")
    @ColumnAlias("insuranceExpires")
    private Date insuranceExpires;
    @JsonProperty("lastUpdated")
    @ColumnAlias("lastUpdated")
    private Date lastUpdated;
    @JsonProperty("masterFormses")
    @ColumnAlias("masterFormses")
    private List masterFormses;
    @JsonProperty("numberOfEmployees")
    @ColumnAlias("numberOfEmployees")
    private Integer numberOfEmployees;
    @JsonProperty("postalCode")
    @ColumnAlias("postalCode")
    private String postalCode;
    @JsonProperty("stateId")
    @ColumnAlias("stateId")
    private Integer stateId;
    @JsonProperty("stateRegNumber")
    @ColumnAlias("stateRegNumber")
    private String stateRegNumber;
    @JsonProperty("states")
    @ColumnAlias("states")
    private States states;
    @JsonProperty("typeOfBusiness")
    @ColumnAlias("typeOfBusiness")
    private String typeOfBusiness;
    @JsonProperty("vendorAdminses")
    @ColumnAlias("vendorAdminses")
    private List vendorAdminses;
    @JsonProperty("vendorApprovalses")
    @ColumnAlias("vendorApprovalses")
    private List vendorApprovalses;
    @JsonProperty("vendorLicenseses")
    @ColumnAlias("vendorLicenseses")
    private List vendorLicenseses;
    @JsonProperty("vendorUserses")
    @ColumnAlias("vendorUserses")
    private List vendorUserses;
    @JsonProperty("vendors2forms")
    @ColumnAlias("vendors2forms")
    private List vendors2forms;

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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte[] getCoi() {
        return this.coi;
    }

    public void setCoi(byte[] coi) {
        this.coi = coi;
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

    public Integer getContractorTypeId() {
        return this.contractorTypeId;
    }

    public void setContractorTypeId(Integer contractorTypeId) {
        this.contractorTypeId = contractorTypeId;
    }

    public ContractorTypes getContractorTypes() {
        return this.contractorTypes;
    }

    public void setContractorTypes(ContractorTypes contractorTypes) {
        this.contractorTypes = contractorTypes;
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

    public List getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List feeses) {
        this.feeses = feeses;
    }

    public String getFeinNumber() {
        return this.feinNumber;
    }

    public void setFeinNumber(String feinNumber) {
        this.feinNumber = feinNumber;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public List getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List masterFormses) {
        this.masterFormses = masterFormses;
    }

    public Integer getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateRegNumber() {
        return this.stateRegNumber;
    }

    public void setStateRegNumber(String stateRegNumber) {
        this.stateRegNumber = stateRegNumber;
    }

    public States getStates() {
        return this.states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public String getTypeOfBusiness() {
        return this.typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public List getVendorAdminses() {
        return this.vendorAdminses;
    }

    public void setVendorAdminses(List vendorAdminses) {
        this.vendorAdminses = vendorAdminses;
    }

    public List getVendorApprovalses() {
        return this.vendorApprovalses;
    }

    public void setVendorApprovalses(List vendorApprovalses) {
        this.vendorApprovalses = vendorApprovalses;
    }

    public List getVendorLicenseses() {
        return this.vendorLicenseses;
    }

    public void setVendorLicenseses(List vendorLicenseses) {
        this.vendorLicenseses = vendorLicenseses;
    }

    public List getVendorUserses() {
        return this.vendorUserses;
    }

    public void setVendorUserses(List vendorUserses) {
        this.vendorUserses = vendorUserses;
    }

    public List getVendors2forms() {
        return this.vendors2forms;
    }

    public void setVendors2forms(List vendors2forms) {
        this.vendors2forms = vendors2forms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorsLinkedWithFormResponse)) return false;
        final VendorsLinkedWithFormResponse vendorsLinkedWithFormResponse = (VendorsLinkedWithFormResponse) o;
        return Objects.equals(getAddress1(), vendorsLinkedWithFormResponse.getAddress1()) &&
                Objects.equals(getAddress2(), vendorsLinkedWithFormResponse.getAddress2()) &&
                Objects.equals(getAltPhone(), vendorsLinkedWithFormResponse.getAltPhone()) &&
                Objects.equals(getBusinessClassification(), vendorsLinkedWithFormResponse.getBusinessClassification()) &&
                Objects.equals(getCity(), vendorsLinkedWithFormResponse.getCity()) &&
                Objects.equals(getCoi(), vendorsLinkedWithFormResponse.getCoi()) &&
                Objects.equals(getCompanyEmail(), vendorsLinkedWithFormResponse.getCompanyEmail()) &&
                Objects.equals(getCompanyName(), vendorsLinkedWithFormResponse.getCompanyName()) &&
                Objects.equals(getCompanyPhone(), vendorsLinkedWithFormResponse.getCompanyPhone()) &&
                Objects.equals(getCompanyWebsite(), vendorsLinkedWithFormResponse.getCompanyWebsite()) &&
                Objects.equals(getContractorTypeId(), vendorsLinkedWithFormResponse.getContractorTypeId()) &&
                Objects.equals(getContractorTypes(), vendorsLinkedWithFormResponse.getContractorTypes()) &&
                Objects.equals(getCountry(), vendorsLinkedWithFormResponse.getCountry()) &&
                Objects.equals(getFaxNumber(), vendorsLinkedWithFormResponse.getFaxNumber()) &&
                Objects.equals(getFeeses(), vendorsLinkedWithFormResponse.getFeeses()) &&
                Objects.equals(getFeinNumber(), vendorsLinkedWithFormResponse.getFeinNumber()) &&
                Objects.equals(getId(), vendorsLinkedWithFormResponse.getId()) &&
                Objects.equals(getInsuranceCompany(), vendorsLinkedWithFormResponse.getInsuranceCompany()) &&
                Objects.equals(getInsuranceExpires(), vendorsLinkedWithFormResponse.getInsuranceExpires()) &&
                Objects.equals(getLastUpdated(), vendorsLinkedWithFormResponse.getLastUpdated()) &&
                Objects.equals(getMasterFormses(), vendorsLinkedWithFormResponse.getMasterFormses()) &&
                Objects.equals(getNumberOfEmployees(), vendorsLinkedWithFormResponse.getNumberOfEmployees()) &&
                Objects.equals(getPostalCode(), vendorsLinkedWithFormResponse.getPostalCode()) &&
                Objects.equals(getStateId(), vendorsLinkedWithFormResponse.getStateId()) &&
                Objects.equals(getStateRegNumber(), vendorsLinkedWithFormResponse.getStateRegNumber()) &&
                Objects.equals(getStates(), vendorsLinkedWithFormResponse.getStates()) &&
                Objects.equals(getTypeOfBusiness(), vendorsLinkedWithFormResponse.getTypeOfBusiness()) &&
                Objects.equals(getVendorAdminses(), vendorsLinkedWithFormResponse.getVendorAdminses()) &&
                Objects.equals(getVendorApprovalses(), vendorsLinkedWithFormResponse.getVendorApprovalses()) &&
                Objects.equals(getVendorLicenseses(), vendorsLinkedWithFormResponse.getVendorLicenseses()) &&
                Objects.equals(getVendorUserses(), vendorsLinkedWithFormResponse.getVendorUserses()) &&
                Objects.equals(getVendors2forms(), vendorsLinkedWithFormResponse.getVendors2forms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress1(),
                getAddress2(),
                getAltPhone(),
                getBusinessClassification(),
                getCity(),
                getCoi(),
                getCompanyEmail(),
                getCompanyName(),
                getCompanyPhone(),
                getCompanyWebsite(),
                getContractorTypeId(),
                getContractorTypes(),
                getCountry(),
                getFaxNumber(),
                getFeeses(),
                getFeinNumber(),
                getId(),
                getInsuranceCompany(),
                getInsuranceExpires(),
                getLastUpdated(),
                getMasterFormses(),
                getNumberOfEmployees(),
                getPostalCode(),
                getStateId(),
                getStateRegNumber(),
                getStates(),
                getTypeOfBusiness(),
                getVendorAdminses(),
                getVendorApprovalses(),
                getVendorLicenseses(),
                getVendorUserses(),
                getVendors2forms());
    }
}
