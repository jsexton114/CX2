package com.civicxpress.letters;

import java.util.Map;

public class DynamicInspection {
    private DynamicInspectionDesign dynamicInspectionType;
    private String inspectionTitle;
    private String creatorFullName;
    private Map<String, Object> formDataValues;

    public DynamicInspection() {

    }

    public DynamicInspection(DynamicInspectionDesign dynamicInspectionType, String name, String creatorFullName) {
        this.dynamicInspectionType = dynamicInspectionType;
        this.inspectionTitle = name;
        this.creatorFullName = creatorFullName;
    }

	public DynamicInspectionDesign getDynamicInspectionType() {
		return dynamicInspectionType;
	}

	public void setDynamicInspectionType(DynamicInspectionDesign dynamicInspectionType) {
		this.dynamicInspectionType = dynamicInspectionType;
	}

	public String getInspectionTitle() {
		return inspectionTitle;
	}

	public void setInspectionTitle(String inspectionTitle) {
		this.inspectionTitle = inspectionTitle;
	}

	public String getCreatorFullName() {
		return creatorFullName;
	}

	public void setCreatorFullName(String creatorFullName) {
		this.creatorFullName = creatorFullName;
	}

	public Map<String, Object> getFormDataValues() {
		return formDataValues;
	}

	public void setFormDataValues(Map<String, Object> formDataValues) {
		this.formDataValues = formDataValues;
	}
}
