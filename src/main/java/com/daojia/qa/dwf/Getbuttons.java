package com.daojia.qa.dwf;

import java.util.HashMap;

import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class Getbuttons extends BaseTest<T>{
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
	 * 获取预算和打印按钮
	 * @return
	 */
		@Test(dataProvider = "getButtonsParam", description="获取预算和打印按钮")
		public void getButtons(String name){
			method_url="http://fmsystem205.djtest.cn/common/getbuttons";
			HashMap<String, String> param=new  HashMap<String, String>();
			String formId="";
			String formType="";
			String sqlofform="";
			if(name.equals("formidisnull")){
				
			}else if(name.equals("formidisnothavavalue")){
				formId="aaa";
			}else if(name.equals("formidishavavalueisprocess")){
				sqlofform="SELECT * FROM t_form WHERE form_type=4 and form_status=2 ORDER BY apply_date desc ";				 
				 formId=GetDbMethod.getCostDetailSelctTest(db, sqlofform,"form_id");
			}else if(name.equals("formidishavavalueisstart")){
				sqlofform="SELECT * FROM t_form WHERE form_type=4 and form_status=1 ORDER BY apply_date desc ";				 
				 formId=GetDbMethod.getCostDetailSelctTest(db, sqlofform,"form_id");
			}else if(name.equals("formidishavavalueiscompelete")){
				sqlofform="SELECT * FROM t_form WHERE form_type=4 and form_status=9 ORDER BY apply_date desc ";				 
				 formId=GetDbMethod.getCostDetailSelctTest(db, sqlofform,"form_id");
			}else{
				Logger.log("没有合适的case");
			}
			 param.put("formId", formId);
			 param.put("formType", formType);
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 Logger.log("获取repData>>>>>>>>"+result+"返回的结果全部都是打印和预算查看");
			 JSONObject repDatas=(JSONObject) result.get("repData");
		
			 if(name.equals("formidisnull")||name.equals("formidisnothavavalue")){
                 Assert.assertEquals(result.get("repCode").toString().trim(),"1");				 
				 Assert.assertEquals(result.get("message").toString().trim(),"获取业务按钮");
			 }else{
				 JSONArray repData=(JSONArray) repDatas.get("businessBtns");
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 
				 Assert.assertEquals(result.get("message").toString().trim(),"成功");
			 }
			
		
		}
		@DataProvider
	    public static Object[][] getButtonsParam() {
	        return new Object[][]{
	        		{"formidisnull"},{"formidisnothavavalue"},
	        		{"formidishavavalueisprocess"},{"formidishavavalueisstart"},{"formidishavavalueiscompelete"}
//	        		{"formidishavavalueisprocess"}
	        };
	        }
}
