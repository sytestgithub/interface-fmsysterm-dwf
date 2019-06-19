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
import com.daojia.qa.util.GetHead;

public class CheckBudgetAmountForPublic extends BaseTest<T>{
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
	@Test(dataProvider = "checkBudgetAmountForPublicParam", description="合同和对公校验预算")
	public void checkBudgetAmountForPublic(String name){
		method_url="http://fmsystem205.djtest.cn/common/checkbudgetamountforpublic";
		List<BudgetCheckDto> bugetcheckdtos=new ArrayList<BudgetCheckDto>();
		HashMap<String, String> param=new  HashMap<String, String>();
		BudgetCheckDto  bugetcheckdto=new BudgetCheckDto();
		BudgetCheckDto  bugetcheckdto1=new BudgetCheckDto();
		BudgetCheckDto  bugetcheckdto2=new BudgetCheckDto();
		BudgetCheckDto  bugetcheckdto3=new BudgetCheckDto();
		
		String costDetailType="";
		String costCenter="";
		String costCenter1="";
		String year="";
		String year1="";
		String month="";
		String month1="";
		String money="";
		String money1="";
		String money2="";
		String costDetailType1="";	
		String costDetailType2="";	
		String reslut="";
		String isOccupy="";//是否占用：1不占用，2待占用，3已占用
		String isRelationBudget="1";//是否关联预算
		String isRelationBudget1="1";//是否关联预算
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
		}else if(name.equals("costCenterNotExistent")){
			costDetailType="010";
			 year="2017";
			 month="09";
			 costCenter="103333333";
			 money="1";
		
		}else if(name.equals("costDetailTypeNotExistent")){
			costDetailType="010333333";
			 year="2017";
			 month="09";
			 costCenter="10.00.78.00.6";
			 money="1";
		
		}else if(name.equals("notRelationBudget")){
			costDetailType="010";
			 year="2017";
			 month="09";
			 costCenter="10.00.78.00.6";
			 money="1";
			 isRelationBudget="0";
	
		}else if(name.equals("RelationBudget")){
			costDetailType="010";
			 year="2017";
			 month="09";
			 costCenter="10.00.78.00.6";
			 money="1";
			 isRelationBudget="1";
		}else if(name.equals("occupyWaitRelationBudget")){
			costDetailType="010";
			 year="3000";
			 month="09";
			 costCenter="10.00.78.00.6";
			 money="1";
			 isRelationBudget="1";
		}else if(name.equals("conditionhavetwo")){
			costDetailType="010";
			 year="2017";
			 month="09";
			 costCenter="10.00.78.00.6";
			 money="1";
			 isRelationBudget="1";
		}else if(name.equals("conditiondifcenterandandrelationandwait")){
			costDetailType="010";
			 year="2017";
			 month="07";
			 costCenter="10.00.78.00.3";
			 money="1";
			 isRelationBudget="1";
              //  不关联预算
			 isRelationBudget1="0";
			 // 成本中心不一致
			 month1="09";
			 costCenter1="10.00.78.00.6";
			 //待占用预算
			 year1="3000";
			 
			 
		}else if(name.equals("conditionhavemoreandsuper")){
			costDetailType="010";
			 year="2017";
			 month="07";
			 costCenter="10.00.78.00.3";
			 money="1";
			 isRelationBudget="1";
			 money1="988.00";
			 
		}else if(name.equals("conditionhavemoreandiffandonesuper")){
			costDetailType="010";
			 year="2017";
			 month="07";
			 costCenter="10.00.78.00.3";
			 money="1";
			 isRelationBudget="1";
			 money1="988.00";
			 month1="12";
			 costCenter1="10.00.78.00.6";
			 isRelationBudget="1";
			
		}else if(name.equals("")){
			
		}else if(name.equals("")){
			
		}
		
