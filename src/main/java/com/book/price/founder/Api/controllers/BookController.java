package com.book.price.founder.Api.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.price.founder.Business.abstracts.BookService;
import com.book.price.founder.Core.utilities.result.abstracts.DataResult;
import com.book.price.founder.Entity.concretes.Book;
import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceListDto;


@RestController
@RequestMapping("/api/book")
public class BookController {
	
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<?> getBookListByNameWithScapers(@RequestParam String bookname) throws IOException{
		DataResult<Map<String, BookMainPageDto>> result = this.bookService.getBookListByNameWithScapers(bookname);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getData());
		}
		else {
			return ResponseEntity.internalServerError().body(result.getMessage());
		}
	}
	
	@PostMapping("/list/prices")
	public ResponseEntity<?> getSingleBookPrices(@RequestBody BookMainPageDto bookDetails) throws IOException{
		DataResult<BookPriceListDto> result = this.bookService.getSingleBookPrices(bookDetails);
		if(result.getSuccess()) {
			return ResponseEntity.ok(result.getData());
		}
		else {
			return ResponseEntity.internalServerError().body(result.getMessage());
		}
	}
}
