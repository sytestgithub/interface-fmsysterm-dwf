package com.daojia.qa.dwf;

import java.util.HashMap;
import java.util.Map;

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

public class getTrelationFormInfo  extends BaseTest<T>{
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
	 * 获取未关联的申请单信息
	 * @return
	 */
		@Test(dataProvider = "getTrelationFormInfoParam", description=" 获取未关联的申请单信息")
		public void getTrelationFormInfo1(String name){
			method_url="http://fmsystem205.djtest.cn/common/getrelationforminfo";
//			method_url="http://10.253.107.227:80/common/getrelationforminfo";
			HashMap<String, String> param=new  HashMap<String, String>();
			String formType="1";
			String userId="4011";
			param.put("formType", formType);
			param.put("userId", userId);
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 Logger.log("获取repData>>>>>>>>"+result);
			 Assert.assertEquals(result.get("repCode").toString().trim(), "0");
			 Assert.assertEquals(result.get("message").toString().trim(), "成功");
	
			 if(name.equals("getTrelationFormInfohave")){
			 
			 JSONObject repdatas=(JSONObject) result.get("repData");
			 JSONArray repdata=(JSONArray) repdatas.get("departmentCode");  //  有问题啊！！
			 for(int i=0;i<repdata.size();i++){
					JSONObject redatejson=repdata.getJSONObject(i);
					Logger.log("第几个"+i+"name名字"+redatejson.get("cityName").toString().trim());
					String sqlofcitysubject= "";
					Logger.log("打印sql结果--》》"+sqlofcitysubject);
					 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
						
						resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofcitysubject);
			 }
			 }
		}
		@DataProvider
	    public static Object[][] getTrelationFormInfoParam() {
	        return new Object[][]{

	        		{"getTrelationFormInfohave"},{"getTrelationFormInfonothave"}
//	        		{"getTrelationFormInfohave"}

	        };
	        }
}
