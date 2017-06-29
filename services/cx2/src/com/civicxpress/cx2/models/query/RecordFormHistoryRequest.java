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

public class RecordFormHistoryRequest implements Serializable {


    @JsonProperty("FormGUID")
    private String formGuid;

    @JsonProperty("FormTypeId")
    private Integer formTypeId;

    @JsonProperty("NewStatusId")
    private Integer newStatusId;

    @JsonProperty("OldStatusId")
    private Integer oldStatusId;

    @JsonProperty("Comments")
    private String comments;

    @JsonProperty("CreatedBy")
    private Integer createdBy;

    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    public Integer getNewStatusId() {
        return this.newStatusId;
    }

    public void setNewStatusId(Integer newStatusId) {
        this.newStatusId = newStatusId;
    }

    public Integer getOldStatusId() {
        return this.oldStatusId;
    }

    public void setOldStatusId(Integer oldStatusId) {
        this.oldStatusId = oldStatusId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordFormHistoryRequest)) return false;
        final RecordFormHistoryRequest recordFormHistoryRequest = (RecordFormHistoryRequest) o;
        return Objects.equals(getFormGuid(), recordFormHistoryRequest.getFormGuid()) &&
                Objects.equals(getFormTypeId(), recordFormHistoryRequest.getFormTypeId()) &&
                Objects.equals(getNewStatusId(), recordFormHistoryRequest.getNewStatusId()) &&
                Objects.equals(getOldStatusId(), recordFormHistoryRequest.getOldStatusId()) &&
                Objects.equals(getComments(), recordFormHistoryRequest.getComments()) &&
                Objects.equals(getCreatedBy(), recordFormHistoryRequest.getCreatedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormGuid(),
                getFormTypeId(),
                getNewStatusId(),
                getOldStatusId(),
                getComments(),
                getCreatedBy());
    }
}
