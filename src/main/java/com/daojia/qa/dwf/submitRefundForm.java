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
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitRefundForm extends BaseTest<T>{
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
		@Test(dataProvider = "submitRefundFormParam", description="保存和提交个人借款单接口")
		public void submitRefundForm1(String name){
			 
				method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
				
			form.setFormId("HKD201705150001");
	   		form.setFormType(8);
	   		form.setApplyDate("2017-12-26");
	   		form.setApplyPName("尚英");
	   		form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setApplyMoney("1");
			form.setPlanRepaymentDate("2017-12-28");
//			form.setApproveMoney(new BigDecimal(1.00)); 批复金额用不上
//			form.setReceiptMoney("1.00");
			form.setCostDesc("描述信息");
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
//			form.setPayStatus("0"); 支付状态不需要填写
//			form.setFormStatus("1");  没有订单状态
			form.setApplyFormId("1511495114642888094");//借款单号
			form.setMoneyType("0");
			form.setState(0);
			form.setInnerRemark("abc");

			form.setDepartmentCode("1-17-09-02");
	     	  String formOption="submit";
	     	  //  初始化还款单
	     	  String sql1="select * from  t_form WHERE  form_id='HKD201705150001'";
              String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
              if(form_status1.equals("2")){
		 	 	 GetDbMethod.endProcess( form.getFormId());
		 	 	String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
		 	 	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='HKD201705150001'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
		 	 	 }else{
		 	 		 
		 	 	 }
              }else{
             	 Logger.log("订单HKD201705150001状态非2"+form_status1);
              }
		 	 	 
            Logger.log("数据处理");
	            
				String modifysql=" UPDATE t_form SET no_amount=0,already_amount=apply_money,wait_amount=0 WHERE  form_type=3 AND apply_p_name='尚英' AND form_status=9";				
			int i=GetDbMethod.updateSelectCondition(db, modifysql);
			if(i>0){
				Logger.log("已完成的借款单处理完毕");
			}else{
				Logger.log("没有需要修改的数据");
			}
			
			String modifysqlofprocess=" UPDATE t_form SET form_status=1 WHERE  form_type=3 AND apply_p_name='尚英' AND form_status=2";				
			int j=GetDbMethod.updateSelectCondition(db, modifysqlofprocess);
			if(j>0){
				Logger.log("处理总的的借款单处理完毕");
			}else{
				Logger.log("没有需要修改的数据");
			}
			
			 String formprocess="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010' ";
		  		int num= db.update(formprocess);
				if(num>0){
					Logger.log("修改数据库中的GRJK201711240010的未冲销成1");
				}else{
					Logger.log("修改数据库中没有GRJK201711240010单修改");
				}
	   	 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file1=new UploadFileDto();
				file1.setFileAddress("http://www.baidu.com1");
				file1.setFileName("filename2");
				file1.setIsPic("0");
				fileList.add(file1);
	      if(name.equals("confirmwhethercanrefund")){
		     		 String notcanrefund="UPDATE t_form SET no_amount=0 ,already_amount=1,wait_amount=0  WHERE form_id='GRJK201711240010' ";
				  		int num1= db.update(notcanrefund);
						if(num1>0){
							Logger.log("修改数据库中的GRJK201711240010的让其已冲销金额1");
						}else{
							Logger.log("修改数据库中没有GRJK201711240010单修改");
						}
		     	  }else if(name.equals("confirmwhethercanrefund1")){
			     		 String notcanrefund="UPDATE t_form SET no_amount=0 ,already_amount=0,wait_amount=1  WHERE form_id='GRJK201711240010' ";
					  		int num1= db.update(notcanrefund);
							if(num1>0){
								Logger.log("修改数据库中的GRJK201711240010的让其待冲金额1");
							}else{
								Logger.log("修改数据库中没有GRJK201711240010单修改");
							}
			     	  }else if(name.equals("UNrepeatsubmitofcomplete")){
	      		 String formcomplete="SELECT * FROM t_form WHERE form_type=3 AND form_status=9";
	      		 String fomrid=GetDbMethod.getCostDetailSelctTest(db, formcomplete, "form_id");
	      		 form.setFormId(fomrid);
	      		 
	      	 }
//			 /*
	     	HashMap<String, String> param=new  HashMap<String, String>();
	      	  param.put("formType","HKD");
	   		 param.put("formOption", formOption);
	   		 param.put("baseForm", JSONObject.toJSONString(form));	

	 	    param.put("fileList", JSONObject.toJSONString(fileList));
	 	    

	   		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
	   			Logger.log("获取结果---->>>>"+result); 	
	   			
	   			
	   			if(name.equals("baseinfohavevalue")){
	   				Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");  
	   			}else if(name.equals("confirmwhethercanrefund1")||name.equals("confirmwhethercanrefund")){
	   				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"未还款金额为0，无需冲销或还款！"); 
	   			}else if(name.equals("UNrepeatsubmitofcomplete")){
	   				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！"); 
	   			}
	   			
		}
		@DataProvider
	    public static Object[][] submitRefundFormParam() {
	        return new Object[][]{
	        		{"baseinfohavevalue"},{"confirmwhethercanrefund"},{"confirmwhethercanrefund1"},
	        		{"UNrepeatsubmitofcomplete"}
//	        		{"confirmwhethercanrefund1"}
	        };
	        }
		
}
