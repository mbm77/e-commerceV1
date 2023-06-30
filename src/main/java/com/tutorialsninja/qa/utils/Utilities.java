package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	public static final int IMPLICIT_WAIT_TIME = 20;
	public static final int PAGE_LOAD_TIME_OUT = 20;

	public static String generateEmailWithTimestamp() {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd_H_m_s").format(new Date());
		String timestampEmail = "mbm"+timestamp+"@gmail.com";
		return timestampEmail;
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excelFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		
		try {
			fis = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	

	XSSFSheet sheet = workbook.getSheet(sheetName);
	int rows = sheet.getLastRowNum();
	int cols = sheet.getRow(0).getLastCellNum();

	Object[][] data = new Object[rows][cols];
	
	for(int i = 0;i<rows;i++){
		XSSFRow row = sheet.getRow(i + 1);
		for (int j = 0; j < cols; j++) {
			XSSFCell cell = row.getCell(j);
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case STRING:
				data[i][j] = cell.getStringCellValue();
				break;
			case NUMERIC:
				data[i][j] = Integer.toString((int) cell.getNumericCellValue());
				break;
			case BOOLEAN:
				data[i][j] = cell.getBooleanCellValue();
				break;

			}
		}
	}
//		workbook.close();
//		fis.close();
		return data;

	}

}
