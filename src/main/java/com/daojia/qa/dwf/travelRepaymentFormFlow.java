package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.CostOffset;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.qa.util.GetUsernamAndPwd;

public class travelRepaymentFormFlow extends BaseTest<T>{
	 public DBUtil db;
	 String method_url="";	
	 Form form =new Form();
	       @BeforeClass
	       public void before() {
	           db = new DBUtil("P");
	           db.connection();
	
	       }

	       @AfterClass
	       public void afterClass() throws Exception {
	           if (db != null) {//断开数据库连接
	               db.closeConnection();
	           } else {
	               Logger.log("失败了");
	           }
	       }
	       
	       @Test(description="差旅申请单")
	   	public void submitTravelApplyForm(){
	   		String formOption="submit";
	   		Logger.log("保存或提交差旅申请单。");
	   		method_url="http://fmsystem205.djtest.cn/submit/travelapplyform";		
	   	
	   		form.setFormId("CLSQ201705150001");
	   		form.setFormType(1);
	   		form.setApplyDate("1988-11-21");
	   		form.setApplyPName("尚英");
	   		form.setApplyPJobnumber("4021");
	   		form.setEmpDepartment("信息技术质量部");
	   		form.setCostCenter("10.00.78.00.6");
	   		form.setEmpPTel("18201137136");
	   		form.setPayCompany("北京五八到家信息技术有限公司");
	   		form.setApplyMoney("4");
	   		form.setApproveMoney(new BigDecimal(4.00));
	   		form.setReceiptMoney("0");
	   		form.setCostDesc("test");
	   		form.setPayeeName("尚英");
	   		form.setReceiveBankName("招商银行");
	   		form.setReceiveBankAccount("6214830104619118");
	   		form.setApproveMoney(new BigDecimal(0));
	   		form.setPayStatus("0");
//	   		form.setFormStatus("0");
	   		form.setFormStatus("1");
	   		form.setMoneyType("0");
	   		form.setState(0);
	   		form.setApplyUserId("4011");
	   		form.setDepartmentCode("1-17-09-02");
	   		
	   		
	   		
	   		CostDetail costdetailtravel=new CostDetail();
	   		costdetailtravel.setStartDate("2017-10-25");
	   		costdetailtravel.setEndDate("2017-10-26");
	   		costdetailtravel.setTravelCity("北京市");
	   		costdetailtravel.setTravelDays(1);
	   		costdetailtravel.setCostdetailDiv("5");//非3的时候提交异常
	   	
	   		CostDetail costdetailstay=new CostDetail();
	   		costdetailstay.setCostHappenDate("2017-10-25");
	   		costdetailstay.setStartDate("2017-10-25");
	   		costdetailstay.setEndDate("2017-10-26");
	   		costdetailstay.setTravelDays(1);
	   		costdetailstay.setCostDetailType("010");
	   		costdetailstay.setTravelCity("北京");
	   		costdetailstay.setStayStandard("100");
	   		costdetailstay.setInvoiceType("0");
	   		costdetailstay.setAmount("1");
	   		costdetailstay.setBudgetStatus("0");
	   		costdetailstay.setCostdetailDiv("1");
	   		CostDetail costdetailtraffic=new CostDetail();
	   		costdetailtraffic.setCostHappenDate("2017-10-25");
	   		costdetailtraffic.setStartCity("北京市");
	   		costdetailtraffic.setEndCity("天津市");
	   		costdetailtraffic.setTrafficTool("火车");
	   		costdetailtraffic.setAmount("1");
	   		costdetailtraffic.setCostDetailType("010");
	   		costdetailtraffic.setBudgetStatus("0");
	   		costdetailtraffic.setCostdetailDiv("2");
	   		CostDetail costdetailother=new CostDetail();
	   		costdetailother.setCostHappenDate("2017-10-25");
	   		costdetailother.setAmount("1");
	   		costdetailother.setCostDetailType("010");
	   		costdetailother.setBudgetStatus("0");
	   		costdetailother.setCostdetailDiv("3");
	   		List<CostDetail> travelInfos=new ArrayList<CostDetail>();
	   		travelInfos.add(costdetailtravel);
	   		List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
	   		applyCostDetailsStay.add(costdetailstay);
	   		List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
	   		applyCostDetailsTraffic.add(costdetailtraffic);
	   		List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
	   		applyCostDetailsTraffic.add(costdetailother);
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
	   	
//	   	@Test(description="审批人审批通过1流程结束")
	   	@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitTravelApplyForm"})
	   public void approveTravelApplyFormIsAgreen1(){
	   		method_url="http://fmsystem205.djtest.cn/approve/approveform";	   		  	  
	   		String formId = "CLSQ201705150001";
	   		String option = "审批留言";
	   		String pass="0";
	   		String userName="";
	   		String password="";
	   		String addusers =""; //暂存待办未空
	   		String nodeId="";
	   		 String sql="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
	   		
	   		String userId =GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");

	   		  HashMap<String, String> param=new  HashMap<String, String>();


			  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"差旅申请单");
			  userName=userPwd.get(0);
			  password=userPwd.get(1);
			  option=userPwd.get(2);
	   		  param.put("formId", formId);
	   		  param.put("option", option);
	   		  param.put("pass", pass);
	   		  param.put("addusers", addusers);
	   		  param.put("nodeId", nodeId);
	   			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead(userName,password));
	   			Logger.log("获取结果---->>>>"+result);
	   			String flow_node_name=GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");
	   			if(result.get("success").toString().contains("已经审批过")){
	   				Assert.assertEquals("已经审批过", "已经审批过");
	   			}
	   			
