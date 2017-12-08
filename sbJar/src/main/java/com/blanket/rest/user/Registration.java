package com.blanket.rest.user;

import com.blanket.data.entity.User;
import com.blanket.service.UserService;
import com.blanket.service.impl.UserServiceImpl;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Path("/user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Registration {

    private static Logger LOGGER = LoggerFactory.getLogger(Registration.class);

    @Autowired
    private UserService userService;

    @Path("/checkUsername/{username}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean checkUsername(@PathParam("username") String username) {
        if (username == null) {
            LOGGER.error("parameter fail");
            throw new IllegalArgumentException("null");
        } else {
            return userService.accountWithUsernameExists(username);
        }

    }

    @Path("/addUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonIgnore
    public int addUser(User user) {
        return userService.addUser(user);
    }


}
