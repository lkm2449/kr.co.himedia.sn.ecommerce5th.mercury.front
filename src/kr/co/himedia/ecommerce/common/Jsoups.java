/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Jsoups.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240112115138][pluto@himedia.co.kr][CREATE: Initial Release]
 */

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-01-12
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
package kr.co.himedia.ecommerce.common;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jsoups {

	public static void main(String[] args) {
		
		// String url	= "http://www.cgv.co.kr/movies/";
		// String url	= "http://www.animalls.co.kr/shop/shopbrand.html?type=X&xcode=002";
		// String url		= "https://www.dogpang.com/shop/goods/goods_list.php?category=002001";
		// Document doc	= null;
		
		for (int page = 1; page <= 14; page++) {
            String url = "https://www.dogpang.com/shop/goods/goods_list.php?category=002001&page=" + page;
            Document doc = null;
            
         	System.out.println("============================================================");
            System.out.println("page : " + page);
            System.out.println("============================================================");

		
		try {
			doc = Jsoup.connect(url).get();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println("============================================================");
		
		// CGV
		/*
		Elements element = doc.select("div .sect-movie-chart");
		Iterator<Element> ie1 = element.select("strong .rank").iterator();
		Iterator<Element> ie2 = element.select("strong .title").iterator();
		while (ie1.hasNext()) {
			System.out.println(ie1.next().text() + "\t" + ie2.next().text());
		}
		*/
		
		// Animal
		/*
		Elements element = doc.select("li .item_list");
		Iterator<Element> ie1 = element.select("p .prdname").iterator();
		Iterator<Element> ie2 = element.select("span .price").iterator();
		while (ie1.hasNext()) {
			System.out.println(ie1.next().text() + "\t" + ie2.next().text());
		}
		*/
		
		// Dogpang
			
			// 상품 상세
			//Elements element = doc.select("#content_view_desc");
			//System.out.println(element.get(0).text());
		
		
		for (int i = 1; i <= 24; i++) {
           
            // 상품명
            Element nameElement = doc.select("#contents > div.list-container > div > div:nth-child(" + i + ") > div > div > a > span").first();
            String name = nameElement != null ? nameElement.text() : "N/A";

            // 가격
            Element priceElement = doc.select("#contents > div.list-container > div > div:nth-child(" + i + ") > div > div > a > div.view-price-new > strong").first();
            String price = priceElement != null ? priceElement.text() : "N/A";
            
            
            Element link = doc.select("#contents > div.list-container > div > div:nth-child("+ i +") > div > a").first();
            String articleUrl = link.attr("href");
            
            String detailUrl = "https://www.dogpang.com" + articleUrl;
            Document docm = null;
            try {
    			docm = Jsoup.connect(detailUrl).get();
    		}
    		catch (IOException e) {
    			e.printStackTrace();
    		} 
            
            // 상품 상세
         	//Elements element = doc.select("#content_view_desc");
         	//System.out.println(element.get(0).text());
         		
            
            
            //Element productElement = doc.select("#contents > div.list-container > div > div:nth-child("+ i +") > div > a").first();
            //String product = productElement != null ? productElement.text() : "N/A";

            // 결과 출력
        
         
         	//카테고리
            Elements element = doc.select("#gnb > li:nth-child(8)");
         	System.out.println(element.get(0).text());
         	//상품 이름
            System.out.println("상품명: " + name);
            //상품 가격
            System.out.println("가격: " + price);
            //상품 고유 url
            //System.out.println("url: " + articleUrl);
            //상품 상세
            element = docm.select("#content_view_desc");
         	System.out.println("상품 상세:"+element.get(0).text());
           
            
        }
    }
}
}    