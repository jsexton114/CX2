/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Gisrecords generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`GISRecords`")
public class Gisrecords implements Serializable {

    private Integer id;
    private String landGuid;
    private Integer municipalityId;
    private Integer subdivisionId;
    private String parcel;
    private String lot;
    private String section;
    private String streetNumber;
    private String streetName;
    private String city;
    private Integer stateId;
    private String streetType;
    private String streetDirection;
    private String sections;
    private String inspectionZone;
    private String latitude;
    private String longitude;
    private String ownerName;
    private String ownerAddress1;
    private String ownerAddress2;
    private String ownerCity;
    private String ownerState;
    private String ownerPostalCode;
    private String ownerPhone;
    private String ownerEmail;
    private String country;
    private Municipalities municipalities;
    private States states;
    private Subdivisions subdivisions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`LandGuid`", nullable = true, length = 255)
    public String getLandGuid() {
        return this.landGuid;
    }

    public void setLandGuid(String landGuid) {
        this.landGuid = landGuid;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`SubdivisionId`", nullable = true, scale = 0, precision = 10)
    public Integer getSubdivisionId() {
        return this.subdivisionId;
    }

    public void setSubdivisionId(Integer subdivisionId) {
        this.subdivisionId = subdivisionId;
    }

    @Column(name = "`Parcel`", nullable = true, length = 255)
    public String getParcel() {
        return this.parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    @Column(name = "`Lot`", nullable = true, length = 255)
    public String getLot() {
        return this.lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @Column(name = "`Section`", nullable = true, length = 255)
    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Column(name = "`StreetNumber`", nullable = true, length = 255)
    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Column(name = "`StreetName`", nullable = true, length = 255)
    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Column(name = "`City`", nullable = true, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "`StateId`", nullable = true, scale = 0, precision = 10)
    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Column(name = "`StreetType`", nullable = true, length = 255)
    public String getStreetType() {
        return this.streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    @Column(name = "`StreetDirection`", nullable = true, length = 255)
    public String getStreetDirection() {
        return this.streetDirection;
    }

    public void setStreetDirection(String streetDirection) {
        this.streetDirection = streetDirection;
    }

    @Column(name = "`Sections`", nullable = true, length = 255)
    public String getSections() {
        return this.sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    @Column(name = "`InspectionZone`", nullable = true, length = 255)
    public String getInspectionZone() {
        return this.inspectionZone;
    }

    public void setInspectionZone(String inspectionZone) {
        this.inspectionZone = inspectionZone;
    }

    @Column(name = "`Latitude`", nullable = true, length = 255)
    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "`Longitude`", nullable = true, length = 255)
    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name = "`OwnerName`", nullable = true, length = 255)
    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Column(name = "`OwnerAddress1`", nullable = true, length = 255)
    public String getOwnerAddress1() {
        return this.ownerAddress1;
    }

    public void setOwnerAddress1(String ownerAddress1) {
        this.ownerAddress1 = ownerAddress1;
    }

    @Column(name = "`OwnerAddress2`", nullable = true, length = 255)
    public String getOwnerAddress2() {
        return this.ownerAddress2;
    }

    public void setOwnerAddress2(String ownerAddress2) {
        this.ownerAddress2 = ownerAddress2;
    }

    @Column(name = "`OwnerCity`", nullable = true, length = 255)
    public String getOwnerCity() {
        return this.ownerCity;
    }

    public void setOwnerCity(String ownerCity) {
        this.ownerCity = ownerCity;
    }

    @Column(name = "`OwnerState`", nullable = true, length = 255)
    public String getOwnerState() {
        return this.ownerState;
    }

    public void setOwnerState(String ownerState) {
        this.ownerState = ownerState;
    }

    @Column(name = "`OwnerPostalCode`", nullable = true, length = 255)
    public String getOwnerPostalCode() {
        return this.ownerPostalCode;
    }

    public void setOwnerPostalCode(String ownerPostalCode) {
        this.ownerPostalCode = ownerPostalCode;
    }

    @Column(name = "`OwnerPhone`", nullable = true, length = 255)
    public String getOwnerPhone() {
        return this.ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    @Column(name = "`OwnerEmail`", nullable = true, length = 255)
    public String getOwnerEmail() {
        return this.ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @Column(name = "`Country`", nullable = true, length = 255)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`MunicipalityId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Municipalities getMunicipalities() {
        return this.municipalities;
    }

    public void setMunicipalities(Municipalities municipalities) {
        if(municipalities != null) {
            this.municipalityId = municipalities.getId();
        }

        this.municipalities = municipalities;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`StateId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public States getStates() {
        return this.states;
    }

    public void setStates(States states) {
        if(states != null) {
            this.stateId = states.getId();
        }

        this.states = states;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`SubdivisionId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Subdivisions getSubdivisions() {
        return this.subdivisions;
    }

    public void setSubdivisions(Subdivisions subdivisions) {
        if(subdivisions != null) {
            this.subdivisionId = subdivisions.getId();
        }

        this.subdivisions = subdivisions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gisrecords)) return false;
        final Gisrecords gisrecords = (Gisrecords) o;
        return Objects.equals(getId(), gisrecords.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

