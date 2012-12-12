package cn.insurance.dao.conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;


/**
 * ��ݿ�l�ӹ�����
 * @author shitao
 *
 */
public final class ConnectionManager
{
	
	/** ���Դ */
	public static DataSource dataSource = null;
	
		
	/**
	 * ��l�ӳ���ȡ��һ��l��
	 * @return
	 */
	public static synchronized Connection getConnection()
	{		
		/*
		 * ��Spring4ȡ��l�ӣ���֧������ʽ���� 
		 */
		return DataSourceUtils.getConnection(dataSource);				
	}		


	/**
	 * �ͷ���ݲ�����Դ
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement st,Connection conn)
	{
		try
		{
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	/**
	 * ��ȡ���Դ
	 * @return
	 */
	public DataSource getDataSource()
	{
		return dataSource;
	}

	/**
	 * �������Դ
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	
	
	
}
