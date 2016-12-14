/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FormFee generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormFee`")
public class FormFee implements Serializable {

    private Integer id;
    private Integer municipalityId;
    private Integer gisid;
    private Integer masterFormsId;
    private String formType;
    private String amount;
    private String feeType;
    private Boolean autoFeeYn;
    private String accountingCode;
    private String paidStatus;
    private Date paidDate;
    private Integer paidByUserId;
    private String transactionId;
    private String comments;
    private String formTitle;
    private Gisrecords gisrecords;
    private Municipalities municipalities;
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`GISID`", nullable = true, scale = 0, precision = 10)
    public Integer getGisid() {
        return this.gisid;
    }

    public void setGisid(Integer gisid) {
        this.gisid = gisid;
    }

    @Column(name = "`MasterFormsId`", nullable = true, scale = 0, precision = 10)
    public Integer getMasterFormsId() {
        return this.masterFormsId;
    }

    public void setMasterFormsId(Integer masterFormsId) {
        this.masterFormsId = masterFormsId;
    }

    @Column(name = "`FormType`", nullable = true, length = 255)
    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Column(name = "`Amount`", nullable = true, length = 255)
    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Column(name = "`FeeType`", nullable = true, length = 255)
    public String getFeeType() {
        return this.feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Column(name = "`AutoFeeYN`", nullable = true)
    public Boolean getAutoFeeYn() {
        return this.autoFeeYn;
    }

    public void setAutoFeeYn(Boolean autoFeeYn) {
        this.autoFeeYn = autoFeeYn;
    }

    @Column(name = "`AccountingCode`", nullable = true, length = 255)
    public String getAccountingCode() {
        return this.accountingCode;
    }

    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    @Column(name = "`PaidStatus`", nullable = true, length = 255)
    public String getPaidStatus() {
        return this.paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "`PaidDate`", nullable = true)
    public Date getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Column(name = "`PaidByUserId`", nullable = true, scale = 0, precision = 10)
    public Integer getPaidByUserId() {
        return this.paidByUserId;
    }

    public void setPaidByUserId(Integer paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    @Column(name = "`TransactionId`", nullable = true, length = 255)
    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "`Comments`", nullable = true, length = 500)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "`FormTitle`", nullable = true, length = 255)
    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`GISID`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Gisrecords getGisrecords() {
        return this.gisrecords;
    }

    public void setGisrecords(Gisrecords gisrecords) {
        if(gisrecords != null) {
            this.gisid = gisrecords.getId();
        }

        this.gisrecords = gisrecords;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`MunicipalityId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Municipalities getMunicipalities() {
        return this.municipalities;
    }

    public void setMunicipalities(Municipalities municipalities) {
        if(municipalities != null) {
            this.municipalityId = municipalities.getId();
        }

        this.municipalities = municipalities;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PaidByUserId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.paidByUserId = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormFee)) return false;
        final FormFee formFee = (FormFee) o;
        return Objects.equals(getId(), formFee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

