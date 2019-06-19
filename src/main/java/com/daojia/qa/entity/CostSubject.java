package com.daojia.qa.entity;

import java.util.List;


public class CostSubject {

	private long id;

	
	private String majorId;

	
	private String costCode;


	private String pid;

	private String costDetailType;


	private Integer isVaild;


	private Integer isLeaf;


	private Integer quality;


	private List<CostReceiptsConfig> costReceiptsConfigList;

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getCostDetailType() {
		return costDetailType;
	}

	public void setCostDetailType(String costDetailType) {
		this.costDetailType = costDetailType;
	}

	public Integer getIsVaild() {
		return isVaild;
	}

	public void setIsVaild(Integer isVaild) {
		this.isVaild = isVaild;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public List<CostReceiptsConfig> getCostReceiptsConfigList() {
		return costReceiptsConfigList;
	}

	public void setCostReceiptsConfigList(List<CostReceiptsConfig> costReceiptsConfigList) {
		this.costReceiptsConfigList = costReceiptsConfigList;
	}

}
