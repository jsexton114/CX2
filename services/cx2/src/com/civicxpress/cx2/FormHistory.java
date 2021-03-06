/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Timestamp;
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
 * FormHistory generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormHistory`")
public class FormHistory implements Serializable {

    private Integer id;
    private String formGuid;
    private Integer formTypeId;
    private Integer newStatusId;
    private Integer oldStatusId;
    private String comments;
    private Integer createdBy;
    private Timestamp createdTime;
    private FormStatuses formStatusesByOldStatusId;
    private FormStatuses formStatusesByNewStatusId;
    private FormTypes formTypes;
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

    @Column(name = "`FormGUID`", nullable = true, length = 255)
    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    @Column(name = "`FormTypeId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Column(name = "`NewStatusId`", nullable = true, scale = 0, precision = 10)
    public Integer getNewStatusId() {
        return this.newStatusId;
    }

    public void setNewStatusId(Integer newStatusId) {
        this.newStatusId = newStatusId;
    }

    @Column(name = "`OldStatusId`", nullable = true, scale = 0, precision = 10)
    public Integer getOldStatusId() {
        return this.oldStatusId;
    }

    public void setOldStatusId(Integer oldStatusId) {
        this.oldStatusId = oldStatusId;
    }

    @Column(name = "`Comments`", nullable = true, length = 1000)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "`CreatedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`CreatedTime`", nullable = true)
    public Timestamp getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`OldStatusId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormStatuses getFormStatusesByOldStatusId() {
        return this.formStatusesByOldStatusId;
    }

    public void setFormStatusesByOldStatusId(FormStatuses formStatusesByOldStatusId) {
        if(formStatusesByOldStatusId != null) {
            this.oldStatusId = formStatusesByOldStatusId.getId();
        }

        this.formStatusesByOldStatusId = formStatusesByOldStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`NewStatusId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormStatuses getFormStatusesByNewStatusId() {
        return this.formStatusesByNewStatusId;
    }

    public void setFormStatusesByNewStatusId(FormStatuses formStatusesByNewStatusId) {
        if(formStatusesByNewStatusId != null) {
            this.newStatusId = formStatusesByNewStatusId.getId();
        }

        this.formStatusesByNewStatusId = formStatusesByNewStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormTypeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormTypes getFormTypes() {
        return this.formTypes;
    }

    public void setFormTypes(FormTypes formTypes) {
        if(formTypes != null) {
            this.formTypeId = formTypes.getId();
        }

        this.formTypes = formTypes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CreatedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.createdBy = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormHistory)) return false;
        final FormHistory formHistory = (FormHistory) o;
        return Objects.equals(getId(), formHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

