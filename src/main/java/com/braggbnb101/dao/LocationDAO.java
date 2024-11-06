package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Location;





public interface LocationDAO extends GenericDAO<Location, Integer> {
  
	List<Location> findAll();
	






}


