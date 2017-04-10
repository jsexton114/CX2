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
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetFormTypesByCategoriesAndMunicipalitiesResponse implements Serializable {

    @JsonProperty("ID")
    @ColumnAlias("ID")
    private Integer id;
    @JsonProperty("FormType")
    @ColumnAlias("FormType")
    private String formType;
    @JsonProperty("FlatFee")
    @ColumnAlias("FlatFee")
    private String flatFee;
    @JsonProperty("FlatFeeAccountingCode")
    @ColumnAlias("FlatFeeAccountingCode")
    private String flatFeeAccountingCode;
    @JsonProperty("SfFee")
    @ColumnAlias("SfFee")
    private String sfFee;
    @JsonProperty("SfFeeAccountingCode")
    @ColumnAlias("SfFeeAccountingCode")
    private String sfFeeAccountingCode;
    @JsonProperty("UnitFee")
    @ColumnAlias("UnitFee")
    private String unitFee;
    @JsonProperty("UnitFeeAccountingCode")
    @ColumnAlias("UnitFeeAccountingCode")
    private String unitFeeAccountingCode;
    @JsonProperty("StateFee")
    @ColumnAlias("StateFee")
    private String stateFee;
    @JsonProperty("StateFeeAccountingCode")
    @ColumnAlias("StateFeeAccountingCode")
    private String stateFeeAccountingCode;
    @JsonProperty("Report")
    @ColumnAlias("Report")
    private String report;
    @JsonProperty("Active")
    @ColumnAlias("Active")
    private Boolean active;
    @JsonProperty("MunicipalityInternalForm")
    @ColumnAlias("MunicipalityInternalForm")
    private Boolean municipalityInternalForm;
    @JsonProperty("CreatedDate")
    @ColumnAlias("CreatedDate")
    private java.sql.Date createdDate;
    @JsonProperty("CollectFees")
    @ColumnAlias("CollectFees")
    private Boolean collectFees;
    @JsonProperty("AutomaticFees")
    @ColumnAlias("AutomaticFees")
    private Boolean automaticFees;
    @JsonProperty("DigitalSignatures")
    @ColumnAlias("DigitalSignatures")
    private Boolean digitalSignatures;
    @JsonProperty("Inspections")
    @ColumnAlias("Inspections")
    private Boolean inspections;
    @JsonProperty("Payments")
    @ColumnAlias("Payments")
    private Boolean payments;
    @JsonProperty("SharedWith")
    @ColumnAlias("SharedWith")
    private Boolean sharedWith;
    @JsonProperty("Attachments")
    @ColumnAlias("Attachments")
    private Boolean attachments;
    @JsonProperty("GISRecord")
    @ColumnAlias("GISRecord")
    private Boolean gisrecord;
    @JsonProperty("MultipleGISRecords")
    @ColumnAlias("MultipleGISRecords")
    private Boolean multipleGisrecords;
    @JsonProperty("GISMap")
    @ColumnAlias("GISMap")
    private Boolean gismap;
    @JsonProperty("FormTableName")
    @ColumnAlias("FormTableName")
    private String formTableName;
    @JsonProperty("MunicipalityId")
    @ColumnAlias("MunicipalityId")
    private Integer municipalityId;
    @JsonProperty("FormTypeGuid")
    @ColumnAlias("FormTypeGuid")
    private String formTypeGuid;
    @JsonProperty("BasementFee")
    @ColumnAlias("BasementFee")
    private String basementFee;
    @JsonProperty("BasementFeeAccountingCode")
    @ColumnAlias("BasementFeeAccountingCode")
    private String basementFeeAccountingCode;
    @JsonProperty("VendorSelection")
    @ColumnAlias("VendorSelection")
    private Boolean vendorSelection;
    @JsonProperty("TitlePrefix")
    @ColumnAlias("TitlePrefix")
    private String titlePrefix;
    @JsonProperty("PrefixDate")
    @ColumnAlias("PrefixDate")
    private String prefixDate;
    @JsonProperty("PrefixNumber")
    @ColumnAlias("PrefixNumber")
    private String prefixNumber;
    @JsonProperty("PrefixNumberStart")
    @ColumnAlias("PrefixNumberStart")
    private BigInteger prefixNumberStart;
    @JsonProperty("PrefixNumberStep")
    @ColumnAlias("PrefixNumberStep")
    private Integer prefixNumberStep;
    @JsonProperty("PrefixDashes")
    @ColumnAlias("PrefixDashes")
    private Boolean prefixDashes;
    @JsonProperty("CurrentPrefixNumber")
    @ColumnAlias("CurrentPrefixNumber")
    private BigInteger currentPrefixNumber;
    @JsonProperty("PrefixNumberResetOn")
    @ColumnAlias("PrefixNumberResetOn")
    private Integer prefixNumberResetOn;
    @JsonProperty("MultipleVendors")
    @ColumnAlias("MultipleVendors")
    private Boolean multipleVendors;
    @JsonProperty("RequireOwner")
    @ColumnAlias("RequireOwner")
    private Boolean requireOwner;
    @JsonProperty("Instructions")
    @ColumnAlias("Instructions")
    private String instructions;
    @JsonProperty("ForceInspectionSequence")
    @ColumnAlias("ForceInspectionSequence")
    private Boolean forceInspectionSequence;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFlatFee() {
        return this.flatFee;
    }

    public void setFlatFee(String flatFee) {
        this.flatFee = flatFee;
    }

    public String getFlatFeeAccountingCode() {
        return this.flatFeeAccountingCode;
    }

    public void setFlatFeeAccountingCode(String flatFeeAccountingCode) {
        this.flatFeeAccountingCode = flatFeeAccountingCode;
    }

    public String getSfFee() {
        return this.sfFee;
    }

    public void setSfFee(String sfFee) {
        this.sfFee = sfFee;
    }

    public String getSfFeeAccountingCode() {
        return this.sfFeeAccountingCode;
    }

    public void setSfFeeAccountingCode(String sfFeeAccountingCode) {
        this.sfFeeAccountingCode = sfFeeAccountingCode;
    }

    public String getUnitFee() {
        return this.unitFee;
    }

    public void setUnitFee(String unitFee) {
        this.unitFee = unitFee;
    }

    public String getUnitFeeAccountingCode() {
        return this.unitFeeAccountingCode;
    }

    public void setUnitFeeAccountingCode(String unitFeeAccountingCode) {
        this.unitFeeAccountingCode = unitFeeAccountingCode;
    }

    public String getStateFee() {
        return this.stateFee;
    }

    public void setStateFee(String stateFee) {
        this.stateFee = stateFee;
    }

    public String getStateFeeAccountingCode() {
        return this.stateFeeAccountingCode;
    }

    public void setStateFeeAccountingCode(String stateFeeAccountingCode) {
        this.stateFeeAccountingCode = stateFeeAccountingCode;
    }

    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getMunicipalityInternalForm() {
        return this.municipalityInternalForm;
    }

    public void setMunicipalityInternalForm(Boolean municipalityInternalForm) {
        this.municipalityInternalForm = municipalityInternalForm;
    }

    public java.sql.Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getCollectFees() {
        return this.collectFees;
    }

    public void setCollectFees(Boolean collectFees) {
        this.collectFees = collectFees;
    }

    public Boolean getAutomaticFees() {
        return this.automaticFees;
    }

    public void setAutomaticFees(Boolean automaticFees) {
        this.automaticFees = automaticFees;
    }

    public Boolean getDigitalSignatures() {
        return this.digitalSignatures;
    }

    public void setDigitalSignatures(Boolean digitalSignatures) {
        this.digitalSignatures = digitalSignatures;
    }

    public Boolean getInspections() {
        return this.inspections;
    }

    public void setInspections(Boolean inspections) {
        this.inspections = inspections;
    }

    public Boolean getPayments() {
        return this.payments;
    }

    public void setPayments(Boolean payments) {
        this.payments = payments;
    }

    public Boolean getSharedWith() {
        return this.sharedWith;
    }

    public void setSharedWith(Boolean sharedWith) {
        this.sharedWith = sharedWith;
    }

    public Boolean getAttachments() {
        return this.attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    public Boolean getGisrecord() {
        return this.gisrecord;
    }

    public void setGisrecord(Boolean gisrecord) {
        this.gisrecord = gisrecord;
    }

    public Boolean getMultipleGisrecords() {
        return this.multipleGisrecords;
    }

    public void setMultipleGisrecords(Boolean multipleGisrecords) {
        this.multipleGisrecords = multipleGisrecords;
    }

    public Boolean getGismap() {
        return this.gismap;
    }

    public void setGismap(Boolean gismap) {
        this.gismap = gismap;
    }

    public String getFormTableName() {
        return this.formTableName;
    }

    public void setFormTableName(String formTableName) {
        this.formTableName = formTableName;
    }

    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getFormTypeGuid() {
        return this.formTypeGuid;
    }

    public void setFormTypeGuid(String formTypeGuid) {
        this.formTypeGuid = formTypeGuid;
    }

    public String getBasementFee() {
        return this.basementFee;
    }

    public void setBasementFee(String basementFee) {
        this.basementFee = basementFee;
    }

    public String getBasementFeeAccountingCode() {
        return this.basementFeeAccountingCode;
    }

    public void setBasementFeeAccountingCode(String basementFeeAccountingCode) {
        this.basementFeeAccountingCode = basementFeeAccountingCode;
    }

    public Boolean getVendorSelection() {
        return this.vendorSelection;
    }

    public void setVendorSelection(Boolean vendorSelection) {
        this.vendorSelection = vendorSelection;
    }

    public String getTitlePrefix() {
        return this.titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    public String getPrefixDate() {
        return this.prefixDate;
    }

    public void setPrefixDate(String prefixDate) {
        this.prefixDate = prefixDate;
    }

    public String getPrefixNumber() {
        return this.prefixNumber;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    public BigInteger getPrefixNumberStart() {
        return this.prefixNumberStart;
    }

    public void setPrefixNumberStart(BigInteger prefixNumberStart) {
        this.prefixNumberStart = prefixNumberStart;
    }

    public Integer getPrefixNumberStep() {
        return this.prefixNumberStep;
    }

    public void setPrefixNumberStep(Integer prefixNumberStep) {
        this.prefixNumberStep = prefixNumberStep;
    }

    public Boolean getPrefixDashes() {
        return this.prefixDashes;
    }

    public void setPrefixDashes(Boolean prefixDashes) {
        this.prefixDashes = prefixDashes;
    }

    public BigInteger getCurrentPrefixNumber() {
        return this.currentPrefixNumber;
    }

    public void setCurrentPrefixNumber(BigInteger currentPrefixNumber) {
        this.currentPrefixNumber = currentPrefixNumber;
    }

    public Integer getPrefixNumberResetOn() {
        return this.prefixNumberResetOn;
    }

    public void setPrefixNumberResetOn(Integer prefixNumberResetOn) {
        this.prefixNumberResetOn = prefixNumberResetOn;
    }

    public Boolean getMultipleVendors() {
        return this.multipleVendors;
    }

    public void setMultipleVendors(Boolean multipleVendors) {
        this.multipleVendors = multipleVendors;
    }

    public Boolean getRequireOwner() {
        return this.requireOwner;
    }

    public void setRequireOwner(Boolean requireOwner) {
        this.requireOwner = requireOwner;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean getForceInspectionSequence() {
        return this.forceInspectionSequence;
    }

    public void setForceInspectionSequence(Boolean forceInspectionSequence) {
        this.forceInspectionSequence = forceInspectionSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetFormTypesByCategoriesAndMunicipalitiesResponse)) return false;
        final GetFormTypesByCategoriesAndMunicipalitiesResponse getFormTypesByCategoriesAndMunicipalitiesResponse = (GetFormTypesByCategoriesAndMunicipalitiesResponse) o;
        return Objects.equals(getId(), getFormTypesByCategoriesAndMunicipalitiesResponse.getId()) &&
                Objects.equals(getFormType(), getFormTypesByCategoriesAndMunicipalitiesResponse.getFormType()) &&
                Objects.equals(getFlatFee(), getFormTypesByCategoriesAndMunicipalitiesResponse.getFlatFee()) &&
                Objects.equals(getFlatFeeAccountingCode(), getFormTypesByCategoriesAndMunicipalitiesResponse.getFlatFeeAccountingCode()) &&
                Objects.equals(getSfFee(), getFormTypesByCategoriesAndMunicipalitiesResponse.getSfFee()) &&
                Objects.equals(getSfFeeAccountingCode(), getFormTypesByCategoriesAndMunicipalitiesResponse.getSfFeeAccountingCode()) &&
                Objects.equals(getUnitFee(), getFormTypesByCategoriesAndMunicipalitiesResponse.getUnitFee()) &&
                Objects.equals(getUnitFeeAccountingCode(), getFormTypesByCategoriesAndMunicipalitiesResponse.getUnitFeeAccountingCode()) &&
                Objects.equals(getStateFee(), getFormTypesByCategoriesAndMunicipalitiesResponse.getStateFee()) &&
                Objects.equals(getStateFeeAccountingCode(), getFormTypesByCategoriesAndMunicipalitiesResponse.getStateFeeAccountingCode()) &&
                Objects.equals(getReport(), getFormTypesByCategoriesAndMunicipalitiesResponse.getReport()) &&
                Objects.equals(getActive(), getFormTypesByCategoriesAndMunicipalitiesResponse.getActive()) &&
                Objects.equals(getMunicipalityInternalForm(), getFormTypesByCategoriesAndMunicipalitiesResponse.getMunicipalityInternalForm()) &&
                Objects.equals(getCreatedDate(), getFormTypesByCategoriesAndMunicipalitiesResponse.getCreatedDate()) &&
                Objects.equals(getCollectFees(), getFormTypesByCategoriesAndMunicipalitiesResponse.getCollectFees()) &&
                Objects.equals(getAutomaticFees(), getFormTypesByCategoriesAndMunicipalitiesResponse.getAutomaticFees()) &&
                Objects.equals(getDigitalSignatures(), getFormTypesByCategoriesAndMunicipalitiesResponse.getDigitalSignatures()) &&
                Objects.equals(getInspections(), getFormTypesByCategoriesAndMunicipalitiesResponse.getInspections()) &&
                Objects.equals(getPayments(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPayments()) &&
                Objects.equals(getSharedWith(), getFormTypesByCategoriesAndMunicipalitiesResponse.getSharedWith()) &&
                Objects.equals(getAttachments(), getFormTypesByCategoriesAndMunicipalitiesResponse.getAttachments()) &&
                Objects.equals(getGisrecord(), getFormTypesByCategoriesAndMunicipalitiesResponse.getGisrecord()) &&
                Objects.equals(getMultipleGisrecords(), getFormTypesByCategoriesAndMunicipalitiesResponse.getMultipleGisrecords()) &&
                Objects.equals(getGismap(), getFormTypesByCategoriesAndMunicipalitiesResponse.getGismap()) &&
                Objects.equals(getFormTableName(), getFormTypesByCategoriesAndMunicipalitiesResponse.getFormTableName()) &&
                Objects.equals(getMunicipalityId(), getFormTypesByCategoriesAndMunicipalitiesResponse.getMunicipalityId()) &&
                Objects.equals(getFormTypeGuid(), getFormTypesByCategoriesAndMunicipalitiesResponse.getFormTypeGuid()) &&
                Objects.equals(getBasementFee(), getFormTypesByCategoriesAndMunicipalitiesResponse.getBasementFee()) &&
                Objects.equals(getBasementFeeAccountingCode(), getFormTypesByCategoriesAndMunicipalitiesResponse.getBasementFeeAccountingCode()) &&
                Objects.equals(getVendorSelection(), getFormTypesByCategoriesAndMunicipalitiesResponse.getVendorSelection()) &&
                Objects.equals(getTitlePrefix(), getFormTypesByCategoriesAndMunicipalitiesResponse.getTitlePrefix()) &&
                Objects.equals(getPrefixDate(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPrefixDate()) &&
                Objects.equals(getPrefixNumber(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPrefixNumber()) &&
                Objects.equals(getPrefixNumberStart(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPrefixNumberStart()) &&
                Objects.equals(getPrefixNumberStep(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPrefixNumberStep()) &&
                Objects.equals(getPrefixDashes(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPrefixDashes()) &&
                Objects.equals(getCurrentPrefixNumber(), getFormTypesByCategoriesAndMunicipalitiesResponse.getCurrentPrefixNumber()) &&
                Objects.equals(getPrefixNumberResetOn(), getFormTypesByCategoriesAndMunicipalitiesResponse.getPrefixNumberResetOn()) &&
                Objects.equals(getMultipleVendors(), getFormTypesByCategoriesAndMunicipalitiesResponse.getMultipleVendors()) &&
                Objects.equals(getRequireOwner(), getFormTypesByCategoriesAndMunicipalitiesResponse.getRequireOwner()) &&
                Objects.equals(getInstructions(), getFormTypesByCategoriesAndMunicipalitiesResponse.getInstructions()) &&
                Objects.equals(getForceInspectionSequence(), getFormTypesByCategoriesAndMunicipalitiesResponse.getForceInspectionSequence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getFormType(),
                getFlatFee(),
                getFlatFeeAccountingCode(),
                getSfFee(),
                getSfFeeAccountingCode(),
                getUnitFee(),
                getUnitFeeAccountingCode(),
                getStateFee(),
                getStateFeeAccountingCode(),
                getReport(),
                getActive(),
                getMunicipalityInternalForm(),
                getCreatedDate(),
                getCollectFees(),
                getAutomaticFees(),
                getDigitalSignatures(),
                getInspections(),
                getPayments(),
                getSharedWith(),
                getAttachments(),
                getGisrecord(),
                getMultipleGisrecords(),
                getGismap(),
                getFormTableName(),
                getMunicipalityId(),
                getFormTypeGuid(),
                getBasementFee(),
                getBasementFeeAccountingCode(),
                getVendorSelection(),
                getTitlePrefix(),
                getPrefixDate(),
                getPrefixNumber(),
                getPrefixNumberStart(),
                getPrefixNumberStep(),
                getPrefixDashes(),
                getCurrentPrefixNumber(),
                getPrefixNumberResetOn(),
                getMultipleVendors(),
                getRequireOwner(),
                getInstructions(),
                getForceInspectionSequence());
    }
}
