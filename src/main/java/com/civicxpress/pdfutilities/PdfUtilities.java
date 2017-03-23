package com.civicxpress.pdfutilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.civicxpress.formservice.FormService.FormDataPojo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

/**
 * Created by jason on 3/15/2017.
 */
public class PdfUtilities {
	
	private final static float LINE_HEIGHT = 20;
    private final static float COLUMN_WIDTH = 275;
	
	private static void addTextCentered(PDPageContentStream contentStream, PDPage page, String text, PDType1Font font, float fontSize) throws IOException {
		float textWidth = (font.getStringWidth(text) / 1000.0f) * fontSize;
        
        float textOffset = (page.getMediaBox().getWidth() - textWidth) / 2;
        contentStream.newLineAtOffset(textOffset, -LINE_HEIGHT);
        contentStream.setFont( font, fontSize );
        contentStream.showText(text);
        contentStream.newLineAtOffset(-textOffset, 0);
	}

    public static String createDynamicFormPdf(FormDataPojo formDataPojo, String title, Map<String, Object> formData, byte[] municipalityLogo, boolean addSignatureLine) throws IOException {
        PDDocument document;
        PDPage page;
        PDPageContentStream contentStream;

        page = new PDPage();
        document = new PDDocument();
        document.addPage( page );
        contentStream = new PDPageContentStream(document, page);

        ByteArrayInputStream logoBais = new ByteArrayInputStream(municipalityLogo);
        BufferedImage municipalityLogoImage = ImageIO.read(logoBais);

        // add an image
        try {
            PDImageXObject ximage = LosslessFactory.createFromImage(document, municipalityLogoImage);
            int width = 160;
            float aspectRatio = ((float) ximage.getHeight()/(float) ximage.getWidth());
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

        contentStream.beginText();
        contentStream.newLineAtOffset( 0, 700 );
        // Form Type
        addTextCentered(contentStream, page, title, PDType1Font.HELVETICA_BOLD, 14f);
        // Form Title
        addTextCentered(contentStream, page, formDataPojo.getFormTitle(), PDType1Font.HELVETICA, 10f);
        // Creator
        addTextCentered(contentStream, page, ("Created By: " + formDataPojo.getCreatorFullName()), PDType1Font.HELVETICA, 10f);
        
        // Add dynamic form fields
        contentStream.setFont( PDType1Font.HELVETICA, 12 );
        contentStream.newLineAtOffset(100, -15);

        for (Map.Entry<String, Object> entry : formData.entrySet())
        {
            String formattedEntryKey = entry.getKey() != null ? entry.getKey().trim() : "";
            String entryValue = "";
            if (entry.getValue() != null) entryValue = formatTextLength(entry.getValue().toString(), 30);
            contentStream.setNonStrokingColor(Color.gray);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset( 0, -LINE_HEIGHT );
            contentStream.showText(formattedEntryKey + ":");
            contentStream.setNonStrokingColor(Color.black);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset( COLUMN_WIDTH, 0);
            contentStream.showText(entryValue);
            contentStream.newLineAtOffset( -COLUMN_WIDTH, 0);
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

    private static void addESignGenieSignature(float LINE_HEIGHT, float COLUMN_WIDTH, PDPageContentStream contentStream) throws IOException {
        contentStream.setNonStrokingColor(Color.black);
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset( 0, -LINE_HEIGHT * 2);
        contentStream.showText("Signature  ");
        contentStream.setNonStrokingColor(Color.white);
        contentStream.newLineAtOffset( COLUMN_WIDTH, 0);
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

    // "How do I convert CamelCase into human-readable names in Java?", polygenelubricants, answered Apr 1 '10 at 11:35, retrieved 3/21/2017
    // http://stackoverflow.com/questions/2559759/how-do-i-convert-camelcase-into-human-readable-names-in-java
//    private static String splitCamelCase(String s) {
//        return s.replaceAll(
//                String.format("%s|%s|%s",
//                        "(?<=[A-Z])(?=[A-Z][a-z])",
//                        "(?<=[^A-Z])(?=[A-Z])",
//                        "(?<=[A-Za-z])(?=[^A-Za-z])"
//                ),
//                " "
//        );
//    }
}
