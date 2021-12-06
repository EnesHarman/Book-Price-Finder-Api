package com.book.price.founder.Business.abstracts;

import java.io.IOException;
import java.util.Map;

import com.book.price.founder.Core.utilities.result.abstracts.DataResult;
import com.book.price.founder.Entity.concretes.Book;
import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceListDto;

public interface BookService {

	DataResult<Map<String, BookMainPageDto>> getBookListByNameWithScapers(String bookname) throws IOException;

	DataResult<BookPriceListDto> getSingleBookPrices(BookMainPageDto bookDetails)throws IOException;

}
