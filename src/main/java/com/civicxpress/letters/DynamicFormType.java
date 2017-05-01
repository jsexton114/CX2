package com.civicxpress.letters;

public class DynamicFormType {
    private String formType;
    private String formTableName;
    private Long municipalityId;

    public DynamicFormType() {

    }

    public DynamicFormType(String formType, String formTableName, Long municipalityId) {
        this.formType = formType;
        this.formTableName = formTableName;
        this.municipalityId = municipalityId;
    }

    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormTableName() {
        return this.formTableName;
    }

    public void setFormTableName(String formTableName) {
        this.formTableName = formTableName;
    }

    public Long getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
    }
}
