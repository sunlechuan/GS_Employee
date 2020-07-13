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
	 * 返回单个对象
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 *        如果没有参数的情况下，就设定数组为空 Object[] params = {}
	 * @return  返回
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
	 * 返回单个对象 带有事务的
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 *        如果没有参数的情况下，就设定数组为空 Object[] params = {}
	 * @return  返回
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
	 * 返回多条数据
	 * @param T
	 * @param sql
	 * @param clazz
	 * @param params 
	 *        如果没有参数就设定为 Object[] params = {}
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
	 * 返回增删改是否成功
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql,Object[] params) {
		Connection con = null;
		//定义一个返回值
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
	 * 返回增删改是否成功 带有事务的
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean update(Connection con,String sql,Object[] params) throws SQLException {
		//定义一个返回值
		boolean flag = false;
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(con,sql, params);
			if(i>0) {
				flag = true;
			}
		return flag;
	}
	/**
	 * 返回批量操作的，需要用到事物
	 * @param con
	 * @param sql
	 * @param params 多行参数用到二元数组
	 * @return
	 * @throws SQLException
	 * 
	 */
	public boolean batchUpdate(Connection con,String sql,Object[][] params) throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		con = JDBCUtils.getConnection();
		int result = 0;//用来接收操作结果的
		boolean flag = false;
		result = qRunner.batch(con, sql, params).length;
		if(result > 0) {
			flag= true;
		}
		return flag;
	}
	/**
	 * 返回统计单值的
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
	 * 返回主键的，通常是执行insert语句时 返回当前的主键
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
