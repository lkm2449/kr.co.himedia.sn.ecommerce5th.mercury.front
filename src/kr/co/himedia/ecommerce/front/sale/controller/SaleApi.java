/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2023 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2023 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SaleApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231220113552][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.sale.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.front.sale.service.SaleSrvc;
/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.sale.controller.SaleApi")
public class SaleApi extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SaleApi.class);
	
	@Inject
	SaleSrvc saleSrvc;
	
	/**
	 * @param SaleDto [판매 빈(Bean)]
	 * @return List<SaleDto>
	 * 
	 * @since 2023-12-15
	 * <p>DESCRIPTION: 목록(전체)</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE:<br>
	 * - headers	: 요청 헤더를 확인<br>
	 * - consumes	: application/json 형태로 요청 확인<br>
	 * - produces	: application/json 형태로 응답 확인<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.GET)<br>
	 * - @RequestMapping(value = requestMappingURL, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")<br>
	 * </p>
	 */
	
	@RequestMapping(value = "/front/sale/searchCounting.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody int searchCounting(@RequestBody final SaleDto saleDto) {
		
		int count = 0;
		
		try {
			count = saleSrvc.searchCounting(saleDto);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".searchCounting()] " + e.getMessage(), e);
		}
		finally {}
		
		return count;
	}
	
	@RequestMapping(value = "/front/sale/counting.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody int counting(@RequestBody final SaleDto saleDto) {
		
		int count = 0;
		
		try {
			count = saleSrvc.counting(saleDto);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".counting()] " + e.getMessage(), e);
		}
		finally {}
		
		return count;
	}
	
	@RequestMapping(value = "/front/sale/salesList.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> salesList(@RequestBody final SaleDto saleDto) {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {
			
			if(saleDto.getCurrentPage() == 0) {
				saleDto.setCurrentPage(1);
			}
			
			listSaleDtoDto = saleSrvc.salesList(saleDto);
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".salesList()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
	
	@RequestMapping(value = "/front/sale/search.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> search(@RequestBody final SaleDto saleDto) {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {

			if(saleDto.getCurrentPage() == 0) {
				saleDto.setCurrentPage(1);
			}
			
			listSaleDtoDto = saleSrvc.search(saleDto);
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".search()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
	
	@RequestMapping(value = "/front/sale/listing.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> listing(@RequestBody final SaleDto saleDto) {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {

			listSaleDtoDto = saleSrvc.listing(saleDto);
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".listing()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
	
	@RequestMapping(value = "/front/sale/mainToyListing.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> mainToyListing() {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {
			listSaleDtoDto = saleSrvc.toyListing();
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".mainToyListing()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
	
	@RequestMapping(value = "/front/sale/mainWalkListing.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> mainWalkListing() {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {
			listSaleDtoDto = saleSrvc.walkListing();
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".mainWalkListing()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
	
	@RequestMapping(value = "/front/sale/mainFeedListing.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> mainFeedListing() {
		
		List<SaleDto> listSaleDtoDto = null;
		
		try {
			listSaleDtoDto = saleSrvc.feedListing();
			
			debuggingJSON(listSaleDtoDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".mainFeedListing()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDtoDto;
	}
}
