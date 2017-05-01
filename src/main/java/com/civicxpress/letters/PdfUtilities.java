package com.civicxpress.letters;

import com.civicxpress.letters.DynamicForm;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;

// NOTE: This class is obsolete. Use PdfDocument instead, which manages the document, page, and content stream as member variables.

public class PdfUtilities {

    private final static float LINE_HEIGHT = 20;
    private final static float COLUMN_WIDTH = 275;

    private static void addTextCentered(PDPageContentStream contentStream, PDPage page, String text, PDType1Font font, float fontSize) throws IOException {
        float textWidth = (font.getStringWidth(text) / 1000.0f) * fontSize;

        float textOffset = (page.getMediaBox().getWidth() - textWidth) / 2;
        contentStream.newLineAtOffset(textOffset, -LINE_HEIGHT);
        contentStream.setFont(font, fontSize);
        contentStream.showText(text);
        contentStream.newLineAtOffset(-textOffset, 0);
    }

    public static String createDynamicFormPdf(DynamicForm dynamicForm, String title, Map<String, Object> formData, byte[] municipalityLogo, boolean addSignatureLine) throws IOException {
        PDDocument document;
        PDPage page;
        PDPageContentStream contentStream;

        page = new PDPage();
        document = new PDDocument();
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);

        addImageToContentStream(municipalityLogo, document, page, contentStream);

        contentStream.beginText();
        contentStream.newLineAtOffset(0, 700);
        // Form Type
        addTextCentered(contentStream, page, title, PDType1Font.HELVETICA_BOLD, 14f);
        // Form Title
        addTextCentered(contentStream, page, dynamicForm.getFormTitle(), PDType1Font.HELVETICA, 10f);
        // Creator
        addTextCentered(contentStream, page, ("Created By: " + dynamicForm.getCreatorFullName()), PDType1Font.HELVETICA, 10f);

