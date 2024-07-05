package com.vibiya.DTO;



import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BusDTO {
	
	
	private Integer busId;
	private String startPoint;
	private String endPoint;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate busJourneyDate;
	
	
	public BusDTO(Integer busId, String startPoint, String endPoint, LocalDate busJourneyDate) {
		super();
		this.busId = busId;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.busJourneyDate = busJourneyDate;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public LocalDate getBusJourneyDate() {
		return busJourneyDate;
	}

	public void setBusJourneyDate(LocalDate busJourneyDate) {
		this.busJourneyDate = busJourneyDate;
	}
	
	
	
	
	
	
	
	
	



}
