/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class AssignedInspectionsLessThanDateResponse implements Serializable {

    @ColumnAlias("inspectionGuid")
    private String inspectionGuid;
    @ColumnAlias("assignedTo")
    private Integer assignedTo;
    @ColumnAlias("assignedToPersonName")
    private String assignedToPersonName;
    @ColumnAlias("outcome")
    private String outcome;
    @ColumnAlias("inspectionTitle")
    private String inspectionTitle;
    @ColumnAlias("inspectionZone")
    private String inspectionZone;
    @ColumnAlias("requestedFor")
    private WMPersistentLocalDateTime requestedFor;
    @ColumnAlias("fullAddress")
    private String fullAddress;
    @ColumnAlias("lot")
    private String lot;
    @ColumnAlias("subdivision")
    private String subdivision;
    @ColumnAlias("fullName")
    private String fullName;
    @ColumnAlias("inspectDesignName")
    private String inspectDesignName;

    public String getInspectionGuid() {
        return this.inspectionGuid;
    }

    public void setInspectionGuid(String inspectionGuid) {
        this.inspectionGuid = inspectionGuid;
    }

    public Integer getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedToPersonName() {
        return this.assignedToPersonName;
    }

    public void setAssignedToPersonName(String assignedToPersonName) {
        this.assignedToPersonName = assignedToPersonName;
    }

    public String getOutcome() {
        return this.outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getInspectionTitle() {
        return this.inspectionTitle;
    }

    public void setInspectionTitle(String inspectionTitle) {
        this.inspectionTitle = inspectionTitle;
    }

    public String getInspectionZone() {
        return this.inspectionZone;
    }

    public void setInspectionZone(String inspectionZone) {
        this.inspectionZone = inspectionZone;
    }

    public WMPersistentLocalDateTime getRequestedFor() {
        return this.requestedFor;
    }

    public void setRequestedFor(WMPersistentLocalDateTime requestedFor) {
        this.requestedFor = requestedFor;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
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

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInspectDesignName() {
        return this.inspectDesignName;
    }

    public void setInspectDesignName(String inspectDesignName) {
        this.inspectDesignName = inspectDesignName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignedInspectionsLessThanDateResponse)) return false;
        final AssignedInspectionsLessThanDateResponse assignedInspectionsLessThanDateResponse = (AssignedInspectionsLessThanDateResponse) o;
        return Objects.equals(getInspectionGuid(), assignedInspectionsLessThanDateResponse.getInspectionGuid()) &&
                Objects.equals(getAssignedTo(), assignedInspectionsLessThanDateResponse.getAssignedTo()) &&
                Objects.equals(getAssignedToPersonName(), assignedInspectionsLessThanDateResponse.getAssignedToPersonName()) &&
                Objects.equals(getOutcome(), assignedInspectionsLessThanDateResponse.getOutcome()) &&
                Objects.equals(getInspectionTitle(), assignedInspectionsLessThanDateResponse.getInspectionTitle()) &&
                Objects.equals(getInspectionZone(), assignedInspectionsLessThanDateResponse.getInspectionZone()) &&
                Objects.equals(getRequestedFor(), assignedInspectionsLessThanDateResponse.getRequestedFor()) &&
                Objects.equals(getFullAddress(), assignedInspectionsLessThanDateResponse.getFullAddress()) &&
                Objects.equals(getLot(), assignedInspectionsLessThanDateResponse.getLot()) &&
                Objects.equals(getSubdivision(), assignedInspectionsLessThanDateResponse.getSubdivision()) &&
                Objects.equals(getFullName(), assignedInspectionsLessThanDateResponse.getFullName()) &&
                Objects.equals(getInspectDesignName(), assignedInspectionsLessThanDateResponse.getInspectDesignName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInspectionGuid(),
                getAssignedTo(),
                getAssignedToPersonName(),
                getOutcome(),
                getInspectionTitle(),
                getInspectionZone(),
                getRequestedFor(),
                getFullAddress(),
                getLot(),
                getSubdivision(),
                getFullName(),
                getInspectDesignName());
    }
}
