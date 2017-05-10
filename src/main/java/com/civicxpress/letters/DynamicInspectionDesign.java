package com.civicxpress.letters;

public class DynamicInspectionDesign {
    private String name;
    private String tableName;
    private Long municipalityId;

    public DynamicInspectionDesign() {

    }

    public DynamicInspectionDesign(String name, String tableName, Long municipalityId) {
        this.name = name;
        this.tableName = tableName;
        this.municipalityId = municipalityId;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}
}
