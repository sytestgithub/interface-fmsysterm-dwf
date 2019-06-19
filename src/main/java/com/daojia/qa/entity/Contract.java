package com.daojia.qa.entity;

import java.math.BigDecimal;
import java.util.List;

public class Contract {
	private long id;

	private String stringId;

	private String contractNumber;

	private String contractId;

	private String formId;

	private String contractNature;

	private String contractCategory;

	private String oppositeContractBody;

	private String ourContractBody;

	private String empDepartment;

	private String costCenter;

	private String isFrameContract;

	private String contractName;

	private String costDesc;

	private String ourFirstStamp;

	private String invoiceType;

	private String clearingForm;

	private String contractStartTime;

	private String contractEndTime;

	private BigDecimal contractAmount;

	private BigDecimal noAmount;

	private BigDecimal waitAmount;

	private BigDecimal alreadyAmount;

	private String recipientBank;

	private String shroffAccountNumber;

	private String shroffAccountAddress;

	private String taxpayerId;

	private String taxpayersAddress;

	private String openingBank;

	private String bankAccount;

	private String isClose;

	private String applyDate;

	private int contractSort;

	private int contractStatus;

	private int beforeInvoiceOrMoney;

	private String taxRate;

	private String enQualification;

	private String invoiceProject;

	private List<FormDto> toPublicInfo;

	private List<ContractRecord> ContractRecord;

	private List<ContractDetail> ContractDetail;

	private String apply_p_name;

	private String apply_p_jobnumber;

	private String inner_remark;
	
	private String filesStr;
	
	private String paymentPeriod;
	private String uniformCode;
	private String recipientBankFullName;
	private String recipientBankAccount;
	private BigDecimal depositOtherAmount;
	private String ourReceivingBank;
	private String ourReceivingBankFullName;
	private int contractAmountCanEstimated;
	private BigDecimal grossProfit;

	private String grossProfitMargin;
	private String ourReceivingBankAccount;
	public String getInner_remark() {
		return inner_remark;
	}

	public void setInner_remark(String inner_remark) {
		this.inner_remark = inner_remark;
	}

	public String getApply_p_jobnumber() {
		return apply_p_jobnumber;
	}

	public void setApply_p_jobnumber(String apply_p_jobnumber) {
		this.apply_p_jobnumber = apply_p_jobnumber;
	}

	public List<ContractRecord> getContractRecord() {
		return ContractRecord;
	}

	public void setContractRecord(List<ContractRecord> contractRecord) {
		ContractRecord = contractRecord;
	}

	public List<FormDto> getToPublicInfo() {
		return toPublicInfo;
	}

	public void setToPublicInfo(List<FormDto> toPublicInfo) {
		this.toPublicInfo = toPublicInfo;
	}

	public String getOppositeContractBody() {
		return oppositeContractBody;
	}

	public void setOppositeContractBody(String oppositeContractBody) {
		this.oppositeContractBody = oppositeContractBody;
	}

	public String getApply_p_name() {
		return apply_p_name;
	}

