package com.example.reportdashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.reportdashboard.model.OutboundOrder;

@Repository
public interface OutboundOrderRepository extends JpaRepository<OutboundOrder, Integer> {

	//Extends CRUD Operations from CRUD Repository
	
	List<OutboundOrder> findByCreatedDate(String createdDate);
	
	//select * from edi_order where created_date::text > '2020-03-13 00:00:00' 
	//and created_date::text < '2020-03-15 00:00:00' and zipcode is null
	@Query(
			value = "SELECT * from edi_order WHERE " +
					"created_date > to_timestamp(?1, 'YYYY:MM:DD') AND created_date < to_timestamp(?2, 'YYYY:MM:DD') AND zipcode is null",
			nativeQuery=true)	
	List<OutboundOrder> findByFromDateAndToDate(@Param("fromDate") String fromDate,
												   @Param("toDate") String toDate);
}
