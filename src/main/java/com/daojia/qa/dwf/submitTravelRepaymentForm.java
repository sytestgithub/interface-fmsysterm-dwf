package com.daojia.qa.dwf;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.CostDetail;
import com.daojia.qa.entity.CostOffset;
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.TravelInfo;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.qa.util.getUtilMethod;

public class submitTravelRepaymentForm extends BaseTest<T>{

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
	    * 提交差旅报销单接口
	    * */
	@Test(dataProvider = "submitTravelRepaymentFormParam", description="提交差旅申请单接口")
	public void submitTravelRepaymentForm1(String name){
		 
		String updatedqlformid="";
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//		method_url="http://10.253.7.168:80/v2/formsubmit";
//		form.setFormId("CLBX201713140001");
		form.setFormId("CLBX201713140001");
		form.setFormType(2);
		form.setApplyDate("2017-12-13");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setInnerRemark("abc");

		
		  String sql1="select * from  t_form WHERE  form_id='CLBX201713140001'";
          String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
          if(form_status1.equals("2")){
         	 GetDbMethod.endProcess( "CLBX201713140001"); 
         		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
         	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='CLBX201713140001'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
          }
         	 }else{
         	 Logger.log("订单CLBX201713140001状态非2"+form_status1);
          }
		
		if(name.equals("applymoneynotcanzero")){
			form.setApplyMoney("0");
		}else{
			form.setApplyMoney("10");
		}

		form.setReceiptMoney("0");
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
		form.setFormStatus("8");
		form.setMoneyType("0");
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
//		form.setApplyFormId("CLSQ201710165692");
		
		form.setApplyFormId("CLSQ201712160122");//申请单和报销的成本中心不一样的
		
		String applyformidupdate="UPDATE t_form SET apply_form_id='CLSQ1111111111' WHERE apply_form_id='CLSQ201712160122' AND form_status IN('2','9')";
		int apply_form_id1=GetDbMethod.updateSelectCondition(db, applyformidupdate);
		if(apply_form_id1>0){
			
			Logger.log("已经成功修改了数据库中跟CLSQ201712160122管理的差旅报销单"+apply_form_id1);
		}else{
			Logger.log("不存在数据库中跟CLSQ201712160122管理的差旅报销单"+apply_form_id1);
		}
		
		if(name.equals("apply_form_idhaverelationandprocess")){
			String applyformidselect="SELECT * FROM t_form WHERE form_type=1 AND apply_p_name='尚英' AND form_status=9 AND form_id IN(SELECT apply_form_id FROM t_form WHERE apply_form_id IS NOT  NULL AND form_status=2)";
			String applyformid1=GetDbMethod.getCostDetailSelctTest(db, applyformidselect, "form_id");
			Logger.log("");
			form.setApplyFormId(applyformid1);//申请单和报销的成本中心不一样的
			
		}else if(name.equals("apply_form_idhaverelationandcomplete")){
			String applyformidselect="SELECT * FROM t_form WHERE form_type=1 AND apply_p_name='尚英' AND form_status=9 AND form_id IN(SELECT apply_form_id FROM t_form WHERE apply_form_id IS NOT  NULL AND form_status=9)";
			String applyformid1=GetDbMethod.getCostDetailSelctTest(db, applyformidselect, "form_id");
			form.setApplyFormId(applyformid1);//申请单和报销的成本中心不一样的
		}else if(name.equals("apply_form_idhaverelatiotravelapplyname")){
			String applyformidselect="SELECT * FROM t_form WHERE form_type=1 AND apply_p_name!='尚英' AND form_status=9 AND form_id IN(SELECT apply_form_id FROM t_form WHERE apply_form_id IS NOT  NULL AND form_status=9)";
			String applyformid1=GetDbMethod.getCostDetailSelctTest(db, applyformidselect, "form_id");
			form.setApplyFormId(applyformid1);//申请单和报销的成本中心不一样的
		}
		
//   		CostDetail costdetailtravel=new CostDetail();
//   		costdetailtravel.setStartDate("2017-12-25");
//   		costdetailtravel.setEndDate("2017-12-26");
//   		costdetailtravel.setTravelCity("北京市");
//   		costdetailtravel.setTravelDays(1);
//   		costdetailtravel.setCostdetailDiv("5");//非3的时候提交异常
		
