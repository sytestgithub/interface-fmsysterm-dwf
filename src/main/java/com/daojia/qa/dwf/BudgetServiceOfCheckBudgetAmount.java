package com.daojia.qa.dwf;

import java.math.BigDecimal;
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

public class BudgetServiceOfCheckBudgetAmount extends BaseTest<T>{
	// 
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
    * 预算校验接口
    * */
	@Test(dataProvider = "checkBudgetAmountParam", description="校验预算")
	public void checkBudgetAmount(String name){
		method_url="http://fmsystem205.djtest.cn/common/checkbudgetamount";
//		method_url="http://10.253.105.125:80/common/checkbudgetamount";
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
		if(name.equals("conditionisnull")){
			 costDetailType="";
			 costCenter="";
			 month="";
			 year="";
			 money="";
			
			
		}else if(name.equals("costDetailTypeisnull")){
			 costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			 money="1";
		
			
		}else if(name.equals("costCenterisnull")){
			costDetailType="010";
			 year="2017";
			 month="09";
			 money="1";
			
		}else if(name.equals("yearisnull")){
			costDetailType="010";
			 costCenter="10.00.78.00.6";
			 month="09";
			 money="1";
			
		}else if(name.equals("monthisnull")){
			costDetailType="010";
			 year="2017";
			 costCenter="10.00.78.00.6";
			 money="1";
			
		}else if(name.equals("moneyisnotnumber")){
			costDetailType="010";
			 year="2017";
			 costCenter="10.00.78.00.6";
	
			 money="ABC";
			
		}else if(name.equals("moneyisnull")){
			costDetailType="010";
			 year="2017";
			 costCenter="10.00.78.00.6";
			 month="09";
			  money="";
		}else if(name.equals("conditionisnothaveconstdetailtypevalue")){
			costDetailType="8888";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			 money="1";
		}else if(name.equals("conditionisnothavecostcentervalue")){
			costDetailType="010";
			costCenter="111.001.78.00.6";
			 year="2017";
			 month="09";
			 money="1";
		}else if(name.equals("conditionisnothaveyearvalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2012";
			 month="09";
			 money="1";
		}else if(name.equals("conditionisnothavemonthvalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="13";
			 money="1";
		}else if(name.equals("conditionisnothavemoneyvalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="13";
			 money="111111111111111";
		}else if(name.equals("conditionishavevalue")){
			costDetailType="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="11";
			 money="2";
		}else if(name.equals("conditionishavevaluemore")){
			costDetailType="010";
			costDetailType1="011";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			 money="2";
			 money1="3";
			 money2="3";
		}else if(name.equals("conditionishavevaluemoresupertwo")){
			costDetailType="010";
			costDetailType1="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="9";
			 money="2";
			 money1="5568.70";
			 money2="1";
		}else if(name.equals("conditionishavevaluemoresupertwo1")){
			costDetailType="010";
			costDetailType1="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="9";
			 money="2";
			 money1="5568.70";
			 money2="1";
		}else if(name.equals("conditionishavevaluemoresuperone")){
			costDetailType="010";
			costDetailType1="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			 money1="2";
			 money="5569.71";
			 money2="1";
		}else if(name.equals("conditionishavevaluemoresuperthree")){
			costDetailType="010";
			costDetailType1="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			 month1="07";
			 money="2";
			 money1="2";
			 money2="5569.71";
		}else if(name.equals("conditionishavevaluemoresuperfourother")){
			costDetailType="010";
			costDetailType1="010";
			costDetailType2="010";
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="09";
			 month1="07";
			 money="2";
			 money1="2";
			 money2="5569.71";
		}else if(name.equals("conditionishavevaluesuper")){
			costDetailType="010";
	
			costCenter="10.00.78.00.6";
			 year="2017";
			 month="11";
			 String budgetsql="SELECT * FROM t_budget WHERE cost_code='10.00.78.00.6' AND YEAR='2017' AND MONTH='11' AND cost_detaile_type='010'";
			BigDecimal money11=new BigDecimal(GetDbMethod.getCostDetailSelctTest(db, budgetsql, "surplus_money").toString());
			money11=money11.add(new BigDecimal(1));
			money=money11.toString();
		}else{
			  Logger.log("没有合适的case");
		}
if(name.equals("conditionishavevaluemore")||name.equals("conditionishavevaluemoresuperone")
		 ||name.equals("conditionishavevaluemoresupertwo")||name.equals("conditionishavevaluemoresupertwo1")||name.equals("conditionishavevaluemoresuperthree")
		 ||name.equals("conditionishavevaluemoresuperfourother")){
	bugetcheckdto.setCostCenter(costCenter);
	bugetcheckdto.setCostDetailType(costDetailType);
	bugetcheckdto.setMoney(money);
	bugetcheckdto.setMonth(month);
	bugetcheckdto.setYear(year);
	bugetcheckdto1.setCostCenter(costCenter);
	bugetcheckdto1.setCostDetailType(costDetailType1);
	bugetcheckdto1.setMoney(money1);
	bugetcheckdto1.setMonth(month);
	bugetcheckdto1.setYear(year);
	bugetcheckdto2.setCostCenter(costCenter);
	bugetcheckdto2.setCostDetailType(costDetailType1);
	bugetcheckdto2.setMoney(money2);
	bugetcheckdto2.setMonth(month);
	bugetcheckdto2.setYear(year);

	bugetcheckdtos.add(bugetcheckdto);
	bugetcheckdtos.add(bugetcheckdto1);
	bugetcheckdtos.add(bugetcheckdto2);
	if(name.equals("conditionishavevaluemoresuperfourother")){
		bugetcheckdto3.setCostCenter(costCenter);
		bugetcheckdto3.setCostDetailType(costDetailType1);
		bugetcheckdto3.setMoney(money2);
		bugetcheckdto3.setMonth(month1);
		bugetcheckdto3.setYear(year);
		bugetcheckdtos.add(bugetcheckdto3);
	}
}else{
	bugetcheckdto.setCostCenter(costCenter);
	bugetcheckdto.setCostDetailType(costDetailType);
	bugetcheckdto.setMoney(money);
	bugetcheckdto.setMonth(month);
	bugetcheckdto.setYear(year);
	bugetcheckdtos.add(bugetcheckdto);
}
		
		 param.put("checkDtos", JSONObject.toJSONString(bugetcheckdtos));
		
		 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
		
		 Logger.log("获取repData>>>>>>>>"+result);
		 if(name.equals("moneyisnull")){
			
			 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
			 Assert.assertEquals(result.get("message").toString().trim(),"预算校验异常");
		 }else{
			 JSONObject repDatas=(JSONObject) result.get("repData");
			 JSONArray repData=(JSONArray) repDatas.get("budgetCheckList");
			 if(!repData.isEmpty()){
				 if(name.equals("conditionisnull")||name.equals("costDetailTypeisnull")
							||name.equals("yearisnull")||name.equals("monthisnull")||name.equals("moneyisnotnumber")
							||name.equals("conditionisnothaveconstdetailtypevalue")||name.equals("conditionisnothavecostcentervalue")
							||name.equals("conditionisnothaveyearvalue")||name.equals("conditionisnothavemonthvalue")
							||name.equals("conditionisnothavemoneyvalue")
							||name.equals("conditionishavevaluesuper")){
					 JSONObject redatejson=repData.getJSONObject(0);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(redatejson.get("result").toString().trim(),"1");
				 }else if(name.equals("conditionishavevalue")||name.equals("costCenterisnull")){
					 JSONObject redatejson=repData.getJSONObject(0);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(redatejson.get("result").toString().trim(),"0");
				 }else if(name.equals("conditionishavevaluemore")||name.equals("conditionishavevaluemoresuperone")
						 ||name.equals("conditionishavevaluemoresupertwo")||name.equals("conditionishavevaluemoresuperthree")
						 ||name.equals("conditionishavevaluemoresuperfourother")||name.equals("conditionishavevaluemoresupertwo1")){
					 for(int i=0;i<repData.size();i++){
						 Logger.log("conditionishavevaluemore有多少条预算："+repData.size());
						 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						 Assert.assertEquals(result.get("message").toString().trim(),"成功");
						  String getreslut= repData.getJSONObject(i).get("result").toString().trim();
//						 Logger.log("获取checkDtos>>>>>>>>"+"获取的是repData.getJSONObject"+i+"==>"+repData.getJSONObject(i)+"获取的结果"+getreslut);
						if(getreslut.equals("0")){
							Logger.log("预算未超额"+repData);
						}else if(getreslut.equals("1")){
							Logger.log("预算超额"+repData);
						}else{
							Logger.log("出现异常");
						}
					 }
					 
				 }else{
					  Logger.log("没有合适的case结果");
				 }
			 }else{
				 
				 Logger.log("repData返回的是空并且没有合适的case");
				  
			 }
		 }
		
		
		 
	}
	@DataProvider
    public static Object[][] checkBudgetAmountParam() {
        return new Object[][]{
//       		   {"conditionisnull"},{"costDetailTypeisnull"},
//       		   {"costCenterisnull"},{"yearisnull"},{"monthisnull"},{"moneyisnull"},{"moneyisnotnumber"},
//       		   {"conditionisnothaveconstdetailtypevalue"},{"conditionisnothavecostcentervalue"},
//       		   {"conditionisnothaveyearvalue"},{"conditionisnothavemonthvalue"},{"conditionisnothavemoneyvalue"},
//       		   {"conditionishavevalue"},{"conditionishavevaluemore"},{"conditionishavevaluemoresuperone"},{"conditionishavevaluesuper"},
//        		{"conditionishavevaluemoresupertwo"},{"conditionishavevaluemoresuperthree"},
//        		{"conditionishavevaluemoresuperfourother"},{"conditionishavevaluemoresupertwo1"},
        		{"conditionisnull"}
        };
        }
}
