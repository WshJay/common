package com.xxx.common.test.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.commons.lang3.StringUtils;

import com.mysql.jdbc.Statement;


public class JdbcPreparedStatement {
	
	public static void main(String[] args) {

	try{

		//加载驱动程序

		//下面的代码为加载JDBD-ODBC驱动程序

		Class.forName("com.mysql.jdbc.Driver");

		//建立连接

		//第二步是用适当的驱动程序连接到DBMS，看下面的代码[自行修改您所连接的数据库相关信息]：
		String url="jdbc:mysql://localhost:3306/test";

		String user = "root";

		String password = "root";
		
		//用url创建连接

		Connection con=DriverManager.getConnection(url,user,password);


		//当前的表中有如下几个字段：ID,NAME,PASSWORD,TEXT,NOTE
		
//		PreparedStatement insertStatement = con.prepareStatement(
//		"INSERT INTO test values(?,?)");
//		insertStatement.setString(3,"88888");
//		
//		insertStatement.setString(4,"这是个测试的应用程序");
//		
//		insertStatement.setString(5,"备注");
		
//		int result = insertStatement.executeUpdate();
//		
//		System.out.println("the result is" + result);
		
//		java.sql.Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("select * from mds_members_mb");
//		while(rs.next()) {
//		   int mbId = rs.getInt("Mb_id");
//		   String MbPassword = rs.getString("Mb_Password");
//		   String payPassword = rs.getString("apypasswd");
//		   if (StringUtils.isBlank(payPassword)) {
//			   System.out.println("update mds_members_mb set Mb_Password ='" + MD5.getMD5Str(MbPassword) + "' where Mb_id =" + mbId + ";");
//		   }else{
//			   System.out.println("update mds_members_mb set Mb_Password ='" + MD5.getMD5Str(MbPassword) + "', apypasswd ='" + MD5.getMD5Str(payPassword) + "' where Mb_id =" + mbId + ";");
//		   }
//		}
		
		System.out.println(MD5.getMD5Str("easwift2015"));
		con.close();
		
	} catch (Exception e){
		
		//输出异常信息
		
		System.err.println("SQLException :"+e.getMessage());
		
		e.printStackTrace();
		
	}
	
	}

}
