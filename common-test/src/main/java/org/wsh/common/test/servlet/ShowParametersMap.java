package org.wsh.common.test.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//获取所有的参数

public class ShowParametersMap extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		String title = "Reading All Request Parameters";
		out.println("<html><head><title>��ȡ���в���</title></head>"
				+ "<BODY BGCOLOR='#FDF5E6'>\n" + "<H1 ALIGN=CENTER>" + title
				+ "</H1>\n" + "<TABLE BORDER=1 ALIGN=CENTER>\n"
				+ "<TR BGCOLOR=\"#FFAD00\">\n"
				+ "<TH>Parameter Name<TH>Parameter Value(s)");
		Map<String, String[]> paramMap = request.getParameterMap();
		Set<Map.Entry<String, String[]>> entries = paramMap.entrySet(); 
		for(Iterator<Map.Entry<String, String[]>> it = entries.iterator(); it.hasNext(); ) {
			Map.Entry<String, String[]> entry = it.next();
			String paramName = entry.getKey();
			out.print("<TR><TD>" + paramName + "\n<TD>");
			String[] paramValues = entry.getValue();
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("<I>No Value</I>");
				else
					out.println(paramValue);
			} else {
				out.println("<UL>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println("<LI>" + paramValues[i]);
				}
				out.println("</UL>");
			}
		}
		out.println("</TABLE>\n</BODY></HTML>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
