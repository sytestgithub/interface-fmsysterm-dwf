package com.daojia.qa.dwf;

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

public class submitCooperationContrApplymentForm   extends BaseTest<T>{
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
	    * 保存合作合同单接口
	    * */
	@Test(dataProvider = "submitCooperationContrApplymentFormParam", description="保存和提交合作合同接口")
	public void submitCooperationContrApplymentForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		String updatedqlformid="";
		Form form =new Form();
		 
			form.setFormId("HZHT201802020001");//合作合同审批单
			form.setFormType(13);
			form.setApplyDate("2018-02-06");
			form.setApplyPName("尚英");
			
			form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
//			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setApplyMoney("1");
			form.setReceiptMoney("1.00");
			form.setCostDesc("合同内容合作类");  // 合同内容
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
//			form.setPayStatus("0"); 支付状态不需要填写
//			form.setFormStatus("1");  没有订单状态
			form.setMoneyType("0");
			form.setState(0);
			form.setDepartmentCode("1-17-09-02");
			form.setBusinessCategory("货的");  //业务类别
			form.setContractNature("3");//合同性质  1支出类   2收入类  3合作类
			 form.setOurContractBody("天津五八到家生活服务有限公司"); //我方签约主体
	         form.setContractName("合作类合同名称001");//合同名称
	         form.setCostDesc("合作类合同内容"); //合同内容
	       
					form.setContractCategory("战略合作类");  //合同类别
					GetDbMethod.endProcess(form.getFormId()); // 结束流程
	         
					
					// formid 审批中、审批完毕
					  String sqlofformidprocess=" SELECT * FROM t_form WHERE form_type='13' AND form_status=2";
					  String sqlofformidend=" SELECT * FROM t_form WHERE form_type='13' AND form_status=9";
					  
					  if(name.equals("processformidnotcansubmit")){
						 form.setFormId( GetDbMethod.getCostDetailSelctTest(db, sqlofformidprocess, "form_id").toString().trim());
					  }else if(name.equals("endformidnotcansubmit")){
						  form.setFormId(  GetDbMethod.getCostDetailSelctTest(db, sqlofformidend, "form_id").toString().trim());
					  }
			String formOption="submit";
			List<Contract> contracts=new ArrayList<Contract>();
			Contract contract=new Contract();
			Contract contract1=new Contract();
			
			//合同开始日期
			contract.setContractStartTime("2018-02-05");
			//合同结束日期
			contract.setContractEndTime("2018-02-06");
			//我方先盖章
			contract.setOurFirstStamp("0");
			//对方签约主体
			contract.setOppositeContractBody("合作类对方签约名称1");
		
           if(name.equals("contractdetailnotcannull")){
				
			}else{
				contracts.add(contract); 
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
			  param.put("formType","HZHT");

			  
				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
				 Logger.log("获取repData>>>>>>>>"+result);
			if(name.equals("valueisnormal")||name.equals("filelistisnull")||name.equals("MoneyTypeisnull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
			}else if(name.equals("contractdetailnotcannull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"合同明细不能为空");
			}
	}
	
	@DataProvider
    public static Object[][] submitCooperationContrApplymentFormParam() {
        return new Object[][]{
//        		{"filelistisnull"},
//        		{"MoneyTypeisnull"},{"valueisnormal"},
//        		{"contractdetailnotcannull"},{""},{""},
//        		{"processformidnotcansubmit"},{"endformidnotcansubmit"},
        		{"valueisnormal"}
        };
        }
}
