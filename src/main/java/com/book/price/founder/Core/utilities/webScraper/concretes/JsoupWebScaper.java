package com.book.price.founder.Core.utilities.webScraper.concretes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.book.price.founder.Core.utilities.result.abstracts.DataResult;
import com.book.price.founder.Core.utilities.result.concretes.SuccessDataResult;
import com.book.price.founder.Core.utilities.webScraper.abstracts.WebScaper;
import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceDto;
import com.book.price.founder.Entity.dtos.BookPriceListDto;

@Service
public class JsoupWebScaper implements WebScaper{
	
	public DataResult<Map<String, BookMainPageDto>> getBookForMainPage(String bookName) throws IOException{
		Map<String, BookMainPageDto> books  = new HashMap<String, BookMainPageDto>();
		IdefixWebScaper.getBooksForMainPage(bookName, books);
		DrWebScaper.getBooksForMainPage(bookName,books);
		//KirmiziKediWebScaper.getBooksForMainPage(bookName, books); Yazar isimleri farklı olduğu için sorun çıkarıyor
		//KitapSepetiWebScaper.getBooksForMainPage(bookName, books);
		return new SuccessDataResult<Map<String,BookMainPageDto>>(books);
	}

	@Override
	public DataResult<BookPriceListDto> getSingleBookPrices(String bookName, String authorName, String publisherName)
			throws IOException {
		BookPriceListDto bookPriceListDto = new BookPriceListDto();
		Map<String,BookPriceDto> map = new HashMap<String, BookPriceDto>();
		
		BookPriceDto idefixPrice = IdefixWebScaper.getSingleBookPrices(bookName, authorName, publisherName);
		map.put("idefix", idefixPrice);
		
		BookPriceDto drPrice = DrWebScaper.getSingleBookPrices(bookName, authorName, publisherName);
		map.put("dr", drPrice);
		
//		BookPriceDto kirmiziKediPrice = KirmiziKediWebScaper.getSingleBookPrices(bookName, authorName, publisherName);
//		map.put("kirmizikedi", kirmiziKediPrice);
		
		bookPriceListDto.setPrices(map);
		return new SuccessDataResult<BookPriceListDto>(bookPriceListDto);
	}
	
	
}
