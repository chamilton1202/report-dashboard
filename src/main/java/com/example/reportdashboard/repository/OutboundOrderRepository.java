package com.example.reportdashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reportdashboard.model.OutboundOrder;

@Repository
public interface OutboundOrderRepository extends JpaRepository<OutboundOrder, Integer> {

	//Extends CRUD Operations from CRUD Repository
	
	List<OutboundOrder> findByCreatedDate(String createdDate);
}