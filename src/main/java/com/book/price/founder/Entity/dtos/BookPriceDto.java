package com.book.price.founder.Entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookPriceDto {
	private float price;
	private String siteImgUrl;
	private String productUrl;
}
