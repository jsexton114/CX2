/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetListofUsersResponse implements Serializable {


    @JsonProperty("Email")
    @ColumnAlias("Email")
    private String email;

    @JsonProperty("FullName")
    @ColumnAlias("FullName")
    private String fullName;

    @JsonProperty("UserId")
    @ColumnAlias("UserId")
    private BigDecimal userId;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        if (!(o instanceof GetListofUsersResponse)) return false;
        final GetListofUsersResponse getListofUsersResponse = (GetListofUsersResponse) o;
        return Objects.equals(getEmail(), getListofUsersResponse.getEmail()) &&
                Objects.equals(getFullName(), getListofUsersResponse.getFullName()) &&
                Objects.equals(getUserId(), getListofUsersResponse.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(),
                getFullName(),
                getUserId());
    }
}
