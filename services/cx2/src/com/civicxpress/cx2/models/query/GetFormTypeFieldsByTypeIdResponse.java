/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.commons.json.serializer.ByteArrayToStringSerializer;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetFormTypeFieldsByTypeIdResponse implements Serializable {


    @JsonProperty("defaultValue")
    @ColumnAlias("defaultValue")
    private String defaultValue;

    @JsonProperty("displayOrder")
    @ColumnAlias("displayOrder")
    private Integer displayOrder;

    @JsonProperty("fieldName")
    @ColumnAlias("fieldName")
    private String fieldName;

    @JsonProperty("fieldTypeId")
    @ColumnAlias("fieldTypeId")
    private int fieldTypeId;

    @JsonProperty("formFieldTypes")
    @ColumnAlias("formFieldTypes")
    private FormFieldTypes formFieldTypes;

    @JsonProperty("formTypeId")
    @ColumnAlias("formTypeId")
    private Integer formTypeId;

    @JsonProperty("formTypes")
    @ColumnAlias("formTypes")
    private FormTypes formTypes;

    @JsonProperty("helpText")
    @ColumnAlias("helpText")
    private String helpText;

    @JsonProperty("id")
    @ColumnAlias("id")
    private Integer id;

    @JsonProperty("label")
    @ColumnAlias("label")
    private String label;

    @JsonProperty("possibleValues")
    @ColumnAlias("possibleValues")
    private String possibleValues;

    @JsonProperty("required")
    @ColumnAlias("required")
    private Boolean required;

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getFieldTypeId() {
        return this.fieldTypeId;
    }

    public void setFieldTypeId(int fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public FormFieldTypes getFormFieldTypes() {
        return this.formFieldTypes;
    }

    public void setFormFieldTypes(FormFieldTypes formFieldTypes) {
        this.formFieldTypes = formFieldTypes;
    }

    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    public FormTypes getFormTypes() {
        return this.formTypes;
    }

    public void setFormTypes(FormTypes formTypes) {
        this.formTypes = formTypes;
    }

    public String getHelpText() {
        return this.helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPossibleValues() {
        return this.possibleValues;
    }

    public void setPossibleValues(String possibleValues) {
        this.possibleValues = possibleValues;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetFormTypeFieldsByTypeIdResponse)) return false;
        final GetFormTypeFieldsByTypeIdResponse getFormTypeFieldsByTypeIdResponse = (GetFormTypeFieldsByTypeIdResponse) o;
        return Objects.equals(getDefaultValue(), getFormTypeFieldsByTypeIdResponse.getDefaultValue()) &&
                Objects.equals(getDisplayOrder(), getFormTypeFieldsByTypeIdResponse.getDisplayOrder()) &&
                Objects.equals(getFieldName(), getFormTypeFieldsByTypeIdResponse.getFieldName()) &&
                Objects.equals(getFieldTypeId(), getFormTypeFieldsByTypeIdResponse.getFieldTypeId()) &&
                Objects.equals(getFormFieldTypes(), getFormTypeFieldsByTypeIdResponse.getFormFieldTypes()) &&
                Objects.equals(getFormTypeId(), getFormTypeFieldsByTypeIdResponse.getFormTypeId()) &&
                Objects.equals(getFormTypes(), getFormTypeFieldsByTypeIdResponse.getFormTypes()) &&
                Objects.equals(getHelpText(), getFormTypeFieldsByTypeIdResponse.getHelpText()) &&
                Objects.equals(getId(), getFormTypeFieldsByTypeIdResponse.getId()) &&
                Objects.equals(getLabel(), getFormTypeFieldsByTypeIdResponse.getLabel()) &&
                Objects.equals(getPossibleValues(), getFormTypeFieldsByTypeIdResponse.getPossibleValues()) &&
                Objects.equals(getRequired(), getFormTypeFieldsByTypeIdResponse.getRequired());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDefaultValue(),
                getDisplayOrder(),
                getFieldName(),
                getFieldTypeId(),
                getFormFieldTypes(),
                getFormTypeId(),
                getFormTypes(),
                getHelpText(),
                getId(),
                getLabel(),
                getPossibleValues(),
                getRequired());
    }
}
