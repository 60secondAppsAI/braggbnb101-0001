package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Discount;





public interface DiscountDAO extends GenericDAO<Discount, Integer> {
  
	List<Discount> findAll();
	






}


