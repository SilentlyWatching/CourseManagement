/**
 * 
 */
package com.exchange.excel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.exchange.common.Common;
import com.exchange.excel.ca.Course;
import com.exchange.excel.db.Db;


public class Save {

	@SuppressWarnings({ "rawtypes" })
	public void save() throws IOException, SQLException {
		ReadExcel xlsMain = new ReadExcel();
		Course course = null;
		List<Course> list = xlsMain.readXls();

		for (int i = 0; i < list.size(); i++) {
			course = list.get(i);
			List l = Db.selectOne(Common.SELECT_STUDENT_SQL + "'%" + course.getbb() + "%'", course);
			
				Db.insert(Common.INSERT_STUDENT_SQL, course);
			
		}
	}
}
