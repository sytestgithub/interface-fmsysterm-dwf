package com.daojia.qa.entity;


import java.util.Date;

import com.daojia.spat.dsf.serializer.component.annotation.DSFSerializable;
import com.daojia.spat.dsf.serializer.component.annotation.DSFMember;




public class ProcessTaskQuery {
	
	//流程实例ID

    private String processInstanceId;
	
	//业务ID(like)

    private String businessId;
	
	//项目类型

    private String projectType;
	
	//流程key

    private String processDefinitionKey;
	
	//当前页数

    private int page;

    //页面大小

    private int pageSize;
	
	//待办人ID

    private String userId;

	//发起人

    private String applyUserId;
	
	//开始发起时间

    private Date startTimeBegin;
	
	//结束发起时间

    private Date startTimeEnd;
	
	//发起人姓名(like)

    private String applyUserName;
	
	//发起人姓名或者业务ID

    private String nameOrBusinessId;
	
	//流程定义名称

    private String processName;
	
	// 请求人用户id
	
	private String requestUserId;
	
	// 任务id
	
	private String taskId;
	
	// 当前办理人姓名

	private String assigneeName;
	
	// 审批类型

	private String approvalType;
		
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Date getStartTimeBegin() {
		return startTimeBegin;
	}

	public void setStartTimeBegin(Date startTimeBegin) {
		this.startTimeBegin = startTimeBegin;
	}

	public Date getStartTimeEnd() {
		return startTimeEnd;
	}

	public void setStartTimeEnd(Date startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}
	
	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getNameOrBusinessId() {
		return nameOrBusinessId;
	}

	public void setNameOrBusinessId(String nameOrBusinessId) {
		this.nameOrBusinessId = nameOrBusinessId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	
	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	@Override
	public String toString() {
		return "ProcessTaskQuery [processInstanceId=" + processInstanceId
				+ ", businessId=" + businessId + ", projectType=" + projectType
				+ ", processDefinitionKey=" + processDefinitionKey + ", page="
				+ page + ", pageSize=" + pageSize + ", userId=" + userId
				+ ", applyUserId=" + applyUserId + ", startTimeBegin="
				+ startTimeBegin + ", startTimeEnd=" + startTimeEnd
				+ ", applyUserName=" + applyUserName + ", processName=" + processName + ", requestUserId="
				+ requestUserId + ", taskId=" + taskId + ", assigneeName=" + assigneeName 
				+ ", approvalType=" + approvalType +"]";
	}

	
}