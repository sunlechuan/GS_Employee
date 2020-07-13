package com.employee.service;

import com.employee.dao.AdminDao;
import com.employee.po.Admin;

public class AdminServiceIMPL implements AdminService{
 AdminDao adminDao=new AdminDao();
	@Override
	public Admin login(String username, String password) {
		// TODO Auto-generated method stub
		String sql="select * from t_user where username=? and password=?";
		Object[] params={username,password};
		return adminDao.get(sql, Admin.class, params);
	}
	@Override
	public boolean register(Admin admin) {
		// TODO Auto-generated method stub
		String sql="insert into t_user(username,password) values(?,?)";
		Object[] params={admin.getUsername(),admin.getPassword()};
		return adminDao.update(sql, params);
	}
	@Override
	public Admin getByName(String username) {
		// TODO Auto-generated method stub
		String sql="select username from t_user where username=?";
		Object[] params={username};
		
return adminDao.get(sql, Admin.class, params);
	}

}
