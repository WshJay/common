package org.wsh.common.resource;

import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsh.common.constants.UserConstants;

import javax.ws.rs.*;
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
    public String doTest() {

        return "123";
    }


}
