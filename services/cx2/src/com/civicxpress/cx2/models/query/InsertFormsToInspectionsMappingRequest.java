/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertFormsToInspectionsMappingRequest implements Serializable {

    @NotNull
    @JsonProperty("RelatedFormGUID")
    private String relatedFormGuid;
    @NotNull
    @JsonProperty("RelatedInspectionGUID")
    private String relatedInspectionGuid;
    @NotNull
    @JsonProperty("AddedBy")
    private Integer addedBy;

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    public String getRelatedInspectionGuid() {
        return this.relatedInspectionGuid;
    }

    public void setRelatedInspectionGuid(String relatedInspectionGuid) {
        this.relatedInspectionGuid = relatedInspectionGuid;
    }

    public Integer getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertFormsToInspectionsMappingRequest)) return false;
        final InsertFormsToInspectionsMappingRequest insertFormsToInspectionsMappingRequest = (InsertFormsToInspectionsMappingRequest) o;
        return Objects.equals(getRelatedFormGuid(), insertFormsToInspectionsMappingRequest.getRelatedFormGuid()) &&
                Objects.equals(getRelatedInspectionGuid(), insertFormsToInspectionsMappingRequest.getRelatedInspectionGuid()) &&
                Objects.equals(getAddedBy(), insertFormsToInspectionsMappingRequest.getAddedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRelatedFormGuid(),
                getRelatedInspectionGuid(),
                getAddedBy());
    }
}
