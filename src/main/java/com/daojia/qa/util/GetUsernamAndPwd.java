package com.daojia.qa.util;

import java.awt.List;
import java.util.ArrayList;

public class GetUsernamAndPwd {
public static ArrayList<String> getUserAndPwd(String userId,String userName,String password,String optionName){
	ArrayList<String> list=new ArrayList<String>();
	  if(userId.equals("王宏业")){
			userName="wanghongye";
			password="`12qweasd";
		
		}else if(userId.equals("尚英")){
			userName="shangying";
			password="sy58haitao!!";
			
		}else if(userId.equals("王桐")){
			userName="wangtong";
			password="`1234qwer";
		
		}else if(userId.equals("姜满")){
			userName="jiangman";
			password="qwer@1234";
		
		}else if(userId.equals("杨建")){
			userName="yangjian";
			password="aiw9276!";
		
		}else if(userId.equals("樊晶凯")){
			userName="fanjingkai";
			password="`123qwer";
			
			
		}else{
			userName="shangying";
			password="sy58haitao!!";
			
		}
	  optionName=optionName+userName+"审批完毕";
	  list.add(userName);
	  list.add(password);
	  list.add(optionName);
	  return list;
}
}
