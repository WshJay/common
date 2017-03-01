package org.wsh.common.test.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
//1.仔细检查连接串。
//2.确认有没有这个表
//3.如果出错了。先去后台看报错的信息。
//4.确认mysql已经启动了。
//5.把jdbc的驱动拷贝到tomcat
public class ShowRs extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();
		
		out.println("<table border=1>");
		out.println("<tr><td>Content:</td></tr>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bbs?user=root&password=bjsxt");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from article"); 
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getString("cont") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt= null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
