package com.vibiya.model;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Route {
	
	@Id
	@Column(unique = true)
	private Integer routeId;
	@NotBlank(message = "Start point cannot be blank!")
	private String routeFrom;
	@NotBlank(message = "Destination point cannot be blank!")
	private String routeTo;
	private String distance;
	
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
	private List<Bus> buses = new ArrayList<>();

	
	public Route()
	{
		
	}
	public Route(Integer routeId, @NotBlank(message = "Start point cannot be blank!") String routeFrom,
			@NotBlank(message = "Destination point cannot be blank!") String routeTo, String distance, List<Bus> buses) {
		super();
		this.routeId = routeId;
		this.routeFrom =routeFrom ;
		this.routeTo = routeTo;
		this.distance = distance;
		this.buses = buses;
	}
	
	

	public Integer getRouteId() {
		return routeId;
	}

    public void setRouteId(Integer routeId) {
		this.routeId = routeId;
    }

     public String getRouteFrom() {
		return routeFrom;
	}



	public void setRouteFrom(String from) {
		routeFrom = from;
	}



	public String getRouteTo() {
		return routeTo;
	}



	public void setRouteTo(String to) {
		routeTo = to;
	}



	public String getDistance() {
		return distance;
	}



	public void setDistance(String distance) {
		this.distance = distance;
	}



	public List<Bus> getBuses() {
		return buses;
	}



	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	
	
	


}
