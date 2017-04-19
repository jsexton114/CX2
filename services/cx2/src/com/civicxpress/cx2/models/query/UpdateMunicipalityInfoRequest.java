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

public class UpdateMunicipalityInfoRequest implements Serializable {

    @JsonProperty("mn")
    private String mn;
    @JsonProperty("em")
    private String em;
    @JsonProperty("ph")
    private String ph;
    @JsonProperty("ad1")
    private String ad1;
    @JsonProperty("ad2")
    private String ad2;
    @JsonProperty("st")
    private Integer st;
    @JsonProperty("ct")
    private String ct;
    @JsonProperty("pc")
    private String pc;
    @JsonProperty("municipality")
    private Integer municipality;

    public String getMn() {
        return this.mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getEm() {
        return this.em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getPh() {
        return this.ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAd1() {
        return this.ad1;
    }

    public void setAd1(String ad1) {
        this.ad1 = ad1;
    }

    public String getAd2() {
        return this.ad2;
    }

    public void setAd2(String ad2) {
        this.ad2 = ad2;
    }

    public Integer getSt() {
        return this.st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public String getCt() {
        return this.ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getPc() {
        return this.pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public Integer getMunicipality() {
        return this.municipality;
    }

    public void setMunicipality(Integer municipality) {
        this.municipality = municipality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateMunicipalityInfoRequest)) return false;
        final UpdateMunicipalityInfoRequest updateMunicipalityInfoRequest = (UpdateMunicipalityInfoRequest) o;
        return Objects.equals(getMn(), updateMunicipalityInfoRequest.getMn()) &&
                Objects.equals(getEm(), updateMunicipalityInfoRequest.getEm()) &&
                Objects.equals(getPh(), updateMunicipalityInfoRequest.getPh()) &&
                Objects.equals(getAd1(), updateMunicipalityInfoRequest.getAd1()) &&
                Objects.equals(getAd2(), updateMunicipalityInfoRequest.getAd2()) &&
                Objects.equals(getSt(), updateMunicipalityInfoRequest.getSt()) &&
                Objects.equals(getCt(), updateMunicipalityInfoRequest.getCt()) &&
                Objects.equals(getPc(), updateMunicipalityInfoRequest.getPc()) &&
                Objects.equals(getMunicipality(), updateMunicipalityInfoRequest.getMunicipality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMn(),
                getEm(),
                getPh(),
                getAd1(),
                getAd2(),
                getSt(),
                getCt(),
                getPc(),
                getMunicipality());
    }
}
