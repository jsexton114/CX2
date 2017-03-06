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
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetListofGroupNameResponse implements Serializable {

    @JsonProperty("GroupName")
    @ColumnAlias("GroupName")
    private String groupName;
    @JsonProperty("ID")
    @ColumnAlias("ID")
    private BigDecimal id;

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetListofGroupNameResponse)) return false;
        final GetListofGroupNameResponse getListofGroupNameResponse = (GetListofGroupNameResponse) o;
        return Objects.equals(getGroupName(), getListofGroupNameResponse.getGroupName()) &&
                Objects.equals(getId(), getListofGroupNameResponse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(),
                getId());
    }
}
