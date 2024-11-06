package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Language;





public interface LanguageDAO extends GenericDAO<Language, Integer> {
  
	List<Language> findAll();
	






}


