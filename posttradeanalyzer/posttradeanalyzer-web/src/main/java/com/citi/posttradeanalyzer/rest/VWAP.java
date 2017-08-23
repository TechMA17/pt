package com.citi.posttradeanalyzer.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/vwap")
public class VWAP {
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetCWAP() {
    	
    	ArrayList<String[]> VWAP = new ArrayList<String[]>();
    	String[] data1 = {"2012-02-04 12:00","80", "85"};
    	String[] data2 = {"2012-02-04 13:00","90", "85"};
    	String[] data3 = {"2012-02-04 14:00","70", "65"};
    	String[] data4 = {"2012-02-04 15:00","60", "65"};
 
    	
    	VWAP.add(data1);
    	VWAP.add(data2);
    	VWAP.add(data3);
    	VWAP.add(data4);
    	
        ResponseBuilder rb= Response.ok(VWAP);  
        return rb.build();        
    }
    
    @GET
    @Path("/option")
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetOption() {
    	String[] stocks = {"APPL","GOOG","BARC"};
    	
        ResponseBuilder rb= Response.ok(stocks);  
        return rb.build();  
    }
    
    
    //@PathParam("stock") String stock
    @GET
    @Path("select/{query}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response doPostOption(@PathParam("query") String stock) {
    	
    	ArrayList<String[]> VWAP = new ArrayList<String[]>();
    	/*
    	String[] data1 = {"2012-02-04 12:00","80", "85"};
    	String[] data2 = {"2012-02-04 13:00","90", "85"};
    	String[] data3 = {"2012-02-04 14:00","70", "65"};
    	String[] data4 = {"2012-02-04 15:00","60", "65"};
    	VWAP.add(data1);
    	VWAP.add(data2);
    	VWAP.add(data3);
    	VWAP.add(data4);*/
    	//String stockname = stock;
    	
    	
    	switch(stock) {
        case "APPL" :
        	String[] data1 = {"2012-02-04 12:00","80", "85"};
        	String[] data2 = {"2012-02-04 13:00","90", "85"};
        	String[] data3 = {"2012-02-04 14:00","70", "65"};
        	String[] data4 = {"2012-02-04 15:00","60", "65"};
        	VWAP.add(data1);
        	VWAP.add(data2);
        	VWAP.add(data3);
        	VWAP.add(data4);
           break;
        case "GOOG" :
        	String[] data5 = {"2012-02-04 12:00","60", "25"};
        	String[] data6 = {"2012-02-04 13:00","100", "25"};
        	String[] data7 = {"2012-02-04 14:00","30", "65"};
        	String[] data8 = {"2012-02-04 15:00","60", "65"};
        	VWAP.add(data5);
        	VWAP.add(data6);
        	VWAP.add(data7);
        	VWAP.add(data8);
        	break;
        case "BARC" :
        	String[] data9 = {"2012-02-04 12:00","80", "85"};
        	String[] data10 = {"2012-02-04 13:00","10", "85"};
        	String[] data11 = {"2012-02-04 14:00","70", "65"};
        	String[] data12 = {"2012-02-04 15:00","160", "65"};
        	VWAP.add(data9);
        	VWAP.add(data10);
        	VWAP.add(data11);
        	VWAP.add(data12);
           break;
        case "default" :
        	System.out.println("Please Select");
        	break;
        default :
           System.out.println("Invalid grade");
           break;
    	}
	
        ResponseBuilder rb= Response.ok(VWAP);  
        return rb.build();    
    
    }

}


