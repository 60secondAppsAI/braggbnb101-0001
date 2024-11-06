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
import com.braggbnb101.dao.TaxDAO;
import com.braggbnb101.domain.Tax;
import com.braggbnb101.dto.TaxDTO;
import com.braggbnb101.dto.TaxSearchDTO;
import com.braggbnb101.dto.TaxPageDTO;
import com.braggbnb101.dto.TaxConvertCriteriaDTO;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import com.braggbnb101.service.TaxService;
import com.braggbnb101.util.ControllerUtils;





@Service
public class TaxServiceImpl extends GenericServiceImpl<Tax, Integer> implements TaxService {

    private final static Logger logger = LoggerFactory.getLogger(TaxServiceImpl.class);

	@Autowired
	TaxDAO taxDao;

	


	@Override
	public GenericDAO<Tax, Integer> getDAO() {
		return (GenericDAO<Tax, Integer>) taxDao;
	}
	
	public List<Tax> findAll () {
		List<Tax> taxs = taxDao.findAll();
		
		return taxs;	
		
	}

	public ResultDTO addTax(TaxDTO taxDTO, RequestDTO requestDTO) {

		Tax tax = new Tax();

		tax.setTaxId(taxDTO.getTaxId());


		tax.setTaxAmount(taxDTO.getTaxAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		tax = taxDao.save(tax);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Tax> getAllTaxs(Pageable pageable) {
		return taxDao.findAll(pageable);
	}

	public Page<Tax> getAllTaxs(Specification<Tax> spec, Pageable pageable) {
		return taxDao.findAll(spec, pageable);
	}

	public ResponseEntity<TaxPageDTO> getTaxs(TaxSearchDTO taxSearchDTO) {
	
			Integer taxId = taxSearchDTO.getTaxId(); 
  			String sortBy = taxSearchDTO.getSortBy();
			String sortOrder = taxSearchDTO.getSortOrder();
			String searchQuery = taxSearchDTO.getSearchQuery();
			Integer page = taxSearchDTO.getPage();
			Integer size = taxSearchDTO.getSize();

	        Specification<Tax> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, taxId, "taxId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Tax> taxs = this.getAllTaxs(spec, pageable);
		
		//System.out.println(String.valueOf(taxs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(taxs.getTotalPages()));
		
		List<Tax> taxsList = taxs.getContent();
		
		TaxConvertCriteriaDTO convertCriteria = new TaxConvertCriteriaDTO();
		List<TaxDTO> taxDTOs = this.convertTaxsToTaxDTOs(taxsList,convertCriteria);
		
		TaxPageDTO taxPageDTO = new TaxPageDTO();
		taxPageDTO.setTaxs(taxDTOs);
		taxPageDTO.setTotalElements(taxs.getTotalElements());
		return ResponseEntity.ok(taxPageDTO);
	}

	public List<TaxDTO> convertTaxsToTaxDTOs(List<Tax> taxs, TaxConvertCriteriaDTO convertCriteria) {
		
		List<TaxDTO> taxDTOs = new ArrayList<TaxDTO>();
		
		for (Tax tax : taxs) {
			taxDTOs.add(convertTaxToTaxDTO(tax,convertCriteria));
		}
		
		return taxDTOs;

	}
	
	public TaxDTO convertTaxToTaxDTO(Tax tax, TaxConvertCriteriaDTO convertCriteria) {
		
		TaxDTO taxDTO = new TaxDTO();
		
		taxDTO.setTaxId(tax.getTaxId());

	
		taxDTO.setTaxAmount(tax.getTaxAmount());

	

		
		return taxDTO;
	}

	public ResultDTO updateTax(TaxDTO taxDTO, RequestDTO requestDTO) {
		
		Tax tax = taxDao.getById(taxDTO.getTaxId());

		tax.setTaxId(ControllerUtils.setValue(tax.getTaxId(), taxDTO.getTaxId()));

		tax.setTaxAmount(ControllerUtils.setValue(tax.getTaxAmount(), taxDTO.getTaxAmount()));



        tax = taxDao.save(tax);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TaxDTO getTaxDTOById(Integer taxId) {
	
		Tax tax = taxDao.getById(taxId);
			
		
		TaxConvertCriteriaDTO convertCriteria = new TaxConvertCriteriaDTO();
		return(this.convertTaxToTaxDTO(tax,convertCriteria));
	}







}
