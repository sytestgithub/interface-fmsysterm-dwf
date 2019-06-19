package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.Contract;
import com.daojia.qa.entity.ContractDetail;
import com.daojia.qa.entity.ContractPaymentDetailDto;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.CostOffset;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetCookiesTest;
import com.daojia.qa.util.GetHead;
import com.google.gson.JsonObject;
import com.offbytwo.jenkins.model.Job;

public class SubmitFormAll {
	String method_url="";
	
	@Test(description="日常报销单")
public void submitDailyRepaymentForm(){
		method_url="http://fmsystem205.djtest.cn/submit/dailyrepaymentform";
		Form form = new Form();
		CostDetail cost = new CostDetail();
		form.setFormId("RCBX20170515");
		form.setFormType(4);
		form.setApplyDate("2017-05-15");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("1.00");
		form.setReceiptMoney("1.00");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
		form.setFormStatus("1");
		form.setMoneyType("0");
		form.setState(0);
		form.setDepartmentCode("1-17-09-02");
		
		cost.setCostHappenDate("2017-09-15");
		cost.setTravelDays(0);
		cost.setCostDetailType("010");
		cost.setBudgetStatus("0");
		cost.setAmount("1");
		cost.setCostdetailDiv("3");
		
		List<CostDetail> costDetails = new ArrayList<CostDetail>();
		costDetails.add(cost);
		List<CostOffset> costOffsets = new ArrayList<CostOffset>();
//		Map<String, List<CostDetail>> costDetailses = new HashMap<String, List<CostDetail>>();
//		costDetailses.put("costDetails", costDetails);
		String formOption="submit";
		String userId="4011";
		  HashMap<String, String> param=new  HashMap<String, String>();
		  param.put("baseForm",JSONObject.toJSONString(form));
		  param.put("formOption",formOption );
		  param.put("userId",userId);
		  param.put("costDetails",JSONObject.toJSONString(costDetails) );
		  param.put("costOffsets", JSONObject.toJSONString(costOffsets));

		JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
		Logger.log("获取结果---->>>>"+result);
}
	
	
	@Test(description="差旅申请单")
	public void travelapplyformold(){
		String formOption="submit";
		Logger.log("保存或提交差旅申请单。");
		method_url="http://fmsystem205.djtest.cn/submit/travelapplyform";		
		Form form = new Form();
		form.setFormId("CLSQ19881121001");
		form.setFormType(1);
		form.setApplyDate("1988-11-21");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("0");
		form.setReceiptMoney("0");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
		form.setFormStatus("1");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
		
		CostDetail costdetail=new CostDetail();
		costdetail.setAmount("1");
		costdetail.setApproveMoney("1");
		costdetail.setBudgetMonth("08");
		costdetail.setBudgetStatus("1");
		costdetail.setCostCenter("10.00.78.00.6");
		costdetail.setCostdetailDiv("ZG");
		costdetail.setCostDetailType("010");
		costdetail.setCostFormId("CLSQ19881121001");
		costdetail.setCostHappenDate("2017-09-15");
		costdetail.setDepartment("1-17-09-02");
		costdetail.setEndCity("");
		costdetail.setId(new Long(1));
		costdetail.setInvoiceType("");
		costdetail.setSort(1);
		costdetail.setStartCity("北京");
		costdetail.setEndCity("上海");
		costdetail.setEndDate("2017-09-18");
		costdetail.setStayStandard("");

		costdetail.setTaxMoney("2");
		costdetail.setTaxRate("0");
		costdetail.setTrafficTool("");
		costdetail.setTravelCity("上海");
		costdetail.setTravelDays(1);
		
		CostDetail costdetailtravel=new CostDetail();
		costdetailtravel.setStartDate("2017-09-15");
		costdetailtravel.setEndDate("2017-09-15");
		costdetailtravel.setTravelCity("天津市");
		costdetailtravel.setTravelDays(0);
		costdetailtravel.setCostdetailDiv("5");//非3的时候提交异常
		costdetailtravel.setCostFormId("CLSQ19881121001");
		costdetailtravel.setSort(0);
		CostDetail costdetailstay=new CostDetail();
		CostDetail costdetailtraffic=new CostDetail();
		CostDetail costdetailother=new CostDetail();
		List<CostDetail> travelInfos=new ArrayList<CostDetail>();
		travelInfos.add(costdetailtravel);
		List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
//		applyCostDetailsStay.add(costdetailstay);
		List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
//		applyCostDetailsTraffic.add(costdetailtraffic);
		List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
//		applyCostDetailsTraffic.add(costdetailother);
		 HashMap<String, String> param=new  HashMap<String, String>();
		 param.put("formOption", formOption);
		 param.put("applyForm", JSONObject.toJSONString(form));		 
		 param.put("travelInfos", JSONObject.toJSONString(travelInfos));
		 param.put("costDetailsStays", JSONObject.toJSONString(applyCostDetailsTraffic));
		 param.put("costDetailsTraffics", JSONObject.toJSONString(applyCostDetailsTraffic));
		 param.put("costDetailsOthers", JSONObject.toJSONString(applyCostDetailsOther));
			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果---->>>>"+result);
		
	}
	
	
	
