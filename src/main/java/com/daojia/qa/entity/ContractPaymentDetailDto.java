package com.daojia.qa.entity;


public class ContractPaymentDetailDto {

	private String stringId;
	private String costTime;
	private String paymentMoney;
	private Boolean selectThis;
	private String taxes;

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}



	public String getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(String paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public Boolean getSelectThis() {
		return selectThis;
	}

	public void setSelectThis(Boolean selectThis) {
		this.selectThis = selectThis;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

}
