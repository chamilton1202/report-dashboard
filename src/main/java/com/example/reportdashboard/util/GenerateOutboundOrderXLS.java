package com.example.reportdashboard.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.reportdashboard.model.OutboundOrder;

public class GenerateOutboundOrderXLS {

	private static final Logger logger = LoggerFactory.getLogger(GenerateOutboundOrderXLS.class);

	public static ByteArrayInputStream outboundOrderReport(List<OutboundOrder> outboundOrderList) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			// create excel xls sheet
			XSSFSheet sheet = workbook.createSheet("Outbound Orders");
			sheet.setDefaultColumnWidth(30);

			// create style for header cells
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			font.setBold(true);
			font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
			style.setFont(font);

			// create header row
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Order Id");
			header.getCell(0).setCellStyle(style);
			header.createCell(1).setCellValue("Created Date");
			header.getCell(1).setCellStyle(style);
			header.createCell(2).setCellValue("Type");
			header.getCell(2).setCellStyle(style);
			header.createCell(3).setCellValue("CPU");
			header.getCell(3).setCellStyle(style);
			header.createCell(4).setCellValue("Status");
			header.getCell(4).setCellStyle(style);

			int rowCount = 1;

			for (OutboundOrder outboundOrder : outboundOrderList) {
				Row userRow = sheet.createRow(rowCount++);
				userRow.createCell(0).setCellValue(outboundOrder.getOrderId());
				userRow.createCell(1).setCellValue(outboundOrder.getCreatedDate().toString());
				userRow.createCell(2).setCellValue(outboundOrder.getType());
				userRow.createCell(3).setCellValue(outboundOrder.getCpu());
				userRow.createCell(4).setCellValue(outboundOrder.getStatus());
			}

			workbook.write(out);
			workbook.close();

		} catch (Exception ex) {
			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
