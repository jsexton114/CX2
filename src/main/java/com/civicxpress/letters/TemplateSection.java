package com.civicxpress.letters;

import javax.xml.soap.Text;

import static com.civicxpress.letters.PdfDocument.*;

public class TemplateSection {

	private int sectionId;
	private byte[] image;
    private String text;
    private float x;
    private float y;
    private float height;
    private float width;
    private LetterElementSettings textSettings;
	
	public TemplateSection() {
		super();
	}

    public TemplateSection(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.textSettings = new LetterElementSettings();
    }

    public int getId() {
		return sectionId;
	}

	public void setId(int sectionId) {
		this.sectionId = sectionId;
	}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setText(String text, TextJustification justification) {
        this.setText(text);;
        this.setJustification(justification);
    }

    public void setText(String text, TextJustification justification, boolean isBold) {
        this.setText(text, justification);
        this.setIsBold(isBold);
    }

    public float getX() { return x; }

    public void setX(float x) { this.x = x; }

    public float getY() { return y; }

    public void setY(float y) { this.y = y; }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public LetterElementSettings getTextSettings() {
        return textSettings;
    }

    public void setTitleSettings(LetterElementSettings textSettings) {
        this.textSettings = textSettings;
    }

    public void setJustification(TextJustification justification) {
        this.getTextSettings().setJustification(justification);
    }

    public void setIsBold(boolean isBold) {
        this.getTextSettings().setIsBold(isBold);
    }

}

