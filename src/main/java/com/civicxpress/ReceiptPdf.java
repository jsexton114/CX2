package com.civicxpress;

import com.civicxpress.cx2.Fees;
import com.civicxpress.PaymentReceipt;
import com.civicxpress.letters.PdfDocument;
import com.civicxpress.letters.PdfDocument.*;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
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
        pdfDocument.addText(36f, yIndex, 500.0f, "Municipality Fees PAID", font, 14f, TextJustification.Centered);
        yIndex -= 20f;
        pdfDocument.addText(36f, yIndex, 500.0f, paymentReceipt.getPaymentMethod(), font, 12f, TextJustification.Centered);
        yIndex -= 20f;
        pdfDocument.addText(36f, yIndex, 500.0f, "Payment # " + paymentReceipt.getPaymentNumber(), font, 12f, TextJustification.Centered);
        yIndex -= 40f;
        pdfDocument.addText(36f, yIndex, 500.0f, "$" + paymentReceipt.getAmountReceived().toString(), font, 14f, TextJustification.Centered);
        yIndex -= 20f;
        pdfDocument.addText(36f, yIndex, 500.0f, paymentReceipt.getDateCreated().toString(), font, 12f, TextJustification.Centered);
        yIndex -= 40f;
        pdfDocument.addText(36f, yIndex, 500.0f, paymentReceipt.getUserFullName(), font, 12f, TextJustification.Centered);
        yIndex -= 20f;
        pdfDocument.addText(36f, yIndex, 500.0f, paymentReceipt.getUserEmail(), font, 12f, TextJustification.Centered);
        yIndex -= 20f;
        for (Fees f : paymentReceipt.getFees()) {
            yIndex -= 20f;
            pdfDocument.addText(36f, yIndex, 250.0f, f.getFeeType(), font, 12f, TextJustification.Left);
            pdfDocument.addText(36f + 250f, yIndex, 250.0f, "$" + f.getAmount().toString(), font, 12f, TextJustification.Left);
        };
        fileStream = new ByteArrayOutputStream();
        pdfDocument.saveToFile(fileStream);
        fileBytesOutput = fileStream.toByteArray();
    }
}