   		CostDetail costdetailstay=new CostDetail();
   		if(name.equals("staysetCostHappenDateisnull")){}else{
   		costdetailstay.setCostHappenDate("2017-11-25");
   		}
   		if(name.equals("staysetStartDateisnull")){}else{
   	   		costdetailstay.setStartDate("2017-11-25");
   	   		}
   		if(name.equals("staysetEndDateisnull")){}else{
   			costdetailstay.setEndDate("2017-11-26");
   	   		}
   	
   		costdetailstay.setTravelDays(1);
   		costdetailstay.setCostDetailType("010");
   		if(name.equals("staysetTravelCityisnull")){}else{
   			costdetailstay.setTravelCity("北京市");
   	   		}
   		
   		costdetailstay.setStayStandard("10");
   		costdetailstay.setInvoiceType("0");

   		if(name.equals("tayamouontiszero")){
   			costdetailstay.setAmount("0");
   		}else if(name.equals("tayamouontisnull")){
   			
   		}else if(name.equals("tayamouontisabc")){
   			costdetailstay.setAmount("abc");
   		}else if(name.equals("tayamouontissmallstand")){
			costdetailstay.setAmount("9");
   		}else if(name.equals("tayamouontisequalstand")){
   			costdetailstay.setAmount("10");
   		}else if(name.equals("tayamouontisbigstand")){
   			costdetailstay.setAmount("11");
   		}else{
   			costdetailstay.setAmount("10");
   		}
   		
   		costdetailstay.setBudgetStatus("0");
   		costdetailstay.setCostdetailDiv("1");
   		if(name.equals("staysetTaxRateisnull")){}else{
   			costdetailstay.setTaxRate("3");
   	   		}
   	
   		CostDetail costdetailtraffic=new CostDetail();
   		if(name.equals("trafficsetCostHappenDateisnull")){
   			
   		}else{
   			costdetailtraffic.setCostHappenDate("2017-10-26");
   		}
   		
   		if(name.equals("trafficsetStartCityisnull")){
   			
   		}else{
   			costdetailtraffic.setStartCity("北京市");
   		}
       
   		
   	  if(name.equals("trafficsetEndCityisnull")){}else{
   		costdetailtraffic.setEndCity("天津市");
		}
   		
   	 if(name.equals("trafficsetTrafficToolisnull")){}else{
   		costdetailtraffic.setTrafficTool("火车");
		}
   		
   
   		if(name.equals("trafficamountcanzero")){
   			costdetailtraffic.setAmount("0");
   		}else if(name.equals("trafficamountnull")){
   		
   		}else if(name.equals("trafficamountabc")){
   			costdetailtraffic.setAmount("abc");
   	   	}else{
   			costdetailtraffic.setAmount("1");
   		}
   		
   		
//   		costdetailtraffic.setTrafficTool("火车");
   		costdetailtraffic.setCostDetailType("010");
   		costdetailtraffic.setBudgetStatus("0");
   		costdetailtraffic.setCostdetailDiv("2");
   		
   		
   		
   		
  
   		
   		
   		CostDetail costdetailother=new CostDetail();
   		costdetailother.setCostHappenDate("2017-10-25");
   		if(name.equals("otheramountcanzero")){
   			costdetailother.setAmount("0");
   		}else if(name.equals("otheramountnull")){
   		
   		}else if(name.equals("otheramountabc")){
   			costdetailother.setAmount("abc");
   	   	}else{
   	   	costdetailother.setAmount("1");
   		}
   		
   		
   		costdetailother.setCostDetailType("010");
   		costdetailother.setBudgetStatus("0");
   		costdetailother.setCostdetailDiv("3");

   		
   		CostDetail costDetailsAllowance =new CostDetail();
   		if(name.equals("Allowanceshappendateisnotnull")){
   		}else{
   	   		costDetailsAllowance.setCostHappenDate("2017-10-25");
   		}
   		
   		if(name.equals("Allowancestravelcityisnotnull")){
   		}else{
   			costDetailsAllowance.setTravelCity("天津市");
   		}
//   		
   		costDetailsAllowance.setCostdetailDiv("4");
   		
