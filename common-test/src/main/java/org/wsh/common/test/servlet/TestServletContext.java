package org.wsh.common.test.servlet;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.util.Date;

public class TestServletContext extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		
		ServletContext application = this.getServletContext();
		
		
		Integer accessCount = (Integer) application.getAttribute("accessCount");
		if (accessCount == null) {
			accessCount = new Integer(0);
			
		} else {
			accessCount = new Integer(accessCount.intValue() + 1);
		}
		// Use setAttribute instead of putValue in version 2.2.
		application.setAttribute("accessCount", accessCount);

		out.println("<html><head><title>Session追踪</title></head>"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">"
				+ accessCount + "\n" + "</TABLE>\n" + "</BODY></HTML>"
				+ "</H1>\n");
				

	}

}
