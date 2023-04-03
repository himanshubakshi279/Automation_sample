package com.Automation.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Automation.framework.utility.ExcelUtil;
public class GoogleHandling {

	private static final Logger LOG = LogManager.getLogger(GoogleHandling.class);
	
	static String Clumn = null;

	public static void setcolumnName(String Clumn) {
		GoogleHandling.Clumn = Clumn;
	}
	static String ReportFile = null;

	public static void setFileName(String ReportFile) {
		GoogleHandling.ReportFile = ReportFile;
	}
	
	static String VideoName = null;

	public static void setVideoName(String VideoName) {
		GoogleHandling.VideoName = VideoName;
	}
	
	// 
	
	@Test(priority =1)
	public void Upload_Test_Report() throws IOException {
		MoveandRenameFiles MoveandRenameFile = new MoveandRenameFiles();
		MoveandRenameFile.CopyandRenameFile();
		String filePath = "../Report_Files";
		String fileName = ReportFile;
		String fileMimeType = "text/html";

		try {
			GDriveProcessor driveProcessor = new GDriveProcessor();
			driveProcessor.uploadFileToDrive(filePath, fileName, fileMimeType);
		} catch (GeneralSecurityException e) {
			LOG.error("GeneralSecurityException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("IOException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test(priority =2)
	public void Upload_Video_on_Drive() throws IOException {
		String filePath = "../test-recordings";
		String fileName = VideoName;
		String fileMimeType = "video/x-msvideo";

		try {
			GDriveProcessor driveProcessor = new GDriveProcessor();
			driveProcessor.uploadFileToDrive(filePath, fileName, fileMimeType);
		} catch (GeneralSecurityException e) {
			LOG.error("GeneralSecurityException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("IOException: " + e.getMessage());
			e.printStackTrace();
		}
	}


	@Test(priority =3)
	public void readLocalExcelAndSaveToGoogleSheet() {
		final String range = "SheetName!C1:Z";
		
		//-------Remove afterwards
		//String Clumn = "Load Time in milliseconds 12-21";
		String sheetId = "testsheetID";
		//-------Remove afterwards
		
		Map<String, List<String>> values1 = new ExcelUtil().open().navigateToSheet("Timeout")
				.readFromSheet();
		List<String> timeSheet = values1.get(Clumn);
		timeSheet.add(0, Clumn);
		System.out.println(Clumn + ":" + timeSheet);

		try {
			GSheetProcessor processor = new GSheetProcessor();

			// Reading from GoogleSheet
			List<List<Object>> values = processor.readGoogleSheet(sheetId, range);

			// Updating second column with new Values
			int i = 0;
			for (List<Object> value : values) {
				if(i < timeSheet.size()) {
					value.add(0, timeSheet.get(i++));
				}else {
					value.add(0, "");
				}
			}

			// Updating Google Sheet
			processor.updateGoogleSheet(sheetId, range, values);

		} catch (GeneralSecurityException e) {
			LOG.error("GeneralSecurityException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("IOException: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

	
	
	