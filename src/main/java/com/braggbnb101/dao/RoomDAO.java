package com.braggbnb101.dao;

import java.util.List;

import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.domain.Room;





public interface RoomDAO extends GenericDAO<Room, Integer> {
  
	List<Room> findAll();
	






}


