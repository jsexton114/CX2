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
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class AllFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse implements Serializable {


    @ColumnAlias("feeId")
    private Integer feeId;

    @ColumnAlias("formGuid")
    private String formGuid;

    @ColumnAlias("formTitle")
    private String formTitle;

    @ColumnAlias("FormType")
    private String formType;

    @ColumnAlias("municipality")
    private String municipality;

    @ColumnAlias("address")
    private String address;

    @ColumnAlias("feeType")
    private String feeType;

    @ColumnAlias("amount")
    private BigDecimal amount;

    public Integer getFeeId() {
        return this.feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getMunicipality() {
        return this.municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFeeType() {
        return this.feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse)) return false;
        final AllFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse = (AllFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse) o;
        return Objects.equals(getFeeId(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getFeeId()) &&
                Objects.equals(getFormGuid(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getFormGuid()) &&
                Objects.equals(getFormTitle(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getFormTitle()) &&
                Objects.equals(getFormType(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getFormType()) &&
                Objects.equals(getMunicipality(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getMunicipality()) &&
                Objects.equals(getAddress(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getAddress()) &&
                Objects.equals(getFeeType(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getFeeType()) &&
                Objects.equals(getAmount(), allFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeeId(),
                getFormGuid(),
                getFormTitle(),
                getFormType(),
                getMunicipality(),
                getAddress(),
                getFeeType(),
                getAmount());
    }
}
