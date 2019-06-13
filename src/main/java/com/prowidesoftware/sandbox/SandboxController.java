/*******************************************************************************
 * Copyright (c) 2016 Prowide Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of private license agreements
 * between Prowide Inc. and its commercial customers and partners.
 *******************************************************************************/
package com.prowidesoftware.sandbox;

import com.prowidesoftware.ProwideException;
import com.prowidesoftware.swift.guitools.FormBuilder;
import com.prowidesoftware.swift.guitools.MtFormBuilder;
import com.prowidesoftware.swift.guitools.MxFormBuilder;
import com.prowidesoftware.swift.model.AbstractSwiftMessage;
import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.MxSwiftMessage;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.mt.MtType;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.prowidesoftware.swift.model.mx.MxType;
import com.prowidesoftware.swift.myformat.*;
import com.prowidesoftware.swift.validator.ValidationEngine;
import com.prowidesoftware.swift.validator.ValidationProblem;
import javafx.util.Pair;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SandboxController {
	private final Logger log = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	private MessageRepository repository;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("messages", repository.findAll());
		return "index";
	}

	/**
	 * Display a list of message types for selection
	 *
	 * We use the specific enums for MT or MX to generate a list.
	 */
	@GetMapping("/selection/{standard}")
	public ModelAndView messageSelection(@PathVariable String standard) {
		ModelAndView mv = new ModelAndView("selection");
		List<Pair> messages = new ArrayList<>();

		if (StringUtils.equals("mx", standard)) {
			// create list for all MX types
			for (MxType type : MxType.values()) {
				String name = StringUtils.replace(type.name(), "_", ".");
				messages.add(new Pair("/create/mx/" + type.name(), name));
			}
		} else {
			// create list for all MT types but system messages
			for (MtType type : MtType.values()) {
				if (!StringUtils.equals("0", type.category())) {
					messages.add(new Pair("/create/mt/" + type.name(), type.nameFormatted()));
				}
			}
		}

		mv.addObject("messages", messages);
		mv.addObject("standard", standard.toUpperCase());
		return mv;
	}

	/**
	 * Display a form for that specific message type
	 */
    @GetMapping("/create/{standard}/{type}")
    protected ModelAndView messageForm(@PathVariable String standard, @PathVariable String type) throws IOException {
    	ModelAndView mv = new ModelAndView("form");
    	mv.addObject("type", type);
		mv.addObject("standard", standard);

		StringWriter out = new StringWriter();

		if (StringUtils.equals("mx", standard)) {
			MxFormBuilder builder = new MxFormBuilder();
			builder.writeMXForm(MxType.valueOf(type), out, null);
		} else {
			MtFormBuilder builder = new MtFormBuilder();
			builder.writeMTForm(MtType.valueOf(type), out, null);
		}

		mv.addObject("form", out);
		return mv;
    }

	/**
	 * Display a form for that persisted message given its id
	 */
	@GetMapping("/repair/{id}")
	protected ModelAndView messageRepair(@PathVariable Long id) throws IOException {
		AbstractSwiftMessage msg = repository.findById(id).orElseThrow(() -> new ProwideException("Message with id=" + id + "not found"));
		ModelAndView mv = new ModelAndView("form");
		StringWriter out = new StringWriter();

		if (msg.isMX()) {
			mv.addObject("standard", "mx");
			MxSwiftMessage mx = (MxSwiftMessage) msg;
			MxFormBuilder builder = new MxFormBuilder();
			builder.writeMXForm(MxType.valueOf(mx.getIdentifier()), out, mx);

		} else {
			mv.addObject("standard", "mt");
			MtSwiftMessage mt = (MtSwiftMessage) msg;
			MtFormBuilder builder = new MtFormBuilder();
			builder.writeMTForm(MtType.valueOf(mt.getMtId()), out, mt);
		}

		mv.addObject("form", out);
		return mv;
	}

	/**
	 * Display a form for that persisted message given its id
	 */
	@GetMapping("/detail/{id}")
	protected ModelAndView messageDetail(@PathVariable Long id) {
		AbstractSwiftMessage msg = repository.findById(id).orElseThrow(() -> new ProwideException("Message with id=" + id + " not found"));
		ModelAndView mv = new ModelAndView("detail");

		FormBuilder builder;
		if (msg.isMX()) {
			mv.addObject("standard", "mx");
			builder = new MxFormBuilder();
		} else {
			mv.addObject("standard", "mt");
			builder = new MtFormBuilder();
		}
		StringWriter out = new StringWriter();
		builder.writeDetail(out, msg);

		mv.addObject("detail", out);
		mv.addObject("id", msg.getId());
		return mv;
	}

	/**
	 * Example backend process when a message is created or repaired in the GUI
	 */
    @PostMapping("/{standard}")
    protected String messageCreation(@PathVariable String standard, HttpServletRequest req) {
		AbstractSwiftMessage msg;
		if (StringUtils.equals("mx", standard)) {
			msg = MxFormBuilder.map(req);
		} else {
			msg = MtFormBuilder.map(req);
		}
		log.info("mapped message: "+msg);
		//System.out.println(msg.message());

		// backend message validation
		// if errors are found, you should return to the form view with an alert
		// as an alternative you could implement this full validation as backend service
		// an do an ajax call from the from end (from the form generated by GUI Tools)
		ValidationEngine e = new ValidationEngine();
		e.initialize();
		MtSwiftMessage mt = (MtSwiftMessage) msg;
		List<ValidationProblem> p = e.validateMessage(mt.modelMessage());
		System.out.println(ValidationProblem.printout(p));


		// conversion to custom XML
		MappingTable t = new MappingTable(FileFormat.MT, FileFormat.XML);
		t.add(new MappingRule("32A/3", "/Document/transaction/amount", new Transformation(Transformation.Key.formatDecimal)));
		t.add(new MappingRule("32A/2", "/Document/transaction/currency"));
		// instead of programmatic mappings you could also load them from an Excel file or from database
		//MappingTable.loadFromSpreadsheet(new File("/Users/sebastian/Downloads/sample.xls"), null, t);

		final String target = MyFormatEngine.translate(mt.message(), t);
		System.out.println(target);

		// conversion to CSV
		MappingTable t2 = new MappingTable(FileFormat.MT, FileFormat.CSV);
		t2.add(new MappingRule("32A/3", "0", new Transformation(Transformation.Key.formatDecimal)));
		t2.add(new MappingRule("32A/2", "1"));

		final String target2 = MyFormatEngine.translate(mt.message(), t2);
		System.out.println(target2);

		// conversion to FIXED LENGTH
		MappingTable t3 = new MappingTable(FileFormat.MT, FileFormat.FIXEDLEN);
		t3.add(new MappingRule("32A/3", "0/15", new Transformation(Transformation.Key.toPIC, "9(14).999")));
		t3.add(new MappingRule("32A/2", "16/3", new Transformation(Transformation.Key.toPIC, "X(3)")));

		final String target3 = MyFormatEngine.translate(mt.message(), t3);
		System.out.println(target3);


		// save the created message in the database
		repository.save(msg);
		log.info("saved message id="+msg.getId());


		// send message to MQ
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			Connection connection = factory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("demo");
			MessageProducer messageProducer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();

			// plain FIN as raw message
			message.setText(msg.message());

			messageProducer.send(message);
			//System.out.println("sent content: " + message.getText() );
		} catch (JMSException ex) {
			ex.printStackTrace();
		}


		// redirect to the detail page.
		return "redirect:/detail/" + msg.getId();
    }

}