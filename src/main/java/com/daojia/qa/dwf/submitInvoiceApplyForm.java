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
import com.daojia.qa.entity.Form;
import com.daojia.qa.entity.InvoiceApplyDetail;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;

public class submitInvoiceApplyForm extends BaseTest<T>{
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
	    * 提交发票申请单接口
	    * */
	@Test(dataProvider = "submitInvoiceApplyFormParam", description="保存和提交FPSQ接口")
	public void submitInvoiceApplyForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		String sqlformid="";
		String formOption="submit";
		Form form =new Form();
		form.setFormId("FPSQ201803281142");
		form.setOppositeContractBody("材料接收方");//材料接收方
		form.setInvoiceType("1");//发票类型（数字）
		form.setBusinessCategory("保姆");//业务类别
		form.setOurContractBody("北京五八到家信息技术有限公司");//我方签约主体
		form.setInvoiceContent("运输费");//开票内容(文字）
		form.setIsRepayment(1);//是否已收款
		if(name.equals("repaymentisyes")){
			form.setPlanRepaymentDate("2019-01-01");//预计还款时间
		}else if(name.equals("repaymentisno")){
			
		}
		
		form.setTaxRate("3%");//税率
		form.setApplyMoney("4");//订单金额
		form.setDepositOtherAmount(new BigDecimal("1"));//折扣
		form.setInvoiceAmount("1");//计算出来  订单金额-折扣
		form.setCostDesc("申请说明");//开票备注
		form.setInnerRemark("备注信息");//内部备注
		GetDbMethod.endProcess(form.getFormId()); // 结束流程
		 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
		 UploadFileDto file=new UploadFileDto();
		   List<InvoiceApplyDetail> invoiceApplyDetails =new ArrayList<InvoiceApplyDetail>();
		   InvoiceApplyDetail invoiceApplyDetail=new InvoiceApplyDetail();
		   //合同信息
		   invoiceApplyDetail.setContractNum("SRHT201803280001");//
		   invoiceApplyDetail.setOppositeContractBody("天津五八到家信息技术有限公司");//对方签约主体
		   invoiceApplyDetail.setUniformCode("0001R");//社会统一代码
		   invoiceApplyDetail.setIsFrameContract(0);//是否框架合同 0 不是 1 是
		   //开票信息
		   invoiceApplyDetail.setInvoiceAddress("开票地址2");//开票地址
		   invoiceApplyDetail.setInvoiceTel("1233322");//开票单位电话
		   invoiceApplyDetail.setRecipientBankFullName("浦发全称2");//对方开户行全称
		   invoiceApplyDetail.setRecipientBankAccount("200033322");//对方银行账号
		   invoiceApplyDetail.setInvoiceDateStart("2018-03-27");//开票期间开始
		   invoiceApplyDetail.setInvoiceDateEnd("2018-04-01");//开票期间结束
		   //发票寄送信息
		   invoiceApplyDetail.setReceiptName("收件人2");//收件人
		   invoiceApplyDetail.setReceiptAddress("收件地址2");//收件地址
		   invoiceApplyDetail.setReceiptTel("11112221");//收件电话
		   invoiceApplyDetail.setInvoiceHolder("领票人2");//领票人
		   
		   invoiceApplyDetails.add(invoiceApplyDetail);
		   
		    file.setFileAddress("http://www.baidu.com");
			file.setFileName("filename1");
			file.setIsPic("0");
			fileList.add(file);
			
			
			 
			HashMap<String, String> param=new  HashMap<String, String>();
			  param.put("baseForm",JSONObject.toJSONString(form));
			  param.put("formOption",formOption );
			  param.put("invoiceDetails",JSONObject.toJSONString(invoiceApplyDetails));

			  param.put("fileList", JSONObject.toJSONString(fileList));
			  param.put("formType","FPSQ");

				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
				 Logger.log("获取repData>>>>>>>>"+result);
				 if(name.equals("nomalvalue")||name.equals("repaymentisyes")||name.equals("repaymentisno")){
				 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
				 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
				 }
	}
	@DataProvider
    public static Object[][] submitInvoiceApplyFormParam() {
        return new Object[][]{ 
//        		{"nomalvalue"},{"repaymentisyes"},{"repaymentisno"},
//        		{"rePeriodContract"},{"rePeriodContractVoid"},{"rePeriodContractRedCoplete"},
        		
        		{"repaymentisno"}
        };
        }
}
