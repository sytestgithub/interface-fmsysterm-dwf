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
import com.daojia.qa.util.GetHead;

public class getVehicleenum extends BaseTest<T>{
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
	 * 获取交通工具
	 * @return
	 */
		@Test(dataProvider = "getVehicleenumParam", description="获取交通工具")
		public void getVehicleenum1(String name){
			method_url="http://fmsystem205.djtest.cn/common/getvehicleenum";
//			method_url="http://10.253.107.227:80/common/getvehicleenum";
			HashMap<String, String> param=new  HashMap<String, String>();
			
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 JSONObject repdates=(JSONObject) result.get("repData");
			 JSONArray repdate=(JSONArray) repdates.get("vehicleList");
			 Logger.log("获取repData>>>>>>>>"+result);
			 Assert.assertEquals(result.get("repCode").toString().trim(), "0");
			 Assert.assertEquals(result.get("message").toString().trim(), "成功");
			 Assert.assertEquals(repdate.size(), 5);
		}
		@DataProvider
	    public static Object[][] getVehicleenumParam() {
	        return new Object[][]{

	        		{"gethaveofVehiclenum"}

	        };
	        }
}
