package com.braggbnb101.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb101.domain.PropertyImage;
import com.braggbnb101.dto.PropertyImageDTO;
import com.braggbnb101.dto.PropertyImageSearchDTO;
import com.braggbnb101.dto.PropertyImagePageDTO;
import com.braggbnb101.dto.PropertyImageConvertCriteriaDTO;
import com.braggbnb101.service.GenericService;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PropertyImageService extends GenericService<PropertyImage, Integer> {

	List<PropertyImage> findAll();

	ResultDTO addPropertyImage(PropertyImageDTO propertyImageDTO, RequestDTO requestDTO);

	ResultDTO updatePropertyImage(PropertyImageDTO propertyImageDTO, RequestDTO requestDTO);

    Page<PropertyImage> getAllPropertyImages(Pageable pageable);

    Page<PropertyImage> getAllPropertyImages(Specification<PropertyImage> spec, Pageable pageable);

	ResponseEntity<PropertyImagePageDTO> getPropertyImages(PropertyImageSearchDTO propertyImageSearchDTO);
	
	List<PropertyImageDTO> convertPropertyImagesToPropertyImageDTOs(List<PropertyImage> propertyImages, PropertyImageConvertCriteriaDTO convertCriteria);

	PropertyImageDTO getPropertyImageDTOById(Integer propertyImageId);







}





