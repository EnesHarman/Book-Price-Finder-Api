package com.book.price.founder.Core.utilities.webScraper.abstracts;

import java.io.IOException;
import java.util.Map;

import com.book.price.founder.Core.utilities.result.abstracts.DataResult;
import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceListDto;

public interface WebScaper {
	public DataResult<Map<String, BookMainPageDto>> getBookForMainPage(String bookName)throws IOException;
	public DataResult<BookPriceListDto> getSingleBookPrices(String bookName, String authorName, String publisherName)throws IOException;
}
