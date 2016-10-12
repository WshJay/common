package org.wsh.common.rest.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.ViewContext;

import javax.servlet.http.HttpServletRequest;

public class ResourceTool {

	private HttpServletRequest request;
	private VelocityContext velocity;

	/*
	 * Initialize toolbox
	 * 
	 * @see org.apache.velocity.tools.view.tools.ViewTool#init(java.lang.Object)
	 */
	public void init(Object arg0) {
		// scope: request or session
		if (arg0 instanceof ViewContext) {
			ViewContext viewContext = (ViewContext) arg0;
			request = viewContext.getRequest();
			velocity = (VelocityContext) viewContext.getVelocityContext();
		}
	}

	public String this_vm() {
		return velocity.getCurrentTemplateName();
	}
}