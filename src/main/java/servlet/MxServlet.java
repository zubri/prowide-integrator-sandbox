/*******************************************************************************
 * Copyright (c) 2016 Prowide Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of private license agreements
 * between Prowide Inc. and its commercial customers and partners.
 *******************************************************************************/
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prowidesoftware.swift.gui.MxFormBuilder;
import com.prowidesoftware.swift.model.MxSwiftMessage;
import com.prowidesoftware.swift.model.mx.MxType;

@WebServlet(
        name = "MxServlet",
        urlPatterns = {"/mx"}
    )
public class MxServlet extends AbstractServlet {
	private static final long serialVersionUID = 3151991653694521562L;

	public static String TYPE = "typeAttributeName";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	final String typeParam = req.getParameter("type");
    	
    	if (typeParam == null) {
    		/*
    		 * If type parameter is not present, 
    		 * display the message type selection page
    		 */
    		forward(req, resp, "mx-list.jsp");
    		
    	} else {
    		/*
    		 * If type parameter is present, store the MtType in request
    		 * and display the form for that particular mesasge type
    		 */
	        final MxType type = MxType.valueOf(typeParam);
	        req.setAttribute(TYPE, type);
	        forward(req, resp, "mx-form.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	/*
    	 * Map form data into an MX.
    	 * 
    	 * When implementing a modification of pre existing message you can
    	 * pass also the existing message as parameter to the map method.
    	 */
    	MxSwiftMessage msg = MxFormBuilder.map(req);

    	/*
    	 * Store the message type in request
    	 */
        final MxType type = MxType.valueOf(req.getParameter("type"));
    	req.setAttribute(TYPE, type);
    	
    	/*
    	 * Store the created message in request
    	 * We use the type as attribute name for demo convenience
    	 */
    	req.getSession().setAttribute(type.name(), msg);
    	
    	/*
    	 * Display the message detail page
    	 */
		forward(req, resp, "mx-detail.jsp");
    }
}
