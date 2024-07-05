package com.vibiya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.Bus;

public interface BusDAO extends JpaRepository<Bus, Integer>{
	
	public List<Bus> findByBusType(String busType);

}
