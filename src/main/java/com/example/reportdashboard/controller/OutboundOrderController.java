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
import com.example.reportdashboard.model.OutboundOrder;
import com.example.reportdashboard.service.OutboundOrderService;
import com.example.reportdashboard.util.GenerateInboundOrderPDF;
import com.example.reportdashboard.util.GenerateOutboundOrderPDF;

@RequestMapping("/Outbound")
@Controller
public class OutboundOrderController {

	private static final Logger log = LoggerFactory.getLogger(OutboundOrderController.class);
	
	@Autowired
	private OutboundOrderService outboundOrderService;
	
	@GetMapping("/OutboundOrders")
	public String getOutboundOrders(Model model) {
		List<OutboundOrder> outboundOrderList = outboundOrderService.findAllOutboundOrders();
		model.addAttribute("outboundOrderList", outboundOrderList);
		return "allOutboundOrders";
	}
	
	@RequestMapping(value = "/DownloadOutboundOrdersReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> outboundOrderReport() {

        List<OutboundOrder> outboundOrderList = outboundOrderService.findAllOutboundOrders();
        ByteArrayInputStream bis = GenerateOutboundOrderPDF.outboundOrderReport(outboundOrderList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=OutboundOrdersReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@RequestMapping(value = "/searchOutboundOrders", method = RequestMethod.POST)
	public String searchOutboundOrder(@ModelAttribute OrderSearch orderSearch, BindingResult result, Model model) {
		//logic to process the input data
		if (result.hasErrors()) {
			return "index";
		}
		
		
		
		//return "outboundOrders";
		return "index";
	}
}
