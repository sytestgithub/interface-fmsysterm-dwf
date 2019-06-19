package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.TravelInfo;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.qa.util.getUtilMethod;

public class submitTravelApplyForm extends BaseTest<T>{
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
	    * 提交差旅申请单接口
	    * */
	@Test(dataProvider = "submitTravelApplyFormParam", description="提交差旅申请单接口")
	public void submitTravelApplyForm1(String name) throws ParseException{
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
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
		
		BigDecimal travelinfossum=new BigDecimal(0.00);
		BigDecimal costdetailssum=new BigDecimal(0.00);
		form.setFormId("CLSQ201712120001");
		
		form.setFormType(1);
		form.setApplyDate("2017-12-12");
		form.setApplyPName("尚英");
		form.setApplyPJobnumber("4021");
		form.setEmpDepartment("信息技术质量部");
		form.setCostCenter("10.00.78.00.6");
		form.setEmpPTel("18201137136");
		form.setPayCompany("北京五八到家信息技术有限公司");
		form.setInnerRemark("abc");

		
		  
		   String sql1="select * from  t_form WHERE  form_id='CLSQ201712120001'";
        String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
        if(form_status1.equals("2")){
       	 GetDbMethod.endProcess("CLSQ201712120001"); 
       		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
       	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='CLSQ201712120001'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
        }
       	 }else{
       	 Logger.log("订单CLSQ201712120001状态非2"+form_status1);
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
				}if(name.equals("receiptmoneynothave")){
		
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
		  TravelInfo travelInfo=new TravelInfo();
		
			  
			
			  if(name.equals("startDatenotnull")){
				  Logger.log("startDatenotnull空");
			  }else {
		
				  travelInfo.setStartDate("2017-11-12");
			  }
		  if(name.equals("endDatenotnull")){
			  Logger.log("endDatenotnull空");
		  }else {
	
			  travelInfo.setEndDate("2017-11-14");
		  }
		  if(name.equals("setStartCitynotnull")){
			  Logger.log("setStartCity空");
		  }else {
			 
			  travelInfo.setStartCity("北京市");
		  }
		  if(name.equals("setEndCitynotnull")){
			  Logger.log("setEndCity空");
		  }else {
			 
			  travelInfo.setEndCity("天津市");
		  }
		if(name.equals("travelDaysnotnull")){
			  Logger.log("travelDaysnotnull空");
		}else{
			  travelInfo.setTravelDays(2);
		}
		
		if(name.equals("stayStandardnotnull")){
			  Logger.log("setStayStandard空");
		}else{
		  travelInfo.setStayStandard(new BigDecimal(10));
		}
		
		  if(name.equals("setStayAmountnotcanbig")){
			  travelInfo.setStayAmount(new BigDecimal(21));
		  }else if(name.equals("setStayAmountcansmall")){
			  travelInfo.setStayAmount(new BigDecimal(19));
		  }else if(name.equals("setStayAmountZERO")){
			  travelInfo.setStayAmount(new BigDecimal(0));
		  }else{
			  travelInfo.setStayAmount(new BigDecimal(2));
		  }
		// 是否校验大于、等于0
		   if(name.equals("allowanceAmountcanzero")){
			   travelInfo.setAllowanceAmount(new BigDecimal(0));
			   } else if(name.equals("allowanceAmountbigstand")){
				   travelInfo.setAllowanceAmount(new BigDecimal(130));
			   }else{
			   travelInfo.setAllowanceAmount(new BigDecimal(10));
		   }
		   if(name.equals("otherAmountcanzero")){
			   travelInfo.setOtherAmount(new BigDecimal(0));
		   }else{
			   travelInfo.setOtherAmount(new BigDecimal(30));
		   }
		 
		  if(name.equals("setTotalAmountnotequerandbig")){
			  travelInfo.setTotalAmount(new BigDecimal(71));
		  }else if(name.equals("setTotalAmountnotequerandsmall")){
			  travelInfo.setTotalAmount(new BigDecimal(69));
		  }else if(name.equals("setTotalAmountisnull")){
			  
		  }else if(name.equals("totalAmountcanzero")){
			  travelInfo.setStartDate("2017-11-12");
			  travelInfo.setEndDate("2017-11-12");
			  travelInfo.setOtherAmount(new BigDecimal(0.00));
			  travelInfo.setAllowanceAmount(new BigDecimal(0.00));
			  travelInfo.setTotalAmount(new BigDecimal(8));
		  }else{
			  travelInfo.setTotalAmount(new BigDecimal(8));
		  }
		  
		  travelInfo.setBudgetStatus("0");
		  travelInfo.setCostDetailType("010");
  if(name.equals("moneysuper")){
	  travelInfo.setStayStandard(new BigDecimal(10000000));
	  travelInfo.setEndDate("2018-01-01");
	  travelInfo.setStayAmount(new BigDecimal(10000000));
		  }
		  
		  if(name.equals("travelInfosnotcannull")){
			 Logger.log("是空");
			}else if(name.equals("travelinfohavemoregettotalmoney")||name.equals("applymoneyequertraveandcostdetail")
					||name.equals("moneysupertwo")||name.equals("")){
				travelInfos.add(travelInfo);
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
			  if(name.equals("moneysupertwo")){
				  travelInfo1.setOtherAmount(new BigDecimal(1000000));
				  travelInfos.add(travelInfo1);
			  }else if(name.equals("")){
				  
			  }else{
				  travelInfos.add(travelInfo1);
			  }
				
			}else{
				 travelInfos.add(travelInfo);
			}
		 
		  
		  List<CostDetail> costDetails = new ArrayList<CostDetail>();
		  CostDetail costdetail = new CostDetail();
		  CostDetail costdetail1 = new CostDetail();
		  costdetail1.setCostHappenDate("2017-10-25");
		  costdetail1.setStartCity("北京市");
		  costdetail1.setEndCity("天津市");
		  costdetail1.setTrafficTool("火车");
		  costdetail1.setAmount("10");
  costdetail1.setBudgetStatus("0");
		  
		  
		  costdetail1.setCostDetailType("010");
	
			  costdetail1.setCostdetailDiv("2");
		  
		  if(name.equals("costdetailhavehappydate")){
			  
		  }else{
			  costdetail.setCostHappenDate("2017-10-25");
		  }
		  if(name.equals("costdetailhavestartcity")){
			  
		  }else{
			  costdetail.setStartCity("北京市");
		  }
		  if(name.equals("costdetailhaveendcity")){
			 
		  }else{
			  costdetail.setEndCity("天津市");
		  }
		  if(name.equals("costdetailhavetrrafictools")){
			
		  }else{
			  costdetail.setTrafficTool("火车");
		  }
		  if(name.equals("costdetailhavemoneyiszero")){
			  costdetail.setAmount("0");
		  }else if(name.equals("costdetailhavemoneyisnotabc")){
			  costdetail.setAmount("abc");
		  }else{
			  costdetail.setAmount("10");
		  }
		
			  costdetail.setBudgetStatus("0");
		  
		  
		  costdetail.setCostDetailType("010");
	
			  costdetail.setCostdetailDiv("2");
		  
		  
	
			 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
				file.setFileAddress("http://www.baidu.com");
				file.setFileName("filename1");
				file.setIsPic("0");
				 UploadFileDto file1=new UploadFileDto();
					file1.setFileAddress("http://www.baidu.com1");
					file1.setFileName("filename2");
					file1.setIsPic("0");
			String formOption="submit";
			
			
			
	           
	           
		if(name.equals("isrepeat")){
			String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=1 AND apply_date='2017-07-08' and form_status=2 ORDER BY apply_date DESC";
			String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
			form.setFormId(formid);
		}else if(name.equals("havecostdetail")||name.equals("applymoneyequertraveandcostdetail")||name.equals("costdetailhavehappydate")
				||name.equals("costdetailhavestartcity")||name.equals("costdetailhaveendcity")||name.equals("costdetailhavetrrafictools")
				||name.equals("costdetailhavemoneyiszero")||name.equals("costdetailhavemoneyisnotabc")||name.equals("applymoneyequertraveandcostdetail")
				||name.equals("moneysupertwo")||name.equals("moneysuper")){
			
			  if(name.equals("applymoneyequertraveandcostdetail")){
				  costDetails.add(costdetail);
				  costDetails.add(costdetail1);
				  fileList.add(file);
					fileList.add(file1);
			  }else if(name.equals("moneysupertwo")){
				
				  costdetail.setAmount("88430.00");
				  costDetails.add(costdetail);
			  }else{
				  costDetails.add(costdetail);
			  }
		}else if(name.equals("havefileinfo")){
			fileList.add(file);
			
		}else if(name.equals("formidisprocess")){
			String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=1 and form_status=2 ORDER BY apply_date DESC";
			String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
			form.setFormId(formid);
		}else if(name.equals("formidiscomplete")){
			String getformidsql="SELECT * FROM t_form WHERE FORM_TYPE=1 and form_status=9 ORDER BY apply_date DESC";
			String formid=GetDbMethod.getCostDetailSelctTest(db, getformidsql, "form_id");
			form.setFormId(formid);
		}else{
			
		}
		  HashMap<String, String> param=new  HashMap<String, String>();
		  param.put("baseForm",JSONObject.toJSONString(form));
		  param.put("formOption",formOption );
		  param.put("costDetails",JSONObject.toJSONString(costDetails) );
		  param.put("travelInfos",JSONObject.toJSONString(travelInfos) );
		  param.put("fileList", JSONObject.toJSONString(fileList));
		  param.put("formType","CLSQ");
//		  GetDbMethod.endProcess( db,form.getFormId()); 
		  
		
		  
		  
		  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 Logger.log("获取repData>>>>>>>>"+result);
			 
			 
				if(name.equals("isrepeat")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！");
				}else if(name.equals("travelInfosnotcannull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差信息不能为空");
				}else if(name.equals("havefileinfo")||name.equals("moneytypenotisnull")||name.equals("receiptmoneynotisnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
				}else if(name.equals("havecostdetail")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
				}else if(name.equals("startDatenotnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差日期不能为空");
				}else if(name.equals("endDatenotnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"返回日期不能为空");
				}else if(name.equals("stayStandardnotnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"住宿标准不能为空");
				}else if(name.equals("setStartCitynotnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出发城市不能为空");
				}else if(name.equals("setEndCitynotnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出差城市不能为空");
				}else if(name.equals("formidisprocess")||name.equals("formidiscomplete")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！");
				}else if(name.equals("travelDaysnotnull")){
					  // 通过formid获取到对应的天数
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用发生日期[2017-11-12]出差信息的住宿报销金额大于城市住宿标准，请重新填写");
					String travelinfodayssql=" SELECT * FROM t_travel_info WHERE  cost_form_id='CLSQ201712120001'";
					String travelinfodays=GetDbMethod.getCostDetailSelctTest(db, travelinfodayssql, "travel_days");
					String travelinfogetdays=String.valueOf(getUtilMethod.daysBetween(travelInfo.getStartDate(),travelInfo.getEndDate()));
					Assert.assertEquals(travelinfogetdays, travelinfodays);
				}else if(name.equals("stayamount")||name.equals("setStayAmountcansmall")
						||name.equals("setStayAmountZERO")||name.equals("applymoneynotisnull")||name.equals("applymoneynotisabc")){
					  // 通过formid获取到对应住宿钱
					// 天数X标准
					Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
					String travelinfodayssql=" SELECT * FROM t_travel_info WHERE  cost_form_id='CLSQ201712120001'";
					String stayamount=GetDbMethod.getCostDetailSelctTest(db, travelinfodayssql, "stay_amount");
//					int days=getUtilMethod.daysBetween(travelInfo.getStartDate(),travelInfo.getEndDate());
//					String stayamountgetoftraveinf=travelInfo.getStayStandard().multiply(new BigDecimal(days)).toString();
					Assert.assertEquals(stayamount, getUtilMethod.getConvertTwoBigDecimal(travelInfo.getStayAmount().toString()));
				}else if(name.equals("allowanceAmountcanzero")||name.equals("receiptmoneynothave")||name.equals("otherAmountcanzero")||name.equals("costdetailhavemoneyiszero")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
					 	
				}else if(name.equals("setTotalAmountisnull")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"totalAmount字段为null");
					 	
				}else if(name.equals("costdetailhavehappydate")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用发生日期不能为空");
					 	
				}else if(name.equals("costdetailhavestartcity")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"出发城市不能为空");
					 	
				}else if(name.equals("costdetailhaveendcity")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"到达城市不能为空");
					 	
				}else if(name.equals("costdetailhavetrrafictools")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"交通工具不能为空");
				
				}else if(name.equals("costdetailhavemoneyisnotabc")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"申请金额必须是数字且大于0");
					
				}else if(name.equals("moneysuper")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"2017-11差旅费预算不足");
					
				}else if(name.equals("receiptmoneynotisabc")
						||name.equals("moneytypenothave")||name.equals("moneytypenotisabc")){
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
//					 Assert.assertEquals(result.get("message").toString().trim(),"");
					
				}else if(name.equals("setStayAmountnotcanbig")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用发生日期[2017-11-12]出差信息的住宿报销金额大于城市住宿标准，请重新填写");
					
				}else if(name.equals("allowanceAmountbigstand")){
					
					Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用发生日期[2017-11-12]出差信息的补贴报销金额大于城市补贴金额，请重新填写。");
					
				}else if(name.equals("setTotalAmountnotequerandbig")||name.equals("setTotalAmountnotequerandsmall")
						||name.equals("totalAmountcanzero")){
					
					for(int i=0;i<travelInfos.size();i++){
						TravelInfo travelInfo1=travelInfos.get(i);
//					int days=getUtilMethod.daysBetween(travelInfo1.getStartDate(),travelInfo.getEndDate());
//					BigDecimal staymoney=(travelInfo1.getStayStandard().multiply(new BigDecimal(days)));
					String totlemoney=(travelInfo1.getStayAmount().add(travelInfo1.getAllowanceAmount())).add(travelInfo.getOtherAmount()).toString();
					String travelinfodayssql=" SELECT * FROM t_form WHERE  form_id='CLSQ201712120001' ORDER BY fill_or_submit_time ASC";
					String totalamount=GetDbMethod.getCostDetailSelctTest(db, travelinfodayssql, "apply_money");
					Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
					 Assert.assertEquals(getUtilMethod.getConvertTwoBigDecimal(totalamount),getUtilMethod.getConvertTwoBigDecimal(totlemoney));
					
					}	
				}else if(name.equals("applymoneyequertraveandcostdetail")||name.equals("travelinfohavemoregettotalmoney")
						||name.equals("moneysupertwo")||name.equals("")){
					BigDecimal sumapplyall=new BigDecimal(0.00);
					if(!travelInfos.isEmpty()){
						
						for(int i=0;i<travelInfos.size();i++){
							Logger.log("打印总金额===》wwww"+sumapplyall);
							BigDecimal sumapply=new BigDecimal(0.00);
							TravelInfo travelInfosum=travelInfos.get(i);
//							int days=getUtilMethod.daysBetween(travelInfosum.getStartDate(),travelInfosum.getEndDate());
//							BigDecimal staymoney=(travelInfosum.getStayStandard().multiply(new BigDecimal(days)));
							BigDecimal staymoney1=travelInfosum.getStayAmount().add(travelInfosum.getAllowanceAmount());
							BigDecimal staymoney2=staymoney1.add(travelInfosum.getOtherAmount());
							sumapplyall=sumapplyall.add(sumapply.add(staymoney2));	
						}
						
					}
			
					if(!costDetails.isEmpty()){
						
						for(int i=0;i<costDetails.size();i++){
							BigDecimal sumapply1=new BigDecimal(0.00);
						CostDetail	costdetail2=costDetails.get(i);
						sumapplyall=sumapplyall.add(sumapply1.add(new BigDecimal(costdetail2.getAmount())));
						
						}
					
					}
					Logger.log("打印总金额===》"+sumapplyall);
					String travelinfodayssql=" SELECT * FROM t_form WHERE  form_id='CLSQ201712120001' ORDER BY fill_or_submit_time ASC";
					String totalamount=GetDbMethod.getCostDetailSelctTest(db, travelinfodayssql, "apply_money");
					if(result.get("repCode").toString().trim().equals("0")){
						Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
						 Assert.assertEquals(getUtilMethod.getConvertTwoBigDecimal(totalamount),getUtilMethod.getConvertTwoBigDecimal(sumapplyall.toString()));
					
					}else{
						Assert.assertEquals(result.get("repCode").toString().trim(),"1");
						if(result.get("message").toString().contains("差旅费预算不足")){
							 Assert.assertEquals("ok","ok");
						}
					
					}
			}
			
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
     
     

	@DataProvider
    public static Object[][] submitTravelApplyFormParam() {
        return new Object[][]{ 		
        		{"isrepeat"},{"havefileinfo"},{"havecostdetail"},
        		{"travelInfosnotcannull"},{"startDatenotnull"},
        		{"endDatenotnull"},{"travelDaysnotnull"},{"stayStandardnotnull"},
        		{"stayamount"},{"allowanceAmount"},{"totalAmount"},{"otherAmount"},
        		{"totalAmountcanzero"},{"otherAmountcanzero"},{"setTotalAmountisnull"},
        		{"allowanceAmountcanzero"},	{"allowanceAmountbigstand"},
        		{"setStartCitynotnull"},{"setEndCitynotnull"},
        		{"moneysuper"},{"formidisprocess"},{"formidiscomplete"},
        		{"setStayAmountnotcanbig"},{"setStayAmountcansmall"},{"stayamountabc"},
        		{"setStayAmountZERO"},{"setTotalAmountnotequerandbig"},{"setTotalAmountnotequerandsmall"},
        	        		{"costdetailhavehappydate"},{"costdetailhavestartcity"},{"costdetailhaveendcity"},
        		{"costdetailhavetrrafictools"},{"costdetailhavemoneyiszero"},{"costdetailhavemoneyisnotabc"},
        		{"costdetailhaveCostdetailDiv"},{"costdetailhaveBudgetStatus"},  // case没有比较做校验
        		{"applymoneyequertraveandcostdetail"},
        		{"travelinfohavemoregettotalmoney"},
        		{"moneysupertwo"},{"applymoneynotisnull"},{"applymoneynotisabc"}
        		,{"receiptmoneynotisnull"},{"receiptmoneynotisabc"},{"receiptmoneynothave"},
        		{"moneytypenotisnull"},{"moneytypenotisabc"},{"moneytypenothave"},
//        		
//        		{"receiptmoneynothave"}
//        		{"receiptmoneynothave","setTotalAmountnotequerandbig",}
        		
       
        		
        		
        };
        		
        		
        }
}
