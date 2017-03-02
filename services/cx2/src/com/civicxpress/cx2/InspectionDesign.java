/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
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
 * InspectionDesign generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`InspectionDesign`")
public class InspectionDesign implements Serializable {

    private Integer id;
    private Integer municipalityId;
    private String inspectDesignName;
    private String instructions;
    private Boolean active;
    private Boolean municipalityInternalInspection;
    private Boolean allowSameDayInspections;
    private Integer sameDayInspectionFee;
    private String sameDayInspectionFeeAcctCode;
    private Boolean allowCancel;
    private Integer cancelFee;
    private String cancelFeeAccount;
    private Boolean allowSameDayCancel;
    private Integer sameDayCancelFee;
    private String sameDayCancelAcctCode;
    private Integer totalInspectionsDaily;
    private Integer totalInspectionsHourly;
    private Boolean allowAdHoc;
    private Boolean callInOnly;
    private String callInMessage;
    private Integer maxDaysInAdvance;
    private Date scheduleDateOnly;
    private Timestamp scheduleDateAndTime;
    private String titlePrefix;
    private String prefixDate;
    private String prefixNumber;
    private Integer prefixStartNumber;
    private Integer prefixNumberStep;
    private Boolean prefixDashes;
    private Integer createdBy;
    private Timestamp createdAt;
    private Users users;
    private Municipalities municipalities;
    private List<InspectionOutcome> inspectionOutcomes = new ArrayList<>();
    private List<InspectionSequence> inspectionSequences = new ArrayList<>();
    private List<MasterInspections> masterInspectionses = new ArrayList<>();
    private List<InspectionCategoryMapping> inspectionCategoryMappings = new ArrayList<>();

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

    @Column(name = "`InspectDesignName`", nullable = true, length = 255)
    public String getInspectDesignName() {
        return this.inspectDesignName;
    }

    public void setInspectDesignName(String inspectDesignName) {
        this.inspectDesignName = inspectDesignName;
    }

