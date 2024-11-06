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

import com.braggbnb101.domain.CancellationPolicy;
import com.braggbnb101.dto.CancellationPolicyDTO;
import com.braggbnb101.dto.CancellationPolicySearchDTO;
import com.braggbnb101.dto.CancellationPolicyPageDTO;
import com.braggbnb101.service.CancellationPolicyService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/cancellationPolicy")
@RestController
public class CancellationPolicyController {

	private final static Logger logger = LoggerFactory.getLogger(CancellationPolicyController.class);

	@Autowired
	CancellationPolicyService cancellationPolicyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CancellationPolicy> getAll() {

		List<CancellationPolicy> cancellationPolicys = cancellationPolicyService.findAll();
		
		return cancellationPolicys;	
	}

	@GetMapping(value = "/{cancellationPolicyId}")
	@ResponseBody
	public CancellationPolicyDTO getCancellationPolicy(@PathVariable Integer cancellationPolicyId) {
		
		return (cancellationPolicyService.getCancellationPolicyDTOById(cancellationPolicyId));
	}

 	@RequestMapping(value = "/addCancellationPolicy", method = RequestMethod.POST)
	public ResponseEntity<?> addCancellationPolicy(@RequestBody CancellationPolicyDTO cancellationPolicyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cancellationPolicyService.addCancellationPolicy(cancellationPolicyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/cancellationPolicys")
	public ResponseEntity<CancellationPolicyPageDTO> getCancellationPolicys(CancellationPolicySearchDTO cancellationPolicySearchDTO) {
 
		return cancellationPolicyService.getCancellationPolicys(cancellationPolicySearchDTO);
	}	

	@RequestMapping(value = "/updateCancellationPolicy", method = RequestMethod.POST)
	public ResponseEntity<?> updateCancellationPolicy(@RequestBody CancellationPolicyDTO cancellationPolicyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cancellationPolicyService.updateCancellationPolicy(cancellationPolicyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
