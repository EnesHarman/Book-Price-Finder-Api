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
public class IdefixWebScaper {
	private static final String idefixBaseUrl = "https://www.idefix.com";
	private static final String idefixSearchUrl = "/search?q=";
	private static final String idefixSiteLogoUrl="https://play-lh.googleusercontent.com/DUWlrtyrjOrFdLx9WD6QwWwpz_IWdt9Ej2vHys10Y-kW6ME_LGCfwWKTtVCxEbXwqA";
	
	
	public static void getBooksForMainPage(String bookName,Map<String, BookMainPageDto> books) throws IOException{
		String searchUrl = idefixBaseUrl+idefixSearchUrl+bookName+"&Media=ince Kapak&Lang=Türkçe";

		try {
			Document document = Jsoup.connect(searchUrl).get();
			Elements productElements = document.select(".catalog-list-carousel.itemlittleProduct");
			List<Element> products = productElements.stream().limit(10).collect(Collectors.toList());
			for(Element product : products) {
				String prdUrl = product.select("a").get(0).attr("href");
				
				Document doc = Jsoup.connect(idefixBaseUrl+ prdUrl).get();
				
				BookMainPageDto bookMainPageDto = new BookMainPageDto();
				
				String name= doc.select("h1.mt0").text();
				bookMainPageDto.setName(name);
				
				String isbn = doc.select(".product-info-list").select("li").last().select("a").text();
				bookMainPageDto.setISBN(isbn);
					
				String imgUrl = doc.select("img#main-product-img").first().attr("data-src");
				bookMainPageDto.setImage(imgUrl);
					
				String author = doc.select("a.authorr").first().text();
				bookMainPageDto.setAuthor(author);
					
				String publisher = doc.select("div.publisher").first().child(1).text();
				bookMainPageDto.setPublisher(publisher);
				books.put(isbn, bookMainPageDto);
			}
			log.info("Data has been got from Idefix.");
			return;
		} catch (Exception e) {
			return;
		}
		
		
	}
	
	public static BookPriceDto getSingleBookPrices(String bookName, String authorName, String publisherName)
			throws IOException {
		BookPriceDto bookPriceDto = new BookPriceDto();
		String searchUrl=idefixBaseUrl+idefixSearchUrl + bookName +"&Manufacturer="+publisherName +"&Person="+authorName;
		try {
			Document document= Jsoup.connect(searchUrl).get();
			
			Elements bookElements = document.select(".catalog-list-carousel.itemlittleProduct");
			
			Element filteredBook = bookElements.stream().filter(p->!p.select("a").get(0).attr("href").contains("ekitap")).collect(Collectors.toList()).get(0);
			
			String bookPriceText = filteredBook.select("#prices").text().split(" ")[0].replace(",", ".");
			float bookPrice = Float.parseFloat(bookPriceText);
			bookPriceDto.setPrice(bookPrice);
			
			String bookUrl = filteredBook.select("a").get(0).attr("href");
			bookPriceDto.setProductUrl(idefixBaseUrl+bookUrl);
	
			bookPriceDto.setSiteImgUrl(idefixSiteLogoUrl);
			return bookPriceDto;
		} catch (Exception e) {
			return null;
		}
		
	}
	 
}
