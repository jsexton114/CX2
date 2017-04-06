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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * CodeList generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`CodeList`")
public class CodeList implements Serializable {

    private Integer codeId;
    private String codeNumber;
    private String codeTitle;
    private String code;
    private boolean activeYn;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime created;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT })
    private Integer createdBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT, Scope.UPDATE })
    @Type(type = "DateTime")
    private LocalDateTime updated;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT, Scope.UPDATE })
    private Integer updatedBy;
    private Integer codeSetId;
    private CodeSets codeSets;
    private Users usersByCreatedBy;
    private Users usersByUpdatedBy;
    private List<Violations> violationses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`CodeId`", nullable = false, scale = 0, precision = 10)
    public Integer getCodeId() {
        return this.codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    @Column(name = "`CodeNumber`", nullable = true, length = 255)
    public String getCodeNumber() {
        return this.codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Column(name = "`CodeTitle`", nullable = true, length = 255)
    public String getCodeTitle() {
        return this.codeTitle;
    }

    public void setCodeTitle(String codeTitle) {
        this.codeTitle = codeTitle;
    }

    @Column(name = "`Code`", nullable = true, length = 5000)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "`ActiveYN`", nullable = false)
    public boolean isActiveYn() {
        return this.activeYn;
    }

    public void setActiveYn(boolean activeYn) {
        this.activeYn = activeYn;
    }

    @Column(name = "`Created`", nullable = true, updatable = false)
    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "`CreatedBy`", nullable = true, updatable = false, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`Updated`", nullable = true)
    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Column(name = "`UpdatedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "`CodeSetId`", nullable = true, scale = 0, precision = 10)
    public Integer getCodeSetId() {
        return this.codeSetId;
    }

    public void setCodeSetId(Integer codeSetId) {
        this.codeSetId = codeSetId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CodeSetId`", referencedColumnName = "`CodeSetId`", insertable = false, updatable = false)
    public CodeSets getCodeSets() {
        return this.codeSets;
    }

    public void setCodeSets(CodeSets codeSets) {
        if(codeSets != null) {
            this.codeSetId = codeSets.getCodeSetId();
        }

        this.codeSets = codeSets;
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
    @JoinColumn(name = "`UpdatedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByUpdatedBy() {
        return this.usersByUpdatedBy;
    }

    public void setUsersByUpdatedBy(Users usersByUpdatedBy) {
        if(usersByUpdatedBy != null) {
            this.updatedBy = usersByUpdatedBy.getId();
        }

        this.usersByUpdatedBy = usersByUpdatedBy;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "codeList")
    public List<Violations> getViolationses() {
        return this.violationses;
    }

    public void setViolationses(List<Violations> violationses) {
        this.violationses = violationses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeList)) return false;
        final CodeList codeList = (CodeList) o;
        return Objects.equals(getCodeId(), codeList.getCodeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeId());
    }
}

