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

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class CountOfVendorUsersResponse implements Serializable {

    @JsonProperty("CountOfVendorUsers")
    @ColumnAlias("CountOfVendorUsers")
    private Integer countOfVendorUsers;

    public Integer getCountOfVendorUsers() {
        return this.countOfVendorUsers;
    }

    public void setCountOfVendorUsers(Integer countOfVendorUsers) {
        this.countOfVendorUsers = countOfVendorUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountOfVendorUsersResponse)) return false;
        final CountOfVendorUsersResponse countOfVendorUsersResponse = (CountOfVendorUsersResponse) o;
        return Objects.equals(getCountOfVendorUsers(), countOfVendorUsersResponse.getCountOfVendorUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountOfVendorUsers());
    }
}
