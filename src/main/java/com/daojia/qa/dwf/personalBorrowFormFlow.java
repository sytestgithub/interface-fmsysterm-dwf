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

import bsh.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.qa.util.GetUsernamAndPwd;

public class personalBorrowFormFlow extends BaseTest<T>{
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
	       @Test(description="个人借款单")
	   	public void submitPersonalBorrowForm(){
	    	  String selectRefundForm="SELECT * FROM t_form WHERE form_type='3' AND apply_p_name='王宏业' AND form_status=9";
	    	  String nameForm=GetDbMethod.getCostDetailSelctTest(db,selectRefundForm,"form_id");
	    	  if(StringUtils.isNotEmpty(nameForm)){
	    		  String   deleteRefundForm="DELETE FROM t_form WHERE form_type='3' AND apply_p_name='王宏业' AND form_status=9 ";
		    		GetDbMethod.getDeleteDeatailTest(db, deleteRefundForm);
	    	  }else{
	    		  Logger.log("王宏业没有借款记录");
	    	  }
	    	   
	    	   String formOption="submit";
	   		Logger.log("保存或提交个人借款单。");
	   		String userId="819";
	   		method_url="http://fmsystem205.djtest.cn/submit/personalborrowform";
	   	
	   		form.setFormId("GRJK201705150001");
	   		form.setFormType(3);
	   		form.setApplyDate("2017-10-30");
	   		form.setApplyPName("王宏业");
	   		form.setApplyPJobnumber("815");
	   		form.setEmpDepartment("企业平台研发部");
	   		form.setCostCenter("10.00.78.00.6");
	   		form.setEmpPTel("18514008498");
	   		form.setPayCompany("北京五八到家信息技术有限公司");
	   		form.setApplyMoney("1");
	   		form.setApproveMoney(new BigDecimal(1.00));
	   		form.setReceiptMoney("0");
	   		form.setCostDesc("test");
	   		form.setPayeeName("王宏业");
	   		form.setReceiveBankName("招商银行");
	   		form.setReceiveBankAccount("6214830138409353");
	   		form.setApproveMoney(new BigDecimal(0));
	   		form.setPayStatus("0");
//	   		form.setFormStatus("0");
	   		form.setFormStatus("1");
	   		form.setMoneyType("0");
	   		form.setState(0);
	   		form.setApplyUserId("4011");
	   		form.setDepartmentCode("1-19-06-04");
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
	   		 param.put("baseForm", JSONObject.toJSONString(form));	
	   		 param.put("costDetails", JSONObject.toJSONString(costDetails));
	   		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead("wanghongye","`12qweasd"));
	   			Logger.log("获取结果---->>>>"+result); 
	    	 
	       }
	       
	       @Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitPersonalBorrowForm"})
