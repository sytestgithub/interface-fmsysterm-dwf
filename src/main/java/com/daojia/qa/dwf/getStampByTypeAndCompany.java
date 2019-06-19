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

public class getStampByTypeAndCompany  extends BaseTest<T>{
	String method_url="";

	/*
	    * 获取办理事项数据接口
	    * */
	@Test( dataProvider = "submitGetStampByTypeAndCompanyParam",description="根据已选择的公司和申请类别从印证管理获取数据")
	public void submitGetStampByTypeAndCompany1(String name){
		method_url="http://fmsystem205.djtest.cn/stamp/getstampbytypeandcompany";
		String applyType="";
		String companyName="";
		if(name.equals("condtionisexist")){
			applyType="";
			companyName="";
		}else if(name.equals("condtionisnotexist")){
			applyType="";
			companyName="";
		}else if(name.equals("condtionisnotexist")){
			applyType="";
			companyName="";
		}else{
			Logger.log("不存在合适的case");
		}
		HashMap<String, String> param=new  HashMap<String, String>();
		  param.put("applyType",applyType);
		  param.put("companyName",companyName);
		  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
			 Logger.log("获取repData>>>>>>>>"+result);
			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
			 Assert.assertEquals(result.get("message").toString().trim(),"成功");

			 JSONArray reparray=(JSONArray) result.get("repData");
		
	}
    public static Object[][] submitGetStampByTypeAndCompanyParam() {
        return new Object[][]{ 
        		{"condtionisexist"},{"condtionisnotexist"},{"companynotmatchtype"}
        		
        		
        };
        }
}
