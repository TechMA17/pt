package com.citi.posttradeanalyzer.data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import edu.emory.mathcs.backport.java.util.Arrays;

/*
 * @author Mengying
 * 
 */

@RequestScoped
public class BizLogic {
	
	@Inject
	QueryEngine qe;

	public String getErrorMessage() {
		return "invalid option";
	}
	
	
	public ArrayList<ArrayList<String>> getIoc() {
		
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> innerArr = new ArrayList<>();
 		
		HashMap<String, String> numIocOrder = qe.getNumberOfIOCOrder();		
		HashMap<String, String> numIocFilledOrder = qe.getNumberOfIOCFilledOrder();
		
		String[] iocTimes = numIocOrder.keySet().toArray(new String[numIocOrder.keySet().toArray().length]);
		Arrays.sort(iocTimes);
	
		long filledCount;
		long iocCount;
		double hitRate;
		for (String time : iocTimes) {
			innerArr = new ArrayList<>();
			if (numIocFilledOrder.containsKey(time)) {
				 filledCount = Long.parseLong(numIocFilledOrder.get(time));
			}  else {
				filledCount = 0;
			}
			iocCount = Long.parseLong(numIocOrder.get(time));
			hitRate = filledCount / iocCount;
			innerArr.add(time);
			innerArr.add(String.valueOf(hitRate));
			arr.add(innerArr);
		}
		return arr;
	}

	
	public ArrayList<ArrayList<String>> getSamplePricePerMinute(String symbol){
		ArrayList<ArrayList<String>> samplePrice = qe.getSamplePricePerMinute(symbol);
		System.out.println(samplePrice);
		return samplePrice;
	}
	
	
	public ArrayList<ArrayList<String>> getVwampValueEveryFiveMins(String symbol){
		ArrayList<ArrayList<String>> vwapPrice = qe.getVwampValueEveryFiveMins(symbol);
		System.out.println(vwapPrice);
		return vwapPrice;
	}

	
	public ArrayList<ArrayList<String>> getCommision() {
		
		ArrayList<ArrayList<String>> arr = qe.getCommissionAndTime();
		System.out.println(arr);
		return arr;
	}
	
	
	public ArrayList<ArrayList<String>> getClientCost() {
		ArrayList<ArrayList<String>> arr = qe.getClientCost();
		System.out.println(arr);
		return arr;
	}
	
	
	public HashMap<String, ArrayList<ArrayList<String>>> getHeatMapData() {
		ArrayList<String> symbols = getSymbols();
		HashMap<String, ArrayList<ArrayList<String>>> map = new HashMap<>();
	
		for (String symbol : symbols) {
			ArrayList<ArrayList<String>> value = qe.getTradeVolumeByFiveMinInterval(symbol);
			System.out.println(value);
			map.put(symbol, value);
		}
		System.out.println("****************************");
		System.out.println(map);
		return map;
	}
	
	
	public long getTotalNumberOfOrders() {
		long num = qe.getTotalNumberOfOrders();
		System.out.println(num);
		return num;
	}
	
	public long getNumberOfCompletedOrders() {
		long num = qe.getTotalNumberOfCompletedOrders();
		System.out.println(num);
		return num;
	}
	
	public long getNumberOfCanceledTrades() {
		long num = qe.getTotalNumberOfCanceledTrades();
		System.out.println(num);
		return num;
	}
	
	public double getTotalCommission() {
		double commission = qe.getTotalCommission();
		System.out.println(commission);
		return commission;
	}
	
	public ArrayList<String> getSymbols(){
		ArrayList<String> symbols = qe.getSymbols();
		System.out.println(symbols);
		return symbols;
	}
}
