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

import com.braggbnb101.domain.Tax;
import com.braggbnb101.dto.TaxDTO;
import com.braggbnb101.dto.TaxSearchDTO;
import com.braggbnb101.dto.TaxPageDTO;
import com.braggbnb101.service.TaxService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/tax")
@RestController
public class TaxController {

	private final static Logger logger = LoggerFactory.getLogger(TaxController.class);

	@Autowired
	TaxService taxService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Tax> getAll() {

		List<Tax> taxs = taxService.findAll();
		
		return taxs;	
	}

	@GetMapping(value = "/{taxId}")
	@ResponseBody
	public TaxDTO getTax(@PathVariable Integer taxId) {
		
		return (taxService.getTaxDTOById(taxId));
	}

 	@RequestMapping(value = "/addTax", method = RequestMethod.POST)
	public ResponseEntity<?> addTax(@RequestBody TaxDTO taxDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = taxService.addTax(taxDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/taxs")
	public ResponseEntity<TaxPageDTO> getTaxs(TaxSearchDTO taxSearchDTO) {
 
		return taxService.getTaxs(taxSearchDTO);
	}	

	@RequestMapping(value = "/updateTax", method = RequestMethod.POST)
	public ResponseEntity<?> updateTax(@RequestBody TaxDTO taxDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = taxService.updateTax(taxDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
