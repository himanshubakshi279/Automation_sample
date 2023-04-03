package com.Automation.framework.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private String fileName = "./TestData/TestData.xlsx";
	private FileInputStream fis;
	private XSSFWorkbook wbook;
	private XSSFSheet sheet;

	public ExcelUtil() {
	}

	public ExcelUtil open() {
		try {
			fis = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
			saveAndClose();
		}
		return this;
	}

	public ExcelUtil navigateToSheet(String sheetName) {
		sheet = wbook.getSheet(sheetName);
		return this;
	}
	
	public void clearExcelFile(int rowNumber) {
	    Row row = sheet.getRow(rowNumber);
	    if (row != null) {
	        for (Cell cell : row) {
	            cell.setCellValue("");
	        }
	    }
	}


	public ExcelUtil writeToSheet(int rowNum, String[] cellValues) {
		DataFormatter formatter = new DataFormatter();
		Iterator<Row> rowIterator = sheet.iterator();
		
		Row row = null;
		for (int i = 0; i < rowNum; i++) {
			if(rowIterator.hasNext()) {
				row = rowIterator.next();
			}else {
				row = sheet.createRow(i);
			}
		}
		
		for(int i=0;i<cellValues.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(cellValues[i]);
		}
		return this;
	}
	
	
	public Map<String, List<String>> readFromSheet() {
		Map<String, List<String>> values = new HashMap<String, List<String>>();
		Iterator<Row> rowIterator = sheet.iterator();

		// For headers
		Row headerRow = rowIterator.next();
		int columnSize = headerRow.getLastCellNum();

		for (int i = 0; i < columnSize; i++) {
			String key = sheet.getRow(0).getCell(i).getStringCellValue();
			List<String> valueList = new ArrayList<String>();

			int j = 1;
			boolean flag = true;
			while (flag) {
				try {
					valueList.add(sheet.getRow(j++).getCell(i).getStringCellValue());
				} catch (Exception e) {
					flag = false;
				}
			}

			values.put(key, valueList);
		}
		return values;
	}

	public void saveAndClose() {
		FileOutputStream fisOut = null;
		try {
			fisOut = new FileOutputStream(fileName);
			wbook.write(fisOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fisOut != null) {
					fisOut.close();
				}
				if (fis != null) {
					fis.close();
				}
				if (wbook != null) {
					wbook.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
