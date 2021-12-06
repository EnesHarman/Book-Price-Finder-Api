package com.book.price.founder.Entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMainPageDto {
	private String ISBN;
	private String author;
	private String image;
	private String publisher;
	private String name;
}
