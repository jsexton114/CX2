/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class CountOfVendorsResponse implements Serializable {


    @JsonProperty("CountOfVendors")
    @ColumnAlias("CountOfVendors")
    private Integer countOfVendors;

    public Integer getCountOfVendors() {
        return this.countOfVendors;
    }

    public void setCountOfVendors(Integer countOfVendors) {
        this.countOfVendors = countOfVendors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountOfVendorsResponse)) return false;
        final CountOfVendorsResponse countOfVendorsResponse = (CountOfVendorsResponse) o;
        return Objects.equals(getCountOfVendors(), countOfVendorsResponse.getCountOfVendors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountOfVendors());
    }
}
