/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * States generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`States`")
public class States implements Serializable {

    private Integer id;
    private String stateName;
    private List<Contractors> contractorses = new ArrayList<>();
    private List<Users> userses = new ArrayList<>();
    private List<Municipalities> municipalitieses = new ArrayList<>();
    private List<Gisrecords> gisrecordses = new ArrayList<>();
    private List<Vendor> vendors = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`StateName`", nullable = true, length = 255)
    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "states")
    public List<Contractors> getContractorses() {
        return this.contractorses;
    }

    public void setContractorses(List<Contractors> contractorses) {
        this.contractorses = contractorses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "states")
    public List<Users> getUserses() {
        return this.userses;
    }

    public void setUserses(List<Users> userses) {
        this.userses = userses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "states")
    public List<Municipalities> getMunicipalitieses() {
        return this.municipalitieses;
    }

    public void setMunicipalitieses(List<Municipalities> municipalitieses) {
        this.municipalitieses = municipalitieses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "states")
    public List<Gisrecords> getGisrecordses() {
        return this.gisrecordses;
    }

    public void setGisrecordses(List<Gisrecords> gisrecordses) {
        this.gisrecordses = gisrecordses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "states")
    public List<Vendor> getVendors() {
        return this.vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof States)) return false;
        final States states = (States) o;
        return Objects.equals(getId(), states.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

