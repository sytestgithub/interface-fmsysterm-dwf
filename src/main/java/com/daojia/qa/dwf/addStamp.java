package com.daojia.qa.dwf;

import java.util.HashMap;

import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.Stamp;
import com.daojia.qa.util.GetHead;

public class addStamp extends BaseTest<T>{
	String method_url="";

	/*
	    * 获取办理事项数据接口
	    * */
	@Test( dataProvider = "addStampParam",description="打印维护页面新建接口")
	public void addStamp1(){
		method_url="http://fmsystem205.djtest.cn/stamp/addstamp";
		Stamp stamp=new Stamp();
		stamp.setStampCode("");// 印证编码
		
		HashMap<String, String> param=new  HashMap<String, String>();
	
		  param.put("stamp",JSONObject.toJSONString(stamp) );
		  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
			 Logger.log("获取repData>>>>>>>>"+result);
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"成功");

			 JSONArray reparray=(JSONArray) result.get("repData");
	}
	@DataProvider
    public static Object[][] addStampParam() {
        return new Object[][]{ 
//        		{"normalvalue"},{"codeUniqueness"},{"stampnameandtypeuniqueness"}
        		{"normalvalue"}
        };
        }
}
