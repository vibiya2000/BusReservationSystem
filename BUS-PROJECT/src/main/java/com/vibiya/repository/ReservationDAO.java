package com.vibiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.Reservation;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

}
