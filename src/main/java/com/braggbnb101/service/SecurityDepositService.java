package com.braggbnb101.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb101.domain.SecurityDeposit;
import com.braggbnb101.dto.SecurityDepositDTO;
import com.braggbnb101.dto.SecurityDepositSearchDTO;
import com.braggbnb101.dto.SecurityDepositPageDTO;
import com.braggbnb101.dto.SecurityDepositConvertCriteriaDTO;
import com.braggbnb101.service.GenericService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SecurityDepositService extends GenericService<SecurityDeposit, Integer> {

	List<SecurityDeposit> findAll();

	ResultDTO addSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO);

	ResultDTO updateSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO);

    Page<SecurityDeposit> getAllSecurityDeposits(Pageable pageable);

    Page<SecurityDeposit> getAllSecurityDeposits(Specification<SecurityDeposit> spec, Pageable pageable);

	ResponseEntity<SecurityDepositPageDTO> getSecurityDeposits(SecurityDepositSearchDTO securityDepositSearchDTO);
	
	List<SecurityDepositDTO> convertSecurityDepositsToSecurityDepositDTOs(List<SecurityDeposit> securityDeposits, SecurityDepositConvertCriteriaDTO convertCriteria);

	SecurityDepositDTO getSecurityDepositDTOById(Integer securityDepositId);







}





