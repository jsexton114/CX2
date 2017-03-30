/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Projects generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Projects`")
public class Projects implements Serializable {

    private String projectGuid;
    private Integer municipalityId;
    private String projectName;
    private String projectDescription;
    private String projectGoals;
    private Timestamp estStartDate;
    private Timestamp estEndDate;
    private Integer createdBy;
    private Timestamp createdDate;
    private Integer modifiedBy;
    private Timestamp modifiedDate;
    private Boolean active;
    private Municipalities municipalities;
    private Users usersByCreatedBy;
    private Users usersByModifiedBy;
    private List<Fees> feeses = new ArrayList<>();
    private List<FormMessages> formMessageses = new ArrayList<>();
    private List<MasterCases> masterCaseses = new ArrayList<>();
    private List<MasterInspections> masterInspectionses = new ArrayList<>();
    private List<ProjectForms> projectFormses = new ArrayList<>();
    private List<ProjectGisrecords> projectGisrecordses = new ArrayList<>();
    private List<ProjectTasks> projectTaskses = new ArrayList<>();
    private List<ProjectSharedWith> projectSharedWiths = new ArrayList<>();
    private List<VendorsToProject> vendorsToProjects = new ArrayList<>();

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "`ProjectGUID`", nullable = false, length = 32)
    public String getProjectGuid() {
        return this.projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`ProjectName`", nullable = true, length = 255)
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "`ProjectDescription`", nullable = true, length = 1000)
    public String getProjectDescription() {
        return this.projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Column(name = "`ProjectGoals`", nullable = true, length = 1000)
    public String getProjectGoals() {
        return this.projectGoals;
    }

    public void setProjectGoals(String projectGoals) {
        this.projectGoals = projectGoals;
    }

    @Column(name = "`EstStartDate`", nullable = true)
    public Timestamp getEstStartDate() {
        return this.estStartDate;
    }

    public void setEstStartDate(Timestamp estStartDate) {
        this.estStartDate = estStartDate;
    }

    @Column(name = "`EstEndDate`", nullable = true)
    public Timestamp getEstEndDate() {
        return this.estEndDate;
    }

    public void setEstEndDate(Timestamp estEndDate) {
        this.estEndDate = estEndDate;
    }

    @Column(name = "`CreatedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`CreatedDate`", nullable = true)
    public Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "`ModifiedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name = "`ModifiedDate`", nullable = true)
    public Timestamp getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
    @JoinColumn(name = "`CreatedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByCreatedBy() {
        return this.usersByCreatedBy;
    }

    public void setUsersByCreatedBy(Users usersByCreatedBy) {
        if(usersByCreatedBy != null) {
            this.createdBy = usersByCreatedBy.getId();
        }

        this.usersByCreatedBy = usersByCreatedBy;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ModifiedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByModifiedBy() {
        return this.usersByModifiedBy;
    }

    public void setUsersByModifiedBy(Users usersByModifiedBy) {
        if(usersByModifiedBy != null) {
            this.modifiedBy = usersByModifiedBy.getId();
        }

        this.usersByModifiedBy = usersByModifiedBy;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<Fees> getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List<Fees> feeses) {
        this.feeses = feeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<FormMessages> getFormMessageses() {
        return this.formMessageses;
    }

    public void setFormMessageses(List<FormMessages> formMessageses) {
        this.formMessageses = formMessageses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<MasterCases> getMasterCaseses() {
        return this.masterCaseses;
    }

    public void setMasterCaseses(List<MasterCases> masterCaseses) {
        this.masterCaseses = masterCaseses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<MasterInspections> getMasterInspectionses() {
        return this.masterInspectionses;
    }

    public void setMasterInspectionses(List<MasterInspections> masterInspectionses) {
        this.masterInspectionses = masterInspectionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<ProjectForms> getProjectFormses() {
        return this.projectFormses;
    }

    public void setProjectFormses(List<ProjectForms> projectFormses) {
        this.projectFormses = projectFormses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<ProjectGisrecords> getProjectGisrecordses() {
        return this.projectGisrecordses;
    }

    public void setProjectGisrecordses(List<ProjectGisrecords> projectGisrecordses) {
        this.projectGisrecordses = projectGisrecordses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<ProjectTasks> getProjectTaskses() {
        return this.projectTaskses;
    }

    public void setProjectTaskses(List<ProjectTasks> projectTaskses) {
        this.projectTaskses = projectTaskses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<ProjectSharedWith> getProjectSharedWiths() {
        return this.projectSharedWiths;
    }

    public void setProjectSharedWiths(List<ProjectSharedWith> projectSharedWiths) {
        this.projectSharedWiths = projectSharedWiths;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "projects")
    public List<VendorsToProject> getVendorsToProjects() {
        return this.vendorsToProjects;
    }

    public void setVendorsToProjects(List<VendorsToProject> vendorsToProjects) {
        this.vendorsToProjects = vendorsToProjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Projects)) return false;
        final Projects projects = (Projects) o;
        return Objects.equals(getProjectGuid(), projects.getProjectGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectGuid());
    }
}