   		if(name.equals("Allowancestraveldaysiszero")){
   			costDetailsAllowance.setTravelDays(0);
   		}else{
   			costDetailsAllowance.setTravelDays(1);
   		}
 if(name.equals("Allowancestraveldaysissmall")){
   			costDetailsAllowance.setAmount("10");
   			
   		}else if(name.equals("Allowancestraveldaysisequal")){
   			costDetailsAllowance.setAmount("60");
   		
   		}else if(name.equals("Allowancestraveldaysbig")){
   			costDetailsAllowance.setAmount("61");
   		
   		}else if(name.equals("Allowanceamountisnull")){
   		
   		
   		}else if(name.equals("Allowanceamountcanzero")){
   	   		
   			costDetailsAllowance.setAmount("0");
   	   	}else if(name.equals("AllowancemoneyisnotABC")){
   	   		
   			costDetailsAllowance.setAmount("abc");
   	   	} else{
   	   		costDetailsAllowance.setAmount("10");
   		}

   		costDetailsAllowance.setBudgetStatus("0");
   		costDetailsAllowance.setCostDetailType("010");
   		

   		
   		
   		
   		CostOffset costOffset=new CostOffset();
//   		List<CostDetail> travelInfos=new ArrayList<CostDetail>();
   		
   		TravelInfo travelInfo1=new TravelInfo();
   	 if(name.equals("travelsetStartDateisnull")){}else{
		  travelInfo1.setStartDate("2017-11-15");
   		}
	 if(name.equals("travelsetEndDateisnull")){}else{
		 travelInfo1.setEndDate("2017-11-16");
  		}
	 if(name.equals("travelsetStartCityisnull")){}else{
		 travelInfo1.setStartCity("北京市");
  		}
	 if(name.equals("travelsetEndCityisnull")){}else{
		 travelInfo1.setEndCity("天津市");
		 }
		 
		 
		  travelInfo1.setBudgetStatus("0");
	
			  travelInfo1.setStayStandard(new BigDecimal(1));
			 
		 
		  travelInfo1.setAllowanceAmount(new BigDecimal(0));
		  travelInfo1.setOtherAmount(new BigDecimal(0));
		  travelInfo1.setTravelDays(2);
		  travelInfo1.setCostDetailType("010");
		  travelInfo1.setStayAmount(new BigDecimal(1));
		  travelInfo1.setTotalAmount(new BigDecimal(1));
		  travelInfo1.setOtherAmount(new BigDecimal(1));
		  
		  
		
		  List<TravelInfo> travelInfos1=new ArrayList<TravelInfo>();
		  
		  
   		if(name.equals("travelinfonotnull")){
   			
   		}else{
   			travelInfos1.add(travelInfo1);
   		}
   		
   		List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
   		
	if(name.equals("applyCostDetailsStaycannull")){
   			
   		}else{
   			applyCostDetailsStay.add(costdetailstay);
   		}
   		List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
     if(name.equals("applyCostDetailsTrafficcannull")){
   			
   		}else{
   			applyCostDetailsTraffic.add(costdetailtraffic);
   		}
   		List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
 
//   		
   	 if(name.equals("applyCostDetailsOthercannull")){
			
		}else{
			applyCostDetailsOther.add(costdetailother);
		}
   		
