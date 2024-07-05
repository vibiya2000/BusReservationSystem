package com.vibiya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.DTO.ReservationDTO;
import com.vibiya.exception.AdminException;
import com.vibiya.exception.BusException;
import com.vibiya.exception.ReservationException;
import com.vibiya.exception.UserException;
import com.vibiya.model.Reservation;
import com.vibiya.service.ReservationService;

import jakarta.validation.Valid;

@RestController
public class ReservationController {
	

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/user/addReservation")
	public ResponseEntity<Reservation> addReservation(@Valid @RequestBody ReservationDTO reservationDTO ,@RequestParam(required = false) String key) throws ReservationException, BusException, UserException
	{
		Reservation savedReservation =reservationService.addReservation(reservationDTO,key);
		return new ResponseEntity<Reservation>(savedReservation,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/user/deleteReservation/{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer reservationId,@RequestParam String key ) throws ReservationException, BusException, UserException
	{
		Reservation deletedReservation = reservationService.deleteReservation(reservationId,key);
		return new ResponseEntity<Reservation>(deletedReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/admin/findReservationById/{id}")
	public ResponseEntity<Reservation> findReservationById(@PathVariable("id") Integer reservationId,@RequestParam String key) throws ReservationException, AdminException
	{
		Reservation foundReservation = reservationService.findReservationById(reservationId,key);
		return new ResponseEntity<Reservation>(foundReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/admin/findAllReservation")
	public ResponseEntity<List<Reservation>> findAllReservation(@RequestParam String key) throws ReservationException
	{
		List<Reservation> reservationList = reservationService.findAllReservation(key);
		return new ResponseEntity<List<Reservation>>(reservationList,HttpStatus.OK);
	}
	
	
	@GetMapping("/user/findReservationByUser")
	public ResponseEntity<List<Reservation>> findReservationByUser(@RequestParam String key) throws ReservationException, UserException
	{
		
		List<Reservation> reservationList = reservationService.findReservationByUser(key);
		return new ResponseEntity<List<Reservation>>(reservationList,HttpStatus.OK);
	}

}
