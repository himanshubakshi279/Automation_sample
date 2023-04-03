package com.Automation.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class MoveandRenameFiles {
	WebDriver driver;

	@Test(priority = 1)
	public void CopyandRenameFile() throws IOException {
		InputStream inStream = null;
		OutputStream outStream = null;
		File afile = new File("./test-output/TestReport.html");
		File bfile = new File("./Report_Files/TestReport.html");
		try {

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];
			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();
			System.out.println("File is copied successful!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	String NewFileName = java.time.LocalDate.now() + "_TestReport.html";
		File rename = new File("./Report_Files/" + NewFileName);
		System.out.println(NewFileName);
		GoogleHandling.setFileName(NewFileName);
		boolean flag = bfile.renameTo(rename);
		if (flag == true) {
			System.out.println("File Successfully Rename");
		}
		else {
			System.out.println("Operation Failed");
		}
	}
}