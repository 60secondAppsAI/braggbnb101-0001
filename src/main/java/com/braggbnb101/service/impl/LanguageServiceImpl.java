package com.braggbnb101.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb101.dao.GenericDAO;
import com.braggbnb101.service.GenericService;
import com.braggbnb101.service.impl.GenericServiceImpl;
import com.braggbnb101.dao.LanguageDAO;
import com.braggbnb101.domain.Language;
import com.braggbnb101.dto.LanguageDTO;
import com.braggbnb101.dto.LanguageSearchDTO;
import com.braggbnb101.dto.LanguagePageDTO;
import com.braggbnb101.dto.LanguageConvertCriteriaDTO;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import com.braggbnb101.service.LanguageService;
import com.braggbnb101.util.ControllerUtils;





@Service
public class LanguageServiceImpl extends GenericServiceImpl<Language, Integer> implements LanguageService {

    private final static Logger logger = LoggerFactory.getLogger(LanguageServiceImpl.class);

	@Autowired
	LanguageDAO languageDao;

	


	@Override
	public GenericDAO<Language, Integer> getDAO() {
		return (GenericDAO<Language, Integer>) languageDao;
	}
	
	public List<Language> findAll () {
		List<Language> languages = languageDao.findAll();
		
		return languages;	
		
	}

	public ResultDTO addLanguage(LanguageDTO languageDTO, RequestDTO requestDTO) {

		Language language = new Language();

		language.setLanguageId(languageDTO.getLanguageId());


		language.setLanguage(languageDTO.getLanguage());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		language = languageDao.save(language);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Language> getAllLanguages(Pageable pageable) {
		return languageDao.findAll(pageable);
	}

	public Page<Language> getAllLanguages(Specification<Language> spec, Pageable pageable) {
		return languageDao.findAll(spec, pageable);
	}

	public ResponseEntity<LanguagePageDTO> getLanguages(LanguageSearchDTO languageSearchDTO) {
	
			Integer languageId = languageSearchDTO.getLanguageId(); 
 			String language = languageSearchDTO.getLanguage(); 
 			String sortBy = languageSearchDTO.getSortBy();
			String sortOrder = languageSearchDTO.getSortOrder();
			String searchQuery = languageSearchDTO.getSearchQuery();
			Integer page = languageSearchDTO.getPage();
			Integer size = languageSearchDTO.getSize();

	        Specification<Language> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, languageId, "languageId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, language, "language"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("language")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Language> languages = this.getAllLanguages(spec, pageable);
		
		//System.out.println(String.valueOf(languages.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(languages.getTotalPages()));
		
		List<Language> languagesList = languages.getContent();
		
		LanguageConvertCriteriaDTO convertCriteria = new LanguageConvertCriteriaDTO();
		List<LanguageDTO> languageDTOs = this.convertLanguagesToLanguageDTOs(languagesList,convertCriteria);
		
		LanguagePageDTO languagePageDTO = new LanguagePageDTO();
		languagePageDTO.setLanguages(languageDTOs);
		languagePageDTO.setTotalElements(languages.getTotalElements());
		return ResponseEntity.ok(languagePageDTO);
	}

	public List<LanguageDTO> convertLanguagesToLanguageDTOs(List<Language> languages, LanguageConvertCriteriaDTO convertCriteria) {
		
		List<LanguageDTO> languageDTOs = new ArrayList<LanguageDTO>();
		
		for (Language language : languages) {
			languageDTOs.add(convertLanguageToLanguageDTO(language,convertCriteria));
		}
		
		return languageDTOs;

	}
	
	public LanguageDTO convertLanguageToLanguageDTO(Language language, LanguageConvertCriteriaDTO convertCriteria) {
		
		LanguageDTO languageDTO = new LanguageDTO();
		
		languageDTO.setLanguageId(language.getLanguageId());

	
		languageDTO.setLanguage(language.getLanguage());

	

		
		return languageDTO;
	}

	public ResultDTO updateLanguage(LanguageDTO languageDTO, RequestDTO requestDTO) {
		
		Language language = languageDao.getById(languageDTO.getLanguageId());

		language.setLanguageId(ControllerUtils.setValue(language.getLanguageId(), languageDTO.getLanguageId()));

		language.setLanguage(ControllerUtils.setValue(language.getLanguage(), languageDTO.getLanguage()));



        language = languageDao.save(language);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LanguageDTO getLanguageDTOById(Integer languageId) {
	
		Language language = languageDao.getById(languageId);
			
		
		LanguageConvertCriteriaDTO convertCriteria = new LanguageConvertCriteriaDTO();
		return(this.convertLanguageToLanguageDTO(language,convertCriteria));
	}







}
