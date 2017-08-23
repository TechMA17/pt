package com.citi.posttradeanalyzer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.jms.JMSException;

@Stateless
public class Extractor {

	@Resource
	private ManagedThreadFactory mtf;
	@Inject
	Instance<ProcessExtraction> runnableInstanceProvider;
	@Inject
	Sender send;
	private File fileOrFolder;
	private int totalNumberOfFiles = 0;
	private List<String> allFilesArrayList = new ArrayList<String>();
	private CountDownLatch cdl;

	private void processFile(String path) {
		ProcessExtraction myRunnable = runnableInstanceProvider.get();
		myRunnable.setPath(path);
		myRunnable.setCdl(cdl);
		Thread t = mtf.newThread(myRunnable);
		t.start();
	}

	public void importFolder(String path) {
		doImport(path);
	}
	public void importFolder() {
		doImport(MyConstants.DEFAULT_IN_FOLDER);
	}
	private void doImport(String path) {

		System.out.println("Reading Folder");
		fileOrFolder = new File(path);
		String temp = "";
		if (fileOrFolder.isDirectory()) {
			for (final File fileEntry : fileOrFolder.listFiles()) {
				if (fileEntry.isFile()) {
					temp = fileEntry.getAbsolutePath();
					allFilesArrayList.add(temp);
				}
			}
		}
		totalNumberOfFiles = allFilesArrayList.size();
		cdl = new CountDownLatch(totalNumberOfFiles);
		for (int i = 0; i < allFilesArrayList.size(); i++) {
			System.out.println(allFilesArrayList.get(i));
			processFile(allFilesArrayList.get(i));			
		}
		
		try {
			cdl.await();
		}catch(InterruptedException e) {}
		System.out.println("Completed Sending To Queue");
		try {
			send.sendMessages("quit");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
