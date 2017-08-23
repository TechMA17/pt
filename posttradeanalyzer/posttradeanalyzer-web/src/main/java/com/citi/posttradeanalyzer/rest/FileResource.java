package com.citi.posttradeanalyzer.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class FileResource {	

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTrade(String path) {
        // Create a URI object, representing the partial URL for the newly inserted object
        URI uri = null;
        try {
            uri = new URI("rest/admin/" + path);
        } catch (URISyntaxException ex) {}

        // Build and return an HTTP response. Note the following points:
        //   - The created() method implicitly sets the HTTP status code to 201.
        //   - The created() method takes a parameter that indicates the URI of the newly inserted Product object.
        //   - The entity() method returns the inserted Product object (JAX-RS will automatically serialize as XML or JSON).
        //
        // Note, Response.created(uri) is equivalent to Response.status(Status.CREATED).location(uri).
        return Response.created(uri)
                       .entity(path)
                       .build();        
    }
}
