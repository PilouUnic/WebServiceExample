package fr.web.service.example.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class BasicWebServiceExample {

    @Autowired
    private TransactionService transactionService;

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") final String msg) {
        final String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();

    }

    @GET
    @Path("/test/{param}")
    public Response getChristopheStatus(@PathParam("param") final String msg) {
        final String output = "Christophe is NOOB : " + msg;
        return Response.status(200).entity(output).build();

    }

    @GET
    @Path("/callTransaction")
    public Response callTransaction() {
        final String output = transactionService.save();
        return Response.status(200).entity(output).build();

    }
}
