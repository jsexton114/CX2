package com.civicxpress;

import com.civicxpress.cx2.Fees;
import com.civicxpress.PaymentReceipt;
import com.civicxpress.letters.PdfDocument;
import com.civicxpress.letters.PdfDocument.*;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReceiptPdf {

    public byte[] fileBytesOutput;

    public ReceiptPdf() {
        
    }
    
    public ReceiptPdf(PaymentReceipt paymentReceipt) {
        ByteArrayOutputStream fileStream = null;
        PDType1Font font = PDType1Font.HELVETICA;
        PdfDocument pdfDocument = new PdfDocument();
        Float rightMargin = 36.0f;
        Float documentWidth = 500.0f;
        Float halfDocumentWidth = 250.0f;
        Float lineHeight = 20.0f;
        Float fontSizeNormal = 12.0f;
        Float fontSizeSmaller = 10.0f;
        Float fontSizeLarger = 14.0f;
        Float yIndex = 792.0f - rightMargin;
        BigDecimal lineItemTotal = BigDecimal.ZERO;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        pdfDocument.addPage();
        pdfDocument.addText(rightMargin, yIndex, documentWidth, "Municipality Fees PAID", font, fontSizeLarger, TextJustification.Centered);
        yIndex -= lineHeight;
        pdfDocument.addText(rightMargin, yIndex, documentWidth, paymentReceipt.getPaymentMethod(), font, fontSizeNormal, TextJustification.Centered);
        yIndex -= lineHeight;
        pdfDocument.addText(rightMargin, yIndex, documentWidth, "Amount received: " + currencyFormatter.format(paymentReceipt.getAmountReceived()), font, fontSizeNormal, TextJustification.Centered);
        yIndex -= lineHeight;
        pdfDocument.addText(rightMargin, yIndex, documentWidth, paymentReceipt.getDateCreated().toString("MM-dd-yyyy"), font, fontSizeNormal, TextJustification.Centered);
        yIndex -= lineHeight * 2;
        pdfDocument.addText(rightMargin, yIndex, documentWidth, paymentReceipt.getUserFullName(), font, fontSizeNormal, TextJustification.Centered);
        yIndex -= lineHeight;
        pdfDocument.addText(rightMargin, yIndex, documentWidth, paymentReceipt.getUserEmail(), font, fontSizeNormal, TextJustification.Centered);
        yIndex -= lineHeight;
        pdfDocument.addText(rightMargin, yIndex, documentWidth, "Payment # " + paymentReceipt.getPaymentNumber(), font, fontSizeSmaller, TextJustification.Centered);
        yIndex -= lineHeight * 2;
        for (Fees f : paymentReceipt.getFees()) {
            yIndex -= lineHeight;
            lineItemTotal = lineItemTotal.add(f.getAmount());
            pdfDocument.addText(rightMargin * 2, yIndex, halfDocumentWidth - rightMargin, f.getFeeType(), font, fontSizeNormal, TextJustification.Left);
            pdfDocument.addText(rightMargin + halfDocumentWidth, yIndex, halfDocumentWidth, currencyFormatter.format(f.getAmount()), font, fontSizeNormal, TextJustification.Left);
        };
        yIndex -= lineHeight / 2;
        pdfDocument.addText(rightMargin + halfDocumentWidth, yIndex, halfDocumentWidth, "----------", font, fontSizeNormal, TextJustification.Left);
        yIndex -= lineHeight / 2;
        pdfDocument.addText(rightMargin * 2, yIndex, halfDocumentWidth - rightMargin, "Total", font, fontSizeNormal, TextJustification.Left);
        pdfDocument.addText(rightMargin + halfDocumentWidth, yIndex, halfDocumentWidth, currencyFormatter.format(lineItemTotal), font, fontSizeNormal, TextJustification.Left);
        fileStream = new ByteArrayOutputStream();
        pdfDocument.saveToFile(fileStream);
        fileBytesOutput = fileStream.toByteArray();
    }
}

