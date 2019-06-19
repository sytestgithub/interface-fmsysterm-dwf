package com.daojia.qa.dwf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.annotation.DSF;
import com.bj58.daojia.qa.base.BaseDsfTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.workflow.entity.ProcessExecResult;
import com.daojia.workflow.entity.ProcessTaskDto;
import com.daojia.workflow.entity.ProcessTaskOut;
import com.daojia.workflow.entity.query.ProcessTaskQuery;
import com.daojia.workflow.service.TaskOperateService;
import com.daojia.workflow.util.PageList;
@DSF(url="tcp://workflow/TaskServiceImpl")
public class personalBorrowFormNewFlow  extends  BaseDsfTest<TaskOperateService>{
	String method_url="";
	DBUtil db;
//	DBUtil dbworkflow;
	Form form =new Form();
	@BeforeClass
	public void before(){
	db=new DBUtil("P");
	db.connection();

	if(db!=null){
		Logger.log("数据库已经连接"+db);

	}

	}

	@AfterClass
	public void afterClass() throws Exception{

	}
	
	/*
	    * 保存和提交个人借款单接口
	    * */
//	/*
		@Test( description="保存和提交个人借款单接口")
		public void submitPersonalBorrowForm1(){
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
			form.setFormId("GRJK201714290001");
	   		form.setFormType(3);
	   		form.setApplyDate("2017-12-26");
	   		form.setApplyPName("尚英");
	   		form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setPlanRepaymentDate("2018-01-01");
			form.setInnerRemark("abc");
			form.setApplyMoney("1");
			form.setReceiptMoney("1.00");
			form.setCostDesc("描述信息");
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
			form.setMoneyType("0");
			form.setState(0);
			form.setDepartmentCode("1-17-09-02");
			 String formOption="submit";
		   		List<CostDetail> costDetails=new ArrayList<CostDetail>();
		   		CostDetail costdetail=new CostDetail();
		   		costdetail.setApproveMoney("1");
		   		costdetail.setAmount("2");
		   		costdetail.setBudgetStatus("0");
		   		costdetail.setCostCenter("10.00.78.00.6");
		   		costdetail.setCostDetailType("010");
		   		costdetail.setCostHappenDate("2017-09-21");
		   		costdetail.setCostdetailDiv("3");
		   		costDetails.add(costdetail);
		   		List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
				 UploadFileDto file1=new UploadFileDto();
					file1.setFileAddress("http://www.baidu.com1");
					file1.setFileName("personarborrowfile1");
					file1.setIsPic("0");
					fileList.add(file1);

					 
			   	   	 Logger.log("数据处理");
			        
					String modifysql=" UPDATE t_form SET no_amount=0,already_amount=apply_money,wait_amount=0 WHERE  form_type=3 AND apply_p_name='尚英'";				
				int i=GetDbMethod.updateSelectCondition(db, modifysql);
				if(i>0){
					Logger.log("修改订单成功");
				}else{
					Logger.log("没有需要修改的数据");
				}
			   		 HashMap<String, String> param=new  HashMap<String, String>();
			      	  param.put("formType","GRJK");
			   		 param.put("formOption", formOption);
			   		 param.put("baseForm", JSONObject.toJSONString(form));	
			   		 param.put("costDetails", JSONObject.toJSONString(costDetails));
			 	    param.put("fileList", JSONObject.toJSONString(fileList));

			   	   	
//					 /*
			   		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			   			Logger.log("获取结果---->>>>"+result); 	
		}
		
