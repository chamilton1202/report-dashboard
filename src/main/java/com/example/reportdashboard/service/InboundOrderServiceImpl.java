package com.example.reportdashboard.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reportdashboard.model.InboundOrder;
import com.example.reportdashboard.repository.InboundOrderRepository;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

	private static final Logger log = LoggerFactory.getLogger(InboundOrderServiceImpl.class);
	
	@Autowired
	private InboundOrderRepository inboundOrderRepos;
	
	@Override
	public void saveInboundOrder(InboundOrder inboundOrder) {
		log.info("saveInboundOrder " + inboundOrder);
		inboundOrderRepos.save(inboundOrder);
	}

	@Override
	public List<InboundOrder> findAllInboundOrders() {
		log.info("findAllInboundOrders");
		return inboundOrderRepos.findAll();
	}

	@Override
	public Optional<InboundOrder> findInboundOrderById(int id) {
		log.info("findInboundOrderById");
		return inboundOrderRepos.findById(id);
	}

	@Override
	public List<InboundOrder> findInboundOrderByCreatedDate(String createdDate) {
		log.info("findInboundOrderByCreatedDate");
		return inboundOrderRepos.findByCreatedDate(createdDate);
	}

	
}
