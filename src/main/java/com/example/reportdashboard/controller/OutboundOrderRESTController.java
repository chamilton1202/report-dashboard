package com.example.reportdashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reportdashboard.model.OutboundOrder;
import com.example.reportdashboard.service.OutboundOrderService;

@RequestMapping("/Outbound/REST")
@RestController
public class OutboundOrderRESTController {

	private static final Logger log = LoggerFactory.getLogger(OutboundOrderRESTController.class);
	
	@Autowired
	private OutboundOrderService outboundOrderService;
	
	@RequestMapping("/All")
	public List<OutboundOrder> getOutboundOrders() {
		return outboundOrderService.findAllOutboundOrders();
	}
}
