package com.braggbnb101.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropertyImagePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<PropertyImageDTO> propertyImages;
}





