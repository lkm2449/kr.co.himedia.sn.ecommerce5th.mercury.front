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
 * File			: CustomerApi.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231221190714][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.customer.controller;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.front.board.dto.ReViewDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyMstDto;
import kr.co.himedia.ecommerce.front.buy.service.BuySrvc;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.dto.WishDto;
import kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.front.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-21
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.customer.controller.CustomerApi")
public class CustomerApi extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(CustomerApi.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	CustomerSrvc customerSrvc;
	
	@Inject
	BuySrvc buySrvc;
	
	/**
	 * @param customerDto [구매자 빈(Bean)]
	 * @return boolean
	 * 
	 * @since 2023-12-21
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
	
	@RequestMapping(value = "/front/customer/writeReview.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean writeReview(@RequestBody final ReViewDto reViewDto) {
		
		boolean isSuccess = false;
		
		try {
			
			if (customerSrvc.insertReViewM(reViewDto)) isSuccess = true;
			
			debuggingJSON(isSuccess);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeReview()] " + e.getMessage(), e);
		}
		finally {}
		
		return isSuccess;
	}
	
	@RequestMapping(value = "/front/customer/reviewList.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> reviewList(@RequestBody final CustomerDto customerDto) {
		
		List<SaleDto> listSaleDto = null;
		
		try {
			
			listSaleDto = customerSrvc.reviewList(customerDto);

			debuggingJSON(listSaleDto);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".buyListD()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDto;
	}
	
	@RequestMapping(value = "/front/customer/buyListD.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<BuyDtlDto> buyListD(@RequestBody final BuyMstDto buyMstDto) {
		
		List<BuyDtlDto> listBuyDto = null;
		
		try {
			
			listBuyDto = buySrvc.listD(buyMstDto);

			debuggingJSON(listBuyDto);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".buyListD()] " + e.getMessage(), e);
		}
		finally {}
		
		return listBuyDto;
	}
	
	@RequestMapping(value = "/front/customer/buyListM.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<BuyMstDto> buyListM(@RequestBody final CustomerDto customerDto) {
		
		List<BuyMstDto> listBuyDto = null;
		
		try {
			
			listBuyDto = buySrvc.listM(customerDto.getSeq_cst());

			debuggingJSON(listBuyDto);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".buyListM()] " + e.getMessage(), e);
		}
		finally {}
		
		return listBuyDto;
	}
	
	@RequestMapping(value = "/front/customer/wishList.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody List<SaleDto> wishList(@RequestBody final WishDto wishDto) {
		
		List<SaleDto> listSaleDto = null;
		
		try {
			listSaleDto = customerSrvc.wishList(wishDto);
			
			debuggingJSON(listSaleDto);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".wishList()] " + e.getMessage(), e);
		}
		finally {}
		
		return listSaleDto;
	}
	
	@RequestMapping(value = "/front/customer/insertWish.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody int insertWish(@RequestBody final WishDto wishDto) {
		
		int isSuccess = 0; // 0 - 실패, 1 - 찜 등록, 2 - 찜 해제
		
		try {
			if(customerSrvc.countWish(wishDto) == 0) {
				if(customerSrvc.insertWish(wishDto)) {
					isSuccess = 1;
				}
			} else {
				if(customerSrvc.deleteWish(wishDto)) {
					isSuccess = 2;
				}
			}

		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".insertWish()] " + e.getMessage(), e);
		}
		finally {}
		
		return isSuccess;
	}
	
	@RequestMapping(value = "/front/customer/daumAddress.api")
	public ModelAndView daumAddress(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/customer/daumAddress");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".daumAddress()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/customer/joinProc.api", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean joinProc(@RequestBody final CustomerDto customerDto) {
		
		boolean isSuccess = false;
		
		try {
			
			if (customerDto.getFlg_sms() == null || customerDto.getFlg_sms() == "") {
				customerDto.setFlg_sms("N");
			}
			
			if (customerDto.getFlg_email() == null || customerDto.getFlg_email() == "") {
				customerDto.setFlg_email("N");
			}
			
			// 해쉬 암호화
			customerDto.setPasswd(HSwithSHA.encode(customerDto.getPasswd()));
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setPhone(aes.encode(customerDto.getPhone()));
			customerDto.setPostcode(aes.encode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.encode(customerDto.getAddr1()));
			customerDto.setAddr2(aes.encode(customerDto.getAddr2()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
//			if (customerSrvc.insert(customerDto)) isSuccess = true;
			
			debuggingJSON(isSuccess);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".joinProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return isSuccess;
	}

}
