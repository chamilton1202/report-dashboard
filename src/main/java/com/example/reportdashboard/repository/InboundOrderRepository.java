package com.example.reportdashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reportdashboard.model.InboundOrder;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Integer> {

	//Extends CRUD Operations from CRUD Repository
	
	List<InboundOrder> findByCreatedDate(String createdDate);
}
