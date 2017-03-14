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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * CaseStatuses generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`CaseStatuses`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`CaseTypeId`", "`SortNumber`"})})
public class CaseStatuses implements Serializable {

    private Integer id;
    private String status;
    private String description;
    private Integer caseTypeId;
    private Integer sortNumber;
    private Boolean sendEmail;
    private String emailSubject;
    private String emailBody;
    private CaseTypes caseTypes;
    private List<MasterCases> masterCaseses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`Status`", nullable = true, length = 255)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "`Description`", nullable = true, length = 1000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "`CaseTypeId`", nullable = true, scale = 0, precision = 10)
    public Integer getCaseTypeId() {
        return this.caseTypeId;
    }

    public void setCaseTypeId(Integer caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    @Column(name = "`SortNumber`", nullable = true, scale = 0, precision = 10)
    public Integer getSortNumber() {
        return this.sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    @Column(name = "`SendEmail`", nullable = true)
    public Boolean getSendEmail() {
        return this.sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Column(name = "`EmailSubject`", nullable = true, length = 255)
    public String getEmailSubject() {
        return this.emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    @Column(name = "`EmailBody`", nullable = true, length = 2147483647)
    public String getEmailBody() {
        return this.emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CaseTypeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public CaseTypes getCaseTypes() {
        return this.caseTypes;
    }

    public void setCaseTypes(CaseTypes caseTypes) {
        if(caseTypes != null) {
            this.caseTypeId = caseTypes.getId();
        }

        this.caseTypes = caseTypes;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "caseStatuses")
    public List<MasterCases> getMasterCaseses() {
        return this.masterCaseses;
    }

    public void setMasterCaseses(List<MasterCases> masterCaseses) {
        this.masterCaseses = masterCaseses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaseStatuses)) return false;
        final CaseStatuses caseStatuses = (CaseStatuses) o;
        return Objects.equals(getId(), caseStatuses.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

