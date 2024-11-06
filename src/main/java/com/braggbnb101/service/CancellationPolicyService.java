package com.braggbnb101.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb101.domain.CancellationPolicy;
import com.braggbnb101.dto.CancellationPolicyDTO;
import com.braggbnb101.dto.CancellationPolicySearchDTO;
import com.braggbnb101.dto.CancellationPolicyPageDTO;
import com.braggbnb101.dto.CancellationPolicyConvertCriteriaDTO;
import com.braggbnb101.service.GenericService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CancellationPolicyService extends GenericService<CancellationPolicy, Integer> {

	List<CancellationPolicy> findAll();

	ResultDTO addCancellationPolicy(CancellationPolicyDTO cancellationPolicyDTO, RequestDTO requestDTO);

	ResultDTO updateCancellationPolicy(CancellationPolicyDTO cancellationPolicyDTO, RequestDTO requestDTO);

    Page<CancellationPolicy> getAllCancellationPolicys(Pageable pageable);

    Page<CancellationPolicy> getAllCancellationPolicys(Specification<CancellationPolicy> spec, Pageable pageable);

	ResponseEntity<CancellationPolicyPageDTO> getCancellationPolicys(CancellationPolicySearchDTO cancellationPolicySearchDTO);
	
	List<CancellationPolicyDTO> convertCancellationPolicysToCancellationPolicyDTOs(List<CancellationPolicy> cancellationPolicys, CancellationPolicyConvertCriteriaDTO convertCriteria);

	CancellationPolicyDTO getCancellationPolicyDTOById(Integer cancellationPolicyId);







}





