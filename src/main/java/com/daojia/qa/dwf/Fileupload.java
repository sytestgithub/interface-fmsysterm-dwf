package com.daojia.qa.dwf;

import java.util.HashMap;

import org.apache.poi.ss.formula.functions.T;
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
import com.daojia.qa.util.GetHead;

public class Fileupload extends BaseTest<T>{
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
	 * 上传附件
	 * @return
	 */
		@Test(dataProvider = "fileuploadParam", description="获取业务预算")
		public void fileupload(String name){
			method_url="http://fmsystem205.djtest.cn/common/fileupload";
			HashMap<String, String> param=new  HashMap<String, String>();
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 Logger.log("获取repData>>>>>>>>"+result);
		}
		@DataProvider
	    public static Object[][] fileuploadParam() {
	        return new Object[][]{
	        		{"getfile"}
	        };
	        }
}
