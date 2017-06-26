package com.civicxpress;

import com.civicxpress.cx2.Fees;

public class PaymentReceiptFee extends Fees {

    private String municipalityName;
    
    public PaymentReceiptFee() {

    }
    
    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

}