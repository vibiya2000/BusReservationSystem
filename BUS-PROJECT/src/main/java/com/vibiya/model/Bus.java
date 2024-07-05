package com.vibiya.model;

import java.time.LocalDate;



import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;



@Entity
public class Bus {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;
	
	@NotNull(message = "Bus driver name cannot be null!")
	private String driverName;
	
	private String busType;
	
	
	@NotNull(message = "Start point cannot be null!")
	private String startPoint;
	@NotNull(message = "Destination point cannot be null!")
	private String endPoint;
	
	@NotNull(message = "Arrival time cannot be null!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime arrivalTime;
	
	@NotNull(message = "Departure time cannot be null!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime departureTime;
	
	@NotNull(message = "Total Seats cannot be null!")
	private Integer seats;
	
	@NotNull(message = "Available seats cannot be null!")
	private Integer availableSeats;
	
	@NotNull(message = "Fare cannot be null!")
	private Integer farePerSeat;
	
	@NotNull(message = "Bus journey date cannot be null!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate busJourneyDate;
	
	@ManyToOne
	 @JoinColumn(name = "routeId", nullable = false)
	private Route route;
	
	
	
	public Bus()
	{
		
	}

	public Bus(Integer busId, @NotNull(message = "Bus driver name cannot be null!") String driverName, String busType,
			@NotNull(message = "Start point cannot be null!") String startPoint,
			@NotNull(message = "Destination point cannot be null!") String endPoint,
			@NotNull(message = "Arrival time cannot be null!") LocalTime arrivalTime,
			@NotNull(message = "Departure time cannot be null!") LocalTime departureTime,
			@NotNull(message = "Total Seats cannot be null!") Integer seats,
			@NotNull(message = "Available seats cannot be null!") Integer availableSeats,
			@NotNull(message = "Fare cannot be null!") Integer farePerSeat,
			@NotNull(message = "Bus journey date cannot be null!") LocalDate busJourneyDate, Route route) {
		super();
		this.busId = busId;
		this.driverName = driverName;
		this.busType = busType;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.seats = seats;
		this.availableSeats = availableSeats;
		this.farePerSeat = farePerSeat;
		this.busJourneyDate = busJourneyDate;
		this.route = route;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndpoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Integer getFarePerSeat() {
		return farePerSeat;
	}

	public void setFarePerSeat(Integer farePerSeat) {
		this.farePerSeat = farePerSeat;
	}

	public LocalDate getBusJourneyDate() {
		return busJourneyDate;
	}

	public void setBusJourneyDate(LocalDate busJourneyDate) {
		this.busJourneyDate = busJourneyDate;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	


}
