package com.example.reportdashboard.service;

import java.util.List;
import java.util.Optional;

import com.example.reportdashboard.model.InboundOrder;

public interface InboundOrderService {

	// CRUD Operations
	// Save
	public void saveInboundOrder(InboundOrder inboundOrder);

	// FindAll
	public List<InboundOrder> findAllInboundOrders();

	// FindById
	public Optional<InboundOrder> findInboundOrderById(int id);

	// Custom Operations
	// FindByCreatedDate
	public List<InboundOrder> findInboundOrderByCreatedDate(String createdDate);

	//Custom Native Query
	public List<InboundOrder> findByFromDateAndToDate(String fromDate, String toDate);
}
