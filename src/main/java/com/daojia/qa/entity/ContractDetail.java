package com.daojia.qa.entity;

import java.math.BigDecimal;


public class ContractDetail {
	private long id;

	private String stringId;

	private String contractDate;
	
	private String contractNumber;

	private String costCenter;

	private String costDetailType;

	private BigDecimal budgetMoney;

	private BigDecimal noAmount;

	private BigDecimal occupyAmount;

	private BigDecimal usedAmount;
	
	private String sort;
	
	private String formId;

	private int isRelationBudget;
	private int budgetStatus;
	private String payeeName;
	private String recipientBank;
	private String recipientBankFullName;
	private String recipientBankAccount;
	private String taxUnitPrice;
	private int num;
	private String status;
	private BigDecimal taxAmount;
	private String costCenterName;
	private String contractDetailId;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostDetailType() {
		return costDetailType;
	}

	public void setCostDetailType(String costDetailType) {
		this.costDetailType = costDetailType;
	}

	public BigDecimal getBudgetMoney() {
		return budgetMoney;
	}

	public void setBudgetMoney(BigDecimal budgetMoney) {
		this.budgetMoney = budgetMoney;
	}

	public BigDecimal getNoAmount() {
		return noAmount;
	}

	public void setNoAmount(BigDecimal noAmount) {
		this.noAmount = noAmount;
	}

	public BigDecimal getOccupyAmount() {
		return occupyAmount;
	}

	public void setOccupyAmount(BigDecimal occupyAmount) {
		this.occupyAmount = occupyAmount;
	}

	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	@Override
	public String toString() {
		return "ContractDetail [id=" + id + ", stringId=" + stringId
				+ ", contractDate=" + contractDate + ", contractNumber="
				+ contractNumber + ", costCenter=" + costCenter
				+ ", costDetailType=" + costDetailType + ", budgetMoney="
				+ budgetMoney + ", noAmount=" + noAmount + ", occupyAmount="
				+ occupyAmount + ", usedAmount=" + usedAmount + ", sort="
				+ sort + ", formId=" + formId + "]";
	}

	public int getIsRelationBudget() {
		return isRelationBudget;
	}

	public void setIsRelationBudget(int isRelationBudget) {
		this.isRelationBudget = isRelationBudget;
	}

	public int getBudgetStatus() {
		return budgetStatus;
	}

	public void setBudgetStatus(int budgetStatus) {
		this.budgetStatus = budgetStatus;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getRecipientBank() {
		return recipientBank;
	}

	public void setRecipientBank(String recipientBank) {
		this.recipientBank = recipientBank;
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

	public String getTaxUnitPrice() {
		return taxUnitPrice;
	}

	public void setTaxUnitPrice(String taxUnitPrice) {
		this.taxUnitPrice = taxUnitPrice;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(String contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

}
