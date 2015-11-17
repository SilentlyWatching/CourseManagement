/**
 * 
 */
package com.exchange.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.exchange.common.Common;
import com.exchange.excel.ca.Course;


public class ReadExcel {

	public List<Course> readXls() throws IOException {
		InputStream is = new FileInputStream(Common.EXCEL_PATH);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Course course = null;
		List<Course> list = new ArrayList<Course>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 3; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					course = new Course();
					HSSFCell aa = hssfRow.getCell(0);
					HSSFCell bb = hssfRow.getCell(1);
					HSSFCell cc = hssfRow.getCell(2);
					HSSFCell dd = hssfRow.getCell(3);
					HSSFCell a = hssfRow.getCell(4);
					HSSFCell b = hssfRow.getCell(5);
					HSSFCell c = hssfRow.getCell(6);
					HSSFCell d = hssfRow.getCell(7);
					HSSFCell e = hssfRow.getCell(8);
					HSSFCell f = hssfRow.getCell(9);
					HSSFCell g = hssfRow.getCell(10);
					HSSFCell h = hssfRow.getCell(11);
					course.setaa(getValue(aa));
					course.setbb(getValue(bb));
					course.setcc(getValue(cc));
					course.setdd(getValue(dd));
					course.seta(getValue(a));
					course.setb(getValue(b));
					course.setc(getValue(c));
					course.setd(getValue(d));
					course.sete(getValue(e));
					course.setf(getValue(f));
					course.setg(getValue(g));
					course.seth(getValue(h));
					list.add(course);
				}
			}
		}
		return list;
	}
	
	 @SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            // 返回数值类型的值
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            // 返回字符串类型的值
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
