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

public class UpdateConsiderClosedForInspectionOutcomeRequest implements Serializable {


    @JsonProperty("ConsiderClosed")
    @NotNull
    private Boolean considerClosed;

    @JsonProperty("id")
    @NotNull
    private Integer id;

    public Boolean getConsiderClosed() {
        return this.considerClosed;
    }

    public void setConsiderClosed(Boolean considerClosed) {
        this.considerClosed = considerClosed;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateConsiderClosedForInspectionOutcomeRequest)) return false;
        final UpdateConsiderClosedForInspectionOutcomeRequest updateConsiderClosedForInspectionOutcomeRequest = (UpdateConsiderClosedForInspectionOutcomeRequest) o;
        return Objects.equals(getConsiderClosed(), updateConsiderClosedForInspectionOutcomeRequest.getConsiderClosed()) &&
                Objects.equals(getId(), updateConsiderClosedForInspectionOutcomeRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsiderClosed(),
                getId());
    }
}
