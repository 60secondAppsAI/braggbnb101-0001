package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Wishlist;





public interface WishlistDAO extends GenericDAO<Wishlist, Integer> {
  
	List<Wishlist> findAll();
	






}


