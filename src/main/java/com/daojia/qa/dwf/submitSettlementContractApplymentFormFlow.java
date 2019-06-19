package com.daojia.qa.dwf;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.annotation.DSF;
import com.bj58.daojia.qa.base.BaseDsfTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.ContractDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetHead;
import com.daojia.workflow.entity.ProcessExecResult;
import com.daojia.workflow.entity.ProcessTaskDto;
import com.daojia.workflow.entity.query.ProcessTaskQuery;
import com.daojia.workflow.service.TaskOperateService;
import com.daojia.workflow.util.PageList;

@DSF(url="tcp://workflow/TaskServiceImpl")
public class submitSettlementContractApplymentFormFlow extends  BaseDsfTest<TaskOperateService>{
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
	    * 保存和提交框架结算单接口
	    * */
//	/*
	@Test( description="保存和提交框架结算单接口")
	public void submitSettlementContractApplymentForm(){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		form.setFormId("KJJS201802021133");//结算框架合同
		form.setFormType(14);
		form.setApplyDate("2018-02-05");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
//		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("1");
		form.setReceiptMoney("1.00");
		form.setCostDesc("描述信息");  // 合同内容
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
//		form.setPayStatus("0"); 支付状态不需要填写
//		form.setFormStatus("1");  没有订单状态
		form.setContractCategory("渠道商支出");  //合同类别
		form.setBusinessCategory("平台"); //业务类别
		form.setMoneyType("0");
		form.setState(0);
		form.setDepartmentCode("1-17-09-02");
	    form.setApplyFormId("ZCHT201802024412");//关联的框架合同
		List<ContractDetail> contractDetails=new ArrayList<ContractDetail>();
		ContractDetail contractdetail2=new ContractDetail();
		contractdetail2.setContractDate("2018-01-05");
		contractdetail2.setCostDetailType("010");
		contractdetail2.setIsRelationBudget(1);
		contractdetail2.setTaxUnitPrice("1");//含税单价
		contractdetail2.setNum(1);//数量
		contractdetail2.setBudgetMoney(new BigDecimal(8)); //金额
		contractdetail2.setBudgetStatus(0);		//预算是否有效
		contractdetail2.setTaxAmount(new BigDecimal(0));  //税金
		// 成本中心
		contractdetail2.setCostCenter("10.00.78.00.6");
		
		// 成本中心
			contractdetail2.setCostCenterName("技术中心413");
		//费用发生日期
		contractdetail2.setContractDate("2018-01");
		
		contractDetails.add(contractdetail2);
		String formOption="submit";
		 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
		 UploadFileDto file=new UploadFileDto();
		    file.setFileAddress("http://www.baidu.com");
			file.setFileName("filename1");
			file.setIsPic("0");
			fileList.add(file);
	
		
		HashMap<String, String> param=new  HashMap<String, String>();
		  param.put("baseForm",JSONObject.toJSONString(form));
		  param.put("formOption",formOption );
		  param.put("contractDetails", JSONObject.toJSONString(contractDetails));
		  param.put("fileList", JSONObject.toJSONString(fileList));
		  param.put("formType","KJJS");
		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
		 Logger.log("获取repData>>>>>>>>"+result);
		 
				 
}
	
	@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitSettlementContractApplymentForm"})
//	@Test(description="审批人审批通过1流程结束")
		public void approveTravelApplyFormIsAgreen1(){
			Map<String,Object> variables=new HashMap<String, Object>();
			try {
//				通过formid或processid获取taskid   
				ProcessTaskQuery processTaskQuery=new ProcessTaskQuery();
				processTaskQuery.setBusinessId(form.getFormId());
			PageList<ProcessTaskDto> processtaskdtos= baseAgent.getProcessTaskByQuery(processTaskQuery);
			if(!processtaskdtos.getList().isEmpty()){
				String taskId=processtaskdtos.getList().get(0).getTaskId();		
				ProcessExecResult reslut=baseAgent.execTask(taskId, variables, "审批通过");
				Logger.log("status返回状态"+reslut.getStatus());
				Logger.log("status返回状态"+processtaskdtos);
				Logger.log("返回的审批记录----》》》"+processtaskdtos.getAmount()+"返回list"+processtaskdtos.getList());
				
				if(reslut.getStatus()==1){
					approveTravelApplyFormIsAgreen1();
				}else{
					Logger.log("已经审批通过");
					
				}
			
			}else{
				Logger.log("流程已经结束");
			}
			
//			String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='ZCHT201802024412'";
//			int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
//			if(reslut1>0){
//				Logger.log("修复数据成功");
//			}else{
//				Logger.log("修复数据失败");
//			}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}
