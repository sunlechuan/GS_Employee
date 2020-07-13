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
	 * ͨ������Դ���ӳػ�ȡ����һ������web��Ŀ�е�c3p0.xml
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
	//ͨ��DataSource��ȡ����
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
	
	//��������
		/*
		 * select @@autocommit;
		 * set @@autocommit = 1;
		 * commit;	
		 * */
		public static void startTransaction() {
			Connection con = getConnection();
			try {
				con.setAutoCommit(false);//false������ǲ��Զ��ύ����true�����Զ��ύ���� ���ʱ�������Լ��ֶ��ύ����
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//�ύ����
		public static void commit() {
			Connection con = getConnection();
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//�ع�����
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
		//�ر���Դ  
		//�ر�connection ����ͬʱ�Ƴ��̳߳��е�connection
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
		//�ر�preparedstatement
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
		//�ر�resultSet
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
		//����
		public static void main(String[] args) {
			System.out.println(getConnection());
		}

}
