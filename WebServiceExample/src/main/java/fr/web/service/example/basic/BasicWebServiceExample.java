package fr.web.service.example.basic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.web.service.example.basic.exception.ApplicationException;

@Component
@Path("/hello")
public class BasicWebServiceExample {

	@Autowired
	private TransactionService transactionService;

	@GET
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public User getMsg() {
		User user = new User("Aurélien", "Cortial");
		return user;
	}

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

	@POST
	@Path("/json")
	public Response jsonExample(InputStream incomingData) {
		StringBuilder crunchifyBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + crunchifyBuilder.toString());

		// return HTTP response 200 in case of success
		return Response.status(200).entity(crunchifyBuilder.toString()).build();
	}

	@Path("/testid/{id}")
	@GET
	public Response getUserBId  ( @PathParam("id") String id ) throws ApplicationException
	{
		//validate mandatory field
		if(id == null) {
			throw new ApplicationException("id is not present in request !!");
		}
		//Validate proper format
		try {
			Integer.parseInt(id);
		}
		catch(NumberFormatException e) {
			throw new ApplicationException("id is not a number !!");
		}
		//Process the request
		return Response.status(200).entity("User with ID " + id + " found !!").build();
	}

}
