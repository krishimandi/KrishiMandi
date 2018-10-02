package com.krishimandi.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.krishimandi.application.KrishiMandiException;
import com.krishimandi.application.SystemConstants;
import com.krishimandi.logs.LogRequestResponseDataObject;
import com.krishimandi.logs.LogUtil;

public class TestFile {

	public static void main(String[] args) {

		LogRequestResponseDataObject obj = new LogRequestResponseDataObject();
		obj.setUserID("12245gcx65");
		obj.setRequestType(19);
		obj.setRequestTime(LocalDateTime.now().toString());
		obj.setRequestData("request data");
		obj.setResponseData("response data");
		try {
		
			LogUtil.logRequestResponse(obj);
		} catch (KrishiMandiException e) {
			e.printStackTrace();
		};
	}

	public static void logRequest() throws KrishiMandiException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet();
		XSSFRow row;
		row = spreadsheet.createRow(1);
		Cell cell = row.createCell(1);
		cell.setCellValue("hi");
		String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		try {
			FileOutputStream out = new FileOutputStream(
					new File("C:/"+ SystemConstants.SYSTEM_NAME + "/" + 
							SystemConstants.REQUEST_RESPONSE_LOG + "/" + todaysDate + ".xlsx"));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("DONE");
	}

	private void mapLocalizationContent() throws KrishiMandiException {

		/*String localizationDirectoryPath = new File("resources").getAbsolutePath();
		File localizationDirectory = new File(localizationDirectoryPath);
		File[] languages = null;
		if(null != localizationDirectory && null != localizationDirectoryPath) {

			languages = localizationDirectory.listFiles();
		}else {

			throw new KrishiMandiException("Error while loading localization content");
		}
		for (int i = 0; i < languages.length; i++) {

			System.out.println(languages[i].getAbsolutePath());
		}*/

		try {
			
			URL url = getClass().getClassLoader().getResource("/resources");
			if(null == url) {

				throw new KrishiMandiException("Error while loading localization content");
			}else {

				File dir = new File(url.toURI());
				for (File nextFile : dir.listFiles()) {
					
					System.out.println("File: " + nextFile.getAbsolutePath());
				}
			}
		}catch (URISyntaxException e) {
			// TODO: handle exception
		}
	}
}
