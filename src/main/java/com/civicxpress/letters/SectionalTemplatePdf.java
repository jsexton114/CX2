package com.civicxpress.letters;

import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.civicxpress.letters.PdfDocument.*;

public class SectionalTemplatePdf {
    private static final String[] GLOBAL_TOKENS = {"FormTitle", "MunicipalityName", "MunicipalityLogo", "MunicipalityAddress", "ContractorName", "ContractorAddress",
            "Location", "LocationMap", "Fees", "AmountDue", "Owner", "Tenant",
            "Violations", "ExpiresDate", "IssuedDate"};
    private static final String TOKEN_PATTERN = "\\[(.*?)\\]";
    private static final int COMPLETE_TOKEN_GROUP_INDEX = 0;
    private static final int TOKEN_NAME_GROUP_INDEX = 1;

    // defaults
    private static final float letterHeight = 792.0f;
    private static final float letterWidth = 612.0f;
    private static final float topMarginHeight = 36.0f;
    private static final float headerYPosition = letterHeight - topMarginHeight;
    private static final float headerFooterMarginWidth = 16.0f;
    private static final float bodyMarginWidth = 36.0f;
    private static final float headerHeight = 90.0f;
    private static final float headerFooterSectionsWidth = 193.3f;
    private static final float rowHeight = 78.6f;
    private static final float numberOfBodyRows = 7;
    private static final float footerHeight = 80.0f;
    private static final float footerYPosition = headerYPosition - headerHeight - (rowHeight * numberOfBodyRows);
    private static final float fullRowWidth = letterWidth - (bodyMarginWidth * 2);
    private static final float oneThirdRowWidth = fullRowWidth / 3;
    private static final float twoThirdsRowWidth = fullRowWidth / 3 * 2;

    private PdfDocument pdfDocument;
    private List<TemplateSectionRow> allRows;
    private int letterTemplateId;
    private String title = null;
    private byte[] municipalityLogo;

    public SectionalTemplatePdf() {
//        PDRectangle.LETTER height: 792.0
//        PDRectangle.LETTER width: 612.0
//        margins are 36.0, except header and footer left and right are 16.0
        pdfDocument = new PdfDocument();
        pdfDocument.addPage();
		this.allRows = new ArrayList<TemplateSectionRow>();
        
    }
    
	public int getId() {
		return letterTemplateId;
	}

