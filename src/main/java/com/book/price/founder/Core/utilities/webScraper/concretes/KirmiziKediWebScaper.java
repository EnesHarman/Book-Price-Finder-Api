package com.book.price.founder.Core.utilities.webScraper.concretes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KirmiziKediWebScaper {
	private static final String kirmiziKediBaseUrl = "https://www.kirmizikedi.com";
	private static final String kirmiziKediSearchUrl = "/arama?q=";
	private static final String kirmiziKediSiteLogoUrl="https://yt3.ggpht.com/gs4qo-6aEkDqm_AmmEKociLr7cLqRUu0rhu-l5ySLpdxjK0hqVJQpJjWINdpb64aZ0XsGjKlTps=s900-c-k-c0x00ffffff-no-rj";
	
	public static void getBooksForMainPage(String bookName,Map<String, BookMainPageDto> books) throws IOException{
		Document document = Jsoup.connect(kirmiziKediBaseUrl+kirmiziKediSearchUrl+bookName).get();
		Elements productElements = document.select(".pli-grid-wrapper");

		List<Element> products = productElements.stream().limit(10).collect(Collectors.toList());
		
		for(Element product : products) {
			String prdUrl = product.select("a").get(0).attr("href");
			Document doc = Jsoup.connect(kirmiziKediBaseUrl+ prdUrl).get();
			
			BookMainPageDto bookMainPageDto = new BookMainPageDto();
			
			String name= doc.select(".pd-info").select("h1").text();
			bookMainPageDto.setName(name);
			
			String isbn = doc.select(".detail-container").select("tr").last().select("td").last().text();
			bookMainPageDto.setISBN(isbn);
				
			String imgUrl = doc.select(".pd-image-wrapper").select("img").attr("src");
			bookMainPageDto.setImage(imgUrl);
				
			String author = doc.select(".author").select("a").text();
			bookMainPageDto.setAuthor(author);
				
			String publisher = doc.select(".brand").select("a").text();
			bookMainPageDto.setPublisher(publisher);
			
			books.put(isbn, bookMainPageDto);
		}
		log.info("Data has been got from Kirmizi Kedi.");
		return;
	}
	
	public static BookPriceDto getSingleBookPrices(String bookName, String authorName, String publisherName)
			throws IOException {
		BookPriceDto bookPriceDto = new BookPriceDto();
		String searchUrl=kirmiziKediBaseUrl+kirmiziKediSearchUrl + bookName +"&f_brand="+publisherName +"&f_author="+authorName;
		
		try {
			Document document= Jsoup.connect(searchUrl).get();
		
			Element filteredBook = document.select(".pli-grid-wrapper").first();
			
			String bookPriceText = filteredBook.select(".product-price").text().split(" ")[1].split(" ")[0].replace(",", ".");
			float bookPrice = Float.parseFloat(bookPriceText);
			bookPriceDto.setPrice(bookPrice);
			
			String bookUrl = filteredBook.select("a").get(0).attr("href");
			bookPriceDto.setProductUrl(kirmiziKediBaseUrl+bookUrl);
	
			bookPriceDto.setSiteImgUrl(kirmiziKediSiteLogoUrl);
			
			return bookPriceDto;
		} catch (Exception e) {
			return null;
		}
		
	}
}
