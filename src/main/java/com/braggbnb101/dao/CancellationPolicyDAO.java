package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.CancellationPolicy;





public interface CancellationPolicyDAO extends GenericDAO<CancellationPolicy, Integer> {
  
	List<CancellationPolicy> findAll();
	






}


