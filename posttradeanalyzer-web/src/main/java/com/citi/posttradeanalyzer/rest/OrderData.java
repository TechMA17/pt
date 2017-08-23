package com.citi.posttradeanalyzer.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/orderdata")
public class OrderData {
	
	//String sendingTime, long clOrdID, String account, int ordQty, String ordType, long sharePrice,
	//int buySell, String stockSymbol, String timeInForce
	
	 @GET
	 @Path("/single")
	 @Produces({MediaType.APPLICATION_JSON})
	 public Response doGetData() {
		 
	    MockOrderData order5 = new MockOrderData("201708171451",12397,"2erew324",110, "Limit", (long) 20.50, 1, "GOOG", "IOC");
	    ResponseBuilder rs= Response.ok(order5);  
	    return rs.build();        
	    }
	
	 
	
	 @POST
	 @Path("/select")
	 @Consumes("application/x-www-form-urlencoded") 
	 @Produces("text/plain")
	 public Response doSelectOrder(@FormParam("id") int id) {
	    	
	    	List<MockOrderData> orders = new ArrayList<MockOrderData>();
	    	
	    	MockOrderData order = new MockOrderData("201708171451",12397,"4ER4",110, "Limit", (long) 20.50, 1, "GOOG", "IOC");
	       	MockOrderData order1 = new MockOrderData("201708182345",34523,"7YR8",200, "Limit", (long) 40.50, 1, "APPL", "IOC");
	       	MockOrderData order2= new MockOrderData("201708201124",23243,"9GF5",310, "DEFAULT", (long) 34.50, 1, "CITI", "On Closing");
	       	MockOrderData order3 = new MockOrderData("201708223545",54326,"2SD4",610, "Limit", (long) 45.50, 1, "BARC", "IOC");
	       	
	       	
	       	orders.add(order);
	       	orders.add(order1);
	       	orders.add(order2);
	       	orders.add(order3);
	       	
	       	
	        ResponseBuilder rb= Response.ok(orders.get(id));  
	        return rb.build(); 
	        
	        //return "hello" + id;
	    }
	  
	 

	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGetAllOrder() {
    	
    	List<MockOrderData> orders = new ArrayList<MockOrderData>();
    	
    	MockOrderData order = new MockOrderData("201708171451",12397,"4ER4",110, "Limit", (long) 20.50, 1, "GOOG", "IOC");
       	MockOrderData order1 = new MockOrderData("201708182345",34523,"7YR8",200, "Limit", (long) 40.50, 1, "APPL", "IOC");
       	MockOrderData order2= new MockOrderData("201708201124",23243,"9GF5",310, "DEFAULT", (long) 34.50, 1, "CITI", "On Closing");
       	MockOrderData order3 = new MockOrderData("201708223545",54326,"2SD4",610, "Limit", (long) 45.50, 1, "BARC", "IOC");
       	
       	
       	orders.add(order);
       	orders.add(order1);
       	orders.add(order2);
       	orders.add(order3);
       	
        ResponseBuilder rb= Response.ok(orders);  
        return rb.build();        
    }
    
	
 
}
    