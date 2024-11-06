package com.braggbnb101.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb101.domain.Language;
import com.braggbnb101.dto.LanguageDTO;
import com.braggbnb101.dto.LanguageSearchDTO;
import com.braggbnb101.dto.LanguagePageDTO;
import com.braggbnb101.dto.LanguageConvertCriteriaDTO;
import com.braggbnb101.service.GenericService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LanguageService extends GenericService<Language, Integer> {

	List<Language> findAll();

	ResultDTO addLanguage(LanguageDTO languageDTO, RequestDTO requestDTO);

	ResultDTO updateLanguage(LanguageDTO languageDTO, RequestDTO requestDTO);

    Page<Language> getAllLanguages(Pageable pageable);

    Page<Language> getAllLanguages(Specification<Language> spec, Pageable pageable);

	ResponseEntity<LanguagePageDTO> getLanguages(LanguageSearchDTO languageSearchDTO);
	
	List<LanguageDTO> convertLanguagesToLanguageDTOs(List<Language> languages, LanguageConvertCriteriaDTO convertCriteria);

	LanguageDTO getLanguageDTOById(Integer languageId);







}





