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

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.commons.data.type.WMPersistentLocalDateTime;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class FormsByCategoryForDashBoardResponse implements Serializable {

    @ColumnAlias("counts")
    private Integer counts;
    @ColumnAlias("Category")
    private String category;

    public Integer getCounts() {
        return this.counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormsByCategoryForDashBoardResponse)) return false;
        final FormsByCategoryForDashBoardResponse formsByCategoryForDashBoardResponse = (FormsByCategoryForDashBoardResponse) o;
        return Objects.equals(getCounts(), formsByCategoryForDashBoardResponse.getCounts()) &&
                Objects.equals(getCategory(), formsByCategoryForDashBoardResponse.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCounts(),
                getCategory());
    }
}
