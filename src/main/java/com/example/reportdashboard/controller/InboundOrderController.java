package com.example.reportdashboard.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.reportdashboard.model.InboundOrder;
import com.example.reportdashboard.model.OrderSearch;
import com.example.reportdashboard.service.InboundOrderService;
import com.example.reportdashboard.util.GenerateInboundOrderPDF;

@RequestMapping("/Inbound")
@Controller
public class InboundOrderController {
	
	private static final Logger log = LoggerFactory.getLogger(InboundOrderController.class);
	
	@Autowired
	private InboundOrderService inboundOrderService;
	
	@RequestMapping(value = "/InboundOrders", method = RequestMethod.GET)
	public String getInboundOrders(Model model) {
		List<InboundOrder> inboundOrderList = inboundOrderService.findAllInboundOrders();
		model.addAttribute("inboundOrderList", inboundOrderList);
		return "allInboundOrders";
	}
	
	@RequestMapping(value = "/searchInboundOrders", method = RequestMethod.POST)
	public String searchInboundOrder(@ModelAttribute OrderSearch orderSearch, BindingResult result, Model model) {
		//logic to process the input data
		if (result.hasErrors()) {
			return "index";
		}
		
		
		
		//return "inboundOrders";
		return "index";
	}
	
	@RequestMapping(value = "/DownloadInboundOrdersReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> inboundOrderReport() {

        List<InboundOrder> inboundOrderList = inboundOrderService.findAllInboundOrders();
        ByteArrayInputStream bis = GenerateInboundOrderPDF.inboundOrderReport(inboundOrderList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=InboundOrdersReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
