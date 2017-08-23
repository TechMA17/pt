package com.citi.posttradeanalyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;
import javax.jms.JMSException;

public class ProcessExtraction implements Runnable {
	private CountDownLatch cdl;
	private String path;

	public void setPath(String path) {
		this.path = path;
	}

	public void setCdl(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Inject
	Sender sender;

	private void sendToMQ(String msg) throws JMSException {
		try {
			sender.sendMessages(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void extractLines(String path) throws IOException, JMSException {
		System.out.println("ExtractLines(" + path + ")");
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			try {
				strLine = strLine.split(": ")[1];
				sendToMQ(strLine);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		br.close();

		File afile = new File(path);
		File parentFolder = afile.getParentFile();
		File outputFolder = new File(parentFolder.getParentFile() + "\\Out\\");
		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		}
		if (afile.renameTo(new File(outputFolder.getPath() +"\\"+ afile.getName()))) {
			System.out.println("Success Moving to: " + outputFolder.getPath() +"\\"+ afile.getName());
		} else {
			System.out.println("Error Moving to: " + outputFolder.getPath() +"\\"+ afile.getName());
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Ended On for " + path + " @ " + dateFormat.format(date));

	}

	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Started On for " + path + " @ " + dateFormat.format(date));
		try {
			extractLines(path);
			cdl.countDown();
		} catch (IOException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}