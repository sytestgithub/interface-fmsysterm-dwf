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
import com.daojia.qa.entity.StampApplyDetail;
import com.daojia.qa.entity.UploadFileDto;
import com.daojia.qa.util.GetDbMethod;
import com.daojia.qa.util.GetHead;
import com.daojia.qa.util.getUtilMethod;

public class submitStampForm  extends BaseTest<T>{
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
	    * 提交印证申请单接口
	    * */
	@Test(dataProvider = "submitStampFormParam", description="保存和提交YZSQ接口")
	public void submitStampForm1(String name){
		method_url="http://fmsystem205.djtest.cn/v2/formsubmit";
		String sqlformid="";
		String formOption="submit";
		Form form =new Form();
		form.setFormId("YZSQ201803281001");
		form.setOppositeContractBody("材料接收方");//材料接收方
		form.setMattersType("1");;//办理事项（数字）
		form.setMattersTypeName("员工个人事务");
		form.setCostDesc("申请说明");//申请说明
		form.setInnerRemark("备注信息");//内部备注
		GetDbMethod.endProcess(form.getFormId()); // 结束流程
		 List<UploadFileDto> fileList=new ArrayList<UploadFileDto>();
		 UploadFileDto file=new UploadFileDto();
		   List<StampApplyDetail> stampDetails =new ArrayList<StampApplyDetail>();
		 StampApplyDetail stampDetail=new StampApplyDetail();
		 stampDetail.setSort(1);
		 stampDetail.setApplyType("1");
		 stampDetail.setApplyTypeName("盖章");
		 stampDetail.setCompanyName("北京五八到家信息技术有限公司");
		 stampDetail.setStampNameId("Z001");//印证id
		 stampDetail.setStampName("测试数据");
		 stampDetail.setStampMaterialName("盖章材料名称");
		 stampDetail.setAmount(new BigDecimal(1));
		 stampDetail.setNum(1);
		 stampDetail.setReturnDate("2019-04-20");
		 stampDetails.add(stampDetail);
		    file.setFileAddress("http://www.baidu.com");
			file.setFileName("filename1");
			file.setIsPic("0");
			fileList.add(file);
			 
			HashMap<String, String> param=new  HashMap<String, String>();
			  param.put("baseForm",JSONObject.toJSONString(form));
			  param.put("formOption",formOption );
			  param.put("stampDetails",JSONObject.toJSONString(stampDetails));

			  param.put("fileList", JSONObject.toJSONString(fileList));
			  param.put("formType","YZSQ");

				 JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			
				 Logger.log("获取repData>>>>>>>>"+result);
				 if(name.equals("nomalvalue")){
					 Assert.assertEquals(result.get("repCode").toString().trim(),"0");
					 Assert.assertEquals(result.get("message").toString().trim(),"提交成功");
				 }else if(name.equals("")){
					 
				 }else{
					 
				 }
	}
	@DataProvider
    public static Object[][] submitStampFormParam() {
        return new Object[][]{ 
        		{"nomalvalue"},{""}
        		
        };
        }
}
