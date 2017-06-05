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

public class UpdateTrackViolationsRequest implements Serializable {

    @NotNull
    @JsonProperty("TrackViolations")
    private Boolean trackViolations;
    @NotNull
    @JsonProperty("formType")
    private Integer formType;

    public Boolean getTrackViolations() {
        return this.trackViolations;
    }

    public void setTrackViolations(Boolean trackViolations) {
        this.trackViolations = trackViolations;
    }

    public Integer getFormType() {
        return this.formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateTrackViolationsRequest)) return false;
        final UpdateTrackViolationsRequest updateTrackViolationsRequest = (UpdateTrackViolationsRequest) o;
        return Objects.equals(getTrackViolations(), updateTrackViolationsRequest.getTrackViolations()) &&
                Objects.equals(getFormType(), updateTrackViolationsRequest.getFormType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrackViolations(),
                getFormType());
    }
}
