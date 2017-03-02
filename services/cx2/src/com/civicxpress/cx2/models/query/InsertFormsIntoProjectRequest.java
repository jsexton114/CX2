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

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertFormsIntoProjectRequest implements Serializable {

    @JsonProperty("RelatedProjectGuid")
    private String relatedProjectGuid;
    @JsonProperty("RelatedFormGUID")
    private String relatedFormGuid;
    @JsonProperty("AddedByUser")
    private Integer addedByUser;
    @JsonProperty("AddedAt")
    private Timestamp addedAt;
    @JsonProperty("Comments")
    private String comments;

    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    public Integer getAddedByUser() {
        return this.addedByUser;
    }

    public void setAddedByUser(Integer addedByUser) {
        this.addedByUser = addedByUser;
    }

    public Timestamp getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertFormsIntoProjectRequest)) return false;
        final InsertFormsIntoProjectRequest insertFormsIntoProjectRequest = (InsertFormsIntoProjectRequest) o;
        return Objects.equals(getRelatedProjectGuid(), insertFormsIntoProjectRequest.getRelatedProjectGuid()) &&
                Objects.equals(getRelatedFormGuid(), insertFormsIntoProjectRequest.getRelatedFormGuid()) &&
                Objects.equals(getAddedByUser(), insertFormsIntoProjectRequest.getAddedByUser()) &&
                Objects.equals(getAddedAt(), insertFormsIntoProjectRequest.getAddedAt()) &&
                Objects.equals(getComments(), insertFormsIntoProjectRequest.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRelatedProjectGuid(),
                getRelatedFormGuid(),
                getAddedByUser(),
                getAddedAt(),
                getComments());
    }
}