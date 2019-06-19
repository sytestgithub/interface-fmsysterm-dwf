package com.daojia.qa.dwf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.daojia.qa.entity.BudgetCheckDto;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.mysql.fabric.xmlrpc.base.Array;

public class BudgetServiceOfGetListByCondition extends BaseTest<T>{
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
    * 获取预算接口
    * */
	@Test(dataProvider = "getListByConditionParam", description="获取预算接口")
	public void getListByCondition(String name){
			method_url="http://fmsystem205.djtest.cn/common/getbudgetview";
			HashMap<String, String> param=new  HashMap<String, String>();
			String costDetailType="";
			String costDetailType1="";
			String costCenter="";
			String year="";
			String month="";
			String month1="";
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
				
			}else if(name.equals("conditionishavevalue")){
				costDetailType="010";
				costCenter="10.00.78.00.6";
				 year="2017";
				 month="09";
				
			}else if(name.equals("conditionishavevaluemore")){
				costDetailType="010";
				costCenter="10.00.78.00.6";
				 year="2017";
				 month="09";
					costDetailType1="011";
					month1="10";
				
			}else{
				  Logger.log("没有合适的case");
			}
			
			BudgetCheckDto dto=new BudgetCheckDto();
			dto.setCostDetailType(costDetailType);
			dto.setCostCenter(costCenter);
			dto.setYear(year);
			dto.setMonth(month);
			BudgetCheckDto dto1=new BudgetCheckDto();
			dto1.setCostDetailType(costDetailType1);
			dto1.setCostCenter(costCenter);
			dto1.setYear(year);
			dto1.setMonth(month);
			BudgetCheckDto dto2=new BudgetCheckDto();
			dto2.setCostDetailType(costDetailType);
			dto2.setCostCenter(costCenter);
			dto2.setYear(year);
			dto2.setMonth(month1);
			List<BudgetCheckDto> checkDtos =new ArrayList<BudgetCheckDto>();
			if(name.equals("conditionishavevaluemore")){
				checkDtos.add(dto);	
				checkDtos.add(dto1);	
				checkDtos.add(dto2);	
			}
			checkDtos.add(dto);			
			 param.put("checkDtos", JSONObject.toJSONString(checkDtos) );
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果"+result);	
			JSONObject repDatas=(JSONObject) result.get("repData");
			 JSONArray repData=(JSONArray) repDatas.get("budgetList");
				if(repData.isEmpty()){
					if(name.equals("conditionisnull")||name.equals("costDetailTypeisnull")||name.equals("costCenterisnull")
							||name.equals("yearisnull")||name.equals("monthisnull")
							||name.equals("conditionisnothaveconstdetailtypevalue")||name.equals("conditionisnothavecostcentervalue")
							||name.equals("conditionisnothaveyearvalue")||name.equals("conditionisnothavemonthvalue")){
						 int redatasize=repData.size();
						 sqlofBudget= "SELECT a.budget_money , a.depart_name , a.cost_center , a.cost_code , b.cost_detail_type AS cost_detaile_type, a.occupy_money , a.used_money, a.surplus_money, a.year, a.month "
									+ "FROM t_budget a LEFT JOIN t_cost_subject b ON a.cost_detaile_type = b.cost_code WHERE a.cost_code ='"
									+ costCenter
									+ "' AND cost_detaile_type='"
									+ costDetailType
									+ "' AND YEAR ='"
									+ year
									+ "' AND MONTH= '" + month+ "'";
							int  sqlsize=GetDbMethod.getCostDetailSelctTestListSize(db,sqlofBudget);
					
						 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						 Assert.assertEquals(result.get("message").toString().trim(),"成功");
						 Assert.assertEquals(redatasize, sqlsize);
					 }
				}else if(name.equals("conditionishavevalue")){
					JSONObject redatejson=repData.getJSONObject(0);
					
					sqlofBudget= "SELECT a.budget_money , a.depart_name , a.cost_center , a.cost_code , b.cost_detail_type AS cost_detaile_type, a.occupy_money , a.used_money, a.surplus_money, a.year, a.month "
							+ "FROM t_budget a LEFT JOIN t_cost_subject b ON a.cost_detaile_type = b.cost_code WHERE a.cost_code ='"
							+ costCenter
							+ "' AND cost_detaile_type='"
							+ costDetailType
							+ "' AND YEAR ='"
							+ year
							+ "' AND MONTH= '" + month+ "'";
					Map<String, Object>  resultparambacks=new HashMap<String, Object>();
					resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofBudget);
					 Logger.log("resultparambacks结果是>>>"+resultparambacks.toString());
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(resultparambacks.get("budget_money").toString().trim(), redatejson.get("budgetMoney").toString().trim());
					 Assert.assertEquals(resultparambacks.get("cost_code").toString().trim(), redatejson.get("costCode").toString().trim());
					 Assert.assertEquals(resultparambacks.get("month").toString().trim(), redatejson.get("month").toString().trim());
					 Assert.assertEquals(resultparambacks.get("year").toString().trim(), redatejson.get("year").toString().trim());
					 Assert.assertEquals(resultparambacks.get("cost_center").toString().trim(), redatejson.get("costCenter").toString().trim());
					 Assert.assertEquals(resultparambacks.get("occupy_money").toString().trim(), redatejson.get("occupyMoney").toString().trim());
					 Assert.assertEquals(resultparambacks.get("surplus_money").toString().trim(), redatejson.get("surplusMoney").toString().trim());
					 Assert.assertEquals(resultparambacks.get("used_money").toString().trim(), redatejson.get("usedMoney").toString().trim());
//					 Assert.assertEquals(resultparambacks.get("cost_detaile_type").toString().trim(), redatejson.get("costDetaileType").toString().trim());
					 Assert.assertEquals(resultparambacks.get("depart_name").toString().trim(), redatejson.get("departName").toString().trim());
				 }else if(name.equals("conditionishavevaluemore")){
					 int redatasize=repData.size();
						for(int i=0;i<redatasize;i++){
							JSONObject redatejson=repData.getJSONObject(i);
							costDetailType=redatejson.get("costDetaileType").toString().trim();
							if(costDetailType.equals("差旅费")){
								costDetailType="010";
							}else if(costDetailType.equals("误餐费")){
								costDetailType="011";
							}
							costCenter=redatejson.get("costCode").toString().trim();
							 year=redatejson.get("year").toString().trim();
							 month=redatejson.get("month").toString().trim();
							sqlofBudget= "SELECT a.budget_money , a.depart_name , a.cost_center , a.cost_code , b.cost_detail_type AS cost_detaile_type, a.occupy_money , a.used_money, a.surplus_money, a.year, a.month "
									+ "FROM t_budget a LEFT JOIN t_cost_subject b ON a.cost_detaile_type = b.cost_code WHERE a.cost_code ='"
									+costCenter
									+ "' AND cost_detaile_type='"
									+costDetailType
									+ "' AND YEAR ='"
									+year 
									+ "' AND MONTH= '" +month+ "'";
							Logger.log("打印sql结果--》》"+sqlofBudget);
							 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
								
								resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofBudget);
								Logger.log(sqlofBudget+"resultparambacks结果是>>>"+resultparambacks.toString());
								 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
								 Assert.assertEquals(result.get("message").toString().trim(),"成功");
								 Assert.assertEquals(resultparambacks.get("budget_money").toString().trim(), redatejson.get("budgetMoney").toString().trim());
								 Assert.assertEquals(resultparambacks.get("cost_code").toString().trim(), redatejson.get("costCode").toString().trim());
								 Assert.assertEquals(resultparambacks.get("month").toString().trim(), redatejson.get("month").toString().trim());
								 Assert.assertEquals(resultparambacks.get("year").toString().trim(), redatejson.get("year").toString().trim());
								 Assert.assertEquals(resultparambacks.get("cost_center").toString().trim(), redatejson.get("costCenter").toString().trim());
								 Assert.assertEquals(resultparambacks.get("occupy_money").toString().trim(), redatejson.get("occupyMoney").toString().trim());
								 Assert.assertEquals(resultparambacks.get("surplus_money").toString().trim(), redatejson.get("surplusMoney").toString().trim());
								 Assert.assertEquals(resultparambacks.get("used_money").toString().trim(), redatejson.get("usedMoney").toString().trim());
								 Assert.assertEquals(resultparambacks.get("cost_detaile_type").toString().trim(), redatejson.get("costDetaileType").toString().trim());
								 Assert.assertEquals(resultparambacks.get("depart_name").toString().trim(), redatejson.get("departName").toString().trim());
							
						}
				 }else{
					 Logger.log("没有合适的case，无效验证");
				 }
			 

			 Logger.log("获取结果---->>>>"+result);
	}
	
	   @DataProvider
       public static Object[][] getListByConditionParam() {
           return new Object[][]{
        		   {"conditionisnull"},{"costDetailTypeisnull"},
        		   {"costCenterisnull"},{"yearisnull"},{"monthisnull"},
        		   {"conditionisnothaveconstdetailtypevalue"},{"conditionisnothavecostcentervalue"},
        		   {"conditionisnothaveyearvalue"},{"conditionisnothavemonthvalue"},
        		   {"conditionishavevalue"}, {"conditionishavevaluemore"},
//        		   {"conditionishavevalue"}
        		
           };
       }
}
