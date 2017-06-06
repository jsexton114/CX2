/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class OutcomeByDesignResponse implements Serializable {

    @ColumnAlias("ID")
    private Integer id;
    @ColumnAlias("InspectDesignId")
    private Integer inspectDesignId;
    @ColumnAlias("Outcome")
    private String outcome;
    @ColumnAlias("AssessFeeYN")
    private Boolean assessFeeYn;
    @ColumnAlias("ConsiderClosed")
    private Boolean considerClosed;
    @ColumnAlias("OutcomeOrder")
    private Integer outcomeOrder;
    @ColumnAlias("SendEmail")
    private Boolean sendEmail;
    @ColumnAlias("EmailSubjectLine")
    private String emailSubjectLine;
    @ColumnAlias("EmailBodyText")
    private String emailBodyText;
    @ColumnAlias("AutoReInspection")
    private Boolean autoReInspection;
    @ColumnAlias("DaysToAutoReInspection")
    private Integer daysToAutoReInspection;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInspectDesignId() {
        return this.inspectDesignId;
    }

    public void setInspectDesignId(Integer inspectDesignId) {
        this.inspectDesignId = inspectDesignId;
    }

    public String getOutcome() {
        return this.outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Boolean getAssessFeeYn() {
        return this.assessFeeYn;
    }

    public void setAssessFeeYn(Boolean assessFeeYn) {
        this.assessFeeYn = assessFeeYn;
    }

    public Boolean getConsiderClosed() {
        return this.considerClosed;
    }

    public void setConsiderClosed(Boolean considerClosed) {
        this.considerClosed = considerClosed;
    }

    public Integer getOutcomeOrder() {
        return this.outcomeOrder;
    }

    public void setOutcomeOrder(Integer outcomeOrder) {
        this.outcomeOrder = outcomeOrder;
    }

    public Boolean getSendEmail() {
        return this.sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getEmailSubjectLine() {
        return this.emailSubjectLine;
    }

    public void setEmailSubjectLine(String emailSubjectLine) {
        this.emailSubjectLine = emailSubjectLine;
    }

    public String getEmailBodyText() {
        return this.emailBodyText;
    }

    public void setEmailBodyText(String emailBodyText) {
        this.emailBodyText = emailBodyText;
    }

    public Boolean getAutoReInspection() {
        return this.autoReInspection;
    }

    public void setAutoReInspection(Boolean autoReInspection) {
        this.autoReInspection = autoReInspection;
    }

    public Integer getDaysToAutoReInspection() {
        return this.daysToAutoReInspection;
    }

    public void setDaysToAutoReInspection(Integer daysToAutoReInspection) {
        this.daysToAutoReInspection = daysToAutoReInspection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutcomeByDesignResponse)) return false;
        final OutcomeByDesignResponse outcomeByDesignResponse = (OutcomeByDesignResponse) o;
        return Objects.equals(getId(), outcomeByDesignResponse.getId()) &&
                Objects.equals(getInspectDesignId(), outcomeByDesignResponse.getInspectDesignId()) &&
                Objects.equals(getOutcome(), outcomeByDesignResponse.getOutcome()) &&
                Objects.equals(getAssessFeeYn(), outcomeByDesignResponse.getAssessFeeYn()) &&
                Objects.equals(getConsiderClosed(), outcomeByDesignResponse.getConsiderClosed()) &&
                Objects.equals(getOutcomeOrder(), outcomeByDesignResponse.getOutcomeOrder()) &&
                Objects.equals(getSendEmail(), outcomeByDesignResponse.getSendEmail()) &&
                Objects.equals(getEmailSubjectLine(), outcomeByDesignResponse.getEmailSubjectLine()) &&
                Objects.equals(getEmailBodyText(), outcomeByDesignResponse.getEmailBodyText()) &&
                Objects.equals(getAutoReInspection(), outcomeByDesignResponse.getAutoReInspection()) &&
                Objects.equals(getDaysToAutoReInspection(), outcomeByDesignResponse.getDaysToAutoReInspection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getInspectDesignId(),
                getOutcome(),
                getAssessFeeYn(),
                getConsiderClosed(),
                getOutcomeOrder(),
                getSendEmail(),
                getEmailSubjectLine(),
                getEmailBodyText(),
                getAutoReInspection(),
                getDaysToAutoReInspection());
    }
}