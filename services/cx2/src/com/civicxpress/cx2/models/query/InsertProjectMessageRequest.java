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

public class InsertProjectMessageRequest implements Serializable {

    @JsonProperty("UserId")
    private Integer userId;
    @JsonProperty("RelatedProjectGUID")
    private String relatedProjectGuid;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("PostedAt")
    private Timestamp postedAt;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getPostedAt() {
        return this.postedAt;
    }

    public void setPostedAt(Timestamp postedAt) {
        this.postedAt = postedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertProjectMessageRequest)) return false;
        final InsertProjectMessageRequest insertProjectMessageRequest = (InsertProjectMessageRequest) o;
        return Objects.equals(getUserId(), insertProjectMessageRequest.getUserId()) &&
                Objects.equals(getRelatedProjectGuid(), insertProjectMessageRequest.getRelatedProjectGuid()) &&
                Objects.equals(getMessage(), insertProjectMessageRequest.getMessage()) &&
                Objects.equals(getPostedAt(), insertProjectMessageRequest.getPostedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(),
                getRelatedProjectGuid(),
                getMessage(),
                getPostedAt());
    }
}
