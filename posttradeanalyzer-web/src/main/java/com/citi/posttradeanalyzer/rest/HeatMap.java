package com.citi.posttradeanalyzer.rest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.citi.posttradeanalyzer.data.BizLogic;



@Path("/heatmap")
public class HeatMap {
	
	@Inject
	BizLogic logic;
	
	@GET
	//@Path("/dailytradevolume")
    @Produces({MediaType.APPLICATION_JSON})
    public Response dailyTradeVolume() {
    	
		/*
		String[] tradeQty1 = {"1", "20", "30", "50", "80", "45", "12"};
		String[] tradeQty2 = {"20", "1", "60", "80", "30", "3", "12"};
		String[] tradeQty3 = {"30", "60", "1", "0", "20", "45", "22"};
		
		String[] timeStamp = { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00" };
		String[] stockName = {"PSLA", "AAPL", "CSCO"};
		
		ArrayList<String[]> dailyTradeVolume = new ArrayList<String[]>();

		dailyTradeVolume.add(tradeQty1);
		dailyTradeVolume.add(tradeQty2);
		dailyTradeVolume.add(tradeQty3);
		
		List<Object> list = new ArrayList<Object>();
		
		list.add(timeStamp);
		list.add(stockName);
		list.add(dailyTradeVolume);
		*/
		
		HashMap<String, ArrayList<ArrayList<String>>> map = new HashMap<>();
		//key --> symbol (string)
		//value --> ArrayList<ArrayList<String>>   [ [time, qty] ]
		map = logic.getHeatMapData();
        ResponseBuilder rs= Response.ok(map);  
        return rs.build();        
    }
	/*
	@GET
	@Path("/timestamp")
    @Produces({MediaType.APPLICATION_JSON})
    public Response doTimeStamp() {
		String[] timeStamp = { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00" };
        ResponseBuilder rs= Response.ok(timeStamp);  
        return rs.build();    
		
	}
	
	
	@GET
	@Path("/stockname")
    @Produces({MediaType.APPLICATION_JSON})
    public Response doGStockName() {
		String[] stockName = {"PSLA", "AAPL", "CSCO"};
        ResponseBuilder rs= Response.ok(stockName);  
        return rs.build();    
	}
	*/
}

	
	

    