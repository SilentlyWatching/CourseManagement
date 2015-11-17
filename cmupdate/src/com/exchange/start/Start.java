/**
 * 
 */
package com.exchange.start;

import java.io.IOException;
import java.sql.SQLException;

import com.exchange.excel.Save;


public class Start {

	public static void main(String[] args) throws IOException, SQLException {
		Save saveData2DB = new Save();
		saveData2DB.save();
		System.out.println("finished");
	}
}
