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
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetFormsForMunicpalityResponse implements Serializable {

    @JsonProperty("Active")
    @ColumnAlias("Active")
    private Boolean active;
    @JsonProperty("Attachments")
    @ColumnAlias("Attachments")
    private String attachments;
    @JsonProperty("AutomaticFees")
    @ColumnAlias("AutomaticFees")
    private String automaticFees;
    @JsonProperty("CollectFees")
    @ColumnAlias("CollectFees")
    private String collectFees;
    @JsonProperty("CreatedDate")
    @ColumnAlias("CreatedDate")
    private java.sql.Date createdDate;
    @JsonProperty("DigitalSignatures")
    @ColumnAlias("DigitalSignatures")
    private String digitalSignatures;
    @JsonProperty("FlatFee")
    @ColumnAlias("FlatFee")
    private String flatFee;
    @JsonProperty("FlatFeeAccountingCode")
    @ColumnAlias("FlatFeeAccountingCode")
    private String flatFeeAccountingCode;
    @JsonProperty("FormTableName")
    @ColumnAlias("FormTableName")
    private String formTableName;
    @JsonProperty("FormType")
    @ColumnAlias("FormType")
    private String formType;
    @JsonProperty("FormTypeGuid")
    @ColumnAlias("FormTypeGuid")
    private String formTypeGuid;
    @JsonProperty("GISMap")
    @ColumnAlias("GISMap")
    private String gismap;
    @JsonProperty("GISRecord")
    @ColumnAlias("GISRecord")
    private String gisrecord;
    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;
    @JsonProperty("Inspections")
    @ColumnAlias("Inspections")
    private String inspections;
    @JsonProperty("MultipleGISRecords")
    @ColumnAlias("MultipleGISRecords")
    private Boolean multipleGisrecords;
    @JsonProperty("MunicipalityId")
    @ColumnAlias("MunicipalityId")
    private BigDecimal municipalityId;
    @JsonProperty("MunicipalityInternalForm")
    @ColumnAlias("MunicipalityInternalForm")
    private Boolean municipalityInternalForm;
    @JsonProperty("PageName")
    @ColumnAlias("PageName")
    private String pageName;
    @JsonProperty("Payments")
    @ColumnAlias("Payments")
    private String payments;
    @JsonProperty("Report")
    @ColumnAlias("Report")
    private String report;
    @JsonProperty("SfFee")
    @ColumnAlias("SfFee")
    private String sfFee;
    @JsonProperty("SfFeeAccountingCode")
    @ColumnAlias("SfFeeAccountingCode")
    private String sfFeeAccountingCode;
    @JsonProperty("SharedWith")
    @ColumnAlias("SharedWith")
    private String sharedWith;
    @JsonProperty("StateFee")
    @ColumnAlias("StateFee")
    private String stateFee;
    @JsonProperty("StateFeeAccountingCode")
    @ColumnAlias("StateFeeAccountingCode")
    private String stateFeeAccountingCode;
    @JsonProperty("TbLocation")
    @ColumnAlias("TbLocation")
    private String tbLocation;
    @JsonProperty("UnitFee")
    @ColumnAlias("UnitFee")
    private String unitFee;
    @JsonProperty("UnitFeeAccountingCode")
    @ColumnAlias("UnitFeeAccountingCode")
    private String unitFeeAccountingCode;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAttachments() {
        return this.attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getAutomaticFees() {
        return this.automaticFees;
    }

    public void setAutomaticFees(String automaticFees) {
        this.automaticFees = automaticFees;
    }

    public String getCollectFees() {
        return this.collectFees;
    }

    public void setCollectFees(String collectFees) {
        this.collectFees = collectFees;
    }

    public java.sql.Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDigitalSignatures() {
        return this.digitalSignatures;
    }

    public void setDigitalSignatures(String digitalSignatures) {
        this.digitalSignatures = digitalSignatures;
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

    public String getFormTableName() {
        return this.formTableName;
    }

    public void setFormTableName(String formTableName) {
        this.formTableName = formTableName;
    }

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormTypeGuid() {
        return this.formTypeGuid;
    }

    public void setFormTypeGuid(String formTypeGuid) {
        this.formTypeGuid = formTypeGuid;
    }

    public String getGismap() {
        return this.gismap;
    }

    public void setGismap(String gismap) {
        this.gismap = gismap;
    }

    public String getGisrecord() {
        return this.gisrecord;
    }

    public void setGisrecord(String gisrecord) {
        this.gisrecord = gisrecord;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getInspections() {
        return this.inspections;
    }

    public void setInspections(String inspections) {
        this.inspections = inspections;
    }

    public Boolean getMultipleGisrecords() {
        return this.multipleGisrecords;
    }

    public void setMultipleGisrecords(Boolean multipleGisrecords) {
        this.multipleGisrecords = multipleGisrecords;
    }

    public BigDecimal getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(BigDecimal municipalityId) {
        this.municipalityId = municipalityId;
    }

    public Boolean getMunicipalityInternalForm() {
        return this.municipalityInternalForm;
    }

    public void setMunicipalityInternalForm(Boolean municipalityInternalForm) {
        this.municipalityInternalForm = municipalityInternalForm;
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPayments() {
        return this.payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
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

    public String getSharedWith() {
        return this.sharedWith;
    }

    public void setSharedWith(String sharedWith) {
        this.sharedWith = sharedWith;
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

    public String getTbLocation() {
        return this.tbLocation;
    }

    public void setTbLocation(String tbLocation) {
        this.tbLocation = tbLocation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetFormsForMunicpalityResponse)) return false;
        final GetFormsForMunicpalityResponse getFormsForMunicpalityResponse = (GetFormsForMunicpalityResponse) o;
        return Objects.equals(getActive(), getFormsForMunicpalityResponse.getActive()) &&
                Objects.equals(getAttachments(), getFormsForMunicpalityResponse.getAttachments()) &&
                Objects.equals(getAutomaticFees(), getFormsForMunicpalityResponse.getAutomaticFees()) &&
                Objects.equals(getCollectFees(), getFormsForMunicpalityResponse.getCollectFees()) &&
                Objects.equals(getCreatedDate(), getFormsForMunicpalityResponse.getCreatedDate()) &&
                Objects.equals(getDigitalSignatures(), getFormsForMunicpalityResponse.getDigitalSignatures()) &&
                Objects.equals(getFlatFee(), getFormsForMunicpalityResponse.getFlatFee()) &&
                Objects.equals(getFlatFeeAccountingCode(), getFormsForMunicpalityResponse.getFlatFeeAccountingCode()) &&
                Objects.equals(getFormTableName(), getFormsForMunicpalityResponse.getFormTableName()) &&
                Objects.equals(getFormType(), getFormsForMunicpalityResponse.getFormType()) &&
                Objects.equals(getFormTypeGuid(), getFormsForMunicpalityResponse.getFormTypeGuid()) &&
                Objects.equals(getGismap(), getFormsForMunicpalityResponse.getGismap()) &&
                Objects.equals(getGisrecord(), getFormsForMunicpalityResponse.getGisrecord()) &&
                Objects.equals(getId(), getFormsForMunicpalityResponse.getId()) &&
                Objects.equals(getInspections(), getFormsForMunicpalityResponse.getInspections()) &&
                Objects.equals(getMultipleGisrecords(), getFormsForMunicpalityResponse.getMultipleGisrecords()) &&
                Objects.equals(getMunicipalityId(), getFormsForMunicpalityResponse.getMunicipalityId()) &&
                Objects.equals(getMunicipalityInternalForm(), getFormsForMunicpalityResponse.getMunicipalityInternalForm()) &&
                Objects.equals(getPageName(), getFormsForMunicpalityResponse.getPageName()) &&
                Objects.equals(getPayments(), getFormsForMunicpalityResponse.getPayments()) &&
                Objects.equals(getReport(), getFormsForMunicpalityResponse.getReport()) &&
                Objects.equals(getSfFee(), getFormsForMunicpalityResponse.getSfFee()) &&
                Objects.equals(getSfFeeAccountingCode(), getFormsForMunicpalityResponse.getSfFeeAccountingCode()) &&
                Objects.equals(getSharedWith(), getFormsForMunicpalityResponse.getSharedWith()) &&
                Objects.equals(getStateFee(), getFormsForMunicpalityResponse.getStateFee()) &&
                Objects.equals(getStateFeeAccountingCode(), getFormsForMunicpalityResponse.getStateFeeAccountingCode()) &&
                Objects.equals(getTbLocation(), getFormsForMunicpalityResponse.getTbLocation()) &&
                Objects.equals(getUnitFee(), getFormsForMunicpalityResponse.getUnitFee()) &&
                Objects.equals(getUnitFeeAccountingCode(), getFormsForMunicpalityResponse.getUnitFeeAccountingCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActive(),
                getAttachments(),
                getAutomaticFees(),
                getCollectFees(),
                getCreatedDate(),
                getDigitalSignatures(),
                getFlatFee(),
                getFlatFeeAccountingCode(),
                getFormTableName(),
                getFormType(),
                getFormTypeGuid(),
                getGismap(),
                getGisrecord(),
                getId(),
                getInspections(),
                getMultipleGisrecords(),
                getMunicipalityId(),
                getMunicipalityInternalForm(),
                getPageName(),
                getPayments(),
                getReport(),
                getSfFee(),
                getSfFeeAccountingCode(),
                getSharedWith(),
                getStateFee(),
                getStateFeeAccountingCode(),
                getTbLocation(),
                getUnitFee(),
                getUnitFeeAccountingCode());
    }
}