   	 costOffset.setBorrowFormNum("GRJK201711240010");
	 costOffset.setCostFormId("CLBX201713140001");
//   	 costOffset.setCostFormId("CLBX201713140008");
	 if(name.equals("costOffsnomoneyiszero")){
		 costOffset.setThisOffsetAmount(new BigDecimal(1.00));
		 //修改数据库中的GRJK201711240010的未冲销成0
		 updatedqlformid="UPDATE t_form SET no_amount=0 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010'";
		int i= db.update(updatedqlformid);
		if(i>0){
			Logger.log("修改数据库中的GRJK201711240010的未冲销成0");
		}else{
			Logger.log("修改数据库中没有GRJK201711240010单修改");
		}
		 
		} else if(name.equals("costOffsThisOffsetAmountZERO")){
			costOffset.setThisOffsetAmount(new BigDecimal(0.00));
		} else  if(name.equals("costOffshavemoneybigapplymoney")){
		 costOffset.setThisOffsetAmount(new BigDecimal(21.00));
		 //修改数据库中的GRJK201711240010的未冲销成0
		 updatedqlformid="UPDATE t_form SET no_amount=22 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010'";
		int i= db.update(updatedqlformid);
		if(i>0){
			Logger.log("修改数据库中的GRJK201711240010的未冲销成0");
		}else{
			Logger.log("修改数据库中没有GRJK201711240010单修改");
		}
	 }else{
		 costOffset.setThisOffsetAmount(new BigDecimal(1.00));
		 updatedqlformid="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010'";
			int i= db.update(updatedqlformid);
			if(i>0){
		
				Logger.log("修改数据库中的GRJK201711240010的未冲销成1");
			}else{
				Logger.log("修改数据库中没有GRJK2017");
		 
	 }
	
	 costOffset.setBorrowAmount(new BigDecimal(1.00));
	 costOffset.setUnOffsetAmount(new BigDecimal(1.00));
	 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
	 UploadFileDto file=new UploadFileDto();
		file.setFileAddress("http://www.baidu.com");
		file.setFileName("filename1");
		file.setIsPic("0");

		List<CostOffset> costOffsets = new ArrayList<CostOffset>();
	  	 if(name.equals("costOffsnomoneyiszero")||name.equals("costOffsThisOffsetAmountZERO")
	  			 ||name.equals("costOffshavemoneybigapplymoney")||name.equals("costoffhavevalue")){
	  		costOffsets.add(costOffset);
			}else{
				
			}
   		List<CostDetail> costDetailsAllowances = new ArrayList<CostDetail>();
   	 if(name.equals("costDetailsAllowancesnotcannull")){
			
		}else{
			costDetailsAllowances.add(costDetailsAllowance);
		}
   
  	 if(name.equals("fileListcannull")){
			
		}else{

			fileList.add(file);
		}
  	 
  	 
 	CostDetail costdetailstay1=new CostDetail();
 	costdetailstay1.setCostHappenDate("2017-10-10");
 	costdetailstay1.setStartDate("2017-10-10");
	costdetailstay1.setEndDate("2017-10-11");
	costdetailstay1.setTravelDays(1);
	costdetailstay1.setTravelCity("上海");
	costdetailstay1.setTaxRate("3");
		costdetailstay1.setBudgetStatus("0");
		costdetailstay1.setCostdetailDiv("1");
		costdetailstay1.setStayStandard("10");
		costdetailstay1.setInvoiceType("0");
		costdetailstay1.setAmount("10");
		costdetailstay1.setCostDetailType("010");
		
 	TravelInfo travelInfo11=new TravelInfo();
 	
 	  travelInfo11.setStartDate("2017-10-15");
		 travelInfo11.setEndDate("2017-10-16");
		 travelInfo11.setStartCity("北京市");
	  travelInfo11.setBudgetStatus("0");
	  travelInfo11.setEndCity("天津市");
	  travelInfo11.setStayStandard(new BigDecimal(1));
	  travelInfo11.setAllowanceAmount(new BigDecimal(0));
	  travelInfo11.setOtherAmount(new BigDecimal(0));
	  travelInfo11.setTravelDays(2);
	  travelInfo11.setCostDetailType("010");
	  travelInfo11.setStayAmount(new BigDecimal(1));
	  travelInfo11.setTotalAmount(new BigDecimal(1));
	  travelInfo11.setOtherAmount(new BigDecimal(1));
	  
		CostDetail costdetailtraffic1=new CostDetail();
 		
   		costdetailtraffic1.setStartCity("北京市");
   		costdetailtraffic1.setEndCity("天津市");
 		costdetailtraffic1.setTrafficTool("火车");
   		costdetailtraffic1.setCostHappenDate("2017-10-25");
   		
   		
   		if(name.equals("differentmonthhavemoreapplymoney")){  //10月份超出申请金额的预算了1块钱
   			costdetailtraffic1.setAmount("1");
   		}else if(name.equals("Octobersuperdifferentmonth")){ //10月份超预算
   	   		costdetailtraffic1.setAmount("4078");
   	   		}else{
   		costdetailtraffic1.setAmount("1");
   		}
   		costdetailtraffic1.setCostDetailType("010");
   		costdetailtraffic1.setBudgetStatus("0");
   		costdetailtraffic1.setCostdetailDiv("2");
   		
   		
		CostDetail costdetailother1=new CostDetail();
		 if(name.equals("Novembersuperdifferentmonth")){ //11月份超预算
   	   		
   	   	costdetailother1.setCostHappenDate("2017-11-25");
   	 	costdetailother1.setAmount("4000");
   	   		}else{
   		costdetailother1.setCostHappenDate("2017-10-25");
   	 	costdetailother1.setAmount("1");
   	   		}
   		costdetailother1.setCostDetailType("010");
   		costdetailother1.setBudgetStatus("0");
   		costdetailother1.setCostdetailDiv("3");
   		
		
		
   		CostDetail costDetailsAllowance1 =new CostDetail();
   		costDetailsAllowance1.setCostHappenDate("2017-11-25");
   		costDetailsAllowance1.setTravelCity("天津市");
   		costDetailsAllowance1.setCostdetailDiv("4");
   		costDetailsAllowance1.setTravelDays(1);
   		costDetailsAllowance1.setAmount("10");
   		costDetailsAllowance1.setBudgetStatus("0");
   		costDetailsAllowance1.setCostDetailType("010");
   		
   		
   		
   		
		 UploadFileDto file1=new UploadFileDto();
			file1.setFileAddress("http://www.baidu.com1");
			file1.setFileName("filename2");
			file1.setIsPic("0");
   	 
   	 
//   	 
// 	CostDetail costdetailstay2=new CostDetail();
// 	
// 	TravelInfo travelInfo12=new TravelInfo();
//		CostDetail costdetailtraffic2=new CostDetail();
//		CostDetail costdetailother2=new CostDetail();
//		CostOffset costOffset2=new CostOffset();
//   		CostDetail costDetailsAllowance2 =new CostDetail();
//   	 UploadFileDto file2=new UploadFileDto();
  	 if(name.equals("havecostdetailallmoredifferent")||name.equals("differentmonthhavemoreapplymoney")
  			||name.equals("Octobersuperdifferentmonth")||name.equals("Novembersuperdifferentmonth")
  			||name.equals("applymoneyequercostdetail")||name.equals("applymoneyequercostdetail")){
  		 
 		applyCostDetailsStay.add(costdetailstay1);
 		travelInfos1.add(travelInfo11);
 		applyCostDetailsTraffic.add(costdetailtraffic1);
 		applyCostDetailsOther.add(costdetailother1);
 		costDetailsAllowances.add(costDetailsAllowance1);
 		fileList.add(file1);
		}
  	
		if(name.equals("isrepeat")){
			String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=2  AND form_id='CLBX201708103360'";
			String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
			form.setFormId(formid);
		}else if(name.equals("")){
			
		}
		String formOption="submit";
		
		 
           
		
		 HashMap<String, String> param=new  HashMap<String, String>();
   		 param.put("formOption", formOption);
   		 param.put("baseForm", JSONObject.toJSONString(form));		 
   		 param.put("travelInfos", JSONObject.toJSONString(travelInfos1));
   		 param.put("costDetailsStays", JSONObject.toJSONString(applyCostDetailsStay));
   		 param.put("costDetailsTraffics", JSONObject.toJSONString(applyCostDetailsTraffic));
   		 param.put("costDetailsOthers", JSONObject.toJSONString(applyCostDetailsOther));
   		 param.put("costOffsets", JSONObject.toJSONString(costOffsets));
   		 param.put("costDetailsAllowance", JSONObject.toJSONString(costDetailsAllowances));
   	    param.put("fileList", JSONObject.toJSONString(fileList));
   	  param.put("formType","CLBX");
   	  

//	 /*
//   	 GetDbMethod.endProcess( db,form.getFormId());
   			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
   			Logger.log("获取结果---->>>>"+result);
   			
   			if(name.equals("setraxnotcannull")){
   				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"税率不能为空");   			
				 }else if(name.equals("travelinfonotnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差信息不能为空"); 
				 }else if(name.equals("Allowanceshappendateisnotnull")||name.equals("staysetCostHappenDateisnull")){
						Assert.assertEquals(result.get("repCode").toString().trim(),"1");
						 Assert.assertEquals(result.get("message").toString().trim(),"费用发生日期不能为空");   	 
				}else if(name.equals("Allowancestravelcityisnotnull")){
								Assert.assertEquals(result.get("repCode").toString().trim(),"1");
								 Assert.assertEquals(result.get("message").toString().trim(),"出差城市不能为空");   	 
				}else if(name.equals("Allowancestraveldaysiszero")){
				Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				if(result.get("message").toString().contains("补贴报销金额不正确")){
					Assert.assertEquals("ok","ok");
					
				}   	 
				}else if(name.equals("Allowanceamountisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"报销金额不能为空");   	 
				}else if(name.equals("costoffissamllzero")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额不能小于0");   	 
				}else if(name.equals("Allowancestraveldaysbig")){
							Assert.assertEquals(result.get("repCode").toString().trim(),"1");
							if(result.get("message").toString().contains("补贴报销金额不正确")){
								Assert.assertEquals("ok","ok");
								
							}   	   	 
				}else if(name.equals("costDetailsAllowancesnotcannull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"补贴明细不能为空");   	 
		        }else if(name.equals("tayamouontisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"报销金额不能为空");   	 
		        }else if(name.equals("tayamouontisabc")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"报销金额必须是数字且大于0");   	 
		        }else if(name.equals("tayamouontisbigstand")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
						if(result.get("message").toString().contains("住宿报销金额不正确")){
					Assert.assertEquals("ok","ok");
					
				}   	   	 
		        }else if(name.equals("trafficamountabc")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"申请金额必须是数字且大于0");   	 
		        }else if(name.equals("trafficamountnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"申请金额不能为空");   	 
		        }else if(name.equals("otheramountnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"报销金额不能为空");   	 
		        }else if(name.equals("otheramountabc")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"申请金额必须是数字且大于0");   	 
		        }else if(name.equals("AllowancemoneyisnotABC")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"申请金额必须是数字且大于0");   	 
		        }else if(name.equals("staysetStartDateisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"入住日期不能为空");   	 
		        }else if(name.equals("staysetEndDateisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"退房日期不能为空");   	 
		        }else if(name.equals("staysetTravelCityisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差城市不能为空");   	 
		        }else if(name.equals("staysetTaxRateisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					if(result.get("message").toString().trim().contains("税率不能为空")){
					 Assert.assertEquals("税率不能为空","税率不能为空");   
					}
		        }else if(name.equals("trafficsetCostHappenDateisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用发生日期不能为空");   	 
		        }else if(name.equals("trafficsetStartCityisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出发城市不能为空");   	 
		        }else if(name.equals("trafficsetEndCityisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"到达城市不能为空");   	 
		        }else if(name.equals("trafficsetTrafficToolisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"交通工具不能为空");   	 
		        }else if(name.equals("travelsetStartDateisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差日期不能为空");   	 

		        }else if(name.equals("travelsetEndDateisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"返回日期不能为空");   	 
		        }else if(name.equals("travelsetStartCityisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出发城市不能为空");   	 
		        }else if(name.equals("travelsetEndCityisnull")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差城市不能为空");   	 
		        }else if(name.equals("costOffsThisOffsetAmountZERO")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额不能小于0");   	 
		        }else if(name.equals("costOffsnomoneyiszero")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于借款单未冲销金额");   	 
		        }else if(name.equals("costOffshavemoneybigapplymoney")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于报销金额");   	 
		        }else if(name.equals("Octobersuperdifferentmonth")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"2017-10差旅费预算不足");   	 
		        }else if(name.equals("Novembersuperdifferentmonth")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"2017-11差旅费预算不足");   	 
		        }else if(name.equals("apply_form_idhaverelationandprocess")||name.equals("apply_form_idhaverelationandcomplete")
		        		){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"差旅申请单已经关联报销单，不能重复提交！！");   	 
		        }else if(name.equals("apply_form_idhaverelatiotravelapplyname")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"差旅申请单申请人不是当前申请人");   	 
		        }else if(name.equals("Allowancestraveldaysissmall")||name.equals("Allowancestraveldaysisequal")
						||name.equals("Allowanceamountcanzero")||name.equals("tayamouontiszero")||name.equals("tayamouontissmallstand")
						||name.equals("tayamouontisequalstand")||name.equals("fileListcannull")
						||name.equals("trafficamountcanzero")||name.equals("otheramountcanzero")||name.equals("havecostdetailall")
						||name.equals("applyCostDetailsStaycannull")||name.equals("applyCostDetailsOthercannull")||name.equals("applyCostDetailsTrafficcannull")
						||name.equals("costOffshavemoneybigapplymoney")
						||name.equals("costoffhavevalue")||name.equals("havecostdetailallmoredifferent")
						||name.equals("applymoneyequercostdetail")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");   	
					 BigDecimal sumapplyall=new BigDecimal(0.00);  
					 sumapplyall=costdetailamount(sumapplyall,applyCostDetailsStay);
					 sumapplyall=costdetailamount(sumapplyall,applyCostDetailsTraffic);
					 sumapplyall=costdetailamount(sumapplyall,applyCostDetailsOther);
					 sumapplyall=costdetailamount(sumapplyall,costDetailsAllowances);
					 String travelinfodayssql=" SELECT * FROM t_form WHERE FORM_TYPE=2  AND form_id='"+form.getFormId()+"'";
						String totalamount=GetDbMethod.getCostDetailSelctTest(db, travelinfodayssql, "apply_money");
						if(name.equals("costoffhavevalue")){
							sumapplyall=sumapplyall.subtract(costOffset.getThisOffsetAmount());

								 totalamount=GetDbMethod.getCostDetailSelctTest(db, travelinfodayssql, "receipt_money");
							Assert.assertEquals(getUtilMethod.getConvertTwoBigDecimal(totalamount),getUtilMethod.getConvertTwoBigDecimal(sumapplyall.toString()));
						}else{
						 Assert.assertEquals(getUtilMethod.getConvertTwoBigDecimal(totalamount),getUtilMethod.getConvertTwoBigDecimal(sumapplyall.toString()));
		       
						}
						}else{
					 Logger.log("没有合适的case");
					  

		        }
//   			 */
	 }
	
	 
	}
	
	
	public BigDecimal costdetailamount(BigDecimal sumapplyall,List<CostDetail> costDetails){

		

		if(!costDetails.isEmpty()){
			
			for(int i=0;i<costDetails.size();i++){
				BigDecimal sumapply1=new BigDecimal(0.00);
			CostDetail	costdetail2=costDetails.get(i);
			sumapplyall=sumapplyall.add(sumapply1.add(new BigDecimal(costdetail2.getAmount())));
			
			}
			
	}
		return sumapplyall;
	}
	
