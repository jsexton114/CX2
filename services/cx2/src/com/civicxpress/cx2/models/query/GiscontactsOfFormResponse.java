/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GiscontactsOfFormResponse implements Serializable {

    @ColumnAlias("ID")
    private Integer id;
    @ColumnAlias("GISRecordId")
    private Integer gisrecordId;
    @ColumnAlias("ContactType")
    private String contactType;
    @ColumnAlias("FirstName")
    private String firstName;
    @ColumnAlias("LastName")
    private String lastName;
    @ColumnAlias("Email")
    private String email;
    @ColumnAlias("Phone")
    private String phone;
    @ColumnAlias("Address1")
    private String address1;
    @ColumnAlias("Address2")
    private String address2;
    @ColumnAlias("City")
    private String city;
    @ColumnAlias("StateId")
    private Integer stateId;
    @ColumnAlias("PostalCode")
    private String postalCode;
    @ColumnAlias("Country")
    private String country;
    @ColumnAlias("Notes")
    private String notes;
    @ColumnAlias("Active")
    private Boolean active;
    @ColumnAlias("Lot")
    private String lot;
    @ColumnAlias("FullAddress")
    private String fullAddress;
    @ColumnAlias("RelatedFormGUID")
    private String relatedFormGuid;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGisrecordId() {
        return this.gisrecordId;
    }

    public void setGisrecordId(Integer gisrecordId) {
        this.gisrecordId = gisrecordId;
    }

    public String getContactType() {
        return this.contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLot() {
        return this.lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiscontactsOfFormResponse)) return false;
        final GiscontactsOfFormResponse giscontactsOfFormResponse = (GiscontactsOfFormResponse) o;
        return Objects.equals(getId(), giscontactsOfFormResponse.getId()) &&
                Objects.equals(getGisrecordId(), giscontactsOfFormResponse.getGisrecordId()) &&
                Objects.equals(getContactType(), giscontactsOfFormResponse.getContactType()) &&
                Objects.equals(getFirstName(), giscontactsOfFormResponse.getFirstName()) &&
                Objects.equals(getLastName(), giscontactsOfFormResponse.getLastName()) &&
                Objects.equals(getEmail(), giscontactsOfFormResponse.getEmail()) &&
                Objects.equals(getPhone(), giscontactsOfFormResponse.getPhone()) &&
                Objects.equals(getAddress1(), giscontactsOfFormResponse.getAddress1()) &&
                Objects.equals(getAddress2(), giscontactsOfFormResponse.getAddress2()) &&
                Objects.equals(getCity(), giscontactsOfFormResponse.getCity()) &&
                Objects.equals(getStateId(), giscontactsOfFormResponse.getStateId()) &&
                Objects.equals(getPostalCode(), giscontactsOfFormResponse.getPostalCode()) &&
                Objects.equals(getCountry(), giscontactsOfFormResponse.getCountry()) &&
                Objects.equals(getNotes(), giscontactsOfFormResponse.getNotes()) &&
                Objects.equals(getActive(), giscontactsOfFormResponse.getActive()) &&
                Objects.equals(getLot(), giscontactsOfFormResponse.getLot()) &&
                Objects.equals(getFullAddress(), giscontactsOfFormResponse.getFullAddress()) &&
                Objects.equals(getRelatedFormGuid(), giscontactsOfFormResponse.getRelatedFormGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getGisrecordId(),
                getContactType(),
                getFirstName(),
                getLastName(),
                getEmail(),
                getPhone(),
                getAddress1(),
                getAddress2(),
                getCity(),
                getStateId(),
                getPostalCode(),
                getCountry(),
                getNotes(),
                getActive(),
                getLot(),
                getFullAddress(),
                getRelatedFormGuid());
    }
}
