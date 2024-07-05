package com.vibiya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.BusException;
import com.vibiya.model.Bus;
import com.vibiya.service.BusService;

import jakarta.validation.Valid;

@RestController
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/addBus")
	public ResponseEntity<Bus> addBus( @Valid @RequestBody Bus bus,@RequestParam String key) throws BusException, AdminException
	{
		
		return new ResponseEntity<Bus>(busService.addBus(bus,key),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updateBus")
	public ResponseEntity<Bus> updateBus(@Valid @RequestBody Bus bus,@RequestParam String key) throws BusException, AdminException
	{
		
		return new ResponseEntity<Bus>(busService.updateBus(bus,key),HttpStatus.FOUND);
	}
	
	
	@DeleteMapping("/deleteBus/{busId}")
	public ResponseEntity<Bus> deleteBus(@PathVariable Integer busId,@RequestParam String key) throws BusException, AdminException
	{
		
		return new ResponseEntity<Bus>(busService.deleteBus(busId,key),HttpStatus.OK);
	}
	
	
	@GetMapping("/findByBusType/{busType}")
	public ResponseEntity<List<Bus>> findByBusType(@PathVariable String busType) throws BusException
	{
		
		return new ResponseEntity<List<Bus>>(busService.findByBusType(busType),HttpStatus.OK);
	}
	
	
	@GetMapping("/findAllBus")
	public ResponseEntity<List<Bus>> findAllBus() throws BusException
	{
		
		return new ResponseEntity<List<Bus>>(busService.findAllBus(),HttpStatus.OK);
	}
	
	
	@GetMapping("/findByBusId/{busId}")
	public ResponseEntity<Bus> getBusesById(@RequestParam Integer busId) throws BusException
	{
		
		return new ResponseEntity<Bus>(busService.findByBusId(busId),HttpStatus.OK);
	}
}
