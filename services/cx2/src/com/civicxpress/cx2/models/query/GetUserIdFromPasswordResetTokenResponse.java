/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetUserIdFromPasswordResetTokenResponse implements Serializable {

    @JsonProperty("UserId")
    @ColumnAlias("UserId")
    private BigDecimal userId;

    public BigDecimal getUserId() {
        return this.userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetUserIdFromPasswordResetTokenResponse)) return false;
        final GetUserIdFromPasswordResetTokenResponse getUserIdFromPasswordResetTokenResponse = (GetUserIdFromPasswordResetTokenResponse) o;
        return Objects.equals(getUserId(), getUserIdFromPasswordResetTokenResponse.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
