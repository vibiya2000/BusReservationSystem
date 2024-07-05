package com.vibiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.Route;

public interface RouteDAO extends JpaRepository<Route,Integer>
{
	public Route findByRouteFromAndRouteTo(String RouteFrom,String RouteTo); 

}
