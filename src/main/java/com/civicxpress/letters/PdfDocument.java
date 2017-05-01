package com.civicxpress.letters;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils; // HACK 2
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;

public class PdfDocument {
    private final static float DEFAULT_LINE_HEIGHT = 16;
    private PDDocument document = null;
    private PDPage currentPage = null;
    private PDPageContentStream contentStream = null;
    public enum TextJustification {
        Left,
        Centered,
        Right
    }

    public PdfDocument() {
        this.document = new PDDocument();
    }

    public static TextJustification textJustificationFromString(String justification) {
    	TextJustification result = null;
    	switch (justification) {
	    	case "Left":
	    		result = TextJustification.Left;
	    		break;
	    	case "Centered":
	    		result = TextJustification.Centered;
	    		break;
	    	case "Right":
	    		result = TextJustification.Right;
	    		break;
    	}
    	return result;
    }
    
    public int addPage() {
        int newPageIndex;
        PDRectangle rectangle;
        this.currentPage = new PDPage(PDRectangle.LETTER);
        newPageIndex = this.getNumberOfPages();
        this.document.addPage(currentPage);
        rectangle = currentPage.getMediaBox();
        System.out.println("PDRectangle.LETTER height: " + rectangle.getHeight());
        System.out.println("PDRectangle.LETTER width: " + rectangle.getWidth());
        try {
            if (contentStream != null) {
                contentStream.close();
            }
            contentStream = new PDPageContentStream(document, currentPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newPageIndex;
    }

    // NOTE: if I have a getPage() I need my own PdfPage to avoid exposing PDFBox
    // Alternatively, I could use a setCurrentPage(int index) and subsequent operations are performed on the current page.
    // -> Or not even provide the ability to set the current page, it's just whatever page we are on now. <-

    public void addTextWithWrapping(float x, float y, float sectionWidth, String text, PDType1Font font, float fontSize,
                                    TextJustification justification, float lineHeight) {
            final String UNIX_LINE_SEPARATOR = "\n";
            final String lineSeparator = UNIX_LINE_SEPARATOR;
            // wavemaker doesn't like horizontal whitespace escape \h so using " \t"
            final String LINE_BREAK_PATTERN = ".*?(([ \t-]+)|(" + lineSeparator + "))";
        float currentLineY = y;
        List<String> words = new ArrayList<String>();
        Pattern pattern = Pattern.compile(LINE_BREAK_PATTERN);
        Matcher matcher;
        String testLine = null;
        String nextLine = null;
        if (text == null) return;
        matcher = pattern.matcher(text + " ");
        while (matcher.find()) {
            float textWidth;
            boolean doesLineEndWithSeparator = false;
            String word;
            word = matcher.group();
            nextLine = StringUtils.join(words, "");
            testLine = nextLine + word;
            doesLineEndWithSeparator = nextLine.endsWith(lineSeparator);
            nextLine = nextLine.replaceAll(lineSeparator, "");
            testLine = testLine.replaceAll(lineSeparator, "");
            textWidth = getTextWidth(testLine, font, fontSize);
            if (textWidth > sectionWidth || doesLineEndWithSeparator) {
                addText(x, currentLineY, sectionWidth, nextLine, font, fontSize, justification);
                words.clear();
                currentLineY -= lineHeight;
            }
            words.add(word);
        }
        testLine = StringUtils.join(words, "");
        addText(x, currentLineY, sectionWidth, testLine, font, fontSize, justification);
    }

    public void addMultipleLinesText(float x, float y, float width, List<String> textLines,
                                     PDType1Font font, float fontSize, TextJustification justification, float lineHeight) {
        float currentLineY = y;
        if (textLines == null) return;
        for (String line: textLines) {
            addText(x, currentLineY, width, line, font, fontSize, justification);
            currentLineY -= lineHeight;
        }
    }

    public void addText(float x, float y, float width, String text, PDType1Font font, float fontSize, TextJustification justification) {
        float textOffset = 0;
        float textWidth;
        if (text == null) return;
        textWidth = getTextWidth(text, font, fontSize);
        switch (justification) {
            case Left:
                break;
            case Centered:
                textOffset = (width - textWidth) / 2;
                break;
            case Right:
                textOffset = width - textWidth;
                break;
        }
        addTextAtOffset(x, y, text, font, fontSize, textOffset);
    }

    private float getTextWidth(String text, PDType1Font font, float fontSize) {
        float textWidth = 0;
        try {
            // HACK: remove /r/n from text before measuring width
            text = text.replaceAll("\r\n", " ");
            // TODO: what is 1000.0f?
            textWidth = (font.getStringWidth(text) / 1000.0f) * fontSize;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textWidth;
    }

    private void addTextAtOffset(float x, float y, String text, PDType1Font font, float fontSize, float textOffset) {
        if (text == null) return;
        try {
            boolean hasLineSeparators = false;
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(x + textOffset, y);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfPages() {
        int numberOfPages;
        numberOfPages = this.document.getNumberOfPages();
        return numberOfPages;
    }

    public void saveToFile(String filePath) {
        try {
            // HACK: hacky to assume stream needs to be closed, and how to insert image instead
            contentStream.close();
            document.save(filePath);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(OutputStream fileStream) {
        try {
            // HACK: hacky to assume stream needs to be closed, and how to insert image instead
            contentStream.close();
            document.save(fileStream);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addImage(float x, float y, int width, byte[] image) {
        ByteArrayInputStream imageStream;
        BufferedImage bufferedImage;
        if (image == null) return;
        imageStream = new ByteArrayInputStream(image);
        try {
            bufferedImage = ImageIO.read(imageStream);
            // add an image
            PDImageXObject xImage = LosslessFactory.createFromImage(document, bufferedImage);
            float aspectRatio = ((float) xImage.getHeight() / (float) xImage.getWidth());
            int height = (int) (aspectRatio * width);
            contentStream.drawImage(xImage, x, y - height, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
