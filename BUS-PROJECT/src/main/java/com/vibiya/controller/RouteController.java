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
import com.vibiya.exception.RouteException;
import com.vibiya.model.Route;
import com.vibiya.service.RouteService;

import jakarta.validation.Valid;

@RestController
public class RouteController {
	
	@Autowired
	RouteService routeService;
	
	@PostMapping("/addRoute")
   public ResponseEntity<Route> addRoute( @Valid @RequestBody Route route,@RequestParam String key) throws RouteException, AdminException
     {
		
	return new ResponseEntity<Route>(routeService.addRoute(route,key),HttpStatus.ACCEPTED);
	
      }
	
	@PutMapping("/updateRoute")
   public ResponseEntity<Route> updateRoute( @Valid @RequestBody Route route,@RequestParam String key) throws RouteException, AdminException
		
   {
		return new  ResponseEntity<Route>(routeService.updateRoute(route,key),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteRoute")
    public ResponseEntity<Route> deleteRoute(@PathVariable Integer routeId,@RequestParam String key) throws RouteException, AdminException
		
    {
		
    return new ResponseEntity<Route>(routeService.deleteRoute(routeId,key),HttpStatus.GONE);
	 }
    
	@GetMapping("/findAllRoute")
public ResponseEntity<List<Route>> findAllRoute(@RequestParam String key) throws RouteException, AdminException
    {
		
		return new ResponseEntity<List<Route>>(routeService.findAllRoute(key),HttpStatus.OK);
	}

     @GetMapping("/findRouteById/{routeId}")
public ResponseEntity<Route> findRouteById(@PathVariable Integer routeId,@RequestParam String key) throws RouteException, AdminException
{
	
	return new ResponseEntity<Route>(routeService.findRouteById(routeId,key),HttpStatus.OK);
}


	
	
	
	
	

}
