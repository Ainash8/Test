package com.java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.NodeList;

public class PaymentReqResp {
	public static String arr[];
	public static String ESBAdditionalTag[];
	static String temp;

	public void printData(String path) throws Exception {

		//XSSFWorkbook workbook = new XSSFWorkbook();
		FileInputStream file1 = new FileInputStream(new File(FileUploadController.folderpath+FetchReqResp.filename+".xlsx"));
		   
		//Get the workbook instance for XLS file 
		XSSFWorkbook workbook = new XSSFWorkbook (file1);

		//Get first sheet from the workbook
		XSSFSheet spreadsheet1 = workbook.getSheetAt(1);
		//XSSFSheet spreadsheet1 = workbook.createSheet("Payment");
		File file = new File(path);
		 XSSFRow row;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);

		sc.useDelimiter("\\Z");

		String mainFile = sc.next();

		String billPaymentReqOpen[] = mainFile.split("<bbps:BillPaymentRequest");
		String billPaymentReqClose[] = billPaymentReqOpen[1].split("</bbps:BillPaymentResponse>");

		String paymentRequest = billPaymentReqClose[0].toString();
		// System.out.println(fetchRequest);

		String billPaymentRespOpen[] = mainFile.split("<bbps:BillPaymentResponse");
		String billPaymentResplose[] = billPaymentRespOpen[1].split("</bbps:BillFetchResponse>");
		String paymentResponse = billPaymentResplose[0].toString();
		// System.out.println(fetchResponse);

		// defining customer params array
		String customerParams[] = {
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Client_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level1_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level2_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level3_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n",
				"<axis:BillDetails><axis:CustomerParams><axis:Tag><axis:Name>Level4_Code</axis:Name><axis:Value></axis:Value></axis:Tag></axis:CustomerParams></axis:BillDetails>\r\n" };

		String paymentMethod[] = { "<axis:PaymentMethod><axis:OFFUSPay></axis:OFFUSPay></axis:PaymentMethod>",
				"<axis:PaymentMethod><axis:PaymentMode></axis:PaymentMode></axis:PaymentMethod>",
				"<axis:PaymentMethod><axis:QuickPay></axis:QuickPay></axis:PaymentMethod>",
				"<axis:PaymentMethod><axis:SplitPay></axis:SplitPay></axis:PaymentMethod>" };

		String amountTags[] = { "<axis:AmountList><axis:amount></axis:amount></axis:AmountList>",
				"<axis:AmountList><axis:Currency></axis:Currency></axis:AmountList>",
				"<axis:AmountList><axis:CustConvFee></axis:CustConvFee></axis:AmountList>" };

		String billerRespFields[] = { "<axis:BillerResponse><axis:Amount></axis:Amount></axis:BillerResponse>",
				"<axis:BillerResponse><axis:BillDate></axis:BillDate></axis:BillerResponse>",
				"<axis:BillerResponse><axis:BillNumber></axis:BillNumber></axis:BillerResponse>",
				"<axis:BillerResponse><axis:BillPeriod></axis:BillPeriod></axis:BillerResponse>",
				"<axis:BillerResponse><axis:CustomerName></axis:CustomerName></axis:BillerResponse>",
				"<axis:BillerResponse><axis:DueDate><axis:DueDate></axis:BillerResponse>" };

		String TxnFields[] = {

				"<axis:TransactionDetails><axis:PaymentDate></axis:PaymentDate></axis:TransactionDetails>",
				"<axis:TransactionDetails><axis:TxnReferenceId></axis:TxnReferenceId></axis:TransactionDetails>" };

		String[] reasonEPH = { "approvalRefNum=", "complianceReason", "complianceRespCd", "responseCode",
				"responseReason" };

		// finding billerid
		String billDetailsTag[] = paymentRequest.split("<BillDetails>");
		String customerParamsTag[] = billDetailsTag[1].split("<CustomerParams>");
		// billerId tag
		String billerId = customerParamsTag[0].toString();
		String billerIdOpen[] = billerId.split("<");
		String billerIdClose[] = billerIdOpen[1].split("/>");
		// printing biller id

		String billerIdTag = billerIdClose[0].toString();
		System.out.println("<BillDetails><" + billerIdTag + "/></BillDetails>");

		// finding customer parameters in fetch
		String customerParamsOpen[] = paymentRequest.split("<CustomerParams>");
		String customerParamsClose[] = customerParamsOpen[1].split("</CustomerParams>");
		String customerparamsTag[] = customerParamsClose[0].split("/>");

		// printing customerparams tag of fetch
		for (int i = 0; i < customerparamsTag.length - 1; i++) {
			System.out.println("<CustomerParams>" + customerparamsTag[i] + "/></CustomerParams>");
		}

