package com.daojia.qa.util;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.http.HttpRequest;

public class GetCookie {
    String InpassUrl="http://inpass.daojia-inc.com/user/login";
    @Test
	public String getCookieResult() throws IOException {
		  final String userName="lijingguo";
		  final String password="xingkong.1234";
		  final String redirect="http://djoy.daojia-inc.com/";
		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
		        String Cookies="";
		    try
		    {

		        JSONObject result = HttpRequest.doPostReturnResponseJson(InpassUrl,
		            new HashMap<String, String>() {
		                {
		                	put("userName",userName);
		                	put("password",password);
		                	put("redirect",redirect);
		                }
		            }, new HashMap<String, String>() {
		                {
		                	put("User-Agent", UserAgent );
		    			
		                }
		            });
		 // System.out.println(result.getJSONArray("cookies"));
		  com.alibaba.fastjson.JSONArray tempCookies = result.getJSONArray("cookies");
		  Cookies= tempCookies.getJSONObject(0).getString("name")+"="+
		           tempCookies.getJSONObject(0).getString("value")+";"+
		   	  	  tempCookies.getJSONObject(1).getString("name")+"="+
		          tempCookies.getJSONObject(1).getString("value");
		//  System.out.println(Cookies);   	
	    }	
		    catch(Exception e)
			 {
				 Assert.fail("Cookies获取失败");
				 e.printStackTrace(); 
			 }		
		  return Cookies;
	 }
    
    @Test
   	public String getCookieResultPassBinghe() throws IOException {
   		  final String userName="houtaiyewuxian";
   		  final String password="58shenqi*";
   		  final String redirect="http://sso.daojia-inc.com/index/index.do";
   		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
   		  String Cookies="";
   		    try
   		    {

   		        JSONObject result = HttpRequest.doPostReturnResponseJson(InpassUrl,
   		            new HashMap<String, String>() {
   		                {
   		                	put("userName",userName);
   		                	put("password",password);
   		                	put("redirect",redirect);
   		                }
   		            }, new HashMap<String, String>() {
   		                {
   		                	put("User-Agent", UserAgent );
   		    			
   		                }
   		            });
   		 // System.out.println(result);
   		  com.alibaba.fastjson.JSONArray tempCookies = result.getJSONArray("cookies");
   		  Cookies= tempCookies.getJSONObject(0).getString("name")+"="+
   		           tempCookies.getJSONObject(0).getString("value")+";"+
	   		   	  	  tempCookies.getJSONObject(1).getString("name")+"="+
	   		          tempCookies.getJSONObject(1).getString("value")+";"+
	   		  		  tempCookies.getJSONObject(2).getString("name")+"="+
	   		  		  tempCookies.getJSONObject(2).getString("value");
   	//	  System.out.println(Cookies);   	
   	    }	
   		    catch(Exception e)
   			 {
   				 Assert.fail("Cookies获取失败");
   				 e.printStackTrace(); 
   			 }
   		    
   		    String Url="http://sso.daojia-inc.com/login/switchuser.do";
   		    final String id="80";
   		    final String username="liaoshixiang";
   		    final String temp=Cookies;
   		    String tempInfo="";
   		 //二次请求   冰河系统校验
   		 try
		    {

		        JSONObject result1 = HttpRequest.doPostReturnResponseJson(Url,
		            new HashMap<String, String>() {
		                {
		                	put("id",id);
		                	put("username",username);
		                }
		            }, new HashMap<String, String>() {
		                {
		                	put("User-Agent", UserAgent );
		                	put("Cookie", temp );
		                	put("Accept", "application/json, text/javascript, */*; q=0.01" );
		                }
		            });
		//  System.out.println(result1);
		  com.alibaba.fastjson.JSONArray temp1 = result1.getJSONArray("cookies");
		          tempInfo = temp1.getJSONObject(0).getString("name")+"="+
							 temp1.getJSONObject(0).getString("value")+";"+
							 temp1.getJSONObject(1).getString("name")+"="+
							 temp1.getJSONObject(1).getString("value")+";"+
							 temp1.getJSONObject(2).getString("name")+"="+
							 temp1.getJSONObject(2).getString("value")+";"+
							 temp1.getJSONObject(3).getString("name")+"="+
							 temp1.getJSONObject(3).getString("value")+";"+
							 temp1.getJSONObject(4).getString("name")+"="+
							 temp1.getJSONObject(4).getString("value");
					   	 	
	    }	
		    catch(Exception e)
			 {
				 Assert.fail("token获取失败");
				 e.printStackTrace(); 
			 } 
   	   		Cookies=Cookies+";"+tempInfo;
   	//	System.out.println(Cookies); 
   		  return Cookies;
   	 }
}
