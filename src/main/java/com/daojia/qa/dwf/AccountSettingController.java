package com.daojia.qa.dwf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.daojia.qa.entity.CostReceiptsConfig;
import com.daojia.qa.entity.CostSubject;
import com.daojia.qa.util.GetCookiesTest;
import com.daojia.qa.util.GetHead;
import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;

public class AccountSettingController {

	String method_url="";
	@Test(description="费用科目")
public void saveCostSubjects(){
		method_url="http://fmsystem205.djtest.cn/saveCostSubjects";
		  HashMap<String, String> param=new  HashMap<String, String>();
		  CostSubject costsubject=new CostSubject();
		  CostReceiptsConfig costReceiptsConfig=new CostReceiptsConfig();
		  costReceiptsConfig.setCostMajorId("010");
		  costReceiptsConfig.setId(new Long(1));
		  costReceiptsConfig.setIsVaild(1);
		  costReceiptsConfig.setReceiptsMajorId("1");
		  List<CostReceiptsConfig> costReceiptsConfigList=new ArrayList<CostReceiptsConfig>();
		  costReceiptsConfigList.add(costReceiptsConfig);
		  Long setid=new Long(00101);
		  costsubject.setId(setid);
		  costsubject.setMajorId("001");
		  costsubject.setCostCode("010");
		  costsubject.setPid("001");
		  costsubject.setCostDetailType("差旅申请");
		  costsubject.setCostReceiptsConfigList(costReceiptsConfigList);
		  List<String> checkedNodes=new ArrayList<String>();
		  checkedNodes.add("122");
		  param.put("add", JSONObject.toJSONString(costsubject));
		  param.put("checkedNodes", JSONObject.toJSONString(checkedNodes));
		  JSONObject result= HttpRequest.doPostReturnResponseJson(method_url,param,GetHead.gethead());
			Logger.log("获取结果---->>>>"+result);
	}
}
