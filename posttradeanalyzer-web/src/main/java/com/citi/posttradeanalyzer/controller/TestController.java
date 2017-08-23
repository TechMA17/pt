package com.citi.posttradeanalyzer.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.citi.posttradeanalyzer.service.Extractor;

@Model
public class TestController {

    @Inject
    private Extractor extractor;
	public void test() {
		System.out.println("TestFunction Extractor");
		//extractor.testFunction();
	}
	
}
