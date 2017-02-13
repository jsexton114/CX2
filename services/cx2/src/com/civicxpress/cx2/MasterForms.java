/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * MasterForms generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`MasterForms`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`FormGUID`"})})
public class MasterForms implements Serializable {

    private String formGuid;
    private Integer municipalityId;
    private Integer formTypeId;
    private Integer userId;
    private Integer formStatusId;
    private Integer assignedToGroupId;
    private Boolean closed;
    private String totalFees;
    private String totalPayment;
    private String balanceDue;
    private Integer cxvendorId;
    private String recordType;
    private Date issuedDate;
    private Date expiresDate;
    private String formTitle;
    private Integer ownerId;
    private Date dateModified;
    private Date submittedOn;
    private FormTypes formTypes;
    private FormStatuses formStatuses;
    private Giscontacts giscontacts;
    private MunicipalityGroups municipalityGroups;
    private Municipalities municipalities;
    private Users users;
    private Vendor vendor;
    private List<FormMessages> formMessageses = new ArrayList<>();
    private List<Gis2forms> gis2formses = new ArrayList<>();
    private List<ProjectForms> projectFormses = new ArrayList<>();
    private List<SharedWith> sharedWiths = new ArrayList<>();
    private List<Vendors2form> vendors2forms = new ArrayList<>();

    @Id
    @Column(name = "`FormGUID`", nullable = false, length = 255)
    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`FormTypeId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Column(name = "`UserId`", nullable = true, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "`FormStatusId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormStatusId() {
        return this.formStatusId;
    }

    public void setFormStatusId(Integer formStatusId) {
        this.formStatusId = formStatusId;
    }

    @Column(name = "`AssignedToGroupId`", nullable = true, scale = 0, precision = 10)
    public Integer getAssignedToGroupId() {
        return this.assignedToGroupId;
    }

    public void setAssignedToGroupId(Integer assignedToGroupId) {
        this.assignedToGroupId = assignedToGroupId;
    }

    @Column(name = "`Closed`", nullable = true)
    public Boolean getClosed() {
        return this.closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    @Column(name = "`TotalFees`", nullable = true, length = 255)
    public String getTotalFees() {
        return this.totalFees;
    }

    public void setTotalFees(String totalFees) {
        this.totalFees = totalFees;
    }

    @Column(name = "`TotalPayment`", nullable = true, length = 255)
    public String getTotalPayment() {
        return this.totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Column(name = "`BalanceDue`", nullable = true, length = 255)
    public String getBalanceDue() {
        return this.balanceDue;
    }

    public void setBalanceDue(String balanceDue) {
        this.balanceDue = balanceDue;
    }

    @Column(name = "`CXVendorId`", nullable = true, scale = 0, precision = 10)
    public Integer getCxvendorId() {
        return this.cxvendorId;
    }

    public void setCxvendorId(Integer cxvendorId) {
        this.cxvendorId = cxvendorId;
    }

    @Column(name = "`RecordType`", nullable = true, length = 255)
    public String getRecordType() {
        return this.recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "`IssuedDate`", nullable = true)
    public Date getIssuedDate() {
        return this.issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "`ExpiresDate`", nullable = true)
    public Date getExpiresDate() {
        return this.expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    @Column(name = "`FormTitle`", nullable = true, length = 255)
    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    @Column(name = "`OwnerId`", nullable = true, scale = 0, precision = 10)
    public Integer getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`DateModified`", nullable = true)
    public Date getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`SubmittedOn`", nullable = true)
    public Date getSubmittedOn() {
        return this.submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
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
    @JoinColumn(name = "`FormStatusId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormStatuses getFormStatuses() {
        return this.formStatuses;
    }

    public void setFormStatuses(FormStatuses formStatuses) {
        if(formStatuses != null) {
            this.formStatusId = formStatuses.getId();
        }

        this.formStatuses = formStatuses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`OwnerId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Giscontacts getGiscontacts() {
        return this.giscontacts;
    }

    public void setGiscontacts(Giscontacts giscontacts) {
        if(giscontacts != null) {
            this.ownerId = giscontacts.getId();
        }

        this.giscontacts = giscontacts;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`AssignedToGroupId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public MunicipalityGroups getMunicipalityGroups() {
        return this.municipalityGroups;
    }

    public void setMunicipalityGroups(MunicipalityGroups municipalityGroups) {
        if(municipalityGroups != null) {
            this.assignedToGroupId = municipalityGroups.getId();
        }

        this.municipalityGroups = municipalityGroups;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CXVendorId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Vendor getVendor() {
        return this.vendor;
    }

    public void setVendor(Vendor vendor) {
        if(vendor != null) {
            this.cxvendorId = vendor.getId();
        }

        this.vendor = vendor;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterForms")
    public List<FormMessages> getFormMessageses() {
        return this.formMessageses;
    }

    public void setFormMessageses(List<FormMessages> formMessageses) {
        this.formMessageses = formMessageses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterForms")
    public List<Gis2forms> getGis2formses() {
        return this.gis2formses;
    }

    public void setGis2formses(List<Gis2forms> gis2formses) {
        this.gis2formses = gis2formses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterForms")
    public List<ProjectForms> getProjectFormses() {
        return this.projectFormses;
    }

    public void setProjectFormses(List<ProjectForms> projectFormses) {
        this.projectFormses = projectFormses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterForms")
    public List<SharedWith> getSharedWiths() {
        return this.sharedWiths;
    }

    public void setSharedWiths(List<SharedWith> sharedWiths) {
        this.sharedWiths = sharedWiths;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterForms")
    public List<Vendors2form> getVendors2forms() {
        return this.vendors2forms;
    }

    public void setVendors2forms(List<Vendors2form> vendors2forms) {
        this.vendors2forms = vendors2forms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MasterForms)) return false;
        final MasterForms masterForms = (MasterForms) o;
        return Objects.equals(getFormGuid(), masterForms.getFormGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormGuid());
    }
}

