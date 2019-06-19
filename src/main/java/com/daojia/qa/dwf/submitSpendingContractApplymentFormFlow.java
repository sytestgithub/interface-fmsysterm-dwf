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
import com.daojia.qa.entity.Contract;
import com.daojia.qa.entity.ContractDetail;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.TravelInfo;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.workflow.entity.ProcessExecResult;
import com.daojia.workflow.entity.ProcessTaskDto;
import com.daojia.workflow.entity.query.ProcessTaskQuery;
import com.daojia.workflow.service.TaskOperateService;
import com.daojia.workflow.util.PageList;

@DSF(url="tcp://workflow/TaskServiceImpl")
public class submitSpendingContractApplymentFormFlow extends  BaseDsfTest<TaskOperateService>{
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
	    * 保存和提交框架合同接口
	    * */
//	/*
		@Test( description="保存和提交框架合同接口")
		public void submitSpendingContractApplymentForm(){
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
			//
			  // 预算记录后 占用金额occupymoney  剩余金额 surplusmoney   
			  //预算sql
	
			 
			form.setFormId("ZCHT201802024414");//支出合同
			form.setFormType(11);
			form.setApplyDate("2018-02-05");
			form.setApplyPName("尚英");
			form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
//			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setApplyMoney("1");
			form.setReceiptMoney("1.00");
			form.setCostDesc("描述信息");  // 合同内容
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
//			form.setPayStatus("0"); 支付状态不需要填写
//			form.setFormStatus("1");  没有订单状态
		
				
		
			form.setMoneyType("0");
			form.setState(0);
			
			form.setDepartmentCode("1-17-09-02");  
			form.setContractCategory("渠道商支出");  //合同类别
			form.setBusinessCategory("平台"); //业务类别
			form.setContractNature("1");//合同性质  1支出类   2收入类  3合作类
			 form.setIsFrameContract("1"); // 1框架合同  0非框架合同 
			 form.setOurContractBody("北京五八到家信息技术有限公司"); //我方签约主体
	         form.setContractName("合同名称001");//合同名称
	         form.setCostDesc("合同内容"); //合同内容
	         
	 		String formOption="submit";
	 		List<Contract> contracts=new ArrayList<Contract>();
	 		Contract contract=new Contract();
	 		//合同开始时间
			contract.setContractStartTime("2018-02-05");
			//合同结束时间
			contract.setContractEndTime("2018-02-06");
			//对方签约主体
			contract.setOppositeContractBody("对方签约名称1");
			//发票类型
			contract.setInvoiceType("1");   //
			//税率
			contract.setTaxRate("3"); 
			//付款周期
			contract.setPaymentPeriod("一次性");
			//我方先盖章
			contract.setOurFirstStamp("1");
			//先票后款
			contract.setBeforeInvoiceOrMoney(0);
			//单位资质
			contract.setEnQualification("一般纳税人");
			//社会统一代码
			contract.setUniformCode("1统一代码4");
			//对方收款银行
			contract.setRecipientBank("招商银行");
			//收款银行全称
			contract.setRecipientBankFullName("招商亚运村支行");
			//对方收款账号
			contract.setRecipientBankAccount("2343904023923345");
			//发票开具项目
			contract.setInvoiceProject("发票开具项目001");
			contract.setContractAmount(new BigDecimal(2.00));
			contract.setDepositOtherAmount(new BigDecimal(1.00));
			contracts.add(contract);
			List<ContractDetail> contractDetails=new ArrayList<ContractDetail>();
			ContractDetail contractdetail=new ContractDetail();
//			contractDetails.add(contractdetail);
			List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
			    file.setFileAddress("http://www.baidu.com");
				file.setFileName("filename1");
				file.setIsPic("0");
				fileList.add(file);
			HashMap<String, String> param=new  HashMap<String, String>();
			  param.put("baseForm",JSONObject.toJSONString(form));
			  param.put("formOption",formOption );
			  param.put("contracts",JSONObject.toJSONString(contracts) );
			  param.put("contractDetails", JSONObject.toJSONString(contractDetails));
			  param.put("fileList", JSONObject.toJSONString(fileList));
			  param.put("formType","ZCHT");

				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
				 Logger.log("获取repData>>>>>>>>"+result);
				 
						 
		}
//		/*
		
		@Test(description="审批人审批通过1流程结束",dependsOnMethods={"submitSpendingContractApplymentForm"})
//		@Test(description="审批人审批通过1流程结束")
			public void approveTravelApplyFormIsAgreen1(){
				Map<String,Object> variables=new HashMap<String, Object>();
				try {
//					通过formid或processid获取taskid   
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
				
//				String updatesql="UPDATE t_form SET form_status=1 WHERE  form_id='ZCHT201802024412'";
//				int reslut1=GetDbMethod.updateSelectCondition( db,updatesql);
//				if(reslut1>0){
//					Logger.log("修复数据成功");
//				}else{
//					Logger.log("修复数据失败");
//				}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		
}
