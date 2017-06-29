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

public class FormsWithCodeEnforcementByMunicipalityResponse implements Serializable {


    @ColumnAlias("formGuid")
    private String formGuid;

    @ColumnAlias("formTitle")
    private String formTitle;

    @ColumnAlias("fullName")
    private String fullName;

    @ColumnAlias("balanceDue")
    private String balanceDue;

    @ColumnAlias("status")
    private String status;

    @ColumnAlias("formType")
    private String formType;

    @ColumnAlias("codeEnforcement")
    private Boolean codeEnforcement;

    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBalanceDue() {
        return this.balanceDue;
    }

    public void setBalanceDue(String balanceDue) {
        this.balanceDue = balanceDue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public Boolean getCodeEnforcement() {
        return this.codeEnforcement;
    }

    public void setCodeEnforcement(Boolean codeEnforcement) {
        this.codeEnforcement = codeEnforcement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormsWithCodeEnforcementByMunicipalityResponse)) return false;
        final FormsWithCodeEnforcementByMunicipalityResponse formsWithCodeEnforcementByMunicipalityResponse = (FormsWithCodeEnforcementByMunicipalityResponse) o;
        return Objects.equals(getFormGuid(), formsWithCodeEnforcementByMunicipalityResponse.getFormGuid()) &&
                Objects.equals(getFormTitle(), formsWithCodeEnforcementByMunicipalityResponse.getFormTitle()) &&
                Objects.equals(getFullName(), formsWithCodeEnforcementByMunicipalityResponse.getFullName()) &&
                Objects.equals(getBalanceDue(), formsWithCodeEnforcementByMunicipalityResponse.getBalanceDue()) &&
                Objects.equals(getStatus(), formsWithCodeEnforcementByMunicipalityResponse.getStatus()) &&
                Objects.equals(getFormType(), formsWithCodeEnforcementByMunicipalityResponse.getFormType()) &&
                Objects.equals(getCodeEnforcement(), formsWithCodeEnforcementByMunicipalityResponse.getCodeEnforcement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormGuid(),
                getFormTitle(),
                getFullName(),
                getBalanceDue(),
                getStatus(),
                getFormType(),
                getCodeEnforcement());
    }
}
