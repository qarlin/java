package com.hatumruna.taskmanager.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

@Controller
@Path("/Hello")
public class HelloRest {

	@GET
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public Response sayTextHello() {
        return Response.ok("Hello World").build();
    }
}