		@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitPersonalBorrowForm1"})
//		@Test(description="审批人审批通过1流程结束")
			public void approvePersonalBorrowFormIsAgreen1(){
				Map<String,Object> variables=new HashMap<String, Object>();
				try {
//					通过formid或processid获取taskid   
					ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
					processTaskQuery.setBusinessId("GRJK201714290001");
				PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
				if(!processtaskdtos.getList().isEmpty()){
					String taskId=processtaskdtos.getList().get(0).getTaskId();		
					ProcessExecResult reslut=baseAgent.execTask(taskId, variables, "审批通过");
					Logger.log("status返回状态"+reslut.getStatus());
					Logger.log("status返回状态"+processtaskdtos);
					Logger.log("返回的审批记录----》》》"+processtaskdtos.getAmount()+"返回list"+processtaskdtos.getList());
					
					if(reslut.getStatus()==1){
						approvePersonalBorrowFormIsAgreen1();
					}else{
						Logger.log("已经审批通过");
						
					}
				
				}else{
					Logger.log("流程已经结束");
				}
				
				String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='GRJK201714290001'";
				int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
				if(reslut1>0){
					Logger.log("修复数据成功");
				}else{
					Logger.log("修复数据失败");
				}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		@Test(description="审批人加签给FJK",dependsOnMethods={"approvePersonalBorrowFormIsAgreen1"})
//		@Test(description="审批人加签给FJK")
			public void approvePersonalBorrowFormSignFJK(){
				String formidsql=" SELECT * FROM t_form WHERE  form_id='GRJK201714290001'";
				String formid=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_id");
				String formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
				if(StringUtils.isNotEmpty(formid)){
					if(formStatus.equals("1")||formStatus.equals("3")||formStatus.equals("8")){
						submitPersonalBorrowForm1();
					}else if(formStatus.equals("9")){
						String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='GRJK201714290001'";
						int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
						if(reslut1>0){
							Logger.log("修复数据成功");
							submitPersonalBorrowForm1();
						}else{
							Logger.log("修复数据失败");
						}
					}else if(formStatus.equals("2")){
				
						 try{
//								通过formid或processid获取taskid   GRJK201714290001
								ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
								processTaskQuery.setBusinessId("GRJK201714290001");
							PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
							if(!processtaskdtos.getList().isEmpty()){
								// 结束工作流流程
								String taskId=processtaskdtos.getList().get(0).getTaskId();		
								baseAgent.endProcess(taskId);		
								formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
								 Logger.log("formStatus状态--》"+formStatus);
							}else{
								Logger.log("流程已经结束");
							}
								
							}catch(Exception e){
							Logger.log("打印异常信息"+e.getMessage());	
							}
						 // 恢复财务系统订单状态初始化
						 String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='GRJK201714290001'";
							int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
							if(reslut1>0){
								Logger.log("修复数据成功");
								submitPersonalBorrowForm1();
							}else{
								Logger.log("修复数据失败");
							}
						
					}
				}else{
					Logger.log("GRJK201714290001是空数据---调用发起流程");
					
					submitPersonalBorrowForm1();
				}
				try{
				
//					通过formid或processid获取taskid   GRJK201714290001
					ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
					processTaskQuery.setBusinessId("GRJK201714290001");
				PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
				if(!processtaskdtos.getList().isEmpty()){
					String taskId=processtaskdtos.getList().get(0).getTaskId();	
					List<String> userIds=new ArrayList<String>();
					userIds.add("1816");
					ProcessExecResult reslut=baseAgent.signProcess(taskId, userIds, "加签给樊晶凯");
					Logger.log("reslut结果---》》》"+ reslut);
					Logger.log("reslut结果---》》》"+ reslut.getStatus());
					Logger.log("reslut结果---》》》"+ reslut.getProcessInstanceId());
					if(reslut.getStatus()==1){
						List<ProcessTaskOut> getProcessTaskList=reslut.getProcessTaskList();
						
						Logger.log("订单处理成功"+getProcessTaskList.get(0).getAssigneeName());
						Assert.assertEquals("樊晶凯", getProcessTaskList.get(0).getAssigneeName().toString().trim());
					}else{
						Logger.log("订单处理失败"+ reslut.getStatus());
					}
				}else{
					Logger.log("流程已经结束");
				}
					
				}catch(Exception e){
					Logger.log("打印异常信息"+e.getMessage());	
					}
			}

		@Test(description="审批人驳回给申请人",dependsOnMethods={"approvePersonalBorrowFormSignFJK"})
//		@Test(description="审批人驳回给申请人")
		public void approvePersonalBorrowFormIsReject1(){
			String formidsql=" SELECT * FROM t_form WHERE  form_id='GRJK201714290001'";
			String formid=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_id");
			String formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
			if(StringUtils.isNotEmpty(formid)){
				if(formStatus.equals("1")||formStatus.equals("3")||formStatus.equals("8")){
					submitPersonalBorrowForm1();
				}else if(formStatus.equals("9")){
					String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='GRJK201714290001'";
					int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
					if(reslut1>0){
						Logger.log("修复数据成功");
						submitPersonalBorrowForm1();
					}else{
						Logger.log("修复数据失败");
					}
					
				}else{
					Logger.log("订单状态是正确的无效进行修改"+formStatus);
				}
			}else{
				Logger.log("GRJK201714290001不存在");
				submitPersonalBorrowForm1();
			}
			try{
//				通过formid或processid获取taskid   GRJK201714290001
				ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
				processTaskQuery.setBusinessId("GRJK201714290001");
			PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
			if(!processtaskdtos.getList().isEmpty()){
				String taskId=processtaskdtos.getList().get(0).getTaskId();		
				baseAgent.endProcess(taskId);
				formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
				if(formStatus.equals("3")){
					Assert.assertEquals("0", "0");
				}else{
					Assert.assertEquals("1", "0");
				}
			}else{
				Logger.log("流程已经结束");
			}
				
			}catch(Exception e){
			Logger.log("打印异常信息"+e.getMessage());	
			}
		
		}
		
}
