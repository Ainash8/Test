package com.java;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FetchReqResp {
	public  String arr[];
	public  String ESBAdditionalTag[];
	 String temp;
	static String filename;
	 static FileOutputStream out;

	
	@SuppressWarnings("deprecation")
	
	//static File file;

	public void convertDatat(String path) throws Exception
	{
		
		 XSSFWorkbook workbook = new XSSFWorkbook();
			
			// Create a blank sheet
			 XSSFSheet spreadsheet = workbook.createSheet("Fetch");

			 XSSFSheet spreadsheet1 = workbook.createSheet("Payment");
			// Create row object

			 XSSFRow row;
		spreadsheet = workbook.getSheet("Fetch");
		Iterator<Row> rowIte = spreadsheet.iterator();
		while(rowIte.hasNext())
		{
			rowIte.next();
			rowIte.remove();
		}
		

		File file = new File(path);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");

		String mainFile = sc.next();
		//getting name of biller
		filename = file.getName();
		filename = filename.replaceAll(".txt", "");
		System.out.println(filename);

		String billFetchReqOpen[] = mainFile.split("<bbps:BillFetchRequest");
		String billfetchReqClose[] = billFetchReqOpen[1].split("</bbps:BillFetchRequest>");

		String fetchRequest = billfetchReqClose[0].toString();
		// System.out.println(fetchRequest);

		String billFetchRespOpen[] = mainFile.split("<bbps:BillFetchResponse");
		String billFetchRespClose[] = billFetchRespOpen[1].split("</bbps:BillFetchResponse>");
		String fetchResponse = billFetchRespClose[0].toString();
		// System.out.println(fetchResponse);

		// defining customer params array
		String customerParams[] = {
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Client_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level1_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level2_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level3_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level4_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n" };
		String fetchRespcustomerParams[] = {
				"<NS3:BillDetails><axis:CustomerParams><NS3:Tag><NS3:Name>Client_Code</NS3:Name><NS3:Value></NS3:Value></NS3:Tag></NS3:CustomerParams></NS3:BillDetails>\r\n",
				"<NS3:BillDetails><axis:CustomerParams><NS3:Tag><NS3:Name>Level1_Code</NS3:Name><NS3:Value></NS3:Value></NS3:Tag></NS3:CustomerParams></NS3:BillDetails>\r\n",
				"<NS3:BillDetails><axis:CustomerParams><NS3:Tag><NS3:Name>Level2_Code</NS3:Name><NS3:Value></NS3:Value></NS3:Tag></NS3:CustomerParams></NS3:BillDetails>\r\n",
				"<NS3:BillDetails><axis:CustomerParams><NS3:Tag><NS3:Name>Level3_Code</NS3:Name><NS3:Value></NS3:Value></NS3:Tag></NS3:CustomerParams></NS3:BillDetails>\r\n",
				"<NS3:BillDetails><axis:CustomerParams><NS3:Tag><NS3:Name>Level4_Code</NS3:Name><NS3:Value></NS3:Value></NS3:Tag></NS3:CustomerParams></NS3:BillDetails>\r\n" };

		String billerRespFields[] = { "<NS3:BillerResponse><NS3:Amount></NS3:Amount></NS3:BillerResponse>",
				"<NS3:BillerResponse><NS3:BillDate></NS3:BillDate></NS3:BillerResponse>",
				"<NS3:BillerResponse><NS3:BillNumber></NS3:BillNumber></NS3:BillerResponse>",
				"<NS3:BillerResponse><NS3:BillPeriod></NS3:BillPeriod></NS3:BillerResponse>",
				"<NS3:BillerResponse><NS3:CustomerName></NS3:CustomerName></NS3:BillerResponse>",
				"<NS3:BillerResponse><NS3:DueDate><NS3:DueDate></NS3:BillerResponse>" };

		String[] reasonEPH = { "approvalRefNum=", "complianceReason", "complianceRespCd", "responseCode",
				"responseReason" };

		// finding billerid
		String billDetailsTag[] = fetchRequest.split("<BillDetails>");
		String customerParamsTag[] = billDetailsTag[1].split("<CustomerParams>");
		// billerId tag
		String billerId = customerParamsTag[0].toString();
		String billerIdOpen[] = billerId.split("<");
		String billerIdClose[] = billerIdOpen[1].split("/>");
		// printing biller id

		String corpCode = billerIdClose[0].toString();
		corpCode = corpCode.replaceAll("Biller id", "Corp_code");

		String billerIdTag = billerIdClose[0].toString();
		System.out.println("<BillDetails><" + billerIdTag + "/></BillDetails>");

		// finding customer parameters in fetch
		String customerParamsOpen[] = fetchRequest.split("<CustomerParams>");
		String customerParamsClose[] = customerParamsOpen[1].split("</CustomerParams>");
		String customerparamsTag[] = customerParamsClose[0].split("/>");

		// printing customerparams tag of fetch
		for (int i = 0; i < customerparamsTag.length - 1; i++) {
			System.out.println("*****<CustomerParams>" + customerparamsTag[i] + "/></CustomerParams>");
		}

		// Finding additional info tags in fetch response
		boolean addInfo = false;

		if (fetchResponse.contains("<AdditionalInfo>")) {
			String additionalInfoOpen[] = fetchResponse.split("<AdditionalInfo>");
			String additionalInfoClose[] = additionalInfoOpen[1].split("</AdditionalInfo>");
			String dummy = additionalInfoClose[0].toString();
			if(dummy.contains("/>") )
			{
				dummy = dummy.replaceAll("/>", "");
			}
			else if(dummy.contains("</Tag>") )
			{
				dummy = dummy.replaceAll("</Tag>", "");
				if(dummy.contains(">"))
				{
					dummy = dummy.replaceAll(">", "");
				}
			}
			dummy = dummy.replaceAll(" ", " ");
			dummy = dummy.trim();
			arr = dummy.split("\n");

			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}

			String additionalInfoParamsCloseParams = additionalInfoClose[0];
			String[] add = additionalInfoParamsCloseParams.split("\"s+");
			for (int i = 0; i < add.length; i++) {
				System.out.println("####" + add[i]);
			}

			String test = additionalInfoParamsCloseParams.replaceAll("<Tag name=",
					"<NS3:AdditionalInfo><NS3:Tag><NS3:Name>");
			String test1 = test.replaceAll("value=", "</NS3:Name><NS3:Value>");
			String test2 = test1.replaceAll("></Tag>|/>", "</NS3:Value></NS3:Tag></NS3:AdditionalInfo><>");
			String test3 = test2.replaceAll("\"", "");
			ESBAdditionalTag = test3.split("<>");
			for (int i = 0; i < ESBAdditionalTag.length; i++) {
				System.out.println("++++||" + ESBAdditionalTag[i] + "+++||");
			}

			addInfo = false;

		} else {
			System.out.println("no data");
			addInfo = true;
		}

		// finding biller response tag
		String billerResponse[] = fetchResponse.split("</BillDetails>");
		String billerResponseClose[] = billerResponse[1].split("<Reason");
		String billerRespOpen[] = billerResponseClose[0].split("<BillerResponse");
		String billerRespClose[] = billerRespOpen[1].split("></BillerResponse>|/>");
		String billerResponseParams = billerRespClose[0].trim();
		String billerResponseFields = billerResponseParams.toString();
		StringBuffer billerResponseFieldsData = new StringBuffer(billerResponseFields);
		billerResponseFieldsData.deleteCharAt(billerResponseFields.length() - 1);
		String billerResponseFieldsParams = billerResponseFieldsData.toString();
		String billerResponseFieldsTag[] = billerResponseFieldsParams.split("\" ");
		List<String> billerRespList = new ArrayList<>();

		// Printing biller response parameters
		for (int i = 0; i < billerResponseFieldsTag.length; i++) {

			if (billerResponseFieldsTag[i].contains("amount")) {
				billerRespList.add("<NS3:BillerResponse><NS3:Amount></NS3:Amount></NS3:BillerResponse>");
			} else if (billerResponseFieldsTag[i].contains("billDate")) {
				billerRespList.add("<NS3:BillerResponse><NS3:BillDate></NS3:BillDate></NS3:BillerResponse>");
			} else if (billerResponseFieldsTag[i].contains("billNumber")) {
				billerRespList.add("<NS3:BillerResponse><NS3:BillNumber></NS3:BillNumber></NS3:BillerResponse>");
			} else if (billerResponseFieldsTag[i].contains("billPeriod")) {
				billerRespList.add("<NS3:BillerResponse><NS3:BillPeriod></NS3:BillPeriod></NS3:BillerResponse>");
			} else if (billerResponseFieldsTag[i].contains("customerName")) {
				billerRespList.add("<NS3:BillerResponse><NS3:CustomerName></NS3:CustomerName></NS3:BillerResponse>");
			} else if (billerResponseFieldsTag[i].contains("dueDate")) {
				billerRespList.add("<NS3:BillerResponse><NS3:DueDate><NS3:DueDate></NS3:BillerResponse>");
			}

			System.out.println("<BillerResponse " + billerResponseFieldsTag[i] + "\"></BillerResponse>");
		}

		String[] EsbrespArray = new String[billerRespList.size()];
		for (int i = 0; i < billerRespList.size(); i++)

			EsbrespArray[i] = billerRespList.get(i);

		String str1[] = billerResponseFields.split("=\"");

		// inserting data into ESB column for biller response

		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(billerResponseFields);
		List<String> list = new ArrayList<>();
		while (m.find()) {
			list.add(m.group(1));

		}

		System.out.println(list);
		String[] respArray = new String[list.size()];
		for (int i = 0; i < list.size(); i++)

			respArray[i] = list.get(i);

		// getting reason params
		String billerReasonOpen[] = billerResponseClose[1].split("<Reason");
		String billerReasonClose[] = billerReasonOpen[0].split("/>|</Reason>");
		String responseReason = billerReasonClose[0].trim();
		String responseReasonTag[] = responseReason.split(" |>");
		// printing reason params
		for (int i = 0; i < responseReasonTag.length; i++) {
			System.out.println("<Reason " + responseReasonTag[i] + "/>");
		}

		// finding fetch response customer params
		String responseCustomerParams[] = fetchResponse.split("<CustomerParams>");
		String responseCustomerParamsCloseTag[] = responseCustomerParams[1].split("</CustomerParams>");
		String responsetagName[] = responseCustomerParamsCloseTag[0].split("/>|></Tag>");

		// printting customer params of response
		for (int i = 0; i < responsetagName.length - 1; i++) {
			System.out.println("====<CustomerParams>" + responsetagName[i] + "</CustomerParams>");
		}

		List<Object[]> fetchRequestinfo = new ArrayList<>();
		List<Object[]> fetchResponseinfo = new ArrayList<>();
		fetchRequestinfo.add(new Object[] { "EPH", "ESB", filename, "Remarks" });
		fetchRequestinfo.add(new Object[] { corpCode,
				"<axis:BillDetails><axis:Biller><axis:Id></axis:Id></axis:Biller></axis:BillDetails>",
				"<BillDetails><" + billerIdTag + "/></BillDetails>" });

		for (int i = 0; i < customerparamsTag.length - 1; i++) {
			fetchRequestinfo.add(new Object[] { "", customerParams[i] + "",
					"<CustomerParams>" + customerparamsTag[i] + "/></CustomerParams>" });
		}

		fetchRequestinfo.add(new Object[] { "", "", "", "" });

		fetchResponseinfo.add(new Object[] { filename, "ESB", "EPH", "Remarks" });

		for (int i = 0; i < responseReasonTag.length; i++) {
			fetchResponseinfo.add(new Object[] { "<Reason " + responseReasonTag[i] + "/>",
					"<NS3:" + responseReasonTag[i] + "/>", reasonEPH[i], "" });

		}

		for (int i = 0; i < billerResponseFieldsTag.length; i++) {

			fetchResponseinfo.add(new Object[] { "<BillerResponse " + billerResponseFieldsTag[i] + "\"/>",
					"<NS3:BillerResponse><NS3:" + EsbrespArray[i]  + "</NS3:BillerResponse>", "", "" });

		}

		// int len = responsetagName.length;

		for (int i = 0; i < responsetagName.length - 1; i++) {

			fetchResponseinfo.add(new Object[] { "<CustomerParams>" + responsetagName[i] + "/></CustomerParams>",
					fetchRespcustomerParams[i], "", "" });
		}

		if (addInfo == false)
			for (int i = 0; i < arr.length; i++) 
			{
				
			
				fetchResponseinfo.add(new Object[] { "<AdditionalInfo>" + arr[i] + "/></AdditionalInfo>",
						ESBAdditionalTag[i], "", "" });
				}
			

		// iteratating over fetch request
		int rowCount = 2;
		for (Object[] data : fetchRequestinfo) {
			Row rows = spreadsheet.createRow(rowCount++);
			int columnCount = 0;
			for (Object field : data) {
				spreadsheet.autoSizeColumn(columnCount);
				Cell cell = rows.createCell(columnCount++);
				CellStyle style1 = cell.getSheet().getWorkbook().createCellStyle();
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				style1.setBorderBottom(CellStyle.BORDER_MEDIUM);
				style1.setBorderTop(CellStyle.BORDER_MEDIUM);
				style1.setBorderRight(CellStyle.BORDER_MEDIUM);
				style1.setBorderLeft(CellStyle.BORDER_MEDIUM);
				style1.setWrapText(true);
				
				cell.setCellStyle(style1);

				if (field instanceof String) {
					if (((String) field).contains("Remarks"))

					{
						for (int i = 0; i < 4; i++) {
							Cell cells = rows.getCell(i);

							CellStyle style3 = cells.getSheet().getWorkbook().createCellStyle();
							style3.setAlignment(CellStyle.ALIGN_CENTER);
							style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
							style3.setFillBackgroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
							style3.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
							style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
							style3.setBorderBottom(CellStyle.BORDER_MEDIUM);
					         style3.setBorderTop(CellStyle.BORDER_MEDIUM);
					         style3.setBorderRight(CellStyle.BORDER_MEDIUM);
					         style3.setBorderLeft(CellStyle.BORDER_MEDIUM);
							cells.setCellStyle(style3);
						}

					}
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		// inserting additional data
		int rowCount2 = spreadsheet.getLastRowNum() + 1;
		{
			// int row2 = spreadsheet.getLastRowNum();
			row = spreadsheet.createRow(rowCount2);
			Cell cell2 = row.createCell(0);
			cell2.setCellValue("Fetch Response");
			CellStyle style3 = cell2.getSheet().getWorkbook().createCellStyle();
			style3.setAlignment(CellStyle.ALIGN_CENTER);
			style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style3.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			style3.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell2.setCellStyle(style3);

			// Create a cellRangeAddress to select a range to merge.
			CellRangeAddress cellRangeAddress3 = new CellRangeAddress(rowCount2, rowCount2, 0, 3);
			spreadsheet.addMergedRegion(cellRangeAddress3);
		}

		// iterarting of fetch response
		int rowCount1 = spreadsheet.getLastRowNum() + 1;
		for (Object[] data : fetchResponseinfo) {
			Row rows = spreadsheet.createRow(rowCount1++);

			int columnCount = 0;
			for (Object field : data) {
				Cell cell = rows.createCell(columnCount++);
				CellStyle style1 = cell.getSheet().getWorkbook().createCellStyle();
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				style1.setBorderBottom(CellStyle.BORDER_MEDIUM);
				style1.setBorderTop(CellStyle.BORDER_MEDIUM);
				style1.setBorderRight(CellStyle.BORDER_MEDIUM);
				style1.setBorderLeft(CellStyle.BORDER_MEDIUM);
				style1.setWrapText(true);
				cell.setCellStyle(style1);

				if (field instanceof String) {
					if (((String) field).contains("Remarks"))

					{
						int test = rows.getRowNum();
						for (int i = 0; i < 4; i++) {
							Cell cells = rows.getCell(i);

							CellStyle style3 = cells.getSheet().getWorkbook().createCellStyle();
							style3.setAlignment(CellStyle.ALIGN_CENTER);
							style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
							style3.setFillBackgroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
							style3.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
							style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
							style3.setBorderBottom(CellStyle.BORDER_MEDIUM);
					         style3.setBorderTop(CellStyle.BORDER_MEDIUM);
					         style3.setBorderRight(CellStyle.BORDER_MEDIUM);
					         style3.setBorderLeft(CellStyle.BORDER_MEDIUM);
							cells.setCellStyle(style3);
						}

					}
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		
		
		/*PaymentErrorCodes pcodes = new PaymentErrorCodes();
		pcodes.errorCodes();*/
		
		// Write the workbook in file system
	//	FileOutputStream out = new FileOutputStream(new File(FileUploadController.folderpath+filename+".xlsx"));
		try {
		String fileDest = FileUploadController.folderpath+filename+".xlsx" ;
		File f = new File(fileDest);
		if(f.exists())
		{
			f.delete();
		}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		out = new FileOutputStream(new File(FileUploadController.folderpath+filename+".xlsx"));
		String pathLink = out.toString();
		row = spreadsheet.createRow((short) 0);
		Cell cell = row.createCell((short) 0);
		cell.setCellValue("ESB to "+filename);
		CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		// Create a cellRangeAddress to select a range to merge.
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 3);

		row = spreadsheet.createRow((short) 1);
		Cell cell1 = row.createCell((short) 0);
		cell1.setCellValue("Bill Fetch Request");
		CellStyle style1 = cell1.getSheet().getWorkbook().createCellStyle();
		style1.setAlignment(CellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell1.setCellStyle(style1);
		CellRangeAddress cellRangeAddress1 = new CellRangeAddress(1, 1, 0, 3);
		Row row1 = spreadsheet.getRow(3);
		CellStyle style2 = row1.getSheet().getWorkbook().createCellStyle();
		style2.setFillForegroundColor(IndexedColors.AQUA.getIndex());

		// Merge the selected cells.
		spreadsheet.addMergedRegion(cellRangeAddress);
		spreadsheet.addMergedRegion(cellRangeAddress1);
		// spreadsheet.addMergedRegion(cellRangeAddress3);
		workbook.write(out);
		out.close();
		//workbook.close();
		
	//	workbook.write(out);
		

		System.out.println(filename+".xlsx written successfully");
		PaymentReqResp payment = new PaymentReqResp();
		payment.printData(path);
		FetchErrorCodes codes = new FetchErrorCodes();
		codes.errorCodes();
		PaymentErrorCodes pcodes = new PaymentErrorCodes();
		pcodes.errorCodes();
		
	
	}
}
