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
 * File			: LoginController.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240214093235][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.login.controller;

import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.front.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.front.login.service.LoginSrvc;
import kr.co.himedia.ecommerce.util.Datetime;
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
@Controller("kr.co.himedia.ecommerce.front.login.controller.LoginWeb")
public class LoginWeb {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoginWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	LoginSrvc loginSrvc;
	
	//비밀번호 변경
	@RequestMapping(value = "/front/login/changePasswd.web")
	public ModelAndView changePasswd(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			customerDto.setPasswd(HSwithSHA.encode(customerDto.getPasswd()));
			
			if (loginSrvc.changePasswd(customerDto)) {
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}
			else {
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}
			
			mav.setViewName("/front/login/loginForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".changePasswd()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//비밀번호 변경 페이지 이동
	@RequestMapping(value = "/front/login/resultPwdForm.web", method = RequestMethod.POST)
	public ModelAndView resultPwdForm(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {

			//대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);

			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			CustomerDto _customerDto = loginSrvc.searchPwd(customerDto);
			
			mav.addObject("customerDto", _customerDto);
			mav.setViewName("/front/login/resultPwdForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".resultPwdForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//비밀번호 찾기 페이지 이동
	@RequestMapping(value = "/front/login/searchPwdForm.web")
	public ModelAndView searchPwdForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/login/searchPwdForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".searchPwdForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//아이디 찾기 결과 페이지
	@RequestMapping(value = "/front/login/resultIdForm.web", method = RequestMethod.POST)
	public ModelAndView resultIdForm(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			
			CustomerDto _customerDto = loginSrvc.searchId(customerDto);
			
			if(_customerDto != null) {
				
				String getid = _customerDto.getId();
				int length = getid.length();
				
				String cutid =  getid.substring(0, 4);
				
				String id = cutid;
				
				for(int i = 4; i<length; i++) {
					id += "*";
				}
				mav.addObject("id", id);
			} else {
				String id = null;
				mav.addObject("id", id);
			}
			mav.setViewName("/front/login/resultIdForm");
			}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".resultIdForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 아이디 찾기 페이지 찾기
	@RequestMapping(value = "/front/login/searchIdForm.web")
	public ModelAndView searchIdForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/front/login/searchIdForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".searchIdForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//로그아웃
	@RequestMapping(value = "/front/login/logout.web")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			HttpSession session = request.getSession(false);
			
			session.invalidate();

			request.setAttribute("redirect"	, "/");
				
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".logout()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	// 로그인 과정
	@RequestMapping(value = "/front/login/loginProc.web", method = RequestMethod.POST)
	public ModelAndView loginProc(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			String id		= request.getParameter("id");
			String passwd	= request.getParameter("passwd");
			
			String check = request.getParameter("remember-check");
			
			/** [개선] 정상 또는 실패 시 IP, Time, Agent 등을 보안상 저장 */
			CustomerDto customerDto = loginSrvc.loginProc(id);
			
			int tc_state = customerDto.getTc_state();
			
			// 해쉬 암호화
			if (customerDto != null
					&&  HSwithSHA.encode(passwd).equals(customerDto.getPasswd())) {
				
				if(1 != tc_state) {
					
					request.setAttribute("script"	, "alert('탈퇴한 회원입니다');");
					request.setAttribute("redirect"	, "/");
					
				}else {
					
				// 대칭키 암호화
				String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
				SKwithAES aes		= new SKwithAES(staticKey);
				
				// 세션에 로그인 정보(로그인 일시 및 ID)를 저장
				HttpSession session = request.getSession(true);
				session.setAttribute("DT_LOGIN", Datetime.getNow("yyyy-MM-dd HH:mm:ss"));
				session.setAttribute("ID", customerDto.getId());
				session.setAttribute("CST_NM", aes.decode(customerDto.getCst_nm()));
				session.setAttribute("SEQ_CST", Integer.toString(customerDto.getSeq_cst()));

				request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/front/front.js\"></script>");
				request.setAttribute("redirect"	, "/");
				
				
					if(check == null) {
						request.setAttribute("script"	, "deleteCookie('key');");
					} else {
						request.setAttribute("script"	, "setCookie('key','" + id + "', 30);");
					}
				
				
				}
			}
			else {
				request.setAttribute("script"	, "alert('아이디와 비밀번호를 확인해주세요!');");
				request.setAttribute("redirect"	, "/front/login/loginForm.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	//로그인 페이지 이동
	@RequestMapping(value = "/front/login/loginForm.web")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {

			mav.setViewName("/front/login/loginForm");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;

	}
	
}
