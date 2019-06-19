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

public class saveTravelRepaymentForm extends BaseTest<T>{
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
	    * 保存差旅报销单接口
	    * */
	@Test(dataProvider = "saveTravelRepaymentFormParam", description="提交差旅申请单接口")
	public void saveTravelRepaymentForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		form.setFormId("CLBX201713140002");
		form.setFormType(2);
		form.setApplyDate("2017-12-13");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		if(name.equals("applymoneynotisnull")){
			form.setApplyMoney("");
		}else if(name.equals("applymoneynotisabc")){
			form.setApplyMoney("abc");
		}else{
		form.setApplyMoney("10");
		}		if(name.equals("receiptmoneynotisnull")){
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
		form.setFormStatus("8");
		form.setInnerRemark("abc");

		if(name.equals("moneytypenotisnull")){}else if(name.equals("moneytypenotisabc")){
			form.setMoneyType("abc");
		}else{
		form.setMoneyType("0");
		}
		form.setState(0);
		form.setApplyUserId("4011");
		form.setDepartmentCode("1-17-09-02");
//		form.setApplyFormId("CLSQ201710165692");
		form.setApplyFormId("CLSQ201712160122");//申请单和报销的成本中心不一样的
		
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
	   		
	   		
	   		
	   		costdetailtraffic1.setAmount("1");
	   		
	   		costdetailtraffic1.setCostDetailType("010");
	   		costdetailtraffic1.setBudgetStatus("0");
	   		costdetailtraffic1.setCostdetailDiv("2");
	   		
	   		
			CostDetail costdetailother1=new CostDetail();
			
	   		costdetailother1.setCostHappenDate("2017-10-25");
	   	 	costdetailother1.setAmount("1");
	   	   		
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
				file1.setFileSize("0");
		   		CostOffset costOffset=new CostOffset();
		      	 costOffset.setBorrowFormNum("GRJK201711240010");
		    	 costOffset.setCostFormId("CLBX201713140002");
		    	 costOffset.setThisOffsetAmount(new BigDecimal(1.00));
				 costOffset.setBorrowAmount(new BigDecimal(1.00));
				 costOffset.setUnOffsetAmount(new BigDecimal(1.00));
				List<CostOffset> costOffsets = new ArrayList<CostOffset>();
				List<CostDetail> applyCostDetailsStay=new ArrayList<CostDetail>();
				List<CostDetail> applyCostDetailsTraffic=new ArrayList<CostDetail>();
				List<CostDetail> applyCostDetailsOther=new ArrayList<CostDetail>();
				List<CostDetail> costDetailsAllowances=new ArrayList<CostDetail>();
				 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
				  List<TravelInfo> travelInfos1=new ArrayList<TravelInfo>();
				  
				  if(name.equals("savenothaveanyvalue")){
				  		 
				 		
						}else if(name.equals("updatesavehaveall")||name.equals("recallcansubmit")||name.equals("Rejectcansubmit")){
			  		 
			 		applyCostDetailsStay.add(costdetailstay1);
			 		travelInfos1.add(travelInfo11);
			 		applyCostDetailsTraffic.add(costdetailtraffic1);
			 		applyCostDetailsOther.add(costdetailother1);
			 		costDetailsAllowances.add(costDetailsAllowance1);
			 		fileList.add(file1);
	                } 
				String formOption="save";
				if(name.equals("processstatusnotcansave")){
					String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=2 and form_status=2 ORDER BY apply_date DESC";
					String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
					form.setFormId(formid);
		        }else if(name.equals("completestatusnotcansave")){
		        	String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=2 and form_status=9 ORDER BY apply_date DESC";
					String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
					form.setFormId(formid);
		        }else if(name.equals("formstatusisrecall")){
					String getformidsql=" UPDATE t_form SET form_status=8 WHERE  form_id='CLBX201713140002'";
					GetDbMethod.updateSelectCondition(db, getformidsql);
					
				}else if(name.equals("formstatusisReject")){
					String getformidsql=" UPDATE t_form SET form_status=3 WHERE  form_id='CLBX201713140002'";
					GetDbMethod.updateSelectCondition(db, getformidsql);
					
				}else if(name.equals("recallcansubmit")){
					String getformidsql=" UPDATE t_form SET form_status=8 WHERE  form_id='CLBX201713140002'";
					GetDbMethod.updateSelectCondition(db, getformidsql);
					 formOption="submit";
					 
				}else if(name.equals("Rejectcansubmit")){
					String getformidsql=" UPDATE t_form SET form_status=3 WHERE  form_id='CLBX201713140002'";
					GetDbMethod.updateSelectCondition(db, getformidsql);
					 formOption="submit";
				}
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
		   	  
//			  GetDbMethod.endProcess( db,form.getFormId());
//			 /*
			  
		   	  String sql1="select * from  t_form WHERE  form_id='CLBX201713140002'";
	           String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
	           if(form_status1.equals("2")){
	          	 GetDbMethod.endProcess( "CLBX201713140002"); 
	          		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
	          	 if(form_statusagins.equals("2")){
			 	 		 Logger.log("任务处理失败强制修改");
			 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='CLBX201713140002'";
			 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
			 	 		 if(one>0){
			 	 			 Logger.log("修改成功"+one);
			 	 		 }
	           }
	          	 }else{
	          	 Logger.log("订单CLBX201713140002状态非2"+form_status1);
	           }
	           
	           
	           
		   	  
		   	  
		   			JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
		   			Logger.log("获取结果---->>>>"+result);
		   			if(name.equals("savenothaveanyvalue")||name.equals("updatesavehaveall")||name.equals("recallcansubmit")
		   					||name.equals("Rejectcansubmit")||name.equals("formstatusisrecall")
		   					||name.equals("formstatusisReject")||name.equals("moneytypenotisnull")
		   					||name.equals("apply_form_idhaverelationandprocess")||name.equals("apply_form_idhaverelationandcomplete")
			        		||name.equals("apply_form_idhaverelatiotravelapplyname")){
		   			 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						
					 String getformidsql="SELECT * FROM t_form  WHERE  form_id='CLBX201713140002'";
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
	                }else if(name.equals("applymoneynotisnull")||name.equals("receiptmoneynotisnull")
							 ||name.equals("moneytypenotisabc")||name.equals("applymoneynotisabc")||name.equals("receiptmoneynotisabc")){
	                	 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
	                	 Assert.assertEquals(result.get("message").toString().trim(),"保存失败");
	                }else if(name.equals("")){
		
	                }else if(name.equals("")){
		
	                }else {
						Logger.log("没有合适的case");
					}
				
		   			
	}
	
	
	@DataProvider
    public static Object[][] saveTravelRepaymentFormParam() {
        return new Object[][]{ 		
        		{"savenothaveanyvalue"},{"updatesavehaveall"},{"saveandsubmit"},{"processstatusnotcansave"},
    		{"completestatusnotcansave"},{"moneytypenotisnull"},{"applymoneynotisnull"},{"receiptmoneynotisnull"}
        		,{"moneytypenotisabc"},{"applymoneynotisbac"},{"receiptmoneynotisabc"},
        	{"formstatusisrecall"},	{"formstatusisReject"},
        		{"recallcansubmit"},{"Rejectcansubmit"},{"updatesavehaveallmore"},
		 	{"apply_form_idhaverelationandprocess"},{"apply_form_idhaverelationandcomplete"},
		{"apply_form_idhaverelatiotravelapplyname"},
        		{"Rejectcansubmit"},{"recallcansubmit"}	,{"updatesavehaveall"}
//        		{"Rejectcansubmit"}
        };
}
}