package com.daojia.qa.dwf;

import java.util.HashMap;

import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.util.GetHead;

public class GetMattersType  extends BaseTest<T>{
	String method_url="";

	/*
	    * 获取办理事项数据接口
	    * */
	@Test( description="办理事项数据接口")
	public void submitGetMattersType1(){
		method_url="http://fmsystem205.djtest.cn/stamp/getmatterstype";
		HashMap<String, String> param=new  HashMap<String, String>();
		  param.put("no","no" );
		  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
			 Logger.log("获取repData>>>>>>>>"+result);
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"成功");

			 JSONArray reparray=(JSONArray) result.get("repData");
		
			 String mattresString="员工个人事务、银行相关事务（财务专用）、发票税务相关事务（税务专用）、"
			 		+ "招聘相关事务、人事相关事务、常规行政事务（行政专用）、外部审计事务、企业询证函、诉讼处理（法务专用）、企业年检事务、"
			 		+ "业务对账单、设立/关闭集团内分/子公司相关事务、竞标材料、争议处理、申请58体系关联关系证明、投融资相关事务、因业务合作提供材料、广告投放业务（在线投放，车身广告等）、"
			 		+ "商标事务、行业协会相关事务、政府业务（申请政府补贴，日常报告等）、行政稽查、网站申请，域名备案，企业账号注册变更注销等（市场部专用）";
			if(reparray.size()>0){
				
				for(int i=0;i<reparray.size();i++){
					 JSONObject repjson1=(JSONObject) reparray.get(i);
					 if(mattresString.contains( repjson1.get("value").toString())){
						 Assert.assertEquals("成功","成功");
					 }
					
				 }
			}else{
				 Logger.log("没有办理事项");
			}
			 
			
	}
	


	
    
}
