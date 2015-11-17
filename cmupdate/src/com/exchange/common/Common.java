/**
 * 
 */
package com.exchange.common;


public class Common {

	// connect the database
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_bb = "test";
	public static final String USERbb = "root";
	public static final String PASSWORD = "123456";
	public static final String IP = "localhost";
	public static final String PORT = "3306";
	public static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DB_bb;
	
	
	public static final String EXCEL_PATH = "lib/¼ÆËã»ú1.xls";
	
	// sql
	public static final String INSERT_STUDENT_SQL = "insert into course_info(aa, bb, cc, dd, a, b, c, d, e, f, g, h) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_STUDENT_SQL = "update course_info set aa = ?, bb = ?, cc= ?, dd = ?,a= ?,b= ?,c= ?,d= ?,e= ?,f= ?,g= ?,h= ? where id = ? ";
	public static final String SELECT_STUDENT_ALL_SQL = "select id,aa,bb,cc,dd,a,b,c,d,e,f,g,h from course_info";
	public static final String SELECT_STUDENT_SQL = "select * from course_info where bb like ";
}
