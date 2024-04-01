/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM3
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
 * File			: LoginController.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240214093235][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.join.controller;

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

import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.join.service.JoinSrvc;
import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-02-14
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.join.controller.JoinWeb")
public class JoinWeb extends Common {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(JoinWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	JoinSrvc joinSrvc;
	
	
	// 필수 이용 약관
	@RequestMapping(value = "/front/join/readTerm.web")
	public ModelAndView readTerm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String url = request.getParameter("url");
			
			mav.addObject("url", url);			
			mav.setViewName("/front/join/readTerm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".readTerm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 아이디 중복 확인
	@RequestMapping(value = "/front/join/joinForm.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean exist(@RequestBody final CustomerDto customerDto) {
		
		boolean exist = true;
		List<CustomerDto> listCustomerDto = null;
		
		try {
			//logger.debug("id=" + sellerDto.getId());
			listCustomerDto	= joinSrvc.listing(customerDto.getId());
			
			if (listCustomerDto.size() == 0) exist = false;
			
			debuggingJSON(exist);
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".exist()] " + e.getMessage(), e);
		}
		finally {}
		
		return exist;
	}
	
	//회원가입 페이지이동
	@RequestMapping(value = "/front/join/joinForm.web")
	public ModelAndView joinForm(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.addObject("flg_sms", customerDto.getFlg_sms());
			mav.addObject("flg_email", customerDto.getFlg_email());

			mav.setViewName("/front/join/joinForm");
		
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".joinForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;

	}
	
	//회원가입 진행
	@RequestMapping(value = "/front/join/joinProc.web")
	public ModelAndView joinProc (HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
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
			customerDto.setAddr3(aes.encode(customerDto.getAddr3()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			if(joinSrvc.search(customerDto) == 1) {
				request.setAttribute("script", "alert('이미 가입한 회원입니다.')");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}else {
				
				request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
				if (joinSrvc.insert(customerDto)) {
					
					request.setAttribute("redirect"	, "/");
				}
				else {
					
					request.setAttribute("redirect"	, "/front/join/joinForm.web");
				}
			}
			
			
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".joinProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 가입 약관 페이지 이동
	@RequestMapping(value = "/front/join/tosForm.web")
	public ModelAndView tosForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {

			mav.setViewName("/front/join/tosForm");
		
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".tosForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	
}
