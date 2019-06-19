package com.daojia.qa.dwf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bj58.daojia.qa.base.BaseTest;
import com.bj58.daojia.qa.database.DBUtil;
import com.bj58.daojia.qa.util.Logger;
import com.daojia.qa.entity.Form;
import com.daojia.qa.util.GetDbMethod;
import com.mysql.fabric.xmlrpc.base.Array;

public class travelForm extends BaseTest<T>{
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
	
	@Test(description="差旅报销")
	public void getTraveFormForSuper(){
   String getsqloftravelprocess="SELECT form_id FROM t_form WHERE form_type=1 AND form_status IN (9) AND form_id  IN(SELECT apply_form_id FROM t_form WHERE form_type=2 AND form_status=2);";
   String getsqloftravelall="SELECT form_id FROM t_form WHERE form_type=1 AND form_status IN (9)";
   String getsqloftravelprocessandend="SELECT form_id FROM t_form WHERE form_type=1 AND form_status IN (9) AND form_id  IN(SELECT apply_form_id FROM t_form WHERE form_type=2 AND form_status IN(2,9))";
   List<Map<String, Object>>  list= db.queryForMapList(getsqloftravelall);
   List<Map<String, Object>>  list1= db.queryForMapList(getsqloftravelprocessandend);
   int sum=0;
   int sum1=0;
   List <Map<String, Object>> getsqloftravelnothave=new ArrayList<Map<String,Object>>(); 
   Map<String, Object> map1=new HashMap<String, Object>();

	 for(int j=0;j<list1.size();j++){
	  	   String proendformid=list1.get(j).get("form_id").toString();
	  	 map1.put("form_id", proendformid);
	  	getsqloftravelnothave.add(map1);
	  	   sum1++;
	     }
 
		for(int i=0;i<list.size();i++){
			   String allformid=list.get(i).get("form_id").toString();
			   sum++;
			   if(!allformid.equals(getsqloftravelnothave.get(i).get("form_id"))){
				   
			   }
			  
		}
	 
	      Logger.log("测试总数--》》》"+  sum);
	      Logger.log("审批中和已经完成的单据--》》》"+  sum1);
	      Logger.log("非关联的申请单--》》》"+getsqloftravelnothave.size());
	}
	
	
}
