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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * StandardViews generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`StandardViews`")
public class StandardViews implements Serializable {

    private Integer id;
    private String viewName;
    private String viewDescription;
    private List<UserViewPreferences> userViewPreferenceses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`ViewName`", nullable = true, length = 255)
    public String getViewName() {
        return this.viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Column(name = "`ViewDescription`", nullable = true, length = 255)
    public String getViewDescription() {
        return this.viewDescription;
    }

    public void setViewDescription(String viewDescription) {
        this.viewDescription = viewDescription;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "standardViews")
    public List<UserViewPreferences> getUserViewPreferenceses() {
        return this.userViewPreferenceses;
    }

    public void setUserViewPreferenceses(List<UserViewPreferences> userViewPreferenceses) {
        this.userViewPreferenceses = userViewPreferenceses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardViews)) return false;
        final StandardViews standardViews = (StandardViews) o;
        return Objects.equals(getId(), standardViews.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

