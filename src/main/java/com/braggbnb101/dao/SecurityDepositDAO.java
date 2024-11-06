package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.SecurityDeposit;





public interface SecurityDepositDAO extends GenericDAO<SecurityDeposit, Integer> {
  
	List<SecurityDeposit> findAll();
	






}


