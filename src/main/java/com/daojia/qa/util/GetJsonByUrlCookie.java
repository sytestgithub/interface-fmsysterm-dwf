package com.daojia.qa.util;

import java.io.IOException;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.http.HttpRequest;

public class GetJsonByUrlCookie extends GetCookie{
	
	 public   JSONObject getJsonResult(String Url) throws IOException {
		 
		 GetCookie temp = new GetCookie();	
		 final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
		  final String Cookie=temp.getCookieResult();
		  System.out.println("Cookie:"+Cookie);
		//  final String Cookie="_djinpasstkt=Rimau5vcC+UUzP6tJ9k9kRpyExEvfNjrtLyhZWvp5LveLfrFrNkxGzYorx+Rt8JKBdHS+olWK8zL/Gz3xGmM0ceEiAgtzqct;_djinpassuser=1142053413065633";
		  JSONObject result = HttpRequest.doPostReturnResponseJson(Url,
		            new HashMap<String, String>() {
		                {}
		            }, new HashMap<String, String>() {
		                {
		                	put("User-Agent", UserAgent );
		    				put("Cookie", Cookie );
		    			
		                }
		            });
		  return result;
	 }
	 
	 public   JSONObject getJsonResultByCaishu(String Url,HashMap<String, String> Prama) throws IOException {
		 
		  GetCookie temp = new GetCookie();			
		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
		  final String Cookie=temp.getCookieResult();
		
		  JSONObject result = HttpRequest.doPostReturnResponseJson(Url,
		            Prama, new HashMap<String, String>() {
		                {
		                	put("User-Agent", UserAgent );
		    				put("Cookie", Cookie );
		    			
		                }
		            });
		  return result;
	 }

	 //获取工单系统的cookie ，有拦截器，需要手动更换cookie
	 public   JSONObject getJsonResultByCaishu_forWork(String Url,HashMap<String, String> Prama) throws IOException {
		  
		  GetCookie temp = new GetCookie();		
		  final String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
		//  final String Cookie="JSESSIONID=68029AA0A0364BAE6C42CB63EE6A8FD3; _djinpassuser=371334416695299; _djinpasstkt='PgzIJnYX9VQUzP6tJ9k9kRpyExEvfNjrKcvD8LVNqWH+8V75FZaM/cdL9PYIM/IQC+/fiR7L1NR0SQI7DJ2pOg=='; acl_username=liaoshixiang; acl_version=67314979432001395055; avl_token=33d2d42d385370953f519402638f00be5083b597312142e162a34837896230e5; acl_userId=80; acl_userRole=%2C";
		  final String Cookie=temp.getCookieResultPassBinghe();
		  JSONObject result = HttpRequest.doPostReturnResponseJson(Url,
		            Prama, new HashMap<String, String>() {
		                {
		                	put("User-Agent", UserAgent );
		    				put("Cookie", Cookie );
		    			
		                }
		            });
		  return result;
	 }



}
