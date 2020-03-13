package com.example.reportdashboard.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.reportdashboard.model.InboundOrder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateInboundOrderPDF {

	private static final Logger logger = LoggerFactory.getLogger(GenerateInboundOrderPDF.class);
	
	public static ByteArrayInputStream inboundOrderReport(List<InboundOrder> inboundOrderList) {
		
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            //table.setWidths(new int[]{1, 3, 3});

            //Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD);
            Font reportInfoFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font tableHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD);
            Font tableFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);
            
            PdfPCell hcell;
            
            //OrderId field
            hcell = new PdfPCell(new Phrase("Order Id", tableHeaderFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            //CreatedDate field
            hcell = new PdfPCell(new Phrase("Created Date", tableHeaderFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            //Type field
            hcell = new PdfPCell(new Phrase("Type", tableHeaderFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            //Zipcode field
            hcell = new PdfPCell(new Phrase("Location", tableHeaderFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            //CPU field
            hcell = new PdfPCell(new Phrase("CPU", tableHeaderFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            //Status field
            hcell = new PdfPCell(new Phrase("Status", tableHeaderFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            for (InboundOrder inboundOrder : inboundOrderList) {

                PdfPCell cell;
                
                //OrderId field
                cell = new PdfPCell(new Phrase(inboundOrder.getOrderId(), tableFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                //CreatedDate field
                cell = new PdfPCell(new Phrase(inboundOrder.getCreatedDate().toString(), tableFont));
                //cell.setPaddingLeft(5);            
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                //Type field
                cell = new PdfPCell(new Phrase(inboundOrder.getType(), tableFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell.setPaddingRight(5);
                table.addCell(cell);
                
                //Zipcode field
                cell = new PdfPCell(new Phrase(inboundOrder.getZipcode(), tableFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell.setPaddingRight(5);
                table.addCell(cell);
                
                //CPU field
                cell = new PdfPCell(new Phrase(inboundOrder.getCpu(), tableFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell.setPaddingRight(5);
                table.addCell(cell);
                
                //Status field
                cell = new PdfPCell(new Phrase(inboundOrder.getStatus(), tableFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            //Open the PDF document
            document.open();
            //Add Paragraph
            document.add(new Paragraph(" "));
            //Add Title
            document.add(new Paragraph("Inbound Orders Report", titleFont));
            //Add Paragraph
            document.add(new Paragraph(" "));
            //Add Paragraph
            document.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), reportInfoFont));
            //Add Paragraph
            document.add(new Paragraph(" "));
            //Add Table
            document.add(table);
            //Close the PDF document
            document.close();

		} catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());	
	}
}
