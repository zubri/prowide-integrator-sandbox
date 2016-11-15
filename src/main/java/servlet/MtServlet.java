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

import com.prowidesoftware.ProwideException;
import com.prowidesoftware.swift.gui.MtFormBuilder;
import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.mt.MtType;
import com.prowidesoftware.swift.model.mt.SRU2016MtType;

@WebServlet(
        name = "MtServlet",
        urlPatterns = {"/mt"}
    )
public class MtServlet extends AbstractServlet {
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
    		forward(req, resp, "mt-list.jsp");
    		
    	} else {
    		/*
    		 * If type parameter is present, store the MtType in request
    		 * and display the form for that particular mesasge type
    		 */
	        final MtType type = SRU2016MtType.valueOf(typeParam);
	        req.setAttribute(TYPE, type);
	        forward(req, resp, "mt-form.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
	    	/*
	    	 * Map form data into an MT.
	    	 * 
	    	 * When implementing a modification of pre existing message you can
	    	 * pass also the existing message as parameter to the map method.
	    	 */
	    	MtSwiftMessage msg = MtFormBuilder.map(req);
	
	    	/*
	    	 * Store the message type in request
	    	 */
	        final MtType type = SRU2016MtType.valueOf(req.getParameter("type"));
	    	req.setAttribute(TYPE, type);
	    	
	    	/*
	    	 * Store the created message in request
	    	 * We use the type as attribute name for demo convenience
	    	 */
	    	req.getSession().setAttribute(type.name(), msg);
	    	
    	} catch (ProwideException e) {
    		req.setAttribute("error", e.getMessage());
    		forward(req, resp, "error.jsp");
    	}
    	
    	/*
    	 * Display the message detail page
    	 */
		forward(req, resp, "mt-detail.jsp");
    }
}
