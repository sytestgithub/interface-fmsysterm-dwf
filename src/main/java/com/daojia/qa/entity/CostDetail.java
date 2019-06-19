package com.daojia.qa.entity;

public class CostDetail {
		private long id;
		
		private String stringId;
		
		private String costFormId;
		
		private String costHappenDate;
		
		private String startDate;
		
		private String endDate;
		
		private int travelDays;
		
		private String costDetailType;
		
		private String travelCity;
		
		private String budgetStatus;
		
		private String invoiceType;
		
		private String taxRate;
		
		private String taxMoney;
		
		private String startCity;
		
		private String endCity;
		
		private String stayStandard;
		
		//private BigDecimal amount;
		private String amount;
		
		private String approveMoney;
		
		private String department;
		
		private String costCenter;
		
		private String budgetMonth ;
		
		private String trafficTool;
		
		private String costdetailDiv;
		
		private int sort;

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

		public String getCostFormId() {
			return costFormId;
		}

		public void setCostFormId(String costFormId) {
			this.costFormId = costFormId;
		}

		public String getCostHappenDate() {
			return costHappenDate;
		}

		public void setCostHappenDate(String costHappenDate) {
			this.costHappenDate = costHappenDate;
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

		public int getTravelDays() {
			return travelDays;
		}

		public void setTravelDays(int travelDays) {
			this.travelDays = travelDays;
		}

		public String getCostDetailType() {
			return costDetailType;
		}

		public void setCostDetailType(String costDetailType) {
			this.costDetailType = costDetailType;
		}

		public String getTravelCity() {
			return travelCity;
		}

		public void setTravelCity(String travelCity) {
			this.travelCity = travelCity;
		}

		public String getBudgetStatus() {
			return budgetStatus;
		}

		public void setBudgetStatus(String budgetStatus) {
			this.budgetStatus = budgetStatus;
		}

		public String getInvoiceType() {
			return invoiceType;
		}

		public void setInvoiceType(String invoiceType) {
			this.invoiceType = invoiceType;
		}

		public String getTaxRate() {
			return taxRate;
		}

		public void setTaxRate(String taxRate) {
			this.taxRate = taxRate;
		}

		public String getTaxMoney() {
			return taxMoney;
		}

		public void setTaxMoney(String taxMoney) {
			this.taxMoney = taxMoney;
		}

		public String getStartCity() {
			return startCity;
		}

		public void setStartCity(String startCity) {
			this.startCity = startCity;
		}

		public String getEndCity() {
			return endCity;
		}

		public void setEndCity(String endCity) {
			this.endCity = endCity;
		}

		public String getStayStandard() {
			return stayStandard;
		}

		public void setStayStandard(String stayStandard) {
			this.stayStandard = stayStandard;
		}

		public String getAmount() {
			return amount;
		}

		public String getApproveMoney() {
			return approveMoney;
		}

		public void setApproveMoney(String approveMoney) {
			this.approveMoney = approveMoney;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getTrafficTool() {
			return trafficTool;
		}

		public void setTrafficTool(String trafficTool) {
			this.trafficTool = trafficTool;
		}
		

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getCostCenter() {
			return costCenter;
		}

		public void setCostCenter(String costCenter) {
			this.costCenter = costCenter;
		}

		public String getBudgetMonth() {
			return budgetMonth;
		}

		public void setBudgetMonth(String budgetMonth) {
			this.budgetMonth = budgetMonth;
		}

		public String getCostdetailDiv() {
			return costdetailDiv;
		}

		public void setCostdetailDiv(String costdetailDiv) {
			this.costdetailDiv = costdetailDiv;
		}

		public int getSort() {
			return sort;
		}

		public void setSort(int sort) {
			this.sort = sort;
		}

		@Override
		public String toString() {
			return "CostDetail [id=" + id + ", stringId=" + stringId
					+ ", costFormId=" + costFormId + ", costHappenDate="
					+ costHappenDate + ", startDate=" + startDate
					+ ", endDate=" + endDate + ", travelDays=" + travelDays
					+ ", costDetailType=" + costDetailType + ", travelCity="
					+ travelCity + ", budgetStatus=" + budgetStatus
					+ ", invoiceType=" + invoiceType + ", taxRate=" + taxRate
					+ ", taxMoney=" + taxMoney + ", startCity=" + startCity
					+ ", endCity=" + endCity + ", stayStandard=" + stayStandard
					+ ", amount=" + amount + ", approveMoney=" + approveMoney
					+ ", department=" + department + ", costCenter="
					+ costCenter + ", budgetMonth=" + budgetMonth
					+ ", trafficTool=" + trafficTool + ", costdetailDiv="
					+ costdetailDiv + "]";
		}

		
		
}

