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

import com.braggbnb101.domain.Language;
import com.braggbnb101.dto.LanguageDTO;
import com.braggbnb101.dto.LanguageSearchDTO;
import com.braggbnb101.dto.LanguagePageDTO;
import com.braggbnb101.service.LanguageService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/language")
@RestController
public class LanguageController {

	private final static Logger logger = LoggerFactory.getLogger(LanguageController.class);

	@Autowired
	LanguageService languageService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Language> getAll() {

		List<Language> languages = languageService.findAll();
		
		return languages;	
	}

	@GetMapping(value = "/{languageId}")
	@ResponseBody
	public LanguageDTO getLanguage(@PathVariable Integer languageId) {
		
		return (languageService.getLanguageDTOById(languageId));
	}

 	@RequestMapping(value = "/addLanguage", method = RequestMethod.POST)
	public ResponseEntity<?> addLanguage(@RequestBody LanguageDTO languageDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = languageService.addLanguage(languageDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/languages")
	public ResponseEntity<LanguagePageDTO> getLanguages(LanguageSearchDTO languageSearchDTO) {
 
		return languageService.getLanguages(languageSearchDTO);
	}	

	@RequestMapping(value = "/updateLanguage", method = RequestMethod.POST)
	public ResponseEntity<?> updateLanguage(@RequestBody LanguageDTO languageDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = languageService.updateLanguage(languageDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