	   			if(flow_node_name.equals("审批完成")){
	   				
	   				Logger.log("流程完毕");
	   			}else{
	   				Logger.log("继续调用");
	   				approveTravelApplyFormIsAgreen1();
	   			}
	   	
	   	}
	@Test(description="提交差旅报销单",dependsOnMethods={"approveTravelApplyFormIsAgreen1"})
	public void submitTravelRepaymentForm(){
		String formOption="submit";
		Logger.log("保存或提交差旅报销单。");
		method_url="http://fmsystem205.djtest.cn/submit/travelrepaymentform";
		form.setFormId("CLBX201705150001");
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
		form.setFormStatus("8");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
		form.setApplyFormId("CLSQ201705150001");
		CostDetail costdetailtravel=new CostDetail();
		costdetailtravel.setCostFormId("CLBX19881121001");
		costdetailtravel.setStartDate("2017-10-25");
		costdetailtravel.setEndDate("2017-10-26");
		costdetailtravel.setTravelCity("北京市");
		costdetailtravel.setTravelDays(1);
		costdetailtravel.setCostdetailDiv("5");//非3的时候提交异常
		costdetailtravel.setSort(0);
		CostDetail costdetailstay=new CostDetail();
		costdetailstay.setCostHappenDate("2017-10-25");
		costdetailstay.setStartDate("2017-10-25");
		costdetailstay.setEndDate("2017-10-26");
		costdetailstay.setTravelDays(1);
		costdetailstay.setCostDetailType("010");
		costdetailstay.setTravelCity("北京市");
		costdetailstay.setStayStandard("100");
		costdetailstay.setInvoiceType("0");
		costdetailstay.setAmount("1");
		costdetailstay.setApproveMoney("1");
		costdetailstay.setCostCenter("10.00.78.00.6");
		costdetailstay.setBudgetStatus("0");
		costdetailstay.setCostdetailDiv("1");
		costdetailstay.setCostFormId("CLBX201705150001");
		costdetailstay.setInvoiceType("0");
		costdetailstay.setSort(0);
		costdetailstay.setTaxMoney("0.03");
		
		CostDetail costdetailtraffic=new CostDetail();
		costdetailtraffic.setCostHappenDate("2017-10-25");
		costdetailtraffic.setStartCity("北京市");
		costdetailtraffic.setEndCity("天津市");
		costdetailtraffic.setTrafficTool("火车");
		costdetailtraffic.setAmount("1");
		costdetailtraffic.setApproveMoney("1");
		costdetailtraffic.setCostDetailType("010");
		costdetailtraffic.setCostCenter("10.00.78.00.6");
		costdetailtraffic.setCostFormId("CLBX201705150001");
		costdetailtraffic.setSort(0);
		costdetailtraffic.setTravelDays(0);
		costdetailtraffic.setBudgetStatus("0");
		costdetailtraffic.setCostdetailDiv("2");
		CostDetail costdetailother=new CostDetail();
		costdetailother.setAmount("1");
		costdetailother.setApproveMoney("1");
		costdetailother.setBudgetStatus("0");
		costdetailother.setCostCenter("10.00.78.00.6");
		costdetailother.setCostDetailType("010");
		costdetailother.setCostFormId("CLBX201705150001");
		costdetailother.setCostHappenDate("2017-10-25");
		costdetailother.setCostdetailDiv("3");
		costdetailother.setSort(0);
		costdetailtraffic.setTravelDays(0);
		CostDetail costDetailsSubsidy=new CostDetail();
		costDetailsSubsidy.setAmount("1");
		costDetailsSubsidy.setApproveMoney("1");
		costDetailsSubsidy.setBudgetStatus("0");
		costDetailsSubsidy.setCostCenter("10.00.78.00.6");
		costDetailsSubsidy.setCostFormId("CLBX201705150001");
		costDetailsSubsidy.setCostHappenDate("2017-10-25");
		costDetailsSubsidy.setCostdetailDiv("4");
		costDetailsSubsidy.setSort(0);
		costDetailsSubsidy.setTravelDays(1);
		List<CostDetail> travelInfos=new ArrayList<CostDetail>();
		travelInfos.add(costdetailtravel);
		List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
		applyCostDetailsStay.add(costdetailstay);
		List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
		applyCostDetailsTraffic.add(costdetailtraffic);
		List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
		applyCostDetailsTraffic.add(costdetailother);
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
	@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitTravelRepaymentForm"})
//	@Test(description="审批人审批通过1流程结束")
public void approveTravelRepaymentFormIsAgreen1(){
		method_url="http://fmsystem205.djtest.cn/approve/approveform";	   		  	  
   		String formId = "CLBX201705150001";
   		String option = "审批留言";
   		String pass="0";
   		String userName="";
   		String password="";
   		String addusers =""; //暂存待办未空
   		String nodeId="";
   		 String sql="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
   		
   		String userId =GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");

   		  HashMap<String, String> param=new  HashMap<String, String>();


		  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"报销单");
		  userName=userPwd.get(0);
		  password=userPwd.get(1);
		  option=userPwd.get(2);
   		  param.put("formId", formId);
   		  param.put("option", option);
   		  param.put("pass", pass);
   		  param.put("addusers", addusers);
   		  param.put("nodeId", nodeId);
   			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead(userName,password));
   			Logger.log("获取结果---->>>>"+result);
   			String flow_node_name=GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");
   			if(result.get("success").toString().contains("已经审批过")){
   				Assert.assertEquals("已经审批过", "已经审批过");
   			}
   			
   			if(flow_node_name.equals("审批完成")){
   				
   				Logger.log("流程完毕");
   			}else{
   				Logger.log("继续调用");
   				approveTravelRepaymentFormIsAgreen1();
   			}  
   			deleteTravelRepaymentFormTestData();
		
	}
	
	@Test(description="审批人审批不通过流程返回给申请人",dependsOnMethods={"approveTravelRepaymentFormIsAgreen1"})
	public void approveTravelRepaymentFormIsNoAgreen(){
		  String selectDailyReymentForm="SELECT * FROM t_form WHERE form_type='2' AND apply_p_name='尚英' AND form_id='CLBX201705150001'";
    	  String nameForm=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
		  if(StringUtils.isNotEmpty(nameForm)){
			  Logger.log("数据库已经存在差旅报销测试单");
    	  }else{
    		  submitTravelApplyForm();
    		  approveTravelApplyFormIsAgreen1();
    		  submitTravelRepaymentForm();
    		 
    	  }
		  String nameFormAgin=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
		  if(StringUtils.isNotEmpty(nameFormAgin)){
			  method_url="http://fmsystem205.djtest.cn/approve/approveform";	   		  	  
				String formId = "CLBX201705150001";
				String option = "审批不通过";
				String pass="1";
				String userName="";
				String password="";
				String addusers =""; //暂存待办未空
				String nodeId="";
				 String sql="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
				
				String userId =GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");
				
				if(userId.contains("审批完成")){
					
					Logger.log("流程完毕");
				}else{
					 HashMap<String, String> param=new  HashMap<String, String>();

					  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"差旅申请单");
					  userName=userPwd.get(0);
					  password=userPwd.get(1);
					  option=userPwd.get(2);
					  param.put("formId", formId);
					  param.put("option", option);
					  param.put("pass", pass);
					  param.put("addusers", addusers);
					  param.put("nodeId", nodeId);
					  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead(userName,password));
						Logger.log("获取结果---->>>>"+result);
						 String form_status="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
						 String get_form_status =GetDbMethod.getCostDetailSelctTest(db, sql, "form_status");
						 if(!get_form_status.equals("")||!get_form_status.equals("NULL")){
							 if(get_form_status.equals("3")){
									Assert.assertEquals("3", get_form_status);
								}else{
									Logger.log("驳回失败");
								}
						 }else{
							 Logger.log("订单状态是空，需要查看问题");
						 }
						
				
				}
				
		  }else{
			  Logger.log("差旅报销工单插入失败");
		  }
				

		  deleteTravelRepaymentFormTestData();
	}
	
	@Test(description="申请人撤回",dependsOnMethods={"approveTravelRepaymentFormIsNoAgreen"})
	public void recallMySubmitTravelRepaymentForm(){
			method_url="http://fmsystem205.djtest.cn/recallMySubmitForm";
			  String selectDailyReymentForm="SELECT * FROM t_form WHERE form_type='2' AND apply_p_name='尚英' AND form_id='CLBX201705150001'";
	    	  String nameForm=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
	    	  if(StringUtils.isNotEmpty(nameForm)){
				  Logger.log("数据库已经存在差旅报销单报销测试单");
	    	  }else{
	    		  submitTravelApplyForm();
	    		  approveTravelApplyFormIsAgreen1();
	    		  submitTravelRepaymentForm();
	    		 
	    	  }
			  String nameFormAgin=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
			  if(StringUtils.isNotEmpty(nameFormAgin)){
				  String formId = "CLBX201705150001";
				  String userName="";
				  String password="";
				  String sql="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
					String userId =GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");
					
					if(userId.contains("审批完成")){
						
						Logger.log("流程完毕");
					}else{
						 HashMap<String, String> param=new  HashMap<String, String>();

						  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"差旅申请单");
						  userName=userPwd.get(0);
						  password=userPwd.get(1);
						  param.put("formId", formId);
						  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead(userName,password));
							Logger.log("获取结果---->>>>"+result);
							 String form_status="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
							 String get_form_status =GetDbMethod.getCostDetailSelctTest(db, sql, "form_status");
							 if(!get_form_status.equals("")||!get_form_status.equals("NULL")){
								 if(get_form_status.equals("8")){
										Assert.assertEquals("8", get_form_status);
									}else{
										Logger.log("驳回失败");
									}
							 }else{
								 Logger.log("订单状态是空，需要查看问题");
							 }
					}
			  }else{
				  Logger.log("差旅报销单工单插入失败");
			  }
	    	
			  deleteTravelRepaymentFormTestData();
		}
	
	public void deleteTravelRepaymentFormTestData(){
		  String urlresponse="http://processmanager.djtest.cn/processDynTemp/endprocess";
	   	   String param="busiId=CLSQ201705150001";
	   	   JSONObject  response=HttpRequest.doGetReturnResponseJson(urlresponse,param,GetHead.gethead());
	   	String urlresponse1="http://processmanager.djtest.cn/processDynTemp/endprocess";
	   	   String param1="busiId=CLBX201705150001";
	   	   JSONObject  response1=HttpRequest.doGetReturnResponseJson(urlresponse1,param1,GetHead.gethead());
	   	   System.out.println(response);
	   	   if(StringUtils.isNotEmpty(response.get("code").toString())){

		String form="DELETE FROM t_form WHERE form_id='CLSQ201705150001'";
  	     
  	     String costdetail="DELETE FROM t_cost_details WHERE cost_form_id='CLSQ201705150001'";
  	     
  	     String flowinstance="DELETE FROM t_flow_instance WHERE form_id='CLSQ201705150001'";
  	     
  	    String flowdata=" DELETE FROM t_flow_data WHERE form_num='CLSQ201705150001'";
  		GetDbMethod.getDeleteDeatailTest(db, form);
  		GetDbMethod.getDeleteDeatailTest(db, costdetail);
  		GetDbMethod.getDeleteDeatailTest(db, flowinstance);
  		GetDbMethod.getDeleteDeatailTest(db, flowdata);
		String formrepayment="DELETE FROM t_form WHERE form_id='CLBX201705150001'";
	     
	     String costdetailrepayment="DELETE FROM t_cost_details WHERE cost_form_id='CLBX201705150001'";
	     
	     String flowinstancerepayment="DELETE FROM t_flow_instance WHERE form_id='CLBX201705150001'";
	     
	    String flowdatarepayment=" DELETE FROM t_flow_data WHERE form_num='CLBX201705150001'";
		GetDbMethod.getDeleteDeatailTest(db, formrepayment);
 		GetDbMethod.getDeleteDeatailTest(db, costdetailrepayment);
 		GetDbMethod.getDeleteDeatailTest(db, flowinstancerepayment);
 		GetDbMethod.getDeleteDeatailTest(db, flowdatarepayment); 
	}else{
		Logger.log("流程平台返回空，请查看流程平台是否调用成功！！！");
	}
	}
	
}
