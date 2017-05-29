/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertMasterInspectionsRequest implements Serializable {

    @NotNull
    @JsonProperty("InspectionGuid")
    private String inspectionGuid;
    @NotNull
    @JsonProperty("InspectionTitle")
    private String inspectionTitle;
    @NotNull
    @JsonProperty("InspectionDesignId")
    private Integer inspectionDesignId;
    @NotNull
    @JsonProperty("FormGuid")
    private String formGuid;
    @NotNull
    @JsonProperty("RequestedBy")
    private Integer requestedBy;
    @NotNull
    @JsonProperty("DateRequested")
    private LocalDateTime dateRequested;
    @JsonProperty("RequestedFor")
    private LocalDateTime requestedFor;

    public String getInspectionGuid() {
        return this.inspectionGuid;
    }

    public void setInspectionGuid(String inspectionGuid) {
        this.inspectionGuid = inspectionGuid;
    }

    public String getInspectionTitle() {
        return this.inspectionTitle;
    }

    public void setInspectionTitle(String inspectionTitle) {
        this.inspectionTitle = inspectionTitle;
    }

    public Integer getInspectionDesignId() {
        return this.inspectionDesignId;
    }

    public void setInspectionDesignId(Integer inspectionDesignId) {
        this.inspectionDesignId = inspectionDesignId;
    }

    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    public Integer getRequestedBy() {
        return this.requestedBy;
    }

    public void setRequestedBy(Integer requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDateTime getDateRequested() {
        return this.dateRequested;
    }

    public void setDateRequested(LocalDateTime dateRequested) {
        this.dateRequested = dateRequested;
    }

    public LocalDateTime getRequestedFor() {
        return this.requestedFor;
    }

    public void setRequestedFor(LocalDateTime requestedFor) {
        this.requestedFor = requestedFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertMasterInspectionsRequest)) return false;
        final InsertMasterInspectionsRequest insertMasterInspectionsRequest = (InsertMasterInspectionsRequest) o;
        return Objects.equals(getInspectionGuid(), insertMasterInspectionsRequest.getInspectionGuid()) &&
                Objects.equals(getInspectionTitle(), insertMasterInspectionsRequest.getInspectionTitle()) &&
                Objects.equals(getInspectionDesignId(), insertMasterInspectionsRequest.getInspectionDesignId()) &&
                Objects.equals(getFormGuid(), insertMasterInspectionsRequest.getFormGuid()) &&
                Objects.equals(getRequestedBy(), insertMasterInspectionsRequest.getRequestedBy()) &&
                Objects.equals(getDateRequested(), insertMasterInspectionsRequest.getDateRequested()) &&
                Objects.equals(getRequestedFor(), insertMasterInspectionsRequest.getRequestedFor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInspectionGuid(),
                getInspectionTitle(),
                getInspectionDesignId(),
                getFormGuid(),
                getRequestedBy(),
                getDateRequested(),
                getRequestedFor());
    }
}