	@DataProvider
    public static Object[][] submitTravelRepaymentFormParam() {
        return new Object[][]{ 		
        		{"isrepeat"},{"travelinfonotnull"},
        		{"formTypeisnothave"},{"staysetCostHappenDateisnull"},{"staysetStartDateisnull"},{"staysetEndDateisnull"},
        		{"staysetTravelCityisnull"},{"staysetTaxRateisnull"},
        		{"endDatenotnull"},{"travelDaysnotnull"},{"stayStandardnotnull"},
        		{"costoffhavevalue"},
        		{"costOffsThisOffsetAmountZERO"},
        		{"costOffshavemoneybigapplymoney"},//本次冲销金额大于申请金额
        		{"costOffsnomoneyiszero"},// 当未冲销金额是0的时候，则本次冲销金额大于当前的可冲销金额是不对的,
        		{"applyCostDetailsStaycannull"},{"applyCostDetailsTrafficcannull"},
        		 {"costDetailsAllowancesnotcannull"},{"applyCostDetailsOthercannull"},{"fileListcannull"},
        		 {""},{"Allowanceshappendateisnotnull"},{"Allowancestravelcityisnotnull"},{"Allowancestraveldaysiszero"},
        		 {"Allowancestraveldaysissmall"},{"Allowancestraveldaysisequal"},{"Allowancestraveldaysbig"},{"Allowanceamountisnull"},{"Allowanceamountcanzero"},
        		{"AllowancemoneyisnotABC"},
        		{"tayamouontiszero"},{"tayamouontisnull"},{"tayamouontisabc"},{"tayamouontissmallstand"},{"tayamouontisequalstand"},{"tayamouontisbigstand"},
        		{"costDetailsAllowancesnotcannull"},
        		{"trafficamountcanzero"},{"trafficamountnull"},{"trafficamountabc"},
        		{"otheramountcanzero"},{"otheramountnull"},{"otheramountabc"},
        		{"trafficsetCostHappenDateisnull"},{"trafficsetStartCityisnull"},{"trafficsetTrafficToolisnull"},
        		{"travelsetStartDateisnull"},{"travelsetEndDateisnull"},{"travelsetStartCityisnull"}, {"travelsetEndCityisnull"},
        		        		{"havecostdetailallmoredifferent"},
 {"differentmonthhavemoreapplymoney"},{"applymoneyequercostdetail"},{"Octobersuperdifferentmonth"},{"Novembersuperdifferentmonth"},
        		 	{"apply_form_idhaverelationandprocess"},{"apply_form_idhaverelationandcomplete"},
        		{"apply_form_idhaverelatiotravelapplyname"},
//        		{"Novembersuperdifferentmonth"},
//        		{"Novembersuperdifferentmonth"}
        	
        };
        }
	public void getBaseFormInfo( Map<String, Object>  resultparambacks,Form form,List<CostDetail> costDetails){
		 // 获取当前申请金额=费用明细总金额
		BigDecimal appylmoneysum=new BigDecimal(0.00);
		for(int i=0;i<costDetails.size();i++){
			appylmoneysum=appylmoneysum.add(BigDecimal.valueOf(Double.parseDouble(costDetails.get(i).getAmount())));
		}
		Assert.assertEquals(resultparambacks.get("inner_remark").toString().trim(),form.getInnerRemark());
		Assert.assertEquals(resultparambacks.get("form_id").toString().trim(),form.getFormId());
		 Assert.assertEquals(resultparambacks.get("form_type").toString().trim(),"4");
		 Assert.assertEquals(resultparambacks.get("apply_p_name").toString().trim(),form.getApplyPName());
		 Assert.assertEquals(resultparambacks.get("apply_p_jobnumber").toString().trim(),form.getApplyPJobnumber());
		 Assert.assertEquals(resultparambacks.get("cost_center").toString().trim(),form.getCostCenter());
		 Assert.assertEquals(resultparambacks.get("emp_p_tel").toString().trim(),form.getEmpPTel());
		 Assert.assertEquals(resultparambacks.get("pay_company").toString().trim(),form.getPayCompany());
		 Assert.assertEquals(resultparambacks.get("cost_desc").toString().trim(),form.getCostDesc());
		 Assert.assertEquals(resultparambacks.get("payee_name").toString().trim(),form.getPayeeName());
		 Assert.assertEquals(resultparambacks.get("receive_bank_name").toString().trim(),form.getReceiveBankName());
		 Assert.assertEquals(resultparambacks.get("receive_bank_account").toString().trim(),form.getReceiveBankAccount());
		 Assert.assertEquals(getUtilMethod.getConvertTwoBigDecimal(resultparambacks.get("apply_money").toString().trim()),getUtilMethod.getConvertTwoBigDecimal(appylmoneysum.toString().trim()));
	}
	