		// finding biller response tag
		String billerResponse[] = paymentRequest.split("</BillDetails>");
		String billerResponseClose[] = billerResponse[1].split("<Reason");
		String billerRespOpen[] = billerResponseClose[0].split("<BillerResponse");
		String billerRespClose[] = billerRespOpen[1].split("></BillerResponse>|/>");

		String billerResponseParams = billerRespClose[0].trim();

		// String BResp[] = billerResponseParams.split("\" ");

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

		String[] EsbreqpArray = new String[billerRespList.size()];
		for (int i = 0; i < billerRespList.size(); i++)

			EsbreqpArray[i] = billerRespList.get(i);

		// Printing biller response parameters
		for (int i = 0; i < billerResponseFieldsTag.length; i++) {
			System.out.println("<BillerResponse " + billerResponseFieldsTag[i] + "\"></BillerResponse>");
		}

		// finding Amount params
		String amountParamsOpen[] = paymentRequest.split("<Amount>");
		String amountParamsCloseTag[] = amountParamsOpen[1].split("</Amount>");
		String amtOpenTag[] = amountParamsCloseTag[0].split("<Amt");
		String amountTagName[] = amtOpenTag[1].split("</Tag>|/>");
		String amoutTrim = amountTagName[0].trim();
		String amountFinalTag[] = amoutTrim.split(" ");
		// printing Amount params
		for (int i = 0; i < amountFinalTag.length; i++) {
			System.out.println("<Amount " + amountFinalTag[i] + "/>");
		}

		// finding payment method
		String paymentMethodOpen[] = paymentRequest.split("<PaymentMethod");
		String paymentMethodClose[] = paymentMethodOpen[1].split("<Amount>");
		String paymentMethodInnerTag[] = paymentMethodClose[0].split("/>");
		String paymentMethodName = paymentMethodInnerTag[0].trim();
		String finalPamentMethodTag[] = paymentMethodName.split(" ");

		for (int i = 0; i < finalPamentMethodTag.length; i++) {
			System.out.println("<PaymentMethod " + finalPamentMethodTag[i] + "/>");
		}

		// finding Txn data for payment request
		String TxnOpen[] = paymentRequest.split("<Txn");
		String txnClose[] = TxnOpen[1].split("/>|</Txn>");
		if (txnClose[0].contains(">")) {
			txnClose = txnClose[0].split(">");
		}
		String txnData = txnClose[0].toString();
		txnData = txnData.trim();
		String txnFinalTag[] = txnData.split(" ");

		for (int i = 0; i < txnFinalTag.length; i++) {
			System.out.println("<Txn " + txnFinalTag[i] + "/>");
		}

		// Finding additional info tags in payment req
		boolean addInfo = false;

