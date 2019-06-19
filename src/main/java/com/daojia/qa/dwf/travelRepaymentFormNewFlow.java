package com.daojia.qa.dwf;

import java.math.BigDecimal;
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
import com.daojia.qa.entity.CostOffset;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.TravelInfo;
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
public class travelRepaymentFormNewFlow extends  BaseDsfTest<TaskOperateService>{
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
	    * 保存和提交差旅报销单接口
	    * */
//	/*
		@Test( description="保存和提交差旅报销单接口")
	   	public void submitTravelRepaymentForm1(){
			String updatedqlformid="";
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//			method_url="http://10.253.7.168:80/v2/formsubmit";
//			form.setFormId("CLBX201713140001");
			form.setFormId("CLBX201714290008");
			form.setFormType(2);
			form.setApplyDate("2017-12-13");
			form.setApplyPName("尚英");
			form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setInnerRemark("abc");
			form.setApplyMoney("10");
			form.setReceiptMoney("0");
			form.setCostDesc("test");
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
			form.setApproveMoney(new BigDecimal(0));
			form.setPayStatus("0");
//			form.setFormStatus("0");
			form.setFormStatus("8");
			form.setMoneyType("0");
			form.setState(0);
			form.setApplyUserId("4011");
			form.setDepartmentCode("1-17-09-02");
//			form.setApplyFormId("CLSQ201710165692");
			
			form.setApplyFormId("CLSQ201712160122");//申请单和报销的成本中心不一样的
			
			String applyformidupdate="UPDATE t_form SET apply_form_id='CLSQ1111111111' WHERE apply_form_id='CLSQ201712160122' AND form_status IN('2','9')";
			
			int apply_form_id1=GetDbMethod.updateSelectCondition(db, applyformidupdate);
			if(apply_form_id1>0){
				
				Logger.log("已经成功修改了数据库中跟CLSQ201712160122管理的差旅报销单"+apply_form_id1);
			}else{
				Logger.log("不存在数据库中跟CLSQ201712160122管理的差旅报销单"+apply_form_id1);
			}
			
			CostDetail costdetailstay=new CostDetail();
			costdetailstay.setCostHappenDate("2017-11-25");
			costdetailstay.setStartDate("2017-11-25");
			costdetailstay.setEndDate("2017-11-26");
			costdetailstay.setTravelDays(1);
	   		costdetailstay.setCostDetailType("010");
	   		costdetailstay.setTravelCity("北京");
	   		costdetailstay.setStayStandard("10");
	   		costdetailstay.setInvoiceType("0");
	   		costdetailstay.setAmount("10");
	   		costdetailstay.setBudgetStatus("0");
	   		costdetailstay.setCostdetailDiv("1");
	   		costdetailstay.setTaxRate("3");
	   		
	   		CostDetail costdetailtraffic=new CostDetail();
	   		costdetailtraffic.setCostHappenDate("2017-10-26");
	   		costdetailtraffic.setStartCity("北京市");
	   		costdetailtraffic.setEndCity("天津市");
	   		costdetailtraffic.setTrafficTool("火车");
	   		costdetailtraffic.setAmount("1");
	   		
	   		costdetailtraffic.setTrafficTool("火车");
	   		costdetailtraffic.setCostDetailType("010");
	   		costdetailtraffic.setBudgetStatus("0");
	   		costdetailtraffic.setCostdetailDiv("2");
	   		
	   		
	   		CostDetail costdetailother=new CostDetail();
	   		costdetailother.setCostHappenDate("2017-10-25");
	   	 	costdetailother.setAmount("1");
	   	 costdetailother.setCostDetailType("010");
	   		costdetailother.setBudgetStatus("0");
	   		costdetailother.setCostdetailDiv("3");
	   		

	   		CostDetail costDetailsAllowance =new CostDetail();
			costDetailsAllowance.setCostHappenDate("2017-10-25");
			costDetailsAllowance.setTravelCity("天津市");
			costDetailsAllowance.setCostdetailDiv("4");
			costDetailsAllowance.setTravelDays(1);
			costDetailsAllowance.setAmount("10");
			costDetailsAllowance.setBudgetStatus("0");
	   		costDetailsAllowance.setCostDetailType("010");

	   		TravelInfo travelInfo1=new TravelInfo();
	   	
			  travelInfo1.setStartDate("2017-11-15");
			 travelInfo1.setEndDate("2017-11-16");
			 travelInfo1.setStartCity("北京");
			 travelInfo1.setEndCity("天津");
			  travelInfo1.setBudgetStatus("0");
				  travelInfo1.setStayStandard(new BigDecimal(1));
			  travelInfo1.setAllowanceAmount(new BigDecimal(0));
			  travelInfo1.setOtherAmount(new BigDecimal(0));
			  travelInfo1.setTravelDays(2);
			  travelInfo1.setCostDetailType("010");
			  travelInfo1.setStayAmount(new BigDecimal(1));
			  travelInfo1.setTotalAmount(new BigDecimal(1));
			  travelInfo1.setOtherAmount(new BigDecimal(1));
			  List<CostOffset> costOffsets = new ArrayList<CostOffset>();
			  List<TravelInfo> travelInfos1=new ArrayList<TravelInfo>();
			  travelInfos1.add(travelInfo1);
			  List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
			  applyCostDetailsStay.add(costdetailstay);
				List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
				applyCostDetailsTraffic.add(costdetailtraffic);
				List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
				applyCostDetailsOther.add(costdetailother);
				 List<CostDetail> costDetailsAllowances = new ArrayList<CostDetail>();
					costDetailsAllowances.add(costDetailsAllowance);
				 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
				 UploadFileDto file=new UploadFileDto();
				
					file.setFileAddress("http://www.baidu.com");
					file.setFileName("filename1");
					file.setIsPic("0");
					fileList.add(file);
					String formOption="submit";
					
					 HashMap<String, String> param=new  HashMap<String, String>();
			   		 param.put("formOption", formOption);
			   		 param.put("baseForm", JSONObject.toJSONString(form));		 
			   		 param.put("travelInfos", JSONObject.toJSONString(travelInfos1));
			   		 param.put("costDetailsStays", JSONObject.toJSONString(applyCostDetailsStay));
			   		 param.put("costDetailsTraffics", JSONObject.toJSONString(applyCostDetailsTraffic));
			   		 param.put("costDetailsOthers", JSONObject.toJSONString(applyCostDetailsOther));
			   		 param.put("costOffsets", JSONObject.toJSONString(costOffsets));
			   		 param.put("costDetailsAllowance", JSONObject.toJSONString(costDetailsAllowances));
			   	    param.put("fileList", JSONObject.toJSONString(fileList));
			   	  param.put("formType","CLBX");
			   	  

//				 /*
			   	 GetDbMethod.endProcess( db,form.getFormId());
			   			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			   			Logger.log("获取结果---->>>>"+result);
			   			

		}
		
		
		@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitTravelRepaymentForm1"})
//		@Test(description="审批人审批通过1流程结束")
			public void approveTravelRepaymentFormIsAgreen1(){
				Map<String,Object> variables=new HashMap<String, Object>();
				try {
//					通过formid或processid获取taskid   
					ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
					processTaskQuery.setBusinessId("CLBX201714290008");
				PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
				if(!processtaskdtos.getList().isEmpty()){
					String taskId=processtaskdtos.getList().get(0).getTaskId();		
					ProcessExecResult reslut=baseAgent.execTask(taskId, variables, "审批通过");
					Logger.log("status返回状态"+reslut.getStatus());
					Logger.log("status返回状态"+processtaskdtos);
					Logger.log("返回的审批记录----》》》"+processtaskdtos.getAmount()+"返回list"+processtaskdtos.getList());
					
					if(reslut.getStatus()==1){
						approveTravelRepaymentFormIsAgreen1();
					}else{
						Logger.log("已经审批通过");
						
					}
				
				}else{
					Logger.log("流程已经结束");
				}
				
				String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='CLBX201714290008'";
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
		
		@Test(description="审批人加签给FJK",dependsOnMethods={"approveTravelRepaymentFormIsAgreen1"})
//		@Test(description="审批人加签给FJK")
			public void approveTravelRepaymentFormSignFJK(){
				String formidsql=" SELECT * FROM t_form WHERE  form_id='CLBX201714290008'";
				String formid=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_id");
				String formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
				if(StringUtils.isNotEmpty(formid)){
					if(formStatus.equals("1")||formStatus.equals("3")||formStatus.equals("8")){
						submitTravelRepaymentForm1();
					}else if(formStatus.equals("9")){
						String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='CLBX201714290008'";
						int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
						if(reslut1>0){
							Logger.log("修复数据成功");
							submitTravelRepaymentForm1();
						}else{
							Logger.log("修复数据失败");
						}
					}else if(formStatus.equals("2")){
				
						 try{
//								通过formid或processid获取taskid   CLBX201714290008
								ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
								processTaskQuery.setBusinessId("CLBX201714290008");
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
						 String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='CLBX201714290008'";
							int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
							if(reslut1>0){
								Logger.log("修复数据成功");
								submitTravelRepaymentForm1();
							}else{
								Logger.log("修复数据失败");
							}
						
					}
				}else{
					Logger.log("CLBX201714290008是空数据---调用发起流程");
					
					submitTravelRepaymentForm1();
				}
				try{
				
//					通过formid或processid获取taskid   CLBX201714290008
					ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
					processTaskQuery.setBusinessId("CLBX201714290008");
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
			
		
		@Test(description="审批人驳回给申请人",dependsOnMethods={"approveTravelRepaymentFormSignFJK"})
//		@Test(description="审批人驳回给申请人")
		public void approveTravelRepaymentFormIsReject1(){
			String formidsql=" SELECT * FROM t_form WHERE  form_id='CLBX201714290008'";
			String formid=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_id");
			String formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
			if(StringUtils.isNotEmpty(formid)){
				if(formStatus.equals("1")||formStatus.equals("3")||formStatus.equals("8")){
					submitTravelRepaymentForm1();
				}else if(formStatus.equals("9")){
					String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='CLBX201714290008'";
					int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
					if(reslut1>0){
						Logger.log("修复数据成功");
						submitTravelRepaymentForm1();
					}else{
						Logger.log("修复数据失败");
					}
					
				}else{
					Logger.log("订单状态是正确的无效进行修改"+formStatus);
				}
			}else{
				Logger.log("CLBX201714290008不存在");
				submitTravelRepaymentForm1();
			}
			try{
//				通过formid或processid获取taskid   CLBX201714290008
				ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
				processTaskQuery.setBusinessId("CLBX201714290008");
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
