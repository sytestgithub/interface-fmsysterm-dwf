package com.daojia.qa.dwf;

import java.util.HashMap;

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
import com.daojia.qa.entity.SelectCondition;
import com.daojia.qa.util.GetHead;

public class getInvoiceManager extends BaseTest<T>{
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
    * 发票查询接口
    * */
	@Test(dataProvider = "getInvoiceManagerParam", description="发票管理页面")
	public void getInvoiceManager(String name){
		
		method_url="http://fmsystem205.djtest.cn/invoice/invoicelist";
		String param="";
		if(name.equals("page")){
			 param="pageSize=10&page=1";
		}else if(name.equals("formid")){
			param="formId=FPSQ201804040306&pageSize=10&page=1";
		}else if(name.equals("businessCategory")){
			param="businessCategory=保姆&pageSize=10&page=1";
		}else if(name.equals("invoiceStatus")){
			param="invoiceStatus=1&pageSize=10&page=1";
		}else if(name.equals("invoiceType")){
			param="invoiceType=1&invoiceStatus=1&pageSize=10&page=1";
		}else if(name.equals("userId")){
			param="userId=尚英&pageSize=10&page=1";
		}else if(name.equals("applyDate")){
			
		}else if(name.equals("invoiceStartDate")){
			param="invoiceStartDate=2018-04-03&invoiceStatus=1&pageSize=10&page=1";
		}
	
		
			
		 JSONObject result= HttpRequest.doGetReturnResponseJson(method_url, param, GetHead.gethead());
		 JSONObject  redate=(JSONObject) result.get("repData");
		Assert.assertEquals(result.get("repCode").toString().trim(), "0");
		Assert.assertEquals(result.get("message"), "成功");
	}
	
	@DataProvider
    public static Object[][] getInvoiceManagerParam() {
        return new Object[][]{
  
//        		 {"page"},{"formid"},{"businessCategory"},{"invoiceStatus"},
//        		 {"invoiceType"},{"userId"},{"startEndDate"},{"invoiceStartDate"},
        		{"userId"}
        };
        }
}
