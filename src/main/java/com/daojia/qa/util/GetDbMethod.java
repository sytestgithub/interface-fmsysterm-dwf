package com.daojia.qa.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.http.HttpRequest;
import com.bj58.daojia.qa.util.Logger;

public class GetDbMethod {
	
public static int deleteBySelectCondition(DBUtil db,String sql, Object... params){

	int num=0;
	num=db.update(sql, params);
	if(num>0){
		
	}else{}
	return num;
}

public static int updateSelectCondition(DBUtil db,String sql, Object... params){

	int num=0;
	num=db.update(sql, params);
	if(num>0){
		
	}else{}
	return num;
}


public static Map getCostDetailSelctTestList(DBUtil db, String sql,List<String> params){
	Map<String, Object> dbResult = db.queryForMap(sql);
	   Map list= new HashMap();
	if (dbResult!=null){
if(params.size()>0){
	for(String paramlist:params){
		list.put(paramlist, dbResult.get(paramlist));
		Logger.log("获取当前参数"+paramlist+"的值"+ list.get(paramlist));
	}
	
}
	 }else{
	   	Logger.log("数据库中没有值，请插入一条数据，谢谢！！");			      
	   }
		return list;
}
	   


public static Map getCostDetailSelctTestList(DBUtil db, String sql,String params){
	Map<String, Object> dbResult = db.queryForMap(sql);
	   Map list= new HashMap();
	if (dbResult!=null){
		list.put(params, dbResult.get(params));
	
   }else{
   	Logger.log("数据库中没有值，请插入一条数据，谢谢！！");			      
   }
	return list;
}

public static Map<String, Object> getCostDetailSelctTestList(DBUtil db, String sql){
	Map<String, Object> dbResult = db.queryForMap(sql);
	
	if (dbResult!=null){
		return dbResult;
	
   }else{
	   
   	Logger.log("数据库中没有值，请插入一条数据，谢谢！！");	
   	return dbResult;
   }
	
}

public static int getCostDetailSelctTestListSize(DBUtil db, String sql){
	List<Map<String, Object>> dbResult = db.queryForMapList(sql);
	int size=0;
	if (dbResult!=null){
		size=dbResult.size();
		return size;
	
   }else{
	   
   	Logger.log("数据库中没有值，请插入一条数据，谢谢！！");	
   	return size;
   }
	
}

public static List<Map<String, Object>> getCostDetailSelctTestListSize1(DBUtil db, String sql){
	List<Map<String, Object>> dbResult = db.queryForMapList(sql);
	
	if (dbResult!=null){
		
		return dbResult;
	
   }else{
	   
   	Logger.log("数据库中没有值，请插入一条数据，谢谢！！");	
   	return dbResult;
   }
	
}



public static String getCostDetailSelctTest(DBUtil db, String sql,String params){
	Map<String, Object> dbResult = db.queryForMap(sql);
	   String list= "";
	if (dbResult!=null){
		list = dbResult.get(params).toString();
		return list;
	
   }else{
   	Logger.log("数据库中没有值，请插入一条数据，谢谢！！");			 
	return list;
   }

}


public static int getDeleteDeatailTest(DBUtil db, String sql,String params){
	  int num=0;
	   num=db.update(sql, params);
	  if(num>0){
		  Logger.log("需要删除的单据数："+num);
		  return num;
	  }else{
		  Logger.log("没有需要删除的单据数！");
		  return 0;  
	  }
	
}
public static int getDeleteDeatailTest(DBUtil db, String sql){
	  int num=0;
	   num=db.update(sql);
	  if(num>0){
		  Logger.log("需要删除的单据数："+num);
		  return num;
		 
	  }else{
		  Logger.log("没有需要删除的单据数！");
		  return num;  
	  }
	
}

public static void deleteFormTestData(DBUtil db,String name){
	
String urlresponse="http://fmsystem205.djtest.cn/v2/recall";
HashMap<String, String> param=new  HashMap<String, String>();
param.put("formId",name);
JSONObject  response=HttpRequest.doPostReturnResponseJson(urlresponse,param,GetHead.gethead());
if(StringUtils.isNotEmpty(response.get("repCode").toString()) ){
System.out.println(response.get("repCode"));
Logger.log("进入财务删除阶段");
 String form="DELETE FROM t_form WHERE form_id='"+name+"'";
String costdetail="DELETE FROM t_cost_details WHERE cost_form_id='"+name+"'";

String flowinstance="DELETE FROM t_flow_instance WHERE form_id='"+name+"'";

String flowdata=" DELETE FROM t_flow_data WHERE form_num='"+name+"'";
String costoff="DELETE FROM t_cost_offset WHERE cost_form_id='"+name+"'";
String filladdress="DELETE FROM t_file_address WHERE form_id='"+name+"'";
GetDbMethod.getDeleteDeatailTest(db, form);
GetDbMethod.getDeleteDeatailTest(db, costdetail);
GetDbMethod.getDeleteDeatailTest(db, flowinstance);
GetDbMethod.getDeleteDeatailTest(db, flowdata);
GetDbMethod.getDeleteDeatailTest(db, costoff);
GetDbMethod.getDeleteDeatailTest(db, filladdress);
}else{

Logger.log("流程平台返回空，请查看流程平台是否调用成功！！！");
}

}

public static void endProcess(DBUtil db,String name){
	
String urlresponse="http://fmsystem205.djtest.cn/v2/recall";
HashMap<String, String> param=new  HashMap<String, String>();
param.put("formId",name);
JSONObject  response=HttpRequest.doPostReturnResponseJson(urlresponse,param,GetHead.gethead());
if(StringUtils.isNotEmpty(response.get("repCode").toString()) ){
System.out.println(response.get("repCode"));
Logger.log("删除成功");
}else{

Logger.log("流程平台返回空，请查看流程平台是否调用成功！！！");
}

}

public static void onlyDeleteFormid(DBUtil db,String name){

Logger.log("进入财务删除阶段");
 String form="DELETE FROM t_form WHERE form_id='"+name+"'";
String costdetail="DELETE FROM t_cost_details WHERE cost_form_id='"+name+"'";

String flowinstance="DELETE FROM t_flow_instance WHERE form_id='"+name+"'";

String flowdata=" DELETE FROM t_flow_data WHERE form_num='"+name+"'";
String costoff="DELETE FROM t_cost_offset WHERE cost_form_id='"+name+"'";
String filladdress="DELETE FROM t_file_address WHERE form_id='"+name+"'";
GetDbMethod.getDeleteDeatailTest(db, form);
GetDbMethod.getDeleteDeatailTest(db, costdetail);
GetDbMethod.getDeleteDeatailTest(db, flowinstance);
GetDbMethod.getDeleteDeatailTest(db, flowdata);
GetDbMethod.getDeleteDeatailTest(db, costoff);
GetDbMethod.getDeleteDeatailTest(db, filladdress);

}

public static void endProcess(String formid){
	
String urlresponse="http://processmanager.djtest.cn/processDynTemp/endprocess";
String param="busiId="+formid;

JSONObject  response=HttpRequest.doGetReturnResponseJson(urlresponse,param,GetHead.gethead());
if(StringUtils.isNotEmpty(response.get("code").toString()) ){
System.out.println(response.get("code"));

}else{

Logger.log("流程平台返回空，请查看流程平台是否调用成功！！！");
}

}

}
