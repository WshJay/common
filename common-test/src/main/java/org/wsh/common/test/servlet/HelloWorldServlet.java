package org.wsh.common.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  初步認識Servlet
 * since Date： 2017/3/1 21:19
 */
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body><center><font color='red'>HelloWorldServlet!</font></center></body></html>");
		out.flush();
		out.close();
	}
}
