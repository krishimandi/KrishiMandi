package com.krishimandi.logs;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.krishimandi.application.KrishiMandiException;
import com.krishimandi.application.SystemConstants;

/**
 * This class logs the following data into a spreadsheet for each request from a client
 * userID, requestType, requestTime, requestData and responseData into a row. 
 */
public class LogUtil {

	/** XSSFWorkbook instance */
	private static XSSFWorkbook workbook = null;
	/** XSSFSheet instance */
	private static XSSFSheet spreadsheet = null;

	/**
	 * This method is used to log data into excel sheet.
	 * @param dataObject - the object contains data that needs to be logged 
	 * into the excel sheet. 
	 * @throws KrishiMandiException
	 */
	public static void logRequestResponse(LogRequestResponseDataObject dataObject) throws KrishiMandiException {

		try {

			/** Throw back exception if dataObject is null. */
			if (null == dataObject)
				throw new KrishiMandiException("Invalid dataObject");

			/** prepare workbook instance */
			workbook = prepareWorkbook();

			/** prepare speadsheet */
			spreadsheet = getSpreadSheet(workbook);

			String todaysDate = new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime());
			File logFile = new File("C:/"+ SystemConstants.SYSTEM_NAME + "/" + 
					SystemConstants.REQUEST_RESPONSE_LOG + "/" + todaysDate + ".xlsx");

			if(null != logFile && !logFile.exists() && spreadsheet.getLastRowNum() == 0) {

				/** prepare header row */
				prepareHeaderRow();
			}	
			
			/** prepare data row */
			prepareDataRow(dataObject);

			FileOutputStream outputStream = new FileOutputStream(logFile);
			workbook.write(outputStream);
			outputStream.close();
		}catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get 'workbook' instance.	
	 * @return XSSFWorkbook instance
	 */
	private static XSSFWorkbook prepareWorkbook() {

		if(null == workbook) {

			return new XSSFWorkbook();
		}else {

			return workbook;
		}
	}

	/**
	 * This method is used to get 'spreadsheet' instance.	
	 * @return XSSFSheet instance
	 * @throws KrishiMandiException 
	 */
	private static XSSFSheet getSpreadSheet(XSSFWorkbook workbook) throws KrishiMandiException {

		if(null != spreadsheet) {

			if(null != workbook) {

				return workbook.getSheet(SystemConstants.LOG_REQUEST_RESPONSE);
			}else {

				throw new KrishiMandiException("Error while returning spreadsheet instance");
			}
		}else {

			if(null != workbook) {

				return workbook.createSheet(SystemConstants.LOG_REQUEST_RESPONSE);
			}else {

				throw new KrishiMandiException("Error while creating spreadsheet");
			}
		}
	}

	/**
	 * This method prepares the header for the log file.
	 * The header row contains following text 
	 * User ID, Request Type, Request Time, Request Data, Response Data.
	 */
	private static void prepareHeaderRow() {

		XSSFRow headerRow = spreadsheet.createRow(0);
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = workbook.createFont();
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		headerStyle.setFont(headerFont);

		Cell userIDTextCell = headerRow.createCell(0);
		userIDTextCell.setCellStyle(headerStyle);
		userIDTextCell.setCellValue(SystemConstants.TEXT_USER_ID);

		Cell requestTypeTextCell = headerRow.createCell(1);
		requestTypeTextCell.setCellStyle(headerStyle);
		requestTypeTextCell.setCellValue(SystemConstants.TEXT_REQUEST_TYPE);

		Cell requestTimeTextCell = headerRow.createCell(2);
		requestTimeTextCell.setCellStyle(headerStyle);
		requestTimeTextCell.setCellValue(SystemConstants.TEXT_REQUEST_TIME);

		Cell requestDataTextCell = headerRow.createCell(3);
		requestDataTextCell.setCellStyle(headerStyle);
		requestDataTextCell.setCellValue(SystemConstants.TEXT_REQUEST_DATA);
		
		Cell responseDataTextCell = headerRow.createCell(4);
		responseDataTextCell.setCellStyle(headerStyle);
		responseDataTextCell.setCellValue(SystemConstants.TEXT_RESPONSE_DATA);
	}

	/**
	 * This method is used to log the request response data for each request 
	 * sent by the user into an excel sheet.
	 * @throws KrishiMandiException 
	 */
	private static void prepareDataRow(LogRequestResponseDataObject dataObject) throws KrishiMandiException {

		
		if(null == dataObject.getUserID() || null == dataObject.getRequestData() || 
				null == dataObject.getRequestTime() || null == dataObject.getResponseData()) {

			throw new KrishiMandiException("Invalid data to log");
		}

		XSSFRow dataRow = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);
		CellStyle dataCellStyle = workbook.createCellStyle();
		dataCellStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
		Font headerFont = workbook.createFont();
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerFont.setBoldweight(Font.COLOR_NORMAL);
		dataCellStyle.setFont(headerFont);

		
		Cell userIDCell = dataRow.createCell(0);
		userIDCell.setCellStyle(dataCellStyle);
		userIDCell.setCellValue(dataObject.getUserID());

		Cell requestTypeCell = dataRow.createCell(1);
		requestTypeCell.setCellStyle(dataCellStyle);
		requestTypeCell.setCellValue(dataObject.getRequestType());

		Cell requestTimeCell = dataRow.createCell(2);
		requestTimeCell.setCellStyle(dataCellStyle);
		requestTimeCell.setCellValue(dataObject.getRequestTime());

		Cell requestDataCell = dataRow.createCell(3);
		requestDataCell.setCellStyle(dataCellStyle);
		requestDataCell.setCellValue(dataObject.getRequestData());
		
		Cell responseDataCell = dataRow.createCell(4);
		responseDataCell.setCellStyle(dataCellStyle);
		responseDataCell.setCellValue(dataObject.getResponseData());
	}
}	