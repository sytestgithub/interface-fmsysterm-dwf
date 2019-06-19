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
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitPersonalBorrowForm extends BaseTest<T>{
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
	    * 保存和提交个人借款单接口
	    * */
		@Test(dataProvider = "submitPersonalBorrowFormParam", description="保存和提交个人借款单接口")
		public void submitPersonalBorrowForm1(String name){


//			
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		form.setFormId("GRJK201712010000");
   		form.setFormType(3);
   		form.setApplyDate("2017-12-25");
   		form.setApplyPName("尚英");
   		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setApplyMoney("1");
		form.setPlanRepaymentDate("2017-12-28");
//		form.setApproveMoney(new BigDecimal(1.00)); 批复金额用不上
//		form.setReceiptMoney("1.00");
		form.setCostDesc("描述信息");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
//		form.setPayStatus("0"); 支付状态不需要填写
//		form.setFormStatus("1");  没有订单状态
		form.setMoneyType("0");
		form.setState(0);
		form.setDepartmentCode("1-17-09-02");
		form.setInnerRemark("abc");

		
		
		 String sql1="select * from  t_form WHERE  form_id='GRJK201712010000'";
         String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
         if(form_status1.equals("2")){
        	 GetDbMethod.endProcess("GRJK201712010000"); 
        		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
        	 if(form_statusagins.equals("2")){
	 	 		 Logger.log("任务处理失败强制修改");
	 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='GRJK201712010000'";
	 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
	 	 		 if(one>0){
	 	 			 Logger.log("修改成功"+one);
	 	 		 }
         }
         }else{
        	 Logger.log("订单GRJK201712010000状态非2"+form_status1);
         }
         
//		 GetDbMethod.endProcess(form.getFormId());
		 
   	   	 Logger.log("数据处理");
        
		String modifysql=" UPDATE t_form SET no_amount=0,already_amount=apply_money,wait_amount=0 WHERE  form_type=3 AND apply_p_name='尚英'";				
	int i=GetDbMethod.updateSelectCondition(db, modifysql);
	if(i>0){
		Logger.log("修改订单成功");
	}else{
		Logger.log("没有需要修改的数据");
	}
//   		form.setApplyFormId("");//借款单号
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
   		
   		if(name.equals("costdetailmusthave")){
   			
			}else{
				costDetails.add(costdetail);
			}
   	 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
		 UploadFileDto file1=new UploadFileDto();
			file1.setFileAddress("http://www.baidu.com1");
			file1.setFileName("filename2");
			file1.setIsPic("0");
  	 if(name.equals("filecannull")){
  		 
  	 }else{
  		fileList.add(file1);
  	 }
  	 
  	 if(name.equals("UNrepeatsubmitofprocess")){
  		 String formprocess="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010' ";
  		int num= db.update(formprocess);
		if(num>0){
			Logger.log("修改数据库中的GRJK201711240010的未冲销成0");
		}else{
			Logger.log("修改数据库中没有GRJK201711240010单修改");
		}
  		 form.setFormId("GRJK201711240010");
  	 }else if(name.equals("UNrepeatsubmitofcomplete")){
  		 String formcomplete="SELECT * FROM t_form WHERE form_type=3 AND form_status=9";
  		 String fomrid=GetDbMethod.getCostDetailSelctTest(db, formcomplete, "form_id");
  		 form.setFormId(fomrid);
  		 
  	 }else if(name.equals("UNrepeatsubmitofprocess1")){
  	  	 String formprocess="UPDATE t_form SET form_status=2 WHERE form_id='GRJK201712010001'";
  	  	int num= db.update(formprocess);
		if(num>0){
			Logger.log("修改数据库中的GRJK201712010001状态修改成功");
		}else{
			Logger.log("修改数据库中没有GRJK201711240010状态修改成功");
		}
 
  	 }

  	
   		 HashMap<String, String> param=new  HashMap<String, String>();
      	  param.put("formType","GRJK");
   		 param.put("formOption", formOption);
   		 param.put("baseForm", JSONObject.toJSONString(form));	
   		 param.put("costDetails", JSONObject.toJSONString(costDetails));
 	    param.put("fileList", JSONObject.toJSONString(fileList));

   	   	
//		 /*
   		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
   			Logger.log("获取结果---->>>>"+result); 	
		
   			if(name.equals("haveallvalue")){
   				Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");  
   			}else if(name.equals("costdetailmusthave")){
   				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用明细不能为空"); 
   	   		}else if(name.equals("UNrepeatsubmitofprocess")||name.equals("UNrepeatsubmitofprocess1")){
   				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"存在未冲销完成的个人借款单或备用金申请单，不能提交！"); 
   	   		if(name.equals("UNrepeatsubmitofprocess1")){
   	   		 String formprocess="UPDATE t_form SET form_status=1 WHERE form_id='GRJK201712010001'";
   	  	  	int num= db.update(formprocess);
   			if(num>0){
   				Logger.log("修改数据库中的GRJK201712010001状态修改成功");
   			}else{
   				Logger.log("修改数据库中没有GRJK201711240010状态修改成功");
   			}
   	   		}
   	   		}else if(name.equals("UNrepeatsubmitofcomplete")){
   				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！"); 
   	   		}
//   	   		*/
		}
		@DataProvider
	    public static Object[][] submitPersonalBorrowFormParam() {
	        return new Object[][]{
	        		{"haveallvalue"},{"costdetailmusthave"},{"filecannull"},{"UNrepeatsubmitofprocess"},
	        		{"UNrepeatsubmitofcomplete"}
	        		
//	        		{"haveallvalue"}
	        };
	        }
}
