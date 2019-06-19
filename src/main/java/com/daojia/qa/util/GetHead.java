package com.daojia.qa.util;

import java.util.HashMap;

public class GetHead {
public static HashMap<String, String> gethead(){
	   String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";      
		String Cookies="";
		GetCookiesTest getcookie=new GetCookiesTest();
		  Cookies=getcookie.getcookies();
		  HashMap<String, String> head=new  HashMap<String, String>();
		  head.put("Cookie",Cookies );
		  head.put("User-Agent",UserAgent );
		  return head;
}
public static HashMap<String, String> getheadbyfinal(){
	   String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";      
		String Cookies="";
		GetCookiesTest getcookie=new GetCookiesTest();
		  String cookies="JSESSIONID=21A6D82C3D80FCA396C83B11D1B26024; Hm_lvt_9d483e9e48ba1faa0dfceaf6333de846=1511318382,1511322861,1511323177; Hm_lpvt_9d483e9e48ba1faa0dfceaf6333de846=1511323256; _djinpassuser=551356360296146; _djinpasstkt=\"WtdRz1rhn4AKVVYCeVWLh/4B2sUWrRyX8C7HL7pjTufZfw/NwaGfZhU6Bp8TLFOOskDYgvqKzT+ccwH2n7Lu0Q==\"";
		  HashMap<String, String> head=new  HashMap<String, String>();
		  head.put("Cookie",cookies);
		  head.put("User-Agent",UserAgent );
		  return head;
}
public static HashMap<String, String> gethead(String userName,String password){
	   String UserAgent="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";      
		String Cookies="";
		GetCookiesTest getcookie=new GetCookiesTest();
		  Cookies=getcookie.getcookies(userName,password);
		  HashMap<String, String> head=new  HashMap<String, String>();
		  head.put("Cookie",Cookies );
		  head.put("User-Agent",UserAgent );
		  return head;
}
}
