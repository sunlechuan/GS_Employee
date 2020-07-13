package com.employee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	/**
	 * 通过数据源连接池获取对象一般用在web项目中的c3p0.xml
	 * ComboPooledDataSource
	 */
	
	private static ComboPooledDataSource dataSource = null;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	static{
		dataSource = new ComboPooledDataSource();
	}
	public static DataSource getDataSource(){
		return dataSource;
	}
	//通过DataSource获取对象
	public static Connection getConnection(){
		Connection con = tl.get();
		try {
			if(con==null)
				con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tl.set(con);
		return con;
	}
	
	//开启事务
		/*
		 * select @@autocommit;
		 * set @@autocommit = 1;
		 * commit;	
		 * */
		public static void startTransaction() {
			Connection con = getConnection();
			try {
				con.setAutoCommit(false);//false代表的是不自动提交事务，true代表自动提交事务 这个时候我们自己手动提交事务
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//提交事务
		public static void commit() {
			Connection con = getConnection();
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//回滚事务
		/*
		 * update user set name='2555',password='heiheih' where id=26;
		 * roolback
		 * commit
		 * */
		public static void rollback() {
			Connection con = getConnection();
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭资源  
		//关闭connection 并且同时移除线程池中的connection
		public static void closeConnection() {
			close(getConnection());
			tl.remove();
			
		}
		private static void close(Connection connection) {
			// TODO Auto-generated method stub
			if(connection !=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//关闭preparedstatement
		public static void closePst(PreparedStatement pst) {
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//关闭resultSet
		public static void closeRest(ResultSet rest) {
			if(rest !=null) {
				try {
					rest.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//测试
		public static void main(String[] args) {
			System.out.println(getConnection());
		}

}
