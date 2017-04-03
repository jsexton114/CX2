/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class VerifyPasswordResetTokenResponse implements Serializable {

    @JsonProperty("ExpirationDate")
    @ColumnAlias("ExpirationDate")
    private java.sql.Date expirationDate;
    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;
    @JsonProperty("Token")
    @ColumnAlias("Token")
    private String token;
    @JsonProperty("UserId")
    @ColumnAlias("UserId")
    private BigDecimal userId;

    public java.sql.Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(java.sql.Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getUserId() {
        return this.userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerifyPasswordResetTokenResponse)) return false;
        final VerifyPasswordResetTokenResponse verifyPasswordResetTokenResponse = (VerifyPasswordResetTokenResponse) o;
        return Objects.equals(getExpirationDate(), verifyPasswordResetTokenResponse.getExpirationDate()) &&
                Objects.equals(getId(), verifyPasswordResetTokenResponse.getId()) &&
                Objects.equals(getToken(), verifyPasswordResetTokenResponse.getToken()) &&
                Objects.equals(getUserId(), verifyPasswordResetTokenResponse.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpirationDate(),
                getId(),
                getToken(),
                getUserId());
    }
}
