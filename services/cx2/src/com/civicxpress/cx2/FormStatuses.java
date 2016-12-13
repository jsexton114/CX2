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
 * FormStatuses generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormStatuses`")
public class FormStatuses implements Serializable {

    private Integer id;
    private Integer formTypeId;
    private String emailTextBody;
    private String emailSubjectLine;
    private Boolean allowSharedWithEdits;
    private Boolean allowAuthorEdits;
    private Boolean allowPayment;
    private Boolean allowReporting;
    private Boolean allowInspections;
    private Boolean considerClosed;
    private Boolean recalculateAutoFees;
    private Boolean createAsRecord;
    private Long sortOrder;
    private String status;
    private String description;
    private Integer readAccess;
    private String report;
    private Integer processOwners;
    private List<SfnewResidentialStructure> sfnewResidentialStructures = new ArrayList<>();
    private List<SfnewElectricConnection> sfnewElectricConnections = new ArrayList<>();
    private List<McnewElectricConnection> mcnewElectricConnections = new ArrayList<>();
    private List<MasterForms> masterFormses = new ArrayList<>();
    private List<McnewResidentialStructure> mcnewResidentialStructures = new ArrayList<>();
    private List<Pudapplication> pudapplications = new ArrayList<>();
    private FormTypes formTypes;
    private MunicipalityGroups municipalityGroupsByReadAccess;
    private MunicipalityGroups municipalityGroupsByProcessOwners;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`FormTypeId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Column(name = "`EmailTextBody`", nullable = true, length = 255)
    public String getEmailTextBody() {
        return this.emailTextBody;
    }

    public void setEmailTextBody(String emailTextBody) {
        this.emailTextBody = emailTextBody;
    }

    @Column(name = "`EmailSubjectLine`", nullable = true, length = 255)
    public String getEmailSubjectLine() {
        return this.emailSubjectLine;
    }

    public void setEmailSubjectLine(String emailSubjectLine) {
        this.emailSubjectLine = emailSubjectLine;
    }

    @Column(name = "`AllowSharedWithEdits`", nullable = true)
    public Boolean getAllowSharedWithEdits() {
        return this.allowSharedWithEdits;
    }

    public void setAllowSharedWithEdits(Boolean allowSharedWithEdits) {
        this.allowSharedWithEdits = allowSharedWithEdits;
    }

    @Column(name = "`AllowAuthorEdits`", nullable = true)
    public Boolean getAllowAuthorEdits() {
        return this.allowAuthorEdits;
    }

    public void setAllowAuthorEdits(Boolean allowAuthorEdits) {
        this.allowAuthorEdits = allowAuthorEdits;
    }

    @Column(name = "`AllowPayment`", nullable = true)
    public Boolean getAllowPayment() {
        return this.allowPayment;
    }

    public void setAllowPayment(Boolean allowPayment) {
        this.allowPayment = allowPayment;
    }

    @Column(name = "`AllowReporting`", nullable = true)
    public Boolean getAllowReporting() {
        return this.allowReporting;
    }

    public void setAllowReporting(Boolean allowReporting) {
        this.allowReporting = allowReporting;
    }

    @Column(name = "`AllowInspections`", nullable = true)
    public Boolean getAllowInspections() {
        return this.allowInspections;
    }

    public void setAllowInspections(Boolean allowInspections) {
        this.allowInspections = allowInspections;
    }

    @Column(name = "`ConsiderClosed`", nullable = true)
    public Boolean getConsiderClosed() {
        return this.considerClosed;
    }

    public void setConsiderClosed(Boolean considerClosed) {
        this.considerClosed = considerClosed;
    }

    @Column(name = "`RecalculateAutoFees`", nullable = true)
    public Boolean getRecalculateAutoFees() {
        return this.recalculateAutoFees;
    }

    public void setRecalculateAutoFees(Boolean recalculateAutoFees) {
        this.recalculateAutoFees = recalculateAutoFees;
    }

    @Column(name = "`CreateAsRecord`", nullable = true)
    public Boolean getCreateAsRecord() {
        return this.createAsRecord;
    }

    public void setCreateAsRecord(Boolean createAsRecord) {
        this.createAsRecord = createAsRecord;
    }

    @Column(name = "`SortOrder`", nullable = true, scale = 0, precision = 19)
    public Long getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Column(name = "`Status`", nullable = true, length = 255)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "`Description`", nullable = true, length = 255)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "`ReadAccess`", nullable = true, scale = 0, precision = 10)
    public Integer getReadAccess() {
        return this.readAccess;
    }

    public void setReadAccess(Integer readAccess) {
        this.readAccess = readAccess;
    }

    @Column(name = "`Report`", nullable = true, length = 255)
    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Column(name = "`ProcessOwners`", nullable = true, scale = 0, precision = 10)
    public Integer getProcessOwners() {
        return this.processOwners;
    }

    public void setProcessOwners(Integer processOwners) {
        this.processOwners = processOwners;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formStatuses")
    public List<SfnewResidentialStructure> getSfnewResidentialStructures() {
        return this.sfnewResidentialStructures;
    }

    public void setSfnewResidentialStructures(List<SfnewResidentialStructure> sfnewResidentialStructures) {
        this.sfnewResidentialStructures = sfnewResidentialStructures;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formStatuses")
    public List<SfnewElectricConnection> getSfnewElectricConnections() {
        return this.sfnewElectricConnections;
    }

    public void setSfnewElectricConnections(List<SfnewElectricConnection> sfnewElectricConnections) {
        this.sfnewElectricConnections = sfnewElectricConnections;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formStatuses")
    public List<McnewElectricConnection> getMcnewElectricConnections() {
        return this.mcnewElectricConnections;
    }

    public void setMcnewElectricConnections(List<McnewElectricConnection> mcnewElectricConnections) {
        this.mcnewElectricConnections = mcnewElectricConnections;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formStatuses")
    public List<MasterForms> getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List<MasterForms> masterFormses) {
        this.masterFormses = masterFormses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formStatuses")
    public List<McnewResidentialStructure> getMcnewResidentialStructures() {
        return this.mcnewResidentialStructures;
    }

    public void setMcnewResidentialStructures(List<McnewResidentialStructure> mcnewResidentialStructures) {
        this.mcnewResidentialStructures = mcnewResidentialStructures;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formStatuses")
    public List<Pudapplication> getPudapplications() {
        return this.pudapplications;
    }

    public void setPudapplications(List<Pudapplication> pudapplications) {
        this.pudapplications = pudapplications;
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
    @JoinColumn(name = "`ReadAccess`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public MunicipalityGroups getMunicipalityGroupsByReadAccess() {
        return this.municipalityGroupsByReadAccess;
    }

    public void setMunicipalityGroupsByReadAccess(MunicipalityGroups municipalityGroupsByReadAccess) {
        if(municipalityGroupsByReadAccess != null) {
            this.readAccess = municipalityGroupsByReadAccess.getId();
        }

        this.municipalityGroupsByReadAccess = municipalityGroupsByReadAccess;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ProcessOwners`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public MunicipalityGroups getMunicipalityGroupsByProcessOwners() {
        return this.municipalityGroupsByProcessOwners;
    }

    public void setMunicipalityGroupsByProcessOwners(MunicipalityGroups municipalityGroupsByProcessOwners) {
        if(municipalityGroupsByProcessOwners != null) {
            this.processOwners = municipalityGroupsByProcessOwners.getId();
        }

        this.municipalityGroupsByProcessOwners = municipalityGroupsByProcessOwners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormStatuses)) return false;
        final FormStatuses formStatuses = (FormStatuses) o;
        return Objects.equals(getId(), formStatuses.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

