package com.daojia.qa.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getUtilMethod {
public static String getConvertTwoBigDecimal(String money){
	  DecimalFormat df = new DecimalFormat("0.00");
	  String returnmoney=df.format(Double.parseDouble(money));
	  return returnmoney;
}

public static BigDecimal getBigeDecimal(String money){
	BigDecimal bigdecimal=new BigDecimal(0.00);
	return bigdecimal.valueOf(Double.parseDouble(money));
}



/**
 *字符串的日期格式的计算
 */
public static int daysBetween(String startDate,String endDate) throws ParseException {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	cal.setTime(sdf.parse(startDate));
	long time1 = cal.getTimeInMillis();
	cal.setTime(sdf.parse(endDate));
	long time2 = cal.getTimeInMillis();
	long between_days=(time2-time1)/(1000*3600*24);

	return Integer.parseInt(String.valueOf(between_days));
}


public static Date getdate(){
	Calendar cal=Calendar.getInstance(); 
	Date date=cal.getTime(); 
	return date;
}
}
