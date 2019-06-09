package cn.itcast.store.web.servlet;

import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;

import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;

public class ExportExcel {
	
	public void exportExcelFile(List<Product> resources, OutputStream out)
			throws IOException{
		// 第一步，创建一个webbook，对应一个Excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workbook.createSheet("sheet1");
		
		// 列宽
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		
		// 第三步，在sheet中添加表头第0行
		XSSFRow headerRow = sheet.createRow(0);
		// 表头样式
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 表头字体
		XSSFFont headerFont = workbook.createFont();
		headerFont.setColor(HSSFColor.VIOLET.index);
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontName("宋体");
		
		headerStyle.setFont(headerFont);
		
		
	    XSSFCell cell = null;
		cell = headerRow.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(headerStyle);

		cell = headerRow.createCell(1);
		cell.setCellValue("商品名称");
		cell.setCellStyle(headerStyle);

		cell = headerRow.createCell(2);
		cell.setCellValue("市场价格");
		cell.setCellStyle(headerStyle);

		cell = headerRow.createCell(3);
		cell.setCellValue("商城价格");
		cell.setCellStyle(headerStyle);
		
		cell = headerRow.createCell(4);
		cell.setCellValue("商品图片");
		cell.setCellStyle(headerStyle);

		cell = headerRow.createCell(5);
		cell.setCellValue("日期");
		cell.setCellStyle(headerStyle);

		cell = headerRow.createCell(6);
		cell.setCellValue("是否热门");
		cell.setCellStyle(headerStyle);
		
		cell = headerRow.createCell(7);
		cell.setCellValue("描述");
		cell.setCellStyle(headerStyle);
		
		cell = headerRow.createCell(8);
		cell.setCellValue("所属种类");
		cell.setCellStyle(headerStyle);
		
		
		// 表体样式
		XSSFCellStyle bodyStyle = workbook.createCellStyle();
		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		bodyStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 表体字体
		XSSFFont bodyFont = workbook.createFont();
		bodyFont.setColor(HSSFColor.BLACK.index);
		bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		bodyFont.setFontName("宋体");
		
		bodyStyle.setFont(bodyFont);
		
		// 声明一个画图的顶级管理器
				XSSFDrawing patriarch = sheet.createDrawingPatriarch();
				
		
		if(resources != null && resources.size() > 0){
			XSSFRow bodyRow = null;
			XSSFCell bodyCell = null;
			
			
			for(int i = 0; i < resources.size(); i++){
				//headerRow =sheet.createRow(i);
				bodyRow = sheet.createRow(i+1);
				
				Product p=resources.get(i);
				bodyCell = bodyRow.createCell(0);
				bodyCell.setCellValue(p.getPid());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(1);
				bodyCell.setCellValue(p.getPname());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(2);
				bodyCell.setCellValue(p.getMarket_price());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(3);
				bodyCell.setCellValue(p.getShop_price());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(4);
				bodyCell.setCellValue(p.getPimage());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(5);
				bodyCell.setCellValue(p.getPdate());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(6);
				bodyCell.setCellValue(p.getIs_hot());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(7);
				bodyCell.setCellValue(p.getPdesc());
				bodyCell.setCellStyle(bodyStyle);
				
				bodyCell = bodyRow.createCell(8);
				bodyCell.setCellValue(p.getCid());
				bodyCell.setCellStyle(bodyStyle);
			}
		}
		// 第六步，存文件
					workbook.write(out);

		}
}









