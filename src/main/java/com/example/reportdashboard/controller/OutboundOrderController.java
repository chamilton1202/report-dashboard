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
import com.example.reportdashboard.model.OutboundOrder;
import com.example.reportdashboard.service.OutboundOrderService;
import com.example.reportdashboard.util.GenerateInboundOrderXLS;
import com.example.reportdashboard.util.GenerateOutboundOrderPDF;
import com.example.reportdashboard.util.GenerateOutboundOrderXLS;

@RequestMapping("/Outbound")
@Controller
public class OutboundOrderController {

	private static final Logger log = LoggerFactory.getLogger(OutboundOrderController.class);

	@Autowired
	private OutboundOrderService outboundOrderService;

	// Used to Display the Results of the Search
	@RequestMapping(value = "/OutboundOrders", method = RequestMethod.GET)
	public String getOutboundOrders(Model model, HttpSession session) {
		log.info("Entered getOutboundOrders()");
		// Get the OutboundOrders List from the Session Attribute saved by the Search
		List<OutboundOrder> outboundOrdersList = (List<OutboundOrder>) session
				.getAttribute("sessionOutboundOrdersList");
		log.info("OutboundOrdersList size: " + outboundOrdersList.size());
		model.addAttribute("outboundOrdersList", outboundOrdersList);

		log.info("Leaving getOutboundOrders()");
		return "outboundOrders";
	}

	// Used to perform the Search and store the results in a Session Attribute
	@RequestMapping(value = "/searchOutboundOrders", method = RequestMethod.POST)
	public String searchInboundOrders(@ModelAttribute OrderSearch orderSearch, BindingResult result, Model model,
			HttpServletRequest request) {
		log.info("Entered searchOutboundOrders()");
		// Check for Errors and return to the Home page
		if (result.hasErrors()) {
			return "redirect:../";
		}

		log.info("From Date: " + orderSearch.getFromDate());
		log.info("To Date: " + orderSearch.getToDate());

		List<OutboundOrder> sessionOutboundOrdersList = outboundOrderService
				.findByFromDateAndToDate(orderSearch.getFromDate(), orderSearch.getToDate());
		log.info("SessionOutboundOrdersList: " + sessionOutboundOrdersList.size());
		// Save the OutboundOrders List to the Session Attribute
		request.getSession().setAttribute("sessionOutboundOrdersList", sessionOutboundOrdersList);

		log.info("Leaving searchOutboundOrders()");
		return "redirect:/Outbound/OutboundOrders";
	}

	// ResponseEntity<InputStreamResource>
	@RequestMapping(value = "/DownloadOutboundOrdersPDFReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public Object downloadOutboundOrdersPDFReport(HttpSession session) {
		log.info("Entered downloadOutboundOrdersPDFReport()");
		// Get the OutboundOrders List from the Session Attribute saved by the Search
		List<OutboundOrder> sessionList = new ArrayList<OutboundOrder>();
		sessionList = (List<OutboundOrder>) session.getAttribute("sessionOutboundOrdersList");
		log.info("Session size: " + sessionList.size());

		ByteArrayInputStream bis = null;

		// Check sessionList
		if (sessionList != null && sessionList.size() > 0) {
			log.info("Generating PDF from existing list");
			// Generate ByteArrayInputStream from existing list
			bis = GenerateOutboundOrderPDF.outboundOrderReport(sessionList);
		} else {
			log.info("No OutboundOrders found - returning to the Home page");
			return "redirect:../";
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=OutboundOrdersReport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	// ResponseEntity<InputStreamResource>
	@RequestMapping(value = "/DownloadOutboundOrdersXLSReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object downloadOutboundOrdersXLSReport(HttpSession session) {
		log.info("Entered downloadOutboundOrdersXLSReport()");
		// Get the OutboundOrders List from the Session Attribute saved by the Search
		List<OutboundOrder> sessionList = new ArrayList<OutboundOrder>();
		sessionList = (List<OutboundOrder>) session.getAttribute("sessionOutboundOrdersList");
		log.info("Session size: " + sessionList.size());

		ByteArrayInputStream bis = null;

		// Check sessionList
		if (sessionList != null && sessionList.size() > 0) {
			log.info("Generating XLS from existing list");
			// Generate ByteArrayInputStream from existing list
			bis = GenerateOutboundOrderXLS.outboundOrderReport(sessionList);
		} else {
			log.info("No OutboundOrders found - returning to the Home page");
			return "redirect:../";
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=OutboundOrdersReport.xls");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(bis));
	}
}
