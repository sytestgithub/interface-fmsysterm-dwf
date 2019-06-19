package com.daojia.qa.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by daojia on 2017-12-8.
 */

public class TravelInfo {

    private long id;
    /**
     * 费用表FormID
     */
    private String costFormId;

    /**
     * 出差日期
     */
    private String startDate;

    /**
     * 返回日期
     */
    private String endDate;

    /**
     * 出发城市
     */
    private String startCity;

    /**
     * 出差城市
     */
    private String endCity;

    /**
     * 出差天数
     */
    private int travelDays;

    /**
     * 住宿标准
     */
    private BigDecimal stayStandard;

    /**
     * 住宿申请金额
     */
    private BigDecimal stayAmount;

    /**
     * 补贴金额
     */
    private BigDecimal allowanceAmount;

    /**
     * 其他申请金额
     */
    private BigDecimal otherAmount;

    /**
     * 合计金额
     */
    private BigDecimal totalAmount;

    /**
     * 预算状态
     */
    private String budgetStatus;

    /**
     * 成本中心
     */
    private String costCenter;

    /**
     * 费用明细类别
     */
    private String costDetailType;

    /**
     * 序号
     */
    private int sort;

    /**
     * 创建时间
     */
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCostFormId() {
        return costFormId;
    }

    public void setCostFormId(String costFormId) {
        this.costFormId = costFormId;
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

    public int getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }

    public BigDecimal getStayStandard() {
        return stayStandard;
    }

    public void setStayStandard(BigDecimal stayStandard) {
        this.stayStandard = stayStandard;
    }

    public BigDecimal getStayAmount() {
        return stayAmount;
    }

    public void setStayAmount(BigDecimal stayAmount) {
        this.stayAmount = stayAmount;
    }

    public BigDecimal getAllowanceAmount() {
        return allowanceAmount;
    }

    public void setAllowanceAmount(BigDecimal allowanceAmount) {
        this.allowanceAmount = allowanceAmount;
    }

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCostDetailType() {
        return costDetailType;
    }

    public void setCostDetailType(String costDetailType) {
        this.costDetailType = costDetailType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TravelInfo{" +
                "id=" + id +
                ", costFormId='" + costFormId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startCity='" + startCity + '\'' +
                ", endCity='" + endCity + '\'' +
                ", travelDays=" + travelDays +
                ", stayStandard=" + stayStandard +
                ", stayAmount=" + stayAmount +
                ", allowanceAmount=" + allowanceAmount +
                ", otherAmount=" + otherAmount +
                ", totalAmount=" + totalAmount +
                ", budgetStatus='" + budgetStatus + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", costDetailType='" + costDetailType + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
