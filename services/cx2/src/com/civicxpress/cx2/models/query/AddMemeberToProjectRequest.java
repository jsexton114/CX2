/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddMemeberToProjectRequest implements Serializable {


    @JsonProperty("RelatedProjectGUID")
    private String relatedProjectGuid;

    @JsonProperty("ProjectSharedOn")
    private Timestamp projectSharedOn;

    @JsonProperty("ProjectSharedWith")
    private Integer projectSharedWith;

    @JsonProperty("ProjectSharedBy")
    private Integer projectSharedBy;

    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    public Timestamp getProjectSharedOn() {
        return this.projectSharedOn;
    }

    public void setProjectSharedOn(Timestamp projectSharedOn) {
        this.projectSharedOn = projectSharedOn;
    }

    public Integer getProjectSharedWith() {
        return this.projectSharedWith;
    }

    public void setProjectSharedWith(Integer projectSharedWith) {
        this.projectSharedWith = projectSharedWith;
    }

    public Integer getProjectSharedBy() {
        return this.projectSharedBy;
    }

    public void setProjectSharedBy(Integer projectSharedBy) {
        this.projectSharedBy = projectSharedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddMemeberToProjectRequest)) return false;
        final AddMemeberToProjectRequest addMemeberToProjectRequest = (AddMemeberToProjectRequest) o;
        return Objects.equals(getRelatedProjectGuid(), addMemeberToProjectRequest.getRelatedProjectGuid()) &&
                Objects.equals(getProjectSharedOn(), addMemeberToProjectRequest.getProjectSharedOn()) &&
                Objects.equals(getProjectSharedWith(), addMemeberToProjectRequest.getProjectSharedWith()) &&
                Objects.equals(getProjectSharedBy(), addMemeberToProjectRequest.getProjectSharedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRelatedProjectGuid(),
                getProjectSharedOn(),
                getProjectSharedWith(),
                getProjectSharedBy());
    }
}
