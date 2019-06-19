package com.daojia.qa.dwf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.daojia.qa.entity.BudgetCheckDto;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class checkBudgetForTravelRepayment  extends BaseTest<T>{
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
	 * 获取未关联的申请单信息
	 * @return
	 */
		@Test(dataProvider = "checkBudgetForTravelRepaymentParam", description=" 获取未关联的申请单信息")
		public void checkBudgetForTravelRepayment1(String name){
			method_url="http://fmsystem205.djtest.cn/common/checkbudgetfortravelrepayment";
//			method_url="http://10.253.7.165:80/common/checkbudgetfortravelrepayment";
			String applyFormId="CLSQ201711136196";
			HashMap<String, String> param=new  HashMap<String, String>();
			List<BudgetCheckDto> bugetcheckdtos=new ArrayList<BudgetCheckDto>();
			BudgetCheckDto  bugetcheckdto=new BudgetCheckDto();
			BudgetCheckDto  bugetcheckdto1=new BudgetCheckDto();
			BudgetCheckDto  bugetcheckdto2=new BudgetCheckDto();
			BudgetCheckDto  bugetcheckdto3=new BudgetCheckDto();
			String costDetailType="";
			String costCenter="";
			String year="";
			String month="";
			String month1="";
			String money="";
			String money1="";
			String money2="";
			String costDetailType1="";	
			String costDetailType2="";	
			String reslut="";
			String sqlofBudget;
			 if(name.equals("checkBudgetsamll")){
					costDetailType="010";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money="2";
				}else if(name.equals("checkBudgetequer")){
					String budgetsql="SELECT * FROM t_budget WHERE cost_code='10.00.78.00.6' AND YEAR='2017' AND MONTH='11' AND cost_detaile_type='010'";
					costDetailType="010";	
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money=GetDbMethod.getCostDetailSelctTest(db, budgetsql, "surplus_money");
				}else if(name.equals("checkBudgetsuper")){
					costDetailType="010";
					
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money="111111763.9";
				}else if(name.equals("checkBudgetothermonth")){
					costDetailType="010";
					month="7";
					money="9693.00";
					costCenter="10.00.78.00.6";
					 year="2017";	
				}else if(name.equals("checkBudgetsonemore")){
					costDetailType="010";
					
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money="763.9";
					 money1="362";
					 money2="362";
				}else if(name.equals("checkBudgetstwomore")){
					costDetailType="010";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money="362";
					 money1="763.9";
					 money2="362";
				}else if(name.equals("checkBudgetsthreemore")){
					costDetailType="010";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money="362";
					 money1="362";
					 money2="763.9";
				}else if(name.equals("checkBudgetsthreemore")){
					costDetailType="010";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 money="362";
					 money1="362";
					 money2="763.9";
				}else if(name.equals("conditionishavevaluemoresuperfourother")){
					costDetailType="010";
					costDetailType1="011";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 month1="11";
					 money="362";
					 money1="362";
					 money2="763.9";
				}else if(name.equals("conditionishavevaluemoresuperfourother1")){
					costDetailType="010";
					costDetailType1="011";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 month1="07";
					 money="362";
					 money1="362";
					 money2="362";
				}else if(name.equals("conditionishavevaluemoresuperfouronlysuper")){
					costDetailType="010";
					costDetailType1="011";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 month1="07";
					 money="1";
					 money1="362";
					 money2="362";
				}else if(name.equals("conditionishavevaluemorefour")){
					costDetailType="010";
					costDetailType1="011";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 month1="07";
					 money="1";
					 money1="3";
					 money2="32";
				}else if(name.equals("conditionishavevaluemorefour1")){
					costDetailType="010";
					costDetailType1="011";
					costCenter="10.00.78.00.6";
					 year="2017";
					 month="11";
					 month1="7";
					 money="1";
					 money1="3";
					 money2="32";
				}else{
				
					  Logger.log("没有合适的case");
				}
	
			 
			 if(name.equals("checkBudgetsamll")||name.equals("checkBudgetsuper")||name.equals("checkBudgetequer")||name.equals("checkBudgetothermonth")){
				 
					bugetcheckdto.setCostCenter(costCenter);
					bugetcheckdto.setCostDetailType(costDetailType);
						bugetcheckdto.setMonth(month);	
						bugetcheckdto.setMoney(money);
				
					
					bugetcheckdto.setYear(year);
					bugetcheckdtos.add(bugetcheckdto);
					param.put("checkDtos",  JSONObject.toJSONString(bugetcheckdtos));
					param.put("applyFormId", applyFormId);
					 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
					 Logger.log("获取repData>>>>>>>>"+result);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 
					 if(!result.get("repData").toString().isEmpty()){
						 JSONObject repDatas=(JSONObject) result.get("repData");
						 JSONArray repData=(JSONArray) repDatas.get("budgetCheckList");
						 for(int i=0;i<repData.size();i++){
							 String getreslut= repData.getJSONObject(i).get("result").toString().trim();
						 if(name.equals("checkBudgetsamll")||name.equals("checkBudgetequer")){
							 Assert.assertEquals(getreslut,"0");
						 }else if(name.equals("checkBudgetsuper")){
							 Assert.assertEquals(getreslut,"1");
							 }else{
								 Logger.log("没有合适的值");
							 }
						 }
					
					 }
					 
					 
			 }else if(name.equals("checkBudgetsonemore")||name.equals("checkBudgetstwomore")||name.equals("checkBudgetsthreemore")
					 ||name.equals("conditionishavevaluemoresuperfourother")||name.equals("conditionishavevaluemoresuperfourother1")
					 ||name.equals("conditionishavevaluemoresuperfouronlysuper")||name.equals("conditionishavevaluemorefour")
					 ||name.equals("conditionishavevaluemorefour1")){
				
				
				 bugetcheckdto.setCostCenter(costCenter);
					bugetcheckdto.setCostDetailType(costDetailType);
					bugetcheckdto.setMoney(money);
					bugetcheckdto.setMonth(month);
					bugetcheckdto.setYear(year);
					bugetcheckdto1.setCostCenter(costCenter);
					bugetcheckdto1.setCostDetailType(costDetailType);
					bugetcheckdto1.setMoney("10809");
					bugetcheckdto1.setMonth(month);
					bugetcheckdto1.setYear(year);
					bugetcheckdto2.setCostCenter(costCenter);
					bugetcheckdto2.setCostDetailType(costDetailType);
					bugetcheckdto2.setMoney(money2);
					bugetcheckdto2.setMonth(month);
					bugetcheckdto2.setYear(year);
					bugetcheckdtos.add(bugetcheckdto);
					bugetcheckdtos.add(bugetcheckdto1);
					bugetcheckdtos.add(bugetcheckdto2);
					
					if(name.equals("conditionishavevaluemoresuperfourother")||name.equals("conditionishavevaluemoresuperfourother1")
							||name.equals("conditionishavevaluemoresuperfouronlysuper")||name.equals("conditionishavevaluemorefour")
							||name.equals("conditionishavevaluemorefour1")){
						bugetcheckdto3.setCostCenter(costCenter);
						bugetcheckdto3.setCostDetailType(costDetailType1);
						if(name.equals("conditionishavevaluemoresuperfouronlysuper")){
							bugetcheckdto3.setMoney("1111111");
						}else{
							bugetcheckdto3.setMoney("1");
						}

						bugetcheckdto3.setMonth(month1);
						bugetcheckdto3.setYear(year);
						bugetcheckdtos.add(bugetcheckdto3);
					}
					param.put("checkDtos",  JSONObject.toJSONString(bugetcheckdtos));
					param.put("applyFormId", applyFormId);
					 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
					 Logger.log("获取repData>>>>>>>>"+result);
					 if(!result.get("repData").toString().isEmpty()){
						 JSONObject repDatas=(JSONObject) result.get("repData");
						 JSONArray repData=(JSONArray) repDatas.get("budgetCheckList");
						 for(int i=0;i<repData.size();i++){
							 String getreslut= repData.getJSONObject(i).get("result").toString().trim();
						 if(getreslut.equals("0")){
							 Logger.log("未超出预算"+repData.getJSONObject(i));
						 }else{
							 Logger.log("超出预算"+repData.getJSONObject(i));
							 }
						 }
					
					 }
			 }
			
	
		}
		@DataProvider
	    public static Object[][] checkBudgetForTravelRepaymentParam() {
	        return new Object[][]{

//	        		{"checkBudgetsamll"},{"checkBudgetsuper"},{"checkBudgetequer"},{"checkBudgetothermonth"},
//	        		{"checkBudgetsonemore"},{"checkBudgetstwomore"},{"checkBudgetsthreemore"},{"conditionishavevaluemoresuperfourother"},
//	        		{"conditionishavevaluemoresuperfourother1"},{"conditionishavevaluemoresuperfouronlysuper"},
//	        		{"conditionishavevaluemoresuperfouronlysuper"},{"conditionishavevaluemorefour"}
	        		{"conditionishavevaluemorefour1"}
	        };
	        }
}