    public void getFileInfo(List<UploadFileDto> fileList,Map<String, Object>  resultparambacksfileaddress){
		// 附件上传
			if(fileList.size()>0||!fileList.isEmpty()){
		
				 for(int i=0;i<fileList.size();i++){
					 Assert.assertEquals(resultparambacksfileaddress.get("form_id").toString().trim(),form.getFormId());
					 Assert.assertEquals(resultparambacksfileaddress.get("file_address").toString().trim(),fileList.get(i).getFileAddress());
					 Assert.assertEquals(resultparambacksfileaddress.get("file_name").toString().trim(),fileList.get(i).getFileName());
					 Assert.assertEquals(resultparambacksfileaddress.get("pic").toString().trim(),fileList.get(i).getIsPic());
				 }
			}
				 
	}
    public void getCostDetail(Map<String, Object>  resultparambackscostdetails,List<CostDetail> costDetails){
   		if(costDetails.size()>0||!costDetails.isEmpty()){

					for(int i=0;i<costDetails.size();i++){
						//无法校验明细，因为没有具体的金额
//						Assert.assertEquals(resultparambackscostdetails.get("amount").toString().trim(),costDetails.get(i).getAmount());
						Assert.assertEquals(resultparambackscostdetails.get("cost_form_id").toString().trim(),form.getFormId());
						Assert.assertEquals(resultparambackscostdetails.get("cost_happen_date").toString().trim(),costDetails.get(i).getCostHappenDate());
//						Assert.assertEquals(resultparambackscostdetails.get("cost_detail_type").toString().trim(),costDetails.get(i).getCostDetailType());
						Assert.assertEquals(resultparambackscostdetails.get("budget_status").toString().trim(),costDetails.get(i).getBudgetStatus());
//						Assert.assertEquals(resultparambackscostdetails.get("cost_center").toString().trim(),form.getCostCenter());
						
					}
				
				
			}
	}
    
}
