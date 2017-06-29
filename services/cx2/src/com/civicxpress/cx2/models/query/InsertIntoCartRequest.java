/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertIntoCartRequest implements Serializable {


    @JsonProperty("FeeId")
    @NotNull
    private Integer feeId;

    @JsonProperty("UserId")
    @NotNull
    private Integer userId;

    public Integer getFeeId() {
        return this.feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertIntoCartRequest)) return false;
        final InsertIntoCartRequest insertIntoCartRequest = (InsertIntoCartRequest) o;
        return Objects.equals(getFeeId(), insertIntoCartRequest.getFeeId()) &&
                Objects.equals(getUserId(), insertIntoCartRequest.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeeId(),
                getUserId());
    }
}
