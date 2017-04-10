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

public class UpdateBalanceDueInMasterFormRequest implements Serializable {

    @NotNull
    @JsonProperty("balanceDue")
    private String balanceDue;
    @NotNull
    @JsonProperty("feeList")
    private List<Integer> feeList = new ArrayList<>();

    public String getBalanceDue() {
        return this.balanceDue;
    }

    public void setBalanceDue(String balanceDue) {
        this.balanceDue = balanceDue;
    }

    public List<Integer> getFeeList() {
        return this.feeList;
    }

    public void setFeeList(List<Integer> feeList) {
        this.feeList = feeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateBalanceDueInMasterFormRequest)) return false;
        final UpdateBalanceDueInMasterFormRequest updateBalanceDueInMasterFormRequest = (UpdateBalanceDueInMasterFormRequest) o;
        return Objects.equals(getBalanceDue(), updateBalanceDueInMasterFormRequest.getBalanceDue()) &&
                Objects.equals(getFeeList(), updateBalanceDueInMasterFormRequest.getFeeList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalanceDue(),
                getFeeList());
    }
}