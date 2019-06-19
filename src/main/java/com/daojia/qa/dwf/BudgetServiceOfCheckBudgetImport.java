package com.daojia.qa.dwf;

import java.util.HashMap;

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
import com.daojia.qa.util.GetHead;

public class BudgetServiceOfCheckBudgetImport extends BaseTest<T>{
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
    * 校验是否导入预算接口
    * */
	@Test(dataProvider = "checkBudgetImportParam", description="校验预算是否导入过")
	public void checkBudgetImport(String name){
		method_url="http://fmsystem205.djtest.cn/common/checkbudgetimport";
		HashMap<String, String> param=new  HashMap<String, String>();
		String costDetailType="";
		String costCenter="";
		String year="";
		String month="";
		String sqlofBudget;
		if(name.equals("conditionisnull")){
			 costDetailType="";
			 costCenter="";
			 year="";
			 month="";
		}else if(name.equals("costDetailTypeisnull")){
			 costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			
		}else if(name.equals("costCenterisnull")){
			costDetailType="010";
			 year="2017";
			 month="09";
			
		}else if(name.equals("yearisnull")){
			costDetailType="010";
			 costCenter="10.00.78.00.6";
			 month="09";
			
		}else if(name.equals("monthisnull")){
			costDetailType="010";
			 year="2017";
			 costCenter="10.00.78.00.6";
			
		}else if(name.equals("conditionisnothaveconstdetailtypevalue")){
			costDetailType="8888";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			
		}else if(name.equals("conditionisnothavecostcentervalue")){
			costDetailType="010";
			costCenter="111.001.78.00.6";
			 year="2017";
			 month="09";
			
		}else if(name.equals("conditionisnothaveyearvalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2012";
			 month="09";
			
		}else if(name.equals("conditionisnothavemonthvalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="13";
			
		}else if(name.equals("conditionishavemonthvalue1")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="9";
			
		}else if(name.equals("conditionishavevalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			
		}else{
			  Logger.log("没有合适的case");
		}
		 param.put("costDetailType",costDetailType );
		 param.put("costCenter", costCenter);
		 param.put("year", year);
		 param.put("month", month);
		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
		 JSONObject repData=(JSONObject) result.get("repData");
		 Logger.log("获取repData>>>>>>>>"+result);
			if(name.equals("conditionisnull")||name.equals("costDetailTypeisnull")
					||name.equals("yearisnull")||name.equals("monthisnull")
					||name.equals("conditionisnothaveconstdetailtypevalue")||name.equals("conditionisnothavecostcentervalue")
					||name.equals("conditionisnothaveyearvalue")||name.equals("conditionisnothavemonthvalue")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"成功");
				 Assert.assertEquals(repData.get("result").toString().trim(),"false");
			}else if(name.equals("conditionishavevalue")||name.equals("costCenterisnull")||name.equals("conditionishavemonthvalue1")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"成功");
				 Assert.assertEquals(repData.get("result").toString().trim(),"true");
			}else{
				 Logger.log("没有合适的case，无效验证");
			}
	}
	 @DataProvider
     public static Object[][] checkBudgetImportParam() {
         return new Object[][]{
//        		   {"conditionisnull"},{"costDetailTypeisnull"},
//        		   {"costCenterisnull"},{"yearisnull"},{"monthisnull"},
//        		   {"conditionisnothaveconstdetailtypevalue"},{"conditionisnothavecostcentervalue"},
//        		   {"conditionisnothaveyearvalue"},{"conditionisnothavemonthvalue"},{"conditionishavemonthvalue1"},
//        		   {"conditionishavevalue"},
        		 {"conditionishavemonthvalue1"}
         };
         }
}
