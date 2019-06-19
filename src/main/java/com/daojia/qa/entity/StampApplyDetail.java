package com.daojia.qa.entity;


import com.daojia.spat.dao.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by daojia on 2018-3-23.
 */

public class StampApplyDetail {
    private Long id;
    private int sort;
    private String applyType;
    private String applyTypeName;
    private String companyName;
    private String stampName;
    private String stampNameId;
    private String stampMaterialName;
    private int num;
    private BigDecimal amount;
    private String returnDate;
    private String formId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    public String getStampMaterialName() {
        return stampMaterialName;
    }

    public void setStampMaterialName(String stampMaterialName) {
        this.stampMaterialName = stampMaterialName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        return "StampApplyDetail{" +
                "id=" + id +
                ", sort=" + sort +
                ", applyType='" + applyType + '\'' +
                ", applyTypeName='" + applyTypeName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", stampName='" + stampName + '\'' +
                ", stampMaterialName='" + stampMaterialName + '\'' +
                ", num=" + num +
                ", amount=" + amount +
                ", returnDate=" + returnDate +
                ", formId='" + formId + '\'' +
                '}';
    }

	public String getStampNameId() {
		return stampNameId;
	}

	public void setStampNameId(String stampNameId) {
		this.stampNameId = stampNameId;
	}
}
