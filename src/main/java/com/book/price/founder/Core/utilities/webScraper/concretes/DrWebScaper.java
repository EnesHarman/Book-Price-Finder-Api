package com.book.price.founder.Core.utilities.webScraper.concretes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.book.price.founder.Entity.dtos.BookMainPageDto;
import com.book.price.founder.Entity.dtos.BookPriceDto;


@Service
@Slf4j
public class DrWebScaper {
	
	private final static String DrBaseUrl = "https://www.dr.com.tr";
	private final static String DrSeachUrl="/Search?Q=";
	private final static String DrSiteLogoUrl="https://upload.wikimedia.org/wikipedia/tr/5/52/D%26R_logo.jpg";
	
	public static void getBooksForMainPage(String bookName,Map<String, BookMainPageDto> books) throws IOException{
		String searchUrl=DrBaseUrl+DrSeachUrl+ bookName+"&Media=ince Kapak&Lang=Türkçe";
		Document document = Jsoup.connect(searchUrl).get();
		Elements results = document.select(".prd");
		List<Element> products =  results.stream()
				.limit(10).collect(Collectors.toList());
		
		for (Element p : products) {
			String prdUrl = p.select("a").get(0).attr("href");

			try {
				Document doc =Jsoup.connect(DrBaseUrl +prdUrl).get();
				
				BookMainPageDto bookMainPageDto = new BookMainPageDto();
				bookMainPageDto.setName(doc.select(".fs-7.mb-0").get(0).text());
					
				Element isbnElement = doc.select("div .product-property").first();
				String isbn = isbnElement.child(0).children().last().child(1).text();
				bookMainPageDto.setISBN(isbn);
					
				String imgUrl = doc.select(".prd-detail").select("img").attr("src");
				bookMainPageDto.setImage(imgUrl);
					
				String author = doc.select(".author").first().child(1).text();
				bookMainPageDto.setAuthor(author);
					
				String publisher = doc.select("#publisherName").first().child(1).text();
				bookMainPageDto.setPublisher(publisher);
					
				books.put(isbn, bookMainPageDto);
			} catch (Exception e) {
				return;
			}
		}
		log.info("Data has been got from D&R");
		return;
	}
	
	public static BookPriceDto getSingleBookPrices(String bookName, String authorName, String publisherName)
			throws IOException {
		BookPriceDto bookPriceDto = new BookPriceDto();
		String searchUrl=DrBaseUrl+DrSeachUrl + bookName +"&Manufacturer="+publisherName +"&Person="+authorName;
		try {
			Document document= Jsoup.connect(searchUrl).get();
			
			Elements results = document.select(".prd");
			Element filteredBook =  results.stream().filter(r->r.select(".prd-media-type").get(0)
					.child(0).attr("title").contains("nce Kapak")).collect(Collectors.toList()).get(0);
	
			String bookPriceText = filteredBook.select("div.prd-price").text().split(" ")[0].replace(",", ".");
			float bookPrice = Float.parseFloat(bookPriceText);
			bookPriceDto.setPrice(bookPrice);
			
			String bookUrl = filteredBook.select("a").get(0).attr("href");
			bookPriceDto.setProductUrl(DrBaseUrl+bookUrl);
			
			bookPriceDto.setSiteImgUrl(DrSiteLogoUrl);
			return bookPriceDto;
		} catch (Exception e) {
			return null;
		}
	
	}
}
