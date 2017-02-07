package org.wsh.common.rest.controller;

import org.springframework.stereotype.Controller;
import org.wsh.common.consumer.service.DemoService;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.system.SystemDO;
import org.wsh.common.rest.controller.request.Article;
import org.wsh.common.rest.response.Helloworld;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.service.api.SystemService;
import org.wsh.common.support.response.ResponseDO;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Controller
@Path("/hello")
public class HomeController {

	@Resource
	private RoleService roleService;

	@Resource
	private DemoService demoService;

	@Resource
	private SystemService systemService;

	@GET
	@Path("/index")
	@Produces("application/json")
	public ResponseDO home() throws Exception {
		try {
			ResponseDO<SystemDO> responseDO = null;
			responseDO = systemService.getSystemDO();
			return responseDO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/world")
	@Produces("application/json")
	public Helloworld helloworld() throws Exception {
		System.out.println("Welcome, HelloWorld...");
		return new Helloworld("Welcome, HelloWorld");
	}

	@GET
	@Path("/test")
	@Produces("application/json")
	public Helloworld test() throws Exception {
		String result = demoService.sayHello();
		System.out.println(result);
		List<RoleDO> roleDOList = roleService.getAllRole();
		for (RoleDO roleDO : roleDOList) {
			System.out.println(roleDO.toString());
		}
		return new Helloworld("Welcome, HelloWorld");
	}

	@GET
	@Path("/auth")
	@Produces("application/json")
	public Helloworld auth(@Context SecurityContext context) {
		return new Helloworld(context.getUserPrincipal().getName());
	}

    //TODO 测试中，此处报错，可能是参数转换为Article实例出错
//	@POST
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Article save(Article article) {
//		return article;
//	}

    /**
     * 因为自动转换为实例出错，所有直接获取参数，再创建实例
     * @param name
     * @param id
     * @return
     */
    @POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Article save(@FormParam("name") String name,
						@FormParam("id") int id) {
		return new Article(id, name);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Article> save(
			@QueryParam("multi") boolean isMulti,
			List<Article> articles) {
		return articles;
	}

}
