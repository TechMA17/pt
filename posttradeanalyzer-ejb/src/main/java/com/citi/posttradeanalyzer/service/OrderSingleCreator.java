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
package com.citi.posttradeanalyzer.service;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.citi.posttradeanalyzer.model.ExecutionReport;
import com.citi.posttradeanalyzer.model.OrderSingle;

/**
 * @author Mengying
 */

@Stateless
public class OrderSingleCreator {

	// @Inject
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public void createNewOrderSingle(OrderSingle order) {
		System.out.println(order);
		try {
			em.persist(order);
		} catch (Exception ex) {
    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

	public void createNewExecutionReport(ExecutionReport report) {
		System.out.println(report);
		em.persist(report);
	}
}
