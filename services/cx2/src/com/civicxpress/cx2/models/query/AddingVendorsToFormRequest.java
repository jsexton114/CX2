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

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddingVendorsToFormRequest implements Serializable {

    @JsonProperty("RelatedFormGUID")
    private String relatedFormGuid;
    @JsonProperty("SharedOn")
    private Timestamp sharedOn;
    @JsonProperty("VendorId")
    private Integer vendorId;

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    public Timestamp getSharedOn() {
        return this.sharedOn;
    }

    public void setSharedOn(Timestamp sharedOn) {
        this.sharedOn = sharedOn;
    }

    public Integer getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddingVendorsToFormRequest)) return false;
        final AddingVendorsToFormRequest addingVendorsToFormRequest = (AddingVendorsToFormRequest) o;
        return Objects.equals(getRelatedFormGuid(), addingVendorsToFormRequest.getRelatedFormGuid()) &&
                Objects.equals(getSharedOn(), addingVendorsToFormRequest.getSharedOn()) &&
                Objects.equals(getVendorId(), addingVendorsToFormRequest.getVendorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRelatedFormGuid(),
                getSharedOn(),
                getVendorId());
    }
}
