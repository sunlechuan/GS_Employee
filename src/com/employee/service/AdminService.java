package com.employee.service;

import com.employee.po.Admin;

public interface AdminService {
public Admin login(String username,String password);

public boolean register(Admin admin);
public Admin getByName(String username);

}
