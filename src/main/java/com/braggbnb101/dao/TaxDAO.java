package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Tax;





public interface TaxDAO extends GenericDAO<Tax, Integer> {
  
	List<Tax> findAll();
	






}


