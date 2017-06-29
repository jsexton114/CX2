/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertNewRoleRequest implements Serializable {


    @JsonProperty("RoleName")
    private String roleName;

    @JsonProperty("MunicipalityId")
    private Integer municipalityId;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("UserId")
    private Integer userId;

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertNewRoleRequest)) return false;
        final InsertNewRoleRequest insertNewRoleRequest = (InsertNewRoleRequest) o;
        return Objects.equals(getRoleName(), insertNewRoleRequest.getRoleName()) &&
                Objects.equals(getMunicipalityId(), insertNewRoleRequest.getMunicipalityId()) &&
                Objects.equals(getDescription(), insertNewRoleRequest.getDescription()) &&
                Objects.equals(getUserId(), insertNewRoleRequest.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleName(),
                getMunicipalityId(),
                getDescription(),
                getUserId());
    }
}
