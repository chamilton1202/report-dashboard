package com.example.reportdashboard.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reportdashboard.model.OutboundOrder;
import com.example.reportdashboard.repository.OutboundOrderRepository;

@Service
public class OutboundOrderServiceImpl implements OutboundOrderService {

	private static final Logger log = LoggerFactory.getLogger(OutboundOrderServiceImpl.class);
	
	@Autowired
	private OutboundOrderRepository outboundOrderRepos;
	
	@Override
	public void saveOutboundOrder(OutboundOrder outboundOrder) {
		log.info("saveOutboundOrder " + outboundOrder);
		outboundOrderRepos.save(outboundOrder);		
	}

	@Override
	public List<OutboundOrder> findAllOutboundOrders() {
		log.info("findAllOutboundOrders");
		return outboundOrderRepos.findAll();
	}

	@Override
	public Optional<OutboundOrder> findOutboundOrderById(int id) {
		log.info("findOutboundOrderById");
		return outboundOrderRepos.findById(id);
	}

	@Override
	public List<OutboundOrder> findOutboundOrderByCreatedDate(String createdDate) {
		log.info("findOutboundOrderByCreatedDate");
		return outboundOrderRepos.findByCreatedDate(createdDate);
	}

}
