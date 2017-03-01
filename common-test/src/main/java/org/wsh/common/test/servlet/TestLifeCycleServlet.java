package org.wsh.common.test.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestLifeCycleServlet extends HttpServlet {
	
	public TestLifeCycleServlet() {
		System.out.println("Constructor!");
	}
	
	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("doGet!");
	}

	@Override
	public void destroy() {
		System.out.println("destory!");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}

}
