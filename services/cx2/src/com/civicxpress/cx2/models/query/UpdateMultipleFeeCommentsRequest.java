/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateMultipleFeeCommentsRequest implements Serializable {

    @NotNull
    @JsonProperty("comments")
    private String comments;
    @NotNull
    @JsonProperty("feeList")
    private List<Integer> feeList;

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        if (!(o instanceof UpdateMultipleFeeCommentsRequest)) return false;
        final UpdateMultipleFeeCommentsRequest updateMultipleFeeCommentsRequest = (UpdateMultipleFeeCommentsRequest) o;
        return Objects.equals(getComments(), updateMultipleFeeCommentsRequest.getComments()) &&
                Objects.equals(getFeeList(), updateMultipleFeeCommentsRequest.getFeeList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComments(),
                getFeeList());
    }
}