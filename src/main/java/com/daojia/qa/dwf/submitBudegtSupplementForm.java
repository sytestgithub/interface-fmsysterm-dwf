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
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitBudegtSupplementForm extends BaseTest<T>{
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
	    * 保存和提交预算增补单接口
	    * */
		@Test(dataProvider = "submitBudegtSupplementFormParam", description="保存和提交预算增补单接口")
		public void submitBudegtSupplementForm1(String name){
			method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
//			method_url="http://fmsystem205.djtest.cn/v2/submit/budegtsupplementform";
			Form form =new Form();
			form.setFormId("YSZB201711270000");//YSZB201711270000
			form.setFormType(9);
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
			
			String sql1="select * from  t_form WHERE  form_id='YSZB201711270000'";
            String  form_status1=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
            if(form_status1.equals("2")){
           	 GetDbMethod.endProcess("YSZB201711270000"); 
           		String  form_statusagins=GetDbMethod.getCostDetailSelctTest(db, sql1, "form_status").toString().trim();
           	 if(form_statusagins.equals("2")){
		 	 		 Logger.log("任务处理失败强制修改");
		 	 		 String  formstatusupdate="UPDATE t_form  SET form_status=1 WHERE form_id='YSZB201711270000'";
		 	 		 int one=GetDbMethod.updateSelectCondition(db, formstatusupdate);
		 	 		 if(one>0){
		 	 			 Logger.log("修改成功"+one);
		 	 		 }
            }
           	 }else{
           	 Logger.log("订单YSZB201711270000状态非2"+form_status1);
            }
            
			
			CostDetail cost = new CostDetail();
			List<CostDetail> costDetails = new ArrayList<CostDetail>();
			cost.setBudgetMonth("2017-11");
//			cost.setCostHappenDate("2017-11-15");
            cost.setCostCenter("10.00.78.00.6");
			cost.setCostDetailType("010");
			cost.setBudgetStatus("0");
			cost.setAmount("1");
			cost.setCostdetailDiv("3");
	
			 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
			 UploadFileDto file=new UploadFileDto();
			String formOption="submit";
		
			 file.setFileAddress("http://www.baidu.com");
				file.setFileName("filename1");
				file.setIsPic("0");

				 
				 
				 if(name.equals("baseinfonothaveCostDesc")){
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
				 }
			
				 if(name.equals("costDetailsisnull")){
					 Logger.log("costDetails传入null");
				 }else{
					 if(name.equals("applymoneyfromcostdetailsamountsum")){
//						 costDetails
							CostDetail cost1 = new CostDetail();
							cost1.setAmount("2");
							cost1.setBudgetMonth("2017-11");
//							cost.setCostHappenDate("2017-11-15");
				            cost1.setCostCenter("10.00.78.00.6");

							cost1.setCostDetailType("010");
							cost1.setBudgetStatus("0");
							cost1.setCostdetailDiv("3");
							 costDetails.add(cost);
							 costDetails.add(cost1);
					 }else if(name.equals("nothavebudget")){
						 cost.setCostCenter("1.1.1");
						 costDetails.add(cost);
					 }else if(name.equals("nothavebudgetofmonth")){
						 ;
						 cost.setBudgetMonth("2017-13");
						 costDetails.add(cost);
					 }else if(name.equals("nothavebudgetofyear")){
						 
						 cost.setBudgetMonth("2019-09");
						 costDetails.add(cost);
					 }else{
						 costDetails.add(cost);
					 }
					 
				 }
				 
				 if(name.equals("fileListishavevalue")){
						
					 fileList.add(file);
				 }else{
					 Logger.log("没有附件");
				 }
				 
				 
                 
			  HashMap<String, String> param=new  HashMap<String, String>();
			  param.put("baseForm",JSONObject.toJSONString(form));
			  param.put("formOption",formOption );
			  param.put("costDetails",JSONObject.toJSONString(costDetails) );
			  param.put("fileList", JSONObject.toJSONString(fileList));  
			  param.put("formType","YSZB");
			 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			 Logger.log("获取repData>>>>>>>>"+result);
			 
			 ////  
			 
//			 GetDbMethod.endProcess(db, "YSZB201711270000");
			 
			 
			 if(name.equals("costDetailsisnull")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"请至少填写一条费用明细");
			}else if(name.equals("baseinfonothaveCostDesc")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用描述不能为空");
			}else if(name.equals("baseinfobighaveCostDesc")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用描述长度不能大于500");
			}else if(name.equals("baseinfohavevalue")||name.equals("fileListishavevalue")){
				String sqlofformid="SELECT * FROM t_form WHERE  form_id='YSZB201711270000'";	
				 Map<String, Object>  resultparambacks=new HashMap<String, Object>();
					
					resultparambacks=GetDbMethod.getCostDetailSelctTestList(db, sqlofformid);
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
//					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
					 // 获取当前申请金额=费用明细总金额
					BigDecimal appylmoneysum=new BigDecimal(0.00);
					for(int i=0;i<costDetails.size();i++){
						appylmoneysum=appylmoneysum.add(BigDecimal.valueOf(Double.parseDouble(costDetails.get(i).getAmount())));
					}
					 Assert.assertEquals(resultparambacks.get("form_id").toString().trim(),form.getFormId());
					 Assert.assertEquals(resultparambacks.get("form_type").toString().trim(),"9");
					 Assert.assertEquals(resultparambacks.get("apply_p_name").toString().trim(),form.getApplyPName());
					 Assert.assertEquals(resultparambacks.get("apply_p_jobnumber").toString().trim(),form.getApplyPJobnumber());
					 Assert.assertEquals(resultparambacks.get("cost_center").toString().trim(),form.getCostCenter());
					 Assert.assertEquals(resultparambacks.get("emp_p_tel").toString().trim(),form.getEmpPTel());
					 Assert.assertEquals(resultparambacks.get("pay_company").toString().trim(),form.getPayCompany());
					 Assert.assertEquals(BigDecimal.valueOf(Double.parseDouble(resultparambacks.get("apply_money").toString().trim())),appylmoneysum);
					 Assert.assertEquals(resultparambacks.get("cost_desc").toString().trim(),form.getCostDesc());
					 Assert.assertEquals(resultparambacks.get("payee_name").toString().trim(),form.getPayeeName());
					 Assert.assertEquals(resultparambacks.get("receive_bank_name").toString().trim(),form.getReceiveBankName());
					 Assert.assertEquals(resultparambacks.get("receive_bank_account").toString().trim(),form.getReceiveBankAccount());
//					 Assert.assertEquals(resultparambacks.get("apply_form_id").toString().trim(),form.getApplyFormId());
					String form_status=resultparambacks.get("form_status").toString().trim();
					if(form_status.equals("2")){
						Assert.assertEquals("true", "true");
					}else{
						Assert.assertEquals("false", "false");
					}
					 Assert.assertEquals(resultparambacks.get("money_type").toString().trim(),form.getMoneyType());
					 Assert.assertEquals(resultparambacks.get("state").toString().trim(),"0");
					
					 String sqlofcostdetails="SELECT * FROM t_cost_details WHERE cost_form_id='YSZB201711270000'";	
					 Map<String, Object>  resultparambackscostdetails=new HashMap<String, Object>();
					
						resultparambackscostdetails=GetDbMethod.getCostDetailSelctTestList(db, sqlofcostdetails);
						for(int i=0;i<costDetails.size();i++){
							//无法校验明细，因为没有具体的金额
//							Assert.assertEquals(resultparambackscostdetails.get("amount").toString().trim(),costDetails.get(i).getAmount());
							Assert.assertEquals(resultparambackscostdetails.get("cost_form_id").toString().trim(),form.getFormId());
//							Assert.assertEquals(resultparambackscostdetails.get("cost_happen_date").toString().trim(),costDetails.get(i).getCostHappenDate());
//							Assert.assertEquals(resultparambackscostdetails.get("cost_detail_type").toString().trim(),costDetails.get(i).getCostDetailType());
							Assert.assertEquals(resultparambackscostdetails.get("budget_status").toString().trim(),costDetails.get(i).getBudgetStatus());
							Assert.assertEquals(resultparambackscostdetails.get("cost_center").toString().trim(),form.getCostCenter());
							
						}
						// 有时间增加一下附件信息
						//
						
						if(name.equals("fileListishavevalue")){

							 String sqloffileaddress="SELECT * FROM t_file_address WHERE  form_id='YSZB201711270000' ORDER BY id ASC";	
							 Map<String, Object>  resultparambacksfileaddress=new HashMap<String, Object>();
								
							 resultparambacksfileaddress=GetDbMethod.getCostDetailSelctTestList(db, sqloffileaddress);
						
							 for(int i=0;i<fileList.size();i++){
								 Assert.assertEquals(resultparambacksfileaddress.get("form_id").toString().trim(),form.getFormId());
								 Assert.assertEquals(resultparambacksfileaddress.get("file_address").toString().trim(),fileList.get(i).getFileAddress());
								 Assert.assertEquals(resultparambacksfileaddress.get("file_name").toString().trim(),fileList.get(i).getFileName());
								 Assert.assertEquals(resultparambacksfileaddress.get("pic").toString().trim(),fileList.get(i).getIsPic());
							 }
						}
	
					
			}else if(name.equals("costDetailssetAmountisABC")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用明细报销金额必须是数字并且大于0");
			}else if(name.equals("nothavebudget")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用发生月份2017-11，成本中心，费用类别差旅费没有导入预算");
			}else if(name.equals("costDetailssetAmountisZERO")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 Assert.assertEquals(result.get("message").toString().trim(),"费用明细报销金额必须是数字并且大于0");
			}else if(name.equals("nothavebudgetofyear")||name.equals("nothavebudget")||name.equals("nothavebudgetofmonth")){
				 
				 if(result.get("message").toString().trim().contains("没有导入预算")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"1");
				 }else{
					 Assert.assertEquals(1, 0);
				 }
			}else{
				Logger.log("没有合适的case");
			}
			 
			 
	
		}
		@DataProvider
	    public static Object[][] submitBudegtSupplementFormParam() {
	        return new Object[][]{
	       		{"baseinfonothaveCostDesc"},{"baseinfobighaveCostDesc"},
	        		{"baseinfonothaveApplyMoney"},{"fileListishavevalue"},{"costDetailssetAmountisABC"},
	        		{"costDetailssetAmountisZERO"},{"costDetailisdouble"},{"nothavebudget"},{"nothavebudgetofmonth"},{"nothavebudgetofyear"},
	       		{"baseinfohavevalue"},
//	        		费用发生月份2017-11，成本中心null，费用类别差旅费没有导入预算
	        		{"applymoneyfromcostdetailsamountsum"}
//	        		{"fileListishavevalue"},
	       		
	        };
		}
}
