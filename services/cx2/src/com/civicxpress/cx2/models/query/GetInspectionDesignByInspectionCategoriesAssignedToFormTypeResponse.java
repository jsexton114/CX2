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
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse implements Serializable {

    @ColumnAlias("ID")
    private Integer id;
    @ColumnAlias("InspectDesignName")
    private String inspectDesignName;
    @ColumnAlias("MaxDaysInAdvance")
    private Integer maxDaysInAdvance;
    @ColumnAlias("AllowSameDayInspections")
    private Boolean allowSameDayInspections;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInspectDesignName() {
        return this.inspectDesignName;
    }

    public void setInspectDesignName(String inspectDesignName) {
        this.inspectDesignName = inspectDesignName;
    }

    public Integer getMaxDaysInAdvance() {
        return this.maxDaysInAdvance;
    }

    public void setMaxDaysInAdvance(Integer maxDaysInAdvance) {
        this.maxDaysInAdvance = maxDaysInAdvance;
    }

    public Boolean getAllowSameDayInspections() {
        return this.allowSameDayInspections;
    }

    public void setAllowSameDayInspections(Boolean allowSameDayInspections) {
        this.allowSameDayInspections = allowSameDayInspections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse)) return false;
        final GetInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse getInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse = (GetInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse) o;
        return Objects.equals(getId(), getInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse.getId()) &&
                Objects.equals(getInspectDesignName(), getInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse.getInspectDesignName()) &&
                Objects.equals(getMaxDaysInAdvance(), getInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse.getMaxDaysInAdvance()) &&
                Objects.equals(getAllowSameDayInspections(), getInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse.getAllowSameDayInspections());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getInspectDesignName(),
                getMaxDaysInAdvance(),
                getAllowSameDayInspections());
    }
}
