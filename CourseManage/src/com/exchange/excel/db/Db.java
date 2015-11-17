/**
 * 
 */
package com.exchange.excel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exchange.common.Common;
import com.exchange.excel.ca.Course;


public class Db {

	
	public static void insert(String sql, Course course) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USERbb, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getaa());
			ps.setString(2, course.getbb());
			ps.setString(3, course.getcc());
			ps.setString(4, course.getdd());
			ps.setString(5, course.geta());
			ps.setString(6, course.getb());
			ps.setString(7, course.getc());
			ps.setString(8, course.getd());
			ps.setString(9, course.gete());
			ps.setString(10, course.getf());
			ps.setString(11, course.getg());
			ps.setString(12, course.geth());
		
			boolean flag = ps.execute();
			if(!flag){
				System.out.println("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List selectOne(String sql, Course course) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USERbb, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("aa").equals(course.getaa()) || rs.getString("bb").equals(course.getbb())|| rs.getString("cc").equals(course.getcc())|| rs.getString("a").equals(course.geta())|| rs.getString("b").equals(course.getb())){
					list.add(1);
				}else{
					list.add(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}
	
	
	public static ResultSet selectAll(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(Common.DRIVER);
			conn = DriverManager.getConnection(Common.URL, Common.USERbb, Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return rs;
	}

}
