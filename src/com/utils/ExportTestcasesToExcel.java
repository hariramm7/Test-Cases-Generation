package com.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportTestcasesToExcel {

	public void export(ArrayList<String> header, List<ArrayList> data,String sheetName,String fileName) {
		// TODO Auto-generated method stub

		try {
			Workbook wb = new HSSFWorkbook();
			Map<String, CellStyle> styles = createStyles(wb);

			Sheet sheet = wb.createSheet(sheetName);
			PrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setLandscape(true);
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);

			// header row
			Row headerRow = sheet.createRow(0);
			headerRow.setHeightInPoints(20);
			Cell headerCell;
			for (int i = 0; i < header.size(); i++) {
				headerCell = headerRow.createCell(i);
				headerCell.setCellValue(header.get(i));
				headerCell.setCellStyle(styles.get("header"));
			}

			int rownum = 1;
			for (int i = 0; i < data.size(); i++) {
				Row row = sheet.createRow(rownum++);
				row.setHeightInPoints(15);
				
				ArrayList<String> rowData = data.get(i);

				if(rowData != null){
				for (int j = 0; j < rowData.size(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(rowData.get(j));
					cell.setCellStyle(styles.get("cell"));
				}
				}
			}
			
			//set column width
			
			for (int i = 0; i < header.size(); i++) {
				sheet.setColumnWidth(i, (header.get(i).length()+5) * 256); 
			}
			
			// Write the output to a file
			if (wb instanceof XSSFWorkbook)
				fileName += "x";
			FileOutputStream out = new FileOutputStream(fileName);
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 18);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(titleFont);
		styles.put("title", style);

		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 11);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		//style.setWrapText(true);
		styles.put("header", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_LEFT);
		//style.setWrapText(true);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styles.put("cell", style);

		return styles;
	}

}
