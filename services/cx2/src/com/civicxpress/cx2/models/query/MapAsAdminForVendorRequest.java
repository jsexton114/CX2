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

public class MapAsAdminForVendorRequest implements Serializable {


    @JsonProperty("UserId")
    private Integer userId;

    @JsonProperty("VendorId")
    private Integer vendorId;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        if (!(o instanceof MapAsAdminForVendorRequest)) return false;
        final MapAsAdminForVendorRequest mapAsAdminForVendorRequest = (MapAsAdminForVendorRequest) o;
        return Objects.equals(getUserId(), mapAsAdminForVendorRequest.getUserId()) &&
                Objects.equals(getVendorId(), mapAsAdminForVendorRequest.getVendorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(),
                getVendorId());
    }
}
