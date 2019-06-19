package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.TravelInfo;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class saveTravelApplyForm extends BaseTest<T>{
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
	    * 保存差旅申请单接口
	    * */
	@Test(dataProvider = "saveTravelApplyFormParam", description="保存差旅申请单接口")
	public void saveTravelApplyForm1(String name) throws ParseException{
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//		method_url="http://10.253.7.168:80/v2/formsubmit";
		form.setFormId("CLSQ201712120002");
		
		form.setFormType(1);
		form.setApplyDate("2017-12-12");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setInnerRemark("abc");

		
		  String sql1="select * from  t_form WHERE  form_id='CLSQ201712120002'";
          String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
          if(form_status1.equals("2")){
         	 GetDbMethod.endProcess( "CLSQ201712120002"); 
         		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
         	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='CLSQ201712120002'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
          }
         	 }else{
         	 Logger.log("订单CLSQ201712120002状态非2"+form_status1);
          }
          
		
		if(name.equals("applymoneynotisnull")){
			form.setApplyMoney("");
		}else if(name.equals("applymoneynotisabc")){
			form.setApplyMoney("abc");
		}else{
		form.setApplyMoney("2");
		}
//		form.setApproveMoney(new BigDecimal(4.00));
if(name.equals("receiptmoneynotisnull")){
	form.setReceiptMoney("");
		}else if(name.equals("receiptmoneynotisabc")){
			form.setReceiptMoney("abc");
		}else{
			form.setReceiptMoney("0");
		}
		
		form.setCostDesc("test");
		form.setPayeeName("尚英");
		form.setReceiveBankName("招商银行");
		form.setReceiveBankAccount("6214830104619118");
		form.setApproveMoney(new BigDecimal(0));
		form.setPayStatus("0");
//		form.setFormStatus("0");
//		form.setFormStatus("1");
		if(name.equals("moneytypenotisnull")){
			form.setMoneyType("");
		}if(name.equals("moneytypenothave")){
		
		}else if(name.equals("moneytypenotisabc")){
			form.setMoneyType("abc");
		}else{
		form.setMoneyType("0");
		}
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
		
		  List<TravelInfo> travelInfos=new ArrayList<TravelInfo>();
//		  TravelInfo travelInfo=new TravelInfo();
		  TravelInfo travelInfo1=new TravelInfo();
		  travelInfo1.setStartDate("2017-11-15");
		  travelInfo1.setEndDate("2017-11-16");
		  travelInfo1.setStartCity("北京市");
		  travelInfo1.setEndCity("天津市");
		  travelInfo1.setBudgetStatus("0");
		  travelInfo1.setStayStandard(new BigDecimal(1));
		  travelInfo1.setAllowanceAmount(new BigDecimal(0));
		  travelInfo1.setOtherAmount(new BigDecimal(0));
		  travelInfo1.setTravelDays(1);
		  travelInfo1.setCostDetailType("010");
		  travelInfo1.setStayAmount(new BigDecimal(1));
		  travelInfo1.setTotalAmount(new BigDecimal(1));
		  
		  TravelInfo travelInfo2=new TravelInfo();
		  travelInfo2.setStartDate("2017-11-15");
		  travelInfo2.setEndDate("2017-11-16");
		  travelInfo2.setStartCity("北京市");
		  travelInfo2.setEndCity("天津市");
		  travelInfo2.setBudgetStatus("0");
		  travelInfo2.setStayStandard(new BigDecimal(1));
		  travelInfo2.setAllowanceAmount(new BigDecimal(0));
		  travelInfo2.setOtherAmount(new BigDecimal(0));
		  travelInfo2.setTravelDays(1);
		  travelInfo2.setCostDetailType("010");
		  travelInfo2.setStayAmount(new BigDecimal(1));
		  travelInfo2.setTotalAmount(new BigDecimal(1));
		  List<CostDetail> costDetails = new ArrayList<CostDetail>();
		  CostDetail costdetail = new CostDetail();
		  costdetail.setCostHappenDate("2017-10-25");
		  costdetail.setBudgetStatus("0");
		  costdetail.setStartCity("北京市");
		  costdetail.setEndCity("天津市");
	  costdetail.setCostDetailType("010");
	  costdetail.setTrafficTool("火车");
	  costdetail.setAmount("10");
		  costdetail.setCostdetailDiv("2");
		  CostDetail costdetail1 = new CostDetail();
		  costdetail1.setCostHappenDate("2017-10-25");
		  costdetail1.setStartCity("北京市");
		  costdetail1.setEndCity("天津市");
		  costdetail1.setTrafficTool("火车");
		  costdetail1.setAmount("10");
  costdetail1.setBudgetStatus("0");
		  
		  
		  costdetail1.setCostDetailType("010");
	
			  costdetail1.setCostdetailDiv("2");
		  List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
				file.setFileAddress("http://www.baidu.com");
				file.setFileName("filename1");
				file.setIsPic("0");
				file.setFileSize("0");
				 UploadFileDto file1=new UploadFileDto();
					file1.setFileAddress("http://www.baidu.com1");
					file1.setFileName("filename2");
					file1.setIsPic("0");
					file1.setFileSize("0");
			String formOption="save";
			
			if(name.equals("recallcansubmit")){
				String getformidsql=" UPDATE t_form SET form_status=8 WHERE  form_id='CLSQ201712120002'";
				GetDbMethod.updateSelectCondition(db, getformidsql);
				 formOption="submit";
			}else if(name.equals("Rejectcansubmit")){
				String getformidsql=" UPDATE t_form SET form_status=3 WHERE  form_id='CLSQ201712120002'";
				GetDbMethod.updateSelectCondition(db, getformidsql);
				 formOption="submit";
			}
			
			
			
			if( name.equals("updatesavehaveall")||name.equals("updatesavehaveallmore")||name.equals("recallcansubmit")||name.equals("Rejectcansubmit")){
				travelInfos.add(travelInfo1);
				costDetails.add(costdetail);
				fileList.add(file);
				if(name.equals("updatesavehaveallmore")){
					travelInfos.add(travelInfo2);
					costDetails.add(costdetail1);
					fileList.add(file1);
				}
			}else if(name.equals("processstatusnotcansave")){
				String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=1 and form_status=2 ORDER BY apply_date DESC";
				String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
				form.setFormId(formid);
			}else if(name.equals("completestatusnotcansave")){
				String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=1 and form_status=9 ORDER BY apply_date DESC";
				String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
				form.setFormId(formid);
			}else if(name.equals("formstatusisrecall")){
				String getformidsql=" UPDATE t_form SET form_status=8 WHERE  form_id='CLSQ201712120002'";
				GetDbMethod.updateSelectCondition(db, getformidsql);
				
			}else if(name.equals("formstatusisReject")){
				String getformidsql=" UPDATE t_form SET form_status=3 WHERE  form_id='CLSQ201712120002'";
				GetDbMethod.updateSelectCondition(db, getformidsql);
				
			}
			
			  HashMap<String, String> param=new  HashMap<String, String>();
			  param.put("baseForm",JSONObject.toJSONString(form));
			  param.put("formOption",formOption );
			  param.put("costDetails",JSONObject.toJSONString(costDetails) );
			  param.put("travelInfos",JSONObject.toJSONString(travelInfos) );
			  param.put("fileList", JSONObject.toJSONString(fileList));
			  param.put("formType","CLSQ");
			  // 清除数据
//			  GetDbMethod.endProcess(form.getFormId()); 
			  
		
	           
	           
	           
	           
	           
			  
			  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
				 Logger.log("获取repData>>>>>>>>"+result);
				 if(name.equals("savenothaveanyvalue")||name.equals("updatesavehaveall")||name.equals("moneytypenothave")
						 ||name.equals("formstatusisrecall")||name.equals("formstatusisReject") ||name.equals("moneytypenotisnull")
						 ||name.equals("recallcansubmit")||name.equals("Rejectcansubmit")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				
					 String getformidsql="SELECT * FROM t_form  WHERE  form_id='CLSQ201712120002'";
						String formstatus=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_status"); 
						if(name.equals("recallcansubmit")||name.equals("Rejectcansubmit")){
							Assert.assertEquals(formstatus.trim(),"2");
							  GetDbMethod.endProcess(form.getFormId()); 
						}else{
						 Assert.assertEquals(formstatus.trim(),"1");
						}
				 }else if(name.equals("processstatusnotcansave")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"此单已在审批中，不允许进行保存！！");
					 
				 }else if(name.equals("completestatusnotcansave")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"此单已在审批中，不允许进行保存！！");
					 
//					 此单已在审批中，不允许进行保存！！
				 }else if(name.equals("applymoneynotisnull")||name.equals("receiptmoneynotisnull")
						 ||name.equals("moneytypenotisabc")||name.equals("applymoneynotisabc")||name.equals("receiptmoneynotisabc")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"保存失败");
					 
//					 此单已在审批中，不允许进行保存！！
				 }
				 
		
	}


	@DataProvider
    public static Object[][] saveTravelApplyFormParam() {
        return new Object[][]{ 		
        		{"savenothaveanyvalue"},{"updatesavehaveall"},{"saveandsubmit"},{"processstatusnotcansave"},
    		{"completestatusnotcansave"},{"moneytypenotisnull"},{"applymoneynotisnull"},{"receiptmoneynotisnull"}
        		,{"moneytypenotisabc"},{"applymoneynotisbac"},{"receiptmoneynotisabc"},
        	{"formstatusisrecall"},	{"formstatusisReject"},
        		{"recallcansubmit"},{"Rejectcansubmit"},{"updatesavehaveallmore"},
//        		{"recallcansubmit"}
//        		{"Rejectcansubmit"}	
        };
		
		
}
}