	@Test(description="差旅报销单")
	public void submittravelrepaymentform(){
		String formOption="submit";
		Logger.log("保存或提交差旅报销单。");
		method_url="http://fmsystem205.djtest.cn/submit/travelrepaymentform";		
		Form form = new Form();
		form.setFormId("CLBX19881121001");
		form.setFormType(2);
		form.setApplyDate("1988-11-21");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("0");
		form.setReceiptMoney("0");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
		form.setFormStatus("1");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
		form.setApplyFormId("CLSQ19881121001");
		CostDetail costdetailtravel=new CostDetail();
		costdetailtravel.setStartDate("2017-09-15");
		costdetailtravel.setEndDate("2017-09-15");
		costdetailtravel.setTravelCity("天津市");
		costdetailtravel.setTravelDays(0);
		costdetailtravel.setCostdetailDiv("5");//非3的时候提交异常
		costdetailtravel.setCostFormId("CLBX19881121001");
		costdetailtravel.setSort(0);
		CostDetail costdetailstay=new CostDetail();
		CostDetail costdetailtraffic=new CostDetail();
		CostDetail costdetailother=new CostDetail();
		CostDetail costDetailsSubsidy=new CostDetail();
		List<CostDetail> travelInfos=new ArrayList<CostDetail>();
		travelInfos.add(costdetailtravel);
		List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
//		applyCostDetailsStay.add(costdetailstay);
		List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
//		applyCostDetailsTraffic.add(costdetailtraffic);
		List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
//		applyCostDetailsTraffic.add(costdetailother);
		List<CostDetail> costDetailsSubsidys=new ArrayList<CostDetail>();
//		applyCostDetailsTraffic.add(costDetailsSubsidy);
		List<CostOffset> costOffsets = new ArrayList<CostOffset>();
		 HashMap<String, String> param=new  HashMap<String, String>();
		 param.put("formOption", formOption);
		 param.put("repaymentForm", JSONObject.toJSONString(form));		 
		 param.put("travelInfos", JSONObject.toJSONString(travelInfos));
		 param.put("costDetailsStays", JSONObject.toJSONString(applyCostDetailsTraffic));
		 param.put("costDetailsTraffics", JSONObject.toJSONString(applyCostDetailsTraffic));
		 param.put("costDetailsOthers", JSONObject.toJSONString(applyCostDetailsOther));
		 param.put("costDetailsSubsidys", JSONObject.toJSONString(costDetailsSubsidys));
		 param.put("costOffsets", JSONObject.toJSONString(costOffsets));
			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果---->>>>"+result);
}
	
