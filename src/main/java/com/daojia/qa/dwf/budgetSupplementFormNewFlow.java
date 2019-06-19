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
public class budgetSupplementFormNewFlow extends  BaseDsfTest<TaskOperateService>{
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
	    * 保存和提交预算增补单接口
	    * */
//	/*
		@Test( description="保存和提交预算增补单接口")
		public void submitBudegtSupplementForm1(){
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//			method_url="http://fmsystem205.djtest.cn/v2/submit/budegtsupplementform";
			Form form =new Form();
			form.setFormId("YSZB201714270000");//YSZB201711270000
			form.setFormType(9);
			form.setApplyDate("2017-11-27");
			form.setApplyPName("尚英");
			form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setApplyMoney("1");
//			form.setApproveMoney(new BigDecimal(1.00)); 批复金额用不上
			form.setReceiptMoney("1.00");
			form.setCostDesc("描述信息");
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
//			form.setPayStatus("0"); 支付状态不需要填写
//			form.setFormStatus("1");  没有订单状态
			form.setMoneyType("0");
			form.setState(0);
			form.setDepartmentCode("1-17-09-02");
			CostDetail cost = new CostDetail();
			List<CostDetail> costDetails = new ArrayList<CostDetail>();
			cost.setBudgetMonth("2017-11");
//			cost.setCostHappenDate("2017-11-15");
            cost.setCostCenter("10.00.78.00.6");
			cost.setCostDetailType("010");
			cost.setBudgetStatus("0");
			cost.setAmount("1");
			cost.setCostdetailDiv("3");
	
			 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
			String formOption="submit";
		
			 file.setFileAddress("http://www.baidu.com");
				file.setFileName("filename1");
				file.setIsPic("0");
				CostDetail cost1 = new CostDetail();
				cost1.setAmount("2");
				cost1.setBudgetMonth("2017-11");
//				cost.setCostHappenDate("2017-11-15");
	            cost1.setCostCenter("10.00.78.00.6");

				cost1.setCostDetailType("010");
				cost1.setBudgetStatus("0");
				cost1.setCostdetailDiv("3");
				 costDetails.add(cost);
				 costDetails.add(cost1);
				 fileList.add(file);
				  HashMap<String, String> param=new  HashMap<String, String>();
				  param.put("baseForm",JSONObject.toJSONString(form));
				  param.put("formOption",formOption );
				  param.put("costDetails",JSONObject.toJSONString(costDetails) );
				  param.put("fileList", JSONObject.toJSONString(fileList));  
				  param.put("formType","YSZB");
				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
				 Logger.log("获取repData>>>>>>>>"+result);
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
		}
			 
		@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitBudegtSupplementForm1"})
//		@Test(description="审批人审批通过1流程结束")
			public void approveBudegtSupplementFormIsAgreen1(){
				Map<String,Object> variables=new HashMap<String, Object>();
				try {
//					通过formid或processid获取taskid   
					ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
					processTaskQuery.setBusinessId("YSZB201714270000");
				PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
				if(!processtaskdtos.getList().isEmpty()){
					String taskId=processtaskdtos.getList().get(0).getTaskId();		
					ProcessExecResult reslut=baseAgent.execTask(taskId, variables, "审批通过");
					Logger.log("status返回状态"+reslut.getStatus());
					Logger.log("status返回状态"+processtaskdtos);
					Logger.log("返回的审批记录----》》》"+processtaskdtos.getAmount()+"返回list"+processtaskdtos.getList());
					
					if(reslut.getStatus()==1){
						approveBudegtSupplementFormIsAgreen1();
					}else{
						Logger.log("已经审批通过");
						
					}
				
				}else{
					Logger.log("流程已经结束");
				}
				
				String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='YSZB201714270000'";
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
		
		
		@Test(description="审批人加签给FJK",dependsOnMethods={"approveBudegtSupplementFormIsAgreen1"})
//		@Test(description="审批人加签给FJK")
			public void approveBudegtSupplementFormSignFJK(){
				String formidsql=" SELECT * FROM t_form WHERE  form_id='YSZB201714270000'";
				String formid=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_id");
				String formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
				if(StringUtils.isNotEmpty(formid)){
					if(formStatus.equals("1")||formStatus.equals("3")||formStatus.equals("8")){
						submitBudegtSupplementForm1();
					}else if(formStatus.equals("9")){
						String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='YSZB201714270000'";
						int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
						if(reslut1>0){
							Logger.log("修复数据成功");
							submitBudegtSupplementForm1();
						}else{
							Logger.log("修复数据失败");
						}
					}else if(formStatus.equals("2")){
				
						 try{
//								通过formid或processid获取taskid   YSZB201714270000
								ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
								processTaskQuery.setBusinessId("YSZB201714270000");
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
						 String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='YSZB201714270000'";
							int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
							if(reslut1>0){
								Logger.log("修复数据成功");
								submitBudegtSupplementForm1();
							}else{
								Logger.log("修复数据失败");
							}
						
					}
				}else{
					Logger.log("YSZB201714270000是空数据---调用发起流程");
					
					submitBudegtSupplementForm1();
				}
				try{
				
//					通过formid或processid获取taskid   YSZB201714270000
					ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
					processTaskQuery.setBusinessId("YSZB201714270000");
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
			
		
		@Test(description="审批人驳回给申请人",dependsOnMethods={"approveBudegtSupplementFormSignFJK"})
//		@Test(description="审批人驳回给申请人")
		public void approveBudegtSupplementFormIsReject1(){
			String formidsql=" SELECT * FROM t_form WHERE  form_id='YSZB201714270000'";
			String formid=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_id");
			String formStatus=GetDbMethod.getCostDetailSelctTest(db, formidsql, "form_status");
			if(StringUtils.isNotEmpty(formid)){
				if(formStatus.equals("1")||formStatus.equals("3")||formStatus.equals("8")){
					submitBudegtSupplementForm1();
				}else if(formStatus.equals("9")){
					String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='YSZB201714270000'";
					int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
					if(reslut1>0){
						Logger.log("修复数据成功");
						submitBudegtSupplementForm1();
					}else{
						Logger.log("修复数据失败");
					}
					
				}else{
					Logger.log("订单状态是正确的无效进行修改"+formStatus);
				}
			}else{
				Logger.log("YSZB201714270000不存在");
				submitBudegtSupplementForm1();
			}
			try{
//				通过formid或processid获取taskid   YSZB201714270000
				ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
				processTaskQuery.setBusinessId("YSZB201714270000");
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
