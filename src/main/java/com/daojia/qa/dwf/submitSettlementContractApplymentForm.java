package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.ContractDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitSettlementContractApplymentForm  extends BaseTest<T>{
	String method_url="";
	DBUtil db;
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
	    if(db!=null){//断开数据库连接
	        db.closeConnection();
	    }else{
	    	Logger.log("数据库已经关闭");
	    }
	}
	/*
	    * 保存框架结算单接口
	    * */
    
@Test(dataProvider = "submitSettlementContractApplymentFormParam", description="保存和提交结算订单接口")
public void submitSettlementContractApplymentForm1(String name){
	method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
	String updatedqlformid="";
	Form form =new Form();
	 
	form.setFormId("KJJS201802021115");//结算框架合同
	form.setFormType(14);
	form.setApplyDate("2018-02-05");
	form.setApplyPName("尚英");
	form.setApplyPJobnumber("4021");
	form.setEmpDepartment("信息技术质量部");
//	form.setCostCenter("10.00.78.00.6");
	form.setEmpPTel("18201137136");
	form.setPayCompany("北京五八到家信息技术有限公司");
	form.setApplyMoney("1");
	form.setReceiptMoney("1.00");
	form.setCostDesc("描述信息");  // 合同内容
	form.setPayeeName("尚英");
	form.setReceiveBankName("招商银行");
	form.setReceiveBankAccount("6214830104619118");
//	form.setPayStatus("0"); 支付状态不需要填写
//	form.setFormStatus("1");  没有订单状态
	form.setContractCategory("渠道商支出");  //合同类别
	form.setBusinessCategory("平台"); //业务类别
	if(name.equals("MoneyTypeisnull")){	}else{
		form.setMoneyType("0");
		}
	form.setState(0);
	form.setDepartmentCode("1-17-09-02");
    form.setApplyFormId("ZCHT201802024413");//关联的框架合同
    
	GetDbMethod.endProcess(form.getFormId()); // 结束流程
    
	List<ContractDetail> contractDetails=new ArrayList<ContractDetail>();
	ContractDetail contractdetail1=new ContractDetail();
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
	contractdetail1.setContractDate("2018-01");
	if(name.equals("budgetdiffsuper")){
		//费用发生日期
		contractdetail1.setContractDate("2016-01");
	}
	//费用明细类别
	contractdetail1.setCostDetailType("010");
	//是否关联预算
	if(name.equals("setIsRelationBudgetnull")){}else if (name.equals("norelationbudget")){
		contractdetail1.setIsRelationBudget(0);  //0 是不关联预算
	}else{
		contractdetail1.setIsRelationBudget(1);  //1 是关联预算
	}
	//含税单价
	if(name.equals("Numnotcannull")){
		
	}else if(name.equals("Numcanzero")){
		contractdetail1.setNum(0);//含税单价
	}else{
		contractdetail1.setNum(1);//数量
	}
	

	//数量
	if(name.equals("setTaxUnitPricenotcannull")){
		
	}else if(name.equals("setTaxUnitPricecanzero")){
		contractdetail1.setTaxUnitPrice("0");//含税单价
	}else{
	
		contractdetail1.setTaxUnitPrice("1");//含税单价
	}
	
	if(name.equals("detailmoneynotcanbigcontractmoney")){
		contractdetail1.setTaxUnitPrice("1");//含税单价
		contractdetail1.setNum(8);//数量
	}else if(name.equals("detailmonecanequalcontractmoney")){
		contractdetail1.setTaxUnitPrice("1");//含税单价
		contractdetail1.setNum(3);//数量
		
		
		
		
	}else if(name.equals("detailmonecansmallcontractmoney")){
		contractdetail1.setTaxUnitPrice("1");//含税单价
		contractdetail1.setNum(1);//数量
	}
	
	contractdetail1.setBudgetMoney(new BigDecimal(8)); //金额
	contractdetail1.setBudgetStatus(0);		//预算是否有效
	if(name.equals("setTaxAmountnotcannull")){
		
	}else if(name.equals("setTaxAmountcanzero")){
		contractdetail1.setTaxAmount(new BigDecimal(0));  //税金
	}else{
		contractdetail1.setTaxAmount(new BigDecimal(8));  //税金	
	}

	
	// 成本中心
	contractdetail1.setCostCenter("10.00.78.00.6");
				// 成本中心名称
	contractdetail1.setCostCenterName("技术中心413");
	
	
	contractDetails.add(contractdetail1);
	
	 if(name.equals("Numcanzero")||name.equals("setTaxUnitPricecanzero")){
		 contractDetails.add(contractdetail2);
	 }else if(name.equals("budgetdiff")){
		 contractdetail2.setContractDate("2022-01");
			//费用明细类别
			contractdetail2.setCostDetailType("011");
		 contractdetail2.setIsRelationBudget(0);
			// 成本中心
			contractdetail2.setCostCenter("10.00.78.00.6");
						// 成本中心名称
			contractdetail2.setCostCenterName("技术中心413");
		 contractDetails.add(contractdetail2);
	 }
	
	String formOption="submit";
	 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
	 UploadFileDto file=new UploadFileDto();
	    file.setFileAddress("http://www.baidu.com");
		file.setFileName("filename1");
		file.setIsPic("0");
		if(name.equals("filelistisnull")){
			
		}else{
		fileList.add(file);
		}
	 
	HashMap<String, String> param=new  HashMap<String, String>();
	  param.put("baseForm",JSONObject.toJSONString(form));
	  param.put("formOption",formOption );
	  param.put("contractDetails", JSONObject.toJSONString(contractDetails));
	  param.put("fileList", JSONObject.toJSONString(fileList));
	  param.put("formType","KJJS");

		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
	
		 Logger.log("获取repData>>>>>>>>"+result);
		 
		 if(name.equals("Numnotcannull")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"合同金额必须大于0");
		 }else if(name.equals("setTaxUnitPricenotcannull")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"含税单价不能为空");
			 
		 }else if(name.equals("setTaxAmountnotcannull")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"税金不能为空");
			 
		 }else if(name.equals("detailmoneynotcanbigcontractmoney")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"金额明细合计必须小于等于合同未出订单金额");
		 }else if(name.equals("normalvalue")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
				String sqlofformid="SELECT * FROM t_form WHERE  form_id='"+form.getApplyFormId()+"'";	
				 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
					resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofformid,"wait_amount");
			 Assert.assertEquals(resultparambacks.get("wait_amount").toString().trim(),"3.00");
			 String sqlofcontract="SELECT * FROM t_contract WHERE  form_id='"+form.getApplyFormId()+"'";
			 Map<String, Object>  resultparambacks1=new HashMap<String, Object>();
				resultparambacks1=GetDbMethod.getCostDetailSelctTestList(db, sqlofcontract,"wait_amount");
			 Assert.assertEquals(resultparambacks1.get("wait_amount").toString().trim(),"3.00");	
			 String sqlofformkjjs="SELECT * FROM t_form WHERE  form_id='"+form.getFormId()+"'";
			 Map<String, Object>  resultparambacks2=new HashMap<String, Object>();
				resultparambacks2=GetDbMethod.getCostDetailSelctTestList(db, sqlofformkjjs,"no_amount");
			 Assert.assertEquals(resultparambacks2.get("no_amount").toString().trim(),"1.00");	
			 
			 
		 }else if(name.equals("filelistisnull")||name.equals("detailmonecanequalcontractmoney")
				 ||name.equals("detailmonecansmallcontractmoney")||name.equals("Numcanzero")
				 ||name.equals("setTaxUnitPricecanzero")||name.equals("setTaxUnitPricecanzero")
				 ||name.equals("setIsRelationBudgetnull")||name.equals("setTaxAmountcanzero")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
		 }else if(name.equals("norelationbudget")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 String sqlofcontractdetailkjjs="SELECT * FROM t_contract_detail WHERE  form_id='"+form.getFormId()+"'";
			 Map<String, Object>  resultparambacks2=new HashMap<String, Object>();
				resultparambacks2=GetDbMethod.getCostDetailSelctTestList(db, sqlofcontractdetailkjjs,"is_relation_budget");
			 Assert.assertEquals(resultparambacks2.get("is_relation_budget").toString().trim(),"0");
			 Map<String, Object>  resultparambacksstatus=new HashMap<String, Object>();
			 resultparambacksstatus=GetDbMethod.getCostDetailSelctTestList(db, sqlofcontractdetailkjjs,"status");
			 Assert.assertEquals(resultparambacksstatus.get("status").toString().trim(),"1");
			 
			 
		 }else if(name.equals("relationbudget")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 String sqlofcontractdetailkjjs="SELECT * FROM t_contract_detail WHERE  form_id='"+form.getFormId()+"'";
			 Map<String, Object>  resultparambacks2=new HashMap<String, Object>();
				resultparambacks2=GetDbMethod.getCostDetailSelctTestList(db, sqlofcontractdetailkjjs,"is_relation_budget");
			 Assert.assertEquals(resultparambacks2.get("is_relation_budget").toString().trim(),"1");
			 Map<String, Object>  resultparambacksstatus=new HashMap<String, Object>();
			 resultparambacksstatus=GetDbMethod.getCostDetailSelctTestList(db, sqlofcontractdetailkjjs,"status");
			 Assert.assertEquals(resultparambacksstatus.get("status").toString().trim(),"3");
		 }else if(name.equals("budgetdiff")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 String sqlofcontractdetailkjjs="SELECT * FROM t_contract_detail WHERE  form_id='"+form.getFormId()+"' ORDER BY sort ASC";
			 List<Map<String, Object>>  resultparambacks1=new ArrayList<Map<String,Object>>();
			 if(contractDetails.size()>0){
				 
					 resultparambacks1=GetDbMethod.getCostDetailSelctTestListSize1(db, sqlofcontractdetailkjjs);
					 Assert.assertEquals(resultparambacks1.get(0).get("status").toString().trim(), "3");
					 Assert.assertEquals(resultparambacks1.get(1).get("status").toString().trim(), "1");
				 
		
			 }
		 }else if(name.equals("budgetdiffsuper")){
			 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"2016-01差旅费预算不足");
		 }
    }

@DataProvider
public static Object[][] submitSettlementContractApplymentFormParam() {
    return new Object[][]{
//    		{"normalvalue"},{"filelistisnull"},
//    		{"detailmoneynotcanbigcontractmoney"},{"detailmonecanequalcontractmoney"},
//    		{"detailmonecansmallcontractmoney"},
//    		{"setTaxUnitPricenotcannull"},{"setTaxUnitPricecanzero"}
//    		{"setIsRelationBudgetnull"},
//    		{"setTaxAmountnotcannull"},{"setTaxAmountcanzero"},
//    		{"Numnotcannull"},{"Numcanzero"},
//    		{"norelationbudget"},{"relationbudget"},{"budgetdiff"}
//    		,{"budgetdiffsuper"}
    		{"normalvalue"}
    };
}
}