	@Test(description="个人借款单")
	public void submitPersonalBorrowform(){
		String formOption="submit";
		Logger.log("保存或提交个人借款单。");
		String userId="4011";
		method_url="http://fmsystem205.djtest.cn/submit/personalborrowform";
		Form form = new Form();
		form.setFormId("GRJK19881121001");
		form.setFormType(3);
		form.setApplyDate("1988-11-21");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("0");
		form.setReceiptMoney("0");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
		form.setFormStatus("1");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
		form.setApplyFormId("");//借款单号

		List<CostDetail> costDetails=new ArrayList<CostDetail>();
		CostDetail costdetail=new CostDetail();
		costdetail.setBudgetMonth("2017-08");
		costdetail.setApproveMoney("1");
		costdetail.setAmount("1");
		costdetail.setBudgetStatus("0");
		costdetail.setCostCenter("10.00.78.00.6");
		costdetail.setCostDetailType("010");
		costdetail.setCostHappenDate("2017-09-21");
		costdetail.setCostdetailDiv("3");
		costDetails.add(costdetail);
		
		 HashMap<String, String> param=new  HashMap<String, String>();
		 param.put("formOption", formOption);
		 param.put("userId", userId);
		 param.put("baseForm", JSONObject.toJSONString(form));	
		 param.put("costDetails", JSONObject.toJSONString(costDetails));
		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果---->>>>"+result);
		
	}
	
	
	@Test(description="个人还款单")
	public void submitRefundForm(){
		String formOption="submit";
		Logger.log("保存或提交个人还款单。");
		String userId="4011";
		method_url="http://fmsystem205.djtest.cn/submit/refundform";
		Form form = new Form();
		form.setFormId("HKD19881121001");
		form.setFormType(8);
		form.setApplyDate("1988-11-21");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("0");
		form.setReceiptMoney("0");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
		form.setFormStatus("1");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
		form.setApplyFormId("");//借款单号

		List<CostDetail> costDetails=new ArrayList<CostDetail>();
		CostDetail costdetail=new CostDetail();
		costdetail.setBudgetMonth("2017-08");
		costdetail.setApproveMoney("1");
		costdetail.setAmount("1");
		costdetail.setBudgetStatus("0");
		costdetail.setCostCenter("10.00.78.00.6");
		costdetail.setCostDetailType("010");
		costdetail.setCostHappenDate("2017-09-21");
		costdetail.setCostdetailDiv("5");
		costDetails.add(costdetail);
		
		 HashMap<String, String> param=new  HashMap<String, String>();
		 param.put("formOption", formOption);
		 param.put("userId", userId);
		 param.put("baseForm", JSONObject.toJSONString(form));	
		 param.put("costDetails", JSONObject.toJSONString(costDetails));
		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果---->>>>"+result);
	}
	
