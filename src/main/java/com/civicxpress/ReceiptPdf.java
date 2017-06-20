package com.civicxpress;

import com.civicxpress.PaymentReceipt;
import com.civicxpress.letters.PdfDocument;
import com.civicxpress.letters.PdfDocument.*;
import java.io.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReceiptPdf {

    public byte[] fileBytesOutput;

    public ReceiptPdf() {
        
    }
    
    public ReceiptPdf(PaymentReceipt paymentReceipt) {
        ByteArrayOutputStream fileStream = null;
        PDType1Font font = PDType1Font.HELVETICA;
        PdfDocument pdfDocument = new PdfDocument();
        Float yIndex = 792.0f - 36.0f;
        pdfDocument.addPage();
        pdfDocument.addText(36f, yIndex, 612.0f, "Municipality Fees PAID", font, 14f, TextJustification.Centered);
        yIndex -= 20f;
        pdfDocument.addText(36f, yIndex, 612.0f, paymentReceipt.getPaymentMethod(), font, 14f, TextJustification.Centered);
        yIndex -= 20f;
        pdfDocument.addText(36f, yIndex, 612.0f, paymentReceipt.getPaymentNumber(), font, 14f, TextJustification.Centered);
        fileStream = new ByteArrayOutputStream();
        pdfDocument.saveToFile(fileStream);
        fileBytesOutput = fileStream.toByteArray();
    }
}