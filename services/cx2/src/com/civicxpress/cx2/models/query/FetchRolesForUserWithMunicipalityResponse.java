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
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class FetchRolesForUserWithMunicipalityResponse implements Serializable {

    @JsonProperty("Description")
    @ColumnAlias("Description")
    private String description;
    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;
    @JsonProperty("MunicipalityId")
    @ColumnAlias("MunicipalityId")
    private BigDecimal municipalityId;
    @JsonProperty("RoleName")
    @ColumnAlias("RoleName")
    private String roleName;
    @JsonProperty("UserId")
    @ColumnAlias("UserId")
    private BigDecimal userId;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(BigDecimal municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public BigDecimal getUserId() {
        return this.userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FetchRolesForUserWithMunicipalityResponse)) return false;
        final FetchRolesForUserWithMunicipalityResponse fetchRolesForUserWithMunicipalityResponse = (FetchRolesForUserWithMunicipalityResponse) o;
        return Objects.equals(getDescription(), fetchRolesForUserWithMunicipalityResponse.getDescription()) &&
                Objects.equals(getId(), fetchRolesForUserWithMunicipalityResponse.getId()) &&
                Objects.equals(getMunicipalityId(), fetchRolesForUserWithMunicipalityResponse.getMunicipalityId()) &&
                Objects.equals(getRoleName(), fetchRolesForUserWithMunicipalityResponse.getRoleName()) &&
                Objects.equals(getUserId(), fetchRolesForUserWithMunicipalityResponse.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(),
                getId(),
                getMunicipalityId(),
                getRoleName(),
                getUserId());
    }
}
