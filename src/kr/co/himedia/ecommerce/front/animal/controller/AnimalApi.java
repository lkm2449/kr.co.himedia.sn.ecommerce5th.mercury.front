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
 * Program		: kr.co.himedia.sn.ecommerce5th.mercury
 * Description	:
 * Environment	: JRE 1.8 or more
 * File			: AnimalApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240305104911][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.animal.controller;

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
import kr.co.himedia.ecommerce.front.animal.dto.AnimalDto;
import kr.co.himedia.ecommerce.front.animal.service.AnimalSrvc;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-05
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.animal.controller.AnimalApi")
public class AnimalApi extends Common{
	
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(AnimalApi.class);
	
	@Inject
	AnimalSrvc animalSrvc;
	
	@RequestMapping(value = "/front/animal/counting.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody int counting() {
		
		int count = 0;
		
		try {
			count = animalSrvc.counting();
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".counting()] " + e.getMessage(), e);
		}
		finally {}
		
		return count;
	}
	
	@RequestMapping(value = "/front/animal/listing.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<AnimalDto> listing(@RequestBody final AnimalDto animalDto) {
		
		List<AnimalDto> listAnimalDto = null;
		
		try {

			listAnimalDto = animalSrvc.listing(animalDto);
			
			for(int i=0; i<listAnimalDto.size(); i++){
				listAnimalDto.get(i).setImg(listAnimalDto.get(i).getImg().replace("http", "https"));
				listAnimalDto.get(i).setImg_thumb(listAnimalDto.get(i).getImg_thumb().replace("http", "https"));
			}
			
			debuggingJSON(listAnimalDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".listing()] " + e.getMessage(), e);
		}
		finally {}
		
		return listAnimalDto;
	}
	
	@RequestMapping(value = "/front/animal/mainListing.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<AnimalDto> mainListing() {
		
		List<AnimalDto> listAnimalDto = null;
		
		try {
			AnimalDto animalDto = new AnimalDto();
			animalDto.setCurrentPage(1);
			
			listAnimalDto = animalSrvc.listing(animalDto);
			
			for(int i=0; i<listAnimalDto.size(); i++){
				listAnimalDto.get(i).setImg(listAnimalDto.get(i).getImg().replace("http", "https"));
				listAnimalDto.get(i).setImg_thumb(listAnimalDto.get(i).getImg_thumb().replace("http", "https"));
			}
			
			debuggingJSON(listAnimalDto);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".mainListing()] " + e.getMessage(), e);
		}
		finally {}
		
		return listAnimalDto;
	}
}
