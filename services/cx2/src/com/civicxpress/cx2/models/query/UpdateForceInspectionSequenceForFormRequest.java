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

public class UpdateForceInspectionSequenceForFormRequest implements Serializable {


    @JsonProperty("ForceInspectionSequence")
    @NotNull
    private Boolean forceInspectionSequence;

    @JsonProperty("form")
    @NotNull
    private Integer form;

    public Boolean getForceInspectionSequence() {
        return this.forceInspectionSequence;
    }

    public void setForceInspectionSequence(Boolean forceInspectionSequence) {
        this.forceInspectionSequence = forceInspectionSequence;
    }

    public Integer getForm() {
        return this.form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateForceInspectionSequenceForFormRequest)) return false;
        final UpdateForceInspectionSequenceForFormRequest updateForceInspectionSequenceForFormRequest = (UpdateForceInspectionSequenceForFormRequest) o;
        return Objects.equals(getForceInspectionSequence(), updateForceInspectionSequenceForFormRequest.getForceInspectionSequence()) &&
                Objects.equals(getForm(), updateForceInspectionSequenceForFormRequest.getForm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getForceInspectionSequence(),
                getForm());
    }
}
