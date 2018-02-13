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
import com.prowidesoftware.swift.model.mt.SRU2017MtType;

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
	        final MtType type = SRU2017MtType.valueOf(typeParam);
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
	    	 * When implementing a modification of pre-existing message you can
	    	 * pass also the existing message as parameter to the map method.
	    	 */
	    	MtSwiftMessage msg = MtFormBuilder.map(req);
	    	log.info("mapped message: "+msg);
	
	    	/*
	    	 * On a real application here you should be calling the validation engine
	    	 * (from Prowide Integrator Validation module) in order to check the created
	    	 * message is full standard compliance, sending the backend validation result
	    	 * back to the form in case of errors. Notice the client side validation done
	    	 * in the form is just a lightweight javascript check on mandatory fields and
	    	 * content. That client side validation (included in the GUI Tools module) does
	    	 * not check for example network/semantic rules.
	    	 */
	    	
	    	/*
	    	 * Store the message type in request, just for convenience.
	    	 */
	        final MtType type = SRU2017MtType.valueOf(req.getParameter(AbstractFormBuilder.TYPE_PARAM));
	    	req.setAttribute(TYPE, type);
	    	log.fine("type: "+type);
	    	
	    	/*
	    	 * Store the created message in user session for demo convenience.
	    	 * This should be replace with database persistence in a real application.
	    	 */
	    	SessionHelper.save(req, msg);

	    	/*
	    	 * Display the message detail page.
	    	 * The JSP will use the message store in session, in a real application
	    	 * you would for example have a specific servlet controller loading the persisted 
	    	 * message from database and passing it to the JSP view. 
	    	 */
			forward(req, resp, "mt-detail.jsp");

    	} catch (ProwideException e) {
    		log.log(Level.WARNING, e.getMessage(), e);
    		req.setAttribute("error", e.getMessage());
    		forward(req, resp, "error.jsp");
    	}
    }
}
