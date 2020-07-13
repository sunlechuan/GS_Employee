package com.employee.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.employee.util.JDBCUtils;
		

public class BaseDao<T> {
	/**
	 * ���ص�������
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 *        ���û�в���������£����趨����Ϊ�� Object[] params = {}
	 * @return  ����
	 */
	public <T> T get(String sql,Class<T> clazz,Object[] params) {
		T obj = null;
		Connection con = null;
		try {
			con = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(con, sql,new BeanHandler<T>(clazz),params);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * ���ص������� ���������
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 *        ���û�в���������£����趨����Ϊ�� Object[] params = {}
	 * @return  ����
	 */
	public <T> T get(Connection con,String sql,Class<T> clazz,Object[] params) {
		T obj = null;
		
		try {
			con = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(con, sql,new BeanHandler<T>(clazz),params);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * ���ض�������
	 * @param T
	 * @param sql
	 * @param clazz
	 * @param params 
	 *        ���û�в������趨Ϊ Object[] params = {}
	 * @return
	 */
	public <T> List<T> query(String sql,Class<T> clazz,Object[] params){
		List beans = null;
		Connection con = null;
		try {
			con = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			beans = (List)qRunner.query(con, sql, new BeanListHandler<T>(clazz), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection();
		}
		return beans;
	}
	/**
	 * ������ɾ���Ƿ�ɹ�
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql,Object[] params) {
		Connection con = null;
		//����һ������ֵ
		boolean flag = false;
		try {
			con = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(con, sql, params);
			if(i>0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * ������ɾ���Ƿ�ɹ� ���������
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean update(Connection con,String sql,Object[] params) throws SQLException {
		//����һ������ֵ
		boolean flag = false;
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(con,sql, params);
			if(i>0) {
				flag = true;
			}
		return flag;
	}
	/**
	 * �������������ģ���Ҫ�õ�����
	 * @param con
	 * @param sql
	 * @param params ���в����õ���Ԫ����
	 * @return
	 * @throws SQLException
	 * 
	 */
	public boolean batchUpdate(Connection con,String sql,Object[][] params) throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		con = JDBCUtils.getConnection();
		int result = 0;//�������ղ��������
		boolean flag = false;
		result = qRunner.batch(con, sql, params).length;
		if(result > 0) {
			flag= true;
		}
		return flag;
	}
	/**
	 * ����ͳ�Ƶ�ֵ��
	 * @param sql
	 * @param params
	 * @return
	 */
	public long getCount(String sql,Object[] params) {
		long count = 0L;
		Connection con = null;
		try {
			con = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			count = (long)qRunner.query(con, sql,new ScalarHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * ���������ģ�ͨ����ִ��insert���ʱ ���ص�ǰ������
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getCurrentKey(String sql,Object[] params) {
		Connection con = null;
		Object key = 0;
		try {
			con = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			key = qRunner.insert(con, sql,new ScalarHandler(1),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	public Object getCurrentKey(Connection con,String sql,Object[] params) throws SQLException {
		Object key = 0;
		
			QueryRunner qRunner = new QueryRunner();
			key = qRunner.insert(con, sql,new ScalarHandler(1),params);
		
		return key;
	}
	

}