		if (paymentRequest.contains("<AdditionalInfo>")) {
			String additionalInfoOpen[] = paymentRequest.split("<AdditionalInfo>");
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


		// getting reason params
		String billerReasonOpen[] = paymentResponse.split("<Reason");
		String billerReasonClose[] = billerReasonOpen[1].split("/>|</Reason>");
		String responseReason = billerReasonClose[0].trim();
		String responseReasonTag[] = responseReason.split(" |>");
		// printing reason params
		for (int i = 0; i < responseReasonTag.length; i++) {
			System.out.println("<Reason " + responseReasonTag[i] + "/>");
		}

		// finding fetch response customer params
		String customerParamsRequest[] = paymentResponse.split("<CustomerParams>");
		String customerParamsRequestCloseTag[] = customerParamsRequest[1].split("</CustomerParams>");
		String customertagName[] = customerParamsRequestCloseTag[0].split("</Tag>|/>");

		// printing customer params of response
		for (int i = 0; i < customertagName.length - 1; i++) {
			System.out.println("====<CustomerParams>" + customertagName[i] + "</CustomerParams>");
		}

		// finding biller response tag
		String paymentResponseParams[] = paymentResponse.split("</BillDetails>");
		String billerResponseInnerTag[] = billerResponse[1].split("<Reason");
		String billerRespParamsOpen[] = billerResponseInnerTag[0].split("<BillerResponse");
		String billerRespParamsClose[] = billerRespParamsOpen[1].split("></BillerResponse>|/>");
		String billerResponseParamsTag = billerRespParamsClose[0].trim();
		String billerPaymentResponseFields = billerResponseParams.toString();
		StringBuffer billerPaymentResponseFieldsData = new StringBuffer(billerPaymentResponseFields);
		billerPaymentResponseFieldsData.deleteCharAt(billerPaymentResponseFields.length() - 1);
		String billerPaymentResponseFieldsParams = billerPaymentResponseFieldsData.toString();
		String billerPaymentResponseFieldsTag[] = billerPaymentResponseFieldsParams.split("\" ");

		// Printing biller response parameters
		for (int i = 0; i < billerPaymentResponseFieldsTag.length; i++) {
			System.out.println("<BillerResponse " + billerPaymentResponseFieldsTag[i] + "\"></BillerResponse>");
		}

		List<String> billerPaymentRespList = new ArrayList<>();

		// Printing biller response parameters
		for (int i = 0; i < billerPaymentResponseFieldsTag.length; i++) {

			if (billerPaymentResponseFieldsTag[i].contains("amount")) {
				billerPaymentRespList.add("<NS3:BillerResponse><NS3:Amount></NS3:Amount></NS3:BillerResponse>");
			} else if (billerPaymentResponseFieldsTag[i].contains("billDate")) {
				billerPaymentRespList.add("<NS3:BillerResponse><NS3:BillDate></NS3:BillDate></NS3:BillerResponse>");
			} else if (billerPaymentResponseFieldsTag[i].contains("billNumber")) {
				billerPaymentRespList.add("<NS3:BillerResponse><NS3:BillNumber></NS3:BillNumber></NS3:BillerResponse>");
			} else if (billerPaymentResponseFieldsTag[i].contains("billPeriod")) {
				billerPaymentRespList.add("<NS3:BillerResponse><NS3:BillPeriod></NS3:BillPeriod></NS3:BillerResponse>");
			} else if (billerPaymentResponseFieldsTag[i].contains("customerName")) {
				billerPaymentRespList
						.add("<NS3:BillerResponse><NS3:CustomerName></NS3:CustomerName></NS3:BillerResponse>");
			} else if (billerPaymentResponseFieldsTag[i].contains("dueDate")) {
				billerPaymentRespList.add("<NS3:BillerResponse><NS3:DueDate><NS3:DueDate></NS3:BillerResponse>");
			}

			System.out.println("<BillerResponse " + billerPaymentResponseFieldsTag[i] + "\"></BillerResponse>");
		}

		String[] EsbRespParamsArray = new String[billerPaymentRespList.size()];
		for (int i = 0; i < billerPaymentRespList.size(); i++)

			EsbRespParamsArray[i] = billerPaymentRespList.get(i);

		// finding txn data of payment resp
		String paymentTxnOpen[] = paymentResponse.split("<Txn");
		String paymentTxnClose[] = paymentTxnOpen[1].split("/>|</Txn>");
		if (paymentTxnClose[0].contains(">")) {
			paymentTxnClose = paymentTxnClose[0].split(">");
		}
		String paymentTxnData = paymentTxnClose[0].toString();
		paymentTxnData = paymentTxnData.trim();
		String paymentTxnFinalTag[] = paymentTxnData.split(" ");
		System.out.println(paymentTxnFinalTag[2]);

		// Finding additional info tags in payment response
		boolean addInfoResp = false;
		//boolean addInfo = false;

		if (paymentResponse.contains("<AdditionalInfo>")) {
			String additionalInfoOpen[] = paymentRequest.split("<AdditionalInfo>");
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

			addInfoResp = false;

		} else {
			System.out.println("no data");
			addInfoResp = true;
		}


		List<Object[]> paymentRequestInfo = new ArrayList<>();
		List<Object[]> paymentResponseInfo = new ArrayList<>();
		paymentRequestInfo.add(new Object[] { "EPH", "ESB", FetchReqResp.filename, "Remarks" });
		paymentRequestInfo.add(new Object[] { "",
				"<axis:BillDetails><axis:Biller><axis:Id></axis:Id></axis:Biller></axis:BillDetails>",
				"<BillDetails><" + billerIdTag + "/></BillDetails>" });

		for (int i = 0; i < customerparamsTag.length - 1; i++) {
			paymentRequestInfo.add(new Object[] { "", customerParams[i],
					"<CustomerParams>" + customerparamsTag[i] + "/></CustomerParams>", "" });
		}

		for (int i = 0; i < billerResponseFieldsTag.length; i++) {

			paymentRequestInfo.add(
					new Object[] { "", EsbreqpArray[i], "<BillerResponse " + billerResponseFieldsTag[i] + "\"/>", "" });

		}
		for (int i = 0; i < finalPamentMethodTag.length; i++) {
			paymentRequestInfo
					.add(new Object[] { "", paymentMethod[i], "<PaymentMethod " + finalPamentMethodTag[i] + "/>", "" });
		}
		for (int i = 0; i < amountFinalTag.length; i++) {
			paymentRequestInfo
					.add(new Object[] { "", amountTags[i], "<Amount><Amt " + amountFinalTag[i] + "/></Amount>", "" });

		}

		for (int i = 0; i < txnFinalTag.length; i++) {
			paymentRequestInfo.add(new Object[] { "", TxnFields[i], "<Txn " + txnFinalTag[i] + "/>", "" });
		}

		if (addInfo == false)
			for (int i = 0; i < arr.length; i++) {
				paymentRequestInfo.add(new Object[] { "", ESBAdditionalTag[i] + "/>", "<AdditionalInfo>" + arr[i] + "/></AdditionalInfo>", "" });
			}
	
		

		paymentResponseInfo.add(new Object[] { FetchReqResp.filename, "ESB", "EPH", "Remarks" });

		for (int i = 0; i < responseReasonTag.length; i++) {
			paymentResponseInfo.add(new Object[] { "<Reason " + responseReasonTag[i] + "/>",
					"<NS3:" + responseReasonTag[i] + "/>", reasonEPH[i], "" });

		}

		for (int i = 0; i < customertagName.length - 1; i++) {
			paymentResponseInfo.add(new Object[] { "<CustomerParams>" + customertagName[i] + "/></CustomerParams>",
					customerParams[i], "" });

		}

		for (int i = 0; i < billerPaymentResponseFieldsTag.length; i++) {
			paymentResponseInfo
					.add(new Object[] { "<BillerResponse " + billerPaymentResponseFieldsTag[i] + "\"></BillerResponse>",
							EsbRespParamsArray[i], "" });
		}

		paymentResponseInfo.add(new Object[] { "<Txn " + paymentTxnFinalTag[2] + "/>", TxnFields[1], "", "" });

		if (addInfoResp == false)
			for (int i = 0; i < arr.length; i++) {

				paymentResponseInfo.add(new Object[] { "<AdditionalInfo>" + arr[i] + "/></AdditionalInfo>",
						ESBAdditionalTag[i], "", "" });
			}

		// iterating payment req
		int rowCount = 2;
		for (Object[] data : paymentRequestInfo) {
			Row rows = spreadsheet1.createRow(rowCount++);
			int columnCount = 0;
			for (Object field : data) {
				spreadsheet1.autoSizeColumn(columnCount);
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
		int rowCount2 = spreadsheet1.getLastRowNum() + 1;
		{
			// int row2 = spreadsheet.getLastRowNum();
			row = spreadsheet1.createRow(rowCount2);
			Cell cell2 = row.createCell(0);
			cell2.setCellValue("Payment Response");
			CellStyle style3 = cell2.getSheet().getWorkbook().createCellStyle();
			style3.setAlignment(CellStyle.ALIGN_CENTER);
			style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style3.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			style3.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell2.setCellStyle(style3);

			// Create a cellRangeAddress to select a range to merge.
			CellRangeAddress cellRangeAddress3 = new CellRangeAddress(rowCount2, rowCount2, 0, 3);
			spreadsheet1.addMergedRegion(cellRangeAddress3);
		}

		// iterating payment resp
		int rowCount1 = spreadsheet1.getLastRowNum() + 1;
		for (Object[] data : paymentResponseInfo) {
			Row rows = spreadsheet1.createRow(rowCount1++);

			int columnCount = 0;
			for (Object field : data) {
				spreadsheet1.autoSizeColumn(columnCount);
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


		FileOutputStream out = new FileOutputStream(new File(FileUploadController.folderpath+FetchReqResp.filename+".xlsx"));
		
		row = spreadsheet1.createRow((short) 0);
		Cell cell = row.createCell((short) 0);
		cell.setCellValue("ESB to "+FetchReqResp.filename);
		CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);

		// Create a cellRangeAddress to select a range to merge.
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 3);

		row =spreadsheet1.createRow((short) 1);
		Cell cell1 = row.createCell((short) 0);
		cell1.setCellValue("Bill Payment Request");
		CellStyle style1 = cell1.getSheet().getWorkbook().createCellStyle();
		style1.setAlignment(CellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell1.setCellStyle(style1);
		CellRangeAddress cellRangeAddress1 = new CellRangeAddress(1, 1, 0, 3);
		Row row1 = spreadsheet1.getRow(3);
		CellStyle style2 = row1.getSheet().getWorkbook().createCellStyle();
		style2.setFillForegroundColor(IndexedColors.AQUA.getIndex());

		// Merge the selected cells.
		spreadsheet1.addMergedRegion(cellRangeAddress);
		spreadsheet1.addMergedRegion(cellRangeAddress1);
		workbook.write(out);
		out.close();
		
		System.out.println("payment created");

	}

}
