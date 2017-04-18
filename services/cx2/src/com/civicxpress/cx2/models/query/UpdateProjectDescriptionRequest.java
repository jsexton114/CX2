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

public class UpdateProjectDescriptionRequest implements Serializable {

    @JsonProperty("ProjectDescription")
    private String projectDescription;
    @JsonProperty("project")
    private String project;

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateProjectDescriptionRequest)) return false;
        final UpdateProjectDescriptionRequest updateProjectDescriptionRequest = (UpdateProjectDescriptionRequest) o;
        return Objects.equals(getProjectDescription(), updateProjectDescriptionRequest.getProjectDescription()) &&
                Objects.equals(getProject(), updateProjectDescriptionRequest.getProject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectDescription(),
                getProject());
    }
}
