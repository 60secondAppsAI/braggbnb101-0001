package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.PropertyImage;





public interface PropertyImageDAO extends GenericDAO<PropertyImage, Integer> {
  
	List<PropertyImage> findAll();
	






}


