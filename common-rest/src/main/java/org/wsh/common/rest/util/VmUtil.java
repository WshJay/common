package org.wsh.common.rest.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.service.api.MenuService;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;


public class VmUtil {
	
	@Resource
	private MenuService menuService;
	
	/**
	 * 求余，如果有余数为false，没有余数为true
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @return
	 */
	public int complementation(int divisor, int dividend){
		return divisor%dividend;
	}
	
	public String loaderVelocity(){
		//初始化参数
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine=new VelocityEngine(properties);
        
        //实例化一个VelocityContext
        VelocityContext context=new VelocityContext();
        
        Subject subject = SecurityUtils.getSubject();
		String userName = (String)subject.getPrincipal();
		System.out.println(menuService);
		List<MenuDO> menuList = menuService.getMenuListByUserName(userName);
		context.put("menuList", menuList);
        //实例化一个StringWriter
        StringWriter writer=new StringWriter();
        
        //从src目录下加载hello.vm模板
        //假若在com.velocity.test包下有一个hello.vm文件,那么加载路径为com/velocity/test/hello.vm
        velocityEngine.mergeTemplate("menu.vm", "gbk", context, writer);
        
        //velocityEngine.mergeTemplate("hello.vm", "gbk", context, writer);
        System.out.println(writer.toString());
        return writer.toString();
		
	}
	
}
