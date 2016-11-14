/*******************************************************************************
 * Copyright (c) 2016 Prowide Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of private license agreements
 * between Prowide Inc. and its commercial customers and partners.
 *******************************************************************************/
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AbstractServlet extends HttpServlet {

	protected void forward(HttpServletRequest req, HttpServletResponse resp, final String jsp) throws ServletException, IOException {
		req.getRequestDispatcher(req.getContextPath()+ jsp).forward(req, resp);
	}
}
