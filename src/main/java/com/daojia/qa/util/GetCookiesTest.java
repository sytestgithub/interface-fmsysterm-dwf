package com.daojia.qa.util;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;

public class GetCookiesTest {
	  @Test(description="获取cookie")
	  public String getcookies(){
		  String InpassUrl="http://inpass.djtest.cn/user/login";
		  final String userName="shangying";
		  final String password="sy58haitao!!";
		  final String redirect="http://fmsystem205.djtest.cn";
//		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";      
		  String Cookies="";
		  
		 
		  HashMap<String, String> param=new  HashMap<String, String>() ;
		  param.put("userName",userName);
		  param.put("password",password);
		  param.put("redirect",redirect);
		  HashMap<String, String> UserAgent1= new HashMap<String, String>();
		  UserAgent1.put("User-Agent", UserAgent );
		  UserAgent1.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		    try
		    {

    JSONObject	result=HttpRequest.doPostReturnResponseJson(InpassUrl,param,UserAgent1);
    System.out.println("结果"+result);
	  com.alibaba.fastjson.JSONArray tempCookies = result.getJSONArray("cookies");
		  Cookies= tempCookies.getJSONObject(0).getString("name")+"="+
		           tempCookies.getJSONObject(0).getString("value")+";"+
		   	  	  tempCookies.getJSONObject(1).getString("name")+"="+
		          tempCookies.getJSONObject(1).getString("value")+";"+
				   	  	  tempCookies.getJSONObject(2).getString("name")+"="+
				          tempCookies.getJSONObject(2).getString("value");
		  Logger.log("打印一下"+Cookies);   	
	    }	
		    catch(Exception e)
			 {
				 Assert.fail("Cookies获取失败---->>>>>>"+e.getMessage());
				 e.printStackTrace(); 
			 }		
		    Logger.log("结果reslut:");
		  return Cookies;
	  }
	
	 @Test(description="动态获取用户名")
	  public String getcookies(String userName,String password){
		  String InpassUrl="http://inpass.djtest.cn/user/login";

		  final String redirect="http://fmsystem205.djtest.cn";
//		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";      
		  String Cookies="";
		  
		 
		  HashMap<String, String> param=new  HashMap<String, String>() ;
		  param.put("userName",userName);
		  param.put("password",password);
		  param.put("redirect",redirect);
		  HashMap<String, String> UserAgent1= new HashMap<String, String>();
		  UserAgent1.put("User-Agent", UserAgent );
		  UserAgent1.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		    try
		    {

    JSONObject	result=HttpRequest.doPostReturnResponseJson(InpassUrl,param,UserAgent1);
    System.out.println("结果"+result);
	  com.alibaba.fastjson.JSONArray tempCookies = result.getJSONArray("cookies");
		  Cookies= tempCookies.getJSONObject(0).getString("name")+"="+
		           tempCookies.getJSONObject(0).getString("value")+";"+
		   	  	  tempCookies.getJSONObject(1).getString("name")+"="+
		          tempCookies.getJSONObject(1).getString("value")+";"+
				   	  	  tempCookies.getJSONObject(2).getString("name")+"="+
				          tempCookies.getJSONObject(2).getString("value");
		  Logger.log("打印一下"+Cookies);   	
	    }	
		    catch(Exception e)
			 {
				 Assert.fail("Cookies获取失败---->>>>>>"+e.getMessage());
				 e.printStackTrace(); 
			 }		
		    Logger.log("结果reslut:");
		  return Cookies;
	  }
	public static void main(String[] args) {
		GetCookiesTest getcookies=new GetCookiesTest();
		getcookies.getcookies();
	}
}
