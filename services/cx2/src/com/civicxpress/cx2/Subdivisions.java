/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Subdivisions generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Subdivisions`")
public class Subdivisions implements Serializable {

    private Integer id;
    private Integer municipalityId;
    private String subdivisionGuid;
    private String subdivision;
    private String subShortCode;
    private Municipalities municipalities;
    private List<Gisrecords> gisrecordses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`SubdivisionGuid`", nullable = true, length = 255)
    public String getSubdivisionGuid() {
        return this.subdivisionGuid;
    }

    public void setSubdivisionGuid(String subdivisionGuid) {
        this.subdivisionGuid = subdivisionGuid;
    }

    @Column(name = "`Subdivision`", nullable = true, length = 255)
    public String getSubdivision() {
        return this.subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    @Column(name = "`SubShortCode`", nullable = true, length = 255)
    public String getSubShortCode() {
        return this.subShortCode;
    }

    public void setSubShortCode(String subShortCode) {
        this.subShortCode = subShortCode;
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

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "subdivisions")
    public List<Gisrecords> getGisrecordses() {
        return this.gisrecordses;
    }

    public void setGisrecordses(List<Gisrecords> gisrecordses) {
        this.gisrecordses = gisrecordses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subdivisions)) return false;
        final Subdivisions subdivisions = (Subdivisions) o;
        return Objects.equals(getId(), subdivisions.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

