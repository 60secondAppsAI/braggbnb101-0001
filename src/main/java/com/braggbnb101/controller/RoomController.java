package com.braggbnb101.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbnb101.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbnb101.domain.Room;
import com.braggbnb101.dto.RoomDTO;
import com.braggbnb101.dto.RoomSearchDTO;
import com.braggbnb101.dto.RoomPageDTO;
import com.braggbnb101.service.RoomService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/room")
@RestController
public class RoomController {

	private final static Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	RoomService roomService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Room> getAll() {

		List<Room> rooms = roomService.findAll();
		
		return rooms;	
	}

	@GetMapping(value = "/{roomId}")
	@ResponseBody
	public RoomDTO getRoom(@PathVariable Integer roomId) {
		
		return (roomService.getRoomDTOById(roomId));
	}

 	@RequestMapping(value = "/addRoom", method = RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody RoomDTO roomDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = roomService.addRoom(roomDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/rooms")
	public ResponseEntity<RoomPageDTO> getRooms(RoomSearchDTO roomSearchDTO) {
 
		return roomService.getRooms(roomSearchDTO);
	}	

	@RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
	public ResponseEntity<?> updateRoom(@RequestBody RoomDTO roomDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = roomService.updateRoom(roomDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
