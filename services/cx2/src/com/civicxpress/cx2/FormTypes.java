/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * FormTypes generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormTypes`")
public class FormTypes implements Serializable {

    private Integer id;
    private String formType;
    private String flatFee;
    private String flatFeeAccountingCode;
    private String sfFee;
    private String sfFeeAccountingCode;
    private String unitFee;
    private String unitFeeAccountingCode;
    private String stateFee;
    private String stateFeeAccountingCode;
    private String report;
    private Boolean active;
    private Boolean municipalityInternalForm;
    private Date createdDate;
    private Boolean collectFees;
    private Boolean automaticFees;
    private Boolean inspections;
    private Boolean payments;
    private Boolean sharedWith;
    private Boolean attachments;
    private Boolean gisrecord;
    private Boolean multipleGisrecords;
    private Boolean gismap;
    private String formTableName;
    private int municipalityId;
    private String formTypeGuid;
    private String basementFee;
    private String basementFeeAccountingCode;
    private Boolean vendorSelection;
    private String titlePrefix;
    private String prefixDate;
    private String prefixNumber;
    private BigInteger prefixNumberStart;
    private int prefixNumberStep;
    private Boolean prefixDashes;
    private BigInteger currentPrefixNumber;
    private Integer prefixNumberResetOn;
    private boolean multipleVendors;
    private boolean requireOwner;
    private String instructions;
    private Boolean forceInspectionSequence;
    private boolean requireSignature;
    private boolean codeEnforcement;
    private String propertyType;
    private String expirationType;
    private Integer expirationDays;
    private Integer expirationStatusId;
    private Municipalities municipalities;
    private List<CodesToForm> codesToForms = new ArrayList<>();
    private List<FormCategoryMapping> formCategoryMappings = new ArrayList<>();
    private List<FormHistory> formHistories = new ArrayList<>();
    private List<FormDraft> formDrafts = new ArrayList<>();
    private List<FormStatuses> formStatuseses = new ArrayList<>();
    private List<FormToInspectionCategoryMapping> formToInspectionCategoryMappings = new ArrayList<>();
    private List<FormTypeFields> formTypeFieldses = new ArrayList<>();
    private List<LetterTemplates> letterTemplateses = new ArrayList<>();
    private List<InspectionSequence> inspectionSequences = new ArrayList<>();
    private List<MasterForms> masterFormses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`FormType`", nullable = true, length = 255)
    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Column(name = "`FlatFee`", nullable = true, length = 255)
    public String getFlatFee() {
        return this.flatFee;
    }

    public void setFlatFee(String flatFee) {
        this.flatFee = flatFee;
    }

    @Column(name = "`FlatFeeAccountingCode`", nullable = true, length = 255)
    public String getFlatFeeAccountingCode() {
        return this.flatFeeAccountingCode;
    }

    public void setFlatFeeAccountingCode(String flatFeeAccountingCode) {
        this.flatFeeAccountingCode = flatFeeAccountingCode;
    }

    @Column(name = "`SfFee`", nullable = true, length = 255)
    public String getSfFee() {
        return this.sfFee;
    }

    public void setSfFee(String sfFee) {
        this.sfFee = sfFee;
    }

    @Column(name = "`SfFeeAccountingCode`", nullable = true, length = 255)
    public String getSfFeeAccountingCode() {
        return this.sfFeeAccountingCode;
    }

    public void setSfFeeAccountingCode(String sfFeeAccountingCode) {
        this.sfFeeAccountingCode = sfFeeAccountingCode;
    }

    @Column(name = "`UnitFee`", nullable = true, length = 255)
    public String getUnitFee() {
        return this.unitFee;
    }

    public void setUnitFee(String unitFee) {
        this.unitFee = unitFee;
    }

    @Column(name = "`UnitFeeAccountingCode`", nullable = true, length = 255)
    public String getUnitFeeAccountingCode() {
        return this.unitFeeAccountingCode;
    }

    public void setUnitFeeAccountingCode(String unitFeeAccountingCode) {
        this.unitFeeAccountingCode = unitFeeAccountingCode;
    }

    @Column(name = "`StateFee`", nullable = true, length = 255)
    public String getStateFee() {
        return this.stateFee;
    }

    public void setStateFee(String stateFee) {
        this.stateFee = stateFee;
    }

    @Column(name = "`StateFeeAccountingCode`", nullable = true, length = 255)
    public String getStateFeeAccountingCode() {
        return this.stateFeeAccountingCode;
    }

    public void setStateFeeAccountingCode(String stateFeeAccountingCode) {
        this.stateFeeAccountingCode = stateFeeAccountingCode;
    }

    @Column(name = "`Report`", nullable = true, length = 255)
    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "`MunicipalityInternalForm`", nullable = true)
    public Boolean getMunicipalityInternalForm() {
        return this.municipalityInternalForm;
    }

    public void setMunicipalityInternalForm(Boolean municipalityInternalForm) {
        this.municipalityInternalForm = municipalityInternalForm;
    }

    @Column(name = "`CreatedDate`", nullable = true)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "`CollectFees`", nullable = true)
    public Boolean getCollectFees() {
        return this.collectFees;
    }

    public void setCollectFees(Boolean collectFees) {
        this.collectFees = collectFees;
    }

    @Column(name = "`AutomaticFees`", nullable = true)
    public Boolean getAutomaticFees() {
        return this.automaticFees;
    }

    public void setAutomaticFees(Boolean automaticFees) {
        this.automaticFees = automaticFees;
    }

    @Column(name = "`Inspections`", nullable = true)
    public Boolean getInspections() {
        return this.inspections;
    }

    public void setInspections(Boolean inspections) {
        this.inspections = inspections;
    }

    @Column(name = "`Payments`", nullable = true)
    public Boolean getPayments() {
        return this.payments;
    }

    public void setPayments(Boolean payments) {
        this.payments = payments;
    }

    @Column(name = "`SharedWith`", nullable = true)
    public Boolean getSharedWith() {
        return this.sharedWith;
    }

    public void setSharedWith(Boolean sharedWith) {
        this.sharedWith = sharedWith;
    }

    @Column(name = "`Attachments`", nullable = true)
    public Boolean getAttachments() {
        return this.attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    @Column(name = "`GISRecord`", nullable = true)
    public Boolean getGisrecord() {
        return this.gisrecord;
    }

    public void setGisrecord(Boolean gisrecord) {
        this.gisrecord = gisrecord;
    }

    @Column(name = "`MultipleGISRecords`", nullable = true)
    public Boolean getMultipleGisrecords() {
        return this.multipleGisrecords;
    }

    public void setMultipleGisrecords(Boolean multipleGisrecords) {
        this.multipleGisrecords = multipleGisrecords;
    }

    @Column(name = "`GISMap`", nullable = true)
    public Boolean getGismap() {
        return this.gismap;
    }

    public void setGismap(Boolean gismap) {
        this.gismap = gismap;
    }

    @Column(name = "`FormTableName`", nullable = true, length = 255)
    public String getFormTableName() {
        return this.formTableName;
    }

    public void setFormTableName(String formTableName) {
        this.formTableName = formTableName;
    }

    @Column(name = "`MunicipalityId`", nullable = false, scale = 0, precision = 10)
    public int getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`FormTypeGuid`", nullable = true, length = 32)
    public String getFormTypeGuid() {
        return this.formTypeGuid;
    }

    public void setFormTypeGuid(String formTypeGuid) {
        this.formTypeGuid = formTypeGuid;
    }

    @Column(name = "`BasementFee`", nullable = true, length = 255)
    public String getBasementFee() {
        return this.basementFee;
    }

    public void setBasementFee(String basementFee) {
        this.basementFee = basementFee;
    }

    @Column(name = "`BasementFeeAccountingCode`", nullable = true, length = 255)
    public String getBasementFeeAccountingCode() {
        return this.basementFeeAccountingCode;
    }

    public void setBasementFeeAccountingCode(String basementFeeAccountingCode) {
        this.basementFeeAccountingCode = basementFeeAccountingCode;
    }

    @Column(name = "`VendorSelection`", nullable = true)
    public Boolean getVendorSelection() {
        return this.vendorSelection;
    }

    public void setVendorSelection(Boolean vendorSelection) {
        this.vendorSelection = vendorSelection;
    }

    @Column(name = "`TitlePrefix`", nullable = true, length = 255)
    public String getTitlePrefix() {
        return this.titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    @Column(name = "`PrefixDate`", nullable = false, length = 255)
    public String getPrefixDate() {
        return this.prefixDate;
    }

    public void setPrefixDate(String prefixDate) {
        this.prefixDate = prefixDate;
    }

    @Column(name = "`PrefixNumber`", nullable = false, length = 255)
    public String getPrefixNumber() {
        return this.prefixNumber;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    @Column(name = "`PrefixNumberStart`", nullable = false, scale = 0, precision = 38)
    public BigInteger getPrefixNumberStart() {
        return this.prefixNumberStart;
    }

    public void setPrefixNumberStart(BigInteger prefixNumberStart) {
        this.prefixNumberStart = prefixNumberStart;
    }

    @Column(name = "`PrefixNumberStep`", nullable = false, scale = 0, precision = 10)
    public int getPrefixNumberStep() {
        return this.prefixNumberStep;
    }

    public void setPrefixNumberStep(int prefixNumberStep) {
        this.prefixNumberStep = prefixNumberStep;
    }

    @Column(name = "`PrefixDashes`", nullable = true)
    public Boolean getPrefixDashes() {
        return this.prefixDashes;
    }

    public void setPrefixDashes(Boolean prefixDashes) {
        this.prefixDashes = prefixDashes;
    }

    @Column(name = "`CurrentPrefixNumber`", nullable = true, scale = 0, precision = 38)
    public BigInteger getCurrentPrefixNumber() {
        return this.currentPrefixNumber;
    }

    public void setCurrentPrefixNumber(BigInteger currentPrefixNumber) {
        this.currentPrefixNumber = currentPrefixNumber;
    }

    @Column(name = "`PrefixNumberResetOn`", nullable = true, scale = 0, precision = 10)
    public Integer getPrefixNumberResetOn() {
        return this.prefixNumberResetOn;
    }

    public void setPrefixNumberResetOn(Integer prefixNumberResetOn) {
        this.prefixNumberResetOn = prefixNumberResetOn;
    }

    @Column(name = "`MultipleVendors`", nullable = false)
    public boolean isMultipleVendors() {
        return this.multipleVendors;
    }

    public void setMultipleVendors(boolean multipleVendors) {
        this.multipleVendors = multipleVendors;
    }

    @Column(name = "`RequireOwner`", nullable = false)
    public boolean isRequireOwner() {
        return this.requireOwner;
    }

    public void setRequireOwner(boolean requireOwner) {
        this.requireOwner = requireOwner;
    }

    @Column(name = "`Instructions`", nullable = true, length = 2147483647)
    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Column(name = "`ForceInspectionSequence`", nullable = true)
    public Boolean getForceInspectionSequence() {
        return this.forceInspectionSequence;
    }

    public void setForceInspectionSequence(Boolean forceInspectionSequence) {
        this.forceInspectionSequence = forceInspectionSequence;
    }

    @Column(name = "`RequireSignature`", nullable = false)
    public boolean isRequireSignature() {
        return this.requireSignature;
    }

    public void setRequireSignature(boolean requireSignature) {
        this.requireSignature = requireSignature;
    }

    @Column(name = "`CodeEnforcement`", nullable = false)
    public boolean isCodeEnforcement() {
        return this.codeEnforcement;
    }

    public void setCodeEnforcement(boolean codeEnforcement) {
        this.codeEnforcement = codeEnforcement;
    }

    @Column(name = "`PropertyType`", nullable = true, length = 255)
    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    @Column(name = "`ExpirationType`", nullable = true, length = 255)
    public String getExpirationType() {
        return this.expirationType;
    }

    public void setExpirationType(String expirationType) {
        this.expirationType = expirationType;
    }

    @Column(name = "`ExpirationDays`", nullable = true, scale = 0, precision = 10)
    public Integer getExpirationDays() {
        return this.expirationDays;
    }

    public void setExpirationDays(Integer expirationDays) {
        this.expirationDays = expirationDays;
    }

    @Column(name = "`ExpirationStatusId`", nullable = true, scale = 0, precision = 10)
    public Integer getExpirationStatusId() {
        return this.expirationStatusId;
    }

    public void setExpirationStatusId(Integer expirationStatusId) {
        this.expirationStatusId = expirationStatusId;
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<CodesToForm> getCodesToForms() {
        return this.codesToForms;
    }

    public void setCodesToForms(List<CodesToForm> codesToForms) {
        this.codesToForms = codesToForms;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormCategoryMapping> getFormCategoryMappings() {
        return this.formCategoryMappings;
    }

    public void setFormCategoryMappings(List<FormCategoryMapping> formCategoryMappings) {
        this.formCategoryMappings = formCategoryMappings;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormHistory> getFormHistories() {
        return this.formHistories;
    }

    public void setFormHistories(List<FormHistory> formHistories) {
        this.formHistories = formHistories;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormDraft> getFormDrafts() {
        return this.formDrafts;
    }

    public void setFormDrafts(List<FormDraft> formDrafts) {
        this.formDrafts = formDrafts;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormStatuses> getFormStatuseses() {
        return this.formStatuseses;
    }

    public void setFormStatuseses(List<FormStatuses> formStatuseses) {
        this.formStatuseses = formStatuseses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormToInspectionCategoryMapping> getFormToInspectionCategoryMappings() {
        return this.formToInspectionCategoryMappings;
    }

    public void setFormToInspectionCategoryMappings(List<FormToInspectionCategoryMapping> formToInspectionCategoryMappings) {
        this.formToInspectionCategoryMappings = formToInspectionCategoryMappings;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormTypeFields> getFormTypeFieldses() {
        return this.formTypeFieldses;
    }

    public void setFormTypeFieldses(List<FormTypeFields> formTypeFieldses) {
        this.formTypeFieldses = formTypeFieldses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<LetterTemplates> getLetterTemplateses() {
        return this.letterTemplateses;
    }

    public void setLetterTemplateses(List<LetterTemplates> letterTemplateses) {
        this.letterTemplateses = letterTemplateses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<InspectionSequence> getInspectionSequences() {
        return this.inspectionSequences;
    }

    public void setInspectionSequences(List<InspectionSequence> inspectionSequences) {
        this.inspectionSequences = inspectionSequences;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<MasterForms> getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List<MasterForms> masterFormses) {
        this.masterFormses = masterFormses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormTypes)) return false;
        final FormTypes formTypes = (FormTypes) o;
        return Objects.equals(getId(), formTypes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

