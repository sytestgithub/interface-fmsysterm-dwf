package com.daojia.qa.entity;

public class BudgetCheckDto {
	private String costDetailType;
	private String costCenter;
	private String year;
	private String month;
	private String money;
	private String result;//是否超预算：1超，0未超
	private String sort;
	private String costdetailDiv;
	private String isOccupy;//是否占用：1不占用，2待占用，3已占用
	private String isRelationBudget;//是否关联预算
	public String getCostDetailType() {
		return costDetailType;
	}
	public void setCostDetailType(String costDetailType) {
		this.costDetailType = costDetailType;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCostdetailDiv() {
		return costdetailDiv;
	}
	public void setCostdetailDiv(String costdetailDiv) {
		this.costdetailDiv = costdetailDiv;
	}
	public String getIsOccupy() {
		return isOccupy;
	}
	public void setIsOccupy(String isOccupy) {
		this.isOccupy = isOccupy;
	}
	public String getIsRelationBudget() {
		return isRelationBudget;
	}
	public void setIsRelationBudget(String isRelationBudget) {
		this.isRelationBudget = isRelationBudget;
	}
	@Override
	public String toString() {
		return "BudgetCheckDto [costDetailType=" + costDetailType + ", costCenter=" + costCenter + ", year=" + year
				+ ", month=" + month + ", money=" + money + ", result=" + result + ", sort=" + sort + ", costdetailDiv="
				+ costdetailDiv + ", isOccupy=" + isOccupy + ", isRelationBudget=" + isRelationBudget + "]";
	}
}