	public void setId(int id) {
		letterTemplateId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getMunicipalityLogo() {
		return municipalityLogo;
	}

	public void setMunicipalityLogo(byte[] municipalityLogo) {
		this.municipalityLogo = municipalityLogo;
	}

	public void setUpDefaultSections() {
		float yPosition = headerYPosition;
		setUpHeaderRow();
		setUpFullRow(1, yPosition -= rowHeight);
		setUpOneThirdRow(2, yPosition -= rowHeight);
		setUpTwoThirdsRow(3, yPosition -= rowHeight);
		setUpFullRow(4, yPosition -= rowHeight);
		setUpOneThirdRow(5, yPosition -= rowHeight);
		setUpTwoThirdsRow(6, yPosition -= rowHeight);
		setUpFullRow(7, yPosition -= rowHeight);
		setUpFooterRow();
		
	}

	private void setUpHeaderRow() {
		TemplateSectionRow header = new TemplateSectionRow(headerFooterMarginWidth, headerFooterMarginWidth);
		header.getSections().add(new TemplateSection(headerFooterMarginWidth, headerYPosition, headerHeight, headerFooterSectionsWidth));
		header.getSections().add(new TemplateSection(headerFooterMarginWidth + headerFooterSectionsWidth, headerYPosition, headerHeight, headerFooterSectionsWidth));
		header.getSections().add(new TemplateSection(headerFooterMarginWidth + (headerFooterSectionsWidth * 2), headerYPosition, headerHeight, headerFooterSectionsWidth));
		this.allRows.add(header);
	}

	private void setUpTwoThirdsRow(int rowIndex, float yPosition) {
		this.allRows.add(new TemplateSectionRow(bodyMarginWidth, bodyMarginWidth));
		this.allRows.get(rowIndex).getSections().add(new TemplateSection(bodyMarginWidth, yPosition, rowHeight, twoThirdsRowWidth));
		this.allRows.get(rowIndex).getSections().add(new TemplateSection(bodyMarginWidth + twoThirdsRowWidth, yPosition, rowHeight, oneThirdRowWidth));
	}

	private void setUpOneThirdRow(int rowIndex, float yPosition) {
		this.allRows.add(new TemplateSectionRow(bodyMarginWidth, bodyMarginWidth));
		this.allRows.get(rowIndex).getSections().add(new TemplateSection(bodyMarginWidth, yPosition, rowHeight, oneThirdRowWidth));
		this.allRows.get(rowIndex).getSections().add(new TemplateSection(bodyMarginWidth + oneThirdRowWidth, yPosition, rowHeight, twoThirdsRowWidth));
	}

	private void setUpFullRow(int rowIndex, float yPosition) {
		this.allRows.add(new TemplateSectionRow(bodyMarginWidth, bodyMarginWidth));
		this.allRows.get(rowIndex).getSections().add(new TemplateSection(bodyMarginWidth, yPosition, rowHeight, fullRowWidth));
	}

	private void setUpFooterRow() {
		TemplateSectionRow footer = new TemplateSectionRow(headerFooterMarginWidth, headerFooterMarginWidth);
		footer.getSections().add(new TemplateSection(headerFooterMarginWidth, footerYPosition, footerHeight, headerFooterSectionsWidth));
		footer.getSections().add(new TemplateSection(headerFooterMarginWidth + headerFooterSectionsWidth, footerYPosition, footerHeight, headerFooterSectionsWidth));
		footer.getSections().add(new TemplateSection(headerFooterMarginWidth + (headerFooterSectionsWidth * 2), footerYPosition, footerHeight, headerFooterSectionsWidth));
		this.allRows.add(footer);
	}

    public byte[] createLetter(GlobalFormInfo globalFormInfo, Map<String, String> tokens) {
        byte[] fileBytesOutput = null;
        this.setMunicipalityLogo(globalFormInfo.getMunicipalityLogo());
        this.generatePdfOutput(tokens);
        ByteArrayOutputStream fileStream = new ByteArrayOutputStream();
        this.saveToFile(fileStream);
        fileBytesOutput = fileStream.toByteArray();
        return fileBytesOutput;
    }

	public List<TemplateSectionRow> getAllRows() {
		return allRows;
	}

    public void generatePdfOutput() {
        Map<String, String> emptyTokens = new HashMap<String, String>();
        generatePdfOutput(emptyTokens);
    }

    public void generatePdfOutput(Map<String, String> tokens) {
    	for (TemplateSectionRow row : this.allRows) {
            writeSectionOutput(row.getSections(), tokens);
    	}
    }

	private void writeSectionOutput(List<TemplateSection> sections, Map<String, String> tokens) {
		for (TemplateSection section : sections) {
    		writeSectionOutput(section, tokens);
    	}
	}

	private void writeSectionOutput(TemplateSection section, Map<String, String> tokens) {
        float x = section.getX();
        float y = section.getY();
        float width = section.getWidth();
        LetterElementSettings textSettings = section.getTextSettings();
        int textFontSize = textSettings.getFontSize();
        float textLineHeight = textSettings.getLineHeight();
        TextJustification textJustification = textSettings.getJustification();
        byte[] image = null;;
        int imageWidth;
        boolean hasImage;
        float textOffset = 0;
 
        if (section.getText() == null) return;
       
	    // HACK: if there is an image token, get the bytes from the database and setImage()
	    if (section.getText().indexOf("[MunicipalityLogo]") >= 0) {
	        section.setText("");
	        section.setImage(municipalityLogo);
	    }
	    
        image = section.getImage();
        imageWidth = 120;
        hasImage = (image != null);
        textOffset = 0;
        String text = applyTextTokens(section.getText(), tokens);
        PDType1Font font = PDType1Font.HELVETICA;
        if (textSettings.isBold()) font = PDType1Font.HELVETICA_BOLD;
        pdfDocument.addImage(x, y, imageWidth, image);
        if (hasImage) textOffset += imageWidth;
        pdfDocument.addTextWithWrapping(x + textOffset, y, width, text,
                font, textFontSize, textJustification, textLineHeight);
       
//        System.out.println(String.format("%f, %f, %f, %f, %d, %f, '%s', %b, '%s'", x, y, section.getHeight(), width, textFontSize, textLineHeight, textJustification, textSettings.isBold(), text));
    }

    public void saveToFile(String filePath) {
        pdfDocument.saveToFile(filePath);
    }

    public void saveToFile(OutputStream fileStream) {
        pdfDocument.saveToFile(fileStream);
    }
    
    private static Address applyTokens(Address templateAddress, Map<String, String> tokens) {
        Address outputAddress = new Address();
        outputAddress.setName(applyTextTokens(templateAddress.getName(), tokens));
        outputAddress.setAddressLine1(applyTextTokens(templateAddress.getAddressLine1(), tokens));
        outputAddress.setAddressLine2(applyTextTokens(templateAddress.getAddressLine2(), tokens));
        outputAddress.setCity(applyTextTokens(templateAddress.getCity(), tokens));
        outputAddress.setState(applyTextTokens(templateAddress.getState(), tokens));
        outputAddress.setPostalCode(applyTextTokens(templateAddress.getPostalCode(), tokens));
        outputAddress.setCountry(applyTextTokens(templateAddress.getCountry(), tokens));
        return outputAddress;
    }

    private static String applyTextTokens(String templateText, Map<String, String> tokens) {
        Pattern p;
        Matcher m;
        String output = templateText;
        if (templateText == null) return null;
        p = Pattern.compile(TOKEN_PATTERN);
        m = p.matcher(templateText);
        while (m.find()) {
            String completeToken = m.group(COMPLETE_TOKEN_GROUP_INDEX);
            String tokenName = m.group(TOKEN_NAME_GROUP_INDEX);
            String valueToApply = tokens.get(tokenName);
            if (valueToApply != null) {
	                output = output.replace(completeToken, valueToApply);
            }
        }
        return output;
    }
}
