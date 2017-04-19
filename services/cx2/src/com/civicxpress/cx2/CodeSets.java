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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * CodeSets generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`CodeSets`")
public class CodeSets implements Serializable {

    private Integer id;
    private String codeSetTitle;
    private boolean globalYn;
    private Integer municipalityId;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime created;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT })
    private Integer createdBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.UPDATE, Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime updated;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.UPDATE, Scope.INSERT })
    private Integer updatedBy;
    private Municipalities municipalities;
    private Users usersByCreatedBy;
    private Users usersByUpdatedBy;
    private List<Code> codes;
    private List<CodesToForm> codesToForms;
    private List<CodesToInspection> codesToInspections;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`CodeSetTitle`", nullable = true, length = 255)
    public String getCodeSetTitle() {
        return this.codeSetTitle;
    }

    public void setCodeSetTitle(String codeSetTitle) {
        this.codeSetTitle = codeSetTitle;
    }

    @Column(name = "`GlobalYN`", nullable = false)
    public boolean isGlobalYn() {
        return this.globalYn;
    }

    public void setGlobalYn(boolean globalYn) {
        this.globalYn = globalYn;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "codeSets")
    public List<Code> getCodes() {
        return this.codes;
    }

    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "codeSets")
    public List<CodesToForm> getCodesToForms() {
        return this.codesToForms;
    }

    public void setCodesToForms(List<CodesToForm> codesToForms) {
        this.codesToForms = codesToForms;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "codeSets")
    public List<CodesToInspection> getCodesToInspections() {
        return this.codesToInspections;
    }

    public void setCodesToInspections(List<CodesToInspection> codesToInspections) {
        this.codesToInspections = codesToInspections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeSets)) return false;
        final CodeSets codeSets = (CodeSets) o;
        return Objects.equals(getId(), codeSets.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

