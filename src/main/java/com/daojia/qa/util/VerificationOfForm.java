package com.daojia.qa.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.CostOffset;
import com.daojia.qa.entity.Form;

public class VerificationOfForm {

	public static  void getDailyRepamentVerfication(){
	
			}
			
	
	
	
	public void getBudgetDeductionLogic( Map<String, Object>  getsqlbudgetafterresultparambacks,BigDecimal appylmoneysum,BigDecimal beforbudgetsurplusmoney,BigDecimal beforbudgetoccupymoney){
		//
		  // 预算记录后 占用金额occupymoney  剩余金额 surplusmoney   
		  //预算sql
		BigDecimal afterbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal afterbudgetsurplusmoney=new BigDecimal(0.00);
			 afterbudgetoccupymoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("occupy_money").toString().trim()));
			 afterbudgetsurplusmoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("surplus_money").toString().trim()));
		// 之前的占用金额+费用明细总金额（申请总金额）   之前剩余金额-费用明细总金额（申请总金额）
				String budgetoccupymoneyreslut=beforbudgetoccupymoney.add(appylmoneysum).toString().trim();
				 String budgetsurplusmoneyreslut=beforbudgetsurplusmoney.subtract(appylmoneysum).toString().trim();
				 Assert.assertEquals(budgetoccupymoneyreslut,afterbudgetoccupymoney.toString().trim());
				 Assert.assertEquals(budgetsurplusmoneyreslut,afterbudgetsurplusmoney.toString().trim());
				
	}
	
	public void getSubmitBudgetDeductionLogic( Map<String, Object>  getsqlbudgetafterresultparambacks,HashMap<String, String> budgetparam){
		//
		  // 预算记录后 占用金额occupymoney  剩余金额 surplusmoney   
		  //预算sql
		BigDecimal afterbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal afterbudgetsurplusmoney=new BigDecimal(0.00);
		BigDecimal afterbudgetusedmoney=new BigDecimal(0.00);
		BigDecimal beforbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal beforbudgetsurplusmoney=new BigDecimal(0.00);
		BigDecimal beforbudgetusedmoney=new BigDecimal(0.00);
		BigDecimal appylmoneysum=new BigDecimal(0.00);
//		beforbudgetoccupymoney=budgetparam.get("");
			 afterbudgetoccupymoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("occupy_money").toString().trim()));
			 afterbudgetsurplusmoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("surplus_money").toString().trim()));
		// 之前的占用金额+费用明细总金额（申请总金额）   之前剩余金额-费用明细总金额（申请总金额）
				String budgetoccupymoneyreslut=beforbudgetoccupymoney.add(appylmoneysum).toString().trim();
				 String budgetsurplusmoneyreslut=beforbudgetsurplusmoney.subtract(appylmoneysum).toString().trim();
				 Assert.assertEquals(budgetoccupymoneyreslut,afterbudgetoccupymoney.toString().trim());
				 Assert.assertEquals(budgetsurplusmoneyreslut,afterbudgetsurplusmoney.toString().trim());
				 Assert.assertEquals(beforbudgetusedmoney,afterbudgetusedmoney);
				
	}
	public void getOffSetDeductionLogic( Map<String, Object>  getsqlbudgetafterresultparambacks,BigDecimal offsetmoneysum,BigDecimal beforoffsetnoamount,BigDecimal beforoffsetwaitamount){
		//
		  // 预算记录后 占用金额occupymoney  剩余金额 surplusmoney   
		  //预算sql
		BigDecimal afteroffsetnoamount=new BigDecimal(0.00);
		BigDecimal afteroffsetwaitamount=new BigDecimal(0.00);
		afteroffsetnoamount=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("no_amount").toString().trim()));
		afteroffsetwaitamount=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("wait_amount").toString().trim()));
		// 之前的占用金额+费用明细总金额（申请总金额）   之前剩余金额-费用明细总金额（申请总金额）
				String offsetnoamountreslut=beforoffsetnoamount.subtract(offsetmoneysum).toString().trim();
				 String offsetwaitamountreslut=beforoffsetwaitamount.add(offsetmoneysum).toString().trim();
				 Assert.assertEquals(afteroffsetnoamount,offsetnoamountreslut.toString().trim());
				 Assert.assertEquals(afteroffsetwaitamount,offsetwaitamountreslut.toString().trim());
				
	}
	public void getSubmitOffSetDeductionLogic( Map<String, Object>  getsqlbudgetafterresultparambacks,HashMap<String, String> setoffparam){
		//
		  //   冲销sql 
	//  noamount 未冲销金额   waitamount 待冲销金额   alreadyamount已冲销金额
		BigDecimal afteroffsetnoamount=new BigDecimal(0.00);
		BigDecimal afteroffsetwaitamount=new BigDecimal(0.00);
		BigDecimal afteroffsetalreadyamount=new BigDecimal(0.00);
		BigDecimal beforoffsetnoamount=new BigDecimal(0.00);
		BigDecimal beforoffsetwaitamount=new BigDecimal(0.00);
		BigDecimal beforoffsetalreadyamount=new BigDecimal(0.00);
		BigDecimal offsetmoneysum=new BigDecimal(0.00);
		afteroffsetnoamount=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("no_amount").toString().trim()));
		afteroffsetwaitamount=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("wait_amount").toString().trim()));
		
		  //之前的未冲销金额-本次冲销金额    之前的待冲销记录+本次冲销金额
				String offsetnoamountreslut=beforoffsetnoamount.subtract(offsetmoneysum).toString().trim();
				 String offsetwaitamountreslut=beforoffsetwaitamount.add(offsetmoneysum).toString().trim();
				 Assert.assertEquals(afteroffsetnoamount,offsetnoamountreslut.toString().trim());
				 Assert.assertEquals(afteroffsetwaitamount,offsetwaitamountreslut.toString().trim());
				 Assert.assertEquals(afteroffsetalreadyamount,beforoffsetalreadyamount);
				
	}

	

	
	public void getCompleteBudgetDeductionLogicReslut( Map<String, Object>  getsqlbudgetafterresultparambacks,HashMap<String, String> budgetparam){
		//
		  // 预算记录后 占用金额occupymoney  剩余金额 surplusmoney   
		  //预算sql
		BigDecimal afterbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal afterbudgetsurplusmoney=new BigDecimal(0.00);
		BigDecimal afterbudgetusedmoney=new BigDecimal(0.00);
		BigDecimal beforbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal beforbudgetsurplusmoney=new BigDecimal(0.00);
		BigDecimal beforbudgetusedmoney=new BigDecimal(0.00);
		BigDecimal appylmoneysum=new BigDecimal(0.00);
//		beforbudgetoccupymoney=budgetparam.get("");
			 afterbudgetoccupymoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("occupy_money").toString().trim()));
			 afterbudgetusedmoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("used_money").toString().trim()));
		// 之前的占用金额-费用明细总金额（申请总金额）   之前已用金额+费用明细总金额（申请总金额）
			 String budgetoccupymoneyreslut=beforbudgetoccupymoney.subtract(appylmoneysum).toString().trim();
				String budgetusedmoneyreslut=beforbudgetusedmoney.add(appylmoneysum).toString().trim();
				
				 Assert.assertEquals(budgetoccupymoneyreslut,afterbudgetoccupymoney.toString().trim());
				 Assert.assertEquals(beforbudgetsurplusmoney,afterbudgetsurplusmoney);
				 Assert.assertEquals(budgetusedmoneyreslut,afterbudgetusedmoney);
				
	}
	public void getCompleteOffSetDeductionLogicReslut( Map<String, Object>  getsqlbudgetafterresultparambacks,HashMap<String, String>setoffparam){
	
		//
		//  noamount 未冲销金额   waitamount 待冲销金额   alreadyamount已冲销金额
		BigDecimal afteroffsetnoamount=new BigDecimal(0.00);
		BigDecimal afteroffsetwaitamount=new BigDecimal(0.00);
		BigDecimal afteroffsetalreadyamount=new BigDecimal(0.00);
		BigDecimal beforoffsetnoamount=new BigDecimal(0.00);
		BigDecimal beforoffsetwaitamount=new BigDecimal(0.00);
		BigDecimal beforoffsetalreadyamount=new BigDecimal(0.00);
		BigDecimal offsetmoneysum=new BigDecimal(0.00);
		afteroffsetalreadyamount=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("already_amount").toString().trim()));
		afteroffsetwaitamount=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("wait_amount").toString().trim()));
		// 		// 之前的待冲销金额-本次冲销金额    之前的已冲销记录+本次冲销金额
				String offsetalreadyamountreslut=beforoffsetalreadyamount.add(offsetmoneysum).toString().trim();
				 String offsetwaitamountreslut=beforoffsetwaitamount.subtract(offsetmoneysum).toString().trim();
				 Assert.assertEquals(afteroffsetnoamount,beforoffsetnoamount.toString().trim());
				 Assert.assertEquals(afteroffsetwaitamount,offsetwaitamountreslut.trim());
				 Assert.assertEquals(afteroffsetalreadyamount,offsetalreadyamountreslut.trim());
	}
	public void getsaveBudgetDeductionLogic( Map<String, Object>  getsqlbudgetafterresultparambacks,HashMap<String, String> budgetparam){
		//
		BigDecimal afterbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal afterbudgetsurplusmoney=new BigDecimal(0.00);
		BigDecimal afterbudgetusedmoney=new BigDecimal(0.00);
		BigDecimal beforbudgetoccupymoney =new BigDecimal(0.00);
		BigDecimal beforbudgetsurplusmoney=new BigDecimal(0.00);
		BigDecimal beforbudgetusedmoney=new BigDecimal(0.00);
		
		 Assert.assertEquals(beforbudgetoccupymoney,afterbudgetoccupymoney);
		 Assert.assertEquals(beforbudgetsurplusmoney,afterbudgetsurplusmoney);
		 Assert.assertEquals(beforbudgetusedmoney,afterbudgetusedmoney);
		

	}
	

	public void getSaveOffSetDeductionLogic( Map<String, Object>  getsqlbudgetafterresultparambacks,HashMap<String, String>setoffparam){
	//
		BigDecimal afteroffsetnoamount=new BigDecimal(0.00);
		BigDecimal afteroffsetwaitamount=new BigDecimal(0.00);
		BigDecimal afteroffsetalreadyamount=new BigDecimal(0.00);
		BigDecimal beforoffsetnoamount=new BigDecimal(0.00);
		BigDecimal beforoffsetwaitamount=new BigDecimal(0.00);
		BigDecimal beforoffsetalreadyamount=new BigDecimal(0.00);
		 Assert.assertEquals(afteroffsetnoamount,beforoffsetnoamount);
		 Assert.assertEquals(afteroffsetwaitamount,beforoffsetwaitamount);
		 Assert.assertEquals(afteroffsetalreadyamount,beforoffsetalreadyamount);
	}
}
