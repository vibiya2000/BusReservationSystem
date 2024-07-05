package com.vibiya.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.DTO.ReservationDTO;
import com.vibiya.exception.BusException;
import com.vibiya.exception.ReservationException;
import com.vibiya.exception.UserException;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.model.Bus;
import com.vibiya.model.Reservation;
import com.vibiya.model.User;
import com.vibiya.model.UserLoginSession;
import com.vibiya.repository.AdminSessionDAO;
import com.vibiya.repository.BusDAO;
import com.vibiya.repository.ReservationDAO;
import com.vibiya.repository.UserDAO;
import com.vibiya.repository.UserSessionDAO;

@Service
public class ReservationService {

	@Autowired
	private ReservationDAO reservationDAO;
	
	@Autowired
	private BusDAO busDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AdminSessionDAO adminSessionDAO;
	
	
public Reservation addReservation(ReservationDTO reservationDTO, String key) throws ReservationException, BusException,UserException {
		
	UserLoginSession loggedInUser= userSessionDAO.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key to reserve seats!");
		}
		
		User user = userDAO.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User not found!"));
		
		
		Optional<Bus> opt = busDAO.findById(reservationDTO.getBusDTO().getBusId());
		
		Bus bus = opt.orElseThrow(()-> new ReservationException("Invalid bus details!"));
		
		if(reservationDTO.getJourneyDate().isBefore(LocalDate.now())) throw new ReservationException("Please enter future date!");
		
		if(!bus.getBusJourneyDate().isEqual(reservationDTO.getJourneyDate())) throw new ReservationException("Bus is not available on "+reservationDTO.getJourneyDate());
		
		if(!reservationDTO.getSource().equalsIgnoreCase(bus.getStartPoint()) || !reservationDTO.getDestination().equalsIgnoreCase(bus.getEndpoint()))
			throw new ReservationException("Bus is not available on route : "+ reservationDTO.getSource()+" - "+reservationDTO.getDestination());
		
		int seatsAvailable = bus.getAvailableSeats();
		if(seatsAvailable < reservationDTO.getNoOfSeatsToBook()) throw new ReservationException("Reservation Failed! Available seats: "+seatsAvailable);
		
		Reservation reservation = new Reservation();
		
		bus.setAvailableSeats(seatsAvailable - reservationDTO.getNoOfSeatsToBook());
		
		Bus updatedBus =busDAO.save(bus);
		
		reservation.setBus(updatedBus);
		
		reservation.setReservationStatus("Successfull");
		reservation.setReservationDate(LocalDate.now());
		reservation.setReservationTime(LocalTime.now());
		reservation.setSource(bus.getStartPoint());
		reservation.setDestination(bus.getEndpoint());
		reservation.setNoOfSeatsBooked(reservationDTO.getNoOfSeatsToBook());
		reservation.setFare(bus.getFarePerSeat()*(reservationDTO.getNoOfSeatsToBook()));
		reservation.setJourneyDate(reservationDTO.getJourneyDate());
		
		List<Reservation> userReservations =user.getReservations();
		userReservations.add(reservation);
		
		user.setReservations(userReservations);
		
		reservation.setUser(user);
		
		Reservation savedReservation = reservationDAO.save(reservation);
		
		if(savedReservation == null) throw new ReservationException("Could not reserve the seats");
		return savedReservation;
	}




	public Reservation deleteReservation(Integer reservationId,String key) throws ReservationException, BusException, UserException {
		
		UserLoginSession loggedInUser= userSessionDAO.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key to reserve seats!");
		}
		
		User user = userDAO.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User not found!"));
		
		List<Reservation> reservationList = user.getReservations();
		
		boolean validReservationId= false;
		
		for(int i=0;i<reservationList.size();i++) {
			
			if(reservationList.get(i).getReservationId() == reservationId)
			{
				validReservationId = true;
				
				Optional<Reservation> Opt = reservationDAO.findById(reservationId);
				Reservation foundReservation = Opt.orElseThrow(()-> new ReservationException("No reservation found!"));
				Bus bus = foundReservation.getBus();
				
				if(foundReservation.getJourneyDate().isBefore(LocalDate.now())) throw new ReservationException("Cannot cancel! Journey completed.");
				
				bus.setAvailableSeats(bus.getAvailableSeats()+foundReservation.getNoOfSeatsBooked());
				busDAO.save(bus);
				
				reservationList.remove(i);
				reservationDAO.delete(foundReservation);
				return foundReservation;
			}
		}
		
		if(!validReservationId) throw new UserException("Reservation Id:"+reservationId+" do not belong to the current user!");
		return null;
	}


	public Reservation findReservationById(Integer reservationId,String key) throws ReservationException {
		
		AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new ReservationException("Please provide a valid key to view reservation!");
		}
		
		Optional<Reservation> Opt = reservationDAO.findById(reservationId);
		Reservation foundReservation = Opt.orElseThrow(()-> new ReservationException("No reservation found!"));
		return foundReservation;
	}
	
public List<Reservation> findAllReservation(String key) throws ReservationException {
		
	AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new ReservationException("Please provide a valid key to view all reservations!");
		}
		
		List<Reservation> reservationList = reservationDAO.findAll();
		if(reservationList.isEmpty()) throw new ReservationException("No reservations found!");
		return reservationList;
	}




	public List<Reservation> findReservationByUser(String key) throws ReservationException, UserException {
		
		UserLoginSession loggedInUser= userSessionDAO.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key to view reservation!");
		}
		
		User user = userDAO.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User not found!"));
		
		return user.getReservations();
	}

	
}
