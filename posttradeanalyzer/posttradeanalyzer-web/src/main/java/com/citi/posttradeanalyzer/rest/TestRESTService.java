/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citi.posttradeanalyzer.rest;

import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.citi.posttradeanalyzer.data.BizLogic;
import com.citi.posttradeanalyzer.service.DataInsertor;
import com.citi.posttradeanalyzer.service.Extractor;
import com.citi.posttradeanalyzer.service.HafizUseThisClass;
import com.citi.posttradeanalyzer.service.Receiver;
import com.citi.posttradeanalyzer.service.Sender;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/lmy")
@RequestScoped
public class TestRESTService {
    
    @Inject
    BizLogic logic;
    
    @Inject 
    HafizUseThisClass extractor;
   
    @Inject
    Extractor ext;
    
    @Inject
    DataInsertor insertor;
    
    @Inject
    Receiver r;
    
    @GET
    @Path("/import")
    @Produces(MediaType.TEXT_PLAIN)
    public String testImport() throws Exception {
    	ext.importFolder();
    	r.startListen();
    	return "ImportTs";
    } 
    
    @GET
    @Path("/im/{path}")
    @Produces(MediaType.TEXT_PLAIN)
    public String testImportPath(@PathParam("path") String path) throws Exception {
    	ext.importFolder(path);
    	r.startListen();
    	return "Importing "+path;
    } 
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMengying_Hafiz() {
    	// test Insertion (Hafiz)
    	//extractor.createObjectAndInsertIntoDB();
		//insertor.createNewOrderSingle("0808090", 100, "lmy", "4",  890, Timestamp.valueOf("2011-10-02 18:48:05.123"), "4", 0, "Apple");

    	return "End of [testDBdxHafiz]";
    }
    
    @GET
    @Path("/{option}")
    @Produces(MediaType.TEXT_PLAIN)
    public String testMengying_Frontend(@PathParam("option") String option) {
    	option = option.trim().toLowerCase();
    	//ioc
    	switch(option) {
    	case "ioc":
    		logic.getIoc();
    		break;
    	case "vwamp":
    		logic.getSamplePricePerMinute("AAPL");
    		logic.getVwampValueEveryFiveMins("AAPL");
    		break;
    	case "commission":
    		logic.getCommision();
    		break;
    	case "cost":
    		logic.getClientCost();
    		break;
    	case "heatmap":
    		logic.getHeatMapData();
    		break;
    		
    	default:
    		System.err.println(logic.getErrorMessage());
    	}
    		
		return "check console for data for " + option;
    }
}
