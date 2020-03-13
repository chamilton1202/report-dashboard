package com.example.reportdashboard.model;

public class OrderSearch {

	private String fromDate;
	
	private String toDate;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "OrderSearch [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
	
}
