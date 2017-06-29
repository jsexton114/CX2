/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.procedure;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class FetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent implements Serializable {


    @ColumnAlias("feeId")
    private Integer feeId;

    @ColumnAlias("feeType")
    private String feeType;

    @ColumnAlias("formGuid")
    private String formGuid;

    @ColumnAlias("formTitle")
    private String formTitle;

    @ColumnAlias("formType")
    private String formType;

    @ColumnAlias("MunicipalityName")
    private String municipalityName;

    public Integer getFeeId() {
        return this.feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public String getFeeType() {
        return this.feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

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

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent)) return false;
        final FetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent = (FetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent) o;
        return Objects.equals(getFeeId(), fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent.getFeeId()) &&
                Objects.equals(getFeeType(), fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent.getFeeType()) &&
                Objects.equals(getFormGuid(), fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent.getFormGuid()) &&
                Objects.equals(getFormTitle(), fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent.getFormTitle()) &&
                Objects.equals(getFormType(), fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent.getFormType()) &&
                Objects.equals(getMunicipalityName(), fetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponseContent.getMunicipalityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeeId(),
                getFeeType(),
                getFormGuid(),
                getFormTitle(),
                getFormType(),
                getMunicipalityName());
    }
}
