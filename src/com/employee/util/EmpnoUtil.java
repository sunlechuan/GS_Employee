package com.employee.util;

public class EmpnoUtil {

	public static  String createEmpno(String empno){
		if(empno.charAt(0)=='0' && empno.charAt(1)=='0'){
			int no = Integer.parseInt(empno.substring(2))+1;
			if(no<10){
				return "00"+no;
			}else{
				return "0"+no;
			}
		}else{
			//ֻ�е�һλΪ0
			if(empno.charAt(0)=='0'){
				int no = Integer.parseInt(empno.substring(1))+1;
				if(no<100){
					return "0"+no;
				}else{
					return ""+no;
				}
			}else{ //��һλ��Ϊ0
				int no =Integer.parseInt(empno)+1;
				return ""+no;
			}
		}
	}
}
