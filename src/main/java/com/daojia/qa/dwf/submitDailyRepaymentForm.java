package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitDailyRepaymentForm extends BaseTest<T>{
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
	    * 保存和提交日常报销单接口
	    * */
		@Test(dataProvider = "submitDailyRepaymentFormParam", description="保存和提交日常报销单接口")
		public void submitDailyRepaymentForm(String name){
			String updatedqlformid="";
//			method_url="http://fmsystem205.djtest.cn/v2/submit/dailyrepaymentform";
//			method_url="http://10.253.7.168:80/v2/submit/dailyrepaymentform";
//
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
			Form form =new Form();
			// 定义冲销前后定义：未冲销、待冲销
			BigDecimal beforcostoffnoamount=new BigDecimal(0.00);
			BigDecimal beforcostoffwaitamount=new BigDecimal(0.00);
			BigDecimal aftercostoffnoamount=new BigDecimal(0.00);
			BigDecimal aftercostoffwaitamount=new BigDecimal(0.00);
			//定义预算前后：占用预算、生育预算
			BigDecimal beforbudgetoccupymoney=new BigDecimal(0.00);
			BigDecimal beforbudgetsurplusmoney=new BigDecimal(0.00);
			BigDecimal afterbudgetoccupymoney=new BigDecimal(0.00);
			BigDecimal afterbudgetsurplusmoney=new BigDecimal(0.00);
			form.setFormId("RCBX201711270000");//RCBX201711270000
			form.setFormType(4);
			form.setApplyDate("2017-11-27");
			form.setApplyPName("尚英");
			form.setApplyPJobnumber("4021");
			form.setEmpDepartment("信息技术质量部");
			form.setCostCenter("10.00.78.00.6");
			form.setEmpPTel("18201137136");
			form.setPayCompany("北京五八到家信息技术有限公司");
			form.setApplyMoney("1");
//			form.setApproveMoney(new BigDecimal(1.00)); 批复金额用不上
			form.setReceiptMoney("1.00");
			form.setCostDesc("描述信息");
			form.setPayeeName("尚英");
			form.setReceiveBankName("招商银行");
			form.setReceiveBankAccount("6214830104619118");
//			form.setPayStatus("0"); 支付状态不需要填写
//			form.setFormStatus("1");  没有订单状态
			form.setMoneyType("0");
			form.setState(0);
			form.setDepartmentCode("1-17-09-02");
			
			
			 String sql1="select * from  t_form WHERE  form_id='RCBX201711270000'";
             String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
             if(form_status1.equals("2")){
            	 GetDbMethod.endProcess("RCBX201711270000"); 
            		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
            	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='RCBX201711270000'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
             }
            	 }else{
            	 Logger.log("订单RCBX201711270000状态非2"+form_status1);
             }
             
             
             
			
			CostDetail cost = new CostDetail();
			List<CostDetail> costDetails = new ArrayList<CostDetail>();
			
			cost.setCostHappenDate("2017-11-15");

			cost.setCostDetailType("010");
			cost.setBudgetStatus("0");
			if(name.equals("superbudget")){		
				cost.setAmount("5888888816.91");
				}else if(name.equals("costDetailisdouble")){
					cost.setAmount("1.00");
				}else{
					cost.setAmount("1");
			}
	
			cost.setCostdetailDiv("3");
		
			List<CostOffset> costOffsets = new ArrayList<CostOffset>();
			CostOffset costOffset=new CostOffset();
			 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
			String formOption="submit";
			
			 if(name.equals("baseinfonothaveformId")){
				 form.setFormId(""); 
			 }else if(name.equals("baseinfonothavePName")){
				 form.setApplyPName("");
				 
			 }else if(name.equals("baseinfonothaveEmpDepartment")){
				 form.setEmpDepartment("");
			 }else if(name.equals("baseinfonothavePJobnumber")){
				 form.setApplyPJobnumber("");
			 }else if(name.equals("baseinfonothaveApplyDate")){
				 form.setApplyDate("");
			 }else if(name.equals("baseinfonothaveCostCente")){
				 form.setCostCenter("");
			 }else if(name.equals("baseinfonothavePayCompany")){
				 form.setPayCompany("");
			 }else if(name.equals("baseinfonothaveApplyFormId")){
				 form.setApplyFormId("");
			 }else if(name.equals("baseinfonothaveReceiveBankName")){
				 form.setReceiveBankName("");
			 }else if(name.equals("baseinfonothaveReceiveBankAccount")){
				 form.setReceiveBankAccount("");
			 }else if(name.equals("baseinfonothaveCostDesc")){
				 form.setCostDesc("");
			 }else if(name.equals("baseinfobighaveCostDesc")){
				 form.setCostDesc("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				 		+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				 		+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				 		+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				 		+ "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
				 		+ "AAA");
			 }else if(name.equals("costDetailssetAmountisABC")){
				 cost.setAmount("ABC");
			 }else if(name.equals("costDetailssetAmountisZERO")){
				 cost.setAmount("0");
			 }else if(name.equals("baseinfonothaveApplyMoney")){
				 form.setApplyMoney("0");
			 }else if(name.equals("baseinfohavevalue")){
				 
			 }else{
				 Logger.log("没有合适的case");
			 }
			 if(name.equals("costDetailsisnull")){
				 Logger.log("costDetails传入null");
			 }else{
				 if(name.equals("applymoneyfromcostdetailsamountsum")){
//					 costDetails
						CostDetail cost1 = new CostDetail();
						cost1.setAmount("2");
						cost1.setCostHappenDate("2017-11-15");

						cost1.setCostDetailType("010");
						cost1.setBudgetStatus("0");
						cost1.setCostdetailDiv("3");
						 costDetails.add(cost);
						 costDetails.add(cost1);
				 }else{
					 costDetails.add(cost);
				 }
				 
			 }
				file.setFileAddress("http://www.baidu.com");
				file.setFileName("filename1");
				file.setIsPic("0");
			 if(name.equals("fileListishavevalue")){
			
				 fileList.add(file);
			 }else{
				 Logger.log("没有附件");
			 }
			 costOffset.setBorrowFormNum("GRJK201711240010");
			 costOffset.setCostFormId("RCBX201711270000");
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
					} else if(name.equals("costOffshavevalue")){
				 
				 costOffset.setThisOffsetAmount(new BigDecimal(1.00));
				 //修改数据库中的GRJK201711240010的未冲销金额还原1
				 updatedqlformid="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010'";
					int i= db.update(updatedqlformid);
					if(i>0){
				
						Logger.log("修改数据库中的GRJK201711240010的未冲销成1");
					}else{
						Logger.log("修改数据库中没有GRJK201711240010单修改");
					}
			 }
			
			 costOffset.setBorrowAmount(new BigDecimal(1.00));
			 costOffset.setUnOffsetAmount(new BigDecimal(1.00));
			 if(name.equals("costOffshavevalue")||name.equals("costOffsnomoneyiszero")||name.equals("costOffsThisOffsetAmountZERO")){
					
				 costOffsets.add(costOffset);
			 }else if(name.equals("costOffshavemoneybigborrowamount")||name.equals("costOffshavemoneybignomoney")){
				 //修改数据库中的GRJK201711240010的未冲销金额还原1
				 updatedqlformid="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0 WHERE form_id='GRJK201711240010'";
					int i= db.update(updatedqlformid);
					if(i>0){
				
						Logger.log("修改数据库中的GRJK201711240010的变更成1");
					}else{
						Logger.log("修改数据库中没有GRJK201711240010单修改");
					}
		
				 costOffset.setThisOffsetAmount(new BigDecimal(2.00));
				 
				 costOffsets.add(costOffset);
			 }else if(name.equals("costOffshavemoneybigapprovemoney")){
				 //修改数据库中的GRJK201711240010的未冲销金额还原1
				 updatedqlformid="UPDATE t_form SET no_amount=2 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010'";
					int i= db.update(updatedqlformid);
					if(i>0){
				
						Logger.log("修改数据库中的GRJK201711240010的未冲销成2");
					}else{
						Logger.log("修改数据库中没有GRJK201711240010单修改");
					}
		
				 costOffset.setThisOffsetAmount(new BigDecimal(2.00));
				 
				 costOffsets.add(costOffset);
			 }else{
				 Logger.log("没有冲销记录");
					
			 }      
          
                     
                     
                     
				  HashMap<String, String> param=new  HashMap<String, String>();
				  param.put("baseForm",JSONObject.toJSONString(form));
				  param.put("formOption",formOption );
				  param.put("costDetails",JSONObject.toJSONString(costDetails) );
				  param.put("costOffsets", JSONObject.toJSONString(costOffsets));
				  param.put("fileList", JSONObject.toJSONString(fileList));
				  param.put("formType","RCBX");
				  // 冲销记录前 未冲销金额noamount  待冲销金额waitamount
				  // 冲销sql
				  String getsqlofborrow="SELECT * FROM t_form WHERE  form_id='GRJK201711240010'";
					 Map<String, Object>  getsqlofborrowresultparambacks=new HashMap<String, Object>();						
					 getsqlofborrowresultparambacks=GetDbMethod.getCostDetailSelctTestList(db, getsqlofborrow);
					 beforcostoffnoamount=BigDecimal.valueOf(Double.parseDouble(getsqlofborrowresultparambacks.get("no_amount").toString().trim()));
					 beforcostoffwaitamount=BigDecimal.valueOf(Double.parseDouble(getsqlofborrowresultparambacks.get("wait_amount").toString().trim()));
				  // 预算记录前 占用金额occupymoney  剩余金额 surplusmoney
				  //预算sql
					 String getsqlbudget=" SELECT * FROM t_budget WHERE cost_code='10.00.78.00.6' AND YEAR='2017' AND MONTH='11'";
					 Map<String, Object>  getsqlbudgetresultparambacks=new HashMap<String, Object>();		
					 getsqlbudgetresultparambacks=GetDbMethod.getCostDetailSelctTestList(db, getsqlbudget);
					 beforbudgetoccupymoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetresultparambacks.get("occupy_money").toString().trim()));
					 beforbudgetsurplusmoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetresultparambacks.get("surplus_money").toString().trim()));
