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

import com.braggbnb101.domain.PropertyImage;
import com.braggbnb101.dto.PropertyImageDTO;
import com.braggbnb101.dto.PropertyImageSearchDTO;
import com.braggbnb101.dto.PropertyImagePageDTO;
import com.braggbnb101.service.PropertyImageService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/propertyImage")
@RestController
public class PropertyImageController {

	private final static Logger logger = LoggerFactory.getLogger(PropertyImageController.class);

	@Autowired
	PropertyImageService propertyImageService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PropertyImage> getAll() {

		List<PropertyImage> propertyImages = propertyImageService.findAll();
		
		return propertyImages;	
	}

	@GetMapping(value = "/{propertyImageId}")
	@ResponseBody
	public PropertyImageDTO getPropertyImage(@PathVariable Integer propertyImageId) {
		
		return (propertyImageService.getPropertyImageDTOById(propertyImageId));
	}

 	@RequestMapping(value = "/addPropertyImage", method = RequestMethod.POST)
	public ResponseEntity<?> addPropertyImage(@RequestBody PropertyImageDTO propertyImageDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = propertyImageService.addPropertyImage(propertyImageDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/propertyImages")
	public ResponseEntity<PropertyImagePageDTO> getPropertyImages(PropertyImageSearchDTO propertyImageSearchDTO) {
 
		return propertyImageService.getPropertyImages(propertyImageSearchDTO);
	}	

	@RequestMapping(value = "/updatePropertyImage", method = RequestMethod.POST)
	public ResponseEntity<?> updatePropertyImage(@RequestBody PropertyImageDTO propertyImageDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = propertyImageService.updatePropertyImage(propertyImageDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
