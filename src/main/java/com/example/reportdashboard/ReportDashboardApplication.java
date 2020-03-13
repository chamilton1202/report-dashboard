package com.example.reportdashboard;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.reportdashboard.model.InboundOrder;
import com.example.reportdashboard.model.OutboundOrder;
import com.example.reportdashboard.service.InboundOrderService;
import com.example.reportdashboard.service.OutboundOrderService;

@SpringBootApplication
public class ReportDashboardApplication {

	private static final Logger log = LoggerFactory.getLogger(ReportDashboardApplication.class);
	
	//@Autowired
	//private InboundOrderService inboundOrderService;
	
	//@Autowired
	//private OutboundOrderService outboundOrderService;
	
	public static void main(String[] args) {
		SpringApplication.run(ReportDashboardApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * log.info("Starting application...");
	 * 
	 * //InboundOrder log.info("InboundOrder");
	 * inboundOrderService.saveInboundOrder(new InboundOrder("1234", new Date(),
	 * "type", "zipcode", "acisBranch", "acisBu", "status"));
	 * 
	 * log.info("findAll()"); inboundOrderService.findAllInboundOrders().forEach(x
	 * -> log.info("InboundOrder: " + x));
	 * 
	 * //OutboundOrder log.info("OutboundOrder");
	 * outboundOrderService.saveOutboundOrder(new OutboundOrder("4321", new Date(),
	 * "type", "acisBranch", "acisBu", "status"));
	 * 
	 * log.info("findAll()"); outboundOrderService.findAllOutboundOrders().forEach(x
	 * -> log.info("OutboundOrder: " + x));
	 * 
	 * }
	 */

}
