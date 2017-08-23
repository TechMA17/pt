package com.citi.posttradeanalyzer.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/clienttradecost")
public class ClientTradeCost {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetAllData() {
    	
    	ArrayList<String[]> tradeCost = new ArrayList<String[]>();
    	String[] data1 = {"C3PO","3000"};
    	String[] data2 = {"68B3","1500"};
    	String[] data3 = {"R2D2","4500"};
    	String[] data4 = {"78Q1", "1000"};
    	String[] data5 = {"SLINE","500"};
    	String[] data6 = {"GERE", "500"};
 
    	tradeCost.add(data1);
    	tradeCost.add(data2);
    	tradeCost.add(data3);
    	tradeCost.add(data4);
    	tradeCost.add(data5);
    	tradeCost.add(data6);
    	
        ResponseBuilder rb= Response.ok(tradeCost);  
        return rb.build();        
    }
    
    @GET
    @Path("/commission")
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetAllComm() {
    	   	
    	ArrayList<String[]> Comms = new ArrayList<String[]>();
    	String[] data1 = {"2012-02-04 12:00","8000"};
    	String[] data2 = {"2012-02-04 13:00","19000"};
    	String[] data3 = {"2012-02-04 14:00","7000"};
    	String[] data4 = {"2012-02-04 15:00","6000"};
 
    	
    	Comms.add(data1);
    	Comms.add(data2);
    	Comms.add(data3);
    	Comms.add(data4);
    	
    	//orders.add(order);
        ResponseBuilder rb= Response.ok(Comms);  
        return rb.build();        
    }
    
    @GET
    @Path("/commissionpie")
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetAllCommPie() {
    	   	
    	ArrayList<String[]> Commspie = new ArrayList<String[]>();
    	String[] data1 = {"C3PO","30"};
    	String[] data2 = {"68B3","15"};
    	String[] data3 = {"R2D2","45"};
    	String[] data4 = {"78Q1", "10"};
    	String[] data5 = {"SLINE","5"};
    	String[] data6 = {"GERE", "5"};
   	 
    	Commspie.add(data1);
    	Commspie.add(data2);
    	Commspie.add(data3);
    	Commspie.add(data4);
    	Commspie.add(data5);
    	Commspie.add(data6);
    	
    	//orders.add(order);
        ResponseBuilder rb= Response.ok(Commspie);  
        return rb.build();        
    }
}
