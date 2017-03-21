package com.civicxpress.pdfutilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jason on 3/15/2017.
 */
public class PdfUtilities    {

    public static String createDynamicFormPdf(String title, Map<String, Object> formData) throws IOException {
        final float LINE_HEIGHT = 20;
        final float COLUMN_WIDTH = 200;
        PDDocument document;
        PDPage page;
        PDFont font;
        PDPageContentStream contentStream;
        String entryValue;

        page = new PDPage();
        document = new PDDocument();
        document.addPage( page );
        font = PDType1Font.HELVETICA_BOLD;
        contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont( font, 14 );
        contentStream.moveTextPositionByAmount( 100, 700 );
        contentStream.drawString(title);
        font = PDType1Font.HELVETICA;
        contentStream.setFont( font, 12 );

        for (Map.Entry<String, Object> entry : formData.entrySet())
        {
            entryValue = "";
            if (entry.getValue() != null) entryValue = entry.getValue().toString();
            contentStream.moveTextPositionByAmount( 0, -LINE_HEIGHT );
            contentStream.drawString(entry.getKey());
            contentStream.moveTextPositionByAmount( COLUMN_WIDTH, 0);
            contentStream.drawString(entryValue);
            contentStream.moveTextPositionByAmount( -COLUMN_WIDTH, 0);
        }

        contentStream.endText();
        contentStream.close();
        File file = File.createTempFile(title, ".pdf");
        document.save(file);
        file.deleteOnExit();

        return file.getPath();
    }

}