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
import com.braggbnb101.dao.GuestDAO;
import com.braggbnb101.domain.Guest;
import com.braggbnb101.dto.GuestDTO;
import com.braggbnb101.dto.GuestSearchDTO;
import com.braggbnb101.dto.GuestPageDTO;
import com.braggbnb101.dto.GuestConvertCriteriaDTO;
import com.braggbnb101.dto.common.RequestDTO;
import com.braggbnb101.dto.common.ResultDTO;
import com.braggbnb101.service.GuestService;
import com.braggbnb101.util.ControllerUtils;





@Service
public class GuestServiceImpl extends GenericServiceImpl<Guest, Integer> implements GuestService {

    private final static Logger logger = LoggerFactory.getLogger(GuestServiceImpl.class);

	@Autowired
	GuestDAO guestDao;

	


	@Override
	public GenericDAO<Guest, Integer> getDAO() {
		return (GenericDAO<Guest, Integer>) guestDao;
	}
	
	public List<Guest> findAll () {
		List<Guest> guests = guestDao.findAll();
		
		return guests;	
		
	}

	public ResultDTO addGuest(GuestDTO guestDTO, RequestDTO requestDTO) {

		Guest guest = new Guest();

		guest.setGuestId(guestDTO.getGuestId());


		guest.setPhoneNumber(guestDTO.getPhoneNumber());


		guest.setPaymentMethod(guestDTO.getPaymentMethod());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		guest = guestDao.save(guest);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Guest> getAllGuests(Pageable pageable) {
		return guestDao.findAll(pageable);
	}

	public Page<Guest> getAllGuests(Specification<Guest> spec, Pageable pageable) {
		return guestDao.findAll(spec, pageable);
	}

	public ResponseEntity<GuestPageDTO> getGuests(GuestSearchDTO guestSearchDTO) {
	
			Integer guestId = guestSearchDTO.getGuestId(); 
 			String phoneNumber = guestSearchDTO.getPhoneNumber(); 
 			String paymentMethod = guestSearchDTO.getPaymentMethod(); 
 			String sortBy = guestSearchDTO.getSortBy();
			String sortOrder = guestSearchDTO.getSortOrder();
			String searchQuery = guestSearchDTO.getSearchQuery();
			Integer page = guestSearchDTO.getPage();
			Integer size = guestSearchDTO.getSize();

	        Specification<Guest> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, guestId, "guestId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, phoneNumber, "phoneNumber"); 
			
			spec = ControllerUtils.andIfNecessary(spec, paymentMethod, "paymentMethod"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("phoneNumber")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("paymentMethod")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Guest> guests = this.getAllGuests(spec, pageable);
		
		//System.out.println(String.valueOf(guests.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(guests.getTotalPages()));
		
		List<Guest> guestsList = guests.getContent();
		
		GuestConvertCriteriaDTO convertCriteria = new GuestConvertCriteriaDTO();
		List<GuestDTO> guestDTOs = this.convertGuestsToGuestDTOs(guestsList,convertCriteria);
		
		GuestPageDTO guestPageDTO = new GuestPageDTO();
		guestPageDTO.setGuests(guestDTOs);
		guestPageDTO.setTotalElements(guests.getTotalElements());
		return ResponseEntity.ok(guestPageDTO);
	}

	public List<GuestDTO> convertGuestsToGuestDTOs(List<Guest> guests, GuestConvertCriteriaDTO convertCriteria) {
		
		List<GuestDTO> guestDTOs = new ArrayList<GuestDTO>();
		
		for (Guest guest : guests) {
			guestDTOs.add(convertGuestToGuestDTO(guest,convertCriteria));
		}
		
		return guestDTOs;

	}
	
	public GuestDTO convertGuestToGuestDTO(Guest guest, GuestConvertCriteriaDTO convertCriteria) {
		
		GuestDTO guestDTO = new GuestDTO();
		
		guestDTO.setGuestId(guest.getGuestId());

	
		guestDTO.setPhoneNumber(guest.getPhoneNumber());

	
		guestDTO.setPaymentMethod(guest.getPaymentMethod());

	

		
		return guestDTO;
	}

	public ResultDTO updateGuest(GuestDTO guestDTO, RequestDTO requestDTO) {
		
		Guest guest = guestDao.getById(guestDTO.getGuestId());

		guest.setGuestId(ControllerUtils.setValue(guest.getGuestId(), guestDTO.getGuestId()));

		guest.setPhoneNumber(ControllerUtils.setValue(guest.getPhoneNumber(), guestDTO.getPhoneNumber()));

		guest.setPaymentMethod(ControllerUtils.setValue(guest.getPaymentMethod(), guestDTO.getPaymentMethod()));



        guest = guestDao.save(guest);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public GuestDTO getGuestDTOById(Integer guestId) {
	
		Guest guest = guestDao.getById(guestId);
			
		
		GuestConvertCriteriaDTO convertCriteria = new GuestConvertCriteriaDTO();
		return(this.convertGuestToGuestDTO(guest,convertCriteria));
	}







}
