package com.employee.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	//ת������
	public static Date toDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return new Date(sdf.parse(date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//��ȡʱ��
	public static Date getNow(){
		java.util.Date now = new java.util.Date();
		return new Date(now.getTime());
	}
}
