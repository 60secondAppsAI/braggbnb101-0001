package com.braggbnb101.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<RoomDTO> rooms;
}





