/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CodesToInspection generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`CodesToInspection`")
public class CodesToInspection implements Serializable {

    private Integer id;
    private Integer codeSetId;
    private Integer inspectionDesignId;
    private CodeSets codeSets;
    private InspectionDesign inspectionDesign;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`CodeSetId`", nullable = true, scale = 0, precision = 10)
    public Integer getCodeSetId() {
        return this.codeSetId;
    }

    public void setCodeSetId(Integer codeSetId) {
        this.codeSetId = codeSetId;
    }

    @Column(name = "`InspectionDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionDesignId() {
        return this.inspectionDesignId;
    }

    public void setInspectionDesignId(Integer inspectionDesignId) {
        this.inspectionDesignId = inspectionDesignId;
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
    @JoinColumn(name = "`InspectionDesignId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionDesign getInspectionDesign() {
        return this.inspectionDesign;
    }

    public void setInspectionDesign(InspectionDesign inspectionDesign) {
        if(inspectionDesign != null) {
            this.inspectionDesignId = inspectionDesign.getId();
        }

        this.inspectionDesign = inspectionDesign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodesToInspection)) return false;
        final CodesToInspection codesToInspection = (CodesToInspection) o;
        return Objects.equals(getId(), codesToInspection.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

