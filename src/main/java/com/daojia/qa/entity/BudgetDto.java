package com.daojia.qa.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BudgetDto {

	private long id;

	private String serialNumber;

	private String departName;
	
	private String departCode;

	private String costCenter;
	
	private String costCode;

	private String costDetaileType;

	private BigDecimal budgetMoney;

	private BigDecimal occupyMoney;

	private BigDecimal usedMoney;

	private BigDecimal surplusMoney;

	private Integer year;

	private Integer month;
	
	private String createUser;
	
	private String updateUser;
	
	private Timestamp createTime;
	
	private Timestamp updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getCostDetaileType() {
		return costDetaileType;
	}

	public void setCostDetaileType(String costDetaileType) {
		this.costDetaileType = costDetaileType;
	}

	public BigDecimal getBudgetMoney() {
		return budgetMoney;
	}

	public void setBudgetMoney(BigDecimal budgetMoney) {
		this.budgetMoney = budgetMoney;
	}

	public BigDecimal getOccupyMoney() {
		return occupyMoney;
	}

	public void setOccupyMoney(BigDecimal occupyMoney) {
		this.occupyMoney = occupyMoney;
	}

	public BigDecimal getUsedMoney() {
		return usedMoney;
	}

	public void setUsedMoney(BigDecimal usedMoney) {
		this.usedMoney = usedMoney;
	}

	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
}
