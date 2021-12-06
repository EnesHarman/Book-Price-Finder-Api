package com.book.price.founder.Business.concretes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.price.founder.Business.abstracts.BookService;
import com.book.price.founder.Core.utilities.result.abstracts.DataResult;
import com.book.price.founder.Core.utilities.webScraper.abstracts.WebScaper;
import com.book.price.founder.Core.utilities.webScraper.concretes.DrWebScaper;
import com.book.price.founder.Entity.concretes.Book;
import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceListDto;
import com.book.price.founder.Repositories.BookRepository;

@Service
public class BookManager implements BookService{

	private final BookRepository bookRepository;
	private final WebScaper webScaper;

	
	@Autowired
	public BookManager(BookRepository bookRepository ,WebScaper webScaper) {
		this.bookRepository = bookRepository;
		this.webScaper = webScaper;
	}
	

	@Override
	public DataResult<Map<String, BookMainPageDto>> getBookListByNameWithScapers(String bookname) throws IOException {
		DataResult<Map<String, BookMainPageDto>> books = this.webScaper.getBookForMainPage(bookname);
		return books;
	}


	@Override
	public DataResult<BookPriceListDto> getSingleBookPrices(BookMainPageDto bookDetails) throws IOException {
		DataResult<BookPriceListDto> result =this.webScaper.getSingleBookPrices(bookDetails.getName(), 
													bookDetails.getAuthor(),bookDetails.getPublisher());
		
		result.getData().setImageUrl(bookDetails.getImage());
		result.getData().setAuthor(bookDetails.getAuthor());
		result.getData().setName(bookDetails.getName());
		result.getData().setPublisher(bookDetails.getPublisher());
		
		return result;
	}
	
	

	

}