	public void setApply_p_name(String apply_p_name) {
		this.apply_p_name = apply_p_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getContractNature() {
		return contractNature;
	}

	public void setContractNature(String contractNature) {
		this.contractNature = contractNature;
	}

	public String getContractCategory() {
		return contractCategory;
	}

	public void setContractCategory(String contractCategory) {
		this.contractCategory = contractCategory;
	}

	public String getOurContractBody() {
		return ourContractBody;
	}

	public void setOurContractBody(String ourContractBody) {
		this.ourContractBody = ourContractBody;
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

	public String getIsFrameContract() {
		return isFrameContract;
	}

	public void setIsFrameContract(String isFrameContract) {
		this.isFrameContract = isFrameContract;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getCostDesc() {
		return costDesc;
	}

	public void setCostDesc(String costDesc) {
		this.costDesc = costDesc;
	}

	public String getOurFirstStamp() {
		return ourFirstStamp;
	}

	public void setOurFirstStamp(String ourFirstStamp) {
		this.ourFirstStamp = ourFirstStamp;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getClearingForm() {
		return clearingForm;
	}

	public void setClearingForm(String clearingForm) {
		this.clearingForm = clearingForm;
	}

	public String getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(String contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public String getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(String contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public BigDecimal getNoAmount() {
		return noAmount;
	}

	public void setNoAmount(BigDecimal noAmount) {
		this.noAmount = noAmount;
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

	public String getRecipientBank() {
		return recipientBank;
	}

	public void setRecipientBank(String recipientBank) {
		this.recipientBank = recipientBank;
	}

	public String getShroffAccountNumber() {
		return shroffAccountNumber;
	}

	public void setShroffAccountNumber(String shroffAccountNumber) {
		this.shroffAccountNumber = shroffAccountNumber;
	}

	public String getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(String taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public String getTaxpayersAddress() {
		return taxpayersAddress;
	}

	public void setTaxpayersAddress(String taxpayersAddress) {
		this.taxpayersAddress = taxpayersAddress;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public int getContractSort() {
		return contractSort;
	}

	public void setContractSort(int contractSort) {
		this.contractSort = contractSort;
	}

	public int getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getShroffAccountAddress() {
		return shroffAccountAddress;
	}

	public void setShroffAccountAddress(String shroffAccountAddress) {
		this.shroffAccountAddress = shroffAccountAddress;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getInvoiceProject() {
		return invoiceProject;
	}

	public void setInvoiceProject(String invoiceProject) {
		this.invoiceProject = invoiceProject;
	}

	public int getBeforeInvoiceOrMoney() {
		return beforeInvoiceOrMoney;
	}

	public void setBeforeInvoiceOrMoney(int beforeInvoiceOrMoney) {
		this.beforeInvoiceOrMoney = beforeInvoiceOrMoney;
	}

	public String getEnQualification() {
		return enQualification;
	}

	public void setEnQualification(String enQualification) {
		this.enQualification = enQualification;
	}

	public List<ContractDetail> getContractDetail() {
		return ContractDetail;
	}

	public void setContractDetail(List<ContractDetail> contractDetail) {
		ContractDetail = contractDetail;
	}

	public String getFilesStr() {
		return filesStr;
	}

	public void setFilesStr(String filesStr) {
		this.filesStr = filesStr;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", stringId=" + stringId + ", contractNumber=" + contractNumber + ", contractId="
				+ contractId + ", formId=" + formId + ", contractNature=" + contractNature + ", contractCategory="
				+ contractCategory + ", oppositeContractBody=" + oppositeContractBody + ", ourContractBody="
				+ ourContractBody + ", empDepartment=" + empDepartment + ", costCenter=" + costCenter
				+ ", isFrameContract=" + isFrameContract + ", contractName=" + contractName + ", costDesc=" + costDesc
				+ ", ourFirstStamp=" + ourFirstStamp + ", invoiceType=" + invoiceType + ", clearingForm="
				+ clearingForm + ", contractStartTime=" + contractStartTime + ", contractEndTime=" + contractEndTime
				+ ", contractAmount=" + contractAmount + ", noAmount=" + noAmount + ", waitAmount=" + waitAmount
				+ ", alreadyAmount=" + alreadyAmount + ", recipientBank=" + recipientBank + ", shroffAccountNumber="
				+ shroffAccountNumber + ", shroffAccountAddress=" + shroffAccountAddress + ", taxpayerId=" + taxpayerId
				+ ", taxpayersAddress=" + taxpayersAddress + ", openingBank=" + openingBank + ", bankAccount="
				+ bankAccount + ", isClose=" + isClose + ", applyDate=" + applyDate + ", contractSort=" + contractSort
				+ ", contractStatus=" + contractStatus + ", beforeInvoiceOrMoney=" + beforeInvoiceOrMoney
				+ ", taxRate=" + taxRate + ", enQualification=" + enQualification + ", invoiceProject="
				+ invoiceProject + ", toPublicInfo=" + toPublicInfo + ", ContractRecord=" + ContractRecord
				+ ", apply_p_name=" + apply_p_name + ", apply_p_jobnumber=" + apply_p_jobnumber + ", inner_remark="
				+ inner_remark + "]";
	}

	public String getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public String getRecipientBankFullName() {
		return recipientBankFullName;
	}

	public void setRecipientBankFullName(String recipientBankFullName) {
		this.recipientBankFullName = recipientBankFullName;
	}

	public String getUniformCode() {
		return uniformCode;
	}

	public void setUniformCode(String uniformCode) {
		this.uniformCode = uniformCode;
	}

	public String getRecipientBankAccount() {
		return recipientBankAccount;
	}

	public void setRecipientBankAccount(String recipientBankAccount) {
		this.recipientBankAccount = recipientBankAccount;
	}

	public BigDecimal getDepositOtherAmount() {
		return depositOtherAmount;
	}

	public void setDepositOtherAmount(BigDecimal depositOtherAmount) {
		this.depositOtherAmount = depositOtherAmount;
	}

	public String getOurReceivingBank() {
		return ourReceivingBank;
	}

	public void setOurReceivingBank(String ourReceivingBank) {
		this.ourReceivingBank = ourReceivingBank;
	}

	public String getOurReceivingBankFullName() {
		return ourReceivingBankFullName;
	}

	public void setOurReceivingBankFullName(String ourReceivingBankFullName) {
		this.ourReceivingBankFullName = ourReceivingBankFullName;
	}

	public int getContractAmountCanEstimated() {
		return contractAmountCanEstimated;
	}

	public void setContractAmountCanEstimated(int contractAmountCanEstimated) {
		this.contractAmountCanEstimated = contractAmountCanEstimated;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(String grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}

	public String getOurReceivingBankAccount() {
		return ourReceivingBankAccount;
	}

	public void setOurReceivingBankAccount(String ourReceivingBankAccount) {
		this.ourReceivingBankAccount = ourReceivingBankAccount;
	}
}
