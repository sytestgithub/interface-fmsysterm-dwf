package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class submitToPublicContractApplymentForm extends BaseTest<T>{
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
	    * 保存提交对公付款单
	    * */
	@Test(dataProvider = "submitToPublicContractApplymentFormParam", description="保存和提交结算订单接口")
	public void submitToPublicContractApplymentForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		String updatedqlformid="";
		Form form =new Form();
		if(name.equals("unframecontract")||name.equals("unframecontractnotcanbigcontractnomount")||name.equals("filelistisnull")){
			form.setFormId("DGFK201802021101");//非框架
		}else if(name.equals("framecontract")||name.equals("framecontractnotcanbigcontractnomount")){
			form.setFormId("DGFK201804020132");//框架
		}else if(name.equals("nocontract")){
			form.setFormId("DGFK201802023308");//无合同
		}

		form.setFormType(15);
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
		form.setInnerRemark("内部备注？");
		if(name.equals("MoneyTypeisnull")){	}else{
			form.setMoneyType("0");
			}
		form.setState(0);
		form.setDepartmentCode("1-17-09-02");
		if(name.equals("applyformidnull")){
		form.setApplyFormId("");
		}else if(name.equals("applyformidnotexit")){
			form.setApplyFormId("112");
		}else if(name.equals("unframecontract")||name.equals("unframecontractnotcanbigcontractnomount")||name.equals("filelistisnull")){
				form.setApplyFormId("ZCHT201802020015");
		}else if(name.equals("framecontract")||name.equals("framecontractnotcanbigcontractnomount")){
			form.setApplyFormId("KJJS201802021133");
	    }else if(name.equals("nocontract")){
			form.setApplyFormId("");
	    }
		
		if(name.equals("setPaymentTypeisnull")){
//			form.setPaymentType("");//1非框架 2框架 3无合同
		}else if(name.equals("unframecontract")||name.equals("unframecontractnotcanbigcontractnomount")||name.equals("filelistisnull")){
			form.setPaymentType("1");//1非框架 2框架 3无合同
		}else if(name.equals("framecontract")||name.equals("framecontractnotcanbigcontractnomount")){
			form.setPaymentType("2");//1非框架 2框架 3无合同
		}else if(name.equals("nocontract")){
			form.setPaymentType("3");//1非框架 2框架 3无合同
		}
		
		
		GetDbMethod.endProcess(form.getFormId()); // 结束流程
		
		String formOption="submit";
		List<ContractDetail> contractDetails=new ArrayList<ContractDetail>();

		ContractDetail contractdetail=new ContractDetail();
		ContractDetail contractdetail1=new ContractDetail();
		//费用发生日期
		contractdetail.setContractDate("2018-01");
		//费用明细类别
		contractdetail.setCostDetailType("010");
		contractdetail.setCostCenter("10.00.78.00.6");
		// 成本中心名称
		contractdetail.setCostCenterName("技术中心413");
		if(name.equals("unframecontract")||name.equals("unframecontractnotcanbigcontractnomount")||name.equals("filelistisnull")){
			
			//是否关联预算  非框架
			contractdetail.setIsRelationBudget(1);
			//未付金额  非框架
			contractdetail.setNoAmount(new BigDecimal(3.00));
			if(name.equals("unframecontractnotcanbigcontractnomount")){
				//本次付款金额
				contractdetail.setBudgetMoney(new BigDecimal(5.00));
			}else {
			//本次付款金额
			contractdetail.setBudgetMoney(new BigDecimal(1.00));
			}
			contractdetail.setContractDetailId("33621084190849");
			contractdetail.setStatus("3");
		}else if(name.equals("framecontract")||name.equals("framecontractnotcanbigcontractnomount")){
			//是否关联预算  框架
			contractdetail.setIsRelationBudget(1);
			//未付金额  框架
			contractdetail.setNoAmount(new BigDecimal(3));
			//本次付款金额
			if(name.equals("framecontractnotcanbigcontractnomount")){
				contractdetail.setBudgetMoney(new BigDecimal(7));
			}else{
			contractdetail.setBudgetMoney(new BigDecimal(1));
			}
			contractdetail.setContractDetailId("33621530642305");
			contractdetail.setStatus("3");
			
		}else if(name.equals("nocontract")){
			//是否关联预算  框架
			contractdetail.setIsRelationBudget(1);
			//本次付款金额
			contractdetail.setBudgetMoney(new BigDecimal(1.00));
			
			//是否关联预算  非框架
			contractdetail.setIsRelationBudget(1);
			//本次付款金额
			contractdetail.setBudgetMoney(new BigDecimal(1));
		}
	
		
		//税金
		contractdetail.setTaxAmount(new BigDecimal(1));

		//预算情况
//		contractdetail.setStatus("1");
		if(name.equals("nocontract")){
			//收款人  无合同付款
			contractdetail.setPayeeName("尚英1");
			//收款银行 无合同付款
			contractdetail.setRecipientBank("中国银行");
			//收款银行全称 无合同付款
			contractdetail.setRecipientBankFullName("中国亚运村银行");
			//收款账号  无合同付款
			contractdetail.setRecipientBankAccount("222233334444");
			
			
		}
		
		contractDetails.add(contractdetail);
		
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
		  param.put("formType","DGFK");
		  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
			 Logger.log("获取repData>>>>>>>>"+result);
			 
			 if(name.equals("unframecontract")||name.equals("framecontract")||name.equals("nocontract")||name.equals("filelistisnull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 }else if(name.equals("applyformidnotexit")||name.equals("setPaymentTypeisnull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"没有获取到formId");
			 }else if(name.equals("applyformidnull")||name.equals("MoneyTypeisnull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"没有获取到formId");
			 }else if(name.equals("unframecontractnotcanbigcontractnomount")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用发生期间[2018-01],费用明细类别[差旅费]本次付款金额不能大于未付金额");
			 }
		
	}
	

	@DataProvider
    public static Object[][] submitToPublicContractApplymentFormParam() {
        return new Object[][]{ 
//        		{"unframecontract"},{"framecontract"},{"nocontract"},
        		//{"unframecontractnotcanbigcontractnomount"},
//        		{"filelistisnull"},
//        		{"unframenorelationbudget"},{"unframerelationbudget"},
//        		{"framenorelationbudget"},{"framerelationbudget"},
        //{"unframediffrelation"},{"framediffrelation"},
//        		{"setPaymentTypeisnull"},
//        		{"applyformidnull"},{"applyformidnotexit"},{"MoneyTypeisnull"},
        		{"filelistisnull"}
        		
        };
        }
}
