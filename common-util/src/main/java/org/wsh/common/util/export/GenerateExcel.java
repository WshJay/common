package org.wsh.common.util.export;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsh.common.util.export.base.ExportBase;

public class GenerateExcel {
	
	private final static Logger log = LoggerFactory.getLogger(GenerateExcel.class);

	/**
	 * 导出Excel
	 * @param data
	 * @param dataList
	 * @return
	 */
	public static XSSFWorkbook exportExcel(ExportBase data, List<? extends ExportBase> dataList) {
		//导出
		XSSFWorkbook wb = new XSSFWorkbook();//创建一个excel文件
		XSSFSheet sheet = wb.createSheet(data.getName());//创建一个工作簿
		//产生标题行
		String[] titles = data.getTitles();
		XSSFRow row = sheet.createRow(0);
		for(short i = 0; i < titles.length; i++){
			XSSFCell cell = row.createCell(i);
			XSSFRichTextString text = new XSSFRichTextString(titles[i]);
            cell.setCellValue(text);
		}
		//遍历集合数据，产生数据行
		for(int i = 0; i< dataList.size(); i++){
			XSSFRow row1 = sheet.createRow(i+1);
			Field[] fields = dataList.get(i).getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				XSSFCell cell = row1.createCell(j);
				fields[j].setAccessible(true); // 设置些属性是可以访问的
		    	try {
		    		XSSFRichTextString text = new XSSFRichTextString();
		    		if (fields[j].get(dataList.get(i)) != null) {
		    			text = new XSSFRichTextString(fields[j].get(dataList.get(i)).toString());
					}
					cell.setCellValue(text); 
				} catch (IllegalArgumentException e) {
					log.error("生成Excel数据异常",e);
				} catch (IllegalAccessException e) {
					log.error("生成Excel数据异常",e);
				}
			}
		}
		return wb;
	}
}

