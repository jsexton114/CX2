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
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class FormsForUsersAndSharedResponse implements Serializable {

    @JsonProperty("formGuid")
    @ColumnAlias("formGuid")
    private String formGuid;
    @JsonProperty("formTitle")
    @ColumnAlias("formTitle")
    private String formTitle;
    @JsonProperty("municipalityName")
    @ColumnAlias("municipalityName")
    private String municipalityName;
    @JsonProperty("createdBy")
    @ColumnAlias("createdBy")
    private String createdBy;
    @JsonProperty("formDesign")
    @ColumnAlias("formDesign")
    private String formDesign;
    @JsonProperty("formStatus")
    @ColumnAlias("formStatus")
    private String formStatus;
    @JsonProperty("lot")
    @ColumnAlias("lot")
    private String lot;
    @JsonProperty("subdivision")
    @ColumnAlias("subdivision")
    private String subdivision;
    @JsonProperty("address")
    @ColumnAlias("address")
    private String address;
    @JsonProperty("primaryVendor")
    @ColumnAlias("primaryVendor")
    private String primaryVendor;
    @JsonProperty("balanceDue")
    @ColumnAlias("balanceDue")
    private String balanceDue;

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

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFormDesign() {
        return this.formDesign;
    }

    public void setFormDesign(String formDesign) {
        this.formDesign = formDesign;
    }

    public String getFormStatus() {
        return this.formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public String getLot() {
        return this.lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getSubdivision() {
        return this.subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrimaryVendor() {
        return this.primaryVendor;
    }

    public void setPrimaryVendor(String primaryVendor) {
        this.primaryVendor = primaryVendor;
    }

    public String getBalanceDue() {
        return this.balanceDue;
    }

    public void setBalanceDue(String balanceDue) {
        this.balanceDue = balanceDue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormsForUsersAndSharedResponse)) return false;
        final FormsForUsersAndSharedResponse formsForUsersAndSharedResponse = (FormsForUsersAndSharedResponse) o;
        return Objects.equals(getFormGuid(), formsForUsersAndSharedResponse.getFormGuid()) &&
                Objects.equals(getFormTitle(), formsForUsersAndSharedResponse.getFormTitle()) &&
                Objects.equals(getMunicipalityName(), formsForUsersAndSharedResponse.getMunicipalityName()) &&
                Objects.equals(getCreatedBy(), formsForUsersAndSharedResponse.getCreatedBy()) &&
                Objects.equals(getFormDesign(), formsForUsersAndSharedResponse.getFormDesign()) &&
                Objects.equals(getFormStatus(), formsForUsersAndSharedResponse.getFormStatus()) &&
                Objects.equals(getLot(), formsForUsersAndSharedResponse.getLot()) &&
                Objects.equals(getSubdivision(), formsForUsersAndSharedResponse.getSubdivision()) &&
                Objects.equals(getAddress(), formsForUsersAndSharedResponse.getAddress()) &&
                Objects.equals(getPrimaryVendor(), formsForUsersAndSharedResponse.getPrimaryVendor()) &&
                Objects.equals(getBalanceDue(), formsForUsersAndSharedResponse.getBalanceDue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormGuid(),
                getFormTitle(),
                getMunicipalityName(),
                getCreatedBy(),
                getFormDesign(),
                getFormStatus(),
                getLot(),
                getSubdivision(),
                getAddress(),
                getPrimaryVendor(),
                getBalanceDue());
    }
}