	if(name.equals("conditiondifcenterandandrelationandwait")){	
		bugetcheckdto.setCostCenter(costCenter);
		bugetcheckdto.setCostDetailType(costDetailType);
		bugetcheckdto.setMoney(money);
		bugetcheckdto.setMonth(month);
		bugetcheckdto.setYear(year);
		bugetcheckdto.setIsRelationBudget(isRelationBudget);
		//不关联预算
		bugetcheckdto1.setCostCenter(costCenter);
		bugetcheckdto1.setCostDetailType(costDetailType);
		bugetcheckdto1.setMoney(money);
		bugetcheckdto1.setMonth(month);
		bugetcheckdto1.setYear(year);
		bugetcheckdto1.setIsRelationBudget(isRelationBudget1);
		// 成本中心预算不一致
		bugetcheckdto2.setCostCenter(costCenter1);
		bugetcheckdto2.setCostDetailType(costDetailType);
		bugetcheckdto2.setMoney(money);
		bugetcheckdto2.setMonth(month1);
		bugetcheckdto2.setYear(year);
		bugetcheckdto2.setIsRelationBudget(isRelationBudget);
		//待占用预算
		bugetcheckdto3.setCostCenter(costCenter);
		bugetcheckdto3.setCostDetailType(costDetailType);
		bugetcheckdto3.setMoney(money);
		bugetcheckdto3.setMonth(month);
		bugetcheckdto3.setYear(year1);
		bugetcheckdto3.setIsRelationBudget(isRelationBudget);
		
		bugetcheckdtos.add(bugetcheckdto);
		bugetcheckdtos.add(bugetcheckdto1);
		bugetcheckdtos.add(bugetcheckdto2);
		bugetcheckdtos.add(bugetcheckdto3);
	}else if(name.equals("conditionhavemoreandsuper")){	
		bugetcheckdto.setCostCenter(costCenter);
		bugetcheckdto.setCostDetailType(costDetailType);
		bugetcheckdto.setMoney(money);
		bugetcheckdto.setMonth(month);
		bugetcheckdto.setYear(year);
		bugetcheckdto.setIsRelationBudget(isRelationBudget);
		bugetcheckdto1.setCostCenter(costCenter);
		bugetcheckdto1.setCostDetailType(costDetailType);
		bugetcheckdto1.setMoney(money1);
		bugetcheckdto1.setMonth(month);
		bugetcheckdto1.setYear(year);
		bugetcheckdto1.setIsRelationBudget(isRelationBudget);
		bugetcheckdtos.add(bugetcheckdto);
		bugetcheckdtos.add(bugetcheckdto1);
	}else if(name.equals("conditionhavemoreandiffandonesuper")){	
		bugetcheckdto.setCostCenter(costCenter);
		bugetcheckdto.setCostDetailType(costDetailType);
		bugetcheckdto.setMoney(money);
		bugetcheckdto.setMonth(month);
		bugetcheckdto.setYear(year);
		bugetcheckdto.setIsRelationBudget(isRelationBudget);
		bugetcheckdto1.setCostCenter(costCenter);
		bugetcheckdto1.setCostDetailType(costDetailType);
		bugetcheckdto1.setMoney(money1);
		bugetcheckdto1.setMonth(month);
		bugetcheckdto1.setYear(year);
		bugetcheckdto1.setIsRelationBudget(isRelationBudget);
		bugetcheckdto2.setCostCenter(costCenter1);
		bugetcheckdto2.setCostDetailType(costDetailType);
		bugetcheckdto2.setMoney(money);
		bugetcheckdto2.setMonth(month1);
		bugetcheckdto2.setYear(year);
		bugetcheckdto2.setIsRelationBudget(isRelationBudget);
		bugetcheckdtos.add(bugetcheckdto);
		bugetcheckdtos.add(bugetcheckdto1);
		bugetcheckdtos.add(bugetcheckdto2);
	}else if(name.equals("conditionhavetwo")){	
		bugetcheckdto.setCostCenter(costCenter);
		bugetcheckdto.setCostDetailType(costDetailType);
		bugetcheckdto.setMoney(money);
		bugetcheckdto.setMonth(month);
		bugetcheckdto.setYear(year);
		bugetcheckdto.setIsRelationBudget(isRelationBudget);
		bugetcheckdto1.setCostCenter(costCenter);
		bugetcheckdto1.setCostDetailType(costDetailType);
		bugetcheckdto1.setMoney(money);
		bugetcheckdto1.setMonth(month);
		bugetcheckdto1.setYear(year);
		bugetcheckdto1.setIsRelationBudget(isRelationBudget);
		bugetcheckdtos.add(bugetcheckdto);
		bugetcheckdtos.add(bugetcheckdto1);
	}else{
		bugetcheckdto.setCostCenter(costCenter);
		bugetcheckdto.setCostDetailType(costDetailType);
		bugetcheckdto.setMoney(money);
		bugetcheckdto.setMonth(month);
		bugetcheckdto.setYear(year);
		bugetcheckdto.setIsRelationBudget(isRelationBudget);
		
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
				 if(name.equals("conditionisnull")||name.equals("costDetailTypeisnull")||name.equals("monthisnull")
						 ||name.equals("moneyisnotnumber")||name.equals("costCenterNotExistent")||name.equals("costDetailTypeNotExistent")
						 ||name.equals("")||name.equals("")||name.equals("")){
					 JSONObject redatejson=repData.getJSONObject(0);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(redatejson.get("result").toString().trim(),"1");
					 Assert.assertEquals(redatejson.get("isOccupy").toString().trim(),"3");
				 }else if(name.equals("costCenterisnull")||name.equals("RelationBudget")){
					 JSONObject redatejson=repData.getJSONObject(0);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(redatejson.get("result").toString().trim(),"0");
					 Assert.assertEquals(redatejson.get("isOccupy").toString().trim(),"3");
				 }else if(name.equals("notRelationBudget")){
					 JSONObject redatejson=repData.getJSONObject(0);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(redatejson.get("isOccupy").toString().trim(),"1");
				 }else if(name.equals("occupyWaitRelationBudget")){
					 JSONObject redatejson=repData.getJSONObject(0);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"成功");
					 Assert.assertEquals(redatejson.get("isOccupy").toString().trim(),"2");
				 }else if(name.equals("conditionhavetwo")||name.equals("conditiondifcenterandandrelationandwait")
						 ||name.equals("conditionhavemoreandsuper")||name.equals("conditionhavemoreandiffandonesuper") ){
					 for(int i=0;i<repData.size();i++){
						 Logger.log("conditionishavevaluemore有多少条预算："+repData.size());
						 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						 Assert.assertEquals(result.get("message").toString().trim(),"成功");
						  String getoccupy=repData.getJSONObject(i).get("isOccupy").toString().trim();
					
//						 Logger.log("获取checkDtos>>>>>>>>"+"获取的是repData.getJSONObject"+i+"==>"+repData.getJSONObject(i)+"获取的结果"+getreslut);
						if(getoccupy.equals("1")){
							Logger.log("不关联预算"+repData);
						}else if(getoccupy.equals("2")){
							Logger.log("关联预算待用预算"+repData);
						}else if(getoccupy.equals("3")){
							Logger.log("关联预算占用预算"+repData);
							  String getreslut= repData.getJSONObject(i).get("result").toString().trim();
							
							  if(getreslut.equals("0")){
									Logger.log("预算未超额"+repData);
								}else if(getreslut.equals("1")){
									Logger.log("预算超额"+repData);
								}else{
									Logger.log("预算出现异常");
								}
				
						}else{
							Logger.log("是否关联预算出现异常");
						}
						
					 }
				 }else if(name.equals("")){
					 
				 }
				 
			 }else{
				 
				 Logger.log("repData返回的是空并且没有合适的case");
				  
			 }
		 }
		 
	}
	@DataProvider
    public static Object[][] checkBudgetAmountForPublicParam() {
        return new Object[][]{
       
//        		 {"conditionisnull"},{"costDetailTypeisnull"},
//     		   {"costCenterisnull"},{"yearisnull"},{"monthisnull"},{"moneyisnull"},{"moneyisnotnumber"},
//        		{"costCenterNotExistent"},{"costDetailTypeNotExistent"},
//        		{"notRelationBudget"},{"RelationBudget"},{"occupyWaitRelationBudget"},
//        		{"conditionhavetwo"},{"conditiondifcenterandandrelationandwait"},{"conditionhavemoreandsuper"},
//        		{"conditionhavemoreandiffandonesuper"},
        		 {"conditionhavemoreandiffandonesuper"}
        };
        }
}
