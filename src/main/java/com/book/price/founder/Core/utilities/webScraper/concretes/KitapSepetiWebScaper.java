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

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KitapSepetiWebScaper {
	
	private static String kitapSepetiBaseUrl = "https://www.kitapsepeti.com";
	private static String kitapSepetiSearchUrl = "/arama/?filter=";
	
	public static void  getBooksForMainPage(String bookName,Map<String, BookMainPageDto> books) throws IOException {
		
		String searchUrl = kitapSepetiBaseUrl+kitapSepetiSearchUrl+bookName;
		
		try {
			Document document = Jsoup.connect(searchUrl).get();
			Elements productElements = document.select("div#booklist");
			System.out.println(productElements);
			List<Element> products = productElements.stream().limit(1).collect(Collectors.toList());
			
			for(Element product : products) {
				String prdUrl = product.select("a").get(0).attr("href");
				
				Document doc = Jsoup.connect(kitapSepetiBaseUrl+ prdUrl).get();
				System.out.println(doc);
//				BookMainPageDto bookMainPageDto = new BookMainPageDto();
//				
//				String name= doc.select("h1.mt0").text();
//				bookMainPageDto.setName(name);
//				
//				String isbn = doc.select(".product-info-list").select("li").last().select("a").text();
//				bookMainPageDto.setISBN(isbn);
//					
//				String imgUrl = doc.select("img#main-product-img").first().attr("data-src");
//				bookMainPageDto.setImage(imgUrl);
//					
//				String author = doc.select("a.authorr").first().text();
//				bookMainPageDto.setAuthor(author);
//					
//				String publisher = doc.select("div.publisher").first().child(1).text();
//				bookMainPageDto.setPublisher(publisher);
//				books.put(isbn, bookMainPageDto);
			}
			log.info("Data has been got from Kitap Sepeti.");
			return;
			
		} catch (Exception e) {
			return;
		}
		
	}
	
}
