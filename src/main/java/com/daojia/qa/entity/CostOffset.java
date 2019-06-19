package com.daojia.qa.entity;

import java.math.BigDecimal;


public class CostOffset {
	private long id;
	
	private String stringId;
	
	private String costFormId;
	
	private String borrowFormNum;
	
	private BigDecimal borrowAmount;
	
	private BigDecimal unOffsetAmount;
	
	private BigDecimal thisOffsetAmount;
	
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

	public String getBorrowFormNum() {
		return borrowFormNum;
	}

	public void setBorrowFormNum(String borrowFormNum) {
		this.borrowFormNum = borrowFormNum;
	}

	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public BigDecimal getUnOffsetAmount() {
		return unOffsetAmount;
	}

	public void setUnOffsetAmount(BigDecimal unOffsetAmount) {
		this.unOffsetAmount = unOffsetAmount;
	}

	public BigDecimal getThisOffsetAmount() {
		return thisOffsetAmount;
	}

	public void setThisOffsetAmount(BigDecimal thisOffsetAmount) {
		this.thisOffsetAmount = thisOffsetAmount;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "CostOffset [id=" + id + ", stringId=" + stringId
				+ ", costFormId=" + costFormId + ", borrowFormNum="
				+ borrowFormNum + ", borrowAmount=" + borrowAmount
				+ ", unOffsetAmount=" + unOffsetAmount + ", thisOffsetAmount="
				+ thisOffsetAmount + "]";
	}

}