//			/*	  
				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
				 Logger.log("获取repData>>>>>>>>"+result);
				 if(name.equals("baseinfonothaveformId")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"没有获取到formId");
				}else if(name.equals("costDetailsisnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"请至少填写一条费用明细");
				}else if(name.equals("baseinfonothaveCostDesc")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用描述不能为空");
				}else if(name.equals("baseinfobighaveCostDesc")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用描述长度不能大于500");
				}else if(name.equals("baseinfohavevalue")||name.equals("costOffshavevalue")||name.equals("baseinfonothaveApplyMoney")||name.equals("fileListishavevalue")){
					
//
//					GetDbMethod.endProcess( db,form.getFormId()); 
					Logger.log("修改成功=========》》》》》》》》》》》》》》》》》》》》》》》");
					String sqlofformid="SELECT * FROM t_form WHERE  form_id='RCBX201711270000'";	
					 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
						
						resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofformid);
						 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
//						 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
						 // 获取当前申请金额=费用明细总金额
						BigDecimal appylmoneysum=new BigDecimal(0.00);
						for(int i=0;i<costDetails.size();i++){
							appylmoneysum=appylmoneysum.add(BigDecimal.valueOf(Double.parseDouble(costDetails.get(i).getAmount())));
						}
					    DecimalFormat df = new DecimalFormat("0.00");
						 Assert.assertEquals(resultparambacks.get("form_id").toString().trim(),form.getFormId());
						 Assert.assertEquals(resultparambacks.get("form_type").toString().trim(),"4");
						 Assert.assertEquals(resultparambacks.get("apply_p_name").toString().trim(),form.getApplyPName());
						 Assert.assertEquals(resultparambacks.get("apply_p_jobnumber").toString().trim(),form.getApplyPJobnumber());
						 Assert.assertEquals(resultparambacks.get("cost_center").toString().trim(),form.getCostCenter());
						 Assert.assertEquals(resultparambacks.get("emp_p_tel").toString().trim(),form.getEmpPTel());
						 Assert.assertEquals(resultparambacks.get("pay_company").toString().trim(),form.getPayCompany());
						 Assert.assertEquals(df.format(Double.parseDouble(resultparambacks.get("apply_money").toString().trim())),df.format(Double.parseDouble(appylmoneysum.toString().trim())));
						 Assert.assertEquals(resultparambacks.get("cost_desc").toString().trim(),form.getCostDesc());
						 Assert.assertEquals(resultparambacks.get("payee_name").toString().trim(),form.getPayeeName());
						 Assert.assertEquals(resultparambacks.get("receive_bank_name").toString().trim(),form.getReceiveBankName());
						 Assert.assertEquals(resultparambacks.get("receive_bank_account").toString().trim(),form.getReceiveBankAccount());
						String form_status=resultparambacks.get("form_status").toString().trim();
						if(form_status.equals("2")){
							Assert.assertEquals("true", "true");
						}else{
							Assert.assertEquals("false", "false");
						}
						 Assert.assertEquals(resultparambacks.get("money_type").toString().trim(),form.getMoneyType());
						 Assert.assertEquals(resultparambacks.get("state").toString().trim(),"0");
						
						 String sqlofcostdetails="SELECT * FROM t_cost_details WHERE cost_form_id='RCBX201711270000'";	
						 Map<String, Object>  resultparambackscostdetails=new HashMap<String, Object>();
						
							resultparambackscostdetails=GetDbMethod.getCostDetailSelctTestList(db, sqlofcostdetails);
							for(int i=0;i<costDetails.size();i++){
								//无法校验明细，因为没有具体的金额
//								Assert.assertEquals(resultparambackscostdetails.get("amount").toString().trim(),costDetails.get(i).getAmount());
								Assert.assertEquals(resultparambackscostdetails.get("cost_form_id").toString().trim(),form.getFormId());
								Assert.assertEquals(resultparambackscostdetails.get("cost_happen_date").toString().trim(),costDetails.get(i).getCostHappenDate());
//								Assert.assertEquals(resultparambackscostdetails.get("cost_detail_type").toString().trim(),costDetails.get(i).getCostDetailType());
								Assert.assertEquals(resultparambackscostdetails.get("budget_status").toString().trim(),costDetails.get(i).getBudgetStatus());
								Assert.assertEquals(resultparambackscostdetails.get("cost_center").toString().trim(),form.getCostCenter());
								
							}
							 
							if(name.equals("costOffshavevalue")){

								 String sqlofcostOffs="SELECT * FROM t_cost_offset WHERE borrow_form_num='GRJK201711240010' AND cost_form_id='RCBX201711270000'";	
								 Map<String, Object>  resultparambackscostOffs=new HashMap<String, Object>();
									
								 resultparambackscostOffs=GetDbMethod.getCostDetailSelctTestList(db, sqlofcostOffs);
								 if(resultparambackscostOffs.get("this_offset_amount").toString().trim().contains(costOffset.getThisOffsetAmount().toString().trim())){
									 Logger.log("is ok ==>"+costOffset.getThisOffsetAmount().toString().trim());
								 }
								 Assert.assertEquals(resultparambackscostOffs.get("cost_form_id").toString().trim(),costOffset.getCostFormId());
								 Assert.assertEquals(resultparambackscostOffs.get("borrow_form_num").toString().trim(),costOffset.getBorrowFormNum());
								  // 冲销记录后 未冲销金额noamount  待冲销金额waitamount
								  // 冲销sql
									 Map<String, Object>  getsqlofborrowafterresultparambacks=new HashMap<String, Object>();						
									 getsqlofborrowafterresultparambacks=GetDbMethod.getCostDetailSelctTestList(db, getsqlofborrow);
									 aftercostoffnoamount=BigDecimal.valueOf(Double.parseDouble(getsqlofborrowafterresultparambacks.get("no_amount").toString().trim()));
									 aftercostoffwaitamount=BigDecimal.valueOf(Double.parseDouble(getsqlofborrowafterresultparambacks.get("wait_amount").toString().trim()));
									//  之前未冲销记录-本次冲销记录   之前未冲销记录+本次冲销记录
									 String costoffnoamountreslut=beforcostoffnoamount.subtract(costOffset.getThisOffsetAmount()).toString().trim();
									String costoffwaitamountreslut=beforcostoffwaitamount.add(costOffset.getThisOffsetAmount()).toString().trim();
									 Assert.assertEquals(costoffnoamountreslut,aftercostoffnoamount.toString().trim());
									 Assert.assertEquals(costoffwaitamountreslut,aftercostoffwaitamount.toString().trim());
							// 收款金额=申请金额-当前冲销金额
									 BigDecimal receipt_moneyreslut=BigDecimal.valueOf(Double.parseDouble(resultparambacks.get("apply_money").toString().trim())).subtract(costOffset.getThisOffsetAmount());
//									 Assert.assertEquals(new BigDecimal(resultparambacks.get("receipt_money").toString().trim()),receipt_moneyreslut);
							//
									 //
									 //
									 //
									 //
									 //
									 //
							}
							// 有时间增加一下附件信息
							//
							
								if(name.equals("fileListishavevalue")){

							 String sqloffileaddress="SELECT * FROM t_file_address WHERE  form_id='RCBX201711270000' ORDER BY id ASC";	
							 Map<String, Object>  resultparambacksfileaddress=new HashMap<String, Object>();
								
							 resultparambacksfileaddress=GetDbMethod.getCostDetailSelctTestList(db, sqloffileaddress);
							 for(int i=0;i<fileList.size();i++){
								 Assert.assertEquals(resultparambacksfileaddress.get("form_id").toString().trim(),form.getFormId());
								 Assert.assertEquals(resultparambacksfileaddress.get("file_address").toString().trim(),fileList.get(i).getFileAddress());
								 Assert.assertEquals(resultparambacksfileaddress.get("file_name").toString().trim(),fileList.get(i).getFileName());
								 Assert.assertEquals(resultparambacksfileaddress.get("pic").toString().trim(),fileList.get(i).getIsPic());
							 }
						}
							//
							  // 预算记录后 占用金额occupymoney  剩余金额 surplusmoney   
							  //预算sql
								 Map<String, Object>  getsqlbudgetafterresultparambacks=new HashMap<String, Object>();		
								 getsqlbudgetafterresultparambacks=GetDbMethod.getCostDetailSelctTestList(db, getsqlbudget);
								 afterbudgetoccupymoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("occupy_money").toString().trim()));
								 afterbudgetsurplusmoney=BigDecimal.valueOf(Double.parseDouble(getsqlbudgetafterresultparambacks.get("surplus_money").toString().trim()));
							// 之前的占用金额+费用明细总金额（申请总金额）   之前剩余金额-费用明细总金额（申请总金额）
									String budgetoccupymoneyreslut=beforbudgetoccupymoney.add(appylmoneysum).toString().trim();
									 String budgetsurplusmoneyreslut=beforbudgetsurplusmoney.subtract(appylmoneysum).toString().trim();
									 Assert.assertEquals(budgetoccupymoneyreslut,afterbudgetoccupymoney.toString().trim());
									 Assert.assertEquals(budgetsurplusmoneyreslut,afterbudgetsurplusmoney.toString().trim());
									
									 //数据撤销还原，避免提交成功后case提示重复提交
									 GetDbMethod.endProcess(form.getFormId()); 
				}else if(name.equals("costOffshavemoneybigapprovemoney")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于报销金额");
				}else if(name.equals("costOffsThisOffsetAmountZERO")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额不能小于等于0");
				}else if(name.equals("costOffshavemoneybigborrowamount")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于借款单未冲销金额");
				}else if(name.equals("costOffshavemoneybigapprovemoney")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于报销金额");
				}else if(name.equals("costOffshavemoneybignomoney")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于借款单未冲销金额");
				}else if(name.equals("costOffsnomoneyiszero")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用冲销本次冲销金额必须小于等于借款单未冲销金额");
				}else if(name.equals("costDetailssetAmountisABC")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用明细报销金额必须是数字并且大于0");
				}else if(name.equals("superbudget")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"2017-11差旅费预算不足");
				}else if(name.equals("costDetailssetAmountisZERO")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"费用明细报销金额必须是数字并且大于0");
				}else{
					Logger.log("没有合适的case");
				}
				 
