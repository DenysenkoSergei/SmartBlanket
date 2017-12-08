package com.blanket.rest.user;

import com.blanket.data.entity.User;
import com.blanket.service.UserService;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Service
@Path("/user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authorization {

    @Autowired
    private UserService userService;

    @Path("/authorize")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String authorize(User user) {
        return userService.checkUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
