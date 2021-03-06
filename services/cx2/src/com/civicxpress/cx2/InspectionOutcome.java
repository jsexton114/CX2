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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * InspectionOutcome generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`InspectionOutcome`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`InspectDesignId`", "`OutcomeOrder`"})})
public class InspectionOutcome implements Serializable {

    private Integer id;
    private Integer inspectDesignId;
    private String outcome;
    private boolean assessFeeYn;
    private boolean considerClosed;
    private Integer outcomeOrder;
    private boolean sendEmail;
    private String emailSubjectLine;
    private String emailBodyText;
    private boolean autoReInspection;
    private Integer daysToAutoReInspection;
    private InspectionDesign inspectionDesign;
    private List<InspectionHistory> inspectionHistoriesForNewOutcomeId;
    private List<InspectionHistory> inspectionHistoriesForOldOutcomeId;
    private List<InspectionOutcomeFee> inspectionOutcomeFees;
    private List<LetterTemplateToInspectionOutcome> letterTemplateToInspectionOutcomes;
    private List<MasterInspections> masterInspectionses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`InspectDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectDesignId() {
        return this.inspectDesignId;
    }

    public void setInspectDesignId(Integer inspectDesignId) {
        this.inspectDesignId = inspectDesignId;
    }

    @Column(name = "`Outcome`", nullable = true, length = 255)
    public String getOutcome() {
        return this.outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Column(name = "`AssessFeeYN`", nullable = false)
    public boolean isAssessFeeYn() {
        return this.assessFeeYn;
    }

    public void setAssessFeeYn(boolean assessFeeYn) {
        this.assessFeeYn = assessFeeYn;
    }

    @Column(name = "`ConsiderClosed`", nullable = false)
    public boolean isConsiderClosed() {
        return this.considerClosed;
    }

    public void setConsiderClosed(boolean considerClosed) {
        this.considerClosed = considerClosed;
    }

    @Column(name = "`OutcomeOrder`", nullable = true, scale = 0, precision = 10)
    public Integer getOutcomeOrder() {
        return this.outcomeOrder;
    }

    public void setOutcomeOrder(Integer outcomeOrder) {
        this.outcomeOrder = outcomeOrder;
    }

    @Column(name = "`SendEmail`", nullable = false)
    public boolean isSendEmail() {
        return this.sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Column(name = "`EmailSubjectLine`", nullable = true, length = 255)
    public String getEmailSubjectLine() {
        return this.emailSubjectLine;
    }

    public void setEmailSubjectLine(String emailSubjectLine) {
        this.emailSubjectLine = emailSubjectLine;
    }

    @Column(name = "`EmailBodyText`", nullable = true, length = 5000)
    public String getEmailBodyText() {
        return this.emailBodyText;
    }

    public void setEmailBodyText(String emailBodyText) {
        this.emailBodyText = emailBodyText;
    }

    @Column(name = "`AutoReInspection`", nullable = false)
    public boolean isAutoReInspection() {
        return this.autoReInspection;
    }

    public void setAutoReInspection(boolean autoReInspection) {
        this.autoReInspection = autoReInspection;
    }

    @Column(name = "`DaysToAutoReInspection`", nullable = true, scale = 0, precision = 10)
    public Integer getDaysToAutoReInspection() {
        return this.daysToAutoReInspection;
    }

    public void setDaysToAutoReInspection(Integer daysToAutoReInspection) {
        this.daysToAutoReInspection = daysToAutoReInspection;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectDesignId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionDesign getInspectionDesign() {
        return this.inspectionDesign;
    }

    public void setInspectionDesign(InspectionDesign inspectionDesign) {
        if(inspectionDesign != null) {
            this.inspectDesignId = inspectionDesign.getId();
        }

        this.inspectionDesign = inspectionDesign;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcomeByNewOutcomeId")
    public List<InspectionHistory> getInspectionHistoriesForNewOutcomeId() {
        return this.inspectionHistoriesForNewOutcomeId;
    }

    public void setInspectionHistoriesForNewOutcomeId(List<InspectionHistory> inspectionHistoriesForNewOutcomeId) {
        this.inspectionHistoriesForNewOutcomeId = inspectionHistoriesForNewOutcomeId;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcomeByOldOutcomeId")
    public List<InspectionHistory> getInspectionHistoriesForOldOutcomeId() {
        return this.inspectionHistoriesForOldOutcomeId;
    }

    public void setInspectionHistoriesForOldOutcomeId(List<InspectionHistory> inspectionHistoriesForOldOutcomeId) {
        this.inspectionHistoriesForOldOutcomeId = inspectionHistoriesForOldOutcomeId;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcome")
    public List<InspectionOutcomeFee> getInspectionOutcomeFees() {
        return this.inspectionOutcomeFees;
    }

    public void setInspectionOutcomeFees(List<InspectionOutcomeFee> inspectionOutcomeFees) {
        this.inspectionOutcomeFees = inspectionOutcomeFees;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcome")
    public List<LetterTemplateToInspectionOutcome> getLetterTemplateToInspectionOutcomes() {
        return this.letterTemplateToInspectionOutcomes;
    }

    public void setLetterTemplateToInspectionOutcomes(List<LetterTemplateToInspectionOutcome> letterTemplateToInspectionOutcomes) {
        this.letterTemplateToInspectionOutcomes = letterTemplateToInspectionOutcomes;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcome")
    public List<MasterInspections> getMasterInspectionses() {
        return this.masterInspectionses;
    }

    public void setMasterInspectionses(List<MasterInspections> masterInspectionses) {
        this.masterInspectionses = masterInspectionses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InspectionOutcome)) return false;
        final InspectionOutcome inspectionOutcome = (InspectionOutcome) o;
        return Objects.equals(getId(), inspectionOutcome.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

