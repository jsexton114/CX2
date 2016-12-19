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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MunicipalityGroups generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`MunicipalityGroups`")
public class MunicipalityGroups implements Serializable {

    private Integer id;
    private String groupName;
    private String groupDescription;
    private Integer municipalityId;
    private List<MasterForms> masterFormses = new ArrayList<>();
    private Municipalities municipalities;
    private List<FormStatuses> formStatusesesForWriteAccess = new ArrayList<>();
    private List<FormStatuses> formStatusesesForReadAccess = new ArrayList<>();
    private List<FormStatuses> formStatusesesForProcessOwners = new ArrayList<>();
    private List<MunicipalityGroupMembers> municipalityGroupMemberses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`GroupName`", nullable = true, length = 255)
    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "`GroupDescription`", nullable = true, length = 255)
    public String getGroupDescription() {
        return this.groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalityGroups")
    public List<MasterForms> getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List<MasterForms> masterFormses) {
        this.masterFormses = masterFormses;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalityGroupsByWriteAccess")
    public List<FormStatuses> getFormStatusesesForWriteAccess() {
        return this.formStatusesesForWriteAccess;
    }

    public void setFormStatusesesForWriteAccess(List<FormStatuses> formStatusesesForWriteAccess) {
        this.formStatusesesForWriteAccess = formStatusesesForWriteAccess;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalityGroupsByReadAccess")
    public List<FormStatuses> getFormStatusesesForReadAccess() {
        return this.formStatusesesForReadAccess;
    }

    public void setFormStatusesesForReadAccess(List<FormStatuses> formStatusesesForReadAccess) {
        this.formStatusesesForReadAccess = formStatusesesForReadAccess;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalityGroupsByProcessOwners")
    public List<FormStatuses> getFormStatusesesForProcessOwners() {
        return this.formStatusesesForProcessOwners;
    }

    public void setFormStatusesesForProcessOwners(List<FormStatuses> formStatusesesForProcessOwners) {
        this.formStatusesesForProcessOwners = formStatusesesForProcessOwners;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalityGroups")
    public List<MunicipalityGroupMembers> getMunicipalityGroupMemberses() {
        return this.municipalityGroupMemberses;
    }

    public void setMunicipalityGroupMemberses(List<MunicipalityGroupMembers> municipalityGroupMemberses) {
        this.municipalityGroupMemberses = municipalityGroupMemberses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MunicipalityGroups)) return false;
        final MunicipalityGroups municipalityGroups = (MunicipalityGroups) o;
        return Objects.equals(getId(), municipalityGroups.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

