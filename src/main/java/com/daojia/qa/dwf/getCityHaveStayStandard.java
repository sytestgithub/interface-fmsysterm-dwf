package com.daojia.qa.dwf;

import java.util.HashMap;
import java.util.Map;

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
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class getCityHaveStayStandard extends BaseTest<T>{
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
	 * 获取城市列表
	 * @return
	 */
		@Test(dataProvider = "getCityHaveStayStandardParam", description="获取城市列表")
		public void getCityHaveStayStandard1(String name){
			method_url="http://fmsystem205.djtest.cn/common/getcityhavestaystandard";
//			method_url="http://10.253.107.227:80/common/getcityhavestaystandard";
			HashMap<String, String> param=new  HashMap<String, String>();
			
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 Logger.log("获取repData>>>>>>>>"+result);
			 Assert.assertEquals(result.get("repCode").toString().trim(), "0");
			 Assert.assertEquals(result.get("message").toString().trim(), "成功");
			 JSONObject repdatas=(JSONObject) result.get("repData");
			 JSONArray repdata=(JSONArray) repdatas.get("citySubjectResultList");  //  有问题啊！！
			 for(int i=0;i<repdata.size();i++){
					JSONObject redatejson=repdata.getJSONObject(i);
					Logger.log("第几个"+i+"name名字"+redatejson.get("cityName").toString().trim());
					String sqlofcitysubject= "SELECT * FROM t_city_subject WHERE  is_vaild='1' and  city_name='"+redatejson.get("cityName").toString().trim()+"'";
					Logger.log("打印sql结果--》》"+sqlofcitysubject);
					 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
						
						resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofcitysubject);
//				 Assert.assertEquals(resultparambacks.get("city_type").toString().trim(),redatejson.get("cityType").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("major_id").toString().trim(),redatejson.get("majorId").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("city_name").toString().trim(),redatejson.get("cityName").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("city_money").toString().trim(),redatejson.get("cityMoney").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("allowance_money").toString().trim(),redatejson.get("allowanceMoney").toString().trim()); 
//				 Assert.assertEquals(resultparambacks.get("is_vaild").toString().trim(),redatejson.get("isVaild").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("pinyin").toString().trim(),redatejson.get("pingyin").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("weight").toString().trim(),redatejson.get("weight").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("pid").toString().trim(),redatejson.get("pid").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("pinyin_short").toString().trim(),redatejson.get("pinyinShort").toString().trim()); 
				 Assert.assertEquals(resultparambacks.get("id").toString().trim(),redatejson.get("id").toString().trim()); 
//				 Assert.assertEquals(resultparambacks.get("is_leaf").toString().trim(),redatejson.get("isLeaf").toString().trim()); 
			 }
			
		}
		@DataProvider
	    public static Object[][] getCityHaveStayStandardParam() {
	        return new Object[][]{

	        		{"gethaveofcity"}

	        };
	        }
}
