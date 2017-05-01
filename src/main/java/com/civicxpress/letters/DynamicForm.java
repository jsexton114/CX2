package com.civicxpress.letters;

import java.util.Map;

public class DynamicForm {
    private DynamicFormType dynamicFormType;
    private String formTitle;
    private String creatorFullName;
    private Map<String, Object> formDataValues;

    public DynamicForm() {

    }

    public DynamicForm(DynamicFormType dynamicFormType, String formTitle, String creatorFullName) {
        this.dynamicFormType = dynamicFormType;
        this.formTitle = formTitle;
        this.creatorFullName = creatorFullName;
    }

    public DynamicFormType getDynamicFormType() {
        return this.dynamicFormType;
    }

    public void setDynamicFormType(DynamicFormType dynamicFormType) {
        this.dynamicFormType = dynamicFormType;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getCreatorFullName() {
        return this.creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    public Map<String, Object> getFormDataValues() {
        return this.formDataValues;
    }

    public void setFormDataValues(Map<String, Object> formDataValues) {
        this.formDataValues = formDataValues;
    }

}
