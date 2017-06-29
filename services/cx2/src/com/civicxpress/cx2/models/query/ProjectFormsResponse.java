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
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class ProjectFormsResponse implements Serializable {


    @JsonProperty("addedAt")
    @ColumnAlias("addedAt")
    private java.util.Date addedAt;

    @JsonProperty("addedByUser")
    @ColumnAlias("addedByUser")
    private Integer addedByUser;

    @JsonProperty("comments")
    @ColumnAlias("comments")
    private String comments;

    @JsonProperty("id")
    @ColumnAlias("id")
    private Integer id;

    @JsonProperty("masterForms")
    @ColumnAlias("masterForms")
    private MasterForms masterForms;

    @JsonProperty("projects")
    @ColumnAlias("projects")
    private Projects projects;

    @JsonProperty("relatedFormGuid")
    @ColumnAlias("relatedFormGuid")
    private String relatedFormGuid;

    @JsonProperty("relatedProjectGuid")
    @ColumnAlias("relatedProjectGuid")
    private String relatedProjectGuid;

    @JsonProperty("users")
    @ColumnAlias("users")
    private Users users;

    public java.util.Date getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(java.util.Date addedAt) {
        this.addedAt = addedAt;
    }

    public Integer getAddedByUser() {
        return this.addedByUser;
    }

    public void setAddedByUser(Integer addedByUser) {
        this.addedByUser = addedByUser;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MasterForms getMasterForms() {
        return this.masterForms;
    }

    public void setMasterForms(MasterForms masterForms) {
        this.masterForms = masterForms;
    }

    public Projects getProjects() {
        return this.projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectFormsResponse)) return false;
        final ProjectFormsResponse projectFormsResponse = (ProjectFormsResponse) o;
        return Objects.equals(getAddedAt(), projectFormsResponse.getAddedAt()) &&
                Objects.equals(getAddedByUser(), projectFormsResponse.getAddedByUser()) &&
                Objects.equals(getComments(), projectFormsResponse.getComments()) &&
                Objects.equals(getId(), projectFormsResponse.getId()) &&
                Objects.equals(getMasterForms(), projectFormsResponse.getMasterForms()) &&
                Objects.equals(getProjects(), projectFormsResponse.getProjects()) &&
                Objects.equals(getRelatedFormGuid(), projectFormsResponse.getRelatedFormGuid()) &&
                Objects.equals(getRelatedProjectGuid(), projectFormsResponse.getRelatedProjectGuid()) &&
                Objects.equals(getUsers(), projectFormsResponse.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddedAt(),
                getAddedByUser(),
                getComments(),
                getId(),
                getMasterForms(),
                getProjects(),
                getRelatedFormGuid(),
                getRelatedProjectGuid(),
                getUsers());
    }
}
