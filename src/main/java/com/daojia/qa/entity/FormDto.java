package com.daojia.qa.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * data used by myForm,formExamineAndApprove,formSelect 
 * 
 * @author little tree
 *
 */
public class FormDto implements Comparable<FormDto>{
	private String id;
	private String formId;
	
	private int formType;
	
	private String applyDate;//填单日期、提交日期
	
	private String applyPName;//申请人姓名
	
	private String empDepartment;
	
	private BigDecimal applyMoney;
	
	private String payStatus;
	
	//private long applyUserId;
	private String formStatus;
	
	private String nextHandlePerson;
	
	private double noAmount;
	
	private String prepOptionUserId;
	
	private String loginUserId;
	
	private String approveTime;
	
	private Date dateTypeApproveTime;
	
	private String payCompany;
	
	private String ourContractBody;

 private String taskId;
	
	private List<ButtonDto> btns;
	
	private String formKey;
	
	private String processId;
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
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

	public String getNextHandlePerson() {
		return nextHandlePerson;
	}

	public void setNextHandlePerson(String nextHandlePerson) {
		this.nextHandlePerson = nextHandlePerson;
	}
	
	public double getNoAmount() {
		return noAmount;
	}

	public void setNoAmount(double noAmount) {
		this.noAmount = noAmount;
	}

	public String getPrepOptionUserId() {
		return prepOptionUserId;
	}

	public void setPrepOptionUserId(String prepOptionUserId) {
		this.prepOptionUserId = prepOptionUserId;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public Date getDateTypeApproveTime() {
		return dateTypeApproveTime;
	}

	public void setDateTypeApproveTime(Date dateTypeApproveTime) {
		this.dateTypeApproveTime = dateTypeApproveTime;
	}

	public int compareTo(FormDto o) {
		return this.dateTypeApproveTime.compareTo(o.dateTypeApproveTime);
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public String getOurContractBody() {
		return ourContractBody;
	}

	public void setOurContractBody(String ourContractBody) {
		this.ourContractBody = ourContractBody;
	}

	@Override
	public String toString() {
		return "FormDto [id=" + id + ", formId=" + formId + ", formType="
				+ formType + ", applyDate=" + applyDate + ", applyPName="
				+ applyPName + ", empDepartment=" + empDepartment
				+ ", applyMoney=" + applyMoney + ", payStatus=" + payStatus
				+ ", formStatus=" + formStatus + ", nextHandlePerson="
				+ nextHandlePerson + ", noAmount=" + noAmount
				+ ", prepOptionUserId=" + prepOptionUserId + ", loginUserId="
				+ loginUserId + ", approveTime=" + approveTime
				+ ", dateTypeApproveTime=" + dateTypeApproveTime
				+ ", payCompany=" + payCompany + ", ourContractBody="
				+ ourContractBody + "]";
	}

}