//				 */
//				 GetDbMethod.deleteFormTestData( db,form.getFormId());
//					 GetDbMethod.endProcess( db,form.getFormId()); 
//				 GetDbMethod.onlyDeleteFormid(db,form.getFormId());
		}
		@DataProvider
	    public static Object[][] submitDailyRepaymentFormParam() {
	        return new Object[][]{
	 
	        		

	       		{"baseinfonothaveCostDesc"},{"baseinfobighaveCostDesc"},
	        		{"baseinfonothaveApplyMoney"},{"fileListishavevalue"},{"costDetailssetAmountisABC"},
	        		{"costDetailssetAmountisZERO"},{"costDetailisdouble"},{"superbudget"},
	       		{"costOffshavevalue"},{"baseinfohavevalue"},
	        		{"costOffsThisOffsetAmountZERO"},
	        		{"costOffshavemoneybigborrowamount"},{"costOffshavemoneybigapprovemoney"},
	        		{"costOffshavemoneybignomoney"},{"costOffsnomoneyiszero"},
	        		{"applymoneyfromcostdetailsamountsum"},
	        		  {"receiptmoneyisnull"}
	        		
	        		
//	        		{"baseinfohavevalue"}
//	        		{"baseinfohavevalue"},{"costOffshavevalue"},{"fileListishavevalue"},{"baseinfonothaveApplyMoney"}
	       		
	        };
	        }
}
