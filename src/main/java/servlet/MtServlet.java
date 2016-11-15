/*******************************************************************************
 * Copyright (c) 2016 Prowide Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of private license agreements
 * between Prowide Inc. and its commercial customers and partners.
 *******************************************************************************/
package servlet;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prowidesoftware.ProwideException;
import com.prowidesoftware.swift.guitools.AbstractFormBuilder;
import com.prowidesoftware.swift.guitools.MtFormBuilder;
import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.mt.MtType;
import com.prowidesoftware.swift.model.mt.SRU2016MtType;

@WebServlet(
        name = "MtServlet",
        urlPatterns = {"/mt"}
    )
public class MtServlet extends AbstractServlet {
	private static final long serialVersionUID = 3151991653694521562L;
	private static transient final java.util.logging.Logger log = java.util.logging.Logger.getLogger(MtServlet.class.getName());

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
    	log.info(req.getParameter("block2Xml"));
    	try {
	    	/*
	    	 * Map form data into an MT.
	    	 * 
	    	 * When implementing a modification of pre existing message you can
	    	 * pass also the existing message as parameter to the map method.
	    	 */
	    	MtSwiftMessage msg = MtFormBuilder.map(req);
	    	log.info("mapped message: "+msg);
	
	    	/*
	    	 * Store the message type in request
	    	 */
	        final MtType type = SRU2016MtType.valueOf(req.getParameter(AbstractFormBuilder.TYPE_PARAM));
	    	req.setAttribute(TYPE, type);
	    	log.fine("type: "+type);
	    	
	    	/*
	    	 * Store the created message in user session for demo convenience
	    	 * This should be replace with database persistence
	    	 */
	    	SessionHelper.save(req, msg);

	    	/*
	    	 * Display the message detail page
	    	 */
			forward(req, resp, "mt-detail.jsp");

    	} catch (ProwideException e) {
    		log.log(Level.WARNING, e.getMessage(), e);
    		req.setAttribute("error", e.getMessage());
    		forward(req, resp, "error.jsp");
    	}
    }
}
