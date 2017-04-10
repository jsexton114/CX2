/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class PreferenceForUserResponse implements Serializable {

    @JsonProperty("id")
    @ColumnAlias("id")
    private Integer id;
    @JsonProperty("userViewPreferenceses")
    @ColumnAlias("userViewPreferenceses")
    private List userViewPreferenceses;
    @JsonProperty("viewDescription")
    @ColumnAlias("viewDescription")
    private String viewDescription;
    @JsonProperty("viewName")
    @ColumnAlias("viewName")
    private String viewName;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List getUserViewPreferenceses() {
        return this.userViewPreferenceses;
    }

    public void setUserViewPreferenceses(List userViewPreferenceses) {
        this.userViewPreferenceses = userViewPreferenceses;
    }

    public String getViewDescription() {
        return this.viewDescription;
    }

    public void setViewDescription(String viewDescription) {
        this.viewDescription = viewDescription;
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreferenceForUserResponse)) return false;
        final PreferenceForUserResponse preferenceForUserResponse = (PreferenceForUserResponse) o;
        return Objects.equals(getId(), preferenceForUserResponse.getId()) &&
                Objects.equals(getUserViewPreferenceses(), preferenceForUserResponse.getUserViewPreferenceses()) &&
                Objects.equals(getViewDescription(), preferenceForUserResponse.getViewDescription()) &&
                Objects.equals(getViewName(), preferenceForUserResponse.getViewName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getUserViewPreferenceses(),
                getViewDescription(),
                getViewName());
    }
}
