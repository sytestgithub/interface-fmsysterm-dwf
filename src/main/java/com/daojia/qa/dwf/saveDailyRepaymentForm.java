package com.daojia.qa.dwf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

public class saveDailyRepaymentForm extends BaseTest<T>{
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
		@Test(dataProvider = "saveDailyRepaymentFormParam", description="保存和提交日常报销单接口")
//	@Test(description="保存和提交日常报销单接口")
		public void getsaveDailyRepaymentForm(String name){
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//			method_url="http://fmsystem205.djtest.cn/v2/submit/dailyrepaymentform";
////			method_url="http://10.253.7.168:80/v2/submit/dailyrepaymentform";
////
			Form form =new Form();
//		
			form.setFormId("RCBX201711270004");//RCBX201711270000
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
			form.setInnerRemark("abc");

			 String sql1="select * from  t_form WHERE  form_id='RCBX201711270004'";
             String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
             if(form_status1.equals("2")){
            	 GetDbMethod.endProcess("RCBX201711270004"); 
            		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
            	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='RCBX201711270004'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
             }
            	 }else{
            	 Logger.log("订单RCBX201711270004状态非2"+form_status1);
             }
             
			
			
			CostDetail cost = new CostDetail();
			List<CostDetail> costDetails = new ArrayList<CostDetail>();
			
			cost.setCostHappenDate("2017-11-15");

			cost.setCostDetailType("010");
			cost.setBudgetStatus("0");
			cost.setAmount("1");
			cost.setCostdetailDiv("3");
			
			List<CostOffset> costOffsets = new ArrayList<CostOffset>();
			CostOffset costOffset=new CostOffset();
			//修复数据
			 String formprocess="UPDATE t_form SET no_amount=1 ,already_amount=0,wait_amount=0  WHERE form_id='GRJK201711240010' ";
		  		int num= db.update(formprocess);
				if(num>0){
					Logger.log("修改数据库中的GRJK201711240010的未冲销成1");
				}else{
					Logger.log("修改数据库中没有GRJK201711240010单修改");
				}
			
			
			 costOffset.setBorrowFormNum("GRJK201711240010");
			 costOffset.setCostFormId("RCBX201711270004");
			 costOffset.setThisOffsetAmount(new BigDecimal(1.00));
			 costOffset.setBorrowAmount(new BigDecimal(1.00));
			 costOffset.setUnOffsetAmount(new BigDecimal(1.00));
			 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
			String formOption="save";
			file.setFileAddress("http://www.baidu.com");
			file.setFileName("filename1");
			file.setIsPic("0");
			file.setFileSize("0");
			Logger.log("是否进入方法");
			 if(name.equals("savenothaveanyvalue")){
				 form.setFormType(0);
					form.setApplyDate("");
					form.setApplyPName("");
					form.setApplyPJobnumber("");
					form.setEmpDepartment("");
					form.setCostCenter("");
					form.setEmpPTel("");
					form.setPayCompany("");
					form.setApplyMoney("0");
//					form.setApproveMoney(new BigDecimal(1.00)); 批复金额用不上
					form.setReceiptMoney("0");
					form.setCostDesc("");
					form.setPayeeName("");
					form.setReceiveBankName("");
					form.setReceiveBankAccount("");
//					form.setPayStatus("0"); 支付状态不需要填写
//					form.setFormStatus("1");  没有订单状态
					form.setMoneyType("0");
					form.setState(0);
					form.setDepartmentCode("");
			 }else if(name.equals("moneytypenotisnull")){
				form.setMoneyType("");
				 
			 }else if(name.equals("applymoneynotisnull")){
			     form.setApplyMoney("");
				 
			 }else if(name.equals("receiptmoneynotisnull")){
				 form.setReceiptMoney("");
				 
			 }else if(name.equals("updatesavehaveall")){
				 costOffsets.add(costOffset);
				 costDetails.add(cost);
				 fileList.add(file);
				 
			 }else if(name.equals("saveandsubmit")){
				 costOffsets.add(costOffset);
				 costDetails.add(cost);
				 fileList.add(file);
				 
				  formOption="submit";
				  //数据撤销还原，避免提交成功后case提示重复提交
					
			 }else if(name.equals("processstatusnotcansave")){
				 String sqlofformid=" SELECT * FROM t_form WHERE  form_type=4 AND form_status=2 ORDER BY apply_date DESC";
			    String formId=GetDbMethod.getCostDetailSelctTest(db, sqlofformid, "form_id");
				 form.setFormId(formId);
				  formOption="submit";
					 costDetails.add(cost);
			 }else if(name.equals("completestatusnotcansave")){
				 String sqlofformid=" SELECT * FROM t_form WHERE  form_type=4 AND form_status=9 ORDER BY apply_date DESC";
				 String formId=GetDbMethod.getCostDetailSelctTest(db, sqlofformid, "form_id");
				    form.setFormId(formId);
					  formOption="submit";
						 costDetails.add(cost);
				 }else if(name.equals("formstatusisrecall")){
						String getformidsql=" UPDATE t_form SET form_status=8 WHERE  form_id='RCBX201711270004'";
						GetDbMethod.updateSelectCondition(db, getformidsql);
						
					}else if(name.equals("formstatusisReject")){
						String getformidsql=" UPDATE t_form SET form_status=3 WHERE  form_id='RCBX201711270004'";
						GetDbMethod.updateSelectCondition(db, getformidsql);
						
					}else {
				 Logger.log("没有合适的case");
			 }
			 
			
			 
