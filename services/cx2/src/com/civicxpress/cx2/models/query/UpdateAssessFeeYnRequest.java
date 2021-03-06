/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAssessFeeYnRequest implements Serializable {


    @JsonProperty("AssessFeeYN")
    private Boolean assessFeeYn;

    @JsonProperty("id")
    @NotNull
    private Integer id;

    public Boolean getAssessFeeYn() {
        return this.assessFeeYn;
    }

    public void setAssessFeeYn(Boolean assessFeeYn) {
        this.assessFeeYn = assessFeeYn;
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
        if (!(o instanceof UpdateAssessFeeYnRequest)) return false;
        final UpdateAssessFeeYnRequest updateAssessFeeYnRequest = (UpdateAssessFeeYnRequest) o;
        return Objects.equals(getAssessFeeYn(), updateAssessFeeYnRequest.getAssessFeeYn()) &&
                Objects.equals(getId(), updateAssessFeeYnRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssessFeeYn(),
                getId());
    }
}
