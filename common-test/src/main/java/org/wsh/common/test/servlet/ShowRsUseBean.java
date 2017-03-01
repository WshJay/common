package org.wsh.common.test.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

public class ShowRsUseBean extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();

		out.println("<table border=1>");
		out.println("<tr><td>Content:</td></tr>");

		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sql = "select * from t";
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getString("title") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DB.closeRs(rs);
			DB.closeStmt(stmt);
			DB.closeConn(conn);
		}

	}

}
