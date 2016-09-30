package org.wsh.common.test.user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.wsh.common.util.date.DateUtil;


public class CreateAppUser {
	
	private static final String chars = "ABCDEF";

	
	public static void main(String[] args) {
		
//		for (int i = 0; i < 99847; i++) {
//			System.out.println(randomXlWeiBoOpenId());
//			AppUserInfo user = new AppUserInfo();
//			user.setOpen_id(randomTxQQOpenId());
//			user.setLogin_type(2);
//			user.setLast_login_time(DateUtil.randomDate("2015-01-02 06:00:00","2015-12-24 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
//			System.out.println(user.getInsertSql());
//		}
		
		/** 读取Excel文件 */
//		readXls("E:/1.xls");
//		String str = randomTxQQOpenId();
//		System.out.println(str);
		
//		try {
//			readTxtFile("E:/芯泰渠道微信号100W-1.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		for (int i = 1; i < 1693599; i++) {
			String updateSql = "UPDATE app_phone_user_info SET last_login_time = CONCAT('2015-12-25 ',LPAD(FLOOR(0 + (RAND() * 23)),2,0),':',LPAD(FLOOR(0 + (RAND() * 59)),2,0),':',LPAD(FLOOR(0 + (RAND() * 59)),2,0)) WHERE id = " + i;
			System.out.println(updateSql);
		}
		
		
	}
	
	
	public static List<AppUserInfo> readXls(String filePath){
		List<AppUserInfo> strList = new ArrayList<AppUserInfo>();
		try {
     	FileInputStream is = new FileInputStream(filePath);
		HSSFWorkbook wbs = new HSSFWorkbook(is);   
        HSSFSheet childSheet = wbs.getSheetAt(0);   
        for (int j = 1; j <= childSheet.getLastRowNum(); j++) { 
               HSSFRow row = childSheet.getRow(j);   
               if (null != row) { 
	              HSSFCell cell0 = row.getCell(0); 
	              HSSFCell cell1 = row.getCell(1);
	              HSSFCell cell2 = row.getCell(2);
	              StringBuffer buffer = new StringBuffer();
//	              if (cell0 != null) {
//	            	  buffer.append(cell0.getStringCellValue()).append(" ");
//	              }
	              if (cell0 != null && cell1 != null) {
	            	  if (StringUtils.isEmpty(cell0.getStringCellValue()) && !StringUtils.isEmpty(cell1.getStringCellValue())) {
	            		  AppUserInfo user = new AppUserInfo();
		            	  user.setMobile(cell1.getStringCellValue());
		            	  user.setLogin_type(0);
		            	  if (cell2 != null) {
		            		  user.setLast_login_time(DateUtil.randomDate("2015-01-02 06:00:00","2015-12-24 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
		            	  }
		            	  System.out.println(user.getInsertSql());
	            	  }
	              }
//	              if (cell2 != null) {
//	            	  buffer.append(cell2.getDateCellValue());
//	              }
//	              System.out.println(buffer.toString());
              }
          }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	              
		return strList;
	}
	
	/**
	 * 随机字符
	 * @param chars
	 * @return
	 */
	public static String randomABC(String chars){
		return String.valueOf(chars.charAt((int)(Math.random() * 6)));
	}
	
	public static int randomNumber(int num){
		Random random=new Random();
		int randomNum = random.nextInt(num);
		return randomNum;
	}
	/**
	 * 随机生成腾讯QQ的OpenId
	 * @return
	 */
	public static String randomTxQQOpenId(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			stringBuffer.append(randomABC(chars));
			stringBuffer.append(randomNumber(9));
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 随机生成新浪微博的OpenId
	 * @return
	 */
	public static String randomXlWeiBoOpenId(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				int n = randomNumber(5);
				if (n == 0) {
					stringBuffer.append(1);
				}else{
					stringBuffer.append(n);
				}
			}else{
				stringBuffer.append(randomNumber(9));
			}
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 随机生成腾讯QQ的OpenId
	 * @return
	 */
	public static String randomTxQQOpenId1(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 9; i++) {
			stringBuffer.append(randomABC(chars));
			stringBuffer.append(randomNumber(9));
			if (i == 2) {
				stringBuffer.append(randomABC(chars));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomABC(chars));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomABC(chars));
			}
			if (i == 5) {
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomNumber(9));
			}
			if (i == 6) {
				stringBuffer.append(randomABC(chars));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomNumber(9));
				stringBuffer.append(randomABC(chars));
			}
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 读取Csv文件 
	 * @param filePath
	 * @throws IOException
	 */
	public static void readCsvFile(String filePath) throws IOException{
		
		FileInputStream is = new FileInputStream(filePath);
		String line;
		// 将InputStream转换为BufferedReader
		InputStreamReader reader = new InputStreamReader(is, "gbk");// 考虑到编码格式
		BufferedReader bufread = new BufferedReader(reader);
		while ((line = bufread.readLine()) != null) {
			if (line.length() == 11) {
				AppUserInfo user = new AppUserInfo();
	          	user.setMobile(line);
	          	user.setLogin_type(0);
          		user.setLast_login_time(DateUtil.randomDate("2015-01-02 06:00:00","2015-12-24 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
	          	System.out.println(user.getInsertSql());
			}
		}
	}
	
	/**
	 * 读取TXT文件 
	 * @param filePath
	 * @throws IOException
	 */
	public static void readTxtFile(String filePath) throws IOException{
		
		FileInputStream is = new FileInputStream(filePath);
		String line;
		// 将InputStream转换为BufferedReader
		InputStreamReader reader = new InputStreamReader(is, "gbk");// 考虑到编码格式
		BufferedReader bufread = new BufferedReader(reader);
		int flage = 0;
		while ((line = bufread.readLine()) != null) {
			flage++;
			if (flage < 120000) {
				if (line.length() == 11) {
					AppUserInfo user = new AppUserInfo();
		          	user.setMobile(line);
		          	user.setLogin_type(0);
	          		user.setLast_login_time(DateUtil.randomDate("2015-01-02 06:00:00","2015-12-24 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
		          	System.out.println(user.getInsertSql());
				}
			}
		}
	}
}

