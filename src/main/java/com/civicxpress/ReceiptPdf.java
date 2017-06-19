package com.civicxpress;

import com.civicxpress.letters.PdfDocument;
import com.civicxpress.letters.PdfDocument.*;
import java.io.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReceiptPdf {

    public byte[] fileBytesOutput;

    public ReceiptPdf() {
        ByteArrayOutputStream fileStream = null;
        PDType1Font font = PDType1Font.HELVETICA;
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.addPage();
        pdfDocument.addText(36f, 792.0f-36.0f, 612.0f, "Municipality Fees PAID",
                font, 14f, TextJustification.Centered);
        fileStream = new ByteArrayOutputStream();
        pdfDocument.saveToFile(fileStream);
        fileBytesOutput = fileStream.toByteArray();
    }
}