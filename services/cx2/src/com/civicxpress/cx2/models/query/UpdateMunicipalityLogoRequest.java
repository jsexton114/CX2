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

public class UpdateMunicipalityLogoRequest implements Serializable {

    @NotNull
    @JsonProperty("logoBytes")
    private List<Byte> logoBytes;
    @NotNull
    @JsonProperty("municipalityId")
    private String municipalityId;

    public List<Byte> getLogoBytes() {
        return this.logoBytes;
    }

    public void setLogoBytes(List<Byte> logoBytes) {
        this.logoBytes = logoBytes;
    }

    public String getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateMunicipalityLogoRequest)) return false;
        final UpdateMunicipalityLogoRequest updateMunicipalityLogoRequest = (UpdateMunicipalityLogoRequest) o;
        return Objects.equals(getLogoBytes(), updateMunicipalityLogoRequest.getLogoBytes()) &&
                Objects.equals(getMunicipalityId(), updateMunicipalityLogoRequest.getMunicipalityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogoBytes(),
                getMunicipalityId());
    }
}
