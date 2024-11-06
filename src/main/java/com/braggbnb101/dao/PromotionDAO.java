package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Promotion;





public interface PromotionDAO extends GenericDAO<Promotion, Integer> {
  
	List<Promotion> findAll();
	






}