//	   	@Test(description="审批人审批通过1流程结束")
	   	public void personalBorrowFormIsAgreen1(){
	   			method_url="http://fmsystem205.djtest.cn/approve/approveform";	   		  	  
	   			String formId = "GRJK201705150001";
	   			String option = "个人借款单留言";
	   			String pass="0";
	   			String userName="";
	   			String password="";
	   			String addusers =""; //暂存待办未空
	   			String nodeId="";
	   			 String sql="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
	   			
	   			String userId =GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");

	   			  HashMap<String, String> param=new  HashMap<String, String>();


	   			  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"个人借款单");
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
	   				if(result.get("success").toString().contains("审批成功")){
	   					Assert.assertEquals("审批成功", "审批成功");
	   				}else{
	   					Logger.log("存在异常");
	   				}
	   				
	   				if(flow_node_name.equals("审批完成")){
	   					Logger.log("流程完毕");
	   				}else{
	   					Logger.log("继续调用");
	   					personalBorrowFormIsAgreen1();
	   				}
	   				deletePersonalBorrowFormTestData();
	   		}
	       
	   	@Test(description="审批人审批不通过流程返回给申请人",dependsOnMethods={"personalBorrowFormIsAgreen1"})
		public void approvePersonalBorrowFormIsNoAgreen(){
			  String selectDailyReymentForm="SELECT * FROM t_form WHERE form_type='3' AND apply_p_name='王宏业' AND form_id='GRJK201705150001'";
	    	  String nameForm=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
			  if(StringUtils.isNotEmpty(nameForm)){
				  Logger.log("数据库已经存在个人借款单测试单--->>"+nameForm.length());
	    	  }else{
	    		  Logger.log("调用接口单申请方法");
	    		  submitPersonalBorrowForm();
	    		  Logger.log("获取了工单");
	    	  }
			  String nameFormAgin=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
			  if(StringUtils.isNotEmpty(nameFormAgin)){
				  Logger.log("进入不同意流程");
				  method_url="http://fmsystem205.djtest.cn/approve/approveform";	   		  	  
					String formId = "GRJK201705150001";
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

						  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"个人借款单");
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
									 Logger.log("返回订单状态");
										Assert.assertEquals("3", get_form_status);
									}else{
										Logger.log("驳回失败");
									}
							 }else{
								 Logger.log("订单状态是空，需要查看问题");
							 }
							
					
					}
					
			  }else{
				  Logger.log("个人借款工单插入失败");
			  }
					

			  deletePersonalBorrowFormTestData();
		}
	       
		@Test(description="申请人撤回",dependsOnMethods={"approvePersonalBorrowFormIsNoAgreen"})
		public void recallMySubmitPersonalBorrowForm(){
				method_url="http://fmsystem205.djtest.cn/recallMySubmitForm";
				  String selectDailyReymentForm="SELECT * FROM t_form WHERE form_type='3' AND apply_p_name='王宏业' AND form_id='GRJK201705150001'";
		    	  String nameForm=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
		    	  if(StringUtils.isNotEmpty(nameForm)){
					  Logger.log("数据库已经存在个人借款单测试单");
		    	  }else{
		    		  Logger.log("调用接口单申请方法");
		    		  submitPersonalBorrowForm();
		    		  Logger.log("获取了工单");
		    	  }
				  String nameFormAgin=GetDbMethod.getCostDetailSelctTest(db,selectDailyReymentForm,"form_id");
				
				  if(StringUtils.isNotEmpty(nameFormAgin)){
					  String formId = "GRJK201705150001";
					  String userName="";
					  String password="";
					  String sql="SELECT * FROM t_form WHERE form_id='"+formId+"'";	
						String userId =GetDbMethod.getCostDetailSelctTest(db, sql, "flow_node_name");
						
						if(userId.contains("审批完成")){
							
							Logger.log("流程完毕");
						}else{
							 HashMap<String, String> param=new  HashMap<String, String>();

							  ArrayList<String> userPwd=GetUsernamAndPwd.getUserAndPwd(userId, userName, password,"个人借款单");
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
					  Logger.log("个人借款工单插入失败");
				  }
		    	
				  deletePersonalBorrowFormTestData();
			}
		
		
		
	       public void deletePersonalBorrowFormTestData(){
	    	   String urlresponse="http://processmanager.djtest.cn/processDynTemp/endprocess";
	    	   String param="busiId=GRJK201705150001";
	    	   JSONObject  response=HttpRequest.doGetReturnResponseJson(urlresponse,param,GetHead.gethead());
	    	   System.out.println(response);
	    	   if(StringUtils.isNotEmpty(response.get("code").toString())){
	    		Logger.log("进入财务删除阶段");
	    	
	 String form="DELETE FROM t_form WHERE form_id='GRJK201705150001'";
	   		     
	   		     String costdetail="DELETE FROM t_cost_details WHERE cost_form_id='GRJK201705150001'";
	   		     
	   		     String flowinstance="DELETE FROM t_flow_instance WHERE form_id='GRJK201705150001'";
	   		     
	   		    String flowdata=" DELETE FROM t_flow_data WHERE form_num='GRJK201705150001'";
	   			GetDbMethod.getDeleteDeatailTest(db, form);
	   			GetDbMethod.getDeleteDeatailTest(db, costdetail);
	   			GetDbMethod.getDeleteDeatailTest(db, flowinstance);
	   			GetDbMethod.getDeleteDeatailTest(db, flowdata);
	    	   }else{
	    			Logger.log("流程平台返回空，请查看流程平台是否调用成功！！！");
	    	   }
	  	}
}
