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
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class saveRefundForm extends BaseTest<T>{
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
		@Test(dataProvider = "saveRefundFormParam", description="保存和提交个人借款单接口")
		public void saveRefundForm1(String name){
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
			
			form.setFormId("HKD201705150003");
	   		form.setFormType(8);
	   		form.setApplyDate("2017-12-26");
	   		form.setApplyPName("尚英");
	   		form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setInnerRemark("abc");
			  String sql1="select * from  t_form WHERE  form_id='HKD201705150003'";
	           String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
	           if(form_status1.equals("2")){
	          	 GetDbMethod.endProcess( "HKD201705150003"); 
	          		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
	          	 if(form_statusagins.equals("2")){
			 	 		 Logger.log("任务处理失败强制修改");
			 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='HKD201705150003'";
			 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
			 	 		 if(one>0){
			 	 			 Logger.log("修改成功"+one);
			 	 		 }
	           }
	          	 }else{
	          	 Logger.log("订单HKD201705150003状态非2"+form_status1);
	           }
	           
	           
          if(name.equals("applymoneynotisnull")){
				
			}else if(name.equals("applymoneynotisbac")){
				form.setApplyMoney("abc");
			}else{
				form.setApplyMoney("1");
			}
          
			form.setPlanRepaymentDate("2017-12-28");
//			form.setApproveMoney(new BigDecimal(1.00)); 批复金额用不上

			 if(name.equals("receiptmoneynotisnull")){
					
				}else if(name.equals("receiptmoneynotisabc")){
					form.setReceiptMoney("abc");
				}else{
					form.setReceiptMoney("1.00");
				}
			form.setCostDesc("描述信息");
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
//			form.setPayStatus("0"); 支付状态不需要填写
//			form.setFormStatus("1");  没有订单状态
			form.setApplyFormId("1511495114642888094");//借款单号
         if(name.equals("moneytypenotisnull")){
				
			}else if(name.equals("moneytypenotisabc")){
				form.setMoneyType("abc");
			}else{
				form.setMoneyType("0");
			}
			form.setState(0);
			form.setDepartmentCode("1-17-09-02");
	     	  String formOption="save";
	     		
	 	   	 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
	 			 UploadFileDto file1=new UploadFileDto();
	 				file1.setFileAddress("http://www.baidu.com1");
	 				file1.setFileName("filename2");
	 				file1.setIsPic("0");
	 				file1.setFileSize("0");
	 				fileList.add(file1);
	 				if(name.equals("UNrepeatsubmitofprocess")){
	 			  		 String formprocess=" UPDATE t_form SET form_status=2 WHERE form_id='HKD201705150003' ";
	 			  		int num= db.update(formprocess);
	 					if(num>0){
	 						Logger.log("修改数据库中的HKD201705150003状态成功");
	 					}else{
	 						Logger.log("修改数据库中没有HKD201705150003状态失败");
	 					}
	 			  		 form.setFormId("HKD201705150003");
	 			  	 }else if(name.equals("UNrepeatsubmitofcomplete")){
	 			  		 String formcomplete="SELECT * FROM t_form WHERE form_type=8 AND form_status=9";
	 			  		 String fomrid=GetDbMethod.getCostDetailSelctTest(db, formcomplete, "form_id");
	 			  		 form.setFormId(fomrid);
	 			  		 
	 			  	 }else if(name.equals("formstatusisrecall")){
	 						String getformidsql=" UPDATE t_form SET form_status=8 WHERE  form_id='HKD201705150003'";
	 						GetDbMethod.updateSelectCondition(db, getformidsql);
	 						
	 					}else if(name.equals("formstatusisReject")){
	 						String getformidsql=" UPDATE t_form SET form_status=3 WHERE  form_id='HKD201705150003'";
	 						GetDbMethod.updateSelectCondition(db, getformidsql);
	 						
	 					}
	 				
	 				
	 				
	 				
	 				
	 				HashMap<String, String> param=new  HashMap<String, String>();
	 		      	  param.put("formType","HKD");
	 		   		 param.put("formOption", formOption);
	 		   		 param.put("baseForm", JSONObject.toJSONString(form));	

	 		 	    param.put("fileList", JSONObject.toJSONString(fileList));
	 		 		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
	 	   			Logger.log("获取结果---->>>>"+result); 	
	 	   			if(name.equals("baseinfohavevalue")||name.equals("formstatusisReject")||name.equals("formstatusisrecall")
	 	   				||name.equals("moneytypenotisnull")||name.equals("receiptmoneynotisnull")){
	 	   			Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"保存成功"); 
	 	   			}else if(name.equals("UNrepeatsubmitofcomplete")){
		 	   			Assert.assertEquals(result.get("repCode").toString().trim(),"1");
						 Assert.assertEquals(result.get("message").toString().trim(),"此单已在审批中，不允许进行保存！！"); 
		 	   			}else if(name.equals("UNrepeatsubmitofprocess")){
		 					 String formprocess=" UPDATE t_form SET form_status=1 WHERE form_id='HKD201705150003' ";
		 			  		int num= db.update(formprocess);
		 					if(num>0){
		 						Logger.log("修改数据库中的HKD201705150003还原状态成功");
		 					}else{
		 						Logger.log("修改数据库中没有HKD201705150003还原状态失败");
		 					}
		 					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
		 					 Assert.assertEquals(result.get("message").toString().trim(),"此单已在审批中，不允许进行保存！！"); 
		 			 }else if(name.equals("moneytypenotisabc")||name.equals("applymoneynotisbac")||name.equals("receiptmoneynotisabc")
		 					||name.equals("moneytypenotisnull")){
			 	   			Assert.assertEquals(result.get("repCode").toString().trim(),"1");
							 Assert.assertEquals(result.get("message").toString().trim(),"保存失败"); 
			 	   			}
	 		 	    
	 		 	    
		}
		@DataProvider
	    public static Object[][] saveRefundFormParam() {
	        return new Object[][]{
	        		{"baseinfohavevalue"},{"UNrepeatsubmitofprocess"},
	        		{"UNrepeatsubmitofcomplete"},	  
	        		{"moneytypenotisnull"},{"applymoneynotisnull"},{"receiptmoneynotisnull"},
	        		{"moneytypenotisabc"},{"applymoneynotisbac"},{"receiptmoneynotisabc"},
	        		{"formstatusisrecall"},	{"formstatusisReject"},
//	        		{"UNrepeatsubmitofprocess"}
	        };
	        }
}
