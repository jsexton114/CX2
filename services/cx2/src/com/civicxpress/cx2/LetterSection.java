/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LetterSection generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`LetterSection`")
public class LetterSection implements Serializable {

    private Integer id;
    private int letterTemplateId;
    private double x;
    private double y;
    private double height;
    private double width;
    private Integer sectionIndex;
    private Integer rowIndex;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`Id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`LetterTemplateId`", nullable = false, scale = 0, precision = 10)
    public int getLetterTemplateId() {
        return this.letterTemplateId;
    }

    public void setLetterTemplateId(int letterTemplateId) {
        this.letterTemplateId = letterTemplateId;
    }

    @Column(name = "`X`", nullable = false, scale = 9, precision = 15)
    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column(name = "`Y`", nullable = false, scale = 9, precision = 15)
    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Column(name = "`Height`", nullable = false, scale = 9, precision = 15)
    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Column(name = "`Width`", nullable = false, scale = 9, precision = 15)
    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Column(name = "`SectionIndex`", nullable = true, scale = 0, precision = 10)
    public Integer getSectionIndex() {
        return this.sectionIndex;
    }

    public void setSectionIndex(Integer sectionIndex) {
        this.sectionIndex = sectionIndex;
    }

    @Column(name = "`RowIndex`", nullable = true, scale = 0, precision = 10)
    public Integer getRowIndex() {
        return this.rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetterSection)) return false;
        final LetterSection letterSection = (LetterSection) o;
        return Objects.equals(getId(), letterSection.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

