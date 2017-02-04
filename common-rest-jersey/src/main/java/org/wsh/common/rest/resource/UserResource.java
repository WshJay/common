package org.wsh.common.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.wsh.common.rest.constants.UserConstants;
import org.wsh.common.service.api.UserService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javafx.scene.input.KeyCode.R;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-13 16:27
 */
@Path(UserConstants.ROOT)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    Logger logger = LoggerFactory.getLogger(UserResource.class);

    /**
     * @return
     */
    @GET
    @Path("/test")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String doTest(@Context ServletContext servletContext) {
//        ApplicationContext ctx =
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        UserService userService = ctx.getBean("userService",UserService.class);
//        System.out.println(userService);
        return "123";
    }


}
