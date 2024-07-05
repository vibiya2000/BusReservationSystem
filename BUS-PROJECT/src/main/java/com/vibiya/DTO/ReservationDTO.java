package com.vibiya.DTO;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class ReservationDTO {
	
   @NotBlank(message = "Start point cannot be blank!")
	private String source;
	
   @NotBlank(message = "Destination point cannot be blank!")
	private String destination;
	
	@NotNull
	private Integer noOfSeatsToBook;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate journeyDate;
	
	private BusDTO busDTO;
	
	
	
	public ReservationDTO(@NotBlank(message = "Start point cannot be blank!") String source,
			@NotBlank(message = "Destination point cannot be blank!") String destination,
			@NotNull Integer noOfSeatsToBook, @NotNull LocalDate journeyDate, BusDTO busDTO) {
		super();
		this.source = source;
		this.destination = destination;
		this.noOfSeatsToBook = noOfSeatsToBook;
		this.journeyDate = journeyDate;
		this.busDTO = busDTO;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getNoOfSeatsToBook() {
		return noOfSeatsToBook;
	}

	public void setNoOfSeatsToBook(Integer noOfSeatsToBook) {
		this.noOfSeatsToBook = noOfSeatsToBook;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public BusDTO getBusDTO() {
		return busDTO;
	}

	public void setBusDTO(BusDTO busDTO) {
		this.busDTO = busDTO;
	}
	
	
	
	
	
	
	
	
	
	
}
