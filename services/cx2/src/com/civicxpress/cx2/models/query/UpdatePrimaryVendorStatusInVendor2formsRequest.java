/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePrimaryVendorStatusInVendor2formsRequest implements Serializable {

    @JsonProperty("pv")
    private Boolean pv;
    @JsonProperty("form")
    private String form;

    public Boolean getPv() {
        return this.pv;
    }

    public void setPv(Boolean pv) {
        this.pv = pv;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatePrimaryVendorStatusInVendor2formsRequest)) return false;
        final UpdatePrimaryVendorStatusInVendor2formsRequest updatePrimaryVendorStatusInVendor2formsRequest = (UpdatePrimaryVendorStatusInVendor2formsRequest) o;
        return Objects.equals(getPv(), updatePrimaryVendorStatusInVendor2formsRequest.getPv()) &&
                Objects.equals(getForm(), updatePrimaryVendorStatusInVendor2formsRequest.getForm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPv(),
                getForm());
    }
}