	@Test(description="预算增补单")
	public void dailyRepaymentForm(){
		String formOption="submit";
		Logger.log("保存或提交预算增补单。");
		String userId="4011";
		method_url="http://fmsystem205.djtest.cn/budegtSupplementForm";	
		Form form = new Form();
		form.setFormId("YSZB19881121001");
		form.setFormType(9);
		form.setApplyDate("1988-11-21");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("0");
		form.setReceiptMoney("0");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
		form.setFormStatus("1");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");

		List<CostDetail> costDetails=new ArrayList<CostDetail>();
		CostDetail costdetail=new CostDetail();
		costdetail.setBudgetMonth("2017-08");
		costdetail.setApproveMoney("1");
		costdetail.setAmount("1");
		costdetail.setBudgetStatus("0");
		costdetail.setCostCenter("10.00.78.00.6");
		costdetail.setCostDetailType("010");
		costdetail.setCostHappenDate("2017-09-21");
		costdetail.setCostdetailDiv("5");
		costDetails.add(costdetail);
		
		 HashMap<String, String> param=new  HashMap<String, String>();
		 param.put("formOption", formOption);
		 param.put("userId", userId);
		 param.put("baseForm", JSONObject.toJSONString(form));	
		 param.put("costDetails", JSONObject.toJSONString(costDetails));
		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果---->>>>"+result);
	}
	
//	@Test(description="合同审批单")
//	public void submitContractApproveForm(){
//		String formOption="submit";
//		Logger.log("保存或提交合同审批单");
//		method_url="http://fmsystem205.djtest.cn/submit/contractapproveform";	
//		Form form = new Form();
//		form.setFormId("HTSP20170515");
//		form.setFormType(6);
//		form.setApplyDate("2017-05-15");
//		form.setApplyPName("尚英");
//		form.setApplyPJobnumber("4021");
//		form.setEmpDepartment("信息技术质量部");
//		form.setCostCenter("10.00.78.00.6");
//		form.setEmpPTel("18201137136");
//		form.setPayCompany("北京五八到家信息技术有限公司");
//		form.setApplyMoney("1.00");
//		form.setReceiptMoney("1.00");
//		form.setNoAmount("1.00");
//		form.setCostDesc("test");
//		form.setPayeeName("尚英");
//		form.setReceiveBankName("招商银行");
//		form.setReceiveBankAccount("6214830104619118");
//		form.setApproveMoney(new BigDecimal(0));
//		form.setPayStatus("0");
//		form.setFormStatus("1");
//		form.setMoneyType("0");
//		form.setState(0);
//		form.setDepartmentCode("1-17-09-02");
//		form.setApplyFormId("");
//		List<ContractDetail> contractDetailscds=new ArrayList<ContractDetail>();
//		ContractDetail contractdetail=new ContractDetail();
//		contractdetail.setBudgetMoney(new BigDecimal(10.00));
//		List<Contract> contractDetails=new ArrayList<Contract>();
//		Contract contrct=new Contract();
//		contrct.setAlreadyAmount(new BigDecimal(10.00));
//		
//		 HashMap<String, String> param=new  HashMap<String, String>();
//		 param.put("formOption", formOption);
//		 param.put("baseForm", JSONObject.toJSONString(form));	
//		 param.put("contractDetailscds", JSONObject.toJSONString(contractDetailscds));
//		 param.put("contractDetails", JSONObject.toJSONString(contractDetails));
//		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
//			Logger.log("获取结果---->>>>"+result);
//		
//	}
//	
//	@Test(description="对公付款单")
//	public void submitToPublicPayForm(){
//		String formOption="submit";
//		Logger.log("保存或提交个人还款单。");
//		method_url="http://fmsystem205.djtest.cn/submit/topublicpayform";	
//		Form form = new Form();
//		form.setFormId("DGFK20170515");
//		form.setFormType(7);
//		form.setApplyDate("2017-05-15");
//		form.setApplyPName("尚英");
//		form.setApplyPJobnumber("4021");
//		form.setEmpDepartment("信息技术质量部");
//		form.setCostCenter("10.00.78.00.6");
//		form.setEmpPTel("18201137136");
//		form.setPayCompany("北京五八到家信息技术有限公司");
//		form.setApplyMoney("1.00");
//		form.setReceiptMoney("1.00");
//		form.setNoAmount("1.00");
//		form.setCostDesc("test");
//		form.setPayeeName("尚英");
//		form.setReceiveBankName("招商银行");
//		form.setReceiveBankAccount("6214830104619118");
//		form.setApproveMoney(new BigDecimal(0));
//		form.setPayStatus("0");
//		form.setFormStatus("1");
//		form.setMoneyType("0");
//		form.setState(0);
//		form.setDepartmentCode("1-17-09-02");
//		form.setApplyFormId("HTSP2017062006143");
//		String userId="4011";
//		List<ContractPaymentDetailDto> contractDetails=new ArrayList<ContractPaymentDetailDto>();
//		ContractPaymentDetailDto contractpaydetail=new ContractPaymentDetailDto();
//		Boolean boo=true;
//		contractpaydetail.setCostTime("2017-09-15");
//		contractpaydetail.setPaymentMoney("1");
//		contractpaydetail.setSelectThis(boo);
//		contractpaydetail.setTaxes("0");
//		 HashMap<String, String> param=new  HashMap<String, String>();
//		 param.put("formOption", formOption);
//		 param.put("userId", userId);
//		 param.put("baseForm", JSONObject.toJSONString(form));	
//		 param.put("applyForm", JSONObject.toJSONString(contractDetails));
//		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
//			Logger.log("获取结果---->>>>"+result);
//	}
	
}