package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/json/feedback")
public class JSONService {

	@GET
	@Path("/get")
	@Produces("application/json")
	public Object getFeedBackInJSON() {

		ObjectToJSON obj = new ObjectToJSON();
		Object fd = obj.readFeedbackFromJsonFile();
		return Response.status(200).entity(fd.toString()).build();

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createFeedBackInJSON(Feedback fd) {

		String result = "Feedback created : " + fd;
		
		ObjectToJSON obj = new ObjectToJSON();
		//boolean input = obj.writeFeedbackToJsonFile(fd);
		obj.appendObjToJSON(fd);
		return Response.status(201).entity(result).build();
		
	}
	
}