/*******************************************************************************
 * Copyright (c) 2016 Prowide Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of private license agreements
 * between Prowide Inc. and its commercial customers and partners.
 *******************************************************************************/
package servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.AbstractSwiftMessage;
import com.prowidesoftware.swift.model.MtId;
import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.MxSwiftMessage;
import com.prowidesoftware.swift.model.mt.MtType;
import com.prowidesoftware.swift.model.mx.MxType;

/**
 * Helper class used as mock database to store and retrieve messages in user session.
 * 
 * @author sebastian
 */
public class SessionHelper {
	private static transient final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SessionHelper.class.getName());

	/**
	 * Saves the message in session as attribute, using the identifier as attribute key.
	 * If a message already exist in session with the same attribute it will be overwritten.
	 * @param req
	 * @param msg
	 */
	public static void save(final HttpServletRequest req, final AbstractSwiftMessage msg) {
		if (msg != null) {
			final String identifier = msg.getIdentifier();
			if (identifier != null) {
				log.info("saving message in session with key: "+msg.getIdentifier());
				req.getSession().setAttribute(identifier, msg);
			} else {
				log.severe("message identifier is null");
			}
		} else {
			log.severe("message is null");
		}
	}
	
	/**
	 * Gets the message stored in session given its type, or null if no mesasge is found
	 * for the given type in session.
	 * @param req
	 * @param type
	 * @return
	 */
	public static MtSwiftMessage load(final HttpServletRequest req, final MtType type) {
		if (type != null) {
			MtId id = new MtId(type.number(), type.variant());
			log.info("retrieving message from session with key: "+id.id());
			return (MtSwiftMessage) req.getSession().getAttribute(id.id());
		} else {
			log.warning("type parameter is null");
		}
		return null;
	}
	
	/**
	 * Gets the message stored in session given its type, or null if no mesasge is found
	 * for the given type in session.
	 * @param req
	 * @param type
	 * @return
	 */
	public static MxSwiftMessage load(final HttpServletRequest req, final MxType type) {
		if (type != null) {
			final String key = StringUtils.replace(type.name(), "_", ".");
			log.info("retrieving message from session with key: "+ key);
			return (MxSwiftMessage) req.getSession().getAttribute(key);
		} else {
			log.warning("type parameter is null");
		}
		return null;
	}

}