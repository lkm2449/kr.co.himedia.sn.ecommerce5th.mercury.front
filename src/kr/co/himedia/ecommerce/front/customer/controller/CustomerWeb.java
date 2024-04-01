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
 * Environment	: JRE 1.7 or more
 * File			: CustomerWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240222170759][pluto@himedia.co.kr][CREATE: Initial Release]
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

import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.front.board.dto.ReViewDto;
import kr.co.himedia.ecommerce.front.buy.dto.BuyDtlDto;
import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.customer.dto.WishDto;
import kr.co.himedia.ecommerce.front.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-02-22
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.front.customer.controller.CustomerWeb")
public class CustomerWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(CustomerWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	private CustomerSrvc customerSrvc;
	
	// 찜 상품 해제
	@RequestMapping(value = "/front/customer/deleteProc.web")
	public ModelAndView deleteProc(HttpServletRequest request, HttpServletResponse response, WishDto wishDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			wishDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			customerSrvc.deleteWish(wishDto);
			request.setAttribute("script"	, "alert('찜을 해제 했습니다');");
			request.setAttribute("redirect"	, "/front/customer/wislist.web");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".tosForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 작성한 리뷰 페이지 이동
	@RequestMapping(value = "/front/customer/wislist.web")
	public ModelAndView wislist(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			pagingDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			PagingListDto pagingListDto = customerSrvc.wisList(pagingDto);
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/front/customer/wislist");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".revlist()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/front/customer/wishProc.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody int wishProc(HttpServletRequest request, HttpServletResponse response, @RequestBody final WishDto wishDto) {
		
		int result = 0;
		
		try {
			
			if(getSession(request, "SEQ_CST") == null || getSession(request, "SEQ_CST") == "") {
				result = 1;
			} else {
				wishDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				if (customerSrvc.countWish(wishDto) == 0) {
					customerSrvc.insertWish(wishDto);
					result = 2;
				}
				else {
					customerSrvc.deleteWish(wishDto);
					result = 3;
				}
			}
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".wishProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return result;
	}
	
	// 찜 상품
	@RequestMapping(value = "/front/customer/insertWish.web")
	public ModelAndView insertWish(HttpServletRequest request, HttpServletResponse response, WishDto wishDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			int seq_sle = wishDto.getSeq_sle();
			
			if(getSession(request, "SEQ_CST") == null || getSession(request, "SEQ_CST") == "") {
				request.setAttribute("script"	, "alert('로그인이 필요한 서비스입니다!');");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			} else {
				wishDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
				
				if (customerSrvc.countWish(wishDto) == 0) {
					customerSrvc.insertWish(wishDto);
					request.setAttribute("script"	, "alert('찜 했습니다');");
					request.setAttribute("redirect"	, "/front/buy/writeForm.web?seq_sle=" + seq_sle);
				}
				else {
					customerSrvc.deleteWish(wishDto);
					request.setAttribute("script"	, "alert('찜을 해제 했습니다');");
					request.setAttribute("redirect"	, "/front/buy/writeForm.web?seq_sle=" + seq_sle);
				}
			}

			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".insertWish()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//마이페이지 상품 리뷰작성
	@RequestMapping(value = "/front/customer/revProc.web")
	public ModelAndView revProc(HttpServletRequest request, HttpServletResponse response, ReViewDto reViewDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			reViewDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			if (customerSrvc.insertRev(reViewDto)) {
				request.setAttribute("script"	, "alert('리뷰작성에 성공 했습니다');");
				request.setAttribute("redirect"	, "/front/customer/main.web");
			}
			else {
				request.setAttribute("script"	, "alert('리뷰작성에 실패 했습니다');");
				request.setAttribute("redirect"	, "/front/customer/buylist.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//마이페이지 구매한 상품 리뷰작성 페이지
	@RequestMapping(value = "/front/customer/revForm.web")
	public ModelAndView revForm(HttpServletRequest request, HttpServletResponse response, BuyDtlDto buyDtlDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BuyDtlDto _buyDtlDto = customerSrvc.revForm(buyDtlDto);
			
			_buyDtlDto.setSeq_buy_dtl(buyDtlDto.getSeq_buy_dtl());
			mav.addObject("buyDtlDto", _buyDtlDto);
			mav.setViewName("/front/customer/revForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 작성한 리뷰 페이지 이동
	@RequestMapping(value = "/front/customer/revlist.web")
	public ModelAndView revlist(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			pagingDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			PagingListDto pagingListDto = customerSrvc.selectRev(pagingDto);
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/front/customer/revlist");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".revlist()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 회원 탈퇴 페이지 이동
	@RequestMapping(value = "/front/customer/withdrawalForm.web")
	public ModelAndView withdrawalForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/customer/withdrawalForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".withdrawalForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 회원 탈퇴
	@RequestMapping(value = "/front/customer/withdrawalProc.web")
	public ModelAndView withdrawalProc(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			customerDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			
			if (customerSrvc.withdrawal(customerDto)) {
				request.setAttribute("redirect"	, "/front/login/logout.web");
			}
			else {
				request.setAttribute("redirect"	, "/front/customer/main.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".withdrawalProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 구매 이력 페이지 이동
	@RequestMapping(value = "/front/customer/buylist.web")
	public ModelAndView buylist(HttpServletRequest request	, HttpServletResponse response, PagingDto pagingDto){
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			pagingDto.setRegister(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			PagingListDto pagingListDto = customerSrvc.mstList(pagingDto);
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("mstList"	, pagingListDto.getList());
			
			List<BuyDtlDto> dtlList = customerSrvc.dtlList(Integer.parseInt(getSession(request, "SEQ_CST")));
			mav.addObject("dtlList", dtlList);
			
			mav.setViewName("/front/customer/buylist");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".buylist()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 개인 정보 수정
	@RequestMapping(value = "/front/customer/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response	, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (customerDto.getFlg_sms() == null || customerDto.getFlg_sms() == "") {
				customerDto.setFlg_sms("N");
			}
			
			if (customerDto.getFlg_email() == null || customerDto.getFlg_email() == "") {
				customerDto.setFlg_email("N");
			}
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			//데이터 암호화
			customerDto.setPhone(aes.encode(customerDto.getPhone()));
			customerDto.setPostcode(aes.encode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.encode(customerDto.getAddr1()));
			customerDto.setAddr3(aes.encode(customerDto.getAddr3()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			customerDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			customerDto.setUpdater(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front.js\"></script>");
			if (customerSrvc.update(customerDto)) {
				request.setAttribute("redirect"	, "/front/customer/main.web");
			}
			else {
				request.setAttribute("redirect"	, "/front/customer/main.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 개인 정보 수정 페이지 이동
	@RequestMapping(value = "/front/customer/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(getSession(request, "ID"));
			
			CustomerDto _customerDto = customerSrvc.select(customerDto);
						
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			//암호화 복호화
			_customerDto.setCst_nm(aes.decode(_customerDto.getCst_nm()));
			_customerDto.setPhone(aes.decode(_customerDto.getPhone()));
			_customerDto.setPostcode(aes.decode(_customerDto.getPostcode()));
			_customerDto.setAddr1(aes.decode(_customerDto.getAddr1()));
			_customerDto.setAddr3(aes.decode(_customerDto.getAddr3()));
			_customerDto.setCst_email(aes.decode(_customerDto.getCst_email()));
			
			mav.addObject("customerDto", _customerDto);
			mav.setViewName("/front/customer/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 비밀번호 변경 페이지 이동
	@RequestMapping(value = "/front/customer/modifyPwdForm.web")
	public ModelAndView modifyPwdForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(getSession(request, "ID"));
			//customerDto.setSeq_cst(getSession(request, "SEQ_CST"));
			
			CustomerDto _customerDto = customerSrvc.selectPwd(customerDto);
			
			mav.addObject("customerDto", _customerDto);
			mav.setViewName("/front/customer/modifyPwdForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyPwdForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 비밀번호 재확인 및 새 비밀번호 입력
	@RequestMapping(value = "/front/customer/modifyPwdProc.web")
	public ModelAndView modifyPwdProc(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String obPasswd_ = request.getParameter("obPasswd_");
			String obPasswd = HSwithSHA.encode(obPasswd_);
			
			customerDto.setPasswd(obPasswd);
			customerDto.setSeq_cst(Integer.parseInt(getSession(request, "SEQ_CST")));
			
			int count = customerSrvc.countPwd(customerDto);
			
			if (1 == count) {
				
				String Passwd_ = request.getParameter("passwd");
				String passwd = HSwithSHA.encode(Passwd_);
				
				customerDto.setPasswd(passwd);
				
				if (customerSrvc.changePasswd(customerDto)) {
					request.setAttribute("redirect"	, "/front/customer/main.web");
				}
				else {
					request.setAttribute("redirect"	, "/front/customer/modifyPwdForm.web");
				}
			}
			
			else {
				request.setAttribute("script"	, "alert('기존 비밀번호가 일치하지 않습니다!');");
				request.setAttribute("redirect"	, "/front/customer/modifyPwdForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyPwdProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 마이페이지 메인 페이지 이동
	@RequestMapping(value = "/front/customer/main.web")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.setViewName("/front/customer/main");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".main()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
}
