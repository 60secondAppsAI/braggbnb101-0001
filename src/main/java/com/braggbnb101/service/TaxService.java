package com.braggbnb101.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb101.domain.Tax;
import com.braggbnb101.dto.TaxDTO;
import com.braggbnb101.dto.TaxSearchDTO;
import com.braggbnb101.dto.TaxPageDTO;
import com.braggbnb101.dto.TaxConvertCriteriaDTO;
import com.braggbnb101.service.GenericService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TaxService extends GenericService<Tax, Integer> {

	List<Tax> findAll();

	ResultDTO addTax(TaxDTO taxDTO, RequestDTO requestDTO);

	ResultDTO updateTax(TaxDTO taxDTO, RequestDTO requestDTO);

    Page<Tax> getAllTaxs(Pageable pageable);

    Page<Tax> getAllTaxs(Specification<Tax> spec, Pageable pageable);

	ResponseEntity<TaxPageDTO> getTaxs(TaxSearchDTO taxSearchDTO);
	
	List<TaxDTO> convertTaxsToTaxDTOs(List<Tax> taxs, TaxConvertCriteriaDTO convertCriteria);

	TaxDTO getTaxDTOById(Integer taxId);







}





