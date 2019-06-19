package com.daojia.qa.entity;


/**
 * Created by daojia on 2018-3-23.
 */
public class InvoiceApplyDetail {
    private Long id;
    private String contractNum;
    private String oppositeContractBody;
    private int isFrameContract;
    private String uniformCode;
    private String invoiceAddress;
    private String invoiceTel;
    private String recipientBankFullName;
    private String recipientBankAccount;
    private String invoiceDateStart;
    private String invoiceDateEnd;
    private String receiptName;
    private String receiptAddress;
    private String receiptTel;
    private String formId;
    private int sort;
    private String invoiceHolder;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOppositeContractBody() {
        return oppositeContractBody;
    }

    public void setOppositeContractBody(String oppositeContractBody) {
        this.oppositeContractBody = oppositeContractBody;
    }

    public int getIsFrameContract() {
        return isFrameContract;
    }

    public void setIsFrameContract(int isFrameContract) {
        this.isFrameContract = isFrameContract;
    }

    public String getUniformCode() {
        return uniformCode;
    }

    public void setUniformCode(String uniformCode) {
        this.uniformCode = uniformCode;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoiceTel() {
        return invoiceTel;
    }

    public void setInvoiceTel(String invoiceTel) {
        this.invoiceTel = invoiceTel;
    }

    public String getRecipientBankFullName() {
        return recipientBankFullName;
    }

    public void setRecipientBankFullName(String recipientBankFullName) {
        this.recipientBankFullName = recipientBankFullName;
    }

    public String getRecipientBankAccount() {
        return recipientBankAccount;
    }

    public void setRecipientBankAccount(String recipientBankAccount) {
        this.recipientBankAccount = recipientBankAccount;
    }

    public String getInvoiceDateStart() {
        return invoiceDateStart;
    }

    public void setInvoiceDateStart(String invoiceDateStart) {
        this.invoiceDateStart = invoiceDateStart;
    }

    public String getInvoiceDateEnd() {
        return invoiceDateEnd;
    }

    public void setInvoiceDateEnd(String invoiceDateEnd) {
        this.invoiceDateEnd = invoiceDateEnd;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptTel() {
        return receiptTel;
    }

    public void setReceiptTel(String receiptTel) {
        this.receiptTel = receiptTel;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getInvoiceHolder() {
        return invoiceHolder;
    }

    public void setInvoiceHolder(String invoiceHolder) {
        this.invoiceHolder = invoiceHolder;
    }

    @Override
    public String toString() {
        return "InvoiceApplyDetail{" +
                "id=" + id +
                ", contractNum='" + contractNum + '\'' +
                ", oppositeContractBody='" + oppositeContractBody + '\'' +
                ", isFrameContract=" + isFrameContract +
                ", uniformCode='" + uniformCode + '\'' +
                ", invoiceAddress='" + invoiceAddress + '\'' +
                ", invoiceTel='" + invoiceTel + '\'' +
                ", recipientBankFullName='" + recipientBankFullName + '\'' +
                ", recipientBankAccount='" + recipientBankAccount + '\'' +
                ", invoiceDateStart='" + invoiceDateStart + '\'' +
                ", invoiceDateEnd='" + invoiceDateEnd + '\'' +
                ", receiptName='" + receiptName + '\'' +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", receiptTel='" + receiptTel + '\'' +
                ", formId='" + formId + '\'' +
                '}';
    }
}
