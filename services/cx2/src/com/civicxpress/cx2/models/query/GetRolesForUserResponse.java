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

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetRolesForUserResponse implements Serializable {


    @JsonProperty("MunicipalityId")
    @ColumnAlias("MunicipalityId")
    private BigDecimal municipalityId;

    @JsonProperty("RoleName")
    @ColumnAlias("RoleName")
    private String roleName;

    public BigDecimal getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(BigDecimal municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetRolesForUserResponse)) return false;
        final GetRolesForUserResponse getRolesForUserResponse = (GetRolesForUserResponse) o;
        return Objects.equals(getMunicipalityId(), getRolesForUserResponse.getMunicipalityId()) &&
                Objects.equals(getRoleName(), getRolesForUserResponse.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMunicipalityId(),
                getRoleName());
    }
}
