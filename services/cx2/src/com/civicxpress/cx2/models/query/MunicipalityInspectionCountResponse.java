/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class MunicipalityInspectionCountResponse implements Serializable {


    @ColumnAlias("inspectionsCount")
    private Long inspectionsCount;

    public Long getInspectionsCount() {
        return this.inspectionsCount;
    }

    public void setInspectionsCount(Long inspectionsCount) {
        this.inspectionsCount = inspectionsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MunicipalityInspectionCountResponse)) return false;
        final MunicipalityInspectionCountResponse municipalityInspectionCountResponse = (MunicipalityInspectionCountResponse) o;
        return Objects.equals(getInspectionsCount(), municipalityInspectionCountResponse.getInspectionsCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInspectionsCount());
    }
}
