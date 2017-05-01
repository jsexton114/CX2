package com.civicxpress.letters;

import java.util.ArrayList;
import java.util.List;

public class TemplateSectionRow {

	private List<TemplateSection> sections;
	private float leftMarginWidth;
	private float rightMarginWidth;
	
	public TemplateSectionRow() {
		super();
	}
	
	public TemplateSectionRow(float leftMarginWidth, float rightMarginWidth) {
		this.sections = new ArrayList<TemplateSection>();
		this.leftMarginWidth = leftMarginWidth;
		this.rightMarginWidth = rightMarginWidth;
	}

	public float getLeftMarginWidth() {
		return leftMarginWidth;
	}

	public void setLeftMarginWidth(float leftMarginWidth) {
		this.leftMarginWidth = leftMarginWidth;
	}

	public float getRightMarginWidth() {
		return rightMarginWidth;
	}

	public void setRightMarginWidth(float rightMarginWidth) {
		this.rightMarginWidth = rightMarginWidth;
	}

	public List<TemplateSection> getSections() {
		return sections;
	}

	public void setSections(List<TemplateSection> sections) {
		this.sections = sections;
	}
	
}
