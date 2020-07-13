package com.employee.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;

public class ExcelTool {
	public List<T> importExcel(Class<T> type,InputStream is) throws IllegalAccessException, InvocationTargetException, InstantiationException{
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.getSheetAt(0);
		int rows=sheet.getPhysicalNumberOfRows();//获取文件所有行数
		HSSFRow titleRow=null;
		int r=0;
		for(;r<rows;r++) {
			HSSFRow row=sheet.getRow(r);
			if(row!=null) {
				titleRow=row;
				break;
			}
		}
		List<T> list=new LinkedList<T>();
		//格式化存储文本
		DecimalFormat df=new DecimalFormat("0");
		r++;
		//遍历行
		for(;r<rows;r++) {
			HSSFRow row=sheet.getRow(r);
			if(row!=null) {
				T obj =type.newInstance();
				int cells=row.getPhysicalNumberOfCells();
				//遍历列
				for(int j=0;j<cells;j++) {
					HSSFCell cell=row.getCell(j);
					if(cell!=null) {
						String name=titleRow.getCell(j).getStringCellValue();
						String value="";
						switch(cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							value=df.format(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value=cell.getStringCellValue();
							break;
						default:
							value="";
							break;
						}
						BeanUtils.setProperty(obj, name, value);
					}
				}
				list.add(obj);
			}
		}
		return list;
	}
	public<T> void exportExcel(String[] headers,String[] dataHeaders,List<T> list,OutputStream out) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		HSSFWorkbook workbook=new HSSFWorkbook();
		//生成表格
		HSSFSheet sheet=workbook.createSheet();
		//生成样式
		HSSFCellStyle headerStyle=workbook.createCellStyle();
		//设置样式
		headerStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		//把字体应用到当前样式
		headerStyle.setFont(font);
		//产生表格标题行
		HSSFRow row=sheet.createRow(0);
		for(int i=0;i<headers.length;i++) {
			HSSFCell cell=row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(headers[i]);
		}
		//遍历集合数据产生数据行
		int index=0;
		for(T t:list) {
			index++;
			row=sheet.createRow(index);
			for(int i=0;i<dataHeaders.length;i++) {
				HSSFCell cell=row.createCell(i);
				String value=BeanUtils.getProperty(t, dataHeaders[i]);
				cell.setCellValue(value);
			}
		}
		try {
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void exportMapExcel(String[] headers,List<Map<String,Object>> list,OutputStream out) {
		HSSFWorkbook workbook=new HSSFWorkbook();
		//生成表格
		HSSFSheet sheet=workbook.createSheet();
		//生成样式
		HSSFCellStyle headerStyle=workbook.createCellStyle();
		//设置样式
		headerStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		//把字体应用到当前样式
		headerStyle.setFont(font);
		//产生表格标题行
		HSSFRow row=sheet.createRow(0);
		for(int i=0;i<headers.length;i++) {
			HSSFCell cell=row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(headers[i]);
		}
		int index=0;
		for(Map<String,Object> map:list) {
			index++;
			row=sheet.createRow(index);
			Set<Entry<String,Object>> set=map.entrySet();
			Iterator<Entry<String,Object>> it=set.iterator();
			int i=0;
			while(it.hasNext()) {
				Entry<String,Object> next =it.next();
				Object value=next.getValue();
				String key=next.getKey();
				if(!key.contains("escoreid")) {
					HSSFCell cell=row.createCell(i++);
					if(value instanceof String) {
						String str=(String)value;
						cell.setCellValue(str);
					}else {
						Integer score=(Integer)value;
						cell.setCellValue(score.intValue());
					}
				}
			}
		}
		try {
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
