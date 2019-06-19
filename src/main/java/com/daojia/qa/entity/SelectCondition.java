package com.daojia.qa.entity;

public class SelectCondition {
private int page;
private int pageSize;
private String formId;
private String formPerson;
private String formStatus;
private String payStatus;
private String startDate;
private String endDate;
private String formType;
private String userId;
private String userName;
private String oppositeContractBody;//对方签约主体
private String fillPerson;//填单人
private String contractNature;
private String sortByOptionTime;
private String payCompany;
private String applyFormId;

private String stampName;//印证名称
private String stampType;//印证类型
private String stampTypeName;//印证类型
private String useState;//状态

private String businessCategory;//业务分类
private String invoiceStatus;//开票状态
private String invoiceStartDate;//开票日期
private String invoiceEndDate;
private String invoiceType;//发票类型
public String getFormPerson() {return formPerson;}
public void setFormPerson(String formPerson) {this.formPerson = formPerson;}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public String getFormId() {
	return formId;
}
public void setFormId(String formId) {
	this.formId = formId;
}
public String getFormStatus() {
	return formStatus;
}
public void setFormStatus(String formStatus) {
	this.formStatus = formStatus;
}
public String getPayStatus() {
	return payStatus;
}
public void setPayStatus(String payStatus) {
	this.payStatus = payStatus;
}

public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}

public String getFormType() {
	return formType;
}
public void setFormType(String formType) {
	this.formType = formType;
}

public String getFillPerson() {
	return fillPerson;
}
public void setFillPerson(String fillPerson) {
	this.fillPerson = fillPerson;
}

public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getOppositeContractBody() {
	return oppositeContractBody;
}
public void setOppositeContractBody(String oppositeContractBody) {
	this.oppositeContractBody = oppositeContractBody;
}

public String getContractNature() {
	return contractNature;
}
public void setContractNature(String contractNature) {
	this.contractNature = contractNature;
}

public String getSortByOptionTime() {
	return sortByOptionTime;
}
public void setSortByOptionTime(String sortByOptionTime) {
	this.sortByOptionTime = sortByOptionTime;
}

public String getPayCompany() {
	return payCompany;
}
public void setPayCompany(String payCompany) {
	this.payCompany = payCompany;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getApplyFormId() {
	return applyFormId;
}
public void setApplyFormId(String applyFormId) {
	this.applyFormId = applyFormId;
}
public String getStampName() {
	return stampName;
}
public void setStampName(String stampName) {
	this.stampName = stampName;
}
public String getStampType() {
	return stampType;
}
public void setStampType(String stampType) {
	this.stampType = stampType;
}
public String getUseState() {
	return useState;
}
public void setUseState(String useState) {
	this.useState = useState;
}
public String getStampTypeName() {
	return stampTypeName;
}
public void setStampTypeName(String stampTypeName) {
	this.stampTypeName = stampTypeName;
}
public String getBusinessCategory() {
	return businessCategory;
}
public void setBusinessCategory(String businessCategory) {
	this.businessCategory = businessCategory;
}
public String getInvoiceStatus() {
	return invoiceStatus;
}
public void setInvoiceStatus(String invoiceStatus) {
	this.invoiceStatus = invoiceStatus;
}
public String getInvoiceStartDate() {
	return invoiceStartDate;
}
public void setInvoiceStartDate(String invoiceStartDate) {
	this.invoiceStartDate = invoiceStartDate;
}
public String getInvoiceEndDate() {
	return invoiceEndDate;
}
public void setInvoiceEndDate(String invoiceEndDate) {
	this.invoiceEndDate = invoiceEndDate;
}
@Override
public String toString() {
	return "SelectCondition [page=" + page + ", pageSize=" + pageSize + ", formId=" + formId + ", formPerson="
			+ formPerson + ", formStatus=" + formStatus + ", payStatus=" + payStatus + ", startDate=" + startDate
			+ ", endDate=" + endDate + ", formType=" + formType + ", userId=" + userId + ", userName=" + userName
			+ ", oppositeContractBody=" + oppositeContractBody + ", fillPerson=" + fillPerson + ", contractNature="
			+ contractNature + ", sortByOptionTime=" + sortByOptionTime + ", payCompany=" + payCompany
			+ ", applyFormId=" + applyFormId + ", stampName=" + stampName + ", stampType=" + stampType
			+ ", stampTypeName=" + stampTypeName + ", useState=" + useState + ", businessCategory=" + businessCategory
			+ ", invoiceStatus=" + invoiceStatus + ", invoiceStartDate=" + invoiceStartDate + ", invoiceEndDate="
			+ invoiceEndDate + "]";
}
public String getInvoiceType() {
	return invoiceType;
}
public void setInvoiceType(String invoiceType) {
	this.invoiceType = invoiceType;
}

}
