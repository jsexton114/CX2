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

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetUserIdResponse implements Serializable {

    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetUserIdResponse)) return false;
        final GetUserIdResponse getUserIdResponse = (GetUserIdResponse) o;
        return Objects.equals(getId(), getUserIdResponse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
