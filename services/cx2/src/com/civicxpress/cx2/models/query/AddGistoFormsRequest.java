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

public class AddGistoFormsRequest implements Serializable {


    @JsonProperty("GISRecordId")
    private Integer gisrecordId;

    @JsonProperty("RelatedFormGUID")
    private String relatedFormGuid;

    @JsonProperty("AddedBy")
    private Integer addedBy;

    @JsonProperty("AddedTime")
    private Timestamp addedTime;

    public Integer getGisrecordId() {
        return this.gisrecordId;
    }

    public void setGisrecordId(Integer gisrecordId) {
        this.gisrecordId = gisrecordId;
    }

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    public Integer getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public Timestamp getAddedTime() {
        return this.addedTime;
    }

    public void setAddedTime(Timestamp addedTime) {
        this.addedTime = addedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddGistoFormsRequest)) return false;
        final AddGistoFormsRequest addGistoFormsRequest = (AddGistoFormsRequest) o;
        return Objects.equals(getGisrecordId(), addGistoFormsRequest.getGisrecordId()) &&
                Objects.equals(getRelatedFormGuid(), addGistoFormsRequest.getRelatedFormGuid()) &&
                Objects.equals(getAddedBy(), addGistoFormsRequest.getAddedBy()) &&
                Objects.equals(getAddedTime(), addGistoFormsRequest.getAddedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGisrecordId(),
                getRelatedFormGuid(),
                getAddedBy(),
                getAddedTime());
    }
}
