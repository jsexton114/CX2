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

public class GetOwnersForGisRecordsResponse implements Serializable {

    @JsonProperty("active")
    @ColumnAlias("active")
    private Boolean active;
    @JsonProperty("address1")
    @ColumnAlias("address1")
    private String address1;
    @JsonProperty("address2")
    @ColumnAlias("address2")
    private String address2;
    @JsonProperty("city")
    @ColumnAlias("city")
    private String city;
    @JsonProperty("contactType")
    @ColumnAlias("contactType")
    private String contactType;
    @JsonProperty("country")
    @ColumnAlias("country")
    private String country;
    @JsonProperty("email")
    @ColumnAlias("email")
    private String email;
    @JsonProperty("firstName")
    @ColumnAlias("firstName")
    private String firstName;
    @JsonProperty("gisrecordId")
    @ColumnAlias("gisrecordId")
    private Integer gisrecordId;
    @JsonProperty("gisrecords")
    @ColumnAlias("gisrecords")
    private Gisrecords gisrecords;
    @JsonProperty("id")
    @ColumnAlias("id")
    private Integer id;
    @JsonProperty("lastName")
    @ColumnAlias("lastName")
    private String lastName;
    @JsonProperty("masterFormses")
    @ColumnAlias("masterFormses")
    private List masterFormses;
    @JsonProperty("notes")
    @ColumnAlias("notes")
    private String notes;
    @JsonProperty("phone")
    @ColumnAlias("phone")
    private String phone;
    @JsonProperty("postalCode")
    @ColumnAlias("postalCode")
    private String postalCode;
    @JsonProperty("stateId")
    @ColumnAlias("stateId")
    private Integer stateId;
    @JsonProperty("states")
    @ColumnAlias("states")
    private States states;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactType() {
        return this.contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGisrecordId() {
        return this.gisrecordId;
    }

    public void setGisrecordId(Integer gisrecordId) {
        this.gisrecordId = gisrecordId;
    }

    public Gisrecords getGisrecords() {
        return this.gisrecords;
    }

    public void setGisrecords(Gisrecords gisrecords) {
        this.gisrecords = gisrecords;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List masterFormses) {
        this.masterFormses = masterFormses;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public States getStates() {
        return this.states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetOwnersForGisRecordsResponse)) return false;
        final GetOwnersForGisRecordsResponse getOwnersForGisRecordsResponse = (GetOwnersForGisRecordsResponse) o;
        return Objects.equals(getActive(), getOwnersForGisRecordsResponse.getActive()) &&
                Objects.equals(getAddress1(), getOwnersForGisRecordsResponse.getAddress1()) &&
                Objects.equals(getAddress2(), getOwnersForGisRecordsResponse.getAddress2()) &&
                Objects.equals(getCity(), getOwnersForGisRecordsResponse.getCity()) &&
                Objects.equals(getContactType(), getOwnersForGisRecordsResponse.getContactType()) &&
                Objects.equals(getCountry(), getOwnersForGisRecordsResponse.getCountry()) &&
                Objects.equals(getEmail(), getOwnersForGisRecordsResponse.getEmail()) &&
                Objects.equals(getFirstName(), getOwnersForGisRecordsResponse.getFirstName()) &&
                Objects.equals(getGisrecordId(), getOwnersForGisRecordsResponse.getGisrecordId()) &&
                Objects.equals(getGisrecords(), getOwnersForGisRecordsResponse.getGisrecords()) &&
                Objects.equals(getId(), getOwnersForGisRecordsResponse.getId()) &&
                Objects.equals(getLastName(), getOwnersForGisRecordsResponse.getLastName()) &&
                Objects.equals(getMasterFormses(), getOwnersForGisRecordsResponse.getMasterFormses()) &&
                Objects.equals(getNotes(), getOwnersForGisRecordsResponse.getNotes()) &&
                Objects.equals(getPhone(), getOwnersForGisRecordsResponse.getPhone()) &&
                Objects.equals(getPostalCode(), getOwnersForGisRecordsResponse.getPostalCode()) &&
                Objects.equals(getStateId(), getOwnersForGisRecordsResponse.getStateId()) &&
                Objects.equals(getStates(), getOwnersForGisRecordsResponse.getStates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActive(),
                getAddress1(),
                getAddress2(),
                getCity(),
                getContactType(),
                getCountry(),
                getEmail(),
                getFirstName(),
                getGisrecordId(),
                getGisrecords(),
                getId(),
                getLastName(),
                getMasterFormses(),
                getNotes(),
                getPhone(),
                getPostalCode(),
                getStateId(),
                getStates());
    }
}
