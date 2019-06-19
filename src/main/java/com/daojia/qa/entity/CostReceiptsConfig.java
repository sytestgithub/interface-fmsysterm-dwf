package com.daojia.qa.entity;



public class CostReceiptsConfig {


	private long id;

	private String costMajorId;

	private String receiptsMajorId;


	private Integer isVaild;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getIsVaild() {
		return isVaild;
	}

	public void setIsVaild(Integer isVaild) {
		this.isVaild = isVaild;
	}

	public String getCostMajorId() {
		return costMajorId;
	}

	public void setCostMajorId(String costMajorId) {
		this.costMajorId = costMajorId;
	}

	public String getReceiptsMajorId() {
		return receiptsMajorId;
	}

	public void setReceiptsMajorId(String receiptsMajorId) {
		this.receiptsMajorId = receiptsMajorId;
	}

}
