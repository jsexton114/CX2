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

import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class FeeByFeeTypesToDashboardResponse implements Serializable {

    @ColumnAlias("Fee")
    private String fee;
    @ColumnAlias("counts")
    private Integer counts;

    public String getFee() {
        return this.fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getCounts() {
        return this.counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeeByFeeTypesToDashboardResponse)) return false;
        final FeeByFeeTypesToDashboardResponse feeByFeeTypesToDashboardResponse = (FeeByFeeTypesToDashboardResponse) o;
        return Objects.equals(getFee(), feeByFeeTypesToDashboardResponse.getFee()) &&
                Objects.equals(getCounts(), feeByFeeTypesToDashboardResponse.getCounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFee(),
                getCounts());
    }
}
