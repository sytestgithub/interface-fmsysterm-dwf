package com.daojia.qa.entity;

import java.math.BigDecimal;


public class ApplyCostDetails {
	private long id;
	
	private long costFormId;
	
	private String costHappenDate;
	
	private String costDetailType;
	
	private BigDecimal reimbursementAmount;
	
	private String budgetStatus;
	
	private int travelDays;
	
	private String travelCity;
	
	private BigDecimal stayStandard;
	
	private String trafficTool;

	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getCostFormId() {
		return costFormId;
	}



	public void setCostFormId(long costFormId) {
		this.costFormId = costFormId;
	}



	public String getCostHappenDate() {
		return costHappenDate;
	}



	public void setCostHappenDate(String costHappenDate) {
		this.costHappenDate = costHappenDate;
	}



	public String getCostDetailType() {
		return costDetailType;
	}



	public void setCostDetailType(String costDetailType) {
		this.costDetailType = costDetailType;
	}



	public BigDecimal getReimbursementAmount() {
		return reimbursementAmount;
	}



	public void setReimbursementAmount(BigDecimal reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}



	public String getBudgetStatus() {
		return budgetStatus;
	}



	public void setBudgetStatus(String budgetStatus) {
		this.budgetStatus = budgetStatus;
	}



	public int getTravelDays() {
		return travelDays;
	}



	public void setTravelDays(int travelDays) {
		this.travelDays = travelDays;
	}



	public String getTravelCity() {
		return travelCity;
	}



	public void setTravelCity(String travelCity) {
		this.travelCity = travelCity;
	}



	public BigDecimal getStayStandard() {
		return stayStandard;
	}



	public void setStayStandard(BigDecimal stayStandard) {
		this.stayStandard = stayStandard;
	}



	public String getTrafficTool() {
		return trafficTool;
	}



	public void setTrafficTool(String trafficTool) {
		this.trafficTool = trafficTool;
	}



	@Override
	public String toString() {
		return "ApplyCostDetails [id=" + id + ", costFormId=" + costFormId
				+ ", costHappenDate=" + costHappenDate + ", costDetailType="
				+ costDetailType + ", reimbursementAmount="
				+ reimbursementAmount + ", budgetStatus=" + budgetStatus
				+ ", travelDays=" + travelDays + ", travelCity=" + travelCity
				+ ", stayStandard=" + stayStandard + ", trafficTool="
				+ trafficTool + "]";
	}


}