    @Column(name = "`Instructions`", nullable = true, length = 1000)
    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "`MunicipalityInternalInspection`", nullable = true)
    public Boolean getMunicipalityInternalInspection() {
        return this.municipalityInternalInspection;
    }

    public void setMunicipalityInternalInspection(Boolean municipalityInternalInspection) {
        this.municipalityInternalInspection = municipalityInternalInspection;
    }

    @Column(name = "`AllowSameDayInspections`", nullable = true)
    public Boolean getAllowSameDayInspections() {
        return this.allowSameDayInspections;
    }

    public void setAllowSameDayInspections(Boolean allowSameDayInspections) {
        this.allowSameDayInspections = allowSameDayInspections;
    }

    @Column(name = "`SameDayInspectionFee`", nullable = true, scale = 0, precision = 10)
    public Integer getSameDayInspectionFee() {
        return this.sameDayInspectionFee;
    }

    public void setSameDayInspectionFee(Integer sameDayInspectionFee) {
        this.sameDayInspectionFee = sameDayInspectionFee;
    }

    @Column(name = "`SameDayInspectionFeeAcctCode`", nullable = true, length = 255)
    public String getSameDayInspectionFeeAcctCode() {
        return this.sameDayInspectionFeeAcctCode;
    }

    public void setSameDayInspectionFeeAcctCode(String sameDayInspectionFeeAcctCode) {
        this.sameDayInspectionFeeAcctCode = sameDayInspectionFeeAcctCode;
    }

    @Column(name = "`AllowCancel`", nullable = true)
    public Boolean getAllowCancel() {
        return this.allowCancel;
    }

    public void setAllowCancel(Boolean allowCancel) {
        this.allowCancel = allowCancel;
    }

    @Column(name = "`CancelFee`", nullable = true, scale = 0, precision = 10)
    public Integer getCancelFee() {
        return this.cancelFee;
    }

    public void setCancelFee(Integer cancelFee) {
        this.cancelFee = cancelFee;
    }

    @Column(name = "`CancelFeeAccount`", nullable = true, length = 255)
    public String getCancelFeeAccount() {
        return this.cancelFeeAccount;
    }

    public void setCancelFeeAccount(String cancelFeeAccount) {
        this.cancelFeeAccount = cancelFeeAccount;
    }

    @Column(name = "`AllowSameDayCancel`", nullable = true)
    public Boolean getAllowSameDayCancel() {
        return this.allowSameDayCancel;
    }

    public void setAllowSameDayCancel(Boolean allowSameDayCancel) {
        this.allowSameDayCancel = allowSameDayCancel;
    }

    @Column(name = "`SameDayCancelFee`", nullable = true, scale = 0, precision = 10)
    public Integer getSameDayCancelFee() {
        return this.sameDayCancelFee;
    }

    public void setSameDayCancelFee(Integer sameDayCancelFee) {
        this.sameDayCancelFee = sameDayCancelFee;
    }

    @Column(name = "`SameDayCancelAcctCode`", nullable = true, length = 255)
    public String getSameDayCancelAcctCode() {
        return this.sameDayCancelAcctCode;
    }

    public void setSameDayCancelAcctCode(String sameDayCancelAcctCode) {
        this.sameDayCancelAcctCode = sameDayCancelAcctCode;
    }

    @Column(name = "`TotalInspectionsDaily`", nullable = true, scale = 0, precision = 10)
    public Integer getTotalInspectionsDaily() {
        return this.totalInspectionsDaily;
    }

    public void setTotalInspectionsDaily(Integer totalInspectionsDaily) {
        this.totalInspectionsDaily = totalInspectionsDaily;
    }

    @Column(name = "`TotalInspectionsHourly`", nullable = true, scale = 0, precision = 10)
    public Integer getTotalInspectionsHourly() {
        return this.totalInspectionsHourly;
    }

    public void setTotalInspectionsHourly(Integer totalInspectionsHourly) {
        this.totalInspectionsHourly = totalInspectionsHourly;
    }

    @Column(name = "`AllowAdHoc`", nullable = true)
    public Boolean getAllowAdHoc() {
        return this.allowAdHoc;
    }

    public void setAllowAdHoc(Boolean allowAdHoc) {
        this.allowAdHoc = allowAdHoc;
    }

    @Column(name = "`CallInOnly`", nullable = true)
    public Boolean getCallInOnly() {
        return this.callInOnly;
    }

    public void setCallInOnly(Boolean callInOnly) {
        this.callInOnly = callInOnly;
    }

    @Column(name = "`CallInMessage`", nullable = true, length = 1000)
    public String getCallInMessage() {
        return this.callInMessage;
    }

    public void setCallInMessage(String callInMessage) {
        this.callInMessage = callInMessage;
    }

    @Column(name = "`MaxDaysInAdvance`", nullable = true, scale = 0, precision = 10)
    public Integer getMaxDaysInAdvance() {
        return this.maxDaysInAdvance;
    }

    public void setMaxDaysInAdvance(Integer maxDaysInAdvance) {
        this.maxDaysInAdvance = maxDaysInAdvance;
    }

    @Column(name = "`ScheduleDateOnly`", nullable = true)
    public Date getScheduleDateOnly() {
        return this.scheduleDateOnly;
    }

    public void setScheduleDateOnly(Date scheduleDateOnly) {
        this.scheduleDateOnly = scheduleDateOnly;
    }

    @Column(name = "`ScheduleDateAndTime`", nullable = true)
    public Timestamp getScheduleDateAndTime() {
        return this.scheduleDateAndTime;
    }

    public void setScheduleDateAndTime(Timestamp scheduleDateAndTime) {
        this.scheduleDateAndTime = scheduleDateAndTime;
    }

    @Column(name = "`TitlePrefix`", nullable = true, length = 255)
    public String getTitlePrefix() {
        return this.titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    @Column(name = "`PrefixDate`", nullable = true, length = 255)
    public String getPrefixDate() {
        return this.prefixDate;
    }

    public void setPrefixDate(String prefixDate) {
        this.prefixDate = prefixDate;
    }

    @Column(name = "`PrefixNumber`", nullable = true, length = 255)
    public String getPrefixNumber() {
        return this.prefixNumber;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    @Column(name = "`PrefixStartNumber`", nullable = true, scale = 0, precision = 10)
    public Integer getPrefixStartNumber() {
        return this.prefixStartNumber;
    }

    public void setPrefixStartNumber(Integer prefixStartNumber) {
        this.prefixStartNumber = prefixStartNumber;
    }

    @Column(name = "`PrefixNumberStep`", nullable = true, scale = 0, precision = 10)
    public Integer getPrefixNumberStep() {
        return this.prefixNumberStep;
    }

    public void setPrefixNumberStep(Integer prefixNumberStep) {
        this.prefixNumberStep = prefixNumberStep;
    }

    @Column(name = "`PrefixDashes`", nullable = true)
    public Boolean getPrefixDashes() {
        return this.prefixDashes;
    }

    public void setPrefixDashes(Boolean prefixDashes) {
        this.prefixDashes = prefixDashes;
    }

    @Column(name = "`CreatedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`CreatedAt`", nullable = true)
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionDesign")
    public List<InspectionOutcome> getInspectionOutcomes() {
        return this.inspectionOutcomes;
    }

    public void setInspectionOutcomes(List<InspectionOutcome> inspectionOutcomes) {
        this.inspectionOutcomes = inspectionOutcomes;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionDesign")
    public List<InspectionSequence> getInspectionSequences() {
        return this.inspectionSequences;
    }

    public void setInspectionSequences(List<InspectionSequence> inspectionSequences) {
        this.inspectionSequences = inspectionSequences;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionDesign")
    public List<MasterInspections> getMasterInspectionses() {
        return this.masterInspectionses;
    }

    public void setMasterInspectionses(List<MasterInspections> masterInspectionses) {
        this.masterInspectionses = masterInspectionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionDesign")
    public List<InspectionCategoryMapping> getInspectionCategoryMappings() {
        return this.inspectionCategoryMappings;
    }

    public void setInspectionCategoryMappings(List<InspectionCategoryMapping> inspectionCategoryMappings) {
        this.inspectionCategoryMappings = inspectionCategoryMappings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InspectionDesign)) return false;
        final InspectionDesign inspectionDesign = (InspectionDesign) o;
        return Objects.equals(getId(), inspectionDesign.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

