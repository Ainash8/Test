package com.java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PaymentErrorCodes {
	
	public void errorCodes() throws Exception
	{

		FileInputStream file1 = new FileInputStream(new File(FileUploadController.folderpath+FetchReqResp.filename+".xlsx"));
		   
		//Get the workbook instance for XLS file 
		XSSFWorkbook workbook = new XSSFWorkbook (file1);

		//Get first sheet from the workbook
		XSSFSheet spreadsheet1 = workbook.getSheetAt(1);
		//XSSFSheet spreadsheet1 = workbook.createSheet("Payment");
		//File file = new File();
		 XSSFRow row;

	      List< Object[] > empinfo = new ArrayList<Object[]> ();
	      empinfo.add( new Object[] {
	         "Response Code", "ResponseReason","Compliance Code", "Compliance Reason",FetchReqResp.filename+" Code"});
	      
	      empinfo.add( new Object[] {
	         "000", "Success", "NA","NA" ,"NA"});
	      
	      empinfo.add( new Object[] {
	         "200", "Failure", "BPR001","Incorrect / invalid customer account","BPR001" });
	      
	      empinfo.add(new Object[] {
	         "", "", "BPR002","Invalid combination of customer parameters ","BPR002" });
	      
	      empinfo.add(new Object[] {
	         "", "", "BPR003","Customer account is blocked / closed","BPR003"});
	      
	      empinfo.add( new Object[] {
	         "", "", "BPR004","Customer account is not activated ","BPR004" });
	      
	      empinfo.add( new Object[] {
	    	         "", "", "BPR005","Payment cannot be accepted at this time","BPR005" });
	      empinfo.add(new Object[] {
	 	         "", "", "BPR006","Payment request has been exceeded for the day","BPR006" });
	      empinfo.add(new Object[] {
	  	         "", "", "BPR007","Repeat payment request","BPR007" });
	      empinfo.add(new Object[] {
	   	         "", "", "BPR008","Due date has been expired to pay this amount. Please re-fetch\r\n" + 
	   	         		"again to get the current outstanding","BPR008" });
	      empinfo.add( new Object[] {
	    	         "", "", "BPR009","Scheduled downtime taken by biller for maintenance activity.\r\n" + 
	    	         		"Please try again later","BPR009" });
	      empinfo.add( new Object[] {
	    	         "", "", "BPR010","Unscheduled downtime taken by biller for maintenance activity.\r\n" + 
	    	         		"Please try again later","BPR010" });
	      
	      //Iterate over data and write to sheet

	      int rowCount =spreadsheet1.getLastRowNum()+2;
	      for(Object[] data : empinfo)
	      {
	     	Row rows = spreadsheet1.createRow(rowCount++);
	     	//Row rows = FetchReqResp.spreadsheet.getRow(rowCount);
	     	
	     	 int columnCount = 6;
	     	 for(Object field :data)
	     	 {
	     		 spreadsheet1.autoSizeColumn(columnCount);
	     		 Cell cell =  rows.createCell(columnCount++);
				 CellStyle style1 = cell.getSheet().getWorkbook().createCellStyle();
		         style1.setAlignment(CellStyle.ALIGN_CENTER);
		         style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		         style1.setBorderBottom(CellStyle.BORDER_MEDIUM);
		         style1.setBorderTop(CellStyle.BORDER_MEDIUM);
		         style1.setBorderRight(CellStyle.BORDER_MEDIUM);
		         style1.setBorderLeft(CellStyle.BORDER_MEDIUM);
		         style1.setWrapText(true);
		         cell.setCellStyle(style1);
	     		 
	     		 if(field instanceof String)
	     		 {
	     			if (((String) field).contains(FetchReqResp.filename))

					{
						for (int i = 6; i < 11; i++) {
							Cell cells = rows.getCell(i);
							CellStyle style3 = cells.getSheet().getWorkbook().createCellStyle();
							style3.setAlignment(CellStyle.ALIGN_CENTER);
							style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
							style3.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
							style3.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
							style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
							style3.setBorderBottom(CellStyle.BORDER_MEDIUM);
							style3.setBorderTop(CellStyle.BORDER_MEDIUM);
							style3.setBorderRight(CellStyle.BORDER_MEDIUM);
							style3.setBorderLeft(CellStyle.BORDER_MEDIUM);
							cells.setCellStyle(style3);
						}

					}

	     			 
	     			 cell.setCellValue((String)field);
	     		 }
	     		 else if (field instanceof Integer) {
	     			 cell.setCellValue((Integer)field);
	     		 }
	     	 }
	     }
	      FileOutputStream out = new FileOutputStream(new File(FileUploadController.folderpath+FetchReqResp.filename+".xlsx"));
	      workbook.write(out);
	      out.close();
	   }

}