			 HashMap<String, String> param=new  HashMap<String, String>();
			  param.put("baseForm",JSONObject.toJSONString(form));
			  param.put("formOption",formOption );
			  param.put("costDetails",JSONObject.toJSONString(costDetails) );
			  param.put("costOffsets", JSONObject.toJSONString(costOffsets));
			  param.put("fileList", JSONObject.toJSONString(fileList));
			  param.put("formType","RCBX");
			  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
				 Logger.log("获取repData>>>>>>>>"+result);
				 if(name.equals("saveandsubmit")||name.equals("savenothaveanyvalue")||name.equals("updatesavehaveall")){
					 
					 getverifaction( name, result, costDetails,fileList,costOffset,form);
				 }else if(name.equals("processstatusnotcansave")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！");
					 
				 }else if(name.equals("completestatusnotcansave")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"该单据已提交成功，请勿重复提交！");
					 
				 }else if(name.equals("moneytypenotisnull")){ //是否需要给予具体的提示呢
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"保存失败");
					 
				 }else if(name.equals("applymoneynotisnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"保存失败");
					 
				 }else if(name.equals("receiptmoneynotisnull")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
					 Assert.assertEquals(result.get("message").toString().trim(),"保存失败");
					 
				 }else{
					 Logger.log("没有合适的case");
				 }
//				 GetDbMethod.endProcess(form.getFormId()); 
		}

		  @DataProvider
		    public static Object[][] saveDailyRepaymentFormParam() {
		        return new Object[][]{
		       			{"savenothaveanyvalue"},{"updatesavehaveall"},{"saveandsubmit"},{"processstatusnotcansave"},
	        		{"completestatusnotcansave"},{"moneytypenotisnull"},{"applymoneynotisnull"},{"receiptmoneynotisnull"},
	        		{"savenothaveanyvalue"},{"formstatusisrecall"},	{"formstatusisReject"}
//		        		{"saveandsubmit"},		       	
		        		
		        		
		        };
		        
		        }
		  
		  public void getverifaction(String name, JSONObject result,List<CostDetail> costDetails,List<UploadFileDto> fileList,CostOffset costOffset,Form form){
			
				Logger.log("修改成功=========》》》》》》》》》》》》》》》》》》》》》》》");
				String sqlofformid="SELECT * FROM t_form WHERE  form_id='RCBX201711270004'";	
				 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
					
					resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofformid);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
						if(costDetails.size()>0||!costDetails.isEmpty()){
//					 // 获取当前申请金额=费用明细总金额
						BigDecimal appylmoneysum=new BigDecimal(0.00);
						for(int i=0;i<costDetails.size();i++){
							appylmoneysum=appylmoneysum.add(BigDecimal.valueOf(Double.parseDouble(costDetails.get(i).getAmount())));
						} 
						}
						if(name.equals("savenothaveanyvalue")){
							Logger.log("--->>>",form.getFormId());
							Assert.assertEquals(resultparambacks.get("form_id").toString().trim(),form.getFormId());
						}else{
						Logger.log("进入savehaveanyvalue校验");
					Assert.assertEquals(resultparambacks.get("form_id").toString().trim(),form.getFormId());
					 Assert.assertEquals(resultparambacks.get("form_type").toString().trim(),"4");
					 Assert.assertEquals(resultparambacks.get("apply_p_name").toString().trim(),form.getApplyPName());
					 Assert.assertEquals(resultparambacks.get("apply_p_jobnumber").toString().trim(),form.getApplyPJobnumber());
					 Assert.assertEquals(resultparambacks.get("cost_center").toString().trim(),form.getCostCenter());
					 Assert.assertEquals(resultparambacks.get("emp_p_tel").toString().trim(),form.getEmpPTel());
					 Assert.assertEquals(resultparambacks.get("pay_company").toString().trim(),form.getPayCompany());
					
//					Assert.assertEquals(new BigDecimal(resultparambacks.get("apply_money").toString().trim()),appylmoneysum);
					 Assert.assertEquals(resultparambacks.get("cost_desc").toString().trim(),form.getCostDesc());
					 Assert.assertEquals(resultparambacks.get("payee_name").toString().trim(),form.getPayeeName());
					 Assert.assertEquals(resultparambacks.get("receive_bank_name").toString().trim(),form.getReceiveBankName());
					 Assert.assertEquals(resultparambacks.get("receive_bank_account").toString().trim(),form.getReceiveBankAccount());
					String form_status=resultparambacks.get("form_status").toString().trim();
					if(form_status.equals("2")){
						 Assert.assertEquals("0提交成功","0提交成功");
					}else if(form_status.equals("1")){
						Logger.log("-------------------------保存成功");
						Assert.assertEquals("保存成功", "保存成功");
					}else{
						Assert.assertEquals("失败", "有问题");
					}
					Assert.assertEquals(resultparambacks.get("money_type").toString().trim(),form.getMoneyType());
					 Assert.assertEquals(resultparambacks.get("state").toString().trim(),"0");
					
					
						if(costDetails.size()>0||!costDetails.isEmpty()){
							
							
							 String sqlofcostdetails="SELECT * FROM t_cost_details WHERE cost_form_id='RCBX201711270004'";	
							 Map<String, Object>  resultparambackscostdetails=new HashMap<String, Object>();
							
								resultparambackscostdetails=GetDbMethod.getCostDetailSelctTestList(db, sqlofcostdetails);
								for(int i=0;i<costDetails.size();i++){
									//无法校验明细，因为没有具体的金额
//									Assert.assertEquals(resultparambackscostdetails.get("amount").toString().trim(),costDetails.get(i).getAmount());
									Assert.assertEquals(resultparambackscostdetails.get("cost_form_id").toString().trim(),form.getFormId());
									Assert.assertEquals(resultparambackscostdetails.get("cost_happen_date").toString().trim(),costDetails.get(i).getCostHappenDate());
//									Assert.assertEquals(resultparambackscostdetails.get("cost_detail_type").toString().trim(),costDetails.get(i).getCostDetailType());
									Assert.assertEquals(resultparambackscostdetails.get("budget_status").toString().trim(),costDetails.get(i).getBudgetStatus());
//									Assert.assertEquals(resultparambackscostdetails.get("cost_center").toString().trim(),form.getCostCenter());
									
								}
							
							
						}
						// 费用冲销如果有时间计算一下前后一致
						
						
						
						
						// 附件上传
						if(fileList.size()>0||!fileList.isEmpty()){
							 String sqloffileaddress="SELECT * FROM t_file_address WHERE  form_id='RCBX201711270004' ORDER BY id ASC";	
							 Map<String, Object>  resultparambacksfileaddress=new HashMap<String, Object>();
								
							 resultparambacksfileaddress=GetDbMethod.getCostDetailSelctTestList(db, sqloffileaddress);
							 for(int i=0;i<fileList.size();i++){
								 Assert.assertEquals(resultparambacksfileaddress.get("form_id").toString().trim(),form.getFormId());
								 Assert.assertEquals(resultparambacksfileaddress.get("file_address").toString().trim(),fileList.get(i).getFileAddress());
								 Assert.assertEquals(resultparambacksfileaddress.get("file_name").toString().trim(),fileList.get(i).getFileName());
								 Assert.assertEquals(resultparambacksfileaddress.get("pic").toString().trim(),fileList.get(i).getIsPic());
							 }
							 

							 String sqlofcostOffs="SELECT * FROM t_cost_offset WHERE borrow_form_num='GRJK201711240010' AND cost_form_id='RCBX201711270004'";	
							 Map<String, Object>  resultparambackscostOffs=new HashMap<String, Object>();
								
							 resultparambackscostOffs=GetDbMethod.getCostDetailSelctTestList(db, sqlofcostOffs);
							 
							 if(StringUtils.isNotEmpty(resultparambackscostOffs.get("cost_form_id").toString())){
								 if(resultparambackscostOffs.get("this_offset_amount").toString().trim().contains(costOffset.getThisOffsetAmount().toString().trim())){
									 Logger.log("is ok ==>"+costOffset.getThisOffsetAmount().toString().trim());
								 }
								 Assert.assertEquals(resultparambackscostOffs.get("cost_form_id").toString().trim(),costOffset.getCostFormId());
								 Assert.assertEquals(resultparambackscostOffs.get("borrow_form_num").toString().trim(),costOffset.getBorrowFormNum());
								  // 冲销记录后 未冲销金额noamount  待冲销金额waitamount
								 Assert.assertEquals(resultparambackscostOffs.get("cost_form_id").toString().trim(),costOffset.getCostFormId());
								 Assert.assertEquals(resultparambackscostOffs.get("borrow_form_num").toString().trim(),costOffset.getBorrowFormNum());
							 }
							 
							 
		  }
						}
		  }
		  
		  // 有时间计算预算前后一致
		  
}
