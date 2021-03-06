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
import javax.persistence.UniqueConstraint;

/**
 * MunicipalityGroupMembers generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`MunicipalityGroupMembers`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`MunicipalityGroupId`", "`UserId`"})})
public class MunicipalityGroupMembers implements Serializable {

    private Integer id;
    private Integer municipalityGroupId;
    private Integer userId;
    private MunicipalityGroups municipalityGroups;
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`MunicipalityGroupId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityGroupId() {
        return this.municipalityGroupId;
    }

    public void setMunicipalityGroupId(Integer municipalityGroupId) {
        this.municipalityGroupId = municipalityGroupId;
    }

    @Column(name = "`UserId`", nullable = true, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`MunicipalityGroupId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public MunicipalityGroups getMunicipalityGroups() {
        return this.municipalityGroups;
    }

    public void setMunicipalityGroups(MunicipalityGroups municipalityGroups) {
        if(municipalityGroups != null) {
            this.municipalityGroupId = municipalityGroups.getId();
        }

        this.municipalityGroups = municipalityGroups;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`UserId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.userId = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MunicipalityGroupMembers)) return false;
        final MunicipalityGroupMembers municipalityGroupMembers = (MunicipalityGroupMembers) o;
        return Objects.equals(getId(), municipalityGroupMembers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

