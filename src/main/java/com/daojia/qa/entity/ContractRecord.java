package com.daojia.qa.entity;

import java.math.BigDecimal;



public class ContractRecord {
	private long id;

	private String formId;

	private String contractNumber;

	private BigDecimal contractAmount;

	private BigDecimal nowAmount;

    private BigDecimal waitAmount;

	private BigDecimal alreadyAmount;

	private String approveTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public BigDecimal getNowAmount() {
		return nowAmount;
	}

	public void setNowAmount(BigDecimal nowAmount) {
		this.nowAmount = nowAmount;
	}

	public BigDecimal getWaitAmount() {
		return waitAmount;
	}

	public void setWaitAmount(BigDecimal waitAmount) {
		this.waitAmount = waitAmount;
	}

	public BigDecimal getAlreadyAmount() {
		return alreadyAmount;
	}

	public void setAlreadyAmount(BigDecimal alreadyAmount) {
		this.alreadyAmount = alreadyAmount;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	@Override
	public String toString() {
		return "ContractRecordDB [id=" + id + ", formId=" + formId + ", contractNumber=" + contractNumber
				+ ", contractAmount=" + contractAmount + ", nowAmount=" + nowAmount + ", waitAmount=" + waitAmount
				+ ", alreadyAmount=" + alreadyAmount + ", approveTime=" + approveTime + "]";
	}

	
}
