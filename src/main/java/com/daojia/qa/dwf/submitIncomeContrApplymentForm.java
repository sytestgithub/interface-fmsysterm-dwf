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
import com.daojia.qa.entity.Contract;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitIncomeContrApplymentForm   extends BaseTest<T>{
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
	    * 保存收入合同单接口
	    * */
	@Test(dataProvider = "submitIncomeContrApplymentFormParam", description="保存和提交收入合同接口")
	public void submitIncomeContrApplymentForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		String updatedqlformid="";
		Form form =new Form();
		 if(name.equals("multe")){
			 form.setFormId("SRHT201802020002");//收入合同审批单   商家入驻协议（H5个人商家）
		 }else if(name.equals("multemore")){
			 form.setFormId("SRHT201802020005");//收入合同审批单   商家入驻协议（H5个人商家）
		 }else if(name.equals("Excellentcoordination")){
			 form.setFormId("SRHT201802020003");//收入合同审批单     优配
		 }else{
		form.setFormId("SRHT201802020001");//收入合同审批单
		 }
		form.setFormType(12);
		form.setApplyDate("2018-02-06");
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
			
		} else if(name.equals("MoneyTypeisdiff")){
			form.setMoneyType("2");
		}else{
		form.setMoneyType("0");
		}
		form.setState(0);
		form.setDepartmentCode("1-17-09-02");
		form.setBusinessCategory("月嫂");  //业务类别
		form.setContractNature("2");//合同性质  1支出类   2收入类  3合作类
		 form.setOurContractBody("北京五八到家信息技术有限公司"); //我方签约主体
         form.setContractName("收入类合同名称001");//合同名称
         form.setCostDesc("收入类合同内容"); //合同内容
         if(name.equals("Excellentcoordination")){
        	 form.setContractCategory("优配收入类");  //合同类别
			}else if(name.equals("multe") ||name.equals("multemore")){
	        	 form.setContractCategory("商家入驻协议（H5个人商家）");  //合同类别
				}else{
				form.setContractCategory("货的收入类");  //合同类别
			}
         
         
			GetDbMethod.endProcess(form.getFormId()); // 结束流程

			// formid 审批中、审批完毕
			  String sqlofformidprocess=" SELECT * FROM t_form WHERE form_type='12' AND form_status=2";
			  String sqlofformidend=" SELECT * FROM t_form WHERE form_type='12' AND form_status=9";
			  
			  if(name.equals("processformidnotcansubmit")){
				 form.setFormId( GetDbMethod.getCostDetailSelctTest(db, sqlofformidprocess, "form_id").toString().trim());
			  }else if(name.equals("endformidnotcansubmit")){
				  form.setFormId(  GetDbMethod.getCostDetailSelctTest(db, sqlofformidend, "form_id").toString().trim());
			  }
		String formOption="submit";
		List<Contract> contracts=new ArrayList<Contract>();
		Contract contract=new Contract();
		Contract contract1=new Contract();
		if(name.equals("multe")){
			//对方签约主体
			contract.setOppositeContractBody("批量对方签约名称1");
		}else if(name.equals("multemore")){
			//对方签约主体
			contract.setOppositeContractBody("批量对方签约名称1");
			contract1.setOppositeContractBody("批量对方签约名称2");
		}else{
			
			
			
			//合同开始日期
			contract.setContractStartTime("2018-02-05");
			//合同结束日期
			contract.setContractEndTime("2018-02-06");
			//对方签约主体
			contract.setOppositeContractBody("对方签约名称1");
			//发票类型
			contract.setInvoiceType("1");  
			//税率
			contract.setTaxRate("3"); 
			//付款周期
			contract.setPaymentPeriod("月结");
			//我方先盖章
			contract.setOurFirstStamp("0");
			//结算方式  //优配则有值
			if(name.equals("Excellentcoordination")){
				contract.setClearingForm("1");
			}else{
		
			}
			//我方收款银行
			contract.setOurReceivingBank("建设银行");
			//收款银行全称
			contract.setOurReceivingBankFullName("建设银行亚运村支行");
			//我方收款账号
			contract.setOurReceivingBankAccount("1334444444444444");

			//发票开具项目
			contract.setInvoiceProject("收入类发票开具项目001");
			//合同金额可预估
			//合同总金额
			if(name.equals("contractmountcanzero")){
				contract.setContractAmount(new BigDecimal(0.00));
			}else{
			contract.setContractAmount(new BigDecimal(1.00));
			}
			//佣金及其他
			//押金及其他  othermountequalzero
					if(name.equals("othermountequalzero")){
						contract.setDepositOtherAmount(new BigDecimal(0.00));
					}else{
						contract.setDepositOtherAmount(new BigDecimal(1.00));
					}
			//毛利  优配有值
					if(name.equals("Excellentcoordination")){
						contract.setGrossProfit(new BigDecimal(1.00));
					}else{
				
					}
			//毛利率   优配有值
			
					if(name.equals("Excellentcoordination")){
						contract.setGrossProfitMargin("1.23");
					}else{
				
					}
			
					
		}
		
		if(name.equals("contractdetailnotcannull")){
			
		}else{
			if(name.equals("multemore")){
				contracts.add(contract); 
				contracts.add(contract1); 
			
			}else{
				contracts.add(contract); 
			}
			
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

			  param.put("fileList", JSONObject.toJSONString(fileList));
			  param.put("formType","SRHT");

			  
				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
				 Logger.log("获取repData>>>>>>>>"+result);
				 if(name.equals("contractdetailnotcannull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"合同明细不能空");
				 }else if(name.equals("MoneyTypeisnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交失败");
				 }else if(name.equals("multe")||name.equals("MoneyTypeisdiff")
						 ||name.equals("contractmountcanzero")||name.equals("othermountequalzero")
						  ||name.equals("multemore")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
				 }
		 
	}
	
	@DataProvider
    public static Object[][] submitIncomeContrApplymentFormParam() {
        return new Object[][]{
//        		{"multe"},{"filelistisnull"},{"contractdetailnotcannull"},
//        		{"Excellentcoordination"},{"contractmountcanzero"},{"othermountequalzero"},
//        		{"multemore"},
//        		{"processformidnotcansubmit"},{"endformidnotcansubmit"},
//        		{"MoneyTypeisnull"},{"MoneyTypeisdiff"},

        		{"filelistisnull"}
        };
        
        }
}
