/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * LetterTemplateToFormStatus generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`LetterTemplateToFormStatus`")
@IdClass(LetterTemplateToFormStatusId.class)
public class LetterTemplateToFormStatus implements Serializable {

    private Integer id;
    private Integer letterTemplateId;
    private Integer formStatusId;

    @Id
    @Column(name = "`Id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "`LetterTemplateId`", nullable = false, scale = 0, precision = 10)
    public Integer getLetterTemplateId() {
        return this.letterTemplateId;
    }

    public void setLetterTemplateId(Integer letterTemplateId) {
        this.letterTemplateId = letterTemplateId;
    }

    @Id
    @Column(name = "`FormStatusId`", nullable = false, scale = 0, precision = 10)
    public Integer getFormStatusId() {
        return this.formStatusId;
    }

    public void setFormStatusId(Integer formStatusId) {
        this.formStatusId = formStatusId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetterTemplateToFormStatus)) return false;
        final LetterTemplateToFormStatus letterTemplateToFormStatus = (LetterTemplateToFormStatus) o;
        return Objects.equals(getId(), letterTemplateToFormStatus.getId()) &&
                Objects.equals(getLetterTemplateId(), letterTemplateToFormStatus.getLetterTemplateId()) &&
                Objects.equals(getFormStatusId(), letterTemplateToFormStatus.getFormStatusId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getLetterTemplateId(),
                getFormStatusId());
    }
}

