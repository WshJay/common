package org.wsh.common.rest.controller;

import org.springframework.stereotype.Controller;
import org.wsh.common.rest.response.Helloworld;
import org.wsh.common.service.api.UserService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-18 15:27
 */
@Controller
@Path("/")
public class LoginController extends LoggerService{

    @Resource
    private UserService userService;

    @GET
    @Path("login")
    @Produces("application/json")
    public ResponseDO toLogin() throws Exception {
        System.out.println("Welcome, HelloWorld...");
        return new ResponseDO();
    }

    @POST
    @Path("login")
    @Produces("application/json")
    public ResponseDO login() throws Exception {
        System.out.println("Welcome, HelloWorld...");
        return new ResponseDO();
    }

}
