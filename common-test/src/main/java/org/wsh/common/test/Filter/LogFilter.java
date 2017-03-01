package org.wsh.common.test.Filter;

//该实例演示记录客户端主机信息的Filter
//Filter的意思是可以在Servlet实例进行实质性的处理之前，起到“过滤”的作用.
//一个Filter可以过滤一个或多个Servlet，
//Filter必须实现javax.Servlet.Filter接口，
//并且必须定义以下三个方法:init(),destory(),doFilter().
//web.xml添加如下内容
//	<filter>
//		<filter-name>LogFilter</filter-name>
//		<filter-class>LogFilter</filter-class>
//	</filter>
//	<filter-mapping>
//		<filter-name>LogFilter</filter-name>
//		<url-pattern>/*</url-pattern>
//	</filter-mapping>
//可以在tomcat  目录的log目录下的localhost.2006-01-02.log看到以下消息:
//2006-1-2  16:25:51  org.apache.catalina.core.ApplicationContext  log
//信息:  [127.0.0.1]  request:  /Filter/
//2006-1-2  16:25:51  org.apache.catalina.core.ApplicationContext  log
//信息:  [127.0.0.1]  done
//这表明logFilter已经被server调用成功.
//当然在实际应用中filter还有其他用处,象登陆验证，编码转化等.

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
	private ServletContext context;

	public void init(FilterConfig arg0) throws ServletException {
		context = arg0.getServletContext();
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
						 FilterChain arg2) throws IOException, ServletException {
		context.log("[" + arg0.getRemoteHost() + "] request: "
				+ ((HttpServletRequest) arg0).getRequestURI());
		arg2.doFilter(arg0, arg1);
		context.log("[" + arg0.getRemoteHost() + "] done");
	}

	public void destroy() {
		context = null;
	}

}