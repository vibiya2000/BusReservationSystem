package com.vibiya.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.RouteException;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.model.Bus;
import com.vibiya.model.Route;
import com.vibiya.repository.AdminDAO;
import com.vibiya.repository.AdminSessionDAO;
import com.vibiya.repository.BusDAO;
import com.vibiya.repository.RouteDAO;

@Service
public class RouteService {
	
	@Autowired
	RouteDAO routeDAO;
	@Autowired
	AdminSessionDAO adminSessionDAO;
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	BusDAO busDAO;
	
	public Route addRoute(Route route,String key) throws AdminException, RouteException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new AdminException("Please provide valid key");
		}
		Route validRoute = routeDAO.findByRouteFromAndRouteTo(route.getRouteFrom(),route.getRouteTo());
		
		if(validRoute!=null)
		{
			throw new RouteException("Route from: "+route.getRouteFrom() +"to "+route.getRouteTo()+" already present");
		}
		
		List<Bus> busList = new ArrayList<>();
		
		route.setBuses(busList);
		return routeDAO.save(route);
		
	}
	
	public Route updateRoute(Route route,String key) throws AdminException, RouteException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new AdminException("Please provide valid key");
		}
		Optional<Route> existedRoute = routeDAO.findById(route.getRouteId());
		if(existedRoute.isPresent()) 
		{
			Route presentRoute =existedRoute.get();
List<Bus> busList = presentRoute.getBuses();
			
			if(!busList.isEmpty()) throw new RouteException("Cannot update running route! Buses are already scheduled in the route.");
			
			return routeDAO.save(route);
		}
		else
			throw new RouteException("Route doesn't exist with routeId : "+ route.getRouteId());

		}
	
   public Route deleteRoute(Integer routeId,String key) throws RouteException, AdminException 
   {
		
		AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key to add route!");
		}
	
		Optional<Route> route=routeDAO.findById(routeId);
		
		if(route.isPresent()) {
			Route existingRoute=route.get();
			routeDAO.delete(existingRoute);
			return existingRoute;
		}
		else
			throw new RouteException("There is no route of routeId : "+ routeId);

	}

   public Route findRouteById(Integer routeId,String key) throws RouteException, AdminException 
   {
	   AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key!");
		}
	Optional<Route> existedRoute=routeDAO.findById(routeId);
	
	if(existedRoute.isPresent()) {
		return existedRoute.get();
	}
	else
		throw new RouteException("There is no route present of routeId :" + routeId);
	
   } 

   public List<Route> findAllRoute(String key) throws RouteException, AdminException {
	   AdminLoginSession loggedInAdmin= adminSessionDAO.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key!");
		}
		
		List<Route> routes=routeDAO.findAll();
		
		if(routes.size()>0)
			return routes;
		else
			throw new RouteException("There is no route available");
			
	}

	
	
	
	}


