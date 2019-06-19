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
import com.daojia.qa.entity.Contract;
import com.daojia.qa.entity.ContractDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitSpendingContractApplymentForm  extends BaseTest<T>{
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
	    * 保存支出合同单接口
	    * */
	@Test(dataProvider = "submitSpendingContractApplymentFormParam", description="保存和提交支出合同接口")
	public void submitSpendingContractApplymentForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//		method_url="http://10.253.7.168:80/v2/formsubmit"; // 亚超
		String updatedqlformid="";
		Form form =new Form();
		 
		form.setFormId("ZCHT201802024414");//支出合同
		form.setFormType(11);
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
		if(name.equals("MoneyTypeisnull")){
			
		}else{
		form.setMoneyType("0");
		}
		form.setState(0);
		
		form.setDepartmentCode("1-17-09-02");  
		form.setContractCategory("渠道商支出");  //合同类别
		form.setBusinessCategory("平台"); //业务类别
		form.setContractNature("1");//合同性质  1支出类   2收入类  3合作类
		 //我方
         if(name.equals("framenotcannull")){
        	 
         }else if(name.equals("unframeallvaluesisright")||name.equals("norelationbudget")
        			||name.equals("nextyear")||name.equals("beforeyear")
        			||name.equals("relationbudgetnormal")||name.equals("relationbudgetsuperbudget")
        			||name.equals("relationbudgetmoredetailnormal")||name.equals("relationbudgetmoredetailsuperbudget")){
        	 form.setFormId("ZCHT201802020015");//ZCHT2018020200022
        	 form.setIsFrameContract("0"); // 1框架合同  0非框架合同 
         }else{
        	 form.setIsFrameContract("1"); // 1框架合同  0非框架合同 
         }
         form.setOurContractBody("北京五八到家信息技术有限公司"); //我方签约主体
         form.setContractName("合同名称001");//合同名称
         form.setCostDesc("合同内容"); //合同内容
//         form.setContractCategory("渠道商支出");//合同类别
		GetDbMethod.endProcess(form.getFormId()); // 结束流程
		
		// formid 审批中、审批完毕
		  String sqlofformidprocess=" SELECT * FROM t_form WHERE form_type='11' AND form_status=2";
		  String sqlofformidend=" SELECT * FROM t_form WHERE form_type='11' AND form_status=9";
		  
		  if(name.equals("processformidnotcansubmit")){
			 form.setFormId( GetDbMethod.getCostDetailSelctTest(db, sqlofformidprocess, "form_id").toString().trim());
		  }else if(name.equals("endformidnotcansubmit")){
			  form.setFormId(  GetDbMethod.getCostDetailSelctTest(db, sqlofformidend, "form_id").toString().trim());
		  }
		  
		  
		  
		String formOption="submit";
		List<Contract> contracts=new ArrayList<Contract>();
		Contract contract=new Contract();
		// 基础信息需要的
//		contract.setContractNature("1");//合同性质  1支出类   2收入类  3合作类
//		contract.setContractCategory("渠道商支出");//合同类别
//		contract.setIsFrameContract("1");// 是否框架合同   0是非框架合同  1是框架合同    这里设置不作用
//		contract                              //业务类别
		//我方签约主体
//		contract.setOurContractBody("北京五八到家信息技术有限公司");
		//合同名称
//		contract.setContractName("合同名称001");
		//合同内容
//		contract.setCostDesc("合同内容");
		//// 合同明细
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
		//合同总金额
		if(name.equals("contractmountbigzero")){
			contract.setContractAmount(new BigDecimal(0.00));
		}else{
		contract.setContractAmount(new BigDecimal(2.00));
		}
		//押金及其他  othermountequalzero
		if(name.equals("othermountequalzero")){
			contract.setDepositOtherAmount(new BigDecimal(0.00));
		}else{
			contract.setDepositOtherAmount(new BigDecimal(1.00));
		}
	
		// 设置总金+押金
		
		if(name.equals("relationbudgetsuperbudget")){
			contract.setContractAmount(new BigDecimal(1000000));
			contract.setDepositOtherAmount(new BigDecimal(0.00));
		}else if(name.equals("relationbudgetmoredetailsuperbudget")){
			contract.setContractAmount(new BigDecimal(1000000));
			contract.setDepositOtherAmount(new BigDecimal(1.00));
		}
		
	if(name.equals("framecontractsnotcannull")){
			
		}else{
			contracts.add(contract);
		}
		
		List<ContractDetail> contractDetails=new ArrayList<ContractDetail>();
		ContractDetail contractdetail=new ContractDetail();
		//多条明细
		ContractDetail contractdetail1=new ContractDetail();
		//费用发生日期
		if(name.equals("nextyear")){
			contractdetail.setContractDate("2019-01");
		}else if(name.equals("beforeyear")){
			contractdetail.setContractDate("2017-12");
		}else{
			contractdetail.setContractDate("2018-01");
		}
		//费用明细类别
		contractdetail.setCostDetailType("010");
		//是否关联预算
		if(name.equals("norelationbudget")){
			contractdetail.setIsRelationBudget(0);  //1 是关联预算
		}else if(name.equals("")){
			
		}else{
			contractdetail.setIsRelationBudget(1);  //1 是关联预算
		}
		
		//金额
		if(name.equals("unframecontractmountequaldetailamount")){
			contractdetail.setBudgetMoney(new BigDecimal(1.00));
		}else if(name.equals("unframeallvaluesisright")){
			contractdetail.setBudgetMoney(new BigDecimal(3.00));
		}else if(name.equals("relationbudgetsuperbudget")){
			contractdetail.setBudgetMoney(new BigDecimal(1000000));
		}else if(name.equals("relationbudgetnormal")){
			contractdetail.setBudgetMoney(new BigDecimal(3));
		}else if(name.equals("relationbudgetmoredetailnormal")||name.equals("relationbudgetmoredetailsuperbudget")){
			contractdetail.setBudgetMoney(new BigDecimal(1.00));
		
			//费用发生日期
			contractdetail1.setContractDate("2018-01");
			//费用明细类别
			contractdetail1.setCostDetailType("010");
			//是否关联预算
			contractdetail1.setIsRelationBudget(1);  //1 是关联预算
			if(name.equals("relationbudgetmoredetailsuperbudget")){
				contractdetail1.setBudgetMoney(new BigDecimal(1000000)); //金额
			}else{
				contractdetail1.setBudgetMoney(new BigDecimal(2.00)); //金额
			}

			contractdetail1.setBudgetStatus(0);		//预算情况
		}else if(name.equals("relationbudgetmoredetailsuperbudget")){
			contractdetail.setBudgetMoney(new BigDecimal(1));
		
			//费用发生日期
			contractdetail1.setContractDate("2018-01");
			//费用明细类别
			contractdetail1.setCostDetailType("010");
			//是否关联预算
			contractdetail1.setIsRelationBudget(1);  //1 是关联预算
			contractdetail1.setBudgetMoney(new BigDecimal(1000000)); //金额
			contractdetail1.setBudgetStatus(0);		//预算情况
			// 成本中心
			contractdetail1.setCostCenter("10.00.78.00.6");
			// 成本中心名称
			contractdetail1.setCostCenter("技术中心413");
			
		}else{
			
			contractdetail.setBudgetMoney(new BigDecimal(3));
		}

		
		//预算情况
		contractdetail.setBudgetStatus(0);
		// 成本中心
		contractdetail.setCostCenter("10.00.78.00.6");
		// 成本中心名称
		contractdetail.setCostCenterName("技术中心413");
		
		if(name.equals("framecontractdetailiscannull")){
			
		}else if(name.equals("unframeallvaluesisright")||name.equals("norelationbudget")
				||name.equals("relationbudgetsuperbudget")||name.equals("relationbudgetnormal")
				||name.equals("nextyear")||name.equals("beforeyear")){
		contractDetails.add(contractdetail);
		}else if(name.equals("relationbudgetmoredetailsuperbudget")||name.equals("relationbudgetmoredetailnormal")
				||name.equals("relationbudgetmoredetailsuperbudget")){
			contractDetails.add(contractdetail);
			contractDetails.add(contractdetail1);
		}else if(name.equals("unframecontractdetailisnotcannull")){
			 form.setIsFrameContract("0");  // 1框架合同  0非框架合同 
		}else{
			
		}
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
		  param.put("contracts",JSONObject.toJSONString(contracts) );
		  param.put("contractDetails", JSONObject.toJSONString(contractDetails));
		  param.put("fileList", JSONObject.toJSONString(fileList));
		  param.put("formType","ZCHT");

		  
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
		
			 Logger.log("获取repData>>>>>>>>"+result);
			 
			 if(name.equals("filelistisnull")||name.equals("othermountequalzero")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 }else if(name.equals("framecontractdetailiscannull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"框架合同不能填写金额明细");
			 }else if(name.equals("framecontractsnotcannull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"合同明细不能为空");
			 }else if(name.equals("framenotcannull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"是否框架合同不能为空");
			 }else if(name.equals("unframecontractdetailisnotcannull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"金额明细不能为空");
			 }else if(name.equals("unframeallvaluesisright")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
					String sqlofformid="SELECT * FROM t_form WHERE  form_id='"+form.getFormId()+"'";	
					 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
						
						resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofformid);
						getBaseInfo(resultparambacks,form);
						//合同金额+其他金额=金额明细总额
						// 合同总金额=合同金额+其他金额
			 }else if(name.equals("frameallvaluesisright")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
					String sqlofformid="SELECT * FROM t_form WHERE  form_id='"+form.getFormId()+"'";	
					 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
						
						resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofformid);
						getBaseInfo(resultparambacks,form);
						// 合同总金额=合同金额+其他金额
			 }else if(name.equals("unframecontractmountequaldetailamount")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"");
			 }else if(name.equals("norelationbudget")||name.equals("relationbudgetnormal")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 }else if(name.equals("processformidnotcansubmit")||name.equals("endformidnotcansubmit")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！");
			 }else if(name.equals("MoneyTypeisnull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交失败");
			 }else if(name.equals("relationbudgetsuperbudget")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"2018-01差旅费预算不足");
			 }else if(name.equals("nextyear")||name.equals("beforeyear")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 }else if(name.equals("relationbudgetmoredetailnormal")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			 }else if(name.equals("relationbudgetmoredetailsuperbudget")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"2018-01差旅费预算不足");
			 }
			 
	}
	
	public static void getBaseInfo(	 Map<String, Object>  resultparambacks,Form form){
		
	}
public static void getContract(	 Map<String, Object>  resultparambacks,Form form){
		
	}

public static void getContractDetail(	 Map<String, Object>  resultparambacks,Form form){
	
}

	@DataProvider
    public static Object[][] submitSpendingContractApplymentFormParam() {
        return new Object[][]{ 
//        		{"frameallvaluesisright"},{"unframeallvaluesisright"},
//        		{"filelistisnull"},{"framecontractdetailiscannull"},{"framenotcannull"},{"framecontractsnotcannull"},
//        		{"unframecontractdetailisnotcannull"},
//        		{"unframecontractmountequaldetailamount"},
//        		{"contractmountbigzero"},{"othermountequalzero"},{""},{""},{""},{""},{""},
//        		{"processformidnotcansubmit"},{"endformidnotcansubmit"},
//        		{"norelationbudget"},{"relationbudgetsuperbudget"},{"relationbudgetnormal"},
//        		{"relationbudgetmoredetailnormal"},{"relationbudgetmoredetailsuperbudget"},
//        		{"MoneyTypeisnull"},
        		// {"nextyear"},{"beforeyear"}
        		{"frameallvaluesisright"},
        };
    }
}
