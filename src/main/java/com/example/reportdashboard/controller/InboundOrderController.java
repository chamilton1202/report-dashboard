package com.example.reportdashboard.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.reportdashboard.model.InboundOrder;
import com.example.reportdashboard.model.OrderSearch;
import com.example.reportdashboard.service.InboundOrderService;
import com.example.reportdashboard.util.GenerateInboundOrderPDF;
import com.example.reportdashboard.util.GenerateInboundOrderXLS;

@RequestMapping("/Inbound")
@Controller
public class InboundOrderController {

	private static final Logger log = LoggerFactory.getLogger(InboundOrderController.class);

	@Autowired
	private InboundOrderService inboundOrderService;

	// Used to Display the Results of the Search
	@RequestMapping(value = "/InboundOrders", method = RequestMethod.GET)
	public String getInboundOrders(Model model, HttpSession session) {
		log.info("Entered getInboundOrders()");
		// Get the InboundOrders List from the Session Attribute saved by the Search
		List<InboundOrder> inboundOrdersList = (List<InboundOrder>) session.getAttribute("sessionInboundOrdersList");
		log.info("InboundOrdersList size: " + inboundOrdersList.size());
		model.addAttribute("inboundOrdersList", inboundOrdersList);

		log.info("Leaving getInboundOrders()");
		return "inboundOrders";
	}

	// Used to perform the Search and store the results in a Session Attribute
	@RequestMapping(value = "/searchInboundOrders", method = RequestMethod.POST)
	public String searchInboundOrders(@ModelAttribute OrderSearch orderSearch, BindingResult result, Model model,
			HttpServletRequest request) {
		log.info("Entered searchInboundOrders()");
		// Check for Errors and return to the Home page
		if (result.hasErrors()) {
			return "redirect:../";
		}

		log.info("From Date: " + orderSearch.getFromDate());
		log.info("To Date: " + orderSearch.getToDate());

		List<InboundOrder> sessionInboundOrdersList = inboundOrderService
				.findByFromDateAndToDate(orderSearch.getFromDate(), orderSearch.getToDate());
		log.info("SessionInboundOrdersList: " + sessionInboundOrdersList.size());
		// Save the InboundOrders List to the Session Attribute
		request.getSession().setAttribute("sessionInboundOrdersList", sessionInboundOrdersList);

		log.info("Leaving searchInboundOrders()");
		return "redirect:/Inbound/InboundOrders";
	}

	// ResponseEntity<InputStreamResource>
	@RequestMapping(value = "/DownloadInboundOrdersPDFReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public Object downloadInboundOrdersPDFReport(HttpSession session) {
		log.info("Entered downloadInboundOrdersPDFReport()");
		// Get the InboundOrders List from the Session Attribute saved by the Search
		List<InboundOrder> sessionList = new ArrayList<InboundOrder>();
		sessionList = (List<InboundOrder>) session.getAttribute("sessionInboundOrdersList");
		log.info("Session size: " + sessionList.size());

		ByteArrayInputStream bis = null;

		// Check sessionList
		if (sessionList != null && sessionList.size() > 0) {
			log.info("Generating PDF from existing list");
			// Generate ByteArrayInputStream from existing list
			bis = GenerateInboundOrderPDF.inboundOrderReport(sessionList);
		} else {
			log.info("No InboundOrders found - returning to the Home page");
			return "redirect:../";
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=InboundOrdersReport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	// ResponseEntity<InputStreamResource>
	@RequestMapping(value = "/DownloadInboundOrdersXLSReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object downloadInboundOrdersXLSReport(HttpSession session) {
		log.info("Entered downloadInboundOrdersXLSReport()");
		// Get the InboundOrders List from the Session Attribute saved by the Search
		List<InboundOrder> sessionList = new ArrayList<InboundOrder>();
		sessionList = (List<InboundOrder>) session.getAttribute("sessionInboundOrdersList");
		log.info("Session size: " + sessionList.size());

		ByteArrayInputStream bis = null;

		// Check sessionList
		if (sessionList != null && sessionList.size() > 0) {
			log.info("Generating XLS from existing list");
			// Generate ByteArrayInputStream from existing list
			bis = GenerateInboundOrderXLS.inboundOrderReport(sessionList);
		} else {
			log.info("No InboundOrders found - returning to the Home page");
			return "redirect:../";
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=InboundOrdersReport.xls");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(bis));
	}
}
