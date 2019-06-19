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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class FormOfGetFormBaseInfo extends BaseTest<T>{
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
	
	
	/**
	 * 初始化表单数据接口
	 * @return
	 */
		@Test(dataProvider = "getFormBaseInfoParam", description="初始化页面信息")
		public void getFormBaseInfo(String name){
			method_url="http://fmsystem205.djtest.cn/common/getformbaseinfo";
//			method_url="http://10.253.7.165:80/common/getformbaseinfo";
			HashMap<String, String> param=new  HashMap<String, String>();
			String formType="";
			String formId="";
			String openFlag="";
			String sqlofform="";
			String travelApplyId="";
			if(name.equals("notmatchformtypeandid")){
				sqlofform="SELECT * FROM t_form WHERE form_type=1 ORDER BY apply_date desc ";
				 formType="RCBX";				 
				 formId=GetDbMethod.getCostDetailSelctTest(db, sqlofform,"form_id");
				 openFlag="modify";
				
			}else if(name.equals("addformidofdailyrepayment")){
				 formType="RCBX";				 
				 openFlag="add";
			}else if(name.equals("addformidofbudgetformid")){
			
				 formType="YSZB";				 

				 openFlag="add";
			}else if(name.equals("updateformidofdailyrepayment")){

				 formType="RCBX";				 

				 formId="RCBX201711209889";
				 openFlag="modify";
			}else if(name.equals("updateformidofbudgetformid")){

				 formType="YSZB";				 

				 formId="YSZB201710252616";
				 openFlag="modify";
			}else if(name.equals("updateformtypeisnull")){
	
				 formId="RCBX201711209889";
				 openFlag="modify";

				
			}else if(name.equals("updateformformidisnull")){
				formType="RCBX";
				 openFlag="modify";
				
			}else if(name.equals("updateformtypeisnull")){
				 formId="RCBX201711209889";
					formType="RCBX";
			}else if(name.equals("typeisnothave")){
//				sqlofform="SELECT * FROM t_form WHERE form_type=4 ORDER BY apply_date desc ";
				 formType="RCBX1";				 
//				 formId=GetDbMethod.getCostDetailSelctTest(db, sqlofform,"form_id");
//				 formId="RCBX201711209889";
				 openFlag="modify";
			}else if(name.equals("formidisnothave")){	
				 formType="RCBX";				 
				 formId="RCBX201711209889222";
				 openFlag="modify";
			}else if(name.equals("openFlagisnothave")){		
				 formType="RCBX";				 
				 formId="RCBX201711209889222";
				 openFlag="modify1";
				 Logger.log("所有非modity都是新增");
			}else if(name.equals("addformidoftravelapply")){		
				 formType="CLSQ";				 

				 openFlag="add";
				 Logger.log("所有非modity都是新增");
			}else if(name.equals("addformidoftravelrepayment")){		
				 formType="CLBX";				 
				 travelApplyId="CLSQ201712200116";
				 openFlag="add";
				 Logger.log("所有非modity都是新增");
			}else if(name.equals("addformidoftravelrepaymentnothaveapplyform")){		
				 formType="CLBX";				 
				 travelApplyId="CLSQ20171113620";
				 openFlag="add";
				 Logger.log("所有非modity都是新增");
			}else if(name.equals("updateformidoftravelapply")){		
				 formType="CLSQ";				 
				 formId="CLSQ201712150188";
				 openFlag="modify";
			}else if(name.equals("updateformidoftravelrepayment")){		
				 formType="CLBX";				 
				 formId="CLSQ201712200116";
				 openFlag="modify";
			}else if(name.equals("addformidofPersonalBorrowForm")){		             
				String modifysql=" UPDATE t_form SET no_amount=0,already_amount=apply_money,wait_amount=0 WHERE  form_type=3 AND apply_p_name='尚英'";				
			int i=GetDbMethod.updateSelectCondition(db, modifysql);
			if(i>0){
				Logger.log("修改订单成功");
			}else if(name.equals("")){		
				 formType="";				 
				 formId="";
				 openFlag="add";
			}else{
				Logger.log("没有需要修改的数据");
			}
				formType="GRJK";				 				 
				 openFlag="add";
				 
			}else if(name.equals("updateformidofPersonalBorrowForm")){		
				 formType="GRJK";				 
				 formId="GRJK201711240010";
				 openFlag="modify";
			}else if(name.equals("addformidofrefundForm")){		
				 formType="HKD";		
				 String updatedqlformid="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010'";
					int i= db.update(updatedqlformid);
					if(i>0){
						Logger.log("修改数据库中的GRJK201711240010的未冲销成1");
					}else{
						Logger.log("修改数据库中没有GRJK2017");
			        }
			
				 openFlag="add";
			}else if(name.equals("updateformidofrefundForm")){		
				 formType="HKD";				 
				 formId="HKD201712190001";
				 openFlag="modify";
			}else if(name.equals("addformidofexpcontract")){		
				 formType="ZCHT";				 
				 formId="";
				 openFlag="add";
			}else if(name.equals("updateformidofexpcontract")){		
				 formType="ZCHT";				 
				 formId="ZCHT2018020200022";
				 openFlag="modify";
			}else if(name.equals("addformidofinccontract")){		
				 formType="SRHT";				 
				 formId="";
				 openFlag="add";
			}else if(name.equals("updateformidofinccontract")){		
				 formType="SRHT";				 
				 formId="SRHT201802020002";
				 openFlag="modify";
			}else if(name.equals("addformidofcoocontract")){		
				 formType="HZHT";				 
				 formId="";
				 openFlag="add";
			}else if(name.equals("updateformidofcoocontract")){		
				 formType="HZHT";				 
				 formId="HZHT201802020001";
				 openFlag="modify";
			}else if(name.equals("addformidofSettlement")){		
				 formType="KJJS";				 
				 formId="";
				 openFlag="add";
			}else if(name.equals("updateformidofSettlement")){		
				 formType="KJJS";				 
				 formId="KJJS201802020001";
				 openFlag="modify";
			}else if(name.equals("addformidofToPublicContract")){		
				 formType="DGFK";				 
				 formId="";
				 openFlag="add";
			}else if(name.equals("updateformidofToPublicContract")){		
				 formType="DGFK";				 
				 formId="";
				 openFlag="modify";
			}else if(name.equals("addformidofstamp")){		
				 formType="YZSQ";				 
				 formId="";
				 openFlag="add";
			}else if(name.equals("updateformidofstamp")){		
				 formType="YZSQ";				 
				 formId="YZSQ201803280001";
				 openFlag="modify";
			}else{
				Logger.log("没有合适的case");
			}
			 param.put("formType", formType);
			 param.put("formId", formId);
			 param.put("openFlag", openFlag);
			 param.put("travelApplyId", travelApplyId);
			 
			 
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
//			 Logger.log("获取repData>>>>>>>>"+result);
			 Logger.log("获取repData>>>>>>>>"+result.getString("repCode"));
			 Logger.log("获取repData>>>>>>>>"+result.getString("message"));
//			 Logger.log("获取repData>>>>>>>>"+result);
			
      if(name.equals("notmatchformtypeandid")||name.equals("addformidofdailyrepayment")||name.equals("addformidofbudgetformid")
    		  ||name.equals("updateformidofdailyrepayment")||name.equals("updateformidofbudgetformid")||name.equals("openFlagisnothave")
    		  ||name.equals("addformidofPersonalBorrowForm")||name.equals("updateformidofPersonalBorrowForm")
    		  ||name.equals("addformidofrefundForm")||name.equals("updateformidofrefundForm")){
    	  if(name.equals("addformidofPersonalBorrowForm")||name.equals("addformidofrefundForm")){
    		  if(result.getString("repCode").toString().equals("1")){
    	         Assert.assertEquals(result.get("message").toString().trim(),"存在未还清的借款单,不允许新增借款");
    		  }else{
    			  Assert.assertEquals(result.get("message").toString().trim(),"成功");
    		  }
    		  
    		  
    	  }else{
    		  Assert.assertEquals(result.get("repCode").toString().trim(),"0");
 			 Assert.assertEquals(result.get("message").toString().trim(),"成功");
    	  }
    	  
      }else if(name.equals("updateformtypeisnull") 
    		  ||name.equals("updateformtypeisnull")){
    	  Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"获取基础信息异常");
      }else if(name.equals("addformidoftravelrepaymentnothaveapplyform")){
    	  Assert.assertEquals(result.get("repCode").toString().trim(),"1");
//			 Assert.assertEquals(result.get("message").toString().trim(),"");
      }else if(name.equals("typeisnothave")||name.equals("updateformformidisnull")){
    	  Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"修改的情况formId不能为空");
      }else if(name.equals("formidisnothave")){
    	  Assert.assertEquals(result.get("repCode").toString().trim(),"0");
      }else{
    		Logger.log("没有合适的case处理");
      }
		}
		@DataProvider
	    public static Object[][] getFormBaseInfoParam() {
	        return new Object[][]{
//	       		 {"notmatchformtypeandid"},{"addformidofdailyrepayment"},{"addformidofbudgetformid"},
//	       		 {"updateformidofdailyrepayment"},{"updateformidofbudgetformid"},
//	       		 {"updateformtypeisnull"},{"updateformformidisnull"},{"updateformtypeisnull"},{"typeisnothave"},
//	        		{"formidisnothave"},{"openFlagisnothave"},
//	        		{"addformidoftravelapply"},{"addformidoftravelrepayment"},{"updateformidoftravelapply"},
//	        		{"updateformidoftravelrepayment"},{"addformidofPersonalBorrowForm"},{"updateformidofPersonalBorrowForm"},
//	        		{"addformidofrefundForm"},{"updateformidofrefundForm"},
//	        		{"addformidofexpcontract"},{"updateformidofexpcontract"},
//	        		{"addformidofinccontract"},{"updateformidofinccontract"},
//	        		{"addformidofcoocontract"},{"updateformidofcoocontract"},
//	        		{"addformidofSettlement"},{"updateformidofSettlement"},
//	        		{"addformidofToPublicContract"},{"updateformidofToPublicContract"},
//	        		{"addformidofstamp"},{"updateformidofstamp"},
//	        		{"addformidofinvoice"},{"updateformidofinvoice"},
	        		{"updateformidofstamp"}

	        };
	        }
}
