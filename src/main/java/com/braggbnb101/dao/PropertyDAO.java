package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Property;





public interface PropertyDAO extends GenericDAO<Property, Integer> {
  
	List<Property> findAll();
	






}


