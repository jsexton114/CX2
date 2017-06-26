package com.civicxpress;

import com.civicxpress.cx2.Fees;
import com.civicxpress.PaymentReceiptFee;
import com.civicxpress.cx2.PaymentHistory;
import org.joda.time.LocalDateTime;

public class PaymentReceipt {

    private PaymentHistory paymentHistory = null;
    private String userFullName = null;
    private String userEmail = null;
    //private Fees[] fees = null;
    private PaymentReceiptFee[] paymentReceiptFees = null;

    public PaymentReceipt() {
        this.paymentHistory = new PaymentHistory();
        //this.fees = new Fees[0];
        this.paymentReceiptFees = new PaymentReceiptFee[0];
    }
    
    public Integer getTransactionId() {
        return this.paymentHistory.getTransactionId();
    }

    public void setTransactionId(Integer transactionId) {
        this.paymentHistory.setTransactionId(transactionId);
    }

    public String getPaymentMethod() {
        return this.paymentHistory.getPaymentMethod();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentHistory.setPaymentMethod(paymentMethod);
    }

    public String getPaymentNumber() {
        return this.paymentHistory.getPaymentNumber();
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentHistory.setPaymentNumber(paymentNumber);
    }
    
    public Float getAmountReceived() {
        return this.paymentHistory.getAmountReceived();
    }

    public void setAmountReceived(Float amountReceived) {
        this.paymentHistory.setAmountReceived(amountReceived);
    }

    public String getComments() {
        return this.paymentHistory.getComments();
    }

    public void setComments(String comments) {
        this.paymentHistory.setComments(comments);
    }

    public LocalDateTime getDateCreated() {
        return this.paymentHistory.getDateCreated();
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.paymentHistory.setDateCreated(dateCreated);
    }

    public String getUserFullName() {
        return this.userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

/*
    public Fees[] getFees() {
        return this.fees;
    }

    public void setFees(Fees[] fees) {
        this.fees = fees;
    }
 */
 
    public PaymentReceiptFee[] getPaymentReceiptFees() {
        return this.paymentReceiptFees;        
    }
    
    public void setPaymentReceiptFees(PaymentReceiptFee[] paymentReceiptFees) {
        this.paymentReceiptFees = paymentReceiptFees;
    }
}

