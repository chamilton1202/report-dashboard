package com.example.reportdashboard.service;

import java.util.List;
import java.util.Optional;

import com.example.reportdashboard.model.OutboundOrder;

public interface OutboundOrderService {

	// CRUD Operations
	// Save
	public void saveOutboundOrder(OutboundOrder outboundOrder);

	// FindAll
	public List<OutboundOrder> findAllOutboundOrders();

	// FindById
	public Optional<OutboundOrder> findOutboundOrderById(int id);

	// Custom Operations
	// FindByCreatedDate
	public List<OutboundOrder> findOutboundOrderByCreatedDate(String createdDate);
	
	//Custom Native Query
	public List<OutboundOrder> findByFromDateAndToDate(String fromDate, String toDate);
}
