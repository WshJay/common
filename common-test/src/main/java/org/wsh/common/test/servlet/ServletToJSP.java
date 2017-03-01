package org.wsh.common.test.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServletToJSP extends HttpServlet {
	public void doGet (HttpServletRequest req,HttpServletResponse resp) {
		try {
			// 设置属性并将它提交给ServletUseJsp.jsp
			resp.setContentType("text/html;charset=gb2312");
			req.setAttribute ("servletName", "ServletToJsp");
			//forward
			//getServletConfig().getServletContext().getRequestDispatcher("/servlet_jsp/ServletUseJsp.jsp").forward(req, resp);
			//sendredirect
			System.out.println(req.getContextPath());
			resp.sendRedirect(req.getContextPath() + "/servlet_jsp/ServletUseJsp.jsp");
		}catch (Exception e){
			e.printStackTrace ();
		}
	}
}
