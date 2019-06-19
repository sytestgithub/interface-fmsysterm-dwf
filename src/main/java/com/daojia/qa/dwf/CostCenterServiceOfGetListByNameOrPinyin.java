package com.daojia.qa.dwf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class CostCenterServiceOfGetListByNameOrPinyin extends BaseTest<T>{
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
 * 应用于预算编辑页面，查找成本中心搜索框体使用
 * */
	@Test(dataProvider = "getListByNameOrPinyinParam", description="获取成本中心列表")
public void getListByNameOrPinyin(String name){
		method_url="http://fmsystem205.djtest.cn/common/getcostcenterinfo";
		  HashMap<String, String> param=new  HashMap<String, String>();
		  String input="";
		  String sqlofcenter="";
		  if(name.equals("costcenterisnull")){
			  input="";
		  }else if(name.equals("costcenternothave")){
			  input="AAA";
			  
		  }else if(name.equals("costcenterhave")){
			  input="技术中心413";
			  
		  }else{
			  Logger.log("没有合适的case");
		  }
		  param.put("input",input );
	
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 

			 Logger.log("获取结果---->>>>"+result);
				Logger.log("获取结果---->>>>"+result.get("repData"));
				JSONObject repDatas=(JSONObject) result.get("repData");
				JSONArray repData=(JSONArray) repDatas.get("costCenter");
			 if(!result.isEmpty()){
				
					if(!repData.isEmpty()&&name.equals("costcenterhave")){
					JSONObject redatejson=repData.getJSONObject(0);
					 sqlofcenter="SELECT *FROM t_cost_center WHERE cost_center_name='技术中心413'";																
					Map<String, Object>  resultparambacks=new HashMap<String, Object>();
					
					resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofcenter);
//				
					 Logger.log("resultparambacks结果是>>>"+resultparambacks.toString()+""+resultparambacks.get("costCenterCode")+">>>redatejson获取的数据"+redatejson.getString("costCenterCode"));
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(resultparambacks.get("id").toString().trim(), redatejson.get("id").toString().trim());
					Assert.assertEquals(resultparambacks.get("string_id").toString().trim(), redatejson.get("stringId").toString().trim());
					Assert.assertEquals(resultparambacks.get("create_time").toString().trim(), redatejson.get("createTime").toString().trim());
					Assert.assertEquals(resultparambacks.get("update_time").toString().trim(), redatejson.get("updateTime").toString().trim());
					Assert.assertEquals(resultparambacks.get("cost_center_name").toString().trim(), redatejson.get("costCenterName").toString().trim());
					Assert.assertEquals(resultparambacks.get("cost_center_code").toString().trim(), redatejson.get("costCenterCode").toString().trim());
					Assert.assertEquals(resultparambacks.get("pay_company").toString().trim(), redatejson.get("payCompany").toString().trim());
					Assert.assertEquals(resultparambacks.get("pay_account").toString().trim(), redatejson.get("payAccount").toString().trim());
					Assert.assertEquals(resultparambacks.get("valid").toString().trim(), redatejson.get("valid").toString().trim());
					Assert.assertEquals(resultparambacks.get("option_people").toString().trim(), redatejson.get("optionPeople").toString().trim());
					Assert.assertEquals(resultparambacks.get("update_people").toString().trim(), redatejson.get("updatePeople").toString().trim());
					Assert.assertEquals(resultparambacks.get("relation_status").toString().trim(), redatejson.get("relationStatus").toString().trim());
					Assert.assertEquals(resultparambacks.get("pinyin").toString().trim(), redatejson.get("pinyin").toString().trim());
					Assert.assertEquals(resultparambacks.get("pinyin_short").toString().trim(), redatejson.get("pinyinShort").toString().trim());
					Assert.assertEquals(resultparambacks.get("is_complete").toString().trim(), redatejson.get("isComplete").toString().trim());
					Assert.assertEquals(resultparambacks.get("service_line").toString().trim(), redatejson.get("serviceLine").toString().trim());
					}else if(!repData.isEmpty()&&name.equals("costcenterisnull")){
						
						int redatasize=repData.size();
						for(int i=0;i<redatasize;i++){
							JSONObject redatejson=repData.getJSONObject(i);
							
							 sqlofcenter="SELECT *FROM t_cost_center WHERE cost_center_name='"+redatejson.get("costCenterName")+"'";	
							 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
								
								resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofcenter);
								 Assert.assertEquals(resultparambacks.get("id").toString().trim(), redatejson.get("id").toString().trim());
									Assert.assertEquals(resultparambacks.get("string_id").toString().trim(), redatejson.get("stringId").toString().trim());
									Assert.assertEquals(resultparambacks.get("create_time").toString().trim(), redatejson.get("createTime").toString().trim());
									Assert.assertEquals(resultparambacks.get("update_time").toString().trim(), redatejson.get("updateTime").toString().trim());
									Assert.assertEquals(resultparambacks.get("cost_center_name").toString().trim(), redatejson.get("costCenterName").toString().trim());
									Assert.assertEquals(resultparambacks.get("cost_center_code").toString().trim(), redatejson.get("costCenterCode").toString().trim());
									Assert.assertEquals(resultparambacks.get("pay_company").toString().trim(), redatejson.get("payCompany").toString().trim());
									Assert.assertEquals(resultparambacks.get("pay_account").toString().trim(), redatejson.get("payAccount").toString().trim());
									Assert.assertEquals(resultparambacks.get("valid").toString().trim(), redatejson.get("valid").toString().trim());
									Assert.assertEquals(resultparambacks.get("option_people").toString().trim(), redatejson.get("optionPeople").toString().trim());
									Assert.assertEquals(resultparambacks.get("update_people").toString().trim(), redatejson.get("updatePeople").toString().trim());
									Assert.assertEquals(resultparambacks.get("relation_status").toString().trim(), redatejson.get("relationStatus").toString().trim());
									Assert.assertEquals(resultparambacks.get("pinyin").toString().trim(), redatejson.get("pinyin").toString().trim());
									Assert.assertEquals(resultparambacks.get("pinyin_short").toString().trim(), redatejson.get("pinyinShort").toString().trim());
									Assert.assertEquals(resultparambacks.get("is_complete").toString().trim(), redatejson.get("isComplete").toString().trim());
									Assert.assertEquals(resultparambacks.get("service_line").toString().trim(), redatejson.get("serviceLine").toString().trim());
						}
						 sqlofcenter=" SELECT * FROM t_cost_center WHERE  valid='是'";	
						int  sqlsize=GetDbMethod.getCostDetailSelctTestListSize(db,sqlofcenter);
						 Logger.log("接口返回的总数"+redatasize+"数据库返回的总数"+sqlsize);
						 Assert.assertEquals(redatasize, sqlsize);
						 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					}else if(repData.isEmpty()&&name.equals("costcenternothave")){
						int redatasize=repData.size();
						 sqlofcenter=" SELECT * FROM t_cost_center WHERE cost_center_name='AAA'";	
						int  sqlsize=GetDbMethod.getCostDetailSelctTestListSize(db,sqlofcenter);
				
						 Logger.log("接口返回的总数"+redatasize+"数据库返回的总数"+sqlsize);
						 Assert.assertEquals(redatasize, sqlsize);
						 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					}else{
						 Logger.log("repData结果是空>>>"+repData.toString());
					}
			
					
			 }else{
				 Logger.log("result结果是空>>>"+result);
			 }
		
		
	}
	
	   @DataProvider
       public static Object[][] getListByNameOrPinyinParam() {
           return new Object[][]{
        		   {"costcenterisnull"},{"costcenternothave"},{"costcenterhave"}
//        		   {"costcenterisnull"}
        		
           };
       }
}
