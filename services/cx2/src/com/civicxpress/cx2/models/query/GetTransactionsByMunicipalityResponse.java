/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetTransactionsByMunicipalityResponse implements Serializable {


    @ColumnAlias("transactionId")
    private Integer transactionId;

    @ColumnAlias("feeAmount")
    private BigDecimal feeAmount;

    @ColumnAlias("userId")
    private Integer userId;

    @ColumnAlias("userEmail")
    private String userEmail;

    @ColumnAlias("userName")
    private String userName;

    @ColumnAlias("paymentMethod")
    private String paymentMethod;

    @ColumnAlias("paymentNumber")
    private String paymentNumber;

    @ColumnAlias("totalTransactionAmount")
    private BigDecimal totalTransactionAmount;

    @ColumnAlias("transactionComments")
    private String transactionComments;

    @ColumnAlias("transactionDate")
    private LocalDateTime transactionDate;

    public Integer getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getFeeAmount() {
        return this.feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentNumber() {
        return this.paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public BigDecimal getTotalTransactionAmount() {
        return this.totalTransactionAmount;
    }

    public void setTotalTransactionAmount(BigDecimal totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public String getTransactionComments() {
        return this.transactionComments;
    }

    public void setTransactionComments(String transactionComments) {
        this.transactionComments = transactionComments;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetTransactionsByMunicipalityResponse)) return false;
        final GetTransactionsByMunicipalityResponse getTransactionsByMunicipalityResponse = (GetTransactionsByMunicipalityResponse) o;
        return Objects.equals(getTransactionId(), getTransactionsByMunicipalityResponse.getTransactionId()) &&
                Objects.equals(getFeeAmount(), getTransactionsByMunicipalityResponse.getFeeAmount()) &&
                Objects.equals(getUserId(), getTransactionsByMunicipalityResponse.getUserId()) &&
                Objects.equals(getUserEmail(), getTransactionsByMunicipalityResponse.getUserEmail()) &&
                Objects.equals(getUserName(), getTransactionsByMunicipalityResponse.getUserName()) &&
                Objects.equals(getPaymentMethod(), getTransactionsByMunicipalityResponse.getPaymentMethod()) &&
                Objects.equals(getPaymentNumber(), getTransactionsByMunicipalityResponse.getPaymentNumber()) &&
                Objects.equals(getTotalTransactionAmount(), getTransactionsByMunicipalityResponse.getTotalTransactionAmount()) &&
                Objects.equals(getTransactionComments(), getTransactionsByMunicipalityResponse.getTransactionComments()) &&
                Objects.equals(getTransactionDate(), getTransactionsByMunicipalityResponse.getTransactionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionId(),
                getFeeAmount(),
                getUserId(),
                getUserEmail(),
                getUserName(),
                getPaymentMethod(),
                getPaymentNumber(),
                getTotalTransactionAmount(),
                getTransactionComments(),
                getTransactionDate());
    }
}
