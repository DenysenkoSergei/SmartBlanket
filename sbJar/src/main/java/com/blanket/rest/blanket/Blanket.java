package com.blanket.rest.blanket;

import com.blanket.data.entity.BlanketStatus;
import com.blanket.service.BlanketCommandService;
import com.blanket.service.BlanketService;
import com.blanket.service.SecurityService;
import com.blanket.service.impl.BlanketCommandServiceImpl;
import com.blanket.service.impl.BlanketServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Path("/blanket")
public class Blanket {

    @Autowired
    BlanketService blanketService;

    @Autowired
    BlanketCommandService blanketCommandService;

    @Autowired
    SecurityService securityService;

    @Path("/get/{id}")
    @GET
    public int getBlanketStatus(@PathParam("id") int id) {
        return blanketService.getBlanketById(id).getBlanketStatusesById().size();
    }

    @Path("/getKey/{key}")
    @GET
    public int getBlanketId(@PathParam("key") String key) {
        return blanketService.getBlanketByKey(key).getId();
    }

    @Path("/persist")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonIgnore
    public int persistBlanket(BlanketStatus blanketStatus) {
        return blanketService.persistBlanketStatus(blanketStatus);
    }

    @Path("/command/{id}")
    @GET
    public String getBlanketRequiredStatus(@PathParam("id") int id) {
        try {
            return new ObjectMapper().writeValueAsString(blanketCommandService.getLastStatus(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Path("/addBlanket/{id}/{serial}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addBlanket(
            @HeaderParam("Username") String username,
            @HeaderParam("Password") String password,
            @PathParam("id") int userId,
            @PathParam("serial") String serialKey) {

        return securityService.checkAccessRights(username, password) && blanketService.addBlanketOwner(userId, serialKey);
    }

    @Path("/removeBlanket/{serial}")
    @POST
    public boolean removeBlanket(
            @HeaderParam("Username") String username,
            @HeaderParam("Password") String password,
            @PathParam("serial") String serialKey) {

        return securityService.checkAccessRights(username, password) && blanketService.addBlanketOwner(null, serialKey);
    }

}
