package com.vibiya.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.BusException;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.model.Bus;
import com.vibiya.model.Route;
import com.vibiya.repository.AdminSessionDAO;
import com.vibiya.repository.BusDAO;
import com.vibiya.repository.RouteDAO;

@Service
public class BusService {
	
	@Autowired
	private BusDAO busDAO;
	
	@Autowired
	private RouteDAO routeDAO;
	
	@Autowired
	private AdminSessionDAO adminSessionDAO;
	
public Bus addBus(Bus bus,String key) throws BusException, AdminException {
		
		AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key to add bus!");
		}
		
		//Optional<Route> routeOptional = routeDAO.findById(bus.getRoute().getRouteId());
		Route route=routeDAO.findByRouteFromAndRouteTo(bus.getStartPoint(), bus.getEndpoint());
		
		if(route != null) {
			//Route route = routeOptional.get();
			route.getBuses().add(bus);
			bus.setRoute(route);
			return busDAO.save(bus);
		}
		else
			throw new BusException("Bus detail is not correct");
	}
   
public Bus updateBus(Bus bus,String key) throws BusException, AdminException {
	
	AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
	
	if(loggedInAdmin == null) {
		throw new AdminException("Please provide a valid key to update bus!");
	}
	
	Optional<Bus> existingBusOpt=busDAO.findById(bus.getBusId());
	
	if(existingBusOpt.isPresent()) {
		
		Bus existingBus = existingBusOpt.get();
		
		if(existingBus.getAvailableSeats()!=existingBus.getSeats()) throw new BusException("Cannot update already scheduled bus!");
		
		//Optional<Route> routeOptional = routeDAO.findById(bus.getRoute().getRouteId());
		Route route=routeDAO.findByRouteFromAndRouteTo(bus.getStartPoint(), bus.getEndpoint());
		if(route == null) throw new BusException("Invalid route!");
		//Route route = routeOptional.get();
		bus.setRoute(route);
		return busDAO.save(bus);
	}
	else
		throw new BusException("Bus doesn't exist with busId : "+ bus.getBusId());

}

    
public Bus deleteBus(Integer busId,String key) throws BusException, AdminException {
	
	AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
	
	if(loggedInAdmin == null) {
		throw new AdminException("Please provide a valid key to delete bus!");
	}
	
	Optional<Bus> bus=busDAO.findById(busId);
	
	
	if(bus.isPresent()) {
		Bus existingBus = bus.get();			
		
		if(LocalDate.now().isBefore(existingBus.getBusJourneyDate()) && existingBus.getAvailableSeats()!=existingBus.getSeats())
			throw new BusException("Cannot delete as the bus is scheduled and reservations are booked for the bus.");
		
		
		busDAO.delete(existingBus);
		
		return existingBus;
	}
	else
		throw new BusException("Bus doesn't exist with busId : "+busId);
	
}

public List<Bus> findByBusType(String BusType) throws BusException {
	List<Bus> listOfBusType = busDAO.findByBusType(BusType);
	
	if(listOfBusType.size() >0)
		return listOfBusType;
	else
		throw new BusException("There is no bus of type "+ BusType);

}

public List<Bus> findAllBus() throws BusException {
	
	List<Bus> buses= busDAO.findAll();
	if(buses.size()>0)
		return buses;
	else
		throw new BusException("There is no bus availabe now");

}


public Bus findByBusId(Integer busId) throws BusException {
	Optional<Bus> bus=busDAO.findById(busId);
	
	if(bus.isPresent()) {
		Bus existingBus = bus.get();
		return existingBus;
	}
	else
		throw new BusException("Bus doesn't exist with busId : "+busId);
}


}
