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
 * File			: AnimalWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240314115154][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.animal.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.front.animal.dto.AnimalDto;
import kr.co.himedia.ecommerce.front.animal.service.AnimalSrvc;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-14
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.animal.controller.AnimalWeb")
public class AnimalWeb {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(AnimalWeb.class);
	
	@Inject
	AnimalSrvc animalSrvc;
	
	@RequestMapping(value = "/front/animal/animalView.web")
	public ModelAndView animalView (HttpServletRequest request, HttpServletResponse response, AnimalDto animalDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			AnimalDto _animalDto = animalSrvc.view(animalDto);
			
			mav.addObject("view", _animalDto);
			
			mav.setViewName("/front/animal/animalView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".animalView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/animal/animalList.web")
	public ModelAndView faqList (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto = animalSrvc.list(pagingDto);
			
			mav.addObject("sort", pagingDto.getSort());
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/front/animal/animalList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".animalList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
}
