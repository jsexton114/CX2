package com.civicxpress.pdfutilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * Created by jason on 3/15/2017.
 */
public class PdfUtilities    {

    public static String createDynamicFormPdf(String title, Map<String, Object> formData, boolean addSignatureLine) throws IOException {
        final float LINE_HEIGHT = 20;
        final float COLUMN_WIDTH = 275;
        PDDocument document;
        PDPage page;
        PDFont font;
        PDPageContentStream contentStream;

        page = new PDPage();
        document = new PDDocument();
        document.addPage( page );
        contentStream = new PDPageContentStream(document, page);

        File logo = getLogoFile();

        // add an image
        try {
            PDImageXObject ximage = PDImageXObject.createFromFile(logo.getAbsolutePath(), document); // TODO: show  municipality image
            float scale = 0.5f; // alter this value to set the image size
            contentStream.drawImage(ximage, 480, 695, ximage.getWidth()*scale, ximage.getHeight()*scale);
        } catch (IOException ioex) {
            System.out.println("No image for you");
        }

        // add the line
        contentStream.setLineWidth(1);
        contentStream.moveTo(20, 685);
        contentStream.lineTo(580, 685);
        contentStream.closeAndStroke();

        contentStream.beginText();
        contentStream.setFont( PDType1Font.HELVETICA_BOLD, 14 );
        contentStream.newLineAtOffset( 100, 690 );
        contentStream.showText(title);
        contentStream.setFont( PDType1Font.HELVETICA, 12 );
        contentStream.newLineAtOffset(0, -15);

        for (Map.Entry<String, Object> entry : formData.entrySet())
        {
            String formattedEntryKey = "";
            String entryValue = "";
            formattedEntryKey = splitCamelCase(formatTextLength(entry.getKey().trim(), 40));
            if (entry.getValue() != null) entryValue = formatTextLength(entry.getValue().toString(), 30);;
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
    private static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    private static File getLogoFile() throws IOException {
        // HACK: quick fix to get a *^&% image on the WM server
        URL imageURL = new URL("http://www.civicxpress.com/wp-content/uploads/2016/12/CXLogo-200.png");
        BufferedImage originalImage = ImageIO.read(imageURL);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "png", baos );
        File logo = File.createTempFile("logo", ".png");
        FileOutputStream fos = new FileOutputStream(logo);
        baos.writeTo(fos);
        fos.close();
        logo.deleteOnExit();
        System.out.println(logo.getAbsolutePath());
        return logo;
    }

}

