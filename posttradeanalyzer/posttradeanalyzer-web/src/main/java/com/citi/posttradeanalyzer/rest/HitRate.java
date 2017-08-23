package com.citi.posttradeanalyzer.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/hitrate")
public class HitRate {
	
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetIOC() {
    	
    	
    	//MockIOC IOC = new MockIOC("25", 12);
    	
    	ArrayList<String[]> IOC = new ArrayList<String[]>();
    	String[] data1 = {"2012-02-04 12:00","80"};
    	String[] data2 = {"2012-02-04 13:00","90"};
    	String[] data3 = {"2012-02-04 14:00","70"};
    	String[] data4 = {"2012-02-04 15:00","60"};
    	
    	IOC.add(data1);
    	IOC.add(data2);
    	IOC.add(data3);
    	IOC.add(data4);
    	
        ResponseBuilder rb= Response.ok(IOC);  
        return rb.build();        
    }
    
    
    
    
    
}
