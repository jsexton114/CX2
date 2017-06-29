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

public class UpdateProcessOwnersForGuidRequest implements Serializable {


    @JsonProperty("AssignedToGroupId")
    private Integer assignedToGroupId;

    @JsonProperty("GUID")
    private String guid;

    public Integer getAssignedToGroupId() {
        return this.assignedToGroupId;
    }

    public void setAssignedToGroupId(Integer assignedToGroupId) {
        this.assignedToGroupId = assignedToGroupId;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateProcessOwnersForGuidRequest)) return false;
        final UpdateProcessOwnersForGuidRequest updateProcessOwnersForGuidRequest = (UpdateProcessOwnersForGuidRequest) o;
        return Objects.equals(getAssignedToGroupId(), updateProcessOwnersForGuidRequest.getAssignedToGroupId()) &&
                Objects.equals(getGuid(), updateProcessOwnersForGuidRequest.getGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssignedToGroupId(),
                getGuid());
    }
}
