package org.wsh.common.test.session;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.*;

//Session追踪

public class URLSession extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(true);

		out.println("<html><head><title>Session追踪</title></head>"
				+ "<BODY>\n"
				+ "session id:" + session.getId() + "<br>"
				+ "from url:" + request.isRequestedSessionIdFromURL() + "<br>"
				+ "from cookie:" + request.isRequestedSessionIdFromCookie() + "<br>"
				+ "<a href=" + response.encodeURL(request.getRequestURL().toString()) + ">test</a>"
				+ "</BODY></HTML>");

	}

	/** Handle GET and POST requests identically. */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
