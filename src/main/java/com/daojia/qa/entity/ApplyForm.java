package com.daojia.qa.entity;

import java.math.BigDecimal;


public class ApplyForm {
	private long id;
	
	private String formId;
	
	private int formType;
	
	private String applyDate;
	
	private String applyPName;
	
	private String applyPJobnumber;
	
	private String empDepartment;
	
	private String costCenter;
	
	private String empPTel;
	
	private String payCompany;
	
	private BigDecimal applyMoney;
	
	private String borrowCause;
	
	private String payeeName;
	
	private String receiveBankName;
	
	private String receiveBankAccount;
	
	private BigDecimal approveMoney;
	
	private String payStatus;
	
	private int applyUserId;
	
	private String formStatus;
	
	private int moneyType;
	
	private int state;
	
	private String fillOrSubmitTime;


	public String getFillOrSubmitTime() {
		return fillOrSubmitTime;
	}

	public void setFillOrSubmitTime(String fillOrSubmitTime) {
		this.fillOrSubmitTime = fillOrSubmitTime;
	}

	public Long getId() {
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

	public int getFormType() {
		return formType;
	}

	public void setFormType(int formType) {
		this.formType = formType;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyPName() {
		return applyPName;
	}

	public void setApplyPName(String applyPName) {
		this.applyPName = applyPName;
	}

	public String getApplyPJobnumber() {
		return applyPJobnumber;
	}

	public void setApplyPJobnumber(String applyPJobnumber) {
		this.applyPJobnumber = applyPJobnumber;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getEmpPTel() {
		return empPTel;
	}

	public void setEmpPTel(String empPTel) {
		this.empPTel = empPTel;
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getBorrowCause() {
		return borrowCause;
	}

	public void setBorrowCause(String borrowCause) {
		this.borrowCause = borrowCause;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getReceiveBankName() {
		return receiveBankName;
	}

	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}

	public String getReceiveBankAccount() {
		return receiveBankAccount;
	}

	public void setReceiveBankAccount(String receiveBankAccount) {
		this.receiveBankAccount = receiveBankAccount;
	}

	public BigDecimal getApproveMoney() {
		return approveMoney;
	}

	public void setApproveMoney(BigDecimal approveMoney) {
		this.approveMoney = approveMoney;
	}


	public int getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(int applyUserId) {
		this.applyUserId = applyUserId;
	}

	

	public int getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	@Override
	public String toString() {
		return "ApplyForm [id=" + id + ", formId=" + formId + ", formType="
				+ formType + ", applyDate=" + applyDate + ", applyPName="
				+ applyPName + ", applyPJobnumber=" + applyPJobnumber
				+ ", empDepartment=" + empDepartment + ", costCenter="
				+ costCenter + ", empPTel=" + empPTel + ", payCompany="
				+ payCompany + ", applyMoney=" + applyMoney + ", borrowCause="
				+ borrowCause + ", payeeName=" + payeeName
				+ ", receiveBankName=" + receiveBankName
				+ ", receiveBankAccount=" + receiveBankAccount
				+ ", approveMoney=" + approveMoney + ", payStatus=" + payStatus
				+ ", applyUserId=" + applyUserId + ", formStatus=" + formStatus
				+ ", moneyType=" + moneyType + ", state=" + state + "]";
	}


}
