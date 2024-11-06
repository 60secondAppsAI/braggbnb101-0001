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
import com.braggbnb101.dao.PropertyImageDAO;
import com.braggbnb101.domain.PropertyImage;
import com.braggbnb101.dto.PropertyImageDTO;
import com.braggbnb101.dto.PropertyImageSearchDTO;
import com.braggbnb101.dto.PropertyImagePageDTO;
import com.braggbnb101.dto.PropertyImageConvertCriteriaDTO;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import com.braggbnb101.service.PropertyImageService;
import com.braggbnb101.util.ControllerUtils;





@Service
public class PropertyImageServiceImpl extends GenericServiceImpl<PropertyImage, Integer> implements PropertyImageService {

    private final static Logger logger = LoggerFactory.getLogger(PropertyImageServiceImpl.class);

	@Autowired
	PropertyImageDAO propertyImageDao;

	


	@Override
	public GenericDAO<PropertyImage, Integer> getDAO() {
		return (GenericDAO<PropertyImage, Integer>) propertyImageDao;
	}
	
	public List<PropertyImage> findAll () {
		List<PropertyImage> propertyImages = propertyImageDao.findAll();
		
		return propertyImages;	
		
	}

	public ResultDTO addPropertyImage(PropertyImageDTO propertyImageDTO, RequestDTO requestDTO) {

		PropertyImage propertyImage = new PropertyImage();

		propertyImage.setPropertyImageId(propertyImageDTO.getPropertyImageId());


		propertyImage.setImageUrl(propertyImageDTO.getImageUrl());


		propertyImage.setDescription(propertyImageDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		propertyImage = propertyImageDao.save(propertyImage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PropertyImage> getAllPropertyImages(Pageable pageable) {
		return propertyImageDao.findAll(pageable);
	}

	public Page<PropertyImage> getAllPropertyImages(Specification<PropertyImage> spec, Pageable pageable) {
		return propertyImageDao.findAll(spec, pageable);
	}

	public ResponseEntity<PropertyImagePageDTO> getPropertyImages(PropertyImageSearchDTO propertyImageSearchDTO) {
	
			Integer propertyImageId = propertyImageSearchDTO.getPropertyImageId(); 
 			String imageUrl = propertyImageSearchDTO.getImageUrl(); 
 			String description = propertyImageSearchDTO.getDescription(); 
 			String sortBy = propertyImageSearchDTO.getSortBy();
			String sortOrder = propertyImageSearchDTO.getSortOrder();
			String searchQuery = propertyImageSearchDTO.getSearchQuery();
			Integer page = propertyImageSearchDTO.getPage();
			Integer size = propertyImageSearchDTO.getSize();

	        Specification<PropertyImage> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, propertyImageId, "propertyImageId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, imageUrl, "imageUrl"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("imageUrl")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<PropertyImage> propertyImages = this.getAllPropertyImages(spec, pageable);
		
		//System.out.println(String.valueOf(propertyImages.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(propertyImages.getTotalPages()));
		
		List<PropertyImage> propertyImagesList = propertyImages.getContent();
		
		PropertyImageConvertCriteriaDTO convertCriteria = new PropertyImageConvertCriteriaDTO();
		List<PropertyImageDTO> propertyImageDTOs = this.convertPropertyImagesToPropertyImageDTOs(propertyImagesList,convertCriteria);
		
		PropertyImagePageDTO propertyImagePageDTO = new PropertyImagePageDTO();
		propertyImagePageDTO.setPropertyImages(propertyImageDTOs);
		propertyImagePageDTO.setTotalElements(propertyImages.getTotalElements());
		return ResponseEntity.ok(propertyImagePageDTO);
	}

	public List<PropertyImageDTO> convertPropertyImagesToPropertyImageDTOs(List<PropertyImage> propertyImages, PropertyImageConvertCriteriaDTO convertCriteria) {
		
		List<PropertyImageDTO> propertyImageDTOs = new ArrayList<PropertyImageDTO>();
		
		for (PropertyImage propertyImage : propertyImages) {
			propertyImageDTOs.add(convertPropertyImageToPropertyImageDTO(propertyImage,convertCriteria));
		}
		
		return propertyImageDTOs;

	}
	
	public PropertyImageDTO convertPropertyImageToPropertyImageDTO(PropertyImage propertyImage, PropertyImageConvertCriteriaDTO convertCriteria) {
		
		PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
		
		propertyImageDTO.setPropertyImageId(propertyImage.getPropertyImageId());

	
		propertyImageDTO.setImageUrl(propertyImage.getImageUrl());

	
		propertyImageDTO.setDescription(propertyImage.getDescription());

	

		
		return propertyImageDTO;
	}

	public ResultDTO updatePropertyImage(PropertyImageDTO propertyImageDTO, RequestDTO requestDTO) {
		
		PropertyImage propertyImage = propertyImageDao.getById(propertyImageDTO.getPropertyImageId());

		propertyImage.setPropertyImageId(ControllerUtils.setValue(propertyImage.getPropertyImageId(), propertyImageDTO.getPropertyImageId()));

		propertyImage.setImageUrl(ControllerUtils.setValue(propertyImage.getImageUrl(), propertyImageDTO.getImageUrl()));

		propertyImage.setDescription(ControllerUtils.setValue(propertyImage.getDescription(), propertyImageDTO.getDescription()));



        propertyImage = propertyImageDao.save(propertyImage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PropertyImageDTO getPropertyImageDTOById(Integer propertyImageId) {
	
		PropertyImage propertyImage = propertyImageDao.getById(propertyImageId);
			
		
		PropertyImageConvertCriteriaDTO convertCriteria = new PropertyImageConvertCriteriaDTO();
		return(this.convertPropertyImageToPropertyImageDTO(propertyImage,convertCriteria));
	}







}
