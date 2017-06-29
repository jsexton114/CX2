/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class MunicipalityProjectsResponse implements Serializable {


    @JsonProperty("active")
    @ColumnAlias("active")
    private Boolean active;

    @JsonProperty("createdBy")
    @ColumnAlias("createdBy")
    private Integer createdBy;

    @JsonProperty("createdDate")
    @ColumnAlias("createdDate")
    private java.util.Date createdDate;

    @JsonProperty("estEndDate")
    @ColumnAlias("estEndDate")
    private java.util.Date estEndDate;

    @JsonProperty("estStartDate")
    @ColumnAlias("estStartDate")
    private java.util.Date estStartDate;

    @JsonProperty("modifiedBy")
    @ColumnAlias("modifiedBy")
    private Integer modifiedBy;

    @JsonProperty("modifiedDate")
    @ColumnAlias("modifiedDate")
    private java.util.Date modifiedDate;

    @JsonProperty("municipalities")
    @ColumnAlias("municipalities")
    private Municipalities municipalities;

    @JsonProperty("municipalityId")
    @ColumnAlias("municipalityId")
    private Integer municipalityId;

    @JsonProperty("projectDescription")
    @ColumnAlias("projectDescription")
    private String projectDescription;

    @JsonProperty("projectFormses")
    @ColumnAlias("projectFormses")
    private List projectFormses;

    @JsonProperty("projectGisrecordses")
    @ColumnAlias("projectGisrecordses")
    private List projectGisrecordses;

    @JsonProperty("projectGoals")
    @ColumnAlias("projectGoals")
    private String projectGoals;

    @JsonProperty("projectGuid")
    @ColumnAlias("projectGuid")
    private String projectGuid;

    @JsonProperty("projectName")
    @ColumnAlias("projectName")
    private String projectName;

    @JsonProperty("projectSharedWiths")
    @ColumnAlias("projectSharedWiths")
    private List projectSharedWiths;

    @JsonProperty("projectTaskses")
    @ColumnAlias("projectTaskses")
    private List projectTaskses;

    @JsonProperty("usersByCreatedBy")
    @ColumnAlias("usersByCreatedBy")
    private Users usersByCreatedBy;

    @JsonProperty("usersByModifiedBy")
    @ColumnAlias("usersByModifiedBy")
    private Users usersByModifiedBy;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public java.util.Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.util.Date getEstEndDate() {
        return this.estEndDate;
    }

    public void setEstEndDate(java.util.Date estEndDate) {
        this.estEndDate = estEndDate;
    }

    public java.util.Date getEstStartDate() {
        return this.estStartDate;
    }

    public void setEstStartDate(java.util.Date estStartDate) {
        this.estStartDate = estStartDate;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public java.util.Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(java.util.Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Municipalities getMunicipalities() {
        return this.municipalities;
    }

    public void setMunicipalities(Municipalities municipalities) {
        this.municipalities = municipalities;
    }

    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List getProjectFormses() {
        return this.projectFormses;
    }

    public void setProjectFormses(List projectFormses) {
        this.projectFormses = projectFormses;
    }

    public List getProjectGisrecordses() {
        return this.projectGisrecordses;
    }

    public void setProjectGisrecordses(List projectGisrecordses) {
        this.projectGisrecordses = projectGisrecordses;
    }

    public String getProjectGoals() {
        return this.projectGoals;
    }

    public void setProjectGoals(String projectGoals) {
        this.projectGoals = projectGoals;
    }

    public String getProjectGuid() {
        return this.projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List getProjectSharedWiths() {
        return this.projectSharedWiths;
    }

    public void setProjectSharedWiths(List projectSharedWiths) {
        this.projectSharedWiths = projectSharedWiths;
    }

    public List getProjectTaskses() {
        return this.projectTaskses;
    }

    public void setProjectTaskses(List projectTaskses) {
        this.projectTaskses = projectTaskses;
    }

    public Users getUsersByCreatedBy() {
        return this.usersByCreatedBy;
    }

    public void setUsersByCreatedBy(Users usersByCreatedBy) {
        this.usersByCreatedBy = usersByCreatedBy;
    }

    public Users getUsersByModifiedBy() {
        return this.usersByModifiedBy;
    }

    public void setUsersByModifiedBy(Users usersByModifiedBy) {
        this.usersByModifiedBy = usersByModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MunicipalityProjectsResponse)) return false;
        final MunicipalityProjectsResponse municipalityProjectsResponse = (MunicipalityProjectsResponse) o;
        return Objects.equals(getActive(), municipalityProjectsResponse.getActive()) &&
                Objects.equals(getCreatedBy(), municipalityProjectsResponse.getCreatedBy()) &&
                Objects.equals(getCreatedDate(), municipalityProjectsResponse.getCreatedDate()) &&
                Objects.equals(getEstEndDate(), municipalityProjectsResponse.getEstEndDate()) &&
                Objects.equals(getEstStartDate(), municipalityProjectsResponse.getEstStartDate()) &&
                Objects.equals(getModifiedBy(), municipalityProjectsResponse.getModifiedBy()) &&
                Objects.equals(getModifiedDate(), municipalityProjectsResponse.getModifiedDate()) &&
                Objects.equals(getMunicipalities(), municipalityProjectsResponse.getMunicipalities()) &&
                Objects.equals(getMunicipalityId(), municipalityProjectsResponse.getMunicipalityId()) &&
                Objects.equals(getProjectDescription(), municipalityProjectsResponse.getProjectDescription()) &&
                Objects.equals(getProjectFormses(), municipalityProjectsResponse.getProjectFormses()) &&
                Objects.equals(getProjectGisrecordses(), municipalityProjectsResponse.getProjectGisrecordses()) &&
                Objects.equals(getProjectGoals(), municipalityProjectsResponse.getProjectGoals()) &&
                Objects.equals(getProjectGuid(), municipalityProjectsResponse.getProjectGuid()) &&
                Objects.equals(getProjectName(), municipalityProjectsResponse.getProjectName()) &&
                Objects.equals(getProjectSharedWiths(), municipalityProjectsResponse.getProjectSharedWiths()) &&
                Objects.equals(getProjectTaskses(), municipalityProjectsResponse.getProjectTaskses()) &&
                Objects.equals(getUsersByCreatedBy(), municipalityProjectsResponse.getUsersByCreatedBy()) &&
                Objects.equals(getUsersByModifiedBy(), municipalityProjectsResponse.getUsersByModifiedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActive(),
                getCreatedBy(),
                getCreatedDate(),
                getEstEndDate(),
                getEstStartDate(),
                getModifiedBy(),
                getModifiedDate(),
                getMunicipalities(),
                getMunicipalityId(),
                getProjectDescription(),
                getProjectFormses(),
                getProjectGisrecordses(),
                getProjectGoals(),
                getProjectGuid(),
                getProjectName(),
                getProjectSharedWiths(),
                getProjectTaskses(),
                getUsersByCreatedBy(),
                getUsersByModifiedBy());
    }
}
