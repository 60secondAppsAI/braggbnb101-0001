package com.braggbnb101.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class DiscountDTO {

	private Integer discountId;

	private Double discountPercentage;

	private Date validUntil;






}
