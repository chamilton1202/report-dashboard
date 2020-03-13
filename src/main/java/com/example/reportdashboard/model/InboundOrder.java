package com.example.reportdashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "edi_order")
public class InboundOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", columnDefinition = "serial")
	private int id;
	
	@Column(name = "order_id", nullable = false)
	private String orderId;
	
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "zipcode", nullable = true)
	private String zipcode;
	
	@Column(name = "acis_branch", nullable = true)
	private String acisBranch;
	
	@Column(name = "acis_bu", nullable = true)
	private String acisBu;
	
	@Column(name = "status", nullable = true)
	private String status;
	
	//Used because cpu is not a DB column but is combined with acis_branch and acis_bu
	@Transient
	private String cpu;

	public InboundOrder() {
	}

	public InboundOrder(String orderId, Date createdDate, String type, String zipcode, String acisBranch, String acisBu,
			String status, String cpu) {
		this.orderId = orderId;
		this.createdDate = createdDate;
		this.type = type;
		this.zipcode = zipcode;
		this.acisBranch = acisBranch;
		this.acisBu = acisBu;
		this.status = status;
		this.cpu = cpu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAcisBranch() {
		return acisBranch;
	}

	public void setAcisBranch(String acisBranch) {
		this.acisBranch = acisBranch;
	}

	public String getAcisBu() {
		return acisBu;
	}

	public void setAcisBu(String acisBu) {
		this.acisBu = acisBu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCpu() {
		cpu = getAcisBranch() + "/" + getAcisBu();
		return cpu;
	}

	public void setCpu(String cpu) {
		cpu = this.acisBranch + "/" + this.acisBu;
		this.cpu = cpu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acisBranch == null) ? 0 : acisBranch.hashCode());
		result = prime * result + ((acisBu == null) ? 0 : acisBu.hashCode());
		result = prime * result + ((cpu == null) ? 0 : cpu.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InboundOrder other = (InboundOrder) obj;
		if (acisBranch == null) {
			if (other.acisBranch != null)
				return false;
		} else if (!acisBranch.equals(other.acisBranch))
			return false;
		if (acisBu == null) {
			if (other.acisBu != null)
				return false;
		} else if (!acisBu.equals(other.acisBu))
			return false;
		if (cpu == null) {
			if (other.cpu != null)
				return false;
		} else if (!cpu.equals(other.cpu))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (id != other.id)
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InboundOrder [id=" + id + ", orderId=" + orderId + ", createdDate=" + createdDate + ", type=" + type
				+ ", zipcode=" + zipcode + ", acisBranch=" + acisBranch + ", acisBu=" + acisBu + ", status=" + status
				+ ", cpu=" + cpu + "]";
	}
	
}
