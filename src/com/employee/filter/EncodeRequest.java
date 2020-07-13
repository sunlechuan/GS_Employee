package com.employee.filter;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeRequest extends HttpServletRequestWrapper{

	HttpServletRequest request = null;
	public EncodeRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request = request;
	}
	//重写父类方法 设置编码
	@Override
	public String getParameter(String name){
		String val = request.getParameter(name);
		if(val!=null){
		try {
			val = new String(val.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}else{
			val="";
		}
		return val;
	}
	
}
