package com.example.reportdashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reportdashboard.model.InboundOrder;
import com.example.reportdashboard.service.InboundOrderService;

@RequestMapping("/Inbound/REST")
@RestController
public class InboundOrderRESTController {

	private static final Logger log = LoggerFactory.getLogger(InboundOrderRESTController.class);
	
	@Autowired
	private InboundOrderService inboundOrderService;
	
	@RequestMapping("/All")
	public List<InboundOrder> getInboundOrders() {
		return inboundOrderService.findAllInboundOrders();
	}
}
