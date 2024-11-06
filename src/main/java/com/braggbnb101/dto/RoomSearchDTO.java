package com.braggbnb101.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer roomId;
	
	private String roomType;
	
	private Double pricePerNight;
	
	private Boolean availability;
	
}
