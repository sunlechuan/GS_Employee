package com.employee.service;

import java.util.List;

import com.employee.dao.HistoryDao;
import com.employee.po.Employee;
import com.employee.po.History;

public class IHistoryServiceIMPL implements HistoryService{
   HistoryDao hdao=new HistoryDao();
	@Override
	public List<History> findByKey(String key,int page,int size) {
		// TODO Auto-generated method stub
		page=(page-1)*size;
		String sql="select h.changeno,h.empno,empname,h.deptno,deptname,e.salary,"
			+" changedate,changereason,dismissiondate,dismissionreason from t_history h,t_emp e,t_dept d "
			+" where e.empno = h.empno and d.deptno= h.deptno and concat(h.empno,empname,deptname) like ? order by changeno desc limit ?,? ";
		Object[] params={"%"+key+"%",page,size};
		return hdao.query(sql, History.class, params);
	}

	@Override
	public int pageCount(int size) {
		// TODO Auto-generated method stub
		String sql="select count(*) count from t_history ";
		Object[] params={};
		int hang=0;
		int pageCount=0;
		History history=hdao.get(sql, History.class, params);
		hang=history.getCount();
		if(hang%size==0){
			pageCount=hang/size;
		}else{
			pageCount=hang/size+1;
		}
		return pageCount;
	}

	@Override
	public List<History> list(String key) {
		// TODO Auto-generated method stub
		String sql="select h.changeno,h.empno,empname,h.deptno,deptname,e.salary,"
				+" changedate,changereason,dismissiondate,dismissionreason from t_history h,t_emp e,t_dept d "
				+" where e.empno = h.empno and d.deptno= h.deptno and concat(h.empno,empname,deptname) like ?  ";
			Object[] params={"%"+key+"%"};
			return hdao.query(sql, History.class, params);
	
	}
}
