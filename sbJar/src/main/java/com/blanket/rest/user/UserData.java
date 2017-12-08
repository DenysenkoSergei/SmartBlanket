package com.blanket.rest.user;

import com.blanket.data.entity.User;
import com.blanket.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/userdata")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    @Autowired
    UserDataService userDataService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @Autowired
    BlanketService blanketService;

    @Autowired
    BlanketCommandService blanketCommandService;

    @Autowired
    private StatisticsProviderService statisticsProviderService;

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGeneralUserData(
            @HeaderParam("Username") String username,
            @HeaderParam("Password") String password) {
        if (securityService.checkAccessRights(username, password)) {
            User curUser = userDataService.getGeneralUserInfo(username);

            try {
                ObjectMapper om = new ObjectMapper();
                return om.writeValueAsString(curUser);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                Response.status(Response.Status.INTERNAL_SERVER_ERROR);
                return "Error";
            }

        } else {
            return "Access Denied!";
        }
    }

    @Path("/currentstatus/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCurrentBlanketStatus(
            @HeaderParam("Username") String username,
            @HeaderParam("Password") String password,
            @PathParam("id") int blanketId) throws JsonProcessingException {
        if (securityService.checkAccessRights(username, password)) {
           ObjectMapper om = new ObjectMapper();
           return om.writeValueAsString(statisticsProviderService.getCurrentStatics(blanketId));
        } else {
            return "Access Denied!";
        }
    }

    @Path("/allstatuses/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBlanketStatuses(
            @HeaderParam("Username") String username,
            @HeaderParam("Password") String password,
            @PathParam("id") int blanketId) throws JsonProcessingException {
        if (securityService.checkAccessRights(username, password)) {
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(statisticsProviderService.getOverallDailyStatistics(blanketId));
        } else {
            return "Access Denied!";
        }
    }

}