        // Add dynamic form fields
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(100, -15);

        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            String formattedEntryKey = entry.getKey() != null ? entry.getKey().trim() : "";
            String entryValue = "";
            if (entry.getValue() != null) entryValue = formatTextLength(entry.getValue().toString(), 30);
            contentStream.setNonStrokingColor(Color.gray);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(0, -LINE_HEIGHT);
            contentStream.showText(formattedEntryKey + ":");
            contentStream.setNonStrokingColor(Color.black);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
            contentStream.showText(entryValue);
            contentStream.newLineAtOffset(-COLUMN_WIDTH, 0);
        }

        if (addSignatureLine) {
            // HACK: This class shouldn't know about eSign Genie syntax
            // TODO: isolate the eSign Genie syntax or change the createDynamicFormPdf arguments to accomdate the needs
            addESignGenieSignature(LINE_HEIGHT, COLUMN_WIDTH, contentStream);
        }

        contentStream.endText();
        contentStream.close();
        File file = File.createTempFile(title, ".pdf");
        document.save(file);
        file.deleteOnExit();

        return file.getPath();
    }

    private static void addImageToContentStream(byte[] municipalityLogo, PDDocument document, PDPage page, PDPageContentStream contentStream) throws IOException {
        ByteArrayInputStream logoBais = new ByteArrayInputStream(municipalityLogo);
        BufferedImage municipalityLogoImage = ImageIO.read(logoBais);

        // add an image
        try {
            PDImageXObject ximage = LosslessFactory.createFromImage(document, municipalityLogoImage);
            int width = 160;
            float aspectRatio = ((float) ximage.getHeight() / (float) ximage.getWidth());
            int height = (int) (aspectRatio * width);
            contentStream.drawImage(ximage, ((page.getMediaBox().getWidth() - width) / 2), 695, width, height);
        } catch (IOException ioex) {
            System.out.println("No image for you");
        }

        // Dividing line
        contentStream.setLineWidth(1);
        contentStream.moveTo(20, 630);
        contentStream.lineTo(580, 630);
        contentStream.closeAndStroke();
    }

    public static String createSimpleHtmlPdf(String title, String html) {
        PDDocument document;
        PDPage page;
        PDRectangle pageSize;
        PDPageContentStream contentStream;
        String outputPath = null;
        byte[] outputBytes = null;

        page = new PDPage();
        pageSize = page.getCropBox();
        document = new PDDocument();
        document.addPage(page);
        try {
            File file;
            contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.newLineAtOffset(80, 700);
            parseHtmlToContentStream(html, contentStream);
            contentStream.endText();
            contentStream.close();
            file = File.createTempFile(title, ".pdf");
            document.save(file);
            //file.deleteOnExit();
            outputPath = file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputPath;
    }

    private static void parseHtmlToContentStream(String html, PDPageContentStream contentStream) throws IOException {
        PDType1Font previousFont = null;
        PDType1Font currentFont = PDType1Font.HELVETICA;
        float previousSize = 12;
        float currentSize = 12;

        Document doc = Jsoup.parse(html);
        Elements bodyElements = doc.getElementsByTag("body");
        Element body = bodyElements.get(0);
        for (Node childNode: body.childNodes()) {
            boolean isNewLineBreak = false;
            boolean isNewParagraph = false;
            boolean isBold = false;
            boolean isItalic = false;
            boolean isUnderline = false;

            previousFont = currentFont;
            previousSize = currentSize;
            if (childNode instanceof Element) {
                Element childElement = (Element)childNode;
                // TODO: clean and refactor how this works, maybe with an array or an Element type
                if (childElement.tagName() == "br") {
                    isNewLineBreak = true;
                    contentStream.newLineAtOffset(0, -15);
                }
                if (childElement.tagName() == "h1") {
                    isNewParagraph = true;
                    contentStream.newLineAtOffset(0, -15);
                    currentSize = 16;
                    currentFont = PDType1Font.HELVETICA_BOLD;
                }
                if (childElement.tagName() == "h2") {
                    isNewParagraph = true;
                    contentStream.newLineAtOffset(0, -15);
                    currentSize = 14;
                    currentFont = PDType1Font.HELVETICA;
                }
                if (childElement.tagName() == "h3") {
                    isNewParagraph = true;
                    contentStream.newLineAtOffset(0, -15);
                    currentSize = 12;
                    currentFont = PDType1Font.HELVETICA_OBLIQUE;
                }
                if (childElement.tagName() == "p") {
                    isNewParagraph = true;
                    contentStream.newLineAtOffset(0, -15);
                }
                if (childElement.tagName() == "b") {
                    isBold = true;
                    currentFont = PDType1Font.HELVETICA_BOLD;
                }
                if (childElement.tagName() == "i") {
                    isItalic = true;
                    currentFont = PDType1Font.HELVETICA_OBLIQUE;
                }
                if (isBold && isItalic) {
                    currentFont = PDType1Font.HELVETICA_BOLD_OBLIQUE;
                }
                if (childElement.tagName() == "u") {
                    isUnderline = true;
                    //PDAnnotationTextMarkup markup = new PDAnnotationTextMarkup(PDAnnotationTextMarkup.SUB_TYPE_UNDERLINE);
                }
                contentStream.setFont(currentFont, currentSize);
                parseHtmlToContentStream(childElement.html(), contentStream);
            } else if (childNode instanceof TextNode){
                TextNode childTextNode = (TextNode)childNode;
                String text = null;
                try {
                    text = childTextNode.text();
                    contentStream.showText(text);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            currentFont = previousFont;
            currentSize = previousSize;
            contentStream.setFont(currentFont, currentSize);

            if (isNewLineBreak) {
                contentStream.newLineAtOffset(0, -6);
            }
            if (isNewParagraph) {
                contentStream.newLineAtOffset(0, -15);
            }
            if (isUnderline) {
                //contentStream.lineTo(100, 0);
            }
        }
    }

    // probably not useful to me...
    // "How to Insert a Linefeed with PDFBox drawString", Lukas, answered Mar 4 '13 at 16:13, retrieved 4/11/2017
    // http://stackoverflow.com/questions/7598099/how-to-insert-a-linefeed-with-pdfbox-drawstring
    private void printMultipleLines(
            PDType1Font font,
            int fontSize,
            PDPageContentStream contentStream, List<String> lines,
            float x,
            float y) throws IOException {
        if (lines.size() == 0) {
            return;
        }
        final int numberOfLines = lines.size();
        final float fontHeight = getFontHeight(font, fontSize);

        contentStream.beginText();
        contentStream.appendRawCommands(fontHeight + " TL\n");
        contentStream.moveTextPositionByAmount( x, y);
        for (int i = 0; i < numberOfLines; i++) {
            contentStream.drawString(lines.get(i));
            if (i < numberOfLines - 1) {
                contentStream.appendRawCommands("T*\n");
            }
        }
        contentStream.endText();
    }

    private float getFontHeight(PDType1Font font, int fontSize) {
        float fontHeight;
        fontHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
        return fontHeight;
    }
    private static void addESignGenieSignature(float LINE_HEIGHT, float COLUMN_WIDTH, PDPageContentStream contentStream) throws IOException {
        contentStream.setNonStrokingColor(Color.black);
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(0, -LINE_HEIGHT * 2);
        contentStream.showText("Signature  ");
        contentStream.setNonStrokingColor(Color.white);
        contentStream.newLineAtOffset(COLUMN_WIDTH, 0);
        contentStream.showText("${s:1:______________}");
    }

    private static String formatTextLength(String text, int maximumLength) {
        final String ELLIPSES = "...";
        String formattedText = null;
        if (text.length() > maximumLength) {
            formattedText = text.substring(0, maximumLength - ELLIPSES.length()) + ELLIPSES;
        } else {
            formattedText = text;
        }
        return formattedText;
    }
